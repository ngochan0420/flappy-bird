import java.awt.Rectangle;

public class Player {
    int x = 50;
    double y = 300;
    int width = 34;
    int height = 24;
    double v = 0;
    double g = 0.5;
    double j = -8;

    int boardHeight = 640;

    public void move() {
        v = v + g;
        y = y + v;
        if (y < 0) y = 0;
    }

    public void update() {
        move();
    }

    public void flap() {
        v = j;
    }

    public boolean isOnGround() {
        return y + height >= boardHeight;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, (int) y, width, height);
    }

    public void reset() {
        y = 300;
        v = 0;
    }
}
