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
        g.setColor(Color.BLACK);
        g.fillRect(0, y, 800, 40);
        g.setColor(Color.WHITE);
        int x = 0;
        for (int i = 0; i< 16; i++) {
            g.fillRect(x, y+17, 30, 6);
            x+=50;
        }
        


////        int startPos = 460;
//        // Draw 4 rectangles as roads on the screen
//        for (int i = 1; i < numRoads; i++) {
//            g.fillRect(0, roadLocs.get(i), 800, 40);
////            startPos-=120; // Offset between each road piece
//        }
    }
}
