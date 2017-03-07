package com.rmsystems.laboratorio01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        //--
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        btnNext = (Button)findViewById(R.id.btnPaso2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                if ( nombre != null && ! nombre.isEmpty() ) {
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("name", nombre);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Debe ingresar un nombre", Toast.LENGTH_SHORT).show();
                    txtNombre.setFocusable(true);
                }
            }
        });
    }
}
