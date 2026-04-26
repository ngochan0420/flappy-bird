import java.awt.*;
import java.util.Random;

public class Pipe {
    int x;
    int width = 64;
    int speed = 4;

    int gap = 150;
    int topHeight;

    Image topImg;
    Image bottomImg;

    int boardWidth;
    int boardHeight;

    boolean passed = false; 

    Random rand = new Random();

    public Pipe(int startX, int boardHeight, Image topImg, Image bottomImg) {
        this.x = startX;
        this.boardHeight = boardHeight;
        this.boardWidth = startX;
        this.topImg = topImg;
        this.bottomImg = bottomImg;
        randomHeight();
    }

    public void randomHeight() {
        int min = 50;
        int max = boardHeight - gap - 50;
        topHeight = rand.nextInt(max - min) + min;
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(topImg, x, 0, width, topHeight, null);
        int bottomY = topHeight + gap;
        int bottomHeight = boardHeight - bottomY;
        g.drawImage(bottomImg, x, bottomY, width, bottomHeight, null);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public void reset() {
        x = boardWidth;
        passed = false;
        randomHeight();
    }

    public boolean collidesWith(Player player) {
        Rectangle playerBounds = player.getBounds();
        Rectangle topPipe = new Rectangle(x, 0, width, topHeight);
        int bottomY = topHeight + gap;
        Rectangle bottomPipe = new Rectangle(x, bottomY, width, boardHeight - bottomY);
        return playerBounds.intersects(topPipe) || playerBounds.intersects(bottomPipe);
    }

    public boolean checkAndMarkPassed(Player player) {
        if (!passed && player.x > x + width) {
            passed = true;
            return true;
        }
        return false;
    }
}
