import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int dx, dy;
    private final int SIZE = 40;
    private final int START_X = 405;
    private final int START_Y = 540;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += dx;
        y += dy;
        dx = 0;
        dy = 0;
        checkBounds();
    }

    private void checkBounds() {
        if (x < 0) x = 0;
        if (x > 760) x = 760;
        if (y < 0) y = 0;
        if (y > 540) y = 540;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -40;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 40;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -40;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 40;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public int getY() {
        return y;
    }

    public void resetPosition() {
        x = START_X;
        y = START_Y;
    }
}

