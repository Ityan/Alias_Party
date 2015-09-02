import javax.swing.*;
import java.awt.*;

/**
 * @author ITyan
 */
public class Cards extends JPanel{

    private static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = sSize.width;
    public static final int HEIGHT = sSize.height;

    private Image background = new ImageIcon("res/overlay-bg.png").getImage();

    public Cards() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void draw(Graphics2D g) {
        int width = background.getWidth(null);
        int height = background.getHeight(null);

        g.drawImage(background, 0, 0, null);

        for (int i = 0; i * width <= WIDTH; i++) {
            for (int j = 0; j * height <= HEIGHT; j++) {
                if (i + j > 0) {
                    g.copyArea(0, 0, width, height, i * width, j * height);
                }
            }
        }
    }

}
