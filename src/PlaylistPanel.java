package src;




import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PlaylistPanel extends JComponent
{
    // List of songs in playlist order           (JList)
    // List of songs in the library              (JList)
    // Add a playlist (there are different ways) (Button)
    // Show current playing song                 (Text Area)
    // Save playlist to file for opening later   (Button)
    // Also need buttons to insert/delete songs from/to playlist

    JPanel middle_panel;
    JPanel mid_mid_panel;
    JPanel play_control_panel;

    JList playlist_list;
    JList song_list;


    JButton move_up_button;
    JButton move_down_button;
    JButton save_button;
    JButton load_button;
    JButton insert_button;
    JButton delete_button;
    JButton shuffle_button;
    JButton clear_button;

    public PlaylistPanel()
    {
        middle_panel        = new JPanel();
        mid_mid_panel       = new JPanel();
        play_control_panel  = new JPanel();

        playlist_list   = new JList();
        song_list       = new JList();


        ImageIcon shiftup = new ImageIcon("src/images/up.png");
        ImageIcon shiftdown = new ImageIcon("src/images/down.png");
        ImageIcon save_playlist = new ImageIcon("src/images/save.png");
        ImageIcon load_playlist = new ImageIcon("src/images/load.png");
        ImageIcon add_song = new ImageIcon("src/images/add.png");
        ImageIcon delete_song = new ImageIcon("src/images/delete.png");
        ImageIcon shuffle = new ImageIcon("src/images/shuffle.png");
        ImageIcon clear = new ImageIcon("src/images/clear.png");


        move_up_button  = new JButton(shiftup);
        move_down_button= new JButton(shiftdown);
        save_button     = new JButton(save_playlist);
        load_button     = new JButton(load_playlist);
        insert_button   = new JButton(add_song);
        delete_button   = new JButton(delete_song);
        shuffle_button  = new JButton(shuffle);
        clear_button    = new JButton(clear);

        setLayout(new BorderLayout());
        initializePanels();

        add(middle_panel, BorderLayout.CENTER);
        add(play_control_panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void initializeComponents()
    {
        playlist_list.setSelectedIndex(-1);
        song_list.setSelectedIndex(-1);
        playlist_list.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Playlist"));
        song_list.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Song Library"));
    }

    private void doTestStuff()
    {
        String[] str =
        {
            "Test1",
            "Test2",
            "Test3",
            "Test4",
            "Test5",
            "Test6",
            "Test7",
            "Test8",
            "Test9",
            "Test10"
        };

        playlist_list.setListData(str);

        song_list.setListData(str);
    }

    public void addTooltips()
    {
        //Instructions when hovering over buttons
        move_up_button.setToolTipText("Click to move song up.");
        move_down_button.setToolTipText("Click to move song down.");
        save_button.setToolTipText("Click to save your playlist.");
        load_button.setToolTipText("Click to load your playlist.");
        insert_button.setToolTipText("Click to insert a song into your playlist.");
        delete_button.setToolTipText("Click to delete a song in your playlist.");
        shuffle_button.setToolTipText("Click to shuffle your playlist.");
        clear_button.setToolTipText("Click to clear your playlist.");
    }

    public void initializePanels()
    {
        doTestStuff();
        addTooltips();
        
        initializeComponents();

        middle_panel.setLayout(new GridLayout(1, 3));
        middle_panel.add(playlist_list);
        middle_panel.add(mid_mid_panel);
        middle_panel.add(song_list);
        middle_panel.setVisible(true);

        mid_mid_panel.setLayout(new GridLayout(7, 7));
        mid_mid_panel.add(play_control_panel);
        mid_mid_panel.add(move_up_button);
        mid_mid_panel.add(move_down_button);
        mid_mid_panel.add(save_button);
        mid_mid_panel.add(load_button);
        mid_mid_panel.add(insert_button);
        mid_mid_panel.add(delete_button);
        mid_mid_panel.add(shuffle_button);
        mid_mid_panel.add(clear_button);
        mid_mid_panel.setVisible(true);
    }
}
