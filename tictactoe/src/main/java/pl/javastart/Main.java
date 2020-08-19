package pl.javastart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        showStage(stage);

    }

    public void showStage(Stage stage) throws IOException {
        VBox vBox = FXMLLoader.load(getClass().getResource("/mainPane.fxml"));
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Java");
        stage.show();
    }
}
