package pe.com.reus.Model;

import com.google.gson.annotations.SerializedName;

public class Reu {

    @SerializedName("idReu")
    private int IdReu;
    @SerializedName("nombre")
    private String Nombre;
    @SerializedName("tipo")
    private String Tipo;
    @SerializedName("fecha")
    private String Fecha;
    @SerializedName("latitud")
    private String Latitud;
    @SerializedName("longitud")
    private String Longitud;
    @SerializedName("direccion")
    private String Direccion;
    @SerializedName("estado")
    private int Estado;
    @SerializedName("actor")
    private Actor actor;

    public int getIdReu() {
        return IdReu;
    }

    public void setIdReu(int idReu) {
        IdReu = idReu;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
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

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

}
