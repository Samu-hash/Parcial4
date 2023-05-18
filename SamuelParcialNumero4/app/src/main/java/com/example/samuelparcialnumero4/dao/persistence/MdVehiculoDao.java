package com.example.samuelparcialnumero4.dao.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.samuelparcialnumero4.dao.conexion.ConexionHelpers;
import com.example.samuelparcialnumero4.dao.conexion.Utils;
import com.example.samuelparcialnumero4.dao.models.MdVehiculos;

import java.util.ArrayList;

public class MdVehiculoDao extends ConexionHelpers {

    private Context context;

    public MdVehiculoDao(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public MdVehiculos insertVehi(MdVehiculos model) {

        try {
            ConexionHelpers dbHelper = new ConexionHelpers(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("marca", model.getMarca());
            values.put("modelo", model.getModelo());
            int id = (int) db.insert(Utils.TABLE_NAME_VEHI, null, values);
            model.setIdVehiculo(id);
        } catch (Exception ex) {
            ex.toString();
            model.setIdVehiculo(0);
        }
        return model;
    }

    public boolean updateVehi(MdVehiculos c) {

        boolean isUpdate = false;

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Utils.TABLE_NAME_VEHI
                    + " SET marca = '" + c.getMarca()
                    + "', modelo = '" + c.getModelo()
                    + "' WHERE id_vehiculo='" + c.getIdVehiculo() + "' ");
            isUpdate = true;
        } catch (Exception ex) {
            ex.toString();
            isUpdate = false;
        } finally {
            db.close();
        }

        return isUpdate;
    }

    public boolean deleteVehi(int id) {

        boolean idDelete = false;

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Utils.TABLE_NAME_VEHI
                    + " WHERE id_vehiculo = '" + id + "'");
            idDelete = true;
        } catch (Exception ex) {
            ex.toString();
            idDelete = false;
        } finally {
            db.close();
        }
        return idDelete;
    }

    public Object viewVehi(int id, boolean isVehi) {

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String subQuery ="";
        if(id!=0)
            subQuery = " where id_vehiculo='"+id+"'";

        cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_NAME_VEHI
                + subQuery+" ORDER BY marca ASC", null);

        return this.recordToTable(cursor, isVehi);
    }

    private Object recordToTable(Cursor cursor, boolean isList){
        ArrayList<MdVehiculos> lista = new ArrayList<>();
        boolean isExists = false;
        if (cursor.moveToFirst()) {
            isExists =true;
            do {
                MdVehiculos model = new MdVehiculos();
                model.setIdVehiculo(cursor.getInt(0));
                model.setMarca(cursor.getString(1));
                model.setModelo(cursor.getString(2));
                lista.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista.size()>1 ? lista : (isExists) ? (isList) ? lista : lista.get(0): lista;
    }
}
