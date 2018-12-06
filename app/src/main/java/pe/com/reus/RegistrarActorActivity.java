package pe.com.reus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.com.reus.Model.Actor;
import pe.com.reus.RestService.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarActorActivity extends AppCompatActivity implements View.OnClickListener {

    private String urlReus = Globals.urlReus;

    private Retrofit retrofitActor;
    private RestService restServiceActor;

    private EditText edtCorreo;
    private EditText edtContraseña;
    private Button btnGrabar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_actor);

        edtCorreo = findViewById(R.id.edtCorreo);
        edtContraseña = findViewById(R.id.edtContraseña);
        btnGrabar = findViewById(R.id.btnGrabar);

        btnGrabar.setOnClickListener(this);

        //Actor
        retrofitActor = new Retrofit.Builder()
                .baseUrl(urlReus)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restServiceActor = retrofitActor.create(RestService.class);
        //Actor

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGrabar:
                grabarActor();
                finish();
                break;

        }
    }

    private void grabarActor() {

        Actor actor = new Actor();
        //actor.setIdActor(0);
        actor.setEmail(edtCorreo.getText().toString());
        actor.setClave(edtContraseña.getText().toString());

        restServiceActor.registrarActor(actor).enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {

                if (response.isSuccessful()) {
                    Log.e("RestService Ok", response.body().toString());
                    Toast.makeText(RegistrarActorActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show();

                    Actor actorData = response.body();
                    Globals.idActor = actorData.getIdActor();
                    Globals.correo = actorData.getEmail();
                    Globals.contraseña = actorData.getClave();

                }
            }
            @Override
            public void onFailure(Call<Actor> call, Throwable t) {
                Log.e("RestService Error", "onFailure: ", t);
                Toast.makeText(RegistrarActorActivity.this, "Usuario no registrado", Toast.LENGTH_LONG).show();
            }
        });
    }

}
