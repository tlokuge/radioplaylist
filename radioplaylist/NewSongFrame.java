
package radioplaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class NewSongFrame extends JFrame
{
    private final PlayListFrame pl_panel;

    private JButton button;

    private JLabel recLabel;
    private JTextField recField;

    private JLabel titleLabel;
    private JTextField titleField;

    private JLabel artistLabel;
    private JTextField artistField;

    private JLabel albumLabel;
    private JTextField albumField;

    private JLabel recTypeLabel;
    private JTextField recTypeField;

    private JLabel yearLabel;
    private JTextField yearField;

    private JLabel durationLabel;
    private JTextField minField;
    private JLabel colonLabel;
    private JTextField secField;

    private JPanel panel;

    public NewSongFrame(final PlayListFrame pl)
    {
        pl_panel = pl;

        if(pl_panel == null)
        {
            System.err.println(StringConstantHolder.CP_OPEN_PF_ERR);
            return;
        }
        
        setTitle("New Song");
        setSize(520, 160);
        setLocation(200, 200);
        
        button = new JButton("Add");
        
        titleLabel = new JLabel("Title:");
        titleField = new JTextField(40);

        artistLabel = new JLabel("Artist:");
        artistField = new JTextField(40);

        albumLabel = new JLabel("Album:");
        albumField = new JTextField(40);

        recTypeLabel = new JLabel("Recording type:");
        recTypeField = new JTextField(5);

        recLabel = new JLabel("Record Number:");
        recField = new JTextField(/*the previous record + 1,*/3);

        yearLabel = new JLabel("Year:");
        yearField = new JTextField(4);

        //time...split in two field, minute and seconds
        durationLabel = new JLabel("Time:");
        minField = new JTextField(2);
        colonLabel = new JLabel(":");
        secField = new JTextField(2);

        //initialize
        panel = new JPanel();
        
        panel.add(titleLabel);
        panel.add(titleField);

        panel.add(artistLabel);
        panel.add(artistField);

        panel.add(albumLabel);
        panel.add(albumField);

        panel.add(recTypeLabel);
        panel.add(recTypeField);

        panel.add(yearLabel);
        panel.add(yearField);

        panel.add(durationLabel);
        panel.add(minField);
        panel.add(colonLabel);
        panel.add(secField);

        panel.add(button);

        button.addActionListener(new NewSongButtonListener());

        add(panel);
    }

    public void resetFields()
    {
        titleField.setText("");
        artistField.setText("");
        albumField.setText("");
        recField.setText("");
        yearField.setText("");
        minField.setText("");
        secField.setText("");
    }

    class NewSongButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Song song = new Song();

            String title   = getTextFromField(titleField);
            String artist  = getTextFromField(artistField);
            String album   = getTextFromField(albumField);
            String recType = getTextFromField(recTypeField);
            String yearStr = getTextFromField(yearField);
            String minStr  = getTextFromField(minField);
            String secStr  = getTextFromField(secField);

            if(isInvalid(title) || isInvalid(artist) || isInvalid(album) || isInvalid(recType)
                    || isInvalid(yearStr) || isInvalid(minStr) || isInvalid(secStr))
            {
                JOptionPane.showMessageDialog(null, "Please enter all field data");
                return;
            }

            song.setTitle(title);
            song.setArtist(artist);
            song.setAlbum(album);
            song.setRecType(recType);

            int year = parseToInteger(yearStr);
            if(year < 0) return;
            int min  = parseToInteger(minStr);
            if(min < 0)  return;
            int sec  = parseToInteger(secStr);
            if(sec < 0)  return;

            song.setYear(year);
            song.setDuration(min * 60 + sec);

            pl_panel.addSongToLibrary(song);

            setVisible(false);
        }

        private int parseToInteger(String str)
        {
            try
            {
                return Integer.parseInt(str);
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid number");
            }

            return -1;
        }

        private boolean isInvalid(String str)
        {
            return str == null || str.isEmpty();
        }

        private String getTextFromField(JTextField field)
        {
            String str = field.getText();
            if(str.isEmpty() || str == null)
                return null;

            return str;
        }
    }
}