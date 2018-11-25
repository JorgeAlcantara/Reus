package pe.com.reus.Model;

import com.google.gson.annotations.SerializedName;

public class Reniec {

    @SerializedName("NumeroDocumento")
    private String numeroDocumento;
    @SerializedName("Apellidos")
    private String apellidos;
    @SerializedName("Nombres")
    private String nombres;
    @SerializedName("Sexo")
    private int sexo;

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
}
