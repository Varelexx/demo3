package ucr.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PantallaCRUDUsuarios {

    public static Scene getScene(Stage stage) {

        // --- Tabla ---
        TableView<Usuario> tabla = new TableView<>();
        tabla.setItems(UsuarioDAO.listar());

        tabla.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");

        // Columnas
        tabla.getColumns().add(col("Cédula", "cedula"));
        tabla.getColumns().add(col("Nombre", "nombre"));
        tabla.getColumns().add(col("Apellidos", "apellidos"));
        tabla.getColumns().add(col("Genero", "genero"));
        tabla.getColumns().add(col("Telefono", "telefono"));
        tabla.getColumns().add(col("Nacimiento", "fechaNacimiento"));
        tabla.getColumns().add(col("Referido", "referido"));

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Campos
        TextField cedula = field("Cédula");
        TextField nombre = field("Nombre");
        TextField apellidos = field("Apellidos");
        TextField genero = field("Genero (Masculino/Femenino)");
        TextField telefono = field("Teléfono");
        DatePicker nacimiento = new DatePicker();
        nacimiento.setPromptText("Fecha de nacimiento");
        nacimiento.setStyle(
                "-fx-background-color: #222; -fx-text-fill: white; -fx-background-radius: 10;"
        );

        TextField referido = field("Cédula Referido (opcional)");

        // Botones (estilo oscuro)
        Button agregar = boton("Agregar");
        Button actualizar = boton("Actualizar");
        Button eliminar = boton("Eliminar");
        Button limpiar = boton("Limpiar");
        Button volver = boton("Volver");

        // Eventos
        agregar.setOnAction(e -> {
            String fechaNac = nacimiento.getValue() != null
                    ? nacimiento.getValue().toString()
                    : null;

            Usuario u = new Usuario(
                    cedula.getText(), nombre.getText(), apellidos.getText(),
                    genero.getText(), telefono.getText(),
                    fechaNac,
                    referido.getText().isEmpty() ? null : referido.getText()
            );

            UsuarioDAO.agregar(u);
            tabla.setItems(UsuarioDAO.listar());
        });

        actualizar.setOnAction(e -> {
            Usuario u = tabla.getSelectionModel().getSelectedItem();
            if (u == null) return;

            u.setNombre(nombre.getText());
            u.setApellidos(apellidos.getText());
            u.setGenero(genero.getText());
            u.setTelefono(telefono.getText());

            String fechaNac = nacimiento.getValue() != null
                    ? nacimiento.getValue().toString()
                    : null;

            u.setFechaNacimiento(fechaNac);
            u.setReferido(referido.getText());

            UsuarioDAO.actualizar(u);
            tabla.setItems(UsuarioDAO.listar());
        });


        eliminar.setOnAction(e -> {
            Usuario u = tabla.getSelectionModel().getSelectedItem();
            if (u == null) return;

            UsuarioDAO.eliminar(u.getCedula());
            tabla.setItems(UsuarioDAO.listar());
        });

        limpiar.setOnAction(e -> {
            cedula.clear();
            nombre.clear();
            apellidos.clear();
            genero.clear();
            telefono.clear();
            //nacimiento.clear();
            referido.clear();
        });

        // Llenar al hacer clic
        tabla.setOnMouseClicked(e -> {
            Usuario u = tabla.getSelectionModel().getSelectedItem();
            if (u == null) return;

            cedula.setText(u.getCedula());
            nombre.setText(u.getNombre());
            apellidos.setText(u.getApellidos());
            genero.setText(u.getGenero());
            telefono.setText(u.getTelefono());

            // Convertir String → LocalDate
            if (u.getFechaNacimiento() != null) {
                nacimiento.setValue(java.time.LocalDate.parse(u.getFechaNacimiento()));
            } else {
                nacimiento.setValue(null);
            }

            referido.setText(u.getReferido());
        });
        volver.setOnAction(e -> stage.setScene(PantallaPrincipal.getScene(stage)));

        // Layout
        VBox campos = new VBox(10, cedula, nombre, apellidos, genero, telefono, nacimiento, referido);
        campos.setStyle("-fx-background-color: #000; -fx-padding: 15;");

        HBox botones = new HBox(10, agregar, actualizar, eliminar, limpiar);
        botones.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, tabla, campos, botones, volver);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #000000;");

        return new Scene(root, 900, 600);
    }

    // Métodos auxiliares
    private static TableColumn<Usuario, String> col(String titulo, String propiedad) {
        TableColumn<Usuario, String> c = new TableColumn<>(titulo);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(propiedad));
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
        b.setStyle(
                "-fx-background-color: #333;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 8 20;"
        );
        return b;
    }
}
