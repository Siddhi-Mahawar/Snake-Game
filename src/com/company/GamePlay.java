package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private  int[] snakeXLength = new int[750];
    private  int[] snakeYLength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean top = false;
    private boolean bottom = false;

    private  ImageIcon rightmouth;
    private  ImageIcon upmouth;
    private  ImageIcon downmouth;
    private  ImageIcon leftmouth;

    private  Timer timer;
    private int delay = 100;

    private int score = 0;

    private int lengthofsnake = 3;

    private ImageIcon snakeimage;
    private ImageIcon titleImage;

    private int moves = 0;

    private  int[] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private  int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private ImageIcon enemyimage;

    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){

        if(moves == 0)
        {
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;

        }


        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        titleImage = new ImageIcon("src/images/snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);

        // draw borders for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        // draw background for the gameplay
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        // draw scores

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Score : "+score,780,30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length : "+lengthofsnake,780,50);

        rightmouth = new ImageIcon("src/images/rightmouth.png");
        rightmouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);

        for(int a = 0;a<lengthofsnake;a++)
        {
            if(a==0 && right)
            {
                rightmouth = new ImageIcon("src/images/rightmouth.png");
                rightmouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }
            if(a==0 && left)
            {
                leftmouth = new ImageIcon("src/images/leftmouth.png");
                leftmouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }
            if(a==0 && bottom)
            {
                downmouth = new ImageIcon("src/images/downmouth.png");
                downmouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }
            if(a==0 && top)
            {
                upmouth = new ImageIcon("src/images/upmouth.png");
                upmouth.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }
            if(a!=0)
            {
                snakeimage = new ImageIcon("src/images/snakeimage.png");
                snakeimage.paintIcon(this,g,snakeXLength[a],snakeYLength[a]);
            }

        }


        enemyimage = new ImageIcon("src/images/enemy.png");
        if((enemyxpos[xpos] == snakeXLength[0]) &&  enemyypos[ypos] == snakeYLength[0])
        {
            lengthofsnake++;
            score++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

        for(int b = 1;b<lengthofsnake;b++)
        {
            if(snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0])
            {
                right = false;
                left = false;
                top = false;
                bottom = false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("Game Over ",300,300);

                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("Space to restart ",300,400);


            }
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if(right)
        {
            for(int r = lengthofsnake-1;r>=0;r--) {
                snakeYLength[r + 1] = snakeYLength[r];
            }
            for(int r = lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeXLength[r] = snakeXLength[r]+25;

                }
                else
                {
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if(snakeXLength[r]>850)
                {
                    snakeXLength[r] = 25;

                }
            }

            repaint();

        }
        if(left)
        {

            for(int r = lengthofsnake-1;r>=0;r--) {
                snakeYLength[r + 1] = snakeYLength[r];
            }
            for(int r = lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeXLength[r] = snakeXLength[r] - 25;

                }
                else
                {
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if(snakeXLength[r] < 25)
                {
                    snakeXLength[r] = 850;

                }
            }
            repaint();
        }
        if(top)
        {

            for(int r = lengthofsnake-1;r>=0;r--) {
                snakeXLength[r + 1] = snakeXLength[r];
            }
            for(int r = lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeYLength[r] = snakeYLength[r] - 25;

                }
                else
                {
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if(snakeYLength[r]<75)
                {
                    snakeYLength[r] = 625;

                }
            }

            repaint();
        }
        if(bottom)
        {

            for(int r = lengthofsnake-1;r>=0;r--) {
                snakeXLength[r + 1] = snakeXLength[r];
            }
            for(int r = lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeYLength[r] = snakeYLength[r]+25;

                }
                else
                {
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if(snakeYLength[r]>625)
                {
                    snakeYLength[r] = 75;

                }
            }

            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            repaint();
        }

            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                moves++;
                right = true;

                if(!left){
                    right = true;
                }
                else
                {
                    right = false;
                    left = true;
                }

                bottom = false;
                top = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                moves++;
                left = true;

                if(!right){
                    left = true;
                }
                else
                {
                    right = false;
                    left = true;
                }

                bottom = false;
                top = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                moves++;
                top = true;

                if(!bottom){
                    top = true;
                }
                else
                {
                    top = true;
                    bottom = false;
                }

                left = false;
                right = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                moves++;
                bottom = true;

                if(!top){
                    bottom = true;
                }
                else
                {
                    top = false;
                    bottom = true;
                }

                left = false;
                right = false;
            }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
