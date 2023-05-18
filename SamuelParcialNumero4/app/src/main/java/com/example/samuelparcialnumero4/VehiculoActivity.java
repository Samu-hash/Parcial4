package com.example.samuelparcialnumero4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samuelparcialnumero4.dao.list.VehiAdapter;
import com.example.samuelparcialnumero4.dao.models.MdVehiculos;
import com.example.samuelparcialnumero4.dao.persistence.MdVehiculoDao;

import java.util.ArrayList;

public class VehiculoActivity extends AppCompatActivity {

    private VehiAdapter adapter;

    private RecyclerView rv;

    private EditText eMarca, eModelo;

    private MdVehiculoDao repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehiculo);

        rv = findViewById(R.id.lista);
        this.eMarca = findViewById(R.id.eMarca);
        this.eModelo = findViewById(R.id.eModelo);
        listar();
    }

    private void listar(){
        rv.setLayoutManager(new LinearLayoutManager(this));

        this.repo = new MdVehiculoDao(this);

        adapter = new VehiAdapter((ArrayList<MdVehiculos>) this.repo.viewVehi(0, true));
        rv.setAdapter(adapter);
    }

    public void guardarRegistro(View view){
        if(!eMarca.getText().toString().equals("")
                || !eModelo.getText().toString().equals("")) {

            MdVehiculos c = new MdVehiculos();
            c.setMarca(eMarca.getText().toString());
            c.setModelo(eModelo.getText().toString());

            c = this.repo.insertVehi(c);

            if (c.getIdVehiculo() > 0) {
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
        this.eMarca.setText("");
        this.eModelo.setText("");
    }
}
