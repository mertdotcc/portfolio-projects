import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateActivityPanel extends JPanel {

    Dimension dim = new Dimension(1280,720);
    ImageIcon tMlogo;

    JButton updateActivity;
    JTextField txEnterGoal;
    JLabel enterGoal ,firstLabel, logoLabel;
    int x,y;
    int goal;
    Activity activity;


    public UpdateActivityPanel(Activity a){


        activity = a;
        firstLabel = new JLabel("Update Activity- " + activity.getName());
        goal = 0;

        enterGoal = new JLabel("Enter Goal(per week)");
        x = 590 ;
        y = 300 ;


        setBackground(new Color(38, 52, 71));

        tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
        logoLabel = new JLabel(tMlogo);

        updateActivity = new JButton("Update Activity");
        enterGoal.setForeground(Color.WHITE);

        txEnterGoal = new JTextField();

        setSize(dim);
        setLocation(100,100);
        setLayout (null);



        logoLabel.setBounds(x-30, y-268, 331, 238);

        firstLabel.setBounds(x+150,y,150,20);
        firstLabel.setForeground(Color.WHITE);
        txEnterGoal.setBounds(x+150,y+30,150,20);
        firstLabel.setBounds(x,y+10,150,20);
        updateActivity.setBounds(x+150,y+60,150,20);
        enterGoal.setBounds(x,y+30,150,20);

        add(logoLabel);


        add(txEnterGoal);
        add(updateActivity);
        add(enterGoal);
        add(firstLabel);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if( !txEnterGoal.getText().equals("")) {
                activity.setGoalPerWeek(Integer.valueOf(txEnterGoal.getText()));
                repaint();
            }
        }
    }

}
