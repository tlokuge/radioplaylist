package radioplaylist;

import javax.swing.JFrame;

/* Images courtesy of:
 * http://dryicons.com/free-icons/icons-list/blue-velvet/
 */
public class PlaylistGUI
{
    private PlayListFrame playlist_frame;
    private ControlFrame control_frame;
    private RadioPlayList radio_playlist;

    private final int CON_FRAME_WIDTH   = 450;
    private final int CON_FRAME_HEIGHT  = 235;

    public PlaylistGUI()
    {
        playlist_frame = new PlayListFrame(LoginManager.instance().getCurrentUser());
        control_frame = new ControlFrame(playlist_frame);
        radio_playlist = new RadioPlayList(control_frame);
        
        playlist_frame.setControlFrame(control_frame);
        control_frame.setRadioPlayList(radio_playlist);

        playlist_frame.setTitle(StringConstantHolder.PGUI_PLYLST_TTL);
        control_frame.setTitle(StringConstantHolder.PGUI_CNTRLS_TTL);

        initializeLibraryFrame();
        initializeControlFrame();
    }

    private void initializeLibraryFrame()
    {
        playlist_frame.setExtendedState(playlist_frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        playlist_frame.setVisible(false);
    }

    private void initializeControlFrame()
    {
        control_frame.setSize(CON_FRAME_WIDTH, CON_FRAME_HEIGHT);
        control_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        control_frame.setResizable(false);
        control_frame.setVisible(true);
    }

    public ControlFrame getControlFrame()   { return control_frame; }
    public PlayListFrame getPlayListFrame() { return playlist_frame; }
}
