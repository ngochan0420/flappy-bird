import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    int width = 360;
    int height = 640;

    BufferedImage bg;
    BufferedImage birdImg;
    BufferedImage pipeTop;
    BufferedImage pipeBottom;

    int birdX = 70;
    int birdY = 160;
    double velocity = 0;

    int pipeX = width;
    int pipeWidth = 50;
    int pipeGap = 120;
    int pipeTopHeight = 140;

    int score = 0;
    boolean gameOver = false;
    boolean started = false;

    Timer timer;

    public GamePanel() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);
        loadImage();
        timer = new Timer(20, this);
        timer.start();
    }

    public void loadImage() {
        try {
            bg          = ImageIO.read(getClass().getResource("/images/bg.png"));
            birdImg     = ImageIO.read(getClass().getResource("/images/bird.png"));
            pipeTop     = ImageIO.read(getClass().getResource("/images/pipe_top.png"));
            pipeBottom  = ImageIO.read(getClass().getResource("/images/pipe_bottom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bg, 0, 0, width, height, null);
        g.drawImage(birdImg, birdX, (int) birdY, 30, 30, null);
        g.drawImage(pipeTop, pipeX, 0, pipeWidth, pipeTopHeight, null);
        g.drawImage(pipeBottom, pipeX, pipeTopHeight + pipeGap,
                pipeWidth, height - (pipeTopHeight + pipeGap), null);

        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.black);
        g.drawString("Score: " + score, 20, 30);

        if (!started && !gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press SPACE to start", width / 2 - 110, height / 2);
        }

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.red);
            g.drawString("Game Over!", width / 2 - 80, height / 2 - 20);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.setColor(Color.black);
            g.drawString("Press SPACE to restart", width / 2 - 110, height / 2 + 20);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!started || gameOver) return;

        velocity += 0.8;
        birdY += velocity;

        pipeX -= 3;
        if (pipeX < -pipeWidth) {
            pipeX = width;
            pipeTopHeight = 80 + (int)(Math.random() * 200);
            score++;
        }

        if (birdY >= height - 30 || birdY <= 0) {
            gameOver = true;
        }

        boolean birdInPipeColumn = (birdX + 30 > pipeX) && (birdX < pipeX + pipeWidth);
        boolean birdHitPipe     = (birdY < pipeTopHeight) || (birdY + 30 > pipeTopHeight + pipeGap);
        if (birdInPipeColumn && birdHitPipe) {
            gameOver = true;
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                birdY = 160;
                velocity = 0;
                pipeX = width;
                pipeTopHeight = 140;
                score = 0;
                gameOver = false;
                started = true;
            } else {
                started = true;
                velocity = -9; 
            }
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
