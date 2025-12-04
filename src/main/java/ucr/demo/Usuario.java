package ucr.demo;

public class Usuario {

    private String cedula;
    private String nombre;
    private String apellidos;
    private String genero;
    private String telefono;
    private String fechaNacimiento;
    private String referido;

    public Usuario(String cedula, String nombre, String apellidos, String genero,
                   String telefono, String fechaNacimiento, String referido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.referido = referido;
    }

    // Getters y setters
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getGenero() { return genero; }
    public String getTelefono() { return telefono; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getReferido() { return referido; }

    public void setNombre(String x) { nombre = x; }
    public void setApellidos(String x) { apellidos = x; }
    public void setGenero(String x) { genero = x; }
    public void setTelefono(String x) { telefono = x; }
    public void setFechaNacimiento(String x) { fechaNacimiento = x; }
    public void setReferido(String x) { referido = x; }
}
