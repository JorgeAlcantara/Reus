package pe.com.reus.Model;

import com.google.gson.annotations.SerializedName;

public class Reu {

    @SerializedName("IdReu")
    private int IdReu;
    @SerializedName("IdActor")
    private int idActor;
    @SerializedName("Nombre")
    private String Nombre;
    @SerializedName("Latitud")
    private String Latitud;
    @SerializedName("Longitud")
    private String Longitud;
    @SerializedName("Direccion")
    private String Direccion;
    @SerializedName("Fecha")
    private String Fecha;
    @SerializedName("Estado")
    private int Estado;

    public int getIdReu() {
        return IdReu;
    }

    public void setIdReu(int idReu) {
        IdReu = idReu;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }
}
