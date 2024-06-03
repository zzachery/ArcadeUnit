import java.awt.*;
import java.util.Random;

public class Car {
    private int x, y;
    private int width, height;
    private int speed;
    private Random rand;

    private Color color;


    public Car(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        rand = new Random();

        // Random color
        int dice = rand.nextInt(4);
        switch (dice) {
            case 1: color = new Color(106, 89, 255); break;
            case 2: color = new Color(255, 166, 89); break;
            case 3: color = new Color(233, 255, 89); break;
            default: color = new Color(255, 67, 54); break;
        }
    }

    // Move across the screen from left to right
    public void move(int level) {
        x += speed;
        if (x > 800) {
            speed = (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/10); // Reset speed after car clears
            x = -width;
        } else if (x < -200) {
            speed = -1* (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/10);
            x = 880-width;
        }
    }

    // Draw car as rectangle
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
