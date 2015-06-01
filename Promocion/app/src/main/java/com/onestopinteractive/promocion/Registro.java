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

import java.util.Random;


public class Registro extends ActionBarActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button btnNombre,btnEmail,btnTelefono,btnFechaNaci,btnOtro,btnFinalizar,btnOtroVip,btnFinalizarVIp,btnSuerte;
    EditText etNombre,etEmail,etTelefono,etDia,etMes,etAno;
    String nombreSupervisor;
    String ubicacionSupervisor;
    TextView prueba ;
    Supervisor supervisor;
    TextView nombreSuper,ubicacionSuper;
    Random ganadorAleatorio;
    int ganador;


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
        btnFechaNaci = (Button) findViewById(R.id.buttonFechaNaci);
        btnOtroVip = (Button) findViewById(R.id.buttonVipOtro);
        btnFinalizarVIp = (Button) findViewById(R.id.buttonVipFinalizar);
        btnSuerte = (Button) findViewById(R.id.buttonSuerte);


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
        btnFechaNaci.setOnClickListener(this);
        btnOtroVip.setOnClickListener(this);
        btnFinalizarVIp.setOnClickListener(this);
        btnSuerte.setOnClickListener(this);

    }


    public void alta(){

        String nombreSupervisor = supervisor.getNombreSupervisor();
        String ubicacionSupervisor = supervisor.getUbicacionSupervisor();

        //Todos los editText utilizados
        etNombre = (EditText) findViewById(R.id.editTextNombrePersona);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etTelefono = (EditText) findViewById(R.id.editTextTelefono);
        etDia = (EditText) findViewById(R.id.editTextDia);
        etMes = (EditText)findViewById(R.id.editTextMes);
        etAno = (EditText) findViewById(R.id.editTextAno);

        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String telefono =etTelefono.getText().toString();
        String fechaNacimiento = etDia.getText().toString()+"/"+etMes.getText().toString()+"/"+etAno.getText().toString();



       prueba = (TextView) findViewById(R.id.textPrueba);
        DataBase baseDatos = new DataBase(this);
        baseDatos.abrir();
        baseDatos.insertarReg(nombreSupervisor, ubicacionSupervisor, nombre, email, telefono, fechaNacimiento);
        prueba.setText(baseDatos.leer());
        baseDatos.cerrar();

        etNombre.setText("");
        etEmail.setText("");
        etTelefono.setText("");
        etDia.setText("");
        etMes.setText("");
        etAno.setText("");

        Toast.makeText(Registro.this,"Se guardo el registro",Toast.LENGTH_SHORT).show();
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonNombre:
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonEmail:
                viewFlipper.setDisplayedChild(2);
                break;

            case R.id.buttonTelefono:
                viewFlipper.setDisplayedChild(4);
                break;

            case R.id.buttonFechaNaci:
                viewFlipper.setDisplayedChild(5);
                break;

            case R.id.buttonSuerte:

                ganadorAleatorio = new Random();
                ganador = ganadorAleatorio.nextInt(3);

                if(ganador < 2) {
                    viewFlipper.setDisplayedChild(6);
                    alta();
                }
                else
                viewFlipper.setDisplayedChild(7);

                break;

            case R.id.buttonOtro:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonFinalizar:
                Intent intent = new Intent(Registro.this,Portada.class);
                startActivity(intent);
                break;

            case R.id.buttonVipOtro:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonVipFinalizar:
                Intent intent2 = new Intent(Registro.this,Portada.class);
                startActivity(intent2);
                break;
        }
    }







}
