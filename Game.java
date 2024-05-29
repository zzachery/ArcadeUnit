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
    private Terrain terrain;

    private int level;

    public Game() {
        createNewGame();
    }

    // Clears to level 1 and resets player
    public void createNewGame() {
        setFocusable(true);
        setBackground(Color.GREEN); // Background color

        player = new Player(400, 580);
        cars = new ArrayList<>();
        terrain = new Terrain();
        level = 1;
        rand = new Random();

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
        // Movement
        player.move();
        for (Car car : cars) {
            car.move();
        }

        // Check if player is at the end of a level
        if (player.getY() <= 0) {
            level++;
            player.resetPosition();
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
                var selection = JOptionPane.showOptionDialog(this, "game over!\nlevels completed: " + level + "\nretry?", "game over",
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw level objects
        terrain.draw(g);
        player.draw(g);
        for (Car car : cars) {
            car.draw(g);
        }

        // Draw level text
        g.setColor(Color.BLACK);
        g.drawString("level: " + level, 10, 20);
    }

    // Spawn cars
    private void generateCars() {
        cars.clear();
        for (int i = 0; i < 4; i++) { // Across each lane
            int y = 465 - 120 * i;
            int speed = (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/4); // Calculate speed: exponentially increase over time
            for (int j = 0; j < rand.nextInt(1, level+1); j++) { // Random amount of cars on each road piece
                cars.add(new Car(rand.nextInt(800), y, 80, 30, speed));
            }
        }
    }
}
