import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.data.time.Minute;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;





public class enterInfoPage extends JFrame{
    Dimension dim = new Dimension(1920,1080);
    JPanel infoPanel;
    JLabel enterDate, startTime, endTime, infoLabel,welcomeLabel;
    JButton update;
    String activityName;
    Date selectedDate, selectedStartTime, selectedEndTime;
	JDatePickerImpl datePicker;
	JSpinner timeSpinnerStart, timeSpinnerEnd;
    String username;
    FileInteractor fileInteractor;
    int x,y;
    
    public enterInfoPage(String title, String username) throws IOException {
    	super("Enter "+ title +" Info");
    	this.username = username;
    	activityName = title;
    	x = 590 ;
    	y = 300 ;

    	fileInteractor = new FileInteractor(username+".txt");
    	
    	UtilDateModel model = new UtilDateModel();
    	Properties p = new Properties();
    	p.put("text.today", "Today");
    	p.put("text.month", "Month");
    	p.put("text.year", "Year");
    	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    	datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		selectedDate = (Date) datePicker.getModel().getValue();

    	
    	timeSpinnerStart = new JSpinner( new SpinnerDateModel() );
    	JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(timeSpinnerStart, "HH:mm");
    	timeSpinnerStart.setEditor(timeEditorStart);
    	timeSpinnerStart.setValue(new Date());

    	selectedStartTime = (Date) timeSpinnerStart.getModel().getValue();

    	
    	timeSpinnerEnd = new JSpinner( new SpinnerDateModel() );
    	JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(timeSpinnerEnd, "HH:mm");
    	timeSpinnerEnd.setEditor(timeEditorEnd);
    	timeSpinnerEnd.setValue(new Date());

		selectedEndTime = (Date) timeSpinnerEnd.getModel().getValue();
    
    	infoPanel = new JPanel();
    	infoPanel.setBackground(new Color(38, 52, 71));
    	
    	infoLabel = new JLabel("Enter Info For "+ title);
    	infoLabel.setForeground(Color.WHITE);
    	infoLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
    	
    	enterDate = new JLabel("Enter Date:");
    	enterDate.setForeground(Color.WHITE);

    	startTime = new JLabel("Enter Start Time:");
    	startTime.setForeground(Color.WHITE);

    	endTime = new JLabel("Enter End Time:");
    	endTime.setForeground(Color.WHITE);

    	update = new JButton("Update Activity"); 	
    	update.addActionListener(new UpdateButtonListener());

    	setSize(dim);
    	setLocation(100,100);
    	infoPanel.setLayout (null); 
    	
    	infoLabel.setBounds(x,y-10,330,30);
    	
    	datePicker.setBounds(x+110,y+26, 150, 28);
    	timeSpinnerStart.setBounds(x+107,y+56, 155, 30);
    	timeSpinnerEnd.setBounds(x+107,y+84, 155, 30);

    	enterDate.setBounds(x,y+30,150,20);
    	startTime.setBounds(x,y+60,150,20);
    	endTime.setBounds(x,y+90,150,20);
    	update.setBounds(x,y+120,150,20);
    	
    	infoPanel.add(infoLabel);
    	infoPanel.add(enterDate);
    	infoPanel.add(startTime);
    	infoPanel.add(endTime);
    	infoPanel.add(update);
    	infoPanel.add(datePicker);
    	infoPanel.add(timeSpinnerStart);
    	infoPanel.add(timeSpinnerEnd);


    getContentPane().add(infoPanel);
    
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setVisible(true);
    }

    class UpdateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				selectedDate = (Date) datePicker.getModel().getValue();
				selectedStartTime = (Date) timeSpinnerStart.getModel().getValue();
				selectedEndTime = (Date) timeSpinnerEnd.getModel().getValue();
				Activity a = new Activity(activityName);

				int endHour = selectedEndTime.getHours();
				int endMinute = selectedEndTime.getMinutes();
				int startHour = selectedStartTime.getHours();
				int startMinute = selectedStartTime.getMinutes();

				int[] start = {startHour, startMinute};
				int[] end = {endHour, endMinute};

				a.setStartTime(start);
				a.setEndTime(end);

				fileInteractor.getFileWriter().print(selectedDate + "~");
				fileInteractor.getFileWriter().close();
				a.printToFile(username+".txt");

				setVisible(false);
				new MainManuFrame(username);

			}
			catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}
