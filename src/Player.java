import java.awt.*;


public class Player {

    private int x;
    private int y;
    private int r;
    private Color color;

    private int score;

    public Player(int startX, int startY, Color color) {
        x = startX;
        y = startY;
        r = 12;
        score = 0;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }

    public void update(int x, int y, int score) {
        this.x = x;
        this.y = y;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }
}
