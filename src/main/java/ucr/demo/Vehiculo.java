package ucr.demo;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cedulaConductor;

    public Vehiculo(String placa, String marca, String modelo, String cedulaConductor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cedulaConductor = cedulaConductor;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getCedulaConductor() { return cedulaConductor; }
    public void setCedulaConductor(String cedulaConductor) { this.cedulaConductor = cedulaConductor; }
}
