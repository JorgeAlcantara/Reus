package pe.com.reus.Model;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Actor {

    @SerializedName("IdActor")
    private int IdActor;
    @SerializedName("Nombres")
    private String Nombres;
    @SerializedName("Apellidos")
    private String Apellidos;
    @SerializedName("Sexo")
    private int Sexo;
    @SerializedName("Telefono")
    private String Telefono;
    @SerializedName("FechaNacimiento")
    private Date FechaNacimiento;
    @SerializedName("Direccion")
    private String Direccion;
    @SerializedName("Estado")
    private int Estado;

    public int getIdActor() {
        return IdActor;
    }

    public void setIdActor(int idActor) {
        IdActor = idActor;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public int getSexo() {
        return Sexo;
    }

    public void setSexo(int sexo) {
        Sexo = sexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
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
}
