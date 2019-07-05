import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class loginPage3 extends JFrame {
    Dimension dim = new Dimension(1280,720);
    ImageIcon tMlogo,tri,tri2;;
    JButton blogin;
    JPanel loginpanel;
    JTextField txusername,txpassword;
    JButton bSignUp;
    JLabel username ,password ,logoLabel , welcomeLabel,triLabel,triLabel1,triLabel2,triLabel3, warning;
    int x,y;
    ArrayList<String> usernames;
    FileInteractor fileInteractor1;
    FileInteractor fileInteractor2;


    public loginPage3()throws IOException {
       super("Log In Page");
       
       x = 590;
       y = 300;
       usernames = new ArrayList<String>();
       fileInteractor1 = new FileInteractor("users.txt");

       fileInteractor1.getFileScanner().useDelimiter("-");

       while (fileInteractor1.getFileScanner().hasNext()) {
           usernames.add( fileInteractor1.getFileScanner().next());
       }

       loginpanel = new JPanel();
       loginpanel.setBackground(new Color(38, 52, 71));
       
       tri = new ImageIcon(getClass().getResource("tri1.png"));
       tri2 = new ImageIcon(getClass().getResource("tri2.png"));

       triLabel = new JLabel(tri);
       triLabel1 = new JLabel(tri2);
       triLabel2 = new JLabel(tri);
       triLabel3 = new JLabel(tri);
   	
       tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
       logoLabel = new JLabel(tMlogo);
       
       blogin = new JButton("Login");
       blogin.addActionListener(new LoginButtonListener());
       bSignUp = new JButton("Sign Up");
       bSignUp.addActionListener(new SignUpButtonListener());
        
       
       triLabel.setBounds(-100,700,633,267);
       triLabel1.setBounds(300,700,633,267);
       triLabel2.setBounds(700,700,633,267);
       triLabel3.setBounds(1100,700,633,267);
       
       loginpanel.add(triLabel);
       loginpanel.add(triLabel1);
       loginpanel.add(triLabel2);
       loginpanel.add(triLabel3);
       
       
        txusername = new JTextField();
        txpassword = new JPasswordField();
        
        welcomeLabel = new JLabel("Welcome to tiMan!");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 30));
        
        username = new JLabel("Username:");
        username.setForeground(Color.WHITE);
       
        password = new JLabel("Password:");
        password.setForeground(Color.WHITE);

        warning = new JLabel();
        warning.setForeground(Color.WHITE);
        warning.setBounds(x+100, y-50,300,25);
        loginpanel.add(warning);

        setSize(dim);
        setLocation(100,100);
        loginpanel.setLayout (null);

        
        welcomeLabel.setBounds(100, 100, 331, 238);

        logoLabel.setBounds(x-30, y-268, 331, 238);


        txusername.setBounds(x+100,y,150,20);
        txpassword.setBounds(x+100,y+30,150,20);
        blogin.setBounds(x+103,y+60,80,20);
        bSignUp.setBounds(x+103,y+90,80,20);
        username.setBounds(x,y,150,20);
        password.setBounds(x,y+30,150,20);

        loginpanel.add(welcomeLabel);

        loginpanel.add(logoLabel);

        loginpanel.add(blogin);
        loginpanel.add(txusername);
        loginpanel.add(txpassword);
        loginpanel.add(bSignUp);
        loginpanel.add(username);
        loginpanel.add(password);

        getContentPane().add(loginpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if( usernames.contains(txusername.getText()) && ! txusername.getText().equals("")) {
                try {
                    fileInteractor2 = new FileInteractor(txusername.getText()+".txt");
                    String userInfo = fileInteractor2.getFileScanner().nextLine();

                    String[] userInfoArray = userInfo.split("-");
                    String userpassword = userInfoArray[2];
                    fileInteractor2.getFileScanner().close();
                    if (userpassword.equals(txpassword.getText())) {
                        setVisible(false);
                        MainManuFrame m = new MainManuFrame(txusername.getText());

                    }
                    else {
                        warning.setText("Password is wrong!");
                    }
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else {
                warning.setText("Insert a valid username");
                System.out.println(5);
            }

        }
    }

    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setVisible(false);
                new signUpPage2();
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}