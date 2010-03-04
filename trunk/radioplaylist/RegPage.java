import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class RegPage
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 100;
    
	public static void main(String[] args)
	{
		boolean pwAdd, conPWAdd, nameAdd, usrNameAdd = false;
		JFrame frame = new JFrame();
		
		//button
		JButton button = new JButton("Register");
		
		//name of user
		final JLabel nameLabel = new JLabel("Name");
		final JTextField name = new JTextField(30);
		
		//username
		final JLabel usrNameLabel = new JLabel("User Name");
		final JTextField usrName = new JTextField(30);
		
		//password
		final JLabel pwLabel = new JLabel("Password");
		final JTextField pw = new JTextField(30);
		final JLabel pwCheck = new JLabel("Weak");
		
		final JLabel conPWLabel = new JLabel("Confirm Password");
		final JTextField conPW = new JTextField(30);
		final JLabel conPWCheck = new JLabel("");
		
		JPanel panel1 = new JPanel();
		panel1.add(nameLabel);
		panel1.add(name);
		panel1.add(usrNameLabel);
		panel1.add(usrName);
		
		JPanel panel2 = new JPanel();
		panel2.add(pwLabel);
		panel2.add(pw);
		panel2.add(pwCheck);
		panel2.add(conPWLabel);
		panel2.add(conPW);
		panel2.add(conPWCheck);
		
		frame.add(panel1);
		frame.add(panel2);
		
		class PWInputListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				while(pw.hasFocus())
				{
					//check Password length
					if(pw.getText().length() < 5)
						pwCheck.setText("Too short");
					else if(pw.getText().length() > 20)
						pwCheck.setText("Too long");
					//checks if password conditions met
					for(int i = 0; i<pw.getText().length(); i++)
					{
						int j = (int)pw.getText().charAt(i);
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
				while(conPW.hasFocus())
				{
					if(!conPW.getText().equals(pw.getText()))
						conPWCheck.setText("Incorrect!");
					
					conPWCheck.setText("Good");
					// conPWAdd = true;
				}
			}
		}
		ActionListener pwListener = new PWInputListener();
		pw.addActionListener(pwListener);
		
		ActionListener conPWListener = new ConPWInputListener();
		conPW.addActionListener(conPWListener);
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}