import javax.swing.*;

public class UpdateActivityFrame extends JFrame {
    Activity a;

    public UpdateActivityFrame(Activity a) {
        super();
        UpdateActivityPanel panel = new UpdateActivityPanel(a);
        add(panel);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(1920,1080);
        setVisible(true);
    }
}
