import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * @author ITyan on 18.08.2015.
 */
public class GamePanel1 extends JPanel implements Runnable {

    private static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = sSize.width;
    public static final int HEIGHT = sSize.height;

    private Player firstPlayer = new Player(160, 95, new Color(100, 149, 237));
    private Player secondPlayer = new Player(160, 145, new Color(255, 0, 255));

    private Image boardImage = new ImageIcon("res/board-bg.jpg").getImage();
    private Image startImage = new ImageIcon("res/start_bg.png").getImage();
    private Image logoImage = new ImageIcon("res/alias-party.png").getImage();
    private Image startBtnImg = new ImageIcon("res/start-btn.png").getImage();

    private JButton startBtn;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private List<MovePoint> movePoints = new ArrayList<>();

    public GamePanel1() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.createGraphics();

        while (running) {
            gameUpdate();
            gameRender();
            gameDraw();
        }
    }

    private void gameUpdate() {
        movePointUpdate();
    }

    private void movePointUpdate() {
        int i = 1;
        for (int x = 220; x < 1200; x += 100) {
            if (i > 8) {
                i = 1;
            }
            int y = 95;
            movePoints.add(new MovePoint(x, y, i));
            i++;
        }

        movePoints.add(new MovePoint(1220, 175, BonusCard.EMOTIONS));

        for (int x = 1120; x > 220; x -= 100) {
            if (i > 8) {
                i = 1;
            }
            int y = 250;
            movePoints.add(new MovePoint(x, y, i));
            i++;
        }

        movePoints.add(new MovePoint(220, 330, BonusCard.ACTION));

        for (int x = 320; x < 1200; x += 100) {
            if (i > 8) {
                i = 1;
            }
            int y = 405;
            movePoints.add(new MovePoint(x, y, i));
            i++;
        }

        movePoints.add(new MovePoint(1220, 485, BonusCard.CELEBRITY));

        for (int x = 1120; x > 200; x -= 100) {
            if (i > 8) {
                i = 1;
            }
            int y = 560;
            movePoints.add(new MovePoint(x, y, i));
            i++;
        }
    }

    private void gameRender() {
        //draw background
        g.drawImage(boardImage, 0, 0, WIDTH, HEIGHT, this);

        //draw start
        g.drawImage(startImage, 110, 70, null);
        g.setColor(Color.green);
        g.setStroke(new BasicStroke(5));
        g.drawLine(205, 70, 205, 170);

        //draw finish
        g.drawImage(startImage, 110, 550, null);
        g.setColor(Color.red);
        g.drawLine(205, 550, 205, 650);

        //draw logo
        g.drawImage(logoImage, 60, 400, null);

        //draw players
        firstPlayer.draw(g);
        secondPlayer.draw(g);

        //draw move points
        for (int i = 0; i < movePoints.size(); i++) {
            movePoints.get(i).draw(g);
        }

        drawPlayerScore(120, 280, new Color(100, 149, 237), firstPlayer);
        drawPlayerScore(200, 280, new Color(255, 0, 255), secondPlayer);

    }

    private void drawPlayerScore(int x, int y, Color color, Player player) {
        int r = 25;
        g.setColor(color);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));

        g.setColor(Color.white);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        String n = player.getScore() + "";
        if (player.getScore() > 9) {
            g.drawString(n, x - (r / 2) - 4, y + (r / 2));
        } else {
            g.drawString(n, x - (r / 2) + 3, y + (r / 2));
        }
    }

    public void drawStartBtn() {
        Image newImage = startBtnImg.getScaledInstance(150, 30, java.awt.Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newImage);

        startBtn = new JButton(icon);
        startBtn.setSize(150, 30);
        startBtn.setLocation(130, 330);

        add(startBtn);
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}
