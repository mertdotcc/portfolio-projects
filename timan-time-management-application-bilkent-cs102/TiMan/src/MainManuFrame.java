import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainManuFrame extends JFrame {
    public JTabbedPane pane;
    public ArrayList<Activity> activities;
    String username;
    mainMenuPanel3 mainMenuPanel;
    FileInteractor fileInteractor1, fileInteractor2;
    public MainManuFrame(String username) throws IOException, Exception {
        this.username = username;
        fileInteractor1 = new FileInteractor(username+"_Activities.txt");

        /*******************************************************
         *add default activities
         */
        activities = new ArrayList<Activity>();
        String strOfActivities  = fileInteractor1.getFileScanner().nextLine();
        String[] arrOfActivities = strOfActivities.split("-");
        for(int i = 0; i < arrOfActivities.length; i++) {
            Activity a = new Activity(arrOfActivities[i]);
            activities.add(a);
        }
        /***********************************************************/

        /************************************************************
         * add default goals to activities
         */
        activities.get(0).setGoalPerWeek(240); //sport
        activities.get(0).setGoalPerWeek(15*60); //social
        activities.get(0).setGoalPerWeek(49*60); //work
        activities.get(0).setGoalPerWeek(56*60); //sleep

        /**********************************************************
         * calculate durations of every activity
         */
        for(int i = 0; i < activities.size(); i++) {
            fileInteractor2 = new FileInteractor(username+".txt");
            Date date1 = new java.util.Date();
            String sdate1 = date1+"";
            String[] ssdate1 = sdate1.split(" ");
            String stringForDate1 = ssdate1[1]+" "+ssdate1[2]+" "+ssdate1[5];

            int count = 0;
            String[] activitytemplate;
            String[] activity;
            while( fileInteractor2.getFileScanner().hasNextLine()) {
                if( count > 0) {
                    String str = fileInteractor2.getFileScanner().nextLine();
                    activitytemplate = str.split("~");
                    String[] datetemplate = activitytemplate[0].split(" ");
                    String filedate = datetemplate[1]+" "+datetemplate[2]+" "+datetemplate[5];

                    if( filedate.equals(stringForDate1)) {
                        for (int j = 1; j < activitytemplate.length; j++) {
                            activity = activitytemplate[j].split("-");
                            if (activity[0].equals(activities.get(i).getName())) {
                                activities.get(i).setDuration( Integer.parseInt(activity[1]));
                            }
                        }
                    }
                }
                else {
                    fileInteractor2.getFileScanner().nextLine();
                }
                count++;
            }
            fileInteractor2.getFileScanner().close();
        }



        pane = new JTabbedPane();
        mainMenuPanel = new mainMenuPanel3(username, activities);
        ArrayList<smallActivityPanel> activityPanels = mainMenuPanel.getActivityPanels();
        //add listener to "add" buttons of activities
        for(int i = 0; i < activityPanels.size(); i++) {
            activityPanels.get(i).getAddButton().addActionListener(new AddButtonListener(activityPanels.get(i).getActivityName()));
        }

        //add listener to "update" buttons of activities
        for( int i = 0; i < activityPanels.size(); i++) {
            activityPanels.get(i).getEditButton().addActionListener(new UpdateButtonListener(activityPanels.get(i).getActivityName()));
        }
        //add panels to the tabs
        pane.addTab("Main Menu",mainMenuPanel);
        for( int i = 0; i < activities.size(); i++) {
            pane.addTab(activities.get(i).getName(),new UpdateActivityPanel(activities.get(i)));
        }

        mainMenuPanel.getBcreateActivity().addActionListener(new CreateActivityListener());
        mainMenuPanel.getSettings().addActionListener(new SettingsButtonListener());

        add(pane);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(1920,1080);
        setVisible(true);
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }


    class AddButtonListener implements ActionListener {

        String activityName;

        public AddButtonListener( String activityName) {
            this.activityName = activityName;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            try {
                new enterInfoPage(activityName, username);
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    class CreateActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setVisible(false);
                CreateActivity c = new CreateActivity(username);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
    class SettingsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new settingPage2();
        }
    }
    class UpdateButtonListener implements ActionListener {
        String aName;
        public UpdateButtonListener(String s) {
            aName = s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new UpdateActivityFrame(new Activity(aName)) ;
        }
    }





}
