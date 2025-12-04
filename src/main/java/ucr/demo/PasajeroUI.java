package ucr.demo;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PasajeroUI {

    public void mostrar() {
        Stage window = new Stage();
        window.setTitle("Gestión de Pasajeros");

        // Tabla
        TableView<Pasajero> tabla = new TableView<>();

        TableColumn<Pasajero, String> colCedula = new TableColumn<>("Cédula Usuario");
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedulaUsuario"));
        colCedula.setMinWidth(200);

        tabla.getColumns().add(colCedula);
        tabla.setItems(PasajeroDAO.listar());

        // Formulario
        TextField txtCedula = new TextField();
        txtCedula.setPromptText("Cédula del pasajero");
        txtCedula.setMinWidth(200);

        // Botón agregar
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            if (!txtCedula.getText().isEmpty()) {
                PasajeroDAO.agregar(new Pasajero(txtCedula.getText()));
                tabla.setItems(PasajeroDAO.listar());
                txtCedula.clear();
            }
        });

        // Botón actualizar
        Button btnActualizar = new Button("Actualizar");
        btnActualizar.setOnAction(e -> {
            Pasajero seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado != null && !txtCedula.getText().isEmpty()) {
                PasajeroDAO.actualizar(new Pasajero(txtCedula.getText()), seleccionado.getCedulaUsuario());
                tabla.setItems(PasajeroDAO.listar());
                txtCedula.clear();
            }
        });

        // Botón eliminar
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> {
            Pasajero seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                PasajeroDAO.eliminar(seleccionado.getCedulaUsuario());
                tabla.setItems(PasajeroDAO.listar());
            }
        });

        HBox botones = new HBox(10, btnAgregar, btnActualizar, btnEliminar);
        VBox layout = new VBox(10, tabla, txtCedula, botones);
        layout.setPadding(new Insets(15));

        Scene escena = new Scene(layout, 450, 450);
        window.setScene(escena);
        window.show();
    }
}
