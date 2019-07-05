import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class mainMenuPanel3 extends JPanel {

    static Dimension dim = new Dimension(1280,720);

    JPanel activityPanel;
    JPanel leaderPanel;
    JPanel chartPanel;
    JPanel buttonPanel;
    JLabel triLabel,triLabel1,triLabel2,triLabel3;
    JButton bcreateActivity, bleaderboard;
    JButton exit,settings;
    ImageIcon createImage, podiumImage, exitImage, settingImage,tri,tri2;
    ArrayList<smallActivityPanel> activityPanels;
    ArrayList<Activity> activities;
    String username;



    public mainMenuPanel3(String username, ArrayList<Activity> activities) throws IOException, Exception {
        this.username = username;
        this.activities = activities;
        setSize(dim);
        setLayout(null);
        setBackground(new Color(38, 52, 71));

        activityPanels = new ArrayList<smallActivityPanel>();

        tri = new ImageIcon(getClass().getResource("tri2.png"));
        tri2 = new ImageIcon(getClass().getResource("tri1.png"));

        triLabel = new JLabel(tri);
        triLabel1 = new JLabel(tri2);
        triLabel2 = new JLabel(tri);
        triLabel3 = new JLabel(tri);

        triLabel.setBounds(-100,600,633,267);
        triLabel1.setBounds(300,600,633,267);
        triLabel2.setBounds(700,650,633,267);
        triLabel3.setBounds(1100,600,633,267);

        JPanel activityPanel = new JPanel();

        createImage = new ImageIcon(getClass().getResource("createactivityicon.png"));
        podiumImage = new ImageIcon(getClass().getResource("podium2.png"));
        exitImage = new ImageIcon(getClass().getResource("logout2.png"));
        settingImage = new ImageIcon(getClass().getResource("settingsicon2.png"));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(1200, 700, 110, 55);
        buttonPanel.setBackground(new Color(38, 52, 71));

        //chartPanel = new JPanel();

        int day = 24*60;
        DefaultPieDataset data = new DefaultPieDataset();
        for(int i = 0; i < activities.size(); i++) {
            data.setValue(activities.get(i).getName(), activities.get(i).getDuration());
            day -= activities.get(i).getDuration();
        }
        data.setValue("Others", day);

        JFreeChart chart = ChartFactory.createPieChart("Your daily summary", data, true,false,false);
        chartPanel = new ChartPanel(chart);

        chartPanel.setLayout(null);
        chartPanel.setBounds(100, 200, 400, 400);
        chartPanel.setBackground(Color.RED);


        for(int i = 0; i < activities.size(); i++) {
            smallActivityPanel sAP = new smallActivityPanel(activities.get(i).getName(), username, new Color(248,190,44) );
            activityPanels.add(sAP);
            activityPanel.add(sAP);
        }
        /*smallActivityPanel pSport = new smallActivityPanel("Sport",username, new Color(248,190,44));
        smallActivityPanel social = new smallActivityPanel("Social",username, new Color(2,152,226));
        smallActivityPanel work = new smallActivityPanel("Work", username, new Color(102,184,106));
        smallActivityPanel sleep = new smallActivityPanel("Sleep",username, new Color(102,51,204));
        smallActivityPanel other = new smallActivityPanel("Other", username,Color.WHITE);

        activityPanels.add(pSport);
        activityPanels.add(social);
        activityPanels.add(work);
        activityPanels.add(sleep);
        activityPanels.add(other);*/


        activityPanel.setLayout(new GridLayout(0,2));
        activityPanel.setBackground(new Color(38, 52, 71));

        /*activityPanel.add(pSport);
        activityPanel.add(social);
        activityPanel.add(work);
        activityPanel.add(sleep);
        activityPanel.add(other);*/

        JScrollPane pane = new JScrollPane(activityPanel);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(600, 200, 415, 400);


        leaderPanel = new JPanel();
        leaderPanel.setLayout(null);
        leaderPanel.setBounds(1100, 200, 200, 400);
        leaderPanel.setBackground(new Color(38, 52, 71));

        bcreateActivity = new JButton(createImage);
        bcreateActivity.setBounds(0, 0, 200, 198);

        bleaderboard = new JButton(podiumImage);
        bleaderboard.setBounds(0, 210, 200, 200);

        exit = new JButton(exitImage);
        exit.setBounds(0, 0, 55, 55);

        settings = new JButton(settingImage);
        settings.setBounds(55, 0, 55, 55);


        bleaderboard.setBounds(0, 210, 200, 200);

        leaderPanel.add(bcreateActivity);
        leaderPanel.add(bleaderboard);

        buttonPanel.add(settings);
        buttonPanel.add(exit);

        add(buttonPanel);
        add(chartPanel);
        add(pane);
        add(leaderPanel);

        add(triLabel);
        add(triLabel1);
        add(triLabel2);
        add(triLabel3);
    }

    public JButton getBcreateActivity() {
        return bcreateActivity;
    }

    public JButton getBleaderboard() {
        return bleaderboard;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getSettings() {
        return settings;
    }

    public ArrayList<smallActivityPanel> getActivityPanels() {
        return activityPanels;
    }


}