package pe.com.reus.Model;

public class Gente {

    private String nombre;
    private String telefono;
    private String distrito;


    public Gente(String nombre, String telefono, String distrito) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.distrito = distrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}
