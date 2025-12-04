package ucr.demo;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class FormViaje {

    public void mostrar() {

        Stage stage = new Stage();
        stage.setTitle("Registrar Viaje");

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Barrios de Guápiles quemados
        String[] barrios = {
                "La Rita", "Guápiles Centro", "Santa Clara", "El Ceibo",
                "Roxana", "Jiménez", "San Martín", "Los Lirios"
        };

        ComboBox<String> cbOrigen = new ComboBox<>();
        cbOrigen.getItems().addAll(barrios);

        ComboBox<String> cbDestino = new ComboBox<>();
        cbDestino.getItems().addAll(barrios);

        // Pasajeros desde DB
        ComboBox<String> cbPasajero = new ComboBox<>();
        cbPasajero.getItems().addAll(ViajeDAO.listarPasajeros());

        Label lblCosto = new Label("Costo: ₡0");

        // Tarifas quemadas
        HashMap<String, Integer> tarifas = new HashMap<>();
        tarifas.put("Guápiles Centro-La Rita", 3000);
        tarifas.put("La Rita-Guápiles Centro", 3000);
        tarifas.put("Guápiles Centro-Roxana", 3500);
        tarifas.put("Roxana-Guápiles Centro", 3500);

        tarifas.put("San Martín-La Rita", 2500);
        tarifas.put("La Rita-San Martín", 2500);

        // Calcular tarifa cuando cambien origen o destino
        Runnable calcularTarifa = () -> {
            String o = cbOrigen.getValue();
            String d = cbDestino.getValue();
            if (o != null && d != null && !o.equals(d)) {
                String key = o + "-" + d;
                lblCosto.setText("Costo: ₡" + tarifas.getOrDefault(key, 4000));
            }
        };

        cbOrigen.setOnAction(e -> calcularTarifa.run());
        cbDestino.setOnAction(e -> calcularTarifa.run());

        Button btnGuardar = new Button("Guardar viaje");

        btnGuardar.setOnAction(e -> {

            String id = "V" + System.currentTimeMillis() % 100000;

            String origen = cbOrigen.getValue();
            String destino = cbDestino.getValue();
            String pasajero = cbPasajero.getValue();
            String conductor = ViajeDAO.obtenerConductorRandom();

            if (origen == null || destino == null || pasajero == null) {
                System.out.println("Complete todos los campos");
                return;
            }

            String key = origen + "-" + destino;
            int costo = tarifas.getOrDefault(key, 4000);

            Viaje v = new Viaje(
                    id,
                    LocalDate.now().toString(),
                    String.valueOf(costo),
                    LocalTime.now().toString(),
                    LocalTime.now().plusMinutes(15).toString(),
                    origen,
                    destino,
                    pasajero,
                    conductor
            );

            ViajeDAO.agregar(v);
        });

        root.getChildren().addAll(
                new Label("Origen"), cbOrigen,
                new Label("Destino"), cbDestino,
                lblCosto,
                new Label("Pasajero"), cbPasajero,
                btnGuardar
        );

        stage.setScene(new Scene(root, 350, 400));
        stage.show();
    }
}
