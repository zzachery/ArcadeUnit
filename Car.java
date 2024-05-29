import java.awt.*;
import java.util.Random;

public class Car {
    private int x, y;
    private int width, height;
    private int speed;

    Random rand = new Random();

    public Car(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move() {
        x += speed;
        if (x > 800) {
            x = -width;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
