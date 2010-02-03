package radioplaylist;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class PlaylistPanel extends JComponent
{
    JPanel main_panel;
    JPanel play_control_panel;

    JList song_library_list;

    ArrayList<PlayList> playlist_list;

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
    
    public PlaylistPanel()
    {
        main_panel         = new JPanel();
        play_control_panel = new JPanel();

        song_library_list  = new JList();

        playlist_list   = new ArrayList<PlayList>();

        playlist_tab    = new JTabbedPane();

        move_up_button         = new JButton(new ImageIcon(StringConstantHolder.PP_UP_IMG));
        move_down_button       = new JButton(new ImageIcon(StringConstantHolder.PP_DN_IMG));
        save_button            = new JButton(new ImageIcon(StringConstantHolder.PP_SV_IMG));
        load_button            = new JButton(new ImageIcon(StringConstantHolder.PP_LD_IMG));
        add_song_button        = new JButton(new ImageIcon(StringConstantHolder.PP_ADDSG_IMG));
        remove_song_button     = new JButton(new ImageIcon(StringConstantHolder.PP_REMSG_IMG));
        add_playlist_button    = new JButton(new ImageIcon(StringConstantHolder.PP_ADDPL_IMG));
        remove_playlist_button = new JButton(new ImageIcon(StringConstantHolder.PP_REMPL_IMG));
        shuffle_button         = new JButton(new ImageIcon(StringConstantHolder.PP_RDM_IMG));
        clear_button           = new JButton(new ImageIcon(StringConstantHolder.PP_CLR_IMG));


        setLayout(new BorderLayout());
        initializePanels();

        add(main_panel, BorderLayout.CENTER);
        add(play_control_panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeComponents()
    {
        song_library_list.setSelectedIndex(-1);
        song_library_list.setBorder(
                BorderFactory.createTitledBorder(new EtchedBorder(), StringConstantHolder.PP_LIBRARY_PANEL));

        for(JList list : playlist_list)
        {
            list.setSelectedIndex(-1);
            list.setBorder(new EtchedBorder());
        }
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

        song_library_list.setListData(str);

        for(int i = 0; i < 5; ++i)
            playlist_list.add(new PlayList());

        Song s1 = new Song(1, "Song 1", "Artist 1", "Album 1", "RecType 1", 450, 1, 1);
        Song s2 = new Song(2, "Song 2", "Artist 2", "Album 2", "RecType 2", 680, 2, 2);
        Song s3 = new Song(3, "Song 3", "Artist 3", "Album 3", "RecType 3", 900, 3, 3);

        playlist_list.get(0).addSong(s1);
        playlist_list.get(0).addSong(s2);
        playlist_list.get(0).addSong(s3);
    }

    private void initializeButtons()
    {
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
        
        move_up_button.addActionListener(new ButtonListener(ButtonType.MVUP));
        move_down_button.addActionListener(new ButtonListener(ButtonType.MVDN));
        save_button.addActionListener(new ButtonListener(ButtonType.SAVE));
        load_button.addActionListener(new ButtonListener(ButtonType.LOAD));
        add_song_button.addActionListener(new ButtonListener(ButtonType.ADD_SONG));
        remove_song_button.addActionListener(new ButtonListener(ButtonType.REMOVE_SONG));
        shuffle_button.addActionListener(new ButtonListener(ButtonType.RANDOMIZE));
        clear_button.addActionListener(new ButtonListener(ButtonType.CLEAR));
        add_playlist_button.addActionListener(new ButtonListener(ButtonType.ADD_PLAYLIST));
        remove_playlist_button.addActionListener(new ButtonListener(ButtonType.REMOVE_PLAYLIST));
    }

    private void initializePlaylistTabs()
    {
        for(PlayList list : playlist_list)
        {
            PlayListTableModel m = new PlayListTableModel(list);

            JTable table = new JTable(m);
            RowSorter <PlayListTableModel> s = new TableRowSorter<PlayListTableModel>(m);
            table.setRowSorter(s);

            playlist_tab.add(new JScrollPane(table), m.getPlayList().getName());
        }

        playlist_tab.setTabPlacement(JTabbedPane.LEFT);
    }

    private void initializePanels()
    {
        doTestStuff();
        initializePlaylistTabs();
        initializeComponents();
        initializeButtons();

        main_panel.setLayout(new GridLayout(1, 3));
//
//        PlayListTableModel m = new PlayListTableModel();
//        m.addSong(new Song(1, "Song 1", "Artist 1", "Album 1", "RecType 1", 450, 1, 1));
//        m.addSong(new Song(1, "Song 2", "Artist 2", "Album 2", "RecType 2", 680, 2, 2));
//        m.addSong(new Song(1, "Song 3", "Artist 3", "Album 3", "RecType 3", 900, 3, 3));
//
//        JTable table = new JTable(m);
//        RowSorter <PlayListTableModel> s = new TableRowSorter<PlayListTableModel>(m);
//        table.setRowSorter(s);

        //main_panel.add(new JScrollPane(table));
        main_panel.add(playlist_tab);
        main_panel.add(song_library_list);
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
    
    private enum ButtonType 
    {
        MVUP,
        MVDN,
        SAVE,
        LOAD,
        ADD_SONG,
        REMOVE_SONG,
        ADD_PLAYLIST,
        REMOVE_PLAYLIST,
        RANDOMIZE,
        CLEAR;
    }
    
    private class ButtonListener implements ActionListener
    {
        private ButtonType type;

        public ButtonListener(ButtonType type)
        {
            this.type = type;
        }

        public void actionPerformed(ActionEvent e)
        {
            switch(type)
            {
                case MVUP:           doMvUpButtonAction(e);        break;
                case MVDN:           doMvDnButtonAction(e);        break;
                case SAVE:           doSaveButtonAction(e);        break;
                case LOAD:           doLoadButtonAction(e);        break;
                case ADD_SONG:       doAddButtonAction(e);         break;
                case REMOVE_SONG:    doRemoveButtonAction(e);      break;
                case ADD_PLAYLIST:   doAddPlayListButtonAction(e); break;
                case REMOVE_PLAYLIST:doRemPlayListButtonAction(e); break;
                case RANDOMIZE:      doRandomButtonAction(e);      break;
                case CLEAR:          doClearButtonAction(e);       break;
            }
        }

        private PlayList getCurrentPlayList()
        {
            JScrollPane p = (JScrollPane)playlist_tab.getSelectedComponent();
            JTable t= (JTable)p.getViewport().getView();
            PlayList pl = ((PlayListTableModel)t.getModel()).getPlayList();

            return pl;
            //if(playlist_tab.getSelectedComponent() instanceof PlayList)
            //    return (PlayList)playlist_tab.getSelectedComponent();

        }
       
        private void doMvUpButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            int ind = pl.getSelectedIndex();
            if(pl.shiftUp(pl.getSongAt(ind)))
                pl.setSelectedIndex(ind - 1);
            //JOptionPane.showMessageDialog(null,"Imagine that this function works");
        }

        private void doMvDnButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            int ind = pl.getSelectedIndex();
            if(pl.shiftDown(pl.getSongAt(ind)))
                pl.setSelectedIndex(ind + 1);
            //JOptionPane.showMessageDialog(null,"Imagine that this function works too");
        }

        private void doSaveButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            pl.savePlaylist();
            //JOptionPane.showMessageDialog(null,"I couldn't figure out how JFileChooser works");
        }

        private void doLoadButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            pl.loadPlaylist();
        }

        private void doAddButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            if(!pl.safeZone())
                sendAlertDialog("Your playlist is not within 43 and 48 minutes", "Time sufficiency alert");
            //add song function here.....
            //pl.addSong( );
        }
        
        private void doRemoveButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            if(!pl.safeZone())
                sendAlertDialog("Your playlist is not within 43 and 48 minutes", "Time sufficiency alert");
            //remove song function here....
            //pl.deleteSong( );

        }

        private void doAddPlayListButtonAction(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "This button blows up the world");
        }

        private void doRemPlayListButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;

            if(sendConfirmDialog(StringConstantHolder.PP_REMPL_CNFM, StringConstantHolder.PP_REMPL_TTL))
            {
                playlist_tab.remove(pl);
                pl = null;
            }
            //JOptionPane.showMessageDialog(null, "<Insert Witty Comment Here>");
        }

        private void doRandomButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;
            pl.randomize();
        }
        
        private void doClearButtonAction(ActionEvent e)
        {
            PlayList pl = getCurrentPlayList();
            if(pl == null)
                return;

            if(sendConfirmDialog(StringConstantHolder.PP_CLR_CNFM, StringConstantHolder.PP_CLR_TTL))
                pl.clearPlaylist();
            //JOptionPane.showMessageDialog(null,"POOF! Oh...oh no..it's still here");
        }

        private boolean sendConfirmDialog(String message, String title)
        {
            return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION;
        }

        private void sendAlertDialog(String message, String title)
        {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }
    }

    class PlayListScrollPane extends JScrollPane
    {
        private PlayList playlist;

        public PlayListScrollPane(PlayList pl)
        {
            super(pl);
            playlist = pl;
        }
    }

    class PlayListTableModel extends AbstractTableModel
    {
        private String[] columnNames =
        {
            "Title", "Artist", "Album", "Duration"
        };

        private PlayList playlist;

        public PlayListTableModel(PlayList pl)
        {
            playlist = pl;
        }

        public PlayList getPlayList() { return playlist; }

        public int getRowCount()
        {
            return playlist.getTotalSongs();
        }

        public int getColumnCount()
        {
            return columnNames.length;
        }

        public String getColumnName(int col)
        {
            return columnNames[col];
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            if(rowIndex < 0 || rowIndex > playlist.getTotalSongs() - 1)
                return null;
            Song s = playlist.getSongAt(rowIndex);
            switch(columnIndex)
            {
                case 0:
                    return s.getTitle();
                case 1:
                    return s.getArtist();
                case 2:
                    return s.getAlbum();
                case 3:
                    return s.getTime();
            }

            return null;
        }
        
        public Class getColumnClass(int column) {
        if (column >= 0 && column <= getColumnCount())
          return getValueAt(0, column).getClass();
        else
          return Object.class;
      }
    }
}
