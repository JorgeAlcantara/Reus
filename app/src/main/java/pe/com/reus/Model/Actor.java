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
    @SerializedName("Email")
    private String Email;
    @SerializedName("Clave")
    private String Clave;

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}




