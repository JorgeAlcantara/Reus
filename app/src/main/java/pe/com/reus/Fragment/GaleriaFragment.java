package pe.com.reus.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import pe.com.reu.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GaleriaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GaleriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GaleriaFragment extends Fragment implements View.OnClickListener {

    private static final int CAPTURE_IMAGE_FRAGMENT_REQUEST_CODE = 100;

    private String APP_DIRECTORY = "Sistam";
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

    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;

    private ImageView imgCamara1;
    private ImageView imgCamara2;
    private ImageView imgCamara3;
    private ImageView imgCamara4;
    private ImageView imgCamara5;

    private Button btnCamara;
    private Button btnCamara1;
    private Button btnCamara2;
    private Button btnCamara3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriaFragment newInstance(String param1, String param2) {
        GaleriaFragment fragment = new GaleriaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        btnCamara = view.findViewById(R.id.btnCamara);

        imgCamara1 = view.findViewById(R.id.imgCamara1);
        imgCamara2 = view.findViewById(R.id.imgCamara2);
        imgCamara3 = view.findViewById(R.id.imgCamara3);
        imgCamara4 = view.findViewById(R.id.imgCamara4);
        imgCamara5 = view.findViewById(R.id.imgCamara5);

        btnCamara.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCamara:
                abrirCamara();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

    private void abrirCamara() {

        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdir();

        if (imgCamara1.getDrawable() != null) {
            if (imgCamara2.getDrawable() != null) {
                if (imgCamara3.getDrawable() != null) {
                    if (imgCamara4.getDrawable() != null) {
                        if (imgCamara5.getDrawable() != null) {
                            Toast.makeText(getActivity(), "Llego al limite de Fotos permitido", Toast.LENGTH_LONG).show();
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

        Uri uriFoto = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", newFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
        startActivityForResult(intent, CAPTURE_IMAGE_FRAGMENT_REQUEST_CODE);
    }

    private void decodeBitmap(String dir) {
        Matrix matrix = new Matrix();
        //matrix.postRotate(90);

//        int newWidth = 50;
//        int newHeight = 50;

//        BitmapFactory.Options options;
//
//        options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bitmap;
        //bitmap = BitmapFactory.decodeFile(dir,options);
        bitmap = BitmapFactory.decodeFile(dir);

        // calculamos el escalado de la imagen destino
//        float scaleWidth = ((float) newWidth) / bitmap.getWidth();
//        float scaleHeight = ((float) newHeight) / bitmap.getHeight();

        //matrix.postScale(scaleWidth, scaleHeight);

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

}
