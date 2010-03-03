package radioplaylist;

import java.util.ArrayList;

public class User
{
    private String realname;
    private String username;
    private String password;
    private PlayList library;
    private ArrayList<PlayList> playlists;

    public User(String realname, String username, String password)
    {
        this.realname = realname;
        this.username  = username;
        this.password  = password;
        playlists = new ArrayList<PlayList>();
    }

    public void setSongLibrary(PlayList library)
    {
        this.library = library;
    }

    public void addPlayList(PlayList pl)
    {
        if(pl != null)
            playlists.add(pl);
    }

    public boolean comparePassword(String other_password)
    {
        return password.equals(other_password);
    }

    public String getRealName()  { return realname; }
    public String getUserName()  { return username; }
    public PlayList getLibrary() { return library;  }
    public ArrayList<PlayList> getPlayLists() { return playlists; }
}
