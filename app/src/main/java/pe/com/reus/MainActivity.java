package pe.com.reus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pe.com.reus.Model.Actor;
import pe.com.reus.RestService.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtCorreo;
    private EditText edtContraseña;
    private Button btnAceptar;
    private TextView txtCrear;

    private String url = Globals.urlLocal;

    private Retrofit retrofit;
    private RestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCorreo = findViewById(R.id.edtCorreo);
        edtContraseña = findViewById(R.id.edtContraseña);
        btnAceptar = findViewById(R.id.btnAceptar);
        txtCrear = findViewById(R.id.btnRegistrar);

        btnAceptar.setOnClickListener(this);
        txtCrear.setOnClickListener(this);

        //
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restService = retrofit.create(RestService.class);
        //

    }

    private void openPrincipal() {
        Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistrar:
                openRegistrarUsuario();
                break;
            case R.id.btnAceptar:
                loguear(edtCorreo.getText().toString(), edtContraseña.getText().toString());
                break;
        }
    }

    private void openRegistrarUsuario() {
        Intent intent = new Intent(this, RegistrarActorActivity.class);
        startActivity(intent);
    }

    /*
    private void loguear(String correo, String contraseña) {
        if (correo.equals(Globals.correo)) {
            if (contraseña.equals(Globals.contraseña)) {
                openPrincipal();
            } else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Correo incorrecto", Toast.LENGTH_LONG).show();
        }

    }
    */
    private void loguear(String nombre, String password) {
        restService.loguear(nombre, password).enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {

                Actor actor = response.body();

                if (actor == null) {

                    Toast.makeText(getApplication(), response.message(), Toast.LENGTH_LONG).show();

                } else {

                    Globals.idActor = actor.getIdActor();
                    Globals.correo = actor.getEmail();
                    Globals.contraseña = actor.getPassword();
                    openPrincipal();

                }

            }

            @Override
            public void onFailure(Call<Actor> call, Throwable t) {
                //Log.e("RestService", "onFailure: ", t);
                Toast.makeText(getApplication(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
