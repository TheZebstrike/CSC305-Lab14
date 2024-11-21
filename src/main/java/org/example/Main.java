package org.example;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public Main(){
        setLayout(new GridLayout(1,1));
        DrawArea canvas = new DrawArea();
        add(canvas);

        DrawAreaListener drawAreaListener = new DrawAreaListener();
        canvas.addMouseListener(drawAreaListener);

        Repository.getInstance().addPropertyChangeListener(canvas);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.setSize(500, 500);
        main.setTitle("My Lab");
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}