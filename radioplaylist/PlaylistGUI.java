package radioplaylist;  

import javax.swing.JFrame;

/* Images courtesy of:
 * http://dryicons.com/free-icons/icons-list/blue-velvet/
 */
public class PlaylistGUI
{
    private PlayListFrame playlist_frame;
    private ControlFrame control_frame;

    private final int LIB_FRAME_WIDTH   = 800;
    private final int LIB_FRAME_HEIGHT  = 500;

    private final int CON_FRAME_WIDTH   = 450;
    private final int CON_FRAME_HEIGHT  = 235;

    public PlaylistGUI()
    {
        playlist_frame = new PlayListFrame();
        control_frame = new ControlFrame(playlist_frame);
        playlist_frame.setControlFrame(control_frame);

        playlist_frame.setLocation(455,0);
            
        playlist_frame.setTitle(StringConstantHolder.PGUI_PLYLST_TTL);
        control_frame.setTitle(StringConstantHolder.PGUI_CNTRLS_TTL);
       
        initializeLibraryFrame();
        initializeControlFrame();
    }

    private void initializeLibraryFrame()
    {
        playlist_frame.setSize(LIB_FRAME_WIDTH, LIB_FRAME_HEIGHT);

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
