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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sda.maciej.nawojczyk.vector.paint.io.SDAFileReader;
import pl.sda.maciej.nawojczyk.vector.paint.shapes.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

   private List<Shape> shapeList = new ArrayList<>();
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
                applyShape();
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                endX = event.getX();
                endY = event.getY();
                System.out.printf("Released x=%f y=%f\n", endX, endY);
                prepareShape();
//                applyShape();
                refreshCanvas();
            }
        });
    }

    private void applyShape() {
        shapeList.add(currentShape);
//        currentShape == null;
    }

    private void prepareShape() {
        currentShape = createShape();
        currentShape.setFillColor(fillColorPicker.getValue());
        currentShape.setStrokeColor(strokeColorPicker.getValue());
    }

    private Shape createShape() {

        switch (currentTool){
            default:
            case LINE:
                return new Line(startX, startY, endX, endY);
            case RECTANGLE:
                return new Rectangle(startX, startY, endX, endY);
            case TRIANGLE:
                return new Triangle(startX, startY, endX, endY);
            case ELLIPSE:
                return new Ellipse(startX, startY, endX, endY);
            case CIRCLE:
                return new Circle(startX, startY, endX, endY);
            case STAR:
                return new Star(startX, startY, endX, endY);
        }
    }

    private void refreshCanvas() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setStroke(Color.BLACK);
        context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Shape shape : shapeList){
            shape.drawShape(context);
        }

        if(currentShape != null){
            currentShape.drawShape(context);
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

    @FXML
    public void handleSave() {
        Optional<String> reduce = shapeList.stream()
                .map(shape -> shape.getData())
                .reduce((acc, text) -> acc + "\n" + text);
        if (reduce.isPresent()) {
            System.out.println(reduce.get());
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YOLO files (*.yolo)", "*.yolo");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null) {
                saveTextToFile(reduce.get(), file);
            } else{
                //todo nie ma co zapisać
            }
        }
    }
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YOLO files (*.yolo)", "*.yolo");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        if(file != null){
            ShapeFactory factory = new ShapeFactory();
            SDAFileReader reader = new SDAFileReader(file);

            shapeList = reader.readFile().stream()
                    .map(String -> factory.get(String))
                    .filter(shape -> shape != null )
                    .collect(Collectors.toList());
            refreshCanvas();

        }

        System.out.println(file.getAbsolutePath());
    }

    public void handleClear() {
        shapeList.clear();
        refreshCanvas();
    }
}

