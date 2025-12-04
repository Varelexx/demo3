package ucr.demo;

import java.sql.*;
import javafx.collections.*;

public class ViajeDAO {

    public static void agregar(Viaje v) {
        String sql = """
            INSERT INTO Viaje (id_viaje, fecha_viaje, costo_viaje, hora_inicio, hora_final,
                               punto_origen, cedula_pasajero, cedula_conductor)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getId());
            ps.setString(2, v.getFecha());
            ps.setString(3, v.getCosto());
            ps.setString(4, v.getHoraInicio());
            ps.setString(5, v.getHoraFinal());
            ps.setString(6, v.getOrigen());
            ps.setString(7, v.getCedulaPasajero());
            ps.setString(8, v.getCedulaConductor());

            ps.executeUpdate();
            System.out.println("Viaje agregado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al agregar viaje: " + e.getMessage());
        }
    }

    public static ObservableList<String> listarPasajeros() {
        ObservableList<String> lista = FXCollections.observableArrayList();

        String sql = "SELECT cedula_usuario FROM Pasajero";

        try (Connection conn = ConexionSQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) lista.add(rs.getString(1));

        } catch (SQLException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }

    public static String obtenerConductorRandom() {
        String sql = """
            SELECT TOP 1 cedula_usuario
            FROM Conductor
            ORDER BY NEWID()
        """;

        try (Connection conn = ConexionSQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getString(1);

        } catch (SQLException e) {
            System.out.println("Error al obtener conductor random: " + e.getMessage());
        }
        return null;
    }


}
