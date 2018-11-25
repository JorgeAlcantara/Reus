package pe.com.reus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import pe.com.reus.Model.Reniec;
import pe.com.reus.RestService.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarActorActivity extends AppCompatActivity {

    private String urlReniec = Global.urlReniec;

    private Retrofit retrofitReniec;
    private RestService restServiceReniec;

    private EditText edtDni;
    private EditText edtNombre;
    private EditText edtApellido;
    private RadioGroup rgSexo;
    private RadioButton rbMasculino, rbFemenino;
    private EditText edtTelefono;
    private EditText edtCorreo;
    private TextView txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_actor);

        edtDni = findViewById(R.id.edtDni);
        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        rgSexo = findViewById(R.id.rgSexo);
        rbMasculino = findViewById(R.id.rbMasculino);
        rbFemenino = findViewById(R.id.rbFemenino);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtCorreo = findViewById(R.id.edtCorreo);
        txtMensaje = findViewById(R.id.txtMensaje);

        //Reniec
        retrofitReniec = new Retrofit.Builder()
                .baseUrl(urlReniec)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restServiceReniec = retrofitReniec.create(RestService.class);
        //Reniec

        edtDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 8) {
                    buscarDni(Long.valueOf(edtDni.getText().toString()));
                } else {
                    limpiarCampos();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

    }

    private void buscarDni(Long dni) {
        restServiceReniec.buscarDni(dni).enqueue(new Callback<Reniec>() {

            private Reniec reniec;

            @Override
            public void onResponse(Call<Reniec> call, Response<Reniec> response) {

                reniec = response.body();

                if (reniec == null) {
                    Toast.makeText(getBaseContext(), "DNI no encontrado", Toast.LENGTH_LONG).show();
                } else {
                    int sexo;

                    edtNombre.setText(reniec.getNombres());
                    edtApellido.setText(reniec.getApellidos());
                    sexo = reniec.getSexo();

                    if (sexo == 1) {
                        rbMasculino.setChecked(true);
                    }

                    if (sexo == 2) {
                        rbFemenino.setChecked(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<Reniec> call, Throwable t) {
                //Log.e("RestService", "onFailure: ", t);
                Toast.makeText(getBaseContext(), "*** DNI no encontrado ***", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void limpiarCampos() {
        edtNombre.setText("");
        edtApellido.setText("");
        rgSexo.clearCheck();
    }

}
