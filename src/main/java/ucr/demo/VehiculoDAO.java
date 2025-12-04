package ucr.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class VehiculoDAO {

    public static ObservableList<Vehiculo> listar() {
        ObservableList<Vehiculo> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Vehiculo";

        try (Connection conn = ConexionSQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Vehiculo(
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cedula_conductor")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }

    public static void agregar(Vehiculo v) {
        String sql = """
            INSERT INTO Vehiculo (placa, marca, modelo, cedula_conductor)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setString(4, v.getCedulaConductor());

            ps.executeUpdate();
            System.out.println("Vehículo agregado.");

        } catch (SQLException e) {
            System.out.println("Error al agregar vehículo: " + e.getMessage());
        }
    }

    public static void eliminar(String placa) {
        String sql = "DELETE FROM Vehiculo WHERE placa = ?";

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, placa);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar vehículo: " + e.getMessage());
        }
    }

    public static void actualizar(Vehiculo v) {
        String sql = """
            UPDATE Vehiculo
            SET marca=?, modelo=?, cedula_conductor=?
            WHERE placa=?
        """;

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getMarca());
            ps.setString(2, v.getModelo());
            ps.setString(3, v.getCedulaConductor());
            ps.setString(4, v.getPlaca());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar vehículo: " + e.getMessage());
        }
    }
}
