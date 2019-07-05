import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import javax.swing.*;
import java.awt.event.*;

public class signUpPage2 extends JFrame   {


    Dimension dim = new Dimension(1280,720);
    ImageIcon tMlogo;
    JButton blogin , bSignUp;
    JPanel signUppanel;
    JTextField txusername, txname , txage ,txweight , txheight,txpassword,txrePassword,txfavAnimal;
    JButton newUSer;
    JLabel username, name , age , weight, height ,password, rePassword, favAnimal,logoLabel,gender, warning;
    int x,y;
    User newUser;
    ArrayList<String> users;

    String userName;
    String normalName;
    int userAge;
    int userWeight;
    int userHeight;
    String userPassword;
    String userRePassword;
    String userFavAnimal;

    JRadioButton button1;
    JRadioButton button2;
    JRadioButton button3;
    ButtonGroup group;
    FileInteractor fileInteractor1, fileInteractor2;

    public signUpPage2() throws Exception{
        super("Sign Up Page");
        users = new ArrayList<String>();
        fileInteractor1 = new FileInteractor("users.txt");
        fileInteractor1.getFileScanner().useDelimiter("-");

        while (fileInteractor1.getFileScanner().hasNext()) {
            users.add( fileInteractor1.getFileScanner().next());
        }

        userName = "";
        normalName = "";
        userAge = 0;
        userWeight = 0;
        userHeight = 0;
        userRePassword = "";

        button1 = new JRadioButton("Woman");
        button2 = new JRadioButton("Man");
        button3 = new JRadioButton("Prefer not to disclose");

        group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);

        x = 590 ;
        y = 300 ;

        signUppanel = new JPanel();
        signUppanel.setBackground(new Color(38, 52, 71));
        button1.setBackground(new Color(38, 52, 71));
        button2.setBackground(new Color(38, 52, 71));
        button3.setBackground(new Color(38, 52, 71));

        tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
        logoLabel = new JLabel(tMlogo);

        bSignUp = new JButton("Sign Up");

        txusername = new JTextField();
        txname = new JTextField();
        txage = new JTextField();
        txweight = new JTextField();
        txheight = new JTextField();
        txpassword = new JPasswordField();
        txrePassword = new JPasswordField();
        txfavAnimal = new JTextField();

        username = new JLabel("Username:");
        username.setForeground(Color.WHITE);
        name = new JLabel("Name:");
        name.setForeground(Color.WHITE);
        age = new JLabel("Age:");
        age.setForeground(Color.WHITE);
        weight = new JLabel("Weight:");
        weight.setForeground(Color.WHITE);
        height = new JLabel("Height:");
        height.setForeground(Color.WHITE);
        password = new JLabel("Password:");
        password.setForeground(Color.WHITE);
        rePassword = new JLabel("Password again:");
        rePassword.setForeground(Color.WHITE);
        favAnimal = new JLabel("Favorite Animal:");
        favAnimal.setForeground(Color.WHITE);
        gender = new JLabel("Gender:");
        gender.setForeground(Color.WHITE);

        warning = new JLabel();
        warning.setForeground(Color.WHITE);
        warning.setBounds(x,y-40,250,50);
        signUppanel.add(warning);

        button1.setForeground(Color.WHITE);
        button2.setForeground(Color.WHITE);
        button3.setForeground(Color.WHITE);

        setSize(dim);
        setLocation(0,0);
        signUppanel.setLayout (null);



        logoLabel.setBounds(x-30, y-268, 331, 238);
        username.setBounds(x,y,150,20);
        name.setBounds(x,y+30,150,20);
        age.setBounds(x,y+60,150,20);
        weight.setBounds(x,y+90,150,20);
        height.setBounds(x,y+120,150,20);
        password.setBounds(x,y+150,150,20);
        rePassword.setBounds(x,y+180,150,20);
        favAnimal.setBounds(x,y+210,150,20);
        bSignUp.setBounds(x+103,y+270,80,20);
        gender.setBounds(x,y+240,150,20);

        button1.setBounds(x,y+240,150,20);
        button2.setBounds(x + 40,y+240,150,20);
        button3.setBounds(x + 80,y+240,150,20);



