import java.awt.*;
import java.util.Random;

public class Terrain {
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        int startPos = 460;
        // Draw 4 rectangles as roads on the screen
        for (int i = 1; i <= 4; i++) {
            g.fillRect(0, startPos, 800, 40);
            startPos-=120; // Offset between each road piece
        }
    }
}
