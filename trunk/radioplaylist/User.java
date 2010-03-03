package radioplaylist;

public class User
{
    private String name;
    private String username;
    private String password;
    private PlayList library;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void setSongLibrary(PlayList library)
    {
        this.library = library;
    }

    public boolean comparePassword(String other_password)
    {
        return password.equals(other_password);
    }

    public String getUserName() { return username; }
}
