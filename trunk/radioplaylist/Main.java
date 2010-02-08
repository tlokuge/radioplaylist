package radioplaylist;  

public class Main
{
    public static void main(String[] args)
    {
        PlaylistGUI gui = new PlaylistGUI();
        RadioPlayList radio = new RadioPlayList(gui.getControlFrame(), gui.getPlayListFrame());
        gui.getControlFrame().setRadioPlayList(radio);
    }
}
