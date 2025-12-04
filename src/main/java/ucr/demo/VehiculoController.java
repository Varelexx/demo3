package ucr.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VehiculoController {

    @FXML private TableView<Vehiculo> tablaVehiculo;
    @FXML private TableColumn<Vehiculo, String> colPlaca;
    @FXML private TableColumn<Vehiculo, String> colMarca;
    @FXML private TableColumn<Vehiculo, String> colModelo;
    @FXML private TableColumn<Vehiculo, String> colConductor;

    @FXML private TextField txtPlaca;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtConductor;

    @FXML
    public void initialize() {
        colPlaca.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getPlaca()));
        colMarca.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getMarca()));
        colModelo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getModelo()));
        colConductor.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCedulaConductor()));

        cargarTabla();
    }

    private void cargarTabla() {
        ObservableList<Vehiculo> lista = VehiculoDAO.listar();
        tablaVehiculo.setItems(lista);
    }

    @FXML
    private void agregarVehiculo() {
        Vehiculo v = new Vehiculo(
                txtPlaca.getText(),
                txtMarca.getText(),
                txtModelo.getText(),
                txtConductor.getText()
        );

        VehiculoDAO.agregar(v);
        cargarTabla();
    }

    @FXML
    private void actualizarVehiculo() {
        Vehiculo v = new Vehiculo(
                txtPlaca.getText(),
                txtMarca.getText(),
                txtModelo.getText(),
                txtConductor.getText()
        );

        VehiculoDAO.actualizar(v);
        cargarTabla();
    }

    @FXML
    private void eliminarVehiculo() {
        Vehiculo seleccionado = tablaVehiculo.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            VehiculoDAO.eliminar(seleccionado.getPlaca());
            cargarTabla();
        }
    }
}
