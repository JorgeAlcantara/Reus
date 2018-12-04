package pe.com.reus.Model;

import com.google.gson.annotations.SerializedName;

public class Reu {

    @SerializedName("IdEvento")
    private int IdEvento;
    @SerializedName("IdActor")
    private int idActor;
    @SerializedName("Descripcion")
    private String Descripcion;
    @SerializedName("Fecha")
    private String Fecha;
    @SerializedName("Hora")
    private String Hora;
    @SerializedName("Latitud")
    private double Latitud;
    @SerializedName("Longitud")
    private double Longitud;
    @SerializedName("Estado")
    private int Estado;
    @SerializedName("Actor")
    private Actor actor;

    public int getIdEvento() {
        return IdEvento;
    }

    public void setIdEvento(int idEvento) {
        IdEvento = idEvento;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
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
