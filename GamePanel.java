/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
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
 int velocity = 0;
 
 int PipeX = width ;
 int pipeWidth = 50;
int pipeGap =120;
int pipeTopHeight = 140;

int score = 0;

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
           bg = ImageIO.read(getClass().getResource("/img/bg.png"));

             birdImg = ImageIO.read(getClass().getResource("/img/bird.png"));

             pipeTop = ImageIO.read(getClass().getResource("/img/pipe_top.png"));

             pipeBottom = ImageIO.read(getClass().getResource("/img/pipe_bottom.png"));

       } catch (IOException e){
           e.printStackTrace();
       }
   }
   protected void paintComponent(Graphics g){
       super.paintComponent(g);


         g.drawImage(bg, 0, 0, width, height, null);

         
         g.drawImage(birdImg, birdX, birdY, 30, 30, null);


         g.drawImage(pipeTop, PipeX, 0, pipeWidth, pipeTopHeight, null);

       
         g.drawImage(pipeBottom, PipeX,

                 pipeTopHeight + pipeGap,

                 pipeWidth,

                 height - (pipeTopHeight + pipeGap),

                 null);

         g.setFont(new Font("Arial", Font.BOLD, 24));

         g.setColor(Color.black);

         g.drawString("Score: " + score, 20, 30);
   }
public void actionPerformed(ActionEvent e) {

       
         velocity += 0.8;

         birdY += velocity;

       

         PipeX -= 3;

         if (PipeX < -pipeWidth) {

             PipeX = width;

             score++;

         }

         repaint();

     }

     public void keyPressed(KeyEvent e) {

         if (e.getKeyCode() == KeyEvent.VK_SPACE) {

             velocity = -9;

         }

     }

     public void keyReleased(KeyEvent e) {}

     public void keyTyped(KeyEvent e) {}

 }
 