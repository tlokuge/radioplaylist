package radioplaylist;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

public class PlayListFrame extends JFrame
{
    private NewSongFrame new_song_frame;

    private ControlFrame control_frame;

    private JPanel main_panel;
    private JPanel play_control_panel;

    private PlayList song_library_list;

    private JTable song_library_table;

    private JTabbedPane playlist_tab;

    private JButton move_up_button;
    private JButton move_down_button;
    private JButton add_song_button;
    private JButton remove_song_button;
    private JButton add_songpl_button;
    private JButton remove_songpl_button;
    private JButton add_playlist_button;
    private JButton remove_playlist_button;
    private JButton shuffle_button;
    private JButton clear_button;

    private JMenuBar menuBar;
    
    public PlayListFrame()
    {
        new_song_frame     = new NewSongFrame(this);

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
        initializeMenus();

        {
            doTestStuff();
        }

        add(main_panel, BorderLayout.CENTER);
        add(play_control_panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new PlayListWindowListener());
        new_song_frame.setVisible(false);
        setVisible(true);
    }

    public void setControlFrame(ControlFrame frame)
    {
        control_frame = frame;
    }

    private void initializeComponents()
    {
        song_library_list.setSelectedIndex(-1);
        song_library_list.setBorder(
                BorderFactory.createTitledBorder(new EtchedBorder(), StringConstantHolder.PP_LIBRARY_PANEL));
    }

