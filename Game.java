import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private Timer timer;
    private Player player;
    private ArrayList<Car> cars;
    private Terrain terrain;
    private int level;
    private Random rand;

    public Game() {
        createNewGame();
    }

    public void createNewGame() {
        setFocusable(true);
        setBackground(Color.BLACK);
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
        player.move();
        for (Car car : cars) {
            car.move();
        }
        checkCollisions();
        checkLevelCompletion();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        terrain.draw(g, level);
        player.draw(g);
        for (Car car : cars) {
            car.draw(g);
        }
        drawInterface(g);
    }

    private void checkCollisions() {
        Rectangle playerBounds = player.getBounds();
        for (Car car : cars) {
            if (playerBounds.intersects(car.getBounds())) {
                timer.stop();
                Object[] options = {"yes", "no"};
                var selection = JOptionPane.showOptionDialog(this, "game over!\nlevels completed: " + level + "\nretry?", "game over",
                        0, 2, null, options, options[0]);
                if (selection == 0) {
                    createNewGame();
                    start();
                } else {
                    System.exit(0);
                }
                // JOptionPane.showMessageDialog(this, "game over!\ntotal levels: " + level);
            }
        }
    }

    private void checkLevelCompletion() {
        if (player.getY() <= 0) {
            level++;
            player.resetPosition();
            generateCars();
        }
    }

    private void generateCars() {
        cars.clear();
        for (int i = 0; i < 4; i++) {
            int y = 465 - 120 * i;
            int speed = (int) (rand.nextInt(3) + 1 + Math.pow(level,2)/4);
            for (int j = 0; j < rand.nextInt(1, level+1); j++) {
                cars.add(new Car(rand.nextInt(800), y, 80, 30, speed));
            }
        }
    }

    private void drawInterface(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString("level: " + level, 10, 20);
    }
}
