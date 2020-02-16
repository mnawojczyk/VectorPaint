package pl.sda.maciej.nawojczyk.vector.paint.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Shape {

    private Paint fillColor = Color.PINK;
    private Paint strokeColor = Color.CHOCOLATE;


    public abstract void draw(GraphicsContext context);

    public Paint getFillColor() {
        return fillColor;
    }

    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
    }
}
