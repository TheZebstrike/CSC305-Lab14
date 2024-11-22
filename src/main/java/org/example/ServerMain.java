package org.example;

import javax.swing.*;
import java.awt.*;

public class ServerMain extends JFrame{
    public ServerMain(){
        setLayout(new GridLayout(1,1));
        DrawArea canvas = new DrawArea();
        add(canvas);

        DrawAreaListener drawAreaListener = new DrawAreaListener();
        canvas.addMouseListener(drawAreaListener);

        Repository.getInstance().addPropertyChangeListener(canvas);
    }

    public static void main(String[] args){
        ServerMain main = new ServerMain();
        main.setSize(500, 500);
        main.setTitle("MQTT Click and Draw Dot");
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);

        DrawPointPublisher drawPointPublisher = new DrawPointPublisher();
        drawPointPublisher.run();
    }
}