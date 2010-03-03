
package radioplaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

enum PlayListButtonType
{
    MVUP,
    MVDN,
    SAVE,
    LOAD,
    ADD_SONG,
    REMOVE_SONG,
    ADD_SONGPL,
    REMOVE_SONGPL,
    //ADD_PLAYLIST,
    //REMOVE_PLAYLIST,
    RANDOMIZE,
    CLEAR;
}

public class PlayListButtonListener implements ActionListener
{
    private PlayListButtonType type;
    private final PlayListFrame panel;

    PlayListButtonListener(final PlayListFrame panel, PlayListButtonType type)
    {
        this.panel = panel;
        this.type  = type;
    }

    public void actionPerformed(ActionEvent e)
    {
        switch(type)
        {
            case MVUP:           doMvUpButtonAction(e);        break;
            case MVDN:           doMvDnButtonAction(e);        break;
            case SAVE:           doSaveButtonAction(e);        break;
            case LOAD:           doLoadButtonAction(e);        break;
            case ADD_SONG:       doAddSongButtonAction(e);     break;
            case REMOVE_SONG:    doRemoveSongButtonAction(e);  break;
            case ADD_SONGPL:     doAddButtonAction(e);         break;
            case REMOVE_SONGPL:  doRemoveButtonAction(e);      break;
            //case ADD_PLAYLIST:   doAddPlayListButtonAction(e); break;
            //case REMOVE_PLAYLIST:doRemPlayListButtonAction(e); break;
            case RANDOMIZE:      doRandomButtonAction(e);      break;
            case CLEAR:          doClearButtonAction(e);       break;
        }
    }

    private PlayList getCurrentPlayList()
    {
        return panel.getCurrentPlayList();
    }

    private Song getSelectedLibrarySong()
    {
        return panel.getSelectedLibrarySong();
    }

    private JTable getSongLibraryTable()
    {
        return panel.getSongLibraryTable();
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
        PlayList pl = new PlayList();
        if(!pl.loadPlaylist())
            return;

        panel.addPlayListToTab(pl);

        Song[] songs = pl.getSongs();
        for(Song s : songs)
            panel.addSongToLibrary(s);
    }

    private void doAddSongButtonAction(ActionEvent e)
    {
        panel.showNewSongFrame();
    }

    private void doRemoveSongButtonAction(ActionEvent e)
    {
        panel.removeSongFromLibrary(panel.getSelectedLibrarySong());
    }

    private void doAddButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        pl.addSong(getSelectedLibrarySong());

        //getSongLibraryTable().repaint();

        if(!pl.safeZone())
            RadioPlayList.sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
        //add song function here.....
        //pl.addSong( );
    }

    private void doRemoveButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        pl.deleteSong(pl.getSongAt(pl.getSelectedIndex()));
        //getSongLibraryTable().repaint();

        if(!pl.safeZone())
            RadioPlayList.sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
        //remove song function here....
        //pl.deleteSong( );
    }
//
//    private void doAddPlayListButtonAction(ActionEvent e)
//    {
//        String name = RadioPlayList.sendInputDialog(StringConstantHolder.PP_PL_NM_PRMPT,
//                StringConstantHolder.PP_PL_NM_TTL);
//        if(name == null)
//            return;
//
//        panel.addPlayListToTab(PlayListFrame.createPlayList(name));
//        //JOptionPane.showMessageDialog(null, "This button blows up the world");
//    }
//
//    private void doRemPlayListButtonAction(ActionEvent e)
//    {
//        PlayList pl = getCurrentPlayList();
//        if(pl == null)
//            return;
//
//        if(RadioPlayList.sendConfirmDialog(StringConstantHolder.PP_REMPL_CNFM, StringConstantHolder.PP_REMPL_TTL))
//            panel.removePlayList(pl);
//        //JOptionPane.showMessageDialog(null, "<Insert Witty Comment Here>");
//    }

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

        if(RadioPlayList.sendConfirmDialog(StringConstantHolder.PP_CLR_CNFM, StringConstantHolder.PP_CLR_TTL))
            pl.clearPlaylist();
        //JOptionPane.showMessageDialog(null,"POOF! Oh...oh no..it's still here");
    }
}