package radioplaylist;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame
{
    private RegPageFrame regPageFrame;

    private JLabel userNameLabel;
    private JTextField userNameField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame()
    {
        initializeComponents();
        addComponentsToFrame();

        setTitle("RadioPlayList Login");
        setSize(400, 325);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginManager.instance();
    }

    private void initializeComponents()
    {
        userNameLabel = new JLabel("Username:   ");
        userNameField = new JTextField(20);

        passwordLabel = new JLabel("Password:   ");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(new ButtonListener(true));
        registerButton.addActionListener(new ButtonListener(false));
        passwordField.addKeyListener(new EnterKeyListener());
    }

    private void addComponentsToFrame()
    {
        setLayout(new BorderLayout());
        JLabel image_label = new JLabel();
        image_label.setIcon(
                new ImageIcon("src/images/logo200x200.jpg"));

        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JPanel mid1 = new JPanel();
        JPanel mid2 = new JPanel();
        JPanel bot = new JPanel();

        top.add(image_label);

        bot.setLayout(new GridLayout(1, 2));

        mid1.add(userNameLabel);
        mid1.add(userNameField);

        mid2.add(passwordLabel);
        mid2.add(passwordField);

        bot.add(loginButton);
        bot.add(registerButton);

        add(top, BorderLayout.NORTH);
        add(pane, BorderLayout.SOUTH);

        pane.add(mid1, BorderLayout.NORTH);
        pane.add(mid2, BorderLayout.CENTER);
        pane.add(bot, BorderLayout.SOUTH);
    }

    class EnterKeyListener implements KeyListener
    {
        public void keyTyped(KeyEvent e)    {}
        public void keyReleased(KeyEvent e) {}
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                loginButton.doClick();
        }

    }

    class ButtonListener implements ActionListener
    {
        private boolean isLoginButton;
        public ButtonListener(boolean isLoginButton)
        {
            this.isLoginButton = isLoginButton;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(isLoginButton)
            {
                User user = LoginManager.instance().login(userNameField.getText(),
                    new String(passwordField.getPassword()));
                if(user != null)
                {
                    PlaylistGUI gui = new PlaylistGUI();
                    
                    if(regPageFrame != null)
                        regPageFrame.dispose();
                    
                    dispose();
                }
            }
            else
            {
                if(regPageFrame == null)
                    regPageFrame = new RegPageFrame();

                regPageFrame.setVisible(true);
            }
        }
    }
}
