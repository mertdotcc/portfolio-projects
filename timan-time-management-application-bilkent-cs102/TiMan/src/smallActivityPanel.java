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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

public class smallActivityPanel extends JPanel{

	String activityName, username;
	JButton add;
	JButton edit;
	JPanel chartPanel;
	FileInteractor fileInteractor;
	int duration;
	
	public smallActivityPanel(String activityName, String username, Color c) throws IOException, Exception {
		this.activityName = activityName;
		this.username = username;

		//scan data from file
		fileInteractor = new FileInteractor(username+".txt");
		Date date1 = new java.util.Date();
		String sdate1 = date1+"";
		String[] ssdate1 = sdate1.split(" ");
		String stringForDate1 = ssdate1[1]+" "+ssdate1[2]+" "+ssdate1[5];

		int count = 0;
		String[] activitytemplate;
		String[] activity;
		while( fileInteractor.getFileScanner().hasNextLine()) {
			if( count > 0) {
				String str = fileInteractor.getFileScanner().nextLine();
				activitytemplate = str.split("~");
				String[] datetemplate = activitytemplate[0].split(" ");
				String filedate = datetemplate[1]+" "+datetemplate[2]+" "+datetemplate[5];
				//System.out.println(activitytemplate[0]);
				if( filedate.equals(stringForDate1)) {
					for (int i = 1; i < activitytemplate.length; i++) {
						activity = activitytemplate[i].split("-");
						if (activity[0].equals(activityName)) {
							duration = Integer.parseInt(activity[1]);
						}
					}
				}
			}
			else {
				fileInteractor.getFileScanner().nextLine();
			}
			count++;
		}
		fileInteractor.getFileScanner().close();

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue(activityName, duration);
		data.setValue("other", 24*60 - duration);
		JFreeChart chart = ChartFactory.createPieChart("", data, false,true,false);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0,40,200,150);
		add(chartPanel);
		JLabel l = new JLabel(activityName);		
		l.setBounds(80, -10, 60, 60);
		add(l);
		setLayout(null);
		add = new JButton();
		add.setBounds(0, 0, 50, 40);
		add.setIcon(new ImageIcon("addbutton6.png"));
		//add.addActionListener(new AddButtonListener());
		edit = new JButton();
		edit.setIcon(new ImageIcon("updatebutton3.png"));
		edit.setBounds(157, 0, 50, 40);

		setBackground(c);
		setPreferredSize(new Dimension(195,195));
		add(add);
		add(edit);
		add(l);
	}
	public JButton getAddButton() {
		return add;
	}
	public JButton getEditButton() {
		return edit;
	}

	public String getActivityName() {
		return activityName;
	}

}
