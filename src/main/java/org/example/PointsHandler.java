package org.example;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class PointsHandler {

    public static String pointsToString(Set<Point> points) {
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
            sb.append("(").append(point.x).append(",").append(point.y).append(");");
        }
        return sb.toString();
    }

    public static Set<Point> parsePointsString(String pointsString) {
        Set<Point> points = new LinkedHashSet<>();
        if (pointsString == null || pointsString.isEmpty()) {
            return points;
        }
        String[] pointPairs = pointsString.split(";");
        for (String pair : pointPairs) {
            if (!pair.trim().isEmpty()) {
                String[] coordinates = pair.replace("(", "").replace(")", "").split(",");
                int x = Integer.parseInt(coordinates[0].trim());
                int y = Integer.parseInt(coordinates[1].trim());
                points.add(new Point(x, y)); // Add to set, ensuring uniqueness
            }
        }
        return points;
    }
}
