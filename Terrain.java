import java.awt.*;
import java.util.Random;

public class Terrain {
    Random random = new Random();
    public void draw(Graphics g, int level) {
        g.setColor(Color.BLUE);
        int startPos = 460;
        for (int i = 1; i <= 4; i++) {
            g.fillRect(0, startPos, 800, 40);
            startPos-=120;
        }
    }
}
