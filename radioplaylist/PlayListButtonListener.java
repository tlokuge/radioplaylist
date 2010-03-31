
package radioplaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JPlayList getCurrentPlayList()
    {
        return panel.getCurrentPlayList();
    }

    private Song getSelectedLibrarySong()
    {
        return panel.getSelectedLibrarySong();
    }

    private void doMvUpButtonAction(ActionEvent e)
    {
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;

        PlayList pl = jpl.getPlayList();
        int ind = jpl.getSelectedIndex();
        if(pl.shiftUp(pl.getSongAt(ind)))
        {
            jpl.setListData(pl.populateListData());
            jpl.setSelectedIndex(ind - 1);
            LoginManager.instance().saveCurrentUser();
        }
    }

    private void doMvDnButtonAction(ActionEvent e)
    {
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;

        PlayList pl = jpl.getPlayList();
        int ind = jpl.getSelectedIndex();
        if(pl.shiftDown(pl.getSongAt(ind)))
        {
            jpl.setListData(pl.populateListData());
            jpl.setSelectedIndex(ind + 1);
            LoginManager.instance().saveCurrentUser();
        }
    }

    private void doSaveButtonAction(ActionEvent e)
    {
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;
        jpl.getPlayList().savePlaylist();
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
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;

        PlayList pl = jpl.getPlayList();
        pl.addSong(getSelectedLibrarySong());
        jpl.setListData(pl.populateListData());
        LoginManager.instance().saveCurrentUser();

        if(!pl.safeZone())
            RadioPlayList.sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
    }

    private void doRemoveButtonAction(ActionEvent e)
    {
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;

        PlayList pl = jpl.getPlayList();
        pl.deleteSong(pl.getSongAt(jpl.getSelectedIndex()));
        LoginManager.instance().saveCurrentUser();

        if(!pl.safeZone())
            RadioPlayList.sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
    }

    private void doRandomButtonAction(ActionEvent e)
    {
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;

        PlayList pl = jpl.getPlayList();
        pl.randomize();
        jpl.setListData(pl.populateListData());
        LoginManager.instance().saveCurrentUser();
    }

    private void doClearButtonAction(ActionEvent e)
    {
        JPlayList jpl = getCurrentPlayList();
        if(jpl == null)
            return;

        if(RadioPlayList.sendConfirmDialog(StringConstantHolder.PP_CLR_CNFM, StringConstantHolder.PP_CLR_TTL))
        {
            jpl.getPlayList().clearPlaylist();
            jpl.setListData(jpl.getPlayList().populateListData());
            LoginManager.instance().saveCurrentUser();
        }
    }
}