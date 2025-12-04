package ucr.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class UsuarioDAO {

    public static ObservableList<Usuario> listar() {
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = ConexionSQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("genero"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("cedula_referido")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return lista;
    }

    public static void agregar(Usuario u) {
        Connection conn = ConexionSQL.getConnection();
        if (conn == null) return;

        String sql = "INSERT INTO Usuario (cedula, nombre, apellidos, genero, telefono, fecha_nacimiento, cedula_referido) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, u.getCedula());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellidos());
            ps.setString(4, u.getGenero());
            ps.setString(5, u.getTelefono());

            // fecha
            if (u.getFechaNacimiento() != null && !u.getFechaNacimiento().isEmpty()) {
                ps.setDate(6, java.sql.Date.valueOf(u.getFechaNacimiento()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            // referido
            if (u.getReferido() == null || u.getReferido().isEmpty()) {
                ps.setNull(7, java.sql.Types.VARCHAR);
            } else {
                ps.setString(7, u.getReferido());
            }

            ps.executeUpdate();
            System.out.println("Usuario agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
    }


    public static void eliminar(String cedula) {
        String sql = "DELETE FROM Usuario WHERE cedula=?";

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public static void actualizar(Usuario u) {
        String sql = """
            UPDATE Usuario 
            SET nombre=?, apellidos=?, genero=?, telefono=?, fecha_nacimiento=?, cedula_referido=?
            WHERE cedula=?
        """;

        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getGenero());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getFechaNacimiento());
            ps.setString(6, u.getReferido());
            ps.setString(7, u.getCedula());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
}
