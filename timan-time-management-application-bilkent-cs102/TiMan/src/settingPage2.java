import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class settingPage2 extends JFrame {
    Dimension dim = new Dimension(1280,720);
    int x,y;
    JPanel settingpanel;
    JLabel weight, height, personaltitle ,passwordtitle, current, new1, new2;
    JTextField txweight, txheight ,txcurrent , txnew1, txnew2;
    JButton bupdate , bchange;



    public settingPage2(){
    	super("Setting Page");
    	
    x = 590 ;
    	y = 300 ; 
    	
    	settingpanel = new JPanel();
    	settingpanel.setBackground(new Color(38, 52, 71));
    	
    	personaltitle = new JLabel("Change Your Personal Data");
    	personaltitle.setForeground(Color.WHITE);
    	personaltitle.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
    	
    	passwordtitle = new JLabel("Change Your Password");
    	passwordtitle.setForeground(Color.WHITE);
    	passwordtitle.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
    	
    	bupdate = new JButton("Update");
    	bchange = new JButton("Change Password");
    	
    	txcurrent = new JPasswordField();
    	txnew1 = new JPasswordField();
    	txnew2 = new JPasswordField();

    weight = new JLabel("Weight:");
    weight.setForeground(Color.WHITE);
    height = new JLabel("Height:");
    height.setForeground(Color.WHITE);
    
    current = new JLabel("Enter your password:");
    current.setForeground(Color.WHITE);
    new1 = new JLabel("Enter new password:");
    new1.setForeground(Color.WHITE);
    new2 = new JLabel("Enter new password again:");
    new2.setForeground(Color.WHITE);
    
    
    txweight = new JTextField();
    txheight = new JTextField();
    	
    setSize(dim);
    setLocation(0,0);
    settingpanel.setLayout (null); 
    
    

    personaltitle.setBounds(x, y-10, 330, 30);

    weight.setBounds(x,y+30,150,20);
    height.setBounds(x,y+60,150,20);
    
    txweight.setBounds(x+100,y+30,150,20);
    txheight.setBounds(x+100,y+60,150,20);
    
    bupdate.setBounds(x+100,y+90,80,20);

    
    passwordtitle.setBounds(x,y+110,330,30);
    
    current.setBounds(x,y+140,150,20);
    txcurrent.setBounds(x+210,y+140,150,20);
    
    new1.setBounds(x,y+170,150,20);
    txnew1.setBounds(x+210,y+170,150,20);

    new2.setBounds(x,y+200,180,20);
    txnew2.setBounds(x+210,y+200,150,20);
    bchange.setBounds(x+150,y+230,200,20);

    

    settingpanel.add(personaltitle);
    settingpanel.add(weight);
    settingpanel.add(height);
    
    settingpanel.add(txweight);
    settingpanel.add(txheight);
    

    
    settingpanel.add(passwordtitle);
    
    settingpanel.add(bupdate);
    settingpanel.add(bchange);

    settingpanel.add(current);
    settingpanel.add(new1);
    settingpanel.add(new2);
    
    settingpanel.add(txcurrent);
    settingpanel.add(txnew1);
    settingpanel.add(txnew2);
    
    


    
    
    getContentPane().add(settingpanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }

}
