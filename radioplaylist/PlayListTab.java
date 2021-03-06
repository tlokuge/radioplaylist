package radioplaylist;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;

public class PlayListTab extends JTabbedPane
{
    private JPanel dummy_panel;
    private Icon close_icon;
    private boolean first_time;
    private boolean ignore;
    private int untitled_num;
    private JTextArea help_area;

    public PlayListTab()
    {
        super();
        close_icon = UIManager.getIcon(StringConstantHolder.PT_CLOSE_ICON_PATH);

        first_time = true;
        ignore = false;
        untitled_num = 0;

        dummy_panel = new JPanel();
        JPanel help_panel = new JPanel();
        help_area = new JTextArea();
        help_area.setEditable(false);
        help_area.setColumns(help_panel.getWidth());
        help_area.setRows(help_panel.getHeight());

        help_panel.add(help_area);
        setTabPlacement(JTabbedPane.LEFT);
        addTab(help_panel);


        addChangeListener(new PlayListTabChangeListener());

        buildHelpArea();
    }

    public void buildHelpArea()
    {
        help_area.setText("\tHelp Area");
        String help =
                "\nThank you for using RadioPlayList!"
                 + "\nThis is a short how-to guide to help you "
                 + "get started."
                 + "\nWARNING: Never give out your username and password"
                 + "\nto anyone for security reasons."
                 + "\nPlease contact your System Administrator for any"
                 + "\n account-related problems\n"
                 + "\nCreating a new playlist:"
                 + "\n   1. Simply click the tab on the left to "
                 + "create a new playlist"
                 + "\n      on the top left of the screen"
                 + "\n   2. Don't forget to name your playlist!\n"
                 + "\nSaving a playlist:"
                 + "\n   1. Once you have a playlist, you may save by "
                 + "clicking on the"
                 + "\n      Radio Playlist menu and selecting Save Playlist\n"
                 + "\nLoading an existing playlist:"
                 + "\n   1. You can load a playlist by clicking on the"
                 + "Radio Playlist menu"
                 + "\n      and selecting Load Playlist\n\n"
                 + "\nWe hope RadioPlayList ensure a comfortable "
                 + "and easy experience"
                 + "\nin creating or managing your playlists.";

        help_area.append(help);
    }

    public void addTab(PlayList pl)
    {
        addTab(new JPlayList(pl));
    }

    public void addTab(Component comp)
    {
        JPanel panel = new JPanel();
        JButton close_button = new JButton(close_icon);
        close_button.addActionListener(new PlayListTabCloseButtonListener(panel));

        if(!first_time)
            panel.add(close_button);

        panel.add(comp);

        super.addTab(comp.getName(), panel);

        if(first_time)
            first_time = false;
        else
            ignore = true;
        super.remove(dummy_panel);
        super.addTab(StringConstantHolder.PT_NEW_TAB, dummy_panel);
    }

    public JPlayList getCurrentPlayList()
    {
        if(!(getSelectedComponent() instanceof JPanel))
            return null;

        for(Component c : ((JPanel)getSelectedComponent()).getComponents())
            if(c instanceof JPlayList)
                return (JPlayList)c;

        return null;
    }

    public boolean containsPlayList(PlayList pl)
    {
        for(Component c : getComponents())
            if(c instanceof JPlayList && ((JPlayList)c).getPlayList().equals(pl))
                return true;

        return false;
    }

    class PlayListTabCloseButtonListener implements ActionListener
    {
        private JPanel panel;

        public PlayListTabCloseButtonListener(JPanel panel)
        {
            this.panel = panel;
        }

        public void actionPerformed(ActionEvent e)
        {
            if(!RadioPlayList.sendConfirmDialog(StringConstantHolder.PT_DELTAB_PROMPT,
                    StringConstantHolder.PT_DELTAB_PROMPT_TTL))
                return;

            setSelectedIndex(getSelectedIndex() - 1);
            remove(panel);

            for(Component c : panel.getComponents())
            {
                if(c instanceof JPlayList)
                {
                    LoginManager.instance().deletePlayList(LoginManager.instance().getCurrentUser(),
                            ((JPlayList)c).getPlayList());
                }
            }

            panel.removeAll();
            panel = null;
        }
    }

    class PlayListTabChangeListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            if(ignore)
            {
                ignore = false;
                return;
            }

            PlayListTab tabs = (PlayListTab)e.getSource();
            if(tabs.getSelectedIndex() == (tabs.getTabCount() - 1))
            {
                String name = RadioPlayList.sendInputDialog(
                        StringConstantHolder.PP_PL_NM_PRMPT, StringConstantHolder.PP_PL_NM_TTL);

                if(name == null)
                {
                    tabs.setSelectedIndex(0);
                    return;
                }
                
                if(name.equalsIgnoreCase("library") || name.equalsIgnoreCase("commercials"))
                {
                    RadioPlayList.sendErrorDialog(
                            StringConstantHolder.PP_INV_NAME, StringConstantHolder.PP_NAME_ERRTTL);
                    return;
                }

                for(PlayList pl : LoginManager.instance().getCurrentUser().getPlayLists())
                    if(pl.getName().equalsIgnoreCase(name))
                    {
                        RadioPlayList.sendErrorDialog(
                                StringConstantHolder.PP_INV_NAME, StringConstantHolder.PP_NAME_ERRTTL);
                        return;
                    }

                if(name.isEmpty())
                {
                    ++untitled_num;
                    name = StringConstantHolder.PT_UNTITLED_PL + untitled_num;
                }

                PlayList pl = PlayListFrame.createPlayList(name);
                addTab(pl);

                LoginManager.instance().getCurrentUser().addPlayList(pl);
                LoginManager.instance().saveCurrentUser();
            }
        }
    }
}
