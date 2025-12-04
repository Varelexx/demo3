package ucr.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PantallaLogin {

    public static Scene getScene(Stage stage) {

        Label usuarioLabel = crearLabel("Usuario");
        TextField usuarioField = crearField();

        Label passLabel = crearLabel("Contraseña");
        PasswordField passField = new PasswordField();
        passField.setStyle(
                "-fx-background-color: #333;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: gray;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 8;"
        );

        Button entrarBtn = PantallaPrincipal.crearBoton("Entrar");
        Button volverBtn = PantallaPrincipal.crearBoton("Volver");

        volverBtn.setOnAction(e -> stage.setScene(PantallaPrincipal.getScene(stage)));

        VBox layout = new VBox(15, usuarioLabel, usuarioField, passLabel, passField, entrarBtn, volverBtn);
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
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 8;"
        );
        return field;
    }
}
