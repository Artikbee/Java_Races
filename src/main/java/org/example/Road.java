package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    // the timer will run actionPerformed every 20 sec in this object
    Timer mainTimer = new Timer(20, this);
    Image img = new ImageIcon("src/main/resources/races.jpeg")
            .getImage();

    Player p = new Player();

    int width = 128;

    int height = 64;

    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies = new ArrayList<>();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();

        addKeyListener(new MyKeyAdapter());

        setFocusable(true);
    }

    // enemies will appear with a random delay
    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200,
                        rand.nextInt(400),
                        rand.nextInt(60),
                        this));

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // creating a keyboard
    private class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }


        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }

    }

    //  drawing method
    public void paint(Graphics g) {

        g = (Graphics2D) g;

        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, width, height, null);

        double v = p.v;
        g.setColor(Color.BLACK);
        Font font = new Font("Times New Roman", Font.PLAIN, 22);
        g.setFont(font);
        g.drawString("Speed: " + v + " km/h", 50, 20);

        //рисуем врагов
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                g.drawImage(e.img, e.x, e.y, width, height, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        for (Enemy e : enemies) {
            e.move();
        }

        p.move();


        repaint();
        collisionWithEnemies();
        testWin();

    }

    private void testWin() {

        if (p.s > 100000) {
            JOptionPane.
                    showMessageDialog(null, "You've won!!!");
            System.exit(1);
        }

    }


    private void collisionWithEnemies() {

        for (Enemy e : enemies) {
            if (p.getRect().intersects(e.getRect())) {
                JOptionPane.
                        showMessageDialog(null, "You've lost!!!");
                System.exit(1);
            }

        }
    }
}
