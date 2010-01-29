
package src;

import javax.swing.JFrame;

public class PlaylistGUI
{
    JFrame library_frame;
    JFrame control_frame;


    PlaylistPanel play_panel;
    ControlPanel  control_panel;

    final int LIB_FRAME_WIDTH   = 700;
    final int LIB_FRAME_HEIGHT  = 500;

    final int CON_FRAME_WIDTH   = 600;
    final int CON_FRAME_HEIGHT  = 222;

    public PlaylistGUI()
    {
        library_frame = new JFrame();
        control_frame = new JFrame();

        library_frame.setLocation(600,0);
            
        library_frame.setTitle(" Song Playlist");
        control_frame.setTitle(" Playlist Controls");
        
        play_panel    = new PlaylistPanel();
        control_panel = new ControlPanel(library_frame);

        initializeLibraryFrame();
        initializeControlFrame();
    }

    private void initializeLibraryFrame()
    {
        library_frame.setSize(LIB_FRAME_WIDTH, LIB_FRAME_HEIGHT);

        library_frame.add(play_panel);
        library_frame.setVisible(false);
    }

    private void initializeControlFrame()
    {
        control_frame.setSize(CON_FRAME_WIDTH, CON_FRAME_HEIGHT);
        control_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        control_frame.add(control_panel);
        control_frame.setVisible(true);
    }
}
