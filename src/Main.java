import javax.swing.*;

/**
 * @author ITyan on 14.08.2015.
 */
public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame("ALIAS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(new GamePanel());
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

}
