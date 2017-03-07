package com.rmsystems.laboratorio01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    //-- propiedades de los controles
    private ImageButton imbConfirmar;
    private Button btnCompartir;

    //-- propiedades de logica de negocio
    private String nombre = "";
    private int edad = 18;
    private int opcion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //-- obtenemos los datos de la actividad anterior
        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            nombre = b.getString("nombre");
            edad = b.getInt("edad");
            opcion = b.getInt("opcion");
        }

        //-- mapeamos los controles
        imbConfirmar = (ImageButton) findViewById(R.id.imbConfirmar);
        btnCompartir = (Button) findViewById(R.id.btnShare);

        imbConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, createMessage(nombre, edad, opcion), Toast.LENGTH_LONG).show();
                imbConfirmar.setVisibility(View.INVISIBLE);
            }
        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                i.putExtra(Intent.EXTRA_TEXT, createMessage(nombre, edad, opcion));
                startActivity(i);
            }
        });
    }

    private String createMessage(String nombre, int edad, int opcion)
    {
        if ( opcion == SecondActivity.OPC_SALUDO ) {
            return "Hola " + nombre + ", ¿Cómo llevas esos " + edad + " años? #MyForm";
        } else {
            return "Espero verte pronto " + nombre + ", antes que cumplas " + (edad + 1) + ".. #MyForm";
        }
    }
}
