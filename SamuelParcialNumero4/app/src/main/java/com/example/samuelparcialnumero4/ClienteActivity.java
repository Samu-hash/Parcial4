package com.example.samuelparcialnumero4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.samuelparcialnumero4.dao.list.ClientAdapter;
import com.example.samuelparcialnumero4.dao.models.MdClientes;
import com.example.samuelparcialnumero4.dao.persistence.MdClientesDao;

import java.util.ArrayList;

public class ClienteActivity  extends AppCompatActivity {

    private ClientAdapter adapter;

    private RecyclerView rv;

    private EditText eNombre, eApellido, eDir, eCuidad;

    private MdClientesDao repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente);

        rv = findViewById(R.id.lista);
        this.eNombre = findViewById(R.id.eNombre);
        this.eApellido = findViewById(R.id.eApellido);
        this.eDir = findViewById(R.id.eDir);
        this.eCuidad = findViewById(R.id.eCuidad);
        listar();
    }

    private void listar(){
        rv.setLayoutManager(new LinearLayoutManager(this));

        this.repo = new MdClientesDao(this);

        adapter = new ClientAdapter((ArrayList<MdClientes>) this.repo.viewClients(0, true));
        rv.setAdapter(adapter);
    }

    public void guardarRegistro(View v){
        if(!eNombre.getText().toString().equals("")
                || !eApellido.getText().toString().equals("") || !eDir.getText().toString().equals("")
        || !eCuidad.getText().toString().equals("")) {

            MdClientes c = new MdClientes();
            c.setNombreCliente(eNombre.getText().toString());
            c.setApellidosCliente(eApellido.getText().toString());
            c.setDireccionCliente(eDir.getText().toString());
            c.setCuidadCliente(eCuidad.getText().toString());

            c = this.repo.insertClient(c);

            if (c.getIdCliente() > 0) {
                Toast.makeText(ClienteActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                limpiar();
                listar();

            } else {
                Toast.makeText(ClienteActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(ClienteActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar(){
        this.eNombre.setText("");
        this.eApellido.setText("");
        this.eDir.setText("");
        this.eCuidad.setText("");
    }
}
