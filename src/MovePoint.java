import javax.swing.*;
import java.awt.*;

/**
 * @author ITyan
 */
public class MovePoint {

    private int number;
    private int x;
    private int y;

    private Image image;

    public MovePoint(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
        image = new ImageIcon("res/point.png").getImage();
    }

    public MovePoint(int x, int y, BonusCard bonusCard) {
        this.x = x;
        this.y = y;

        switch (bonusCard) {
            case EMOTIONS:
                image = new ImageIcon("res/emotion.png").getImage();
                break;
            case CELEBRITY:
                image = new ImageIcon("res/celebrity.png").getImage();
                break;
            case ACTION:
                image = new ImageIcon("res/action.png").getImage();
                break;
        }
    }

    public void draw(Graphics2D g) {

        g.drawImage(image, x, y, null);

        if (number != 0) {
            g.setColor(Color.white);
            g.setFont(new Font("SansSerif", Font.BOLD, 45));
            g.drawString(Integer.toString(number), x + 17, y + 45);
        }
    }
}
