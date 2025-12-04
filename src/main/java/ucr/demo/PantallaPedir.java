package ucr.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PantallaPedir {

    public static Scene getScene(Stage stage) {

        Label origenLabel = crearLabel("Ubicación actual");
        TextField origenField = crearField();

        Label destinoLabel = crearLabel("Destino");
        TextField destinoField = crearField();

        Button solicitarBtn = PantallaPrincipal.crearBoton("Solicitar viaje");
        solicitarBtn.setOnAction(e -> {
            System.out.println("Viaje de " + origenField.getText() +
                    " a " + destinoField.getText());
        });

        Button volverBtn = PantallaPrincipal.crearBoton("Volver");
        volverBtn.setOnAction(e -> stage.setScene(PantallaPrincipal.getScene(stage)));

        VBox layout = new VBox(15, origenLabel, origenField, destinoLabel, destinoField, solicitarBtn, volverBtn);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #000000;");

        return new Scene(layout, 420, 480);
    }

    private static Label crearLabel(String texto) {
        Label label = new Label(texto);
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-size: 16px;");
        return label;
    }

    private static TextField crearField() {
        TextField field = new TextField();
        field.setStyle(
                "-fx-background-color: #333;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: gray;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 8;"
        );
        return field;
    }
}
