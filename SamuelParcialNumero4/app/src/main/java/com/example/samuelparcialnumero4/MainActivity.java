package com.example.samuelparcialnumero4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCli, btnVehi, btnCliVehi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnCli = findViewById(R.id.btnCli);
        this.btnVehi = findViewById(R.id.btnVehi);
        this.btnCliVehi = findViewById(R.id.btnCliVehi);

    }

    public void pantallaCliente(View view){
        Intent intent = new Intent(this, ClienteActivity.class);
        startActivity(intent);
    }

    public void pantallaVehiculo(View view){
        Intent intent = new Intent(this, VehiculoActivity.class);
        startActivity(intent);
    }

    public void pantallaClienteVehiculo(View view){
        Intent intent = new Intent(this, ClienteVehiculoActivity.class);
        startActivity(intent);
    }
}