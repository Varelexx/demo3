package ucr.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioRepository {

    private static ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    public static ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void agregar(Usuario u) {
        usuarios.add(u);
    }

    public static void eliminar(Usuario u) {
        usuarios.remove(u);
    }
}
