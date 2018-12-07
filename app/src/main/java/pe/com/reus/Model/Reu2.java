package pe.com.reus.Model;

public class Reu2 {

    private String nombre;
    private String fecha;
    private String direccion;

    public Reu2(String nombre, String fecha, String direccion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
