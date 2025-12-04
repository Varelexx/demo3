package ucr.demo;

public class Viaje {

    private String id;
    private String fecha;
    private String costo;
    private String horaInicio;
    private String horaFinal;
    private String origen;
    private String destino;
    private String cedulaPasajero;
    private String cedulaConductor;

    public Viaje(String id, String fecha, String costo, String horaInicio,
                 String horaFinal, String origen, String destino,
                 String cedulaPasajero, String cedulaConductor) {

        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.origen = origen;
        this.destino = destino;
        this.cedulaPasajero = cedulaPasajero;
        this.cedulaConductor = cedulaConductor;
    }

    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public String getCosto() { return costo; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFinal() { return horaFinal; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public String getCedulaPasajero() { return cedulaPasajero; }
    public String getCedulaConductor() { return cedulaConductor; }
}
