import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int dx, dy; // delta x, delta y

    private final int SIZE = 40;

    // Starting positions
    private final int START_X = 405;
    private final int START_Y = 540;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Change player position based off of movement
    public void move() {
        x += dx;
        y += dy;
        dx = 0;
        dy = 0;
        checkBounds();
    }

    // Make sure player does not go out of screen
    private void checkBounds() {
        if (x < 0) x = 0; // left boundary
        if (x > 760) x = 760; // right boundary
        if (y < 0) y = 0; // top boundary
        if (y > 540) y = 540; // bottom boundary
    }

    // Draw player on screen
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, SIZE, SIZE);
    }

    // Check key pressed for movement
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int moveConstant = 40; // Move by player size for accurate grid movement
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { // Change direction left
            dx = -1*moveConstant;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { // Right
            dx = moveConstant;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { // Up
            dy = -1*moveConstant;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { // Down
            dy = moveConstant;
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public int getY() {
        return y;
    }

    // Resets position to starting position at the end of a level
    public void resetPosition() {
        x = START_X;
        y = START_Y;
    }
}

