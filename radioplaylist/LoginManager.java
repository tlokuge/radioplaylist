
package radioplaylist;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LoginManager
{
    private ArrayList<User> users;
    private User current_user;

    public LoginManager()
    {
        users = new ArrayList<User>();
        current_user = null;

        initialize();
    }

    public void initialize()
    {
    }

    public void addUser(String realname, String username, String password)
    {
        users.add(new User(realname, username, password));
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
        if(index != -1 && users.get(index).comparePassword(password))
            return users.get(index);

        return null;
    }

    public boolean login(String username, String password)
    {
        User user = getUser(username, password);
        if(user == null)
            return false;

        current_user = user;

        return true;
    }

    public boolean saveCurrentUser()
    {
        if(current_user == null)
            return false;

        File user_folder = new File("src/users/" + current_user.getUserName());
        if(!user_folder.exists() && !user_folder.mkdir())
            return false;
        else
            System.out.println("Exists");

        try
        {
            File save_file;
            save_file = new File(user_folder.getAbsolutePath() + File.separator
                    + "library.txt");
            if(current_user.getLibrary() != null &&
                    !save_file.exists() && save_file.createNewFile())
            {
                System.out.println("Saving Library...");
                current_user.getLibrary().savePlaylist(save_file);
            }

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
                    System.out.println("Saving: " + current.getName());
                    playlist_names += current.getName() + "\n";
                    current.savePlaylist(save_file);
                }
                else
                    System.out.println("Save Failed: " + save_file.getName());
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

    public User getCurrentUser() { return current_user; }
}
