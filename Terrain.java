import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    private int y;

    public Terrain(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(84, 90, 97));
        g.fillRect(0, y, 800, 40);
        g.setColor(Color.WHITE);
        int x = 0;
        for (int i = 0; i< 16; i++) {
            g.fillRect(x, y+17, 30, 6);
            x+=50;
        }
        

        g.setColor(new Color(64, 68, 74));
        g.fillRect(0, y, 800, 3);
        g.fillRect(0, y + 37, 800, 3);
    }
}
