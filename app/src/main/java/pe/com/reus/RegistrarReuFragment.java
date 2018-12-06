package pe.com.reus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pe.com.reus.Model.Actor;
import pe.com.reus.Model.Reu;
import pe.com.reus.RestService.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarReuFragment extends Fragment implements View.OnClickListener {

    private final Calendar cFecha = Calendar.getInstance();

    private EditText edtNombre;
    private Spinner spiTipo;
    private EditText edtFecha;
    private EditText edtLugar;

    private Button btnGrabar;
    private Button btnCancelar;
    private Button btnMapa;

    private String urlReus = Globals.urlReus;


    private Retrofit retrofitReus;
    private RestService restServiceReus;

    public RegistrarReuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrar_reu, container, false);

        edtNombre = view.findViewById(R.id.edtNombre);
        spiTipo = view.findViewById(R.id.spnTipo);
        edtFecha = view.findViewById(R.id.edtFecha);
        edtLugar = view.findViewById(R.id.edtLugar);

        btnGrabar = view.findViewById(R.id.btnGrabar);
        btnCancelar = view.findViewById(R.id.btnCancelar);
        btnMapa = view.findViewById(R.id.btnMapa);

        edtFecha.setOnClickListener(this);
        btnGrabar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        btnMapa.setOnClickListener(this);

        fechaActual();
        cargarTipo(view);

        //Reu
        retrofitReus = new Retrofit.Builder()
                .baseUrl(urlReus)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restServiceReus = retrofitReus.create(RestService.class);
        //Reu

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtFecha:
                dateOnClickFecha(view);
                break;
            case R.id.btnGrabar:
                grabarReu();
                break;
            case R.id.btnMapa:
                openMapa();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 1) {
                String message = data.getStringExtra("direccion");
                edtLugar.setText(message);
            }
        }

    }

    DatePickerDialog.OnDateSetListener dateFecha = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cFecha.set(Calendar.YEAR, year);
            cFecha.set(Calendar.MONTH, monthOfYear);
            cFecha.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            fechaActual();
        }
    };

    private void fechaActual() {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        edtFecha.setText(sdf.format(cFecha.getTime()));
    }

    private void dateOnClickFecha(View view) {
        new DatePickerDialog(getActivity(), dateFecha,
                cFecha.get(Calendar.YEAR), cFecha.get(Calendar.MONTH), cFecha.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void grabarReu() {

        Reu reu = new Reu();
        //reu.setIdReu(0);
        reu.setIdActor(Globals.idActor);
        reu.setNombre(edtNombre.getText().toString());
        reu.setLatitud(Globals.latitud.toString());
        reu.setLongitud(Globals.longitud.toString());
        reu.setDireccion(Globals.direccion);
        reu.setFecha(edtFecha.getText().toString());
        reu.setEstado(1);

        restServiceReus.registrarReu(reu).enqueue(new Callback<Reu>() {
            @Override
            public void onResponse(Call<Reu> call, Response<Reu> response) {

                if (response.isSuccessful()) {
                    Log.i("RestService", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Reu> call, Throwable t) {
                Log.i("RestService", "onFailure: ", t);

            }
        });
    }

    private void openMapa() {

        Intent intent = new Intent(getActivity(), MapaActivity.class);
        startActivityForResult(intent, 1);
    }

    private void cargarTipo(View view) {
        final String[] tipos = new String[] { "Familiar",    "Amistad", "Profesional" };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),    android.R.layout.simple_spinner_item, tipos);
        adaptador.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        spiTipo.setAdapter(adaptador);
    }

}
