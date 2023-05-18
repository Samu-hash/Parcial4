package com.example.samuelparcialnumero4.dao.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.samuelparcialnumero4.dao.conexion.ConexionHelpers;
import com.example.samuelparcialnumero4.dao.conexion.Utils;
import com.example.samuelparcialnumero4.dao.models.MdClienteVehiculo;

import java.util.ArrayList;

public class MdClienteVehiculoDao extends ConexionHelpers {

    private Context context;

    public MdClienteVehiculoDao(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean insertCliVehi(MdClienteVehiculo model) {

        boolean isCreate = false;
        try {
            ConexionHelpers dbHelper = new ConexionHelpers(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id_cliente", model.getIdCliente());
            values.put("id_vehiculo", model.getIdVehiculo());
            values.put("matricula", model.getMatricula());
            values.put("kilometro", model.getKilometros());
            db.insert(Utils.TABLE_NAME_CLI_VEHI, null, values);
            isCreate =true;
        } catch (Exception ex) {
            ex.toString();
        }
        return isCreate;
    }

    public boolean updateCliVehi(MdClienteVehiculo c) {

        boolean isUpdate = false;

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Utils.TABLE_NAME_CLI_VEHI
                    + " SET matricula = '" + c.getMatricula()
                    + "', kilometro = '" + c.getKilometros()
                    + "' WHERE id_cliente='" + c.getIdCliente()
                    + "' and id_vehiculo='"+c.getIdVehiculo()+"'");
            isUpdate = true;
        } catch (Exception ex) {
            ex.toString();
            isUpdate = false;
        } finally {
            db.close();
        }

        return isUpdate;
    }

    public boolean deleteCliVehi(int id, int id2) {

        boolean idDelete = false;

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Utils.TABLE_NAME_CLI_VEHI
                    + " WHERE id_cliente = '" + id + "' and id_vehiculo='"+id2+"'");
            idDelete = true;
        } catch (Exception ex) {
            ex.toString();
            idDelete = false;
        } finally {
            db.close();
        }
        return idDelete;
    }

    public Object viewCliVehi(int id, int id2, boolean isList) {

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String subQuery ="";
        if(id!=0 && id2!=0)
            subQuery = " where id_cliente='"+id+ "' and id_vehiculo='"+id2+"'";

        cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_NAME_CLI_VEHI
                + subQuery+" ORDER BY matricula ASC", null);

        return this.recordToTable(cursor,isList);
    }

    private Object recordToTable(Cursor cursor, boolean isList){
        ArrayList<MdClienteVehiculo> lista = new ArrayList<>();
        boolean isExists = false;
        if (cursor.moveToFirst()) {
            isExists =true;
            do {
                MdClienteVehiculo model = new MdClienteVehiculo();
                model.setIdCliente(cursor.getInt(0));
                model.setIdVehiculo(cursor.getInt(1));
                model.setMatricula(cursor.getString(2));
                model.setKilometros(cursor.getString(3));
                lista.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista.size()>1 ? lista : (isExists) ? (isList) ? lista : lista.get(0): lista;
    }
}
