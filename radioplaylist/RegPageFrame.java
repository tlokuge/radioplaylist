import java.util.*;
import java.awt.event.*;
import javax.swing.*;

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
            boolean pwAdd, conPWAdd, nameAdd, usrNameAdd = false;
            JFrame frame = new JFrame();
		
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
            pwCheck = new JLabel("Weak");
		
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
        }

        public void resetFields()
        {
            nameField.setText("");
            usrNameField.setText("");
            pwField.setText("");
            conPWField.setText("");
        }

        class PWInputListener implements ActionListener
	{
            public void actionPerformed(ActionEvent event)
            {
                while(pwField.hasFocus())
		{
                    //check Password length
                    if(pwField.getPassword().length < 5)
                        pwCheck.setText("Too short");
                    else if(pwField.getPassword().length > 20)
			pwCheck.setText("Too long");
                    //checks if password conditions met
                    for(int i = 0; i<pwField.getPassword().length; i++)
                    {
                        int j = (int)pwField.getPassword()[i];
			if((j > 47 && j < 58) || (j > 64 && j < 91) || (j > 96 && j < 123));
                            pwCheck.setText("Password Invalid");
                    }
                    //otherwise outputs good...not sure if that's a good idea
                    pwCheck.setText("Good");
                    // pwAdd = true;
                }
            }
        }
		
	class ConPWInputListener implements ActionListener
	{
            public void actionPerformed(ActionEvent event)
            {
		while(conPWField.hasFocus())
		{
                    if(conPWField.getPassword()!=(pwField.getPassword()))
                        conPWCheck.setText("Incorrect!");
					
                    conPWCheck.setText("Good");
                    // conPWAdd = true;
		}
            }
	}
	ActionListener pwListener = new PWInputListener();
	pwField.addActionListener(pwListener);
		
	ActionListener conPWListener = new ConPWInputListener();
	conPWField.addActionListener(conPWListener);

}