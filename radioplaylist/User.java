package radioplaylist;

import java.util.ArrayList;

public class User
{
    private String realname;
    private String username;
    private String password;
    private PlayList library;
    private PlayList commercial;
    private ArrayList<PlayList> playlists;
    private boolean isLoaded;

    public User(String realname, String username, String password, boolean isLoaded)
    {
        this.realname  = realname;
        this.username  = username;
        this.password  = password;
        this.isLoaded  = isLoaded;
        playlists = new ArrayList<PlayList>();
        library = new PlayList(username + "'s Library");
        commercial = new PlayList(username + "'s Commercial");
    }
    
    public void addPlayList(PlayList pl)
    {
        if(pl != null)
            playlists.add(pl);
    }

    public void removePlayList(PlayList pl)
    {
        if(pl != null && playlists.contains(pl))
            playlists.remove(pl);
    }

    public boolean comparePassword(String other_password)
    {
        return password.equals(other_password);
    }

    public void load() { isLoaded = true; }

    public String getRealName()      { return realname; }
    public String getUserName()      { return username; }
    public String getPassword()      { return password; }
    public boolean isLoaded()        { return isLoaded; }
    public PlayList getLibrary()     { return library;  }
    public PlayList getCommercial() { return commercial; }
    public ArrayList<PlayList> getPlayLists() { return playlists; }
}
