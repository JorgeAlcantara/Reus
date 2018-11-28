package pe.com.reus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrarReuFragment extends Fragment implements View.OnClickListener {

    private final Calendar cFecha = Calendar.getInstance();
    private EditText edtFecha;
    private Button btnGrabar;
    private Button btnMapa;

    public RegistrarReuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_registrar_reu, container, false);

        edtFecha = view.findViewById(R.id.edtFecha);
        btnGrabar = view.findViewById(R.id.btnGrabar);
        btnMapa = view.findViewById(R.id.btnMapa);

        edtFecha.setOnClickListener(this);
        btnGrabar.setOnClickListener(this);
        btnMapa.setOnClickListener(this);

        fechaActual();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtFecha:
                dateOnClickFecha(view);
                break;
            case R.id.btnGrabar:
                grabar();
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

    public void grabar() {

    }

    public void openMapa() {
        Intent intent = new Intent(getActivity(), MapaActivity.class);
        startActivity(intent);
    }

}
