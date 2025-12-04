package ucr.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PantallaPrincipal {

    public static Scene getScene(Stage stage) {

        Text titulo = new Text("Uber");
        titulo.setFont(Font.font("Arial", 48));
        titulo.setFill(Color.WHITE);

     // Button btnPedir = crearBoton("Pedir Viaje");
        Button btnLogin = crearBoton("Iniciar Sesión");
        Button btnCRUD = crearBoton("Usuarios ");
        Button btnCRUD2 = crearBoton("Conductores ");
        Button btnCRUD3 = crearBoton("Vehículos ");
        Button btnViaje = crearBoton("Registrar viaje");
        Button btnPasajeros = crearBoton("Pasajeros");
        btnPasajeros.setOnAction(e -> new PasajeroUI().mostrar());


       // btnPedir.setOnAction(e -> stage.setScene(PantallaPedir.getScene(stage)));
        btnLogin.setOnAction(e -> stage.setScene(PantallaLogin.getScene(stage)));
        btnCRUD.setOnAction(e -> stage.setScene(PantallaCRUDUsuarios.getScene(stage)));
        btnCRUD2.setOnAction(e -> stage.setScene(PantallaCRUDConductores.getScene(stage)));
        btnViaje.setOnAction(e -> new FormViaje().mostrar());
        VBox layout = new VBox(30, titulo, btnViaje, btnLogin, btnCRUD, btnCRUD2, btnPasajeros);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #000000;");




        return new Scene(layout, 420, 420);

    }

    static Button crearBoton(String texto) {
        Button btn = new Button(texto);
        btn.setStyle(
                "-fx-background-color: #222;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-padding: 12 30;" +
                        "-fx-background-radius: 10;"
        );
        return btn;
    }


}
