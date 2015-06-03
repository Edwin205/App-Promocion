package com.onestopinteractive.promocion;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Random;


public class Registro extends ActionBarActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button btnNombre,btnEmail,btnTelefono,btnFechaNaci,btnOtro,btnFinalizar
            ,btnOtroVip,btnFinalizarVIp,btnSuerte,btnS,btnM,btnL,btnSI
            ,btnNO,btnSigPersonalizada,btnCalle,btnExterior,btnInterior,btnColonia,btnCiudad,btnCodigoPostal,btnEstado;

    EditText etNombre,etEmail,etTelefono,etDia,etMes,etAno,etFrase,etCalle,etExterior,etInterior,etColonia ,etCiudad,etCodigoPostal,etEstado;
    String nombreSupervisor;
    String ubicacionSupervisor,premio,personalizada;
    TextView prueba,prubaFin;
    Supervisor supervisor;
    TextView nombreSuper,ubicacionSuper;
    Random ganadorAleatorio;
    int ganador;
   int cantidadSmall,cantidadMedium,cantidadLarge,porcentaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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
        btnS = (Button) findViewById(R.id.buttonSmall);
        btnM = (Button) findViewById(R.id.buttonMedium);
        btnL = (Button) findViewById(R.id.buttonLarge);
        btnSI = (Button) findViewById(R.id.buttonSI);
        btnNO = (Button) findViewById(R.id.buttonNO);
        btnSigPersonalizada = (Button) findViewById(R.id.buttonSigPersonalizacion);
        btnCalle = (Button) findViewById(R.id.buttonCalle);
        btnExterior = (Button) findViewById(R.id.buttonExterior);
        btnInterior = (Button) findViewById(R.id.buttonInterior);
        btnColonia = (Button) findViewById(R.id.buttonColonia);
        btnCiudad = (Button) findViewById(R.id.buttonCiudad);
        btnCodigoPostal = (Button) findViewById(R.id.buttonCodigoPostal);
        btnEstado = (Button) findViewById(R.id.buttonEstado);

        //El viewFlipper
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);


        Intent intent = getIntent();
        cantidadSmall = Integer.parseInt(intent.getStringExtra("cantidadS"));
        cantidadMedium = Integer.parseInt(intent.getStringExtra("cantidadM"));
        cantidadLarge = Integer.parseInt(intent.getStringExtra("cantidadL"));
        nombreSupervisor = intent.getStringExtra("nombreSupervisor");
        ubicacionSupervisor = intent.getStringExtra("ubicacionSupervisor");
        porcentaje = Integer.parseInt(intent.getStringExtra("porcentaje"));


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
        btnS.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnL.setOnClickListener(this);
        btnSI.setOnClickListener(this);
        btnNO.setOnClickListener(this);
        btnSigPersonalizada.setOnClickListener(this);
        btnCalle.setOnClickListener(this);
        btnExterior.setOnClickListener(this);
        btnInterior.setOnClickListener(this);
        btnColonia.setOnClickListener(this);
        btnCiudad.setOnClickListener(this);
        btnCodigoPostal.setOnClickListener(this);
        btnEstado.setOnClickListener(this);

        personalizada = "";






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
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                viewFlipper.setDisplayedChild(5);
                break;

            case R.id.buttonSuerte:

                ganadorAleatorio = new Random();
                ganador = 1+ganadorAleatorio.nextInt(porcentaje);

                if(ganador == porcentaje) {
                    viewFlipper.setDisplayedChild(7);
                    premio = "Gano playera";

                }
                else {
                    viewFlipper.setDisplayedChild(6);
                    premio = "Experiencia VIP";
                    alta();
                }

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

            case R.id.buttonSmall:

                cantidadSmall -= 1;

                if(cantidadSmall == 1) {
                    Toast.makeText(Registro.this, "Es la ultima playera small", Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                }

                else if(cantidadSmall<=0)
                    Toast.makeText(Registro.this,"Ya no quedan mas playeras Small",Toast.LENGTH_SHORT).show();

                else {
                    Toast.makeText(Registro.this,"Quedan"+cantidadSmall+" "+"playeras small",Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                }
                break;

            case R.id.buttonMedium:
                cantidadMedium -= 1;

                if(cantidadMedium == 1) {
                    Toast.makeText(Registro.this, "Es la ultima playera small", Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                }

                else if(cantidadMedium<=0)
                    Toast.makeText(Registro.this,"Ya no quedan mas playeras Small",Toast.LENGTH_SHORT).show();

                else {
                    Toast.makeText(Registro.this,"Quedan"+cantidadMedium+" "+"playeras small",Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                }
                break;

            case R.id.buttonLarge:
                cantidadLarge -= 1;

                if(cantidadLarge == 1) {
                    Toast.makeText(Registro.this, "Es la ultima playera small", Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                }

                else if(cantidadLarge<=0)
                    Toast.makeText(Registro.this,"Ya no quedan mas playeras Small",Toast.LENGTH_SHORT).show();

                else {
                    Toast.makeText(Registro.this,"Quedan"+cantidadLarge+" "+"playeras small",Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                }
                break;

            case R.id.buttonSI:
                viewFlipper.setDisplayedChild(9);
                personalizada = "Si";
                break;

            case R.id.buttonNO:
                viewFlipper.setDisplayedChild(3);
                personalizada = "No";
                alta();
                break;

            case R.id.buttonSigPersonalizacion:
                viewFlipper.setDisplayedChild(10);
                break;

            case R.id.buttonCalle:
                viewFlipper.setDisplayedChild(11);
                break;

            case R.id.buttonExterior:
                viewFlipper.setDisplayedChild(12);
                break;

            case R.id.buttonInterior:
                viewFlipper.setDisplayedChild(13);
                break;

            case R.id.buttonColonia:
                viewFlipper.setDisplayedChild(14);
                break;

            case R.id.buttonCiudad:
                viewFlipper.setDisplayedChild(15);
                break;

            case R.id.buttonCodigoPostal:
                viewFlipper.setDisplayedChild(16);
                break;

            case R.id.buttonEstado:
                InputMethodManager inputM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputM.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                alta();
                viewFlipper.setDisplayedChild(3);
                break;
        }
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
        etFrase = (EditText) findViewById(R.id.editTextPersonalizacion);
        etCalle = (EditText) findViewById(R.id.editTextCalle);
        etExterior = (EditText) findViewById(R.id.editTextExterior);
        etInterior = (EditText) findViewById(R.id.editTextInterior);
        etColonia = (EditText) findViewById(R.id.editTextColonia);
        etCiudad = (EditText) findViewById(R.id.editTextCiudad);
        etCodigoPostal = (EditText) findViewById(R.id.editTextCodigoPostal);
        etEstado = (EditText) findViewById(R.id.editTextEstado);

        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String telefono =etTelefono.getText().toString();
        String fechaNacimiento = etDia.getText().toString()+"/"+etMes.getText().toString()+"/"+etAno.getText().toString();
        String frase = etFrase.getText().toString();
        String calle = etCalle.getText().toString();
        String noExterior  = etExterior.getText().toString();
        String noInterior = etInterior.getText().toString();
        String colonia = etColonia.getText().toString();
        String ciudad = etCiudad.getText().toString();
        String codigoPostal = etCodigoPostal.getText().toString();
        String estado = etEstado.getText().toString();

        prueba = (TextView) findViewById(R.id.textPrueba);
        prubaFin = (TextView) findViewById(R.id.textViewPruebaFin);
        DataBase baseDatos = new DataBase(this);
        baseDatos.abrir();
        baseDatos.insertarReg(nombreSupervisor, ubicacionSupervisor, nombre, email,
                telefono, fechaNacimiento, premio, personalizada, calle, frase, noExterior, noInterior, colonia, ciudad, codigoPostal, estado);
        prueba.setText(baseDatos.leer());
        prubaFin.setText(baseDatos.leer());
        baseDatos.cerrar();

        etNombre.setText("");
        etEmail.setText("");
        etTelefono.setText("");
        etDia.setText("");
        etMes.setText("");
        etAno.setText("");
        etFrase.setText("");
        etCalle.setText("");
        etExterior.setText("");
        etInterior.setText("");
        etColonia.setText("");
        etCiudad.setText("");
        etCodigoPostal.setText("");
        etEstado.setText("");

        Toast.makeText(Registro.this,"Se guardo el registro",Toast.LENGTH_SHORT).show();
    }


}
