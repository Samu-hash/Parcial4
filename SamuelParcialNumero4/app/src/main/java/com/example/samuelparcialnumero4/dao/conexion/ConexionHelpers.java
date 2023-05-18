package com.example.samuelparcialnumero4.dao.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionHelpers extends SQLiteOpenHelper {

    public ConexionHelpers(@Nullable Context context) {
        super(context, Utils.DB_NAME, null, Utils.VERSION_SQL);
        System.out.println("========================Creando base de datos");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("========================Creando tablas");
        sqLiteDatabase.execSQL(Utils.CREATE_TABLE_CLI);
        sqLiteDatabase.execSQL(Utils.CREATE_TABLE_VEHI);
        sqLiteDatabase.execSQL(Utils.CREATE_TABLE_CLI_VEHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Utils.DROP_TABLE_CLI);
        sqLiteDatabase.execSQL(Utils.DROP_TABLE_VEHI);
        sqLiteDatabase.execSQL(Utils.DROP_TABLE_CLI_VEHI);

        onCreate(sqLiteDatabase);
    }
}
