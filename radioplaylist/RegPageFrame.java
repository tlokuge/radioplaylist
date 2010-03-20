package radioplaylist;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegPageFrame extends JFrame
{
    private final int REG_FRAME_WIDTH   = 600;
    private final int REG_FRAME_HEIGHT  = 420;

    private JButton reg_button;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel usrNameLabel;
    private JTextField usrNameField;
    private JLabel usrCheck;

    private JLabel pwLabel;
    private JPasswordField pwField;
    private JLabel pwCheck;

    private JLabel conPWLabel;
    private JPasswordField conPWField;
    private JLabel conPWCheck;

    public RegPageFrame()
    {
        initializeComponents();

        setLayout(new BorderLayout());

        setupTopPanel();
        setupMiddlePanel();
        setupBottomPanel();

        setTitle("Registration Page");
        setSize(REG_FRAME_WIDTH, REG_FRAME_HEIGHT);
        setVisible(true);

    }

    private void initializeComponents()
    {
        //reg_button
        reg_button = new JButton("Register");

        //name of user
        nameLabel = new JLabel("Name:   ");
        nameField = new JTextField(30);

        //username
        usrNameLabel = new JLabel("User Name:   ");
        usrNameField = new JTextField(30);
        usrCheck     = new JLabel("");

        //password
        pwLabel = new JLabel("Password:   ");
        pwField = new JPasswordField(30);
        pwCheck = new JLabel("Too short");

        conPWLabel = new JLabel("Confirm Password:   ");
        conPWField = new JPasswordField(30);
        conPWCheck = new JLabel("Too short");

        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        usrNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        conPWLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        pwField.getDocument().addDocumentListener(new PWInputListener());
        conPWField.getDocument().addDocumentListener(new ConPWInputListener());
        usrNameField.getDocument().addDocumentListener(new usrNameCheckListener());
        reg_button.addActionListener(new RegButtonListener());
    }

    private void setupTopPanel()
    {
        JPanel top = new JPanel();

        JLabel image_label = new JLabel();


        top.setLayout(new GridLayout(1, 1));
        
        image_label.setIcon(
                new ImageIcon("src/images/logo200x200.jpg"));
        image_label.setHorizontalAlignment(SwingConstants.CENTER);
        
        top.add(image_label);

        add(top, BorderLayout.NORTH);
    }

    private void setupMiddlePanel()
    {
        JPanel mid = new JPanel();

        mid.setLayout(new GridLayout(4, 3));

        mid.add(nameLabel);
        mid.add(nameField);
        mid.add(new JLabel());

        mid.add(usrNameLabel);
        mid.add(usrNameField);
        mid.add(usrCheck);

        mid.add(pwLabel);
        mid.add(pwField);
        mid.add(pwCheck);

        mid.add(conPWLabel);
        mid.add(conPWField);
        mid.add(conPWCheck);

        add(mid, BorderLayout.CENTER);
    }

    private void setupBottomPanel()
    {
        JPanel bot = new JPanel();

        bot.add(reg_button);

        add(bot, BorderLayout.SOUTH);
    }

    public void resetFields()
    {
        nameField.setText("");
        usrNameField.setText("");
        usrCheck.setText("   Too short");
        pwField.setText("");
        pwCheck.setText("   Too short");
        conPWField.setText("");
        conPWCheck.setText("   Too short");
    }

    public boolean checkText(String text, JLabel output)
    {
        if(text.length() < 5)
        {
            output.setText("   Too short");
            return false;
        }

        if(text.length() > 20)
        {
            output.setText("   Too long");
            return false;
        }

        for(int i = 0; i < text.length(); i++)
        {
            int j = (int) text.charAt(i);
            if(!((j > 47 && j < 58)
                    || (j > 59 && j < 91)
                    || (j > 96 && j < 123)))
            {
                output.setText("   Invalid");
                return false;
            }
        }

        return true;
    }

    class PWInputListener implements DocumentListener
    {

        public void checkPassword()
        {
            conPWCheck.setText("");

            if(checkText(new String(pwField.getPassword()), pwCheck))
                pwCheck.setText("   Good");
        }

        public void insertUpdate(DocumentEvent e) { checkPassword(); }
        public void removeUpdate(DocumentEvent e) { checkPassword(); }
        public void changedUpdate(DocumentEvent e) {}
    }

    class ConPWInputListener implements DocumentListener
    {
        public void checkPasswords()
        {
            String text = pwCheck.getText();
            if(!text.equals("   Password Invalid") && !text.equals("   Too short")
                    && !text.equals("   Too long")
                    && Arrays.equals(conPWField.getPassword(), pwField.getPassword()))
            {
                conPWCheck.setText("   Correct");
                return;
            }

            conPWCheck.setText("   Incorrect");
        }

        public void insertUpdate(DocumentEvent e) { checkPasswords(); }
        public void removeUpdate(DocumentEvent e) { checkPasswords(); }
        public void changedUpdate(DocumentEvent e) {}
    }

    class usrNameCheckListener implements DocumentListener
    {
        public void checkUserName()
        {
            if(!checkText(usrNameField.getText(), usrCheck))
                return;

            if(LoginManager.instance().contains(usrNameField.getText()) == -1)
                usrCheck.setText("   Available!");
            else
                usrCheck.setText("   Unavailable!");
        }
        
        public void insertUpdate(DocumentEvent e) { checkUserName(); }
        public void removeUpdate(DocumentEvent e) { checkUserName(); }
        public void changedUpdate(DocumentEvent e) {}
    }

    class RegButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(conPWCheck.getText().equals("   Correct") && nameField.getText().length() > 0
                    && usrCheck.getText().equals("   Available!"))
            {
                LoginManager.instance().addUser(nameField.getText(), usrNameField.getText(),
                        new String(conPWField.getPassword()));

                RadioPlayList.sendAlertDialog("User " + nameField.getText() + 
                        "[" + usrNameField.getText() + "] added!", "Registered");
                
                resetFields();
            }
        }
    }
}
