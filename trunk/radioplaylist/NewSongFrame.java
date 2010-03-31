
package radioplaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class NewSongFrame extends JFrame
{
    private final PlayListFrame pl_panel;

    private JButton button;

    private JLabel titleLabel;
    private JTextField titleField;

    private JLabel artistLabel;
    private JTextField artistField;

    private JLabel albumLabel;
    private JTextField albumField;

    private JComboBox recTypeCombo;
    private JLabel recTypeLabel;

    private JLabel yearLabel;
    private JTextField yearField;

    private JLabel durationLabel;
    private JTextField minField;
    private JLabel colonLabel;
    private JTextField secField;

    public NewSongFrame(final PlayListFrame pl)
    {
        super();
        pl_panel = pl;

        if(pl_panel == null)
        {
            System.err.println(StringConstantHolder.CP_OPEN_PF_ERR);
            return;
        }

        setTitle(StringConstantHolder.NSF_FRAME_TITLE);
        setSize(520, 160);
        setLocation(200, 200);

        initializeComponents();
        addComponents();
    }

    private void initializeComponents()
    {
        button = new JButton(StringConstantHolder.NSF_BUTTON_NAME);

        titleLabel = new JLabel(StringConstantHolder.NSF_TITLE_LABEL);
        titleField = new JTextField(40);

        artistLabel = new JLabel(StringConstantHolder.NSF_ARTIST_LABEL);
        artistField = new JTextField(40);

        albumLabel = new JLabel(StringConstantHolder.NSF_ALBUM_LABEL);
        albumField = new JTextField(40);

        recTypeLabel = new JLabel(StringConstantHolder.NSF_RECTYPE_LABEL);
        recTypeCombo = new JComboBox(StringConstantHolder.NSF_RECORD_TYPES);
        recTypeCombo.setEditable(false);

        yearLabel = new JLabel(StringConstantHolder.NSF_YEAR_LABEL);
        yearField = new JTextField(4);

        //time...split in two field, minute and seconds
        durationLabel = new JLabel(StringConstantHolder.NSF_TIME_LABEL);
        minField = new JTextField(2);
        colonLabel = new JLabel(StringConstantHolder.NSF_COLON_LABEL);
        secField = new JTextField(2);

        button.addActionListener(new NewSongButtonListener());
    }

    private void addComponents()
    {
        JPanel panel = new JPanel();

        panel.add(titleLabel);
        panel.add(titleField);

        panel.add(artistLabel);
        panel.add(artistField);

        panel.add(albumLabel);
        panel.add(albumField);

        panel.add(recTypeLabel);
        panel.add(recTypeCombo);

        panel.add(yearLabel);
        panel.add(yearField);

        panel.add(durationLabel);
        panel.add(minField);
        panel.add(colonLabel);
        panel.add(secField);

        panel.add(button);

        add(panel);
    }

    public void resetFields()
    {
        titleField.setText("");
        artistField.setText("");
        albumField.setText("");
        yearField.setText("");
        minField.setText("");
        secField.setText("");
        recTypeCombo.setSelectedIndex(0);
    }

    class NewSongButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Song song = new Song();

            String title   = getTextFromField(titleField);
            String artist  = getTextFromField(artistField);
            String album   = getTextFromField(albumField);
            String yearStr = getTextFromField(yearField);
            String minStr  = getTextFromField(minField);
            String secStr  = getTextFromField(secField);

            String recType = (String)recTypeCombo.getSelectedItem();

            if(isInvalid(title) || isInvalid(artist) || isInvalid(album) || isInvalid(recType)
                    || isInvalid(yearStr) || isInvalid(minStr) || isInvalid(secStr))
            {
                RadioPlayList.sendAlertDialog(StringConstantHolder.NSF_WARN_DATA,
                        StringConstantHolder.NSF_FIELD_WARNING);
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
            if(min == 0 && sec == 0)
                return;

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
                RadioPlayList.sendAlertDialog(StringConstantHolder.NSF_INVALID_NUMB,
                        StringConstantHolder.NSF_FRAME_TITLE);
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
