import javafx.scene.chart.BarChart;

import javax.swing.*;

public class ActivitySummaryPanel extends JPanel {

    public PieChart chart;
    public JPanel pieChartPanel;
    public JPanel barChartPanel;
    public BarChart graphic;
    public Activity activity;

    public ActivitySummaryPanel( Activity a) {
        activity = a;
        pieChartPanel = new JPanel(/*icon ismi gir*/);//icon ismi ile jpg dosya ismi aynı olmalı
    }
}
