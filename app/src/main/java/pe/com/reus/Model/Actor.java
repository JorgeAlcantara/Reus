package pe.com.reus.Model;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Actor {

    @SerializedName("idactor")
    private int IdActor;
    @SerializedName("nombre")
    private String Nombre;
    @SerializedName("apellido")
    private String Apellido;
    @SerializedName("sexo")
    private int Sexo;
    @SerializedName("telefono")
    private String Telefono;
    @SerializedName("email")
    private String Email;
    @SerializedName("password")
    private String Password;
    @SerializedName("estado")
    private String Estado;

    public int getIdActor() {
        return IdActor;
    }

    public void setIdActor(int idActor) {
        IdActor = idActor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}




