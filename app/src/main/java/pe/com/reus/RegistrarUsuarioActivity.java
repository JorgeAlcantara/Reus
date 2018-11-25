package pe.com.reus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import pe.com.reus.RestService.RestService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    private String urlReniec = Global.urlReniec;

    private Retrofit retrofitUsuario;
    private RestService restServiceUsuario;

    private EditText edtCorreo;
    private EditText edtContraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        edtCorreo = findViewById(R.id.edtCorreo);
        edtContraseña = findViewById(R.id.edtContraseña);


    }

    private void invocarServicioReset() {
        //Usuario
        retrofitUsuario = new Retrofit.Builder()
                .baseUrl(urlReniec)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restServiceUsuario = retrofitUsuario.create(RestService.class);
        //Usuario
    }

}
