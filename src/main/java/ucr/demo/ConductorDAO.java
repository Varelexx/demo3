package ucr.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class ConductorDAO {

    public static ObservableList<Conductor> listar() {
        ObservableList<Conductor> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Conductor";

        try (Connection conn = ConexionSQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Conductor(
                        rs.getString("cedula_usuario"),
                        rs.getDouble("ganancias_diarias"),
                        rs.getInt("tiempo_laborado")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return lista;
    }

    public static void agregar(Conductor c) {
        String sql = """
            INSERT INTO Conductor (cedula_usuario, ganancias_diarias, tiempo_laborado)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCedulaUsuario());
            ps.setDouble(2, c.getGananciasDiarias());
            ps.setInt(3, c.getTiempoLaborado());

            ps.executeUpdate();
            System.out.println("Conductor agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
    }

    public static void eliminar(String cedula) {
        String sql = "DELETE FROM Conductor WHERE cedula_usuario=?";

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public static void actualizar(Conductor c) {
        String sql = """
            UPDATE Conductor
            SET ganancias_diarias=?, tiempo_laborado=?
            WHERE cedula_usuario=?
        """;

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, c.getGananciasDiarias());
            ps.setInt(2, c.getTiempoLaborado());
            ps.setString(3, c.getCedulaUsuario());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
}
