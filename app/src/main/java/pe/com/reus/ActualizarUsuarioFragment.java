package pe.com.reus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import pe.com.reus.Model.Reniec;
import pe.com.reus.RestService.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarUsuarioFragment extends Fragment {

    private String urlReniec = Globals.urlReniec;

    private Retrofit retrofitReniec;
    private RestService restServiceReniec;

    private EditText edtCorreo;
    private EditText edtContraseña;
    private EditText edtDni;
    private EditText edtNombre;
    private EditText edtApellido;
    private RadioGroup rgSexo;
    private RadioButton rbMasculino, rbFemenino;
    private EditText edtTelefono;

    public ActualizarUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar_usuario, container, false);

        edtCorreo = view.findViewById(R.id.edtCorreo);
        edtContraseña = view.findViewById(R.id.edtContraseña);
        edtDni = view.findViewById(R.id.edtDni);
        edtNombre = view.findViewById(R.id.edtNombre);
        edtApellido = view.findViewById(R.id.edtApellido);
        rgSexo = view.findViewById(R.id.rgSexo);
        rbMasculino = view.findViewById(R.id.rbMasculino);
        rbFemenino = view.findViewById(R.id.rbFemenino);
        edtTelefono = view.findViewById(R.id.edtTelefono);

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

        return view;
    }

    private void buscarDni(Long dni) {
        restServiceReniec.buscarDni(dni).enqueue(new Callback<Reniec>() {

            private Reniec reniec;

            @Override
            public void onResponse(Call<Reniec> call, Response<Reniec> response) {

                reniec = response.body();

                if (reniec == null) {
                    Toast.makeText(getActivity(), "Error con Servicios de la RENIEC", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getActivity(), "*** DNI no encontrado ***", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void limpiarCampos() {
        edtNombre.setText("");
        edtApellido.setText("");
        rgSexo.clearCheck();
    }

}
