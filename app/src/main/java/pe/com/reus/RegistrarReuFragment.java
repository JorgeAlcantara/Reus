package pe.com.reus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
    private EditText edtFecha;
    private EditText edtLugar;
    private Button btnGrabar;
    private Button btnMapa;

    private String mensaje;

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

        edtFecha = view.findViewById(R.id.edtFecha);
        edtLugar = view.findViewById(R.id.edtLugar);

        btnGrabar = view.findViewById(R.id.btnGrabar);
        btnMapa = view.findViewById(R.id.btnMapa);

        edtFecha.setOnClickListener(this);
        btnGrabar.setOnClickListener(this);
        btnMapa.setOnClickListener(this);

        fechaActual();

        //Reniec
        retrofitReus = new Retrofit.Builder()
                .baseUrl(urlReus)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restServiceReus = retrofitReus.create(RestService.class);
        //Reniec

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

    DatePickerDialog.OnDateSetListener dateFecha = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cFecha.set(Calendar.YEAR, year);
            cFecha.set(Calendar.MONTH, monthOfYear);
            cFecha.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            fechaActual();
        }
    };

    public void fechaActual() {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        edtFecha.setText(sdf.format(cFecha.getTime()));
    }

    public void dateOnClickFecha(View view) {
        new DatePickerDialog(getActivity(), dateFecha,
                cFecha.get(Calendar.YEAR), cFecha.get(Calendar.MONTH), cFecha.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void grabarReu() {

        Actor actor = new Actor();
        actor.setIdActor(4);


        Reu reu = new Reu();
        reu.setIdEvento(1);
        reu.setIdActor(4);
        reu.setDescripcion(edtLugar.getText().toString());
        reu.setLatitud(Globals.latitud);
        reu.setLongitud(Globals.longitud);
        reu.setFecha(edtFecha.getText().toString());
        reu.setHora("");
        reu.setEstado(1);

        restServiceReus.registrarReu(reu).enqueue(new Callback<Reu>() {
            @Override
            public void onResponse(Call<Reu> call, Response<Reu> response) {

                if (response.isSuccessful()) {
                    mensaje = response.body().toString();
                    //txtMensaje.setText("Registro OK - " + mensaje.toString());
                    Log.i("RestService - OK", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Reu> call, Throwable t) {
                Log.i("RestService - Error ", "onFailure: ", t);
                //txtMensaje.setText(t.toString());
            }
        });
    }

    public void openMapa() {

        Intent intent = new Intent(getActivity(), MapaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            String message = data.getStringExtra("direccion");
            edtLugar.setText(message);
        }

    }

}
