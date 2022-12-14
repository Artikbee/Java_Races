package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //creating a frame
        JFrame f = new JFrame("Java Races");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1100,600);

        //adding a class Road
        f.add(new Road());

        f.setVisible(true);
    }
}