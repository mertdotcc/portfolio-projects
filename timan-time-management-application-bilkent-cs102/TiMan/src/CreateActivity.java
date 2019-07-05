import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateActivity extends JFrame {

    Dimension dim = new Dimension(1280,720);
    ImageIcon tMlogo;
    JButton createActivity;
    JPanel loginpanel;
    JTextField txActivityName,txEnterGoal;
    JLabel activityName ,enterGoal ,logoLabel , welcomeLabel;
    int x,y;
    int goal;
    String ActName, username;
    Activity activity;
    FileInteractor fileInteractor;


    public CreateActivity(String username) throws Exception{

        super("Create an Activity");
        fileInteractor = new FileInteractor(username+".txt");
        this.username = username;
        ActName = "";
        goal = 0;
        activity = new Activity(ActName);

        x = 590 ;
        y = 300 ;

        loginpanel = new JPanel();
        loginpanel.setBackground(new Color(38, 52, 71));

        tMlogo = new ImageIcon(getClass().getResource("TMtrans.png"));
        logoLabel = new JLabel(tMlogo);

        createActivity = new JButton("Create Activity");
        createActivity.addActionListener(new buttonListener());

        txActivityName = new JTextField();
        txEnterGoal = new JTextField();

        activityName = new JLabel("Activity Name ");
        activityName.setForeground(Color.WHITE);

        enterGoal = new JLabel("Enter Goal(per week) ");
        enterGoal.setForeground(Color.WHITE);

        setSize(dim);
        setLocation(100,100);
        loginpanel.setLayout (null);



        logoLabel.setBounds(x-30, y-268, 331, 238);


        txActivityName.setBounds(x+150,y,150,20);
        txEnterGoal.setBounds(x+150,y+30,150,20);
        createActivity.setBounds(x+150,y+60,150,20);
        activityName.setBounds(x,y,150,20);
        enterGoal.setBounds(x,y+30,150,20);

        loginpanel.add(logoLabel);

        loginpanel.add(createActivity);
        loginpanel.add(txActivityName);
        loginpanel.add(txEnterGoal);

        loginpanel.add(activityName);
        loginpanel.add(enterGoal);

        getContentPane().add(loginpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public class buttonListener implements ActionListener {

        public void actionPerformed( ActionEvent e) {
            try {
                String aName = txActivityName.getText();
                int aGoal = Integer.parseInt(txEnterGoal.getText());
                Activity a = createActivity(aName, aGoal);
                setVisible(false);
                FileInteractor temp = new FileInteractor(username+"_Activities.txt");
                temp.getFileWriter().print("-"+a.getName());
                temp.getFileWriter().close();
                MainManuFrame m = new MainManuFrame(username);
                /*m.setVisible(false);
                m.getActivities().add(a);
                m.rebuild();*/

            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public Activity createActivity(String name, int goall) {
        Activity nActivity = new Activity(name);
        nActivity.setGoalPerWeek(goall);
        return nActivity;
    }

    public Activity getActivity() {
        return activity;
    }

    public JButton getCreateActivity() {
        return createActivity;
    }

    public String getActName() {
        return ActName;
    }

    public int getGoal() {
        return goal;
    }
}