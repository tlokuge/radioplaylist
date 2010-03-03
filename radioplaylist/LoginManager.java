
package radioplaylist;

import java.util.ArrayList;

public class LoginManager
{
    private ArrayList<User> users;

    public LoginManager()
    {
        users = new ArrayList<User>();
    }

    public void addUser(String username, String password)
    {
        users.add(new User(username, password));
    }

    public User getUser(String username, String password)
    {
        for(User user : users)
            if(user.getUserName().equals(username) &&
            user.comparePassword(password))
                return user;

        return null;
    }
}
