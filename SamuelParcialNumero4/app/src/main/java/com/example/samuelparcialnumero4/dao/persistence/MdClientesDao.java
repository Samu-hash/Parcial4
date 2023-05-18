package com.example.samuelparcialnumero4.dao.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.samuelparcialnumero4.dao.conexion.ConexionHelpers;
import com.example.samuelparcialnumero4.dao.conexion.Utils;
import com.example.samuelparcialnumero4.dao.models.MdClientes;

import java.util.ArrayList;

public class MdClientesDao extends ConexionHelpers {

    private Context context;

    public MdClientesDao(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public MdClientes insertClient(MdClientes c) {

        try {
            ConexionHelpers dbHelper = new ConexionHelpers(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_cliente", c.getNombreCliente());
            values.put("apellidos_cliente", c.getApellidosCliente());
            values.put("direccion_cliente", c.getDireccionCliente());
            values.put("cuidad_cliente", c.getCuidadCliente());
            int id = (int) db.insert(Utils.TABLE_NAME_CLI, null, values);
            c.setIdCliente(id);
        } catch (Exception ex) {
            ex.toString();
            c.setIdCliente(0);
        }
        return c;
    }

    public boolean updateClient(MdClientes c) {

        boolean isUpdate = false;

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Utils.TABLE_NAME_CLI
                    + " SET nombre_cliente = '" + c.getNombreCliente()
                    + "', apellidos_cliente = '" + c.getApellidosCliente()
                    + "', direccion_cliente = '" + c.getDireccionCliente()
                    + "', cuidad_cliente = '" + c.getCuidadCliente()
                    + "' WHERE id_cliente='" + c.getIdCliente() + "' ");
            isUpdate = true;
        } catch (Exception ex) {
            ex.toString();
            isUpdate = false;
        } finally {
            db.close();
        }

        return isUpdate;
    }

    public boolean deleteClient(int id) {

        boolean idDelete = false;

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Utils.TABLE_NAME_CLI
                    + " WHERE id_cliente = '" + id + "'");
            idDelete = true;
        } catch (Exception ex) {
            ex.toString();
            idDelete = false;
        } finally {
            db.close();
        }
        return idDelete;
    }

    public Object viewClients(int id, boolean isList) {

        ConexionHelpers dbHelper = new ConexionHelpers(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String subQuery = "";

        if(id!=0)
            subQuery = " where id_cliente='"+id+"'";

        cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_NAME_CLI
                + subQuery+" ORDER BY nombre_cliente ASC", null);

        return this.recordToTable(cursor, isList);
    }

    private Object recordToTable(Cursor cursor, boolean isList){
        ArrayList<MdClientes> lista = new ArrayList<>();
        boolean isExists = false;
        if (cursor.moveToFirst()) {
            isExists =true;
            do {
                MdClientes model = new MdClientes();
                model.setIdCliente(cursor.getInt(0));
                model.setNombreCliente(cursor.getString(1));
                model.setApellidosCliente(cursor.getString(2));
                model.setDireccionCliente(cursor.getString(3));
                model.setCuidadCliente(cursor.getString(4));
                lista.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista.size()>1 ? lista : (isExists) ? (isList) ? lista : lista.get(0): lista;
    }
}
