
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
        {
            pl.setSelectedIndex(ind - 1);
            LoginManager.getLoginManager().saveCurrentUser();
        }
    }

    private void doMvDnButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        int ind = pl.getSelectedIndex();
        if(pl.shiftDown(pl.getSongAt(ind)))
        {
            pl.setSelectedIndex(ind + 1);
            LoginManager.getLoginManager().saveCurrentUser();
        }
    }

    private void doSaveButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;
        pl.savePlaylist();
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
        LoginManager.getLoginManager().saveCurrentUser();

        if(!pl.safeZone())
            RadioPlayList.sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
    }

    private void doRemoveButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        pl.deleteSong(pl.getSongAt(pl.getSelectedIndex()));
        LoginManager.getLoginManager().saveCurrentUser();

        if(!pl.safeZone())
            RadioPlayList.sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
    }

    private void doRandomButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        pl.randomize();
        LoginManager.getLoginManager().saveCurrentUser();
    }

    private void doClearButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        if(RadioPlayList.sendConfirmDialog(StringConstantHolder.PP_CLR_CNFM, StringConstantHolder.PP_CLR_TTL))
        {
            pl.clearPlaylist();
            LoginManager.getLoginManager().saveCurrentUser();
        }
    }
}