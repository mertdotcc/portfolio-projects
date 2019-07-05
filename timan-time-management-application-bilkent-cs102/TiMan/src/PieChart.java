import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.util.Date;
import java.util.Scanner;

public class PieChart extends JPanel {

    //public PieDataset dataset;
    public JFreeChart chart;
    public User user;
    public Date date;

    public PieChart(User user, Date date, String chartTitle, boolean includeLegend) {
        this.date = date;
        this.user = user;



        //public PieDataset dataset;
        /*chart = ChartFactory.createPieChart(
                "Mobile Sales",   // chart title
                //dataset,          // data
                includeLegend,             // include legend
                true,
                false);

        //add(chart);*/
    }
}
