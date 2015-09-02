import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author ITyan
 */
public class GamePanel extends JPanel {

    private static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = sSize.width;
    public static final int HEIGHT = sSize.height;

    private Player firstPlayer = new Player(160, 95, new Color(100, 149, 237));
    private Player secondPlayer = new Player(160, 145, new Color(255, 0, 255));

    private Image boardImage = new ImageIcon("res/board-bg.jpg").getImage();
    private Image startImage = new ImageIcon("res/start_bg.png").getImage();
    private Image logoImage = new ImageIcon("res/alias-party.png").getImage();
    private Image newGameBtnImage = new ImageIcon("res/start-btn.png").getImage();
    private Image startBtnImage1 = new ImageIcon("res/firstPlStart.png").getImage();
    private Image startBtnImage2 = new ImageIcon("res/secPlStart.png").getImage();
    private Icon startBtnIcon;

    private boolean imageChange = false;

    private JButton newGameBtn = new JButton();
    private JButton startBtn = new JButton();

    private Graphics2D g;

    private java.util.List<MovePoint> movePoints = new ArrayList<>();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        add(newGameBtn);
        add(startBtn);
    }

    @Override
    public void paint(Graphics graphics) {
        g = (Graphics2D) graphics;

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
        g.drawImage(logoImage, 40, 400, null);

        //draw players
        firstPlayer.draw(g);
        secondPlayer.draw(g);

        //draw players score
        drawPlayerScore(100, 280, new Color(100, 149, 237), firstPlayer);
        drawPlayerScore(180, 280, new Color(255, 0, 255), secondPlayer);

        //draw move points
        addMovePoint();
        for (MovePoint movePoint : movePoints) {
            movePoint.draw(g);
        }

        //draw new game button
        drawNewGameBtn();

        //draw start button
        drawStartBtn();
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

    private void addMovePoint() {
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

    public void drawNewGameBtn() {
        Image newImage = newGameBtnImage.getScaledInstance(150, 30, java.awt.Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newImage);

        newGameBtn.setIcon(icon);
        newGameBtn.setSize(150, 30);
        newGameBtn.setLocation(60, 350);

        initNewGameBtn();
    }

    private void initNewGameBtn() {
        newGameBtn.addActionListener(e -> {
            firstPlayer.update(160, 95, 0);
            secondPlayer.update(160, 145, 0);
            repaint();
        });
    }

    public void drawStartBtn() {

        Image newImage = startBtnImage1.getScaledInstance(150, 40, java.awt.Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newImage);

        startBtn.setIcon(icon);
        startBtn.setSize(150, 40);
        startBtn.setLocation(630, 340);

        initStartBtn();
    }

    public void initStartBtn() {
        startBtn.addActionListener(e -> {
            if (imageChange) {
                Image newImage = startBtnImage1.getScaledInstance(150, 40, java.awt.Image.SCALE_SMOOTH);
                startBtnIcon = new ImageIcon(newImage);
                startBtn.setIcon(startBtnIcon);

                imageChange = false;
            } else {
                Image newImage = startBtnImage2.getScaledInstance(150, 40, java.awt.Image.SCALE_SMOOTH);
                startBtnIcon = new ImageIcon(newImage);
                startBtn.setIcon(startBtnIcon);

                imageChange = true;
            }
        });
    }

    private Image background = new ImageIcon("res/overlay-bg.png").getImage();
    public void drawCardsScreen() {
        int width = background.getWidth(this);
        int height = background.getHeight(this);

        g.drawImage(background, 0, 0, null);

        for (int i = 0; i * width <= WIDTH; i++) {
            for (int j = 0; j * height <= HEIGHT; j++) {
                if (i + j > 0) {
                    g.copyArea(0, 0, width, height, i * width, j * height);
                }
            }
        }
        repaint();
    }
}