        txusername.setBounds(x+100,y,150,20);
        txname.setBounds(x+100,y+30,150,20);
        txage.setBounds(x+100,y+60,150,20);
        txweight.setBounds(x+100,y+90,150,20);
        txheight.setBounds(x+100,y+120,150,20);
        txpassword.setBounds(x+100,y+150,150,20);
        txrePassword.setBounds(x+100,y+180,150,20);
        txfavAnimal.setBounds(x+100,y+210,150,20);

        button1.setBounds(x + 100,y+240,80,20);
        button2.setBounds(x + 180,y+240,60,20);
        button3.setBounds(x + 240,y+240,150,20);

        signUppanel.add(logoLabel);

        signUppanel.add(username);
        signUppanel.add(name);
        signUppanel.add(age);
        signUppanel.add(weight);
        signUppanel.add(height);
        signUppanel.add(password);
        signUppanel.add(rePassword);
        signUppanel.add(favAnimal);

        signUppanel.add(txusername);
        signUppanel.add(txname);
        signUppanel.add(txage);
        signUppanel.add(txweight);
        signUppanel.add(txheight);
        signUppanel.add(txpassword);
        signUppanel.add(txrePassword);
        signUppanel.add(txfavAnimal);
        signUppanel.add(bSignUp);

        signUppanel.add(button1);
        signUppanel.add(button2);
        signUppanel.add(button3);
        signUppanel.add(gender);

        ActionListener listen = new ButtonListener();
        bSignUp.addActionListener(listen);


        getContentPane().add(signUppanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public class ButtonListener implements ActionListener  {

        public void actionPerformed( ActionEvent e) {
            userName = txusername.getText();
            normalName =  txname.getText();
            /*userAge =  Integer.parseInt(txage.getText());
            userWeight = Integer.parseInt(txweight.getText());
            userHeight = Integer.parseInt(txheight.getText());*/
            userPassword = txpassword.getText();
            userRePassword = txrePassword.getText();
            userFavAnimal = txfavAnimal.getText();
            if(txusername.getText().equals("") || txname.getText().equals("") || txage.getText().equals("") ||
                    txweight.getText().equals("") || txheight.getText().equals("") || txpassword.getText().equals("") ||
                    txrePassword.getText().equals("") || txfavAnimal.getText().equals("")) {
                System.out.println(-1);
                warning.setText("Fill all the fields");
            }
            else if (users.contains(userName)) {
                warning.setText("Username is already taken!");
            }
            else if(userPassword.equals(userRePassword)) {

                try {
                    createUser();
                    setVisible(false);
                    new loginPage3();
                    System.out.println(1);

                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println(2);
                }
            }
            else {
                warning.setText("Re-Typed Password is wrong!");
                System.out.print(3);
            }

        }
    }
    public void createUser() throws IOException {
        if( button1.isSelected() ) {
            User myUser = new User (normalName,userName,userPassword , userFavAnimal, User.Gender.FEMALE, userAge, userHeight,userWeight);
            myUser.printInfo();
            myUser.printInfo("users.txt");
            fileInteractor2 = new FileInteractor(userName+"_Activities.txt");
            fileInteractor2.getFileWriter().print("Sport-Social-Work-Sleep-Other");
            fileInteractor2.getFileWriter().close();
        }
        else if( button2.isSelected()) {
            User myUser = new User (normalName,userName,userPassword , userFavAnimal, User.Gender.MALE, userAge, userHeight,userWeight);
            myUser.printInfo();
            myUser.printInfo("users.txt");
            fileInteractor2 = new FileInteractor(userName+"_Activities.txt");
            fileInteractor2.getFileWriter().print("Sport-Social-Work-Sleep-Other");
            fileInteractor2.getFileWriter().close();

        }
        else if( button3.isSelected() ) {
            User myUser = new User (normalName,userName,userPassword , userFavAnimal, User.Gender.OTHER, userAge, userHeight,userWeight);
            myUser.printInfo();
            myUser.printInfo("users.txt");
            fileInteractor2 = new FileInteractor(userName+"_Activities.txt");
            fileInteractor2.getFileWriter().print("Sport-Social-Work-Sleep-Other");
            fileInteractor2.getFileWriter().close();

        }
        else {
            warning.setText("Fill all the fields!");
        }
    }
}
