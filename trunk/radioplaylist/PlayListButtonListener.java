
package radioplaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

enum ButtonType
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

public class PlayListButtonListener implements ActionListener
{
    private ButtonType type;
    private final PlayListPanel panel;

    public PlayListButtonListener(final PlayListPanel panel, ButtonType type)
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

        pl.addSong(getSelectedLibrarySong());

        getSongLibraryTable().repaint();

        if(!pl.safeZone())
            sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
        //add song function here.....
        //pl.addSong( );
    }

    private void doRemoveButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        pl.deleteSong(getSelectedLibrarySong());
        getSongLibraryTable().repaint();

        if(!pl.safeZone())
            sendAlertDialog(StringConstantHolder.PP_TIME_WARN, StringConstantHolder.PP_TIME_TTL);
        //remove song function here....
        //pl.deleteSong( );
    }

    private void doAddPlayListButtonAction(ActionEvent e)
    {
        panel.addPlayListToTab(panel.createPlayList(
                JOptionPane.showInputDialog(StringConstantHolder.PP_PL_NM_PRMPT)), true);
        //JOptionPane.showMessageDialog(null, "This button blows up the world");
    }

    private void doRemPlayListButtonAction(ActionEvent e)
    {
        PlayList pl = getCurrentPlayList();
        if(pl == null)
            return;

        if(sendConfirmDialog(StringConstantHolder.PP_REMPL_CNFM, StringConstantHolder.PP_REMPL_TTL))
            panel.removePlayList(pl);
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