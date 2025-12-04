package ucr.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PantallaCRUDConductores {

    public static Scene getScene(Stage stage) {

        // Tabla
        TableView<Conductor> tabla = new TableView<>();
        tabla.setItems(ConductorDAO.listar());

        tabla.setStyle("-fx-background-color: #000; -fx-text-fill: white;");

        tabla.getColumns().add(col("Cédula", "cedulaUsuario"));
        tabla.getColumns().add(col("Ganancias Diarias", "gananciasDiarias"));
        tabla.getColumns().add(col("Horas Laboradas", "tiempoLaborado"));

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Campos
        TextField cedula = field("Cédula del usuario");
        TextField ganancias = field("Ganancias diarias");

        Spinner<Integer> tiempo = new Spinner<>(0, 24, 8);
        tiempo.setEditable(true);
        tiempo.setStyle("-fx-background-color: #222; -fx-text-fill: white; -fx-background-radius: 10;");

        // Botones
        Button agregar = boton("Agregar");
        Button actualizar = boton("Actualizar");
        Button eliminar = boton("Eliminar");
        Button limpiar = boton("Limpiar");
        Button volver = boton("Volver");

        // Evento AGREGAR
        agregar.setOnAction(e -> {
            try {
                Conductor c = new Conductor(
                        cedula.getText(),
                        Double.parseDouble(ganancias.getText()),
                        tiempo.getValue()
                );

                ConductorDAO.agregar(c);
                tabla.setItems(ConductorDAO.listar());
            } catch (Exception ex) {
                System.out.println("Error al leer datos: " + ex.getMessage());
            }
        });
        volver.setOnAction(e -> stage.setScene(PantallaPrincipal.getScene(stage)));

        // Evento ACTUALIZAR
        actualizar.setOnAction(e -> {
            Conductor c = tabla.getSelectionModel().getSelectedItem();
            if (c == null) return;

            c.setGananciasDiarias(Double.parseDouble(ganancias.getText()));
            c.setTiempoLaborado(tiempo.getValue());

            ConductorDAO.actualizar(c);
            tabla.setItems(ConductorDAO.listar());
        });

        // Evento ELIMINAR
        eliminar.setOnAction(e -> {
            Conductor c = tabla.getSelectionModel().getSelectedItem();
            if (c == null) return;

            ConductorDAO.eliminar(c.getCedulaUsuario());
            tabla.setItems(ConductorDAO.listar());
        });

        // Llenado al dar clic
        tabla.setOnMouseClicked(e -> {
            Conductor c = tabla.getSelectionModel().getSelectedItem();
            if (c == null) return;

            cedula.setText(c.getCedulaUsuario());
            ganancias.setText(String.valueOf(c.getGananciasDiarias()));
            tiempo.getValueFactory().setValue(c.getTiempoLaborado());
        });

        // Limpieza
        limpiar.setOnAction(e -> {
            cedula.clear();
            ganancias.clear();
            tiempo.getValueFactory().setValue(8);
        });

        // Layout
        VBox campos = new VBox(10, cedula, ganancias, tiempo);
        campos.setPadding(new Insets(10));
        campos.setStyle("-fx-background-color: #000;");

        HBox botones = new HBox(10, agregar, actualizar, eliminar, limpiar);
        botones.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, tabla, campos, botones, volver);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #000;");

        return new Scene(root, 900, 600);
    }

    private static TableColumn<Conductor, String> col(String titulo, String prop) {
        TableColumn<Conductor, String> c = new TableColumn<>(titulo);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        return c;
    }

    private static TextField field(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        tf.setStyle("-fx-background-color: #222; -fx-text-fill: white; -fx-background-radius: 10;");
        return tf;
    }

    private static Button boton(String texto) {
        Button b = new Button(texto);
        b.setStyle("""
                -fx-background-color: #333;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-background-radius: 10;
                -fx-padding: 8 20;
            """);
        return b;
    }
}
