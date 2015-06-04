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
    EditText superUbicacion,superNombre,tallaS,tallaM,tallaL,porcentajeGanador;
    TextView nombreSuper,ubicacionSuper;
    String cantidadS,cantidadM,cantidadL,porcentaje;




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
                    String ubicacionSuperv = ubicacionSuper.getText().toString();
                    String nombreSuperv = nombreSuper.getText().toString();
                    boolean darError = true;

                   if(nombreSuperv != "" && ubicacionSuperv != "")
                       darError = false;

                    else
                   Toast.makeText(Portada.this,"Ingresa tus datos antes de continuar.",Toast.LENGTH_SHORT).show();

                    if(darError == false) {

                        tallaS = (EditText) findViewById(R.id.editTextSmall);
                        tallaM = (EditText) findViewById(R.id.editTextMediuem);
                        tallaL = (EditText) findViewById(R.id.editTextLarge);
                        porcentajeGanador = (EditText) findViewById(R.id.editTextPorcentaje);

                         cantidadS = tallaS.getText().toString();
                         cantidadM = tallaM.getText().toString();
                         cantidadL = tallaL.getText().toString();
                         porcentaje = porcentajeGanador.getText().toString();




                        Intent intent = new Intent(Portada.this,Registro.class);
                        intent.putExtra("cantidadS",cantidadS);
                        intent.putExtra("cantidadM",cantidadM);
                        intent.putExtra("cantidadL",cantidadL);
                        intent.putExtra("porcentaje",porcentaje);
                        intent.putExtra("nombreSupervisor",nombreSuperv);
                        intent.putExtra("ubicacionSupervisor",ubicacionSuperv);
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

                EditText validacionSm = (EditText) findViewById(R.id.editTextSmall);
                String validacionS = validacionSm.getText().toString();
                EditText validacionMe = (EditText) findViewById(R.id.editTextMediuem);
                String validacionM = validacionMe.getText().toString();
                EditText validacionGr = (EditText) findViewById(R.id.editTextLarge);
                String validacionL = validacionGr.getText().toString();
                EditText validacionPo = (EditText) findViewById(R.id.editTextPorcentaje);
                String validacionP = validacionPo.getText().toString();
                boolean botonOff = true;


                if(validacionS.equals("")) {
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }

                else if (validacionM.equals("")){
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }

                else if(validacionL.equals("")){
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }

                else if (validacionP.equals("")){
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }
                else if (nombreSupervisor.equals("")){
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }
                else if (ubicacionSupervisor.equals("")){
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }

                else
                  botonOff = true;

                if(botonOff == true) {
                  Toast.makeText(Portada.this, "Se a cambiado de supervisor.", Toast.LENGTH_SHORT).show();
                  viewFlipper.setDisplayedChild(0);
              }


        }
    }
}
