package pe.com.reus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kosalgeek.android.imagebase64encoder.ImageBase64;

import java.io.File;
import java.io.FileNotFoundException;

import pe.com.reus.Model.Foto;
import pe.com.reus.Model.Imagen;

public class GaleriaFotosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAPTURE_IMAGE_FRAGMENT_REQUEST_CODE = 100;

    private String APP_DIRECTORY = "Reus";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "Media";
    private String TEMPORAL_PICTURE_NAME1 = "imagen1.jpg";
    private String TEMPORAL_PICTURE_NAME2 = "imagen2.jpg";
    private String TEMPORAL_PICTURE_NAME3 = "imagen3.jpg";
    private String TEMPORAL_PICTURE_NAME4 = "imagen4.jpg";
    private String TEMPORAL_PICTURE_NAME5 = "imagen5.jpg";
    private String path = "";

    private String dir = "";
    private String dir1 = "";
    private String dir2 = "";
    private String dir3 = "";
    private String dir4 = "";
    private String dir5 = "";

    private ImageView imgCamara1;
    private ImageView imgCamara2;
    private ImageView imgCamara3;
    private ImageView imgCamara4;
    private ImageView imgCamara5;

    private Button btnCamara;
    private Button btnCamara1;
    private Button btnCamara2;
    private Button btnCamara3;
    private Button btnGrabar;

    private ProgressBar progressBar;

    private int fotosCantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_fotos);

        imgCamara1 = findViewById(R.id.imgCamara1);
        imgCamara2 = findViewById(R.id.imgCamara2);
        imgCamara3 = findViewById(R.id.imgCamara3);
        imgCamara4 = findViewById(R.id.imgCamara4);
        imgCamara5 = findViewById(R.id.imgCamara5);

        btnCamara = findViewById(R.id.btnCamara);
        btnGrabar = findViewById(R.id.btnGrabar);

        progressBar = findViewById(R.id.progress_bar);

        btnCamara.setOnClickListener(this);
        btnGrabar.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAPTURE_IMAGE_FRAGMENT_REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                    if (imgCamara1.getDrawable() != null) {
                        if (imgCamara2.getDrawable() != null) {
                            if (imgCamara3.getDrawable() != null) {
                                if (imgCamara4.getDrawable() != null) {
                                    if (imgCamara5.getDrawable() != null) {
                                        //Toast.makeText(getActivity(), "Llego al limite de Fotos permitido", Toast.LENGTH_LONG).show();
                                    } else {
                                        dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME5;
                                    }
                                } else {
                                    dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME4;
                                }
                            } else {
                                dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME3;
                            }
                        } else {
                            dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME2;
                        }
                    } else {
                        dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME1;
                    }

                    decodeBitmap(dir);
                }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCamara:
                abrirCamara();
                break;
            case R.id.btnGrabar:
                grabarAsyncTask();
                break;
        }
    }

    private void grabarAsyncTask() {

        new miAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); //PARALELO

    }

    private class miAsyncTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnGrabar.setEnabled(false);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Integer validar;
            validar = grabarFoto();

            return validar;
        }

        @Override
        protected void onPostExecute(Integer validar) {
            progressBar.setVisibility(View.INVISIBLE);
            btnGrabar.setEnabled(true);
            if (validar == 1) {
                Toast.makeText(GaleriaFotosActivity.this, "Fotos Conforme", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(GaleriaFotosActivity.this, "***Fotos No Conforme*** ", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void abrirCamara() {

        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdir();

        if (imgCamara1.getDrawable() != null) {
            if (imgCamara2.getDrawable() != null) {
                if (imgCamara3.getDrawable() != null) {
                    if (imgCamara4.getDrawable() != null) {
                        if (imgCamara5.getDrawable() != null) {
                            Toast.makeText(this, "Llego al limite de Fotos permitido", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator +
                                    TEMPORAL_PICTURE_NAME5;
                        }
                    } else {
                        path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator +
                                TEMPORAL_PICTURE_NAME4;
                    }
                } else {
                    path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator +
                            TEMPORAL_PICTURE_NAME3;
                }
            } else {
                path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator +
                        TEMPORAL_PICTURE_NAME2;
            }
        } else {
            path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator +
                    TEMPORAL_PICTURE_NAME1;
        }

        File newFile = new File(path);

        Uri uriFoto = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", newFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
        startActivityForResult(intent, CAPTURE_IMAGE_FRAGMENT_REQUEST_CODE);
    }

    private void decodeBitmap(String dir) {
        Matrix matrix = new Matrix();
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        if (imgCamara1.getDrawable() != null) {
            if (imgCamara2.getDrawable() != null) {
                if (imgCamara3.getDrawable() != null) {
                    if (imgCamara4.getDrawable() != null) {
                        if (imgCamara5.getDrawable() != null) {
                            //Toast.makeText(getActivity(), "Llego al limite de Fotos permitido", Toast.LENGTH_LONG).show();
                        } else {
                            dir5 = dir;
                            imgCamara5.setImageBitmap(bitmap);
                        }
                    } else {
                        dir4 = dir;
                        imgCamara4.setImageBitmap(bitmap);
                    }
                } else {
                    dir3 = dir;
                    imgCamara3.setImageBitmap(bitmap);
                }
            } else {
                dir2 = dir;
                imgCamara2.setImageBitmap(bitmap);
            }
        } else {
            dir1 = dir;
            imgCamara1.setImageBitmap(bitmap);
        }
    }

    private String imageViewToString(String ruta) {

        String encodedImage = "";
        try {
            encodedImage = ImageBase64
                    .with(this)
                    .requestSize(384, 384)
                    .encodeFile(ruta);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return encodedImage;

    }

    private int grabarFoto() {

        Foto foto = new Foto();
        fotosCantidad = 0;
        String mensaje = "";

        if (imgCamara1.getDrawable() != null) {
            fotosCantidad = fotosCantidad + 1;
        }

        if (imgCamara2.getDrawable() != null) {
            fotosCantidad = fotosCantidad + 1;
        }

        if (imgCamara3.getDrawable() != null) {
            fotosCantidad = fotosCantidad + 1;
        }

        if (imgCamara4.getDrawable() != null) {
            fotosCantidad = fotosCantidad + 1;
        }

        if (imgCamara5.getDrawable() != null) {
            fotosCantidad = fotosCantidad + 1;
        }

        if (fotosCantidad > 0) {
            foto.setFotoRuta1(imageViewToString(dir1));
            foto.setFotoRuta2(imageViewToString(dir2));
            foto.setFotoRuta3(imageViewToString(dir3));
            foto.setFotoRuta4(imageViewToString(dir4));
            foto.setFotoRuta5(imageViewToString(dir5));
            foto.setFotosCantidad(fotosCantidad);

            Imagen imagen = new Imagen();
            imagen.grabarFoto(foto);


        }

        return 1;

    }

}

