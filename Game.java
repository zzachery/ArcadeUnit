import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private Random rand;

    private Timer timer;
    private Player player;
    private ArrayList<Car> cars;
    private ArrayList<Terrain> terrains;
    private int time;
    private String timeString;

    private int level;

    public Game() {
        createNewGame();
    }

    // Clears to level 1 and resets player
    public void createNewGame() {
        setFocusable(true);
        setBackground(Color.GREEN); // Background color

        player = new Player(400, 580);
        cars = new ArrayList<Car>();
        terrains = new ArrayList<Terrain>();
        level = 1;
        rand = new Random();

        generateTerrain();
        generateCars();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }
        });
        timer = new Timer(20, this);
    }

    public void start() {
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        time+=20;
        timeString=(time/60000)+":"+((time/1000)%60)+":"+(time-(time/1000)*1000);
        // Movement
        player.move();
        for (Car car : cars) {
            car.move(level);
        }

        // Check if player is at the end of a level
        if (player.getY() <= 0) {
            level++;
            player.resetPosition();
            generateTerrain();
            generateCars();
        }

        // Check collisions
        Rectangle playerHitbox = player.getHitbox();
        for (Car car : cars) {
            // Player collides
            if (playerHitbox.intersects(car.getHitbox())) {
                timer.stop();
                // Display retry screen
                Object[] options = {"yes", "no"};
                var selection = JOptionPane.showOptionDialog(this, "game over!\nlevels completed: " + level + "\ntotal time: "+timeString+"\nretry?", "game over",
                        0, 2, null, options, options[0]);
                if (selection == 0) {
                    createNewGame();
                    start();
                } else {
                    System.exit(0);
                }
            }
        }

        // Repaint all objects
        repaint();
        String OS = System.getProperty("os.name");
        if (OS.indexOf("nix") >= 0
        || OS.indexOf("nux") >= 0
        || OS.indexOf("aix") > 0) {
            Toolkit.getDefaultToolkit().sync();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw level objects
        for (Terrain t : terrains) {
            t.draw(g);
        }
        for (Car c : cars) {
            c.draw(g);
        }
        player.draw(g);

        // Draw level text
        g.setColor(Color.BLACK);
        g.drawString("level: " + level, 10, 20);
        g.drawString("time: " + timeString, 700, 20);
    }

    // Spawn cars
    private void generateCars() {
        cars.clear();
        for (Terrain t : terrains) { // Across each lane
            int y = t.getY() + 5;
            int speed = (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/4); // Calculate speed: exponentially increase over time
            int opposite = rand.nextInt(1,3);
            for (int j = 0; j < rand.nextInt(1, level+1); j++) { // Random amount of cars on each road piece
                if (opposite == 1) { // Right to left
                    cars.add(new Car(800-rand.nextInt(800), y, 80, 30, -1*speed));
                } else { // Left to right
                    cars.add(new Car(rand.nextInt(800), y, 80, 30, speed));
                }
            }
        }
    }

    // Spawn road pieces
    private void generateTerrain() {
        terrains.clear();
        int pos = 460;
        for(int i = 1; i < 12; i++) {
            int coinflip = rand.nextInt(1,3);
            if (coinflip == 1) { // Spawn road
                terrains.add(new Terrain(pos));
            }
            pos-=40;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create new frame
                JFrame frame = new JFrame("frogger");
                Game game = new Game();
                frame.add(game);
                frame.setSize(800, 620);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                // Start game
                game.start();
            }
        });
    }
}
