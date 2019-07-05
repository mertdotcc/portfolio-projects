import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

public class SettingsFrame extends JFrame{

    JPanel panelUp;
    JPanel panelBelow;
    JPanel empty;

    JLabel labelTop1;
    JLabel labelTop2;
    JLabel labelTop3;
    JLabel labelBelow1;
    JLabel labelBelow2;
    JLabel labelBelow3;

    JButton buttonTop;
    JButton buttonBelow;

    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JTextField text5;
    JTextField text6;

    public SettingsFrame() {

        createItems();
        createPanel();

        this.setLayout(new GridLayout(2,1));
        this.add(panelUp);
        this.add(panelBelow);

        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void createItems() {
        panelUp = new JPanel();
        panelBelow = new JPanel();
        empty = new JPanel();

        labelTop1 = new JLabel("Username: ");
        labelTop2 = new JLabel("Height: ");
        labelTop3 = new JLabel("Weight: ");
        labelBelow1 = new JLabel("New password: ");
        labelBelow2 = new JLabel("Current password: ");
        labelBelow3 = new JLabel("Re-enter new password: ");

        buttonTop = new JButton("Update");
        buttonBelow = new JButton("Change password");

        text1 = new JTextField(50);
        text2 = new JTextField(50);
        text3 = new JTextField(50);
        text4 = new JTextField(50);
        text5 = new JTextField(50);
        text6 = new JTextField(50);
    }

    public void createPanel() {
        panelUp.setLayout(new GridLayout(4,2));
        panelBelow.setLayout(new GridLayout(4,2));

        panelUp.add(labelTop1);
        panelUp.add(text1);

        panelUp.add(labelTop2);
        panelUp.add(text2);

        panelUp.add(labelTop3);
        panelUp.add(text3);

        //panelUp.add(empty);
        panelUp.add(buttonTop);

        panelBelow.add(labelBelow1);
        panelBelow.add(text4);

        panelBelow.add(labelBelow2);
        panelBelow.add(text5);

        panelBelow.add(labelBelow3);
        panelBelow.add(text6);

        //panelBelow.add(empty);
        panelBelow.add(buttonBelow);



    }



}