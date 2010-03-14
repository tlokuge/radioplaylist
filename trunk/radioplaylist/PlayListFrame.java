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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class PlayListFrame extends JFrame
{
    private NewSongFrame new_song_frame;

    private ControlFrame control_frame;

    private JPanel main_panel;
    private JPanel play_control_panel;

    private PlayList song_library_list;

    private JTable song_library_table;

    private PlayListTab playlist_tab;

    private JFrame resultsFrame;
    private JTextField searchField;
    private PlayList  searchResults;

    private JButton move_up_button;
    private JButton move_down_button;
    private JButton add_song_button;
    private JButton remove_song_button;
    private JButton add_songpl_button;
    private JButton remove_songpl_button;
    private JButton shuffle_button;
    private JButton clear_button;

    private JMenuBar menuBar;

    private User user;

    public PlayListFrame(User user)
    {
        if(user == null || !user.isLoaded())
        {
            RadioPlayList.sendErrorDialog("Unable to load playlists - invalid user!", "Fatal error");
            System.exit(1);
        }

        this.user = user;

        new_song_frame     = new NewSongFrame(this);

        main_panel         = new JPanel();
        play_control_panel = new JPanel();
        song_library_table = new JTable();
        playlist_tab       = new PlayListTab();

        song_library_list  = user.getLibrary();

        setLayout(new BorderLayout());

        initializeButtons();
        initializeSearchComponents();
        initializeComponents();
        initializePanels();
        initializeMenus();
        initializeUser();

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

    private void initializeUser()
    {
        for(PlayList pl : user.getPlayLists())
            addPlayListToTab(pl);
    }

    private void initializeSearchComponents()
    {
        searchField = new JTextField(25);
        searchField.getDocument().addDocumentListener(new searchFieldListener());

        searchResults = new PlayList();

        resultsFrame = new JFrame();
        resultsFrame.setSize(300, 300);
        resultsFrame.setVisible(false);

        resultsFrame.add(searchResults);
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
        JMenuItem randItem     = new JMenuItem(StringConstantHolder.PP_RDM_NM);
        JMenuItem clearItem    = new JMenuItem(StringConstantHolder.PP_CLR_NM);

        loadItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.LOAD));
        saveItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.SAVE));
        newSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONG));
        delSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONG));
        addSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONGPL));
        remSongItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONGPL));
        randItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.RANDOMIZE));
        clearItem.addActionListener(new PlayListButtonListener(this, PlayListButtonType.CLEAR));

        menu.add(loadItem);
        menu.add(saveItem);
        menu.add(newSongItem);
        menu.add(delSongItem);
        menu.add(addSongItem);
        menu.add(remSongItem);
        menu.add(randItem);
        menu.add(clearItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void initializeButtons()
    {
        // Initializing buttons
        move_up_button         = createButton(StringConstantHolder.PP_UP_NM, StringConstantHolder.PP_UP_IMG);
        move_down_button       = createButton(StringConstantHolder.PP_DN_NM, StringConstantHolder.PP_DN_IMG);
        shuffle_button         = createButton(StringConstantHolder.PP_RDM_NM, StringConstantHolder.PP_RDM_IMG);
        clear_button           = createButton(StringConstantHolder.PP_CLR_NM, StringConstantHolder.PP_CLR_IMG);
        add_song_button        = createButton(StringConstantHolder.PP_ADD_NM, StringConstantHolder.PP_ADD_IMG);
        remove_song_button     = createButton(StringConstantHolder.PP_DEL_NM, StringConstantHolder.PP_DEL_IMG);
        add_songpl_button      = createButton(StringConstantHolder.PP_ADDSG_NM, StringConstantHolder.PP_ADDSG_IMG);
        remove_songpl_button   = createButton(StringConstantHolder.PP_REMSG_NM, StringConstantHolder.PP_REMSG_IMG);
        
        //Instructions when hovering over buttons
        move_up_button.setToolTipText(StringConstantHolder.PP_UP_TT);
        move_down_button.setToolTipText(StringConstantHolder.PP_DN_TT);
        shuffle_button.setToolTipText(StringConstantHolder.PP_RDM_TT);
        clear_button.setToolTipText(StringConstantHolder.PP_CLR_TT);
        add_song_button.setToolTipText(StringConstantHolder.PP_ADD_TT);
        remove_song_button.setToolTipText(StringConstantHolder.PP_DEL_TT);
        add_songpl_button.setToolTipText(StringConstantHolder.PP_ADDPL_TT);
        remove_songpl_button.setToolTipText(StringConstantHolder.PP_DELPL_TT);
        

        move_up_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.MVUP));
        move_down_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.MVDN));
        shuffle_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.RANDOMIZE));
        clear_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.CLEAR));
        add_song_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONG));
        remove_song_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONG));
        add_songpl_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.ADD_SONGPL));
        remove_songpl_button.addActionListener(new PlayListButtonListener(this, PlayListButtonType.REMOVE_SONGPL));
        
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

    public static PlayList createPlayList(String name)
    {
        if(name == null || name.isEmpty())
            name = StringConstantHolder.PL_NEW_PL;

        PlayList pl = new PlayList(name);
        pl.setBorder(new EtchedBorder());
        pl.setSelectedIndex(-1);

        return pl;
    }

    public void addPlayListToTab(PlayList pl)
    {
        for(Component c : playlist_tab.getComponents())
            if((c instanceof PlayList) && ((PlayList)c).equals(pl))
                return;

        playlist_tab.addTab(pl);
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

        LoginManager.getLoginManager().saveCurrentUser();
    }

    public void removeSongFromLibrary(Song song)
    {
        if(!song_library_list.containsSong(song))
            return;

        song_library_list.deleteSong(song);
        song_library_table.revalidate();

        LoginManager.getLoginManager().saveCurrentUser();
    }

    private void initializePanels()
    {
        main_panel.setLayout(new GridLayout(1, 3));
        main_panel.add(playlist_tab);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Search"));
        panel2.add(searchField);
        panel2.add(initializeSongLibrary(), song_library_list.getName());
        panel2.setBorder(new EtchedBorder());
        panel2.setVisible(true);

        main_panel.add(panel2);
        main_panel.setVisible(true);

        play_control_panel.setLayout(new GridLayout(1, 9));
        play_control_panel.add(move_up_button);
        play_control_panel.add(move_down_button);
        play_control_panel.add(shuffle_button);
        play_control_panel.add(clear_button);
        play_control_panel.add(add_songpl_button);
        play_control_panel.add(remove_songpl_button);
        play_control_panel.add(add_song_button);
        play_control_panel.add(remove_song_button);      
        play_control_panel.setVisible(true);
    }

    public PlayList getCurrentPlayList()
    {
        if(!(playlist_tab.getSelectedComponent() instanceof JPanel))
            return null;

        JPanel panel = (JPanel)playlist_tab.getSelectedComponent();
        for(int i = 0; i < panel.getComponentCount(); ++i)
        {
            if(panel.getComponent(i) instanceof PlayList)
                return (PlayList)panel.getComponent(i);
        }

        return null;
    }

    public Song getSelectedLibrarySong()
    {
        if(song_library_table.getSelectedRow() < 0
                || song_library_table.getSelectedRow() >= song_library_table.getModel().getRowCount())
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
            RadioPlayList.sendErrorDialog(StringConstantHolder.IMG_LOAD_ERR + path,
                    StringConstantHolder.PL_LOAD_ERR);
            button.setText(name);
        }

        return button;
    }

    class searchFieldListener implements DocumentListener
    {
        public void search()
        {
            String search = searchField.getText();

            for(Song s : searchResults.getSongs())
                searchResults.deleteSong(s);

            if(!search.isEmpty() && !search.equals(" "))
                for(Song s : song_library_list.getSongs())
                    if(s.getTitle().toLowerCase().contains(search.toLowerCase()))
                        searchResults.addSong(s);

            resultsFrame.add(searchResults);
            if(!resultsFrame.isVisible())
            {
                resultsFrame.setAlwaysOnTop(true);
                resultsFrame.setVisible(true);
                searchField.requestFocus();
            }
        }

        public void insertUpdate(DocumentEvent e) { search(); }
        public void removeUpdate(DocumentEvent e) { search(); }
        public void changedUpdate(DocumentEvent e) {}

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
