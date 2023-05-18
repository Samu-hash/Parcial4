package com.example.samuelparcialnumero4.dao.conexion;

public class Utils {

    public static final int VERSION_SQL = 1;

    public static final String DB_NAME = "parcial4samuel.db";
    public static final String TABLE_NAME_CLI = "md_clientes";
    public static final String TABLE_NAME_VEHI = "md_vehiculos";
    public static final String TABLE_NAME_CLI_VEHI = "md_cliente_vehiculo";

    public static final String CREATE_TABLE_CLI = "CREATE TABLE "+TABLE_NAME_CLI+"(id_cliente integer primary key autoincrement, " +
            "nombre_cliente text not null, apellidos_cliente text not null, direccion_cliente text not null, cuidad_cliente text not null)";
    public static final String CREATE_TABLE_VEHI = "CREATE TABLE "+TABLE_NAME_VEHI+"(id_vehiculo integer primary key autoincrement, " +
            "marca text not null, modelo text not null)";
    public static final String CREATE_TABLE_CLI_VEHI = "CREATE TABLE "+TABLE_NAME_CLI_VEHI+"(id_cliente integer not null, " +
            "id_vehiculo int not null, matricula text not null, kilometro int not null, primary key(id_cliente, id_vehiculo))";

    public static final String DROP_TABLE_CLI = "DROP TABLE "+TABLE_NAME_CLI;
    public static final String DROP_TABLE_VEHI = "DROP TABLE "+TABLE_NAME_VEHI;
    public static final String DROP_TABLE_CLI_VEHI = "DROP TABLE "+TABLE_NAME_CLI_VEHI;

}
