import java.awt.*;
import java.util.Random;

public class Pipe {

    private int x;
    private int width = 60;
    private int gap = 150;
    private int topHeight;
    private int panelHeight;
    private int speed = 4;

    private Image topImg;
    private Image bottomImg;

    private Random rand = new Random();

    public Pipe(int startX, int panelHeight, Image topImg, Image bottomImg) {
        this.x = startX;
        this.panelHeight = panelHeight;
        this.topImg = topImg;
        this.bottomImg = bottomImg;

        int min = 50;
        int max = panelHeight - gap - 50;
        topHeight = rand.nextInt(max - min) + min;
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(topImg, x, 0, width, topHeight, null);

        int bottomY = topHeight + gap;
        int bottomHeight = panelHeight - bottomY;

        g.drawImage(bottomImg, x, bottomY, width, bottomHeight, null);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }
}
