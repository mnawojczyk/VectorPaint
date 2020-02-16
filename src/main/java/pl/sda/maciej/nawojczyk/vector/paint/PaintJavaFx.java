package pl.sda.maciej.nawojczyk.vector.paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PaintJavaFx extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image("icon.png");
        Parent root = FXMLLoader.load(getClass()
                .getResource("/paint.fxml"));
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Paint");
        Scene scene = new Scene(root, primaryStage.getWidth(),
                primaryStage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

