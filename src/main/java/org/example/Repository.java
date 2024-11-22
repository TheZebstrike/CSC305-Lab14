package org.example;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class Repository extends PropertyChangeSupport {
    private static Repository instance;
    private Stack<Point> points = new Stack<>();
    private String subscriber_id;
    private String publisher_id;
    private String whoAmI;

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

    public String getSubscriber_id() {
        return subscriber_id;
    }

    public void setSubscriber_id(String subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getWhoAmI() {
        return whoAmI;
    }

    public void setWhoAmI(String whoAmI) {
        this.whoAmI = whoAmI;
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
        if (!pts.isEmpty()) {
            this.points = new Stack<>();
            for (Point point : pts) {
                addPoint(point);
            }
        }
    }
    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }
}
