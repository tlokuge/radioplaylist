
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

    public LoginManager()
    {
        users = new ArrayList<User>();
        current_user = null;
        users_file = null;

        initialize();
    }

    public void initialize()
    {
        File user_folder = new File("src/users/");
        users_file = new File("src/users/users.txt");
        try
        {
            if(!user_folder.exists() && !user_folder.mkdir())
            {
                RadioPlayList.sendErrorDialog("Unable to create user folder. Aborting program", "Save Error");
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
            }
            else if(!users_file.exists() && !users_file.createNewFile())
            {
                RadioPlayList.sendErrorDialog("Unable to create users file. Aborting program", "Save Error!");
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
            RadioPlayList.sendAlertDialog("That username is taken", "User Error");
            return;
        }

        String hashed_pass = SHA1(password);
        if(hashed_pass != null)
            password = hashed_pass;

        users.add(new User(realname, username, password, true));
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
        String hashed_pass = SHA1(password);
        if(hashed_pass == null)
            return null;

        User user = getUser(username, hashed_pass);
        if(user == null)
        {
            RadioPlayList.sendAlertDialog("Invalid Username", "Login Alert!");
            return null;
        }

        if(!user.isLoaded())
            loadUser(user);

        current_user = user;

        return current_user;
    }

    public boolean loadUser(String username)
    {
        int index = contains(username);
        if(index == -1)
            return false;

        return loadUser(users.get(index));
    }

    private boolean userFileContains(String username)
    {
        if(users_file == null)
            return false;

        try
        {
            Scanner in = new Scanner(new FileReader(users_file));
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                StringTokenizer strtok = new StringTokenizer(line, " ");
                if(strtok.countTokens() == 0)
                    break;

                String u_name = strtok.nextToken();
                System.out.println(u_name);
                if(username.equals(u_name))
                    return true;

                while(strtok.hasMoreTokens()) { strtok.nextToken(); }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loadUser(User user)
    {
        if(user == null)
            return false;

        File user_folder = new File("src/users/" + user.getUserName());
        if(!user_folder.exists())
        {
            RadioPlayList.sendErrorDialog("Cannot load user that was not saved!", "Load Error!");
            return false;
        }

        PlayList pl = new PlayList();
        if(!pl.loadPlaylist(new File(user_folder.getAbsolutePath() + File.separator
                + "library.txt")))
        {
            RadioPlayList.sendErrorDialog("Unable to load user library for " + user.getUserName(), "Load Error");
            return false;
        }

        File list_file = new File(user_folder.getAbsolutePath() + File.separator
                + user.getUserName() + "_playlists.txt");
        if(!list_file.exists())
            return true;

        try
        {
            Scanner in = new Scanner(new FileReader(list_file));
            while(in.hasNextLine())
            {
                pl = new PlayList();
                String path = user_folder.getPath() + File.separator
                        + in.nextLine() + ".txt";
                File playlist_file = new File(path);
                if(pl.loadPlaylist(playlist_file))
                    user.addPlayList(pl);
            }
        }
        catch(Exception e)
        {
            System.err.println("Load Error");
            e.printStackTrace();
        }

        user.load();
        return true;
    }

    public boolean saveCurrentUser()
    {
        if(current_user == null)
            return false;

        File user_folder = new File("src/users/" + current_user.getUserName());
        if(!user_folder.exists() && !user_folder.mkdir())
        {
            RadioPlayList.sendErrorDialog("Unable to create user folder", "Save Error!");
            return false;
        }

        try
        {
            if(users_file == null)
            {
                RadioPlayList.sendErrorDialog("Unable to save user" + current_user.getUserName(), "User Save Error");
                return false;
            }

            if(!userFileContains(current_user.getUserName()))
            {
                PrintWriter out = new PrintWriter(new FileWriter(users_file, true));
                out.write(current_user.getUserName() + " "
                        + current_user.getPassword() + " "
                        + current_user.getRealName() + "\n");
                out.close();
            }

            File save_file;
            save_file = new File(user_folder.getAbsolutePath() + File.separator
                    + "library.txt");
            if(!save_file.exists() && save_file.createNewFile())
                current_user.getLibrary().savePlaylist(save_file);

            if(current_user.getPlayLists().size() < 1)
                return true;

            PlayList current;
            String playlist_names = "";

            for(int i = 0; i < current_user.getPlayLists().size(); ++i)
            {
                current = current_user.getPlayLists().get(i);
                save_file = new File(user_folder.getAbsolutePath() +
                        File.separator + current.getName() + ".txt");
                if(!save_file.exists() && save_file.createNewFile())
                {
                    playlist_names += current.getName() + "\n";
                    current.savePlaylist(save_file);
                }
                else
                    RadioPlayList.sendAlertDialog("Unable to create playlist file " + current.getName(), "Save Error!");
            }

            save_file = new File(user_folder.getAbsolutePath() + File.separator +
                    current_user.getUserName() + "_playlists.txt");
            if(save_file.exists() || save_file.createNewFile())
            {
                PrintWriter out = new PrintWriter(new FileWriter(save_file, true));
                out.write(playlist_names);
                out.close();
            }
        }
        catch(Exception e)
        {
            RadioPlayList.sendErrorDialog(e.getLocalizedMessage(), "User Save Error");
            e.printStackTrace();
            return false;
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
}
