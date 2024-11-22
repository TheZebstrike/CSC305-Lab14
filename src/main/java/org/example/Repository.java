package org.example;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class Repository extends PropertyChangeSupport {
    private static Repository instance;
    private Stack<Point> points = new Stack<>();
    private Boolean isReady = Boolean.TRUE;

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

    public String pointsToString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
            sb.append("(").append(point.x).append(",").append(point.y).append(");");
        }
        return sb.toString();
    }
    public void parseAndSetPoints(String pointsString) {
        Stack<Point> pts = new Stack<>();
        if (pointsString != null && !pointsString.isEmpty()) {

            String[] pointPairs = pointsString.split(";");
            for (String pair : pointPairs) {
                if (!pair.trim().isEmpty()) {
                    String[] coordinates = pair.replace("(", "").replace(")", "").split(",");
                    int x = Integer.parseInt(coordinates[0].trim());
                    int y = Integer.parseInt(coordinates[1].trim());
                    pts.push(new Point(x, y));
                }
            }
        }
        if ((pts.size() == 10 && !pts.equals(points)) || pts.size() > points.size()) {
            this.points = new Stack<>();
            for (Point point : pts) {
                addPoint(point);
            }
            setReady(true);
        } else {
            setReady(false);
        }
    }
    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
