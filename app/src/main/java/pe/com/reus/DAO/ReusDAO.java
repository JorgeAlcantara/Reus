package pe.com.reus.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import pe.com.reus.DbHelper.DbHelper;
import pe.com.reus.Exception.DAOException;
import pe.com.reus.Model.Ubicacion;

public class ReusDAO {

    private DbHelper _dbHelper;

    public ReusDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String direccion, String lalitud, String longitud) throws DAOException {
        Log.i("ReusDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{direccion, lalitud, longitud};
            db.execSQL("INSERT INTO ubicacion(direccion, latitud,longitud) VALUES(?,?,?)", args);
            Log.i("ReusDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("ReusDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Ubicacion> buscar(String criterio) throws DAOException {
        Log.i("ReusDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Ubicacion> lista = new ArrayList<Ubicacion>();
        try {
            //Cursor c = db.rawQuery("select id, direccion, latitud, longitud from ubicacion where direccion like '%"+criterio+"%'", null);
            Cursor c = db.rawQuery("select id, direccion, latitud, longitud from ubicacion", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    String direccion = c.getString(c.getColumnIndex("direccion"));
                    String latitud = c.getString(c.getColumnIndex("latitud"));
                    String longitud = c.getString(c.getColumnIndex("longitud"));

                    Ubicacion modelo = new Ubicacion();
                    modelo.setId(id);
                    modelo.setDireccion(direccion);
                    modelo.setLatitud(latitud);
                    modelo.setLongitud(longitud);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ReusDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }

}
