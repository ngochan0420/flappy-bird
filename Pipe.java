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

    int boardHeight;

    Random rand = new Random();

    public Pipe(int startX, int boardHeight, Image topImg, Image bottomImg) {
        this.x = startX;
        this.boardHeight = boardHeight;
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
}