    private void initializeMenus()
    {
        menuBar = new JMenuBar();

        JMenu menu = new JMenu(StringConstantHolder.PP_MN_BR);
        
        JMenuItem loadItem     = new JMenuItem(StringConstantHolder.PP_LD_NM);
        JMenuItem saveItem     = new JMenuItem(StringConstantHolder.PP_SV_NM);
        JMenuItem newSongItem  = new JMenuItem(StringConstantHolder.PP_ADD_NM);
        JMenuItem delSongItem  = new JMenuItem(StringConstantHolder.PP_DEL_NM);
        JMenuItem addSongItem  = new JMenuItem(StringConstantHolder.PP_ADDSG_NM);
        JMenuItem remSongItem  = new JMenuItem(StringConstantHolder.PP_REMSG_NM);
        JMenuItem addPLItem    = new JMenuItem(StringConstantHolder.PP_ADDPL_NM);
        JMenuItem remPLItem    = new JMenuItem(StringConstantHolder.PP_REMPL_NM);
        JMenuItem randItem     = new JMenuItem(StringConstantHolder.PP_RDM_NM);
        JMenuItem clearItem    = new JMenuItem(StringConstantHolder.PP_CLR_NM);

        loadItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.LOAD));
        saveItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.SAVE));
        newSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONG));
        delSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONG));
        addSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONGPL));
        remSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONGPL));
        addPLItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_PLAYLIST));
        remPLItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_PLAYLIST));
        randItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.RANDOMIZE));
        clearItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.CLEAR));

        menu.add(loadItem);
        menu.add(saveItem);
        menu.add(newSongItem);
        menu.add(delSongItem);
        menu.add(addSongItem);
        menu.add(remSongItem);
        menu.add(addPLItem);
        menu.add(remPLItem);
        menu.add(randItem);
        menu.add(clearItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void doTestStuff()
    {
        PlayList p1 = createPlayList("Sample PlayList 1");
        PlayList p2 = createPlayList("Sample PlayList 2");
        PlayList p3 = createPlayList("Sample PlayList 3");
        PlayList p4 = createPlayList("Sample PlayList 4");

        Song s1 = new Song(1, "Song 1", "Artist 1", "Album 1", "1", 450, 1, 1);
        Song s2 = new Song(2, "Song 2", "Artist 2", "Album 2", "2", 680, 2, 2);
        Song s3 = new Song(3, "Song 3", "Artist 3", "Album 3", "3", 900, 3, 3);
        
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
        add_song_button        = createButton(StringConstantHolder.PP_ADD_NM, StringConstantHolder.PP_ADD_IMG);
        remove_song_button     = createButton(StringConstantHolder.PP_DEL_NM, StringConstantHolder.PP_DEL_IMG);
        add_songpl_button      = createButton(StringConstantHolder.PP_ADDSG_NM, StringConstantHolder.PP_ADDSG_IMG);
        remove_songpl_button   = createButton(StringConstantHolder.PP_REMSG_NM, StringConstantHolder.PP_REMSG_IMG);
        add_playlist_button    = createButton(StringConstantHolder.PP_ADDPL_NM, StringConstantHolder.PP_ADDPL_IMG);
        remove_playlist_button = createButton(StringConstantHolder.PP_REMPL_NM, StringConstantHolder.PP_REMPL_IMG);
        shuffle_button         = createButton(StringConstantHolder.PP_RDM_NM, StringConstantHolder.PP_RDM_IMG);
        clear_button           = createButton(StringConstantHolder.PP_CLR_NM, StringConstantHolder.PP_CLR_IMG);

        //Instructions when hovering over buttons
        move_up_button.setToolTipText(StringConstantHolder.PP_UP_TT);
        move_down_button.setToolTipText(StringConstantHolder.PP_DN_TT);
        add_song_button.setToolTipText(StringConstantHolder.PP_ADD_TT);
        remove_song_button.setToolTipText(StringConstantHolder.PP_DEL_TT);
        add_songpl_button.setToolTipText(StringConstantHolder.PP_ADDPL_TT);
        remove_songpl_button.setToolTipText(StringConstantHolder.PP_DELPL_TT);
        add_playlist_button.setToolTipText(StringConstantHolder.PP_ADD_PL);
        remove_playlist_button.setToolTipText(StringConstantHolder.PP_REM_PL);
        shuffle_button.setToolTipText(StringConstantHolder.PP_RDM_TT);
        clear_button.setToolTipText(StringConstantHolder.PP_CLR_TT);
        
        move_up_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.MVUP));
        move_down_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.MVDN));
        add_song_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONG));
        remove_song_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONG));
        add_songpl_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONGPL));
        remove_songpl_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONGPL));
        shuffle_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.RANDOMIZE));
        clear_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.CLEAR));
        add_playlist_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_PLAYLIST));
        remove_playlist_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_PLAYLIST));
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

    public void showNewSongFrame()
    {
        new_song_frame.resetFields();
        new_song_frame.setVisible(true);
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
        song_library_table.revalidate();
    }

    public void removeSongFromLibrary(Song song)
    {
        if(!song_library_list.containsSong(song))
            return;

        song_library_list.deleteSong(song);
        song_library_table.revalidate();
    }

    private void initializePanels()
    {
        main_panel.setLayout(new GridLayout(1, 3));
        main_panel.add(playlist_tab);
        main_panel.add(initializeSongLibrary(), song_library_list.getName());
        main_panel.setVisible(true);

        play_control_panel.setLayout(new GridLayout(1, 9));
        play_control_panel.add(move_up_button);
        play_control_panel.add(move_down_button);
        play_control_panel.add(add_song_button);
        play_control_panel.add(remove_song_button);
        play_control_panel.add(add_songpl_button);
        play_control_panel.add(remove_songpl_button);
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
        if(song_library_table.getSelectedRow() < 0)
            return null;
        
        return ((PlayListTableModel) song_library_table.getModel())
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
        String path = StringConstantHolder.RD_PLYLST_IMG_FLDR + image_path;
        if(new File(path).exists())
            button.setIcon(new ImageIcon(path));
        else
        {
            System.err.println(StringConstantHolder.IMG_LOAD_ERR + path);
            button.setText(name);
        }

        return button;
    }

    class PlayListWindowListener implements WindowListener
    {
        public void windowOpened(WindowEvent e)      {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e)   {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowActivated(WindowEvent e)   {}
        public void windowClosed(WindowEvent e)      {}
        public void windowClosing(WindowEvent e)
        {
            if(control_frame != null)
                control_frame.togglePlayListFrame(false);
        }
    }
}
