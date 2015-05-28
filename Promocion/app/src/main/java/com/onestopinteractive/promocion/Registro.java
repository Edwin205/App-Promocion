package com.onestopinteractive.promocion;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;



public class Registro extends ActionBarActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button btnNombre,btnEmail,btnTelefono,btnOtro,btnFinalizar;
    EditText etNombre,etEmail,etTelefono;
    String nombreSupervisor;
    String ubicacionSupervisor;
    TextView prueba ;
    Supervisor supervisor;
    TextView nombreSuper,ubicacionSuper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Todos los EditText a utlizar


        //Todos los botones que se utlizan
        setContentView(R.layout.activity_registro);
        btnNombre = (Button) findViewById(R.id.buttonNombre);
        btnEmail = (Button) findViewById(R.id.buttonEmail);
        btnTelefono = (Button) findViewById(R.id.buttonTelefono);
        btnOtro = (Button) findViewById(R.id.buttonOtro);
        btnFinalizar= (Button) findViewById(R.id.buttonFinalizar);


        //El viewFlipper
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);



        Intent intent = getIntent();
        nombreSupervisor = intent.getStringExtra("nombreSupervisor");
        ubicacionSupervisor = intent.getStringExtra("ubicacionSupervisor");


        //Datos del supervisor
        supervisor = new Supervisor();
        supervisor.setNombreSupervisor(nombreSupervisor);
        supervisor.setUbicacionSupervisor(ubicacionSupervisor);

        //TextView's
        nombreSuper = (TextView) findViewById(R.id.textViewNombreSprueba);
        ubicacionSuper = (TextView) findViewById(R.id.textViewUbicacionSPrueba);







        //Si se da click en cualquier bototn de estos
        btnNombre.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnOtro.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);
        btnTelefono.setOnClickListener(this);

    }


    public void alta(View v){

        String nombreSupervisor = supervisor.getNombreSupervisor();
        String ubicacionSupervisor = supervisor.getUbicacionSupervisor();


        etNombre = (EditText) findViewById(R.id.editTextNombrePersona);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etTelefono = (EditText) findViewById(R.id.editTextTelefono);

        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String telefono =etTelefono.getText().toString();



       prueba = (TextView) findViewById(R.id.textPrueba);
        DataBase baseDatos = new DataBase(this);
        baseDatos.abrir();
        baseDatos.insertarReg(nombreSupervisor,ubicacionSupervisor,nombre,email,telefono);
        prueba.setText(baseDatos.leer());
        baseDatos.cerrar();
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonNombre:
                viewFlipper.showNext();
                break;

            case R.id.buttonEmail:
                viewFlipper.showNext();
                break;

            case R.id.buttonTelefono:
                viewFlipper.showNext();
                break;

            case R.id.buttonOtro:
                viewFlipper.setDisplayedChild(0);
                break;


            case R.id.buttonFinalizar:
                Intent intent = new Intent(Registro.this,Portada.class);
                startActivity(intent);
                break;
        }
    }







}