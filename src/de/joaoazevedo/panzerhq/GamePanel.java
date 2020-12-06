package de.joaoazevedo.panzerhq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;



public class GamePanel extends JPanel {

    public static final String IMAGE_DIR = "./images/";
    private final Dimension prefSize = new Dimension(1180, 780);

    private ImageIcon backgroundImage;
    private final String[] backgroundImages = new String [] {"bg_mud.jpg", "bg_snow.jpg", "bg_sand.jpg"};

    private boolean gameOver = false;
    private int tanksDestroyedCounter = 0;

    private Timer t;

    public GamePanel() {

        setFocusable(true);
        setPreferredSize(prefSize);

        initGame();
        startGame();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private void initGame() {
        setBackgroundImage(1);
        createGameObjects();

        t = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doOnTick();
            }
        });
    }

    private void createGameObjects() {
        //Spielobjekte werden erzeugt
    }

    private void initPlayersTank() {

    }

    public void setBackgroundImage(int imageNumber) {

        String imagePath = IMAGE_DIR + backgroundImages[imageNumber];
        URL imageURL = getClass().getResource(imagePath);
        backgroundImage = new ImageIcon(imageURL);
    }

    private void startGame() {
        t.start();
    }

    public void pauseGame() {
        t.stop();
    }

    public void continueGame() {
        if(!isGameOver()) t.start();
    }

    public void restartGame() {

        tanksDestroyedCounter = 0;
        setGameOver(false);
        createGameObjects();
        startGame();
    }

    public void endGame() {

        setGameOver(true);
        pauseGame();
    }

    private void doOnTick() {

        tanksDestroyedCounter++;

        if(tanksDestroyedCounter > 2015) {
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g; //type cast
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        backgroundImage.paintIcon(null, g,0, 0);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
        g.setColor(Color.BLUE);
        g.drawString("Panzer zerstört: " + tanksDestroyedCounter, 22, prefSize.height / 5);

        if(isGameOver()) {

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", prefSize.width / 2 - 130, prefSize.height / 5);
        }
    }
}
