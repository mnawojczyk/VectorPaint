package pl.sda.maciej.nawojczyk.vector.paint.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Shape {

    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    public Triangle(double x1, double y1, double x2, double y2) {
        point1 = new Point2D(Math.min(x1, x2), Math.max(y1, y2));
        point2 = new Point2D(Math.max(x1, x2), Math.max(y1, y2));
        point3 = new Point2D((x1 - x2) / 2, Math.min(y1, y2));
    }

    public void draw(GraphicsContext context) {

    }
}
