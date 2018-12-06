package pe.com.reus;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pe.com.reus.DAO.ReusDAO;
import pe.com.reus.Exception.DAOException;
import pe.com.reus.Model.Ubicacion;


public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    AutoCompleteTextView autDireccion;
    ArrayList<Ubicacion> resultados;

    private GoogleMap mMap;
    private Marker marcador;
    private double lat = 0.0;
    private double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        autDireccion = findViewById(R.id.autDireccion);

        buscarUbicacion();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,resultados);

        autDireccion.setAdapter(adapter);
        autDireccion.setThreshold(1);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        miUbicacion();

        /*
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-12.085937, -76.976017);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Jockey Plaza").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        */

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .anchor(0.0f, 1.0f)
                        .position(latLng));

                lat = latLng.latitude;
                lng = latLng.longitude;

                confirmarUbicacion();
                // Toast.makeText(getApplicationContext(), "position actual : " + String.valueOf(ii), Toast.LENGTH_LONG).show();
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                double ii;
                double ff;
                ii = marker.getPosition().latitude;
                ff = marker.getPosition().longitude;
                Toast.makeText(getApplicationContext(), "position actual : " + String.valueOf(ii), Toast.LENGTH_LONG).show();

                return false;
            }
        });

    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion Actual")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locListener);
    }

    private void confirmarUbicacion() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Esta seguro de confirmar ubicacion");
        alertDialogBuilder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Toast.makeText(MapaActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                        obtenerDireccion();
                        grabarUbicacionSQL();
                        cerrarMapa();
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void obtenerDireccion() {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String city = "";
        String state;
        String zip;
        String country;
        String direccion = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            zip = addresses.get(0).getPostalCode();
            country = addresses.get(0).getCountryName();
            direccion = addresses.get(0).getAddressLine(0);

        } catch (IOException e) {
            Log.e("MapaActivity", e.getMessage());
        }

        Toast.makeText(MapaActivity.this, "Direccion obtenida : " + direccion, Toast.LENGTH_LONG).show();
        Globals.direccion = direccion;
        Globals.latitud = String.valueOf(lat);
        Globals.longitud = String.valueOf(lng);

    }

    private void grabarUbicacionSQL() {

        String direccion = Globals.direccion;
        String latitud = Globals.latitud;
        String longitud = Globals.longitud;

        ReusDAO dao = new ReusDAO(getBaseContext());
        try {
            dao.insertar(direccion, latitud, longitud);

            Toast toast = Toast.makeText(getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        } catch (DAOException e) {
            Log.e("MapaActivity", "=> " + e.getMessage());
        }
    }

    private void cerrarMapa() {
        Intent intent = new Intent();
        intent.putExtra("direccion", Globals.direccion);
        setResult(1, intent);
        finish();
    }

    public void buscarUbicacion() {

        ReusDAO dao = new ReusDAO(getBaseContext());
        try {
            resultados = dao.buscar(autDireccion.toString());

            /*
            String[] encontrados = new String[resultados.size()];
            int i = 0;
            for (Ubicacion gm : resultados){
                encontrados[i++] = gm.getDireccion();
            }


            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this.getBaseContext(),
                    android.R.layout.simple_expandable_list_item_1,
                    encontrados);

            ListView listaResultados = (ListView)findViewById(R.id.listaResultados);
            listaResultados.setAdapter(adaptador);
            */


        } catch (DAOException e) {
            Log.e("GeneroMusicalBuscarAc", "====> " + e.getMessage());
        }
    }

}
