import javax.swing.*;
import java.awt.*;

/**
 * @author ITyan on 14.08.2015.
 */
public class Board extends JPanel{

    //panel size
    private static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();

    public static final int WIDTH = sSize.width;
    public static final int HEIGHT = sSize.height;

    private Player firstPlayer = new Player(160, 95, new Color(100, 149, 237));
    private Player secondPlayer = new Player(160, 145, new Color(255, 0, 255));

    private Image boardImage = new ImageIcon("res/board-bg.jpg").getImage();
    private Image startImage = new ImageIcon("res/start_bg.png").getImage();
    private Image logoImage = new ImageIcon("res/alias-party.png").getImage();
    private Image startBtnImg = new ImageIcon("res/start-btn.png").getImage();

    private JButton startBtn;

    public Board() {
        setLayout(null);
        drawStartBtn();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(boardImage, 0, 0, WIDTH, HEIGHT, this);
        graphics2D.drawImage(startImage, 110, 70, null);
        graphics2D.drawImage(startImage, 110, 550, null);
        graphics2D.drawImage(logoImage, 120, 400, null);

        graphics2D.setColor(Color.green);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawLine(205, 70, 205, 170);

        graphics2D.setColor(Color.red);
        graphics2D.drawLine(205, 550, 205, 650);

        firstPlayer.draw(graphics2D);
        secondPlayer.draw(graphics2D);
    }

    public void drawStartBtn() {
        Image newImage = startBtnImg.getScaledInstance( 150, 30,  java.awt.Image.SCALE_SMOOTH ) ;
        Icon icon = new ImageIcon(newImage);

        startBtn = new JButton(icon);
        startBtn.setSize(150, 30);
        startBtn.setLocation(130, 330);
        add(startBtn);
    }
}
