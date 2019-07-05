import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.*;
import org.jfree.ui.*;
import java.util.*;
import java.io.*;

public class ActivitySummaryPanell extends JPanel  {

    public String fileName;
    public String userName;
    public int age;
    public int weight;
    public int height;
    public String password;
    public String favoriteAnimal;
    public String activityName;

    public Date date;
    public ArrayList<Activity> activities;
    public int duration;
    public int activityGoal;



    public ActivitySummaryPanell(String activityName){
        fileName = userName;
        this.activityName = activityName;
        this.duration = 0;

    }

    public int getActivityDuration() throws FileNotFoundException, IOException {
        Scanner scan = new Scanner( new File( fileName));
        scan.useDelimiter("-");
        userName = scan.next();
        age = scan.nextInt();
        weight = scan.nextInt();
        height = scan.nextInt();
        password = scan.next();
        favoriteAnimal = scan.next();

        while( scan.hasNextLine()){

            while( scan.hasNext()){
                //date = scan.next();
                activityName = scan.next();
                //activityStartTime = scan.next();
                //activityEndTime = scan.next();
                activityGoal = scan.nextInt();
                Activity a = new Activity( activityName);

                if ( a.getName().equals(activityName)){
                    duration += a.calculateDuration();
                }

            }
        }
        return duration;
    }
}
