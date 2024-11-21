package org.example;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class Repository extends PropertyChangeSupport {
    private static Repository instance;
    private final Stack<Point> points = new Stack<>();

    public Repository(){
        super(new Object());
    }

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public Stack<Point> getPoints(){
        return points;
    }

    public void addPoint(Point point){
        if (points.size() == 10) {
            points.removeFirst();
        }
        points.push(point);
        firePropertyChange("points", null, point);
    }
}
