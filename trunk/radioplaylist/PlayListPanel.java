package radioplaylist;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

public class PlayListPanel extends JPanel
{
    JPanel main_panel;
    JPanel play_control_panel;

    PlayList song_library_list;

    JTable song_library_table;

    JTabbedPane playlist_tab;

    JButton move_up_button;
    JButton move_down_button;
    JButton save_button;
    JButton load_button;
    JButton add_song_button;
    JButton remove_song_button;
    JButton add_playlist_button;
    JButton remove_playlist_button;
    JButton shuffle_button;
    JButton clear_button;
    
    public PlayListPanel()
    {
        main_panel         = new JPanel();
        play_control_panel = new JPanel();

        song_library_list  = new PlayList();
        song_library_table = new JTable();
        playlist_tab       = new JTabbedPane();

        setLayout(new BorderLayout());
        initializeButtons();
        initializePlaylistTabs();
        initializeComponents();
        initializePanels();

        {
            doTestStuff();
        }

        add(main_panel, BorderLayout.CENTER);
        add(play_control_panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeComponents()
    {
        song_library_list.setSelectedIndex(-1);
        song_library_list.setBorder(
                BorderFactory.createTitledBorder(new EtchedBorder(), StringConstantHolder.PP_LIBRARY_PANEL));
    }

    private void doTestStuff()
    {
        PlayList p1 = createPlayList("Sample PlayList 1");
        PlayList p2 = createPlayList("Sample PlayList 2");
        PlayList p3 = createPlayList("Sample PlayList 3");
        PlayList p4 = createPlayList("Sample PlayList 4");

        Song s1 = new Song(1, "Song 1", "Artist 1", "Album 1", "RecType 1", 450, 1, 1);
        Song s2 = new Song(2, "Song 2", "Artist 2", "Album 2", "RecType 2", 680, 2, 2);
        Song s3 = new Song(3, "Song 3", "Artist 3", "Album 3", "RecType 3", 900, 3, 3);
        
        addSongToLibrary(s1);
        addSongToLibrary(s2);
        addSongToLibrary(s3);
        
        p1.addSong(s1);
        p1.addSong(s2);
        p1.addSong(s3);

        p2.addSong(s1);
        p2.addSong(s2);

        p3.addSong(s1);

        addPlayListToTab(p1, false);
        addPlayListToTab(p2, false);
        addPlayListToTab(p3, true);
        addPlayListToTab(p4, false);
    }

    private void initializeButtons()
    {
        // Initializing buttons
        move_up_button         = createButton(StringConstantHolder.PP_UP_NM, StringConstantHolder.PP_UP_IMG);
        move_down_button       = createButton(StringConstantHolder.PP_DN_NM, StringConstantHolder.PP_DN_IMG);
        save_button            = createButton(StringConstantHolder.PP_SV_NM, StringConstantHolder.PP_SV_IMG);
        load_button            = createButton(StringConstantHolder.PP_LD_NM, StringConstantHolder.PP_LD_IMG);
        add_song_button        = createButton(StringConstantHolder.PP_ADDSG_NM, StringConstantHolder.PP_ADDSG_IMG);
        remove_song_button     = createButton(StringConstantHolder.PP_REMSG_NM, StringConstantHolder.PP_REMPL_IMG);
        add_playlist_button    = createButton(StringConstantHolder.PP_ADDPL_NM, StringConstantHolder.PP_ADDPL_IMG);
        remove_playlist_button = createButton(StringConstantHolder.PP_REMPL_NM, StringConstantHolder.PP_REMPL_IMG);
        shuffle_button         = createButton(StringConstantHolder.PP_RDM_NM, StringConstantHolder.PP_RDM_IMG);
        clear_button           = createButton(StringConstantHolder.PP_CLR_NM, StringConstantHolder.PP_CLR_IMG);

        //Instructions when hovering over buttons
        move_up_button.setToolTipText(StringConstantHolder.PP_UP_TT);
        move_down_button.setToolTipText(StringConstantHolder.PP_DN_TT);
        save_button.setToolTipText(StringConstantHolder.PP_SV_TT);
        load_button.setToolTipText(StringConstantHolder.PP_LD_TT);
        add_song_button.setToolTipText(StringConstantHolder.PP_ADD_TT);
        remove_song_button.setToolTipText(StringConstantHolder.PP_DEL_TT);
        add_playlist_button.setToolTipText(StringConstantHolder.PP_ADD_PL);
        remove_playlist_button.setToolTipText(StringConstantHolder.PP_REM_PL);
        shuffle_button.setToolTipText(StringConstantHolder.PP_RDM_TT);
        clear_button.setToolTipText(StringConstantHolder.PP_CLR_TT);
        
        move_up_button.addActionListener(new PlayListButtonListener(this, ButtonType.MVUP));
        move_down_button.addActionListener(new PlayListButtonListener(this, ButtonType.MVDN));
        save_button.addActionListener(new PlayListButtonListener(this, ButtonType.SAVE));
        load_button.addActionListener(new PlayListButtonListener(this, ButtonType.LOAD));
        add_song_button.addActionListener(new PlayListButtonListener(this, ButtonType.ADD_SONG));
        remove_song_button.addActionListener(new PlayListButtonListener(this, ButtonType.REMOVE_SONG));
        shuffle_button.addActionListener(new PlayListButtonListener(this, ButtonType.RANDOMIZE));
        clear_button.addActionListener(new PlayListButtonListener(this, ButtonType.CLEAR));
        add_playlist_button.addActionListener(new PlayListButtonListener(this, ButtonType.ADD_PLAYLIST));
        remove_playlist_button.addActionListener(new PlayListButtonListener(this, ButtonType.REMOVE_PLAYLIST));
    }

    private void initializePlaylistTabs()
    {
        playlist_tab.setTabPlacement(JTabbedPane.LEFT);
    }

    private Component initializeSongLibrary()
    {
        PlayListTableModel model = new PlayListTableModel(song_library_list);
        song_library_table.setModel(model);
        RowSorter<PlayListTableModel> sorter = new TableRowSorter<PlayListTableModel>(model);
        song_library_table.setRowSorter(sorter);

        return new JScrollPane(song_library_table);
    }

    public PlayList createPlayList(String name)
    {
        if(name == null || name.isEmpty())
            name = StringConstantHolder.PL_NEW_PL;

        PlayList pl = new PlayList(name);
        pl.setBorder(new EtchedBorder());
        pl.setSelectedIndex(-1);
        return pl;
    }

    public void addPlayListToTab(PlayList pl, boolean select)
    {
        for(Component c : playlist_tab.getComponents())
            if((c instanceof PlayList) && ((PlayList)c).equals(pl))
                return;

        playlist_tab.add(pl, pl.getName());
        if(select)
            playlist_tab.setSelectedComponent(pl);
    }

    public void removePlayList(PlayList pl)
    {
        playlist_tab.remove(pl);
        pl = null;
    }

    public void addSongToLibrary(Song song)
    {
        if(song_library_list.containsSong(song))
            return;

        song_library_list.addSong(song);
    }

    private void initializePanels()
    {
        main_panel.setLayout(new GridLayout(1, 3));
        main_panel.add(playlist_tab);
        main_panel.add(initializeSongLibrary(), song_library_list.getName());
        main_panel.setVisible(true);

        play_control_panel.setLayout(new GridLayout(1, 7));
        play_control_panel.add(move_up_button);
        play_control_panel.add(move_down_button);
        play_control_panel.add(save_button);
        play_control_panel.add(load_button);
        play_control_panel.add(add_song_button);
        play_control_panel.add(remove_song_button);
        play_control_panel.add(add_playlist_button);
        play_control_panel.add(remove_playlist_button);
        play_control_panel.add(shuffle_button);
        play_control_panel.add(clear_button);
        play_control_panel.setVisible(true);
    }

    public PlayList getCurrentPlayList()
    {
        if(playlist_tab.getSelectedComponent() instanceof PlayList)
            return (PlayList)playlist_tab.getSelectedComponent();

        return null;
    }

    public Song getSelectedLibrarySong()
    {
        return ((PlayListTableModel)song_library_table.getModel())
                .getPlayList().getSongAt(
                song_library_table.getRowSorter().convertRowIndexToModel(
                song_library_table.getSelectedRow()));
    }

    public JTable getSongLibraryTable()
    {
        return song_library_table;
    }

    private JButton createButton(String name, String image_path)
    {
        JButton button = new JButton();
        File imageFile = new File(image_path);
        if(imageFile.exists())
            button.setIcon(new ImageIcon(image_path));
        else
            button.setText(name);

        return button;
    }
}
