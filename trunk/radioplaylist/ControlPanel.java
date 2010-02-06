package radioplaylist;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class ControlPanel extends JPanel
{
    final JFrame playlist_frame;

    JPanel label_panel;
    JPanel button_panel;

    Label prev_song;
    Label cur_song;
    Label next_song;
    Label duration;

    JButton play_button;
    JButton previous_button;
    JButton stop_button;
    JButton next_button;
    JButton playlist_button;
    
    ImageIcon play_icon;
    ImageIcon pause_icon;
    ImageIcon playlist_icon;
    ImageIcon playliston_icon;

    public ControlPanel(final JFrame playlist_frame)
    {
        this.playlist_frame = playlist_frame;

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

    private ImageIcon createImage(String image_path)
    {
        File imageFile = new File(image_path);
        if(imageFile.exists())
            return new ImageIcon(image_path);

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
        cur_song  = new Label(StringConstantHolder.CP_NWP_LABEL);
        prev_song = new Label(StringConstantHolder.CP_PRV_LABEL);
        next_song = new Label(StringConstantHolder.CP_NXT_LABEL);
        duration  = new Label();

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

    private void doTestStuff()
    {
        cur_song.setRightText("SONG - ARTIST - TIME");
        prev_song.setRightText("SONG - ARTIST - TIME");
        next_song.setRightText("SONG - ARTIST - TIME");
        duration.setLeftText("SONG: 0:12 / 3:45");
        duration.setRightText("PLAYLIST: 6:78 / 90:00");
    }

    private class Label extends JPanel
    {
        private JLabel left_label;
        private JLabel right_label;

        Label()
        {
            super();
            left_label  = new JLabel();
            right_label = new JLabel();

            initialize();
        }

        Label(String left)
        {
            super();
            left_label  = new JLabel(left);
            right_label = new JLabel();

            initialize();
        }

        Label(String left, String right)
        {
            super();
            left_label  = new JLabel(left);
            right_label = new JLabel(right);

            initialize();
        }

        void initialize()
        {
            left_label.setHorizontalAlignment(SwingConstants.LEFT);
            right_label.setHorizontalAlignment(SwingConstants.RIGHT);
            add(left_label);
            add(right_label);

            setVisible(true);
        }

        void setLeftText(String text)
        {
            left_label.setText(text);

            repaint();
        }

        void setRightText(String text)
        {
            right_label.setText(text);

            repaint();
        }
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
                if(pause_icon != null)
                    play_button.setIcon(pause_icon);
                else
                    play_button.setText(StringConstantHolder.CP_PSE_NM);
            }
            else
            {
                if(play_icon != null)
                    play_button.setIcon(play_icon);
                else
                    play_button.setText(StringConstantHolder.CP_PLY_NM);
            }
        }

        private void doPreviousButtonAction(ActionEvent e)
        {

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
            {
                playlist_frame.setVisible(false);
                if(playlist_icon != null)
                    playlist_button.setIcon(playlist_icon);
            }
            else
            {
                playlist_frame.setVisible(true);
                if(playliston_icon != null)
                    playlist_button.setIcon(playliston_icon);
            }
        }
    }
}
