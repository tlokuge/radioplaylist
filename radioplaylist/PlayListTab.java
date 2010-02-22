package radioplaylist;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;

public class PlayListTab extends JTabbedPane
{
    private JPanel dummy_panel;
    private Icon close_icon;
    private boolean first_time;
    private boolean ignore;

    public PlayListTab()
    {
        super();
        close_icon = UIManager.getIcon("InternalFrame.paletteCloseIcon");

        first_time = true;
        ignore = false;

        dummy_panel = new JPanel();
        setTabPlacement(JTabbedPane.LEFT);
        addTab(PlayListFrame.createPlayList("PlayList 1"));

        addChangeListener(new PlayListTabChangeListener());
    }

    public void addTab(Component comp)
    {
        JPanel panel = new JPanel();
        JButton close_button = new JButton("Delete PlayList");
        close_button.addActionListener(new PlayListTabCloseButtonListener(panel, this));

        if(!first_time)
            panel.add(close_button);
        panel.add(comp);

        super.addTab(comp.getName(), panel);
    
        if(first_time)
            first_time = false;
        else
            ignore = true;
        super.remove(dummy_panel);
        super.addTab("Click to add new playlist", dummy_panel);
    }

    class PlayListTabCloseButtonListener implements ActionListener
    {
        private JPanel panel;
        private PlayListTab tabs;

        public PlayListTabCloseButtonListener(JPanel panel, PlayListTab tabs)
        {
            this.panel = panel;
            this.tabs = tabs;
        }

        public void actionPerformed(ActionEvent e)
        {
            if(!RadioPlayList.sendConfirmDialog("Are you sure you wish to remove this tab?", "Delete Tab?"))
                return;

            ignore = true;
            tabs.remove(panel);
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

                if(name.isEmpty())
                    name = "[PH] New PlayList";

                addTab(PlayListFrame.createPlayList(name));
            }
        }
    }
}
