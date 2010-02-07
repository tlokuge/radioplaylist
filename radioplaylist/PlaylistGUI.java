package radioplaylist;  

import javax.swing.JFrame;

/* Images courtesy of:
 * http://dryicons.com/free-icons/icons-list/blue-velvet/
 */
public class PlaylistGUI
{
    PlayListFrame library_frame;
    ControlFrame control_frame;

    PlayListFrame play_panel;
    ControlFrame  control_panel;

    final int LIB_FRAME_WIDTH   = 800;
    final int LIB_FRAME_HEIGHT  = 500;

    final int CON_FRAME_WIDTH   = 450;
    final int CON_FRAME_HEIGHT  = 235;

    public PlaylistGUI()
    {
        library_frame = new PlayListFrame();
        control_frame = new ControlFrame(library_frame);

        library_frame.setLocation(455,0);
            
        library_frame.setTitle(StringConstantHolder.PGUI_PLYLST_TTL);
        control_frame.setTitle(StringConstantHolder.PGUI_CNTRLS_TTL);
       
        initializeLibraryFrame();
        initializeControlFrame();
    }

    private void initializeLibraryFrame()
    {
        library_frame.setSize(LIB_FRAME_WIDTH, LIB_FRAME_HEIGHT);

        library_frame.setVisible(false);
    }

    private void initializeControlFrame()
    {
        control_frame.setSize(CON_FRAME_WIDTH, CON_FRAME_HEIGHT);
        control_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        control_frame.setResizable(false);
        control_frame.setVisible(true);
    }
}
