package radioplaylist;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ControlFrame extends JFrame
{
    private final PlayListFrame playlist_frame;
    private RadioPlayList radio;

    private JPanel label_panel;
    private JPanel button_panel;

    private ControlLabel prev_song;
    private ControlLabel cur_song;
    private ControlLabel next_song;
    private ControlLabel duration;

    private JButton play_button;
    private JButton previous_button;
    private JButton stop_button;
    private JButton next_button;
    private JButton playlist_button;
    
    private ImageIcon play_icon;
    private ImageIcon pause_icon;
    private ImageIcon playlist_icon;
    private ImageIcon playliston_icon;

    public ControlFrame(final PlayListFrame playlist_frame)
    {
        this.playlist_frame = playlist_frame;
        radio = null;

        if(playlist_frame == null)
        {
            System.err.println(StringConstantHolder.CP_OPEN_PF_ERR);
            return;
        }

        label_panel  = new JPanel();
        button_panel = new JPanel();

        initializeImages();
        initializeLabels();
        initializeLabelPanel();
        initializeButtons();
        initializeButtonPanel();
        doTestStuff();

        setLayout(new BorderLayout());
        add(label_panel, BorderLayout.NORTH);
        add(button_panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void setRadioPlayList(RadioPlayList radio)
    {
        this.radio = radio;
    }

    private ImageIcon createImage(String image_path)
    {
        String path = StringConstantHolder.RD_PLYLST_IMG_FLDR + image_path;
        if(new File(path).exists())
            return new ImageIcon(path);
        else
            System.err.println(StringConstantHolder.IMG_LOAD_ERR + path);

        return null;
    }

    private void initializeImages()
    {
        play_icon  = createImage(StringConstantHolder.CP_PLY_IMG);
        pause_icon = createImage(StringConstantHolder.CP_PSE_IMG);
        playlist_icon   = createImage(StringConstantHolder.CP_PLST_IMG);
        playliston_icon = createImage(StringConstantHolder.CP_PLSTN_IMG);
    }

    private void initializeLabels()
    {
        cur_song  = new ControlLabel(StringConstantHolder.CP_NWP_LABEL);
        prev_song = new ControlLabel(StringConstantHolder.CP_PRV_LABEL);
        next_song = new ControlLabel(StringConstantHolder.CP_NXT_LABEL);
        duration  = new ControlLabel();

        prev_song.setBorder(new EtchedBorder());
        next_song.setBorder(new EtchedBorder());
    }

    private void initializeLabelPanel()
    {
        label_panel.setBorder(new EtchedBorder());
        label_panel.setLayout(new GridLayout(4, 1));
        label_panel.add(cur_song);
        label_panel.add(duration);
        label_panel.add(prev_song);
        label_panel.add(next_song);
        label_panel.setVisible(true);
    }

    private void initializeButtons()
    {
        play_button     = createButton(StringConstantHolder.CP_PLY_NM, StringConstantHolder.CP_PLY_IMG);
        stop_button     = createButton(StringConstantHolder.CP_STP_NM, StringConstantHolder.CP_STP_IMG);
        previous_button = createButton(StringConstantHolder.CP_PRV_NM, StringConstantHolder.CP_PRV_IMG);
        next_button     = createButton(StringConstantHolder.CP_NXT_NM, StringConstantHolder.CP_NXT_IMG);
        playlist_button = createButton(StringConstantHolder.CP_PLST_NM, StringConstantHolder.CP_PLST_IMG);

        play_button.setToolTipText(StringConstantHolder.CP_PLY_TT);
        stop_button.setToolTipText(StringConstantHolder.CP_STP_TT);
        previous_button.setToolTipText(StringConstantHolder.CP_PRV_TT);
        next_button.setToolTipText(StringConstantHolder.CP_NXT_TT);
        playlist_button.setToolTipText(StringConstantHolder.CP_OPN_TT);

        play_button.addActionListener(new ButtonListener(ButtonType.PLAY));
        stop_button.addActionListener(new ButtonListener(ButtonType.STOP));
        previous_button.addActionListener(new ButtonListener(ButtonType.PREVIOUS));
        next_button.addActionListener(new ButtonListener(ButtonType.NEXT));
        playlist_button.addActionListener(new ButtonListener(ButtonType.PLAYLIST));
    }

    private void initializeButtonPanel()
    {
        button_panel.setLayout(new GridLayout(1, 4));
        button_panel.add(play_button);
        button_panel.add(stop_button);
        button_panel.add(previous_button);
        button_panel.add(next_button);
        button_panel.add(playlist_button);
        button_panel.setVisible(true);
    }

    private JButton createButton(String name, String image_path)
    {
        JButton button = new JButton();
        ImageIcon image = createImage(image_path);
        if(image != null)
            button.setIcon(image);
        else
            button.setText(name);

        return button;
    }

    public void togglePlayListFrame(boolean on)
    {
        playlist_frame.setVisible(on);
        if(on)
        {
            if(playliston_icon != null)
                playlist_button.setIcon(playliston_icon);
        }
        else
        {
            if(playlist_icon != null)
                playlist_button.setIcon(playlist_icon);
        }
    }

    public void setCurrentSong(Song s)
    {
        if(s == null)
            cur_song.setRightText("--");
        else
            cur_song.setRightText(s.getSongInfo());
    }

    public void setPreviousSong(Song s)
    {
        if(s == null)
            prev_song.setRightText("--");
        else
            prev_song.setRightText(s.getSongInfo());
    }

    public void setNextSong(Song s)
    {
        if(s == null)
            next_song.setRightText("--");
        else
            next_song.setRightText(s.getSongInfo());
    }

    public void setSongDuration(String elapsed, String total)
    {
        duration.setLeftText("SONG: " + elapsed + " / " + total);
    }

    public void setPlayListDuration(String elapsed, String total)
    {
        duration.setRightText("PLAYLIST: " + elapsed + " / " + total);
    }

    private void doTestStuff()
    {
        cur_song.setRightText("SONG - ARTIST - TIME");
        prev_song.setRightText("SONG - ARTIST - TIME");
        next_song.setRightText("SONG - ARTIST - TIME");
        duration.setLeftText("SONG: 0:12 / 3:45");
        duration.setRightText("PLAYLIST: 6:78 / 90:00");
    }

    private enum ButtonType { PLAY, PREVIOUS, STOP, NEXT, PLAYLIST; }

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
                case PLAY:      doPlayButtonAction(e);     break;
                case PREVIOUS:  doPreviousButtonAction(e); break;
                case STOP:      doStopButtonAction(e);     break;
                case NEXT:      doNextButtonAction(e);     break;
                case PLAYLIST:  doPlaylistButtonAction(e); break;
            }
        }
       
        private void doPlayButtonAction(ActionEvent e)
        {
            if(play_button.getIcon() == play_icon ||
                    play_button.getText().equalsIgnoreCase(StringConstantHolder.CP_PLY_NM))
            {
                if(radio != null)
                {
                    if(!radio.isPlaying())
                        radio.play(playlist_frame.getCurrentPlayList());
                    else
                        radio.pausePlayList();
                }

                if(pause_icon != null)
                    play_button.setIcon(pause_icon);
                else
                    play_button.setText(StringConstantHolder.CP_PSE_NM);
            }
            else
            {
                if(radio != null)
                {
                    if(radio.isPlaying() && radio.isPaused())
                        radio.resumePlayList();
                }

                if(play_icon != null)
                    play_button.setIcon(play_icon);
                else
                    play_button.setText(StringConstantHolder.CP_PLY_NM);
            }
        }

        private void doPreviousButtonAction(ActionEvent e)
        {
            radio.playPreviousSong();
        }

        private void doStopButtonAction(ActionEvent e)
        {
            if(play_icon != null)
                play_button.setIcon(play_icon);
            else
                play_button.setText(StringConstantHolder.CP_PLY_NM);
        }

        private void doNextButtonAction(ActionEvent e)
        {

        }

        private void doPlaylistButtonAction(ActionEvent e)
        {
            if(playlist_frame.isVisible())
                togglePlayListFrame(false);
            else
                togglePlayListFrame(true);
        }
    }
}
