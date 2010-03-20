
package radioplaylist;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginManager
{
    private ArrayList<User> users;
    private User current_user;
    private File users_file;

    private static LoginManager instance;

    private LoginManager()
    {
        users = new ArrayList<User>();
        current_user = null;
        users_file = null;

        initialize();
    }

    public void initialize()
    {
        File user_folder = new File(StringConstantHolder.RD_PLYLST_USR_FLDR);
        users_file = new File(StringConstantHolder.RD_PLYLST_USR_FLDR
                + StringConstantHolder.RD_PLYLST_USR_FILE);
        try
        {
            if(!user_folder.exists() && !user_folder.mkdir())
            {
                RadioPlayList.sendErrorDialog(StringConstantHolder.LGN_MGR_USR_FLDR_ERR,
                        StringConstantHolder.LGN_MGR_SAVE_ERR_TTL);
                System.exit(1);
            }

            if(users_file.exists())
            {
                Scanner in = new Scanner(new FileReader(users_file));
                while(in.hasNextLine())
                {
                    String line = in.nextLine();
                    StringTokenizer strtok = new StringTokenizer(line, " ");
                    if(!strtok.hasMoreTokens())
                        break;
                    String username = strtok.nextToken();
                    if(!strtok.hasMoreTokens())
                        break;
                    String password = strtok.nextToken();
                    String realname = "";
                    while(strtok.hasMoreTokens())
                        realname += strtok.nextToken() + " ";

                    users.add(new User(realname, username, password, false));
                }
                in.close();
            }
            else if(!users_file.exists() && !users_file.createNewFile())
            {
                RadioPlayList.sendErrorDialog(StringConstantHolder.LGN_MGR_USR_FILE_ERR,
                        StringConstantHolder.LGN_MGR_SAVE_ERR_TTL);
                System.exit(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addUser(String realname, String username, String password)
    {
        if(contains(username) != -1)
        {
            RadioPlayList.sendAlertDialog(StringConstantHolder.LGN_MGR_USRNME_TAKEN,
                    StringConstantHolder.LGN_MGR_USR_ERR_TTL);
            return;
        }

        String hashed_pass = SHA1(password);
        if(hashed_pass != null)
            password = hashed_pass;

        User u = new User(realname, username, password, true);
        users.add(u);
        saveUser(u);
    }

    public int contains(String username)
    {
        for(int i = 0; i < users.size(); ++i)
            if(users.get(i).getUserName().equals(username))
                return i;

        return -1;
    }

    private User getUser(String username, String password)
    {
        int index = contains(username);
        if(index == -1)
            return null;

        if(index != -1 && users.get(index).comparePassword(password))
            return users.get(index);

        return null;
    }

    public User login(String username, String password)
    {
        if(contains(username) == -1)
        {
            RadioPlayList.sendAlertDialog(StringConstantHolder.LGN_MGR_UNK_USR_PASS,
                    StringConstantHolder.LGN_MGR_USR_ERR_TTL);
            return null;
        }

        String hashed_pass = SHA1(password);
        User user = getUser(username, hashed_pass);
        if(user == null)
        {
            RadioPlayList.sendAlertDialog(StringConstantHolder.LGN_MGR_UNK_USR_PASS,
                    StringConstantHolder.LGN_MGR_USR_ERR_TTL);
            return null;
        }

        if(!user.isLoaded())
            if(loadUser(user))
                user.load();

        current_user = user;

        RadioPlayList.sendAlertDialog(String.format(StringConstantHolder.LGN_MGR_LOGGED_IN_AS, user.getRealName()),
                StringConstantHolder.LGN_MGR_USR_ERR_TTL);
        return current_user;
    }

    private boolean loadUser(User user)
    {
        if(user == null)
            return false;

        File user_folder = new File(StringConstantHolder.RD_PLYLST_USR_FLDR + user.getUserName());
        if(!user_folder.exists())
        {
            RadioPlayList.sendErrorDialog(StringConstantHolder.LGN_MGR_LD_ERR_1,
                    StringConstantHolder.LGN_MGR_LD_ERR_TTL);
            return false;
        }

        if(!user.getLibrary().loadPlaylist(new File(user_folder.getAbsolutePath() + File.separator
                + StringConstantHolder.RD_PLYLST_LBR_FILE)))
        {
            RadioPlayList.sendErrorDialog(String.format(StringConstantHolder.LGN_MGR_LD_ERR_2, user.getUserName())
                    , StringConstantHolder.LGN_MGR_LD_ERR_TTL);
            return false;
        }

        File list_file = new File(user_folder.getAbsolutePath() + File.separator
                + user.getUserName() + StringConstantHolder.RD_PLYLST_PL_FILE);
        if(!list_file.exists())
            return true;

        try
        {
            Scanner in = new Scanner(new FileReader(list_file));
            PlayList pl;
            while(in.hasNextLine())
            {
                pl = new PlayList();
                String pl_name = in.nextLine();
                String path = user_folder.getPath() + File.separator
                        + pl_name + ".txt";
                File playlist_file = new File(path);
                if(pl.loadPlaylist(playlist_file))
                    user.addPlayList(pl);
                else
                    RadioPlayList.sendErrorDialog(
                            String.format(StringConstantHolder.LGN_MGR_PL_LD_ERR, pl_name),
                            StringConstantHolder.LGN_MGR_LD_ERR_TTL);
            }
            in.close();
        }
        catch(Exception e)
        {
            RadioPlayList.sendErrorDialog(e.getMessage(), StringConstantHolder.LGN_MGR_LD_ERR_TTL);
            e.printStackTrace();
        }

        return true;
    }

    public boolean saveCurrentUser()
    {
        return saveUser(current_user);
    }

    private boolean saveUser(User u)
    {
        if(u == null || !users.contains(u))
            return false;

        String userName = u.getUserName();
        File user_folder = new File(StringConstantHolder.RD_PLYLST_USR_FLDR + userName);
        if(!user_folder.exists() && !user_folder.mkdir())
        {
            RadioPlayList.sendErrorDialog(StringConstantHolder.LGN_MGR_USR_FLDR_ERR,
                    StringConstantHolder.LGN_MGR_SAVE_ERR_TTL);
            return false;
        }

        try
        {
            if(users_file == null)
            {
                RadioPlayList.sendErrorDialog(StringConstantHolder.LGN_MGR_SAVE_ERR_1 + userName,
                        StringConstantHolder.LGN_MGR_SAVE_ERR_TTL);
                return false;
            }

            PrintWriter out = new PrintWriter(new FileWriter(users_file, true));
            out.write(userName + " "
                    + u.getPassword() + " "
                    + u.getRealName() + "\n");
            out.close();

            File save_file;
            save_file = new File(user_folder.getAbsolutePath() + File.separator
                    + StringConstantHolder.RD_PLYLST_LBR_FILE);
            if(save_file.exists() || save_file.createNewFile())
                u.getLibrary().savePlaylist(save_file);
            
            if(u.getPlayLists().size() < 1)
                return true;

            PlayList current;
            String playlist_names = "";

            for(int i = 0; i < u.getPlayLists().size(); ++i)
            {
                current = u.getPlayLists().get(i);
                save_file = new File(user_folder.getAbsolutePath() +
                        File.separator + current.getName() + ".txt");
                if(save_file.exists())
                    current.savePlaylist(save_file);
                else if(save_file.createNewFile())
                {
                    playlist_names += current.getName() + "\n";
                    current.savePlaylist(save_file);
                }
                else
                    RadioPlayList.sendAlertDialog(
                            String.format(StringConstantHolder.LGN_MGR_SAVE_ERR_2, current.getName(), u.getUserName()),
                            StringConstantHolder.LGN_MGR_SAVE_ERR_TTL);
            }

            save_file = new File(user_folder.getAbsolutePath() + File.separator +
                    userName + StringConstantHolder.RD_PLYLST_PL_FILE);
            if(save_file.exists() || save_file.createNewFile())
            {
                out = new PrintWriter(new FileWriter(save_file, true));
                out.write(playlist_names);
                out.flush();
                out.close();
            }
        }
        catch(Exception e)
        {
            RadioPlayList.sendErrorDialog(e.getLocalizedMessage(),
                    StringConstantHolder.LGN_MGR_SAVE_ERR_TTL);
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deletePlayList(User u, PlayList pl)
    {
        if(u == null || pl == null)
            return false;

        File pl_list_file = new File(StringConstantHolder.RD_PLYLST_USR_FLDR + u.getUserName()
                + File.separator + u.getUserName() + StringConstantHolder.RD_PLYLST_PL_FILE);
        if(!pl_list_file.exists())
            return false;

        String pl_name = pl.getName();
        try
        {
            String pl_list = "";
            Scanner in = new Scanner(pl_list_file);
            boolean exists = false;
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                if(line.equals(pl_name))
                {
                    exists = true;
                    break;
                }
                pl_list += line + "\n";
            }
            in.close();
            if(!exists)
                return false;
            File pl_file = new File(StringConstantHolder.RD_PLYLST_USR_FLDR + u.getUserName()
                    + File.separator + pl_name + ".txt");
            if(pl_file.exists())
                pl_file.delete();
            PrintWriter out = new PrintWriter(new FileWriter(pl_list_file));
            out.write(pl_list);
            out.flush();
            out.close();
            u.removePlayList(pl);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    private static String convertToHex(byte[] data)
    {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < data.length; ++i)
        {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do
            {
                if((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            }
            while(two_halfs++ < 1);
        }

        return buf.toString();
    }

    public static String SHA1(String text)
    {
        try
        {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public User getCurrentUser() { return current_user; }

    public static synchronized LoginManager instance()
    {
        if(instance == null)
            instance = new LoginManager();

        return instance;
    }

    public Object clone()
            throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
}
