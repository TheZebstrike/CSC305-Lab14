package org.example;

import javax.swing.*;
import java.awt.*;

public class ClientMain extends JFrame{
    public ClientMain(){
        setLayout(new GridLayout(1,1));
        DrawArea canvas = new DrawArea();
        add(canvas);

        DrawAreaListener drawAreaListener = new DrawAreaListener();
        canvas.addMouseListener(drawAreaListener);

        Repository.getInstance().addPropertyChangeListener(canvas);
    }

    public static void main(String[] args){
        ClientMain main = new ClientMain();
        main.setSize(500, 500);
        main.setTitle("MQTT Click and Draw Dot");
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);

        DrawPointSubscriber drawPointSubscriber = new DrawPointSubscriber();
        drawPointSubscriber.run();
    }
}