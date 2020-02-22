package pl.sda.maciej.nawojczyk.vector.paint.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Star extends Shape {

    private double xc;
    private double yc;
    private double radius;

    public Star(double x1, double y1, double x2, double y2) {
        xc = (x1 + x2)/2;
        yc = (y1 + y2)/2;

        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);
        double d = Math.min(width, height);
        radius = d/2;

    }

    private Star(Builder builder){
        this.xc = builder.x1;
        this.yc = builder.y1;
        this.radius = builder.x2;
        setFillColor(builder.fillColor);
        setStrokeColor(builder.strokeColor);
    }

    public void draw(GraphicsContext context) {
        context.beginPath();
        int count = 10;

        context.moveTo(xc, yc - radius);

        for (int i = 0; i < count; i++) {
            double a = (double) i/count * 2 * Math.PI;
            double r = i % 2 == 0 ? radius : radius / 2;
            double xDelta = Math.sin(a) * r;
            double yDelta = Math.cos(a) * r;
            double x = xc + xDelta;
            double y = yc - yDelta;

            context.lineTo(x, y);
        }

        context.closePath();
        context.stroke();
        context.fill();

    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Circle;");
        builder.append(xc).append(";");
        builder.append(yc).append(";");
        builder.append(radius).append(";");
        builder.append(getFillColor()).append(";");
        builder.append(getStrokeColor()).append(";");
        return builder.toString();
    }

    public static class Builder{
        double x1;
        double y1;
        double x2;
        double y2;
        Color fillColor = Color.CHOCOLATE;
        Color strokeColor = Color.AZURE;

        public Star build(){
            return new Star(this);
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
