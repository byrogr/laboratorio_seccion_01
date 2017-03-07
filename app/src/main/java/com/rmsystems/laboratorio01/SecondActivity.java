package com.rmsystems.laboratorio01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    //-- propiedad de los controles
    private SeekBar skbEdad;
    private TextView lblEdad;
    private RadioButton rdoSaludo, rdoDespedida;
    private Button btnPaso3;

    //-- propiedades logica de negocio
    private int edad = 18;
    private String nombre = "";

    private final int MAX_EDAD = 60;
    private final int MIN_EDAD = 16;

    public static final int OPC_SALUDO = 1;
    public static final int OPC_DESPEDIDA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //-- mapeamos los controles
        skbEdad = (SeekBar) findViewById(R.id.skbEdad);
        lblEdad = (TextView) findViewById(R.id.lblEdad);
        btnPaso3 = (Button) findViewById(R.id.btnPaso3);
        rdoSaludo = (RadioButton) findViewById(R.id.rdoSaludo);
        rdoDespedida = (RadioButton) findViewById(R.id.rdoDespedida);

        //-- recogemos los datos de la actividad anterior
        Bundle b = getIntent().getExtras();

        if ( b != null ) {
            nombre = b.getString("name");
        }

        //-- evento change del seekbar
        skbEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int edadActual, boolean fromUser) {
                edad = edadActual;
                lblEdad.setText( edad + "" );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //-- no es necesario para nuestra app
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                edad = seekBar.getProgress();
                lblEdad.setText( edad + "" );

                if ( edad < MIN_EDAD ) {
                    btnPaso3.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "La edad debe ser mayor a " + MIN_EDAD + " anios", Toast.LENGTH_LONG).show();
                } else if ( edad > MAX_EDAD ) {
                    btnPaso3.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "La edad debe ser menor a " + MAX_EDAD + " anios", Toast.LENGTH_LONG).show();
                } else {
                    btnPaso3.setVisibility(View.VISIBLE);
                }
            }
        });

        btnPaso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcion = rdoSaludo.isChecked() ? OPC_SALUDO : OPC_DESPEDIDA;
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("nombre", nombre);
                i.putExtra("edad", edad);
                i.putExtra("opcion", opcion);
                startActivity(i);
            }
        });
    }
}
