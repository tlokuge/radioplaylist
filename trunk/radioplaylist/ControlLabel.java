package radioplaylist;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ControlLabel extends JPanel
{
    private JLabel left_label;
    private JLabel right_label;

    ControlLabel()
    {
        super();
        left_label = new JLabel();
        right_label = new JLabel();

        initialize();
    }

    ControlLabel(String left)
    {
        super();
        left_label = new JLabel(left);
        right_label = new JLabel();

        initialize();
    }

    ControlLabel(String left, String right)
    {
        super();
        left_label = new JLabel(left);
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
