package com.example.samuelparcialnumero4.dao.models;

public class MdClientes {

    private int idCliente;
    private String nombreCliente;
    private String apellidosCliente;
    private String direccionCliente;
    private String cuidadCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCuidadCliente() {
        return cuidadCliente;
    }

    public void setCuidadCliente(String cuidadCliente) {
        this.cuidadCliente = cuidadCliente;
    }
}
