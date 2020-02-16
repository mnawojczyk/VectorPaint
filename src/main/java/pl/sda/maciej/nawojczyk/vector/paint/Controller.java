package pl.sda.maciej.nawojczyk.vector.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.sda.maciej.nawojczyk.vector.paint.shapes.Line;
import pl.sda.maciej.nawojczyk.vector.paint.shapes.Rectangle;
import pl.sda.maciej.nawojczyk.vector.paint.shapes.Shape;

public class Controller {

   @FXML public ColorPicker fillColorPicker;
   @FXML public ColorPicker strokeColorPicker;
   @FXML public Canvas canvas;
   @FXML public Button lineTool;
   @FXML public Button rectangleTool;
   @FXML public Button triangleTool;
   @FXML public Button circleTool;
   @FXML public Button ellipseTool;
   @FXML public Button starTool;

   private double startX;
   private double startY;

   private double endX;
   private double endY;

   private Shape currentShape;
   private Tool currentTool = Tool.LINE;

    public void initialize(){
        System.out.println("Hello!");
        refreshCanvas();
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
               startX = event.getX();
               startY = event.getY();
                System.out.printf("Pressed x=%f y=%f\n", startX, startY);
                refreshCanvas();
            }
        });
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                endX = event.getX();
                endY = event.getY();
                System.out.printf("Released x=%f y=%f\n", endX, endY);
                prepareShape();
                refreshCanvas();
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                endX = event.getX();
                endY = event.getY();
                System.out.printf("Released x=%f y=%f\n", endX, endY);
                prepareShape();
                refreshCanvas();
            }
        });
    }

    private void prepareShape() {
        currentShape = createShape();
    }

    private Shape createShape() {

        switch (currentTool){
            default:
            case LINE:
                return new Line(startX, startY, endX, endY);
            case RECTANGLE:
                return new Rectangle(startX, startY, endX, endY);
        }
    }

    private void refreshCanvas() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setStroke(Color.BLACK);
        context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if(currentShape != null){
            currentShape.draw(context);
        }

    }

    @FXML
    public void changeTool(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source == lineTool){
            currentTool = Tool.LINE;
        }else if(source == rectangleTool){
            currentTool = Tool.RECTANGLE;
        }else if(source == triangleTool){
            currentTool = Tool.TRIANGLE;
        }else if(source == circleTool){
            currentTool = Tool.CIRCLE;
        }else if(source == ellipseTool){
            currentTool = Tool.ELLIPSE;
        }else if(source == starTool){
            currentTool = Tool.STAR;
        }else{
            throw new IllegalStateException("Unsupported Tool");
        }
        System.out.println(currentShape);
    }


}
