package com.example.samuelparcialnumero4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samuelparcialnumero4.dao.list.ClientVehiAdapter;
import com.example.samuelparcialnumero4.dao.models.MdClienteVehiculo;
import com.example.samuelparcialnumero4.dao.persistence.MdClienteVehiculoDao;

import java.util.ArrayList;

public class ClienteVehiculoActivity extends AppCompatActivity {

    private ClientVehiAdapter adapter;

    private RecyclerView rv;

    private EditText eIdClient, eIdVehi, eMatri, eKilo;

    private MdClienteVehiculoDao repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_vehiculo);

        rv = findViewById(R.id.lista);
        this.eIdClient = findViewById(R.id.eIdCliente);
        this.eIdVehi = findViewById(R.id.eIdVehiculo);
        this.eMatri = findViewById(R.id.eMatri);
        this.eKilo = findViewById(R.id.eKilo);
        listar();
    }

    private void listar(){
        rv.setLayoutManager(new LinearLayoutManager(this));

        this.repo = new MdClienteVehiculoDao(this);

        adapter = new ClientVehiAdapter((ArrayList<MdClienteVehiculo>) this.repo.viewCliVehi(0, 0, true));
        rv.setAdapter(adapter);
    }

    public void guardarRegistro(View v){
        if(!eIdClient.getText().toString().equals("")
                || !eIdVehi.getText().toString().equals("") || !eMatri.getText().toString().equals("")
                || !eKilo.getText().toString().equals("")) {

            MdClienteVehiculo c = new MdClienteVehiculo();
            c.setIdCliente(Integer.parseInt(eIdClient.getText().toString()));
            c.setIdVehiculo(Integer.parseInt(eIdVehi.getText().toString()));
            c.setMatricula(eMatri.getText().toString());
            c.setKilometros(eKilo.getText().toString());

            boolean resp = this.repo.insertCliVehi(c);

            if (resp) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                limpiar();
                listar();

            } else {
                Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar(){
        this.eIdClient.setText("");
        this.eIdVehi.setText("");
        this.eMatri.setText("");
        this.eKilo.setText("");
    }
}
