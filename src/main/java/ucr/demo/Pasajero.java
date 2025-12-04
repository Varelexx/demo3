package ucr.demo;

public class Pasajero {

    private String cedulaUsuario;

    public Pasajero(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }
}
