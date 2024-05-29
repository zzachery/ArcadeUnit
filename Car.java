import java.awt.*;
import java.util.Random;

public class Car {
    private int x, y;
    private int width, height;
    private int speed;

    public Car(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    // Move across the screen from left to right
    public void move() {
        x += speed;
        if (x > 800) {
            x = -width;
        }
    }

    // Draw car as rectangle
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
