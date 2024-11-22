package org.example;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class DrawArea extends JPanel implements PropertyChangeListener{
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        List<Point> points = Repository.getInstance().getPoints();
        for(int i = 0; i < points.size(); i++){
            Point p = points.get(i);
            if(i == points.size() - 1){
                g.setColor(Color.BLACK);
            } else{
                int shade = 255 - (i * (255 / points.size()));
                g.setColor(new Color(shade, shade, shade));
            }
            g.fillOval(p.x, p.y, 10, 10);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        repaint();
    }
}
