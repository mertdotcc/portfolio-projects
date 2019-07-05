import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class recoverPasswordPage3 extends JFrame{

    Dimension dim = new Dimension(1280,720);
    ImageIcon tMlogo,tri,tri2;;
    JPanel recoverPanel;
    JTextField txusername ,txfavAnimal;
    JLabel username, favAnimal, showPassword , logoLabel,triLabel,triLabel1,triLabel2,triLabel3;;
    JButton savePassword;
    
    int x,y;

    public recoverPasswordPage3(){
       super("Recover Password Page");
       
       x = 590 ;
       y = 300 ; 
    
    txusername = new JTextField();
    txfavAnimal = new JTextField();
    tri = new ImageIcon(getClass().getResource("tri2.png"));
	tri2 = new ImageIcon(getClass().getResource("tri1.png"));
       
       recoverPanel = new JPanel();
       recoverPanel.setBackground(new Color(38, 52, 71));

        tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
    logoLabel = new JLabel(tMlogo);
    
    username = new JLabel("Username:");
    username.setForeground(Color.WHITE);
    favAnimal = new JLabel("Favorite Animal:");
    favAnimal.setForeground(Color.WHITE);
    showPassword = new JLabel("Your password is:");
    showPassword.setForeground(Color.WHITE);
    
    savePassword = new JButton("Save my Password");

	triLabel = new JLabel(tri);
	triLabel1 = new JLabel(tri2);
	triLabel2 = new JLabel(tri);
	triLabel3 = new JLabel(tri);
    
    triLabel.setBounds(-100,700,633,267);
    triLabel1.setBounds(300,700,633,267);
    triLabel2.setBounds(700,700,633,267);
    triLabel3.setBounds(1100,700,633,267);
    
    

    
    setSize(dim);
    setLocation(0,0);
    recoverPanel.setLayout (null); 
    
    logoLabel.setBounds(x-30, y-268, 331, 238);

    username.setBounds(x,y,150,20);
    favAnimal.setBounds(x,y+30,150,20);
    
    savePassword.setBounds(x+60,y+60,150,20);
    showPassword.setBounds(x,y+90,150,20);
    
    txusername.setBounds(x+100,y,150,20);
    txfavAnimal.setBounds(x+100,y+30,150,20);
    
    recoverPanel.add(logoLabel);

    recoverPanel.add(username);
    recoverPanel.add(favAnimal);
    recoverPanel.add(txusername);
    recoverPanel.add(txfavAnimal);
    recoverPanel.add(showPassword);
    recoverPanel.add(savePassword);
    
    recoverPanel.add(triLabel);
    recoverPanel.add(triLabel1);
    recoverPanel.add(triLabel2);
    recoverPanel.add(triLabel3);
    
    getContentPane().add(recoverPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
    
    
       
       

    }

}