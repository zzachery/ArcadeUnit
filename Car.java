import java.awt.*;
import java.util.Random;

public class Car {
    private int x, y;
    private int width, height;
    private int speed;
    private Random rand;


    public Car(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        rand = new Random();

    }

    // Move across the screen from left to right
    public void move(int level) {
        x += speed;
        if (x > 800) {
            speed = (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/4);
            x = -width;
        } else if (x < -200) {
            speed = -1* (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/4);
            x = 880-width;
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
