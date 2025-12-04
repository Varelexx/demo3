package ucr.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class PasajeroDAO {

    public static ObservableList<Pasajero> listar() {
        ObservableList<Pasajero> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Pasajero";

        try (Connection conn = ConexionSQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Pasajero(rs.getString("cedula_usuario")));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer pasajeros: " + e.getMessage());
        }
        return lista;
    }

    public static void agregar(Pasajero p) {
        String sql = "INSERT INTO Pasajero (cedula_usuario) VALUES (?)";

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCedulaUsuario());
            ps.executeUpdate();
            System.out.println("Pasajero agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar pasajero: " + e.getMessage());
        }
    }

    public static void actualizar(Pasajero p, String cedulaVieja) {
        String sql = "UPDATE Pasajero SET cedula_usuario=? WHERE cedula_usuario=?";

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCedulaUsuario());
            ps.setString(2, cedulaVieja);

            ps.executeUpdate();
            System.out.println("Pasajero actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar pasajero: " + e.getMessage());
        }
    }

    public static void eliminar(String cedula) {
        String sql = "DELETE FROM Pasajero WHERE cedula_usuario = ?";

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.executeUpdate();
            System.out.println("Pasajero eliminado.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar pasajero: " + e.getMessage());
        }
    }
}
