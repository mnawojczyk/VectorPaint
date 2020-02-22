package pl.sda.maciej.nawojczyk.vector.paint.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {

    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    public Triangle(double x1, double y1, double x2, double y2) {
        point1 = new Point2D(Math.min(x1, x2), Math.max(y1, y2));
        point2 = new Point2D(Math.max(x1, x2), Math.max(y1, y2));
        point3 = new Point2D((x1 + x2) / 2, Math.min(y1, y2));
    }

    private Triangle(Builder builder){
        point1 = builder.point1;
        point2 = builder.point2;
        point3 = builder.point3;
        setFillColor(builder.fillColor);
        setStrokeColor(builder.strokeColor);
    }

    public void draw(GraphicsContext context) {
        context.beginPath();
        context.moveTo(point1.getX(), point1.getY());
        context.lineTo(point2.getX(), point2.getY());
        context.lineTo(point3.getX(), point3.getY());
        context.lineTo(point1.getX(), point1.getY());
        context.stroke();
        context.fill();
        context.closePath();
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Triangle;");
        builder.append(point1.getX()).append(";");
        builder.append(point1.getY()).append(";");
        builder.append(point2.getX()).append(";");
        builder.append(point2.getY()).append(";");
        builder.append(point3.getX()).append(";");
        builder.append(point3.getY()).append(";");
        builder.append(getFillColor()).append(";");
        builder.append(getStrokeColor()).append(";");
        return builder.toString();
    }

    public static class Builder{
        private Point2D point1;
        private Point2D point2;
        private Point2D point3;
        Color fillColor = Color.CHOCOLATE;
        Color strokeColor = Color.AZURE;

        public Triangle build(){
            return new Triangle(this);
        }

        public Builder setPoint1(double x, double y) {
            this.point1 = new Point2D(x, y);
            return this;
        }

        public Builder setPoint2(double x, double y) {
            this.point2 = new Point2D(x, y);
            return this;
        }

        public Builder setPoint3(double x, double y) {
            this.point3 = new Point2D(x, y);
            return this;
        }

        public Builder setFillColor(String fillColor) {
            this.fillColor = Color.valueOf(fillColor);
            return this;
        }

        public Builder setStrokeColor(String strokeColor) {
            this.strokeColor = Color.valueOf(strokeColor);
            return this;
        }
    }
}

