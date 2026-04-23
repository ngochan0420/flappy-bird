import java.awt.*;
import javax.swing.*;

public class AssetManager extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    AssetManager() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.blue);

        backgroundImg = new ImageIcon(getClass().getResource("./bg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./bird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./pipe_top.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./pipe_bottom.png")).getImage();   
    }
}
