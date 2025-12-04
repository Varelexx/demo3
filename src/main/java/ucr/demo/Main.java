package ucr.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Uber App - JavaFX");

        // Cargar pantalla principal
        Scene mainScene = PantallaPrincipal.getScene(stage);

        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
