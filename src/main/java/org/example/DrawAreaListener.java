package org.example;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawAreaListener implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e){
        Point point = new Point(e.getX(), e.getY());
        Repository.getInstance().addPoint(new Point(e.getX(), e.getY()));

        MQTTManager.getInstance().publishPoint(point);
    }

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}
}
