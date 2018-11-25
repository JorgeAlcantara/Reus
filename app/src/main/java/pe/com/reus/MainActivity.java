package pe.com.reus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAceptar;
    private TextView txtCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = findViewById(R.id.btnAceptar);
        txtCrear = findViewById(R.id.btnRegistrar);

        btnAceptar.setOnClickListener(this);
        txtCrear.setOnClickListener(this);

    }

    private void openPrincipal() {
        Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    private void openRegistrarUsuario() {
        Intent intent = new Intent(getBaseContext(), RegistrarActorActivity.class);
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
                //loguear(edtNombre.getText().toString(), edtPassword.getText().toString());
                openPrincipal();
                break;
        }
    }
}
