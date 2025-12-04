package ucr.demo;

public class Conductor {

    private String cedulaUsuario;
    private double gananciasDiarias;
    private int tiempoLaborado;

    public Conductor(String cedulaUsuario, double gananciasDiarias, int tiempoLaborado) {
        this.cedulaUsuario = cedulaUsuario;
        this.gananciasDiarias = gananciasDiarias;
        this.tiempoLaborado = tiempoLaborado;
    }

    // Getters y Setters
    public String getCedulaUsuario() { return cedulaUsuario; }
    public void setCedulaUsuario(String cedulaUsuario) { this.cedulaUsuario = cedulaUsuario; }

    public double getGananciasDiarias() { return gananciasDiarias; }
    public void setGananciasDiarias(double gananciasDiarias) { this.gananciasDiarias = gananciasDiarias; }

    public int getTiempoLaborado() { return tiempoLaborado; }
    public void setTiempoLaborado(int tiempoLaborado) { this.tiempoLaborado = tiempoLaborado; }
}
