import javax.swing.*;

public class JImageComponent {
    public JImageComponent() {
        JFrame frame = new JFrame("Display Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = (JPanel) frame.getContentPane();

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("logo.JPG"));// your image here
        panel.add(label);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
