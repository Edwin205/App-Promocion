package com.onestopinteractive.promocion;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class Portada extends ActionBarActivity implements View.OnClickListener {

    Button siguiente;
    ViewFlipper viewFlipper;
    Button buttonSettings;
    Button buttonGuardar;
    EditText superUbicacion,superNombre;
    TextView nombreSuper,ubicacionSuper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);


       viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipperPortada);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(this);
        buttonGuardar.setOnClickListener(this);

        siguiente = (Button) findViewById(R.id.btnPortada);





            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                    ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
                    String darError2 = ubicacionSuper.getText().toString();
                    String darError = nombreSuper.getText().toString();
                    boolean Error = true;

                   if(darError != "" && darError2 != "")
                       Error = false;

                    else
                   Toast.makeText(Portada.this,"Ingresa tus datos antes de continuar",Toast.LENGTH_SHORT).show();

                    if(Error == false) {
                        Intent intent = new Intent(Portada.this,Registro.class);
                        startActivity(intent);
                    }

                }
            });

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonGuardar:
                superNombre = (EditText) findViewById(R.id.editTextNombreSupervisor);
                superUbicacion = (EditText) findViewById(R.id.editTextUbicacion);
                nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);

                String nombreSupervisor = superNombre.getText().toString();
                String ubicacionSupervisor = superUbicacion.getText().toString();

                nombreSuper.setText(nombreSupervisor);
                ubicacionSuper.setText(ubicacionSupervisor);

                Toast.makeText(Portada.this,"Se cambio de supervisor",Toast.LENGTH_SHORT).show();

                viewFlipper.setDisplayedChild(0);

                break;
        }
    }
}
