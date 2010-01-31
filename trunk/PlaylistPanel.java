 

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;

public class PlaylistPanel extends JComponent
{
    JPanel main_panel;
    JPanel list_panel;
    JPanel play_control_panel;

    JList song_library_list;
    JList playlist_song_list;

    JButton move_up_button;
    JButton move_down_button;
    JButton save_button;
    JButton load_button;
    JButton add_button;
    JButton remove_button;
    JButton shuffle_button;
    JButton clear_button;
    
    public PlaylistPanel()
    {
        main_panel        = new JPanel();
        list_panel       = new JPanel();
        play_control_panel  = new JPanel();

        song_library_list   = new JList();
        playlist_song_list       = new JList();

        move_up_button  = new JButton(new ImageIcon(StringConstantHolder.PP_UP_IMG));
        move_down_button= new JButton(new ImageIcon(StringConstantHolder.PP_DN_IMG));
        save_button     = new JButton(new ImageIcon(StringConstantHolder.PP_SV_IMG));
        load_button     = new JButton(new ImageIcon(StringConstantHolder.PP_LD_IMG));
        add_button   = new JButton(new ImageIcon(StringConstantHolder.PP_ADD_IMG));
        remove_button   = new JButton(new ImageIcon(StringConstantHolder.PP_DEL_IMG));
        shuffle_button  = new JButton(new ImageIcon(StringConstantHolder.PP_RDM_IMG));
        clear_button    = new JButton(new ImageIcon(StringConstantHolder.PP_CLR_IMG));

        setLayout(new BorderLayout());
        initializePanels();

        add(main_panel, BorderLayout.CENTER);
        add(play_control_panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void initializeComponents()
    {
        song_library_list.setSelectedIndex(-1);
        playlist_song_list.setSelectedIndex(-1);
        song_library_list.setBorder(
                BorderFactory.createTitledBorder(new EtchedBorder(), StringConstantHolder.PP_LIBRARY_PANEL));
        playlist_song_list.setBorder(
                BorderFactory.createTitledBorder(new EtchedBorder(), StringConstantHolder.PP_PLAYLIST_PANEL));
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

        playlist_song_list.setListData(str);
    }

    public void initializeButtons()
    {
        //Instructions when hovering over buttons
        move_up_button.setToolTipText(StringConstantHolder.PP_UP_TT);
        move_down_button.setToolTipText(StringConstantHolder.PP_DN_TT);
        save_button.setToolTipText(StringConstantHolder.PP_SV_TT);
        load_button.setToolTipText(StringConstantHolder.PP_LD_TT);
        add_button.setToolTipText(StringConstantHolder.PP_ADD_TT);
        remove_button.setToolTipText(StringConstantHolder.PP_DEL_TT);
        shuffle_button.setToolTipText(StringConstantHolder.PP_RDM_TT);
        clear_button.setToolTipText(StringConstantHolder.PP_CLR_TT);
        
        move_up_button.addActionListener(new ButtonListener(ButtonType.MVUP));
        move_down_button.addActionListener(new ButtonListener(ButtonType.MVDN));
        save_button.addActionListener(new ButtonListener(ButtonType.SAVE));
        load_button.addActionListener(new ButtonListener(ButtonType.LOAD));
        add_button.addActionListener(new ButtonListener(ButtonType.ADD));
        remove_button.addActionListener(new ButtonListener(ButtonType.REMOVE));
        shuffle_button.addActionListener(new ButtonListener(ButtonType.RANDOMIZE));
        clear_button.addActionListener(new ButtonListener(ButtonType.CLEAR));
    }

    public void initializePanels()
    {
        doTestStuff();
        initializeButtons();
        
        initializeComponents();

        main_panel.setLayout(new GridLayout(1, 3));
        main_panel.add(list_panel);
        main_panel.add(playlist_song_list);
        main_panel.add(song_library_list);
        main_panel.setVisible(true);

        play_control_panel.setLayout(new GridLayout(1, 7));
        play_control_panel.add(move_up_button);
        play_control_panel.add(move_down_button);
        play_control_panel.add(save_button);
        play_control_panel.add(load_button);
        play_control_panel.add(add_button);
        play_control_panel.add(remove_button);
        play_control_panel.add(shuffle_button);
        play_control_panel.add(clear_button);
        play_control_panel.setVisible(true);
    }
    
    //calling all playlist methods
    PlayMeth method = new PlayMeth();
    
    private enum ButtonType { MVUP, MVDN , SAVE , LOAD , ADD, REMOVE, RANDOMIZE, CLEAR; }
    
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
                case MVUP:      doMvUpButtonAction(e);     break;
                case MVDN:      doMvDnButtonAction(e);     break;
                case SAVE:      doSaveButtonAction(e);     break;
                case LOAD:      doLoadButtonAction(e);     break;
                case ADD:       doAddButtonAction(e);      break;
                case REMOVE:    doRemoveButtonAction(e);   break;
                case RANDOMIZE: doRandomButtonAction(e);   break;
                case CLEAR:     doClearButtonAction(e);    break;
            }
        }
       
        private void doMvUpButtonAction(ActionEvent e)
        {
            //method.shiftUp(
        }

        private void doMvDnButtonAction(ActionEvent e)
        {
            
        }

        private void doSaveButtonAction(ActionEvent e)
        {
            
        }

        private void doLoadButtonAction(ActionEvent e)
        {
            
        }

        private void doAddButtonAction(ActionEvent e)
        {
            
        }
        
        private void doRemoveButtonAction(ActionEvent e)
        {
            
        }
        
        private void doRandomButtonAction(ActionEvent e)
        {
        
        }
        
        private void doClearButtonAction(ActionEvent e)
        {
            
        }
    }
}
