package radioplaylist;

public class Main
{
    public static void main(String[] args)
    {
        PlaylistGUI gui = new PlaylistGUI();
        RadioPlayList radio = new RadioPlayList(gui.getControlFrame());
        gui.getControlFrame().setRadioPlayList(radio);

        LoginManager m = new LoginManager();
        m.addUser("Generic User 1", "username1", "Somepass3");
        if(!m.login("username1", "Somepass3"))
            System.out.println("Login fail");

        User u = m.getCurrentUser();
        u.setSongLibrary(PlayListFrame.createPlayList("dsada"));
        for(int i = 0; i < 10; ++i)
            u.addPlayList(PlayListFrame.createPlayList("PlayList " + (i + 1)));

        if(m.saveCurrentUser())
            System.out.println("Saved!");
        else
            System.out.println("Save fail :(");

        m.addUser("Generic User 2", "username2", "Somepass3");
        if(!m.login("username2", "Somepass3"))
            System.out.println("Login2 failed");

        u = m.getCurrentUser();
        u.setSongLibrary(PlayListFrame.createPlayList("qwiueq"));
        for(int i = 0; i < 5; ++i)
            u.addPlayList(PlayListFrame.createPlayList("PlayList " + (i + 9)));

        if(m.saveCurrentUser())
            System.out.println("Saved2");
        else
            System.out.println("Save failed :(");
    }
}
