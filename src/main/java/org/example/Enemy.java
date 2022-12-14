package org.example;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    int x;
    int y;
    int v;
    int width = 128;
    int height = 64;
    Image img = new ImageIcon("src/main/resources/enemy_matiz.png")
            .getImage();
    Road road;


    public Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }


    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    public void move() {
        x = x - road.p.v + v;
    }



}
