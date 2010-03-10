package radioplaylist;

import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegPageFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 100;

        private JButton button;

        private JLabel nameLabel;
        private JTextField nameField;

        private JLabel usrNameLabel;
        private JTextField usrNameField;

        private JLabel pwLabel;
        private JPasswordField pwField;
        private JLabel pwCheck;

        private JLabel conPWLabel;
        private JPasswordField conPWField;
        private JLabel conPWCheck;

        private JPanel panel;

	public RegPageFrame()
        {
            //button
            button = new JButton("Register");
		
            //name of user
            nameLabel = new JLabel("Name");
            nameField = new JTextField(30);
		
            //username
            usrNameLabel = new JLabel("User Name");
            usrNameField = new JTextField(30);
		
            //password
            pwLabel = new JLabel("Password");
            pwField = new JPasswordField(30);
            pwCheck = new JLabel("Too short");
		
            conPWLabel = new JLabel("Confirm Password");
            conPWField = new JPasswordField(30);
            conPWCheck = new JLabel("");
		
            panel = new JPanel();
            //name
            panel.add(nameLabel);
            panel.add(nameField);
            //user name
            panel.add(usrNameLabel);
            panel.add(usrNameField);
            //password
            panel.add(pwLabel);
            panel.add(pwField);
            panel.add(pwCheck);
            //confirm password
            panel.add(conPWLabel);
            panel.add(conPWField);
            panel.add(conPWCheck);
		
            add(panel);

            pwField.getDocument().addDocumentListener(new PWInputListener());

            conPWField.getDocument().addDocumentListener(new ConPWInputListener());
        }

        public void resetFields()
        {
            nameField.setText("");
            usrNameField.setText("");
            pwField.setText("");
            conPWField.setText("");
        }

        class PWInputListener implements DocumentListener
	{
            public void checkPassword()
            {
                conPWCheck.setText("");

                //check Password length
                if(pwField.getPassword().length < 5)
                {
                    pwCheck.setText("Too short");
                    return;
                }
                else if(pwField.getPassword().length > 20)
                {
                    pwCheck.setText("Too long");
                    return;
                }
                //checks if password conditions met
                for(int i = 0; i < pwField.getPassword().length; i++)
                {
                    int j = (int)pwField.getPassword()[i];
                    if((j > 47 && j < 58) || (j > 64 && j < 91) || (j > 96 && j < 123))
                    {
                        pwCheck.setText("Password Invalid");
                        return;
                    }
                }
                //otherwise outputs good...not sure if that's a good idea
                pwCheck.setText("Good");
                // pwAdd = true;
            }
            public void insertUpdate(DocumentEvent e) { checkPassword(); }
            public void removeUpdate(DocumentEvent e) { checkPassword(); }
            public void changedUpdate(DocumentEvent e) {}
        }
		
	class ConPWInputListener implements DocumentListener
	{
            public void checkPasswords()
            {
                if(pwField.getPassword().length > 5
                        && pwField.getPassword().length < 20
                        && Arrays.equals(conPWField.getPassword(), pwField.getPassword()))
                {
                    conPWCheck.setText("Correct");
                    return;
                }

                conPWCheck.setText("Incorrect");
            }

            public void insertUpdate(DocumentEvent e) { checkPasswords(); }
            public void removeUpdate(DocumentEvent e) { checkPasswords(); }
            public void changedUpdate(DocumentEvent e) {}
	}
}