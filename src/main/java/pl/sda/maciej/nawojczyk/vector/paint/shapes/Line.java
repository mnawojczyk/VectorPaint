package pl.sda.maciej.nawojczyk.vector.paint.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {

    public double x1;
    public double x2;
    public double y1;
    public double y2;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    private Line(Builder builder){
        this.x1 = builder.x1;
        this.y1 = builder.y1;
        this.x2 = builder.x2;
        this.y2 = builder.y2;
        setFillColor(builder.fillColor);
        setStrokeColor(builder.strokeColor);
    }

    public void draw(GraphicsContext context) {
        context.setStroke(getStrokeColor());
        context.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Line;");
        builder.append(x1).append(";");
        builder.append(y1).append(";");
        builder.append(x2).append(";");
        builder.append(y2).append(";");
        builder.append(getFillColor()).append(";");
        builder.append(getStrokeColor()).append(";");
        return builder.toString();
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public static class Builder{
        double x1;
        double y1;
        double x2;
        double y2;
        Color fillColor = Color.CHOCOLATE;
        Color strokeColor = Color.AZURE;

        public Line build(){
            return new Line(this);
        }

        public Builder setX1(double x1) {
            this.x1 = x1;
            return this;
        }

        public Builder setY1(double y1) {
            this.y1 = y1;
            return this;
        }

        public Builder setX2(double x2) {
            this.x2 = x2;
            return this;
        }

        public Builder setY2(double y2) {
            this.y2 = y2;
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
