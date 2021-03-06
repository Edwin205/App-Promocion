package com.onestopinteractive.promocion;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import android.content.SharedPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Registro extends ActionBarActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button btnNombre, btnApellido,btnApellidoMaterno,btnEmail,btnTelefono,btnTelefonoSecund,btnFechaNaci,btnOtro
            ,btnOtroVip,btnSuerte,btnS,btnL,btnSI
            ,btnNO,btnCancelar,btnSiCancelar,btnNocancelar,btnSigPersonalizada,btnDelegacion,btnCalle,btnExterior,btnInterior,btnColonia,btnCiudad,btnCodigoPostal,btnEstado,btnTicket;

    EditText etNombre,etApellidos,etApellidoMaterno,etEmail,etTelefono,etTelefonoSecundario,etDia,etMes,etAno,etFrase,etCalle,etExterior,etInterior,etColonia ,etCiudad,etCodigoPostal,etEstado,etDelegacion,etTicket;
    String nombreSupervisor,refernciaTienda;
    String ubicacionSupervisor,premio;

    Supervisor supervisor;
    TextView nombreSuper,ubicacionSuper,prueba,pruebaFin;
    Random ganadorAleatorio;
    int ganador;
    int porcentaje;
    DataBase baseDatos;
    TextView sqlprueba;
    int diaI,mesI,anoI;
    String diaS,mesS,anoS;
    String medida;
    Intent intent;
    Button atrasPoliticas,atrasCondiciones,btnCondiciones,btnPoliticas,btnTerminos;
    boolean checked;
    boolean politicas;
    int preview=0,atras=0;
    Button btnBodegaAhorrera,btnCasaLey,btnChedrahui,btnComercialMexicana,btnHEB,btnSoriana,btnSuperama,btnWalmart,btnSiHay,btnNoHay;
    String tiendaCompra;
    Registro registerl;
    boolean enBlanco;
    Button btnAtras;
    int totales,nuevos;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseDatos = new DataBase(this);
        sqlprueba = (TextView) findViewById(R.id.textViewSQLprueba);
        registerl = new Registro();
        enBlanco = false;

       if(nuevos==0)
       {
           SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
           nuevos= sharedPref.getInt("Nuevos",0);
       }

        if(totales ==0)
        {
            SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
            totales= sharedPref.getInt("totales",0);
        }


        medida = "L/XL";
        Intent registros = new Intent();

        //Todos los botones que se utlizan
        setContentView(R.layout.activity_registro);

        btnAtras = (Button) findViewById(R.id.buttonAtras);
        btnSiHay = (Button) findViewById(R.id.buttonSiHay);
        btnNoHay = (Button) findViewById(R.id.buttonNoHay);
        btnNombre = (Button) findViewById(R.id.buttonNombre);
        btnEmail = (Button) findViewById(R.id.buttonEmail);
        btnTelefono = (Button) findViewById(R.id.buttonTelefono);
        btnOtro = (Button) findViewById(R.id.buttonOtro);
        btnFechaNaci = (Button) findViewById(R.id.buttonFechaNaci);
        btnOtroVip = (Button) findViewById(R.id.buttonVipOtro);
        btnSuerte = (Button) findViewById(R.id.buttonSuerte);
        btnS = (Button) findViewById(R.id.buttonSmall);
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
        btnTicket =(Button) findViewById(R.id.buttonTicket);
        btnApellido = (Button) findViewById(R.id.buttonApellidos);
        btnDelegacion = (Button) findViewById(R.id.buttonDelegacion);
        btnApellidoMaterno = (Button) findViewById(R.id.buttonApellidoMaterno);
        btnTelefonoSecund = (Button) findViewById(R.id.buttonTelefonoSecundario);
        btnCancelar = (Button) findViewById(R.id.buttonCancelar);
        btnSiCancelar = (Button) findViewById(R.id.buttonSiCancelar);
        btnNocancelar = (Button) findViewById(R.id.buttonNoCancelar);
        atrasCondiciones = (Button) findViewById(R.id.buttonAtrasTermi);
        atrasPoliticas = (Button) findViewById(R.id.buttonAtrasPoliticas);
        btnCondiciones = (Button) findViewById(R.id.buttonCondiciones);
        btnPoliticas = (Button) findViewById(R.id.buttonPoliticas);
        btnTerminos = (Button) findViewById(R.id.buttonTerminos);
        btnBodegaAhorrera = (Button) findViewById(R.id.buttonBAurrera);
        btnCasaLey = (Button) findViewById(R.id.buttonCasaLey);
        btnChedrahui = (Button) findViewById(R.id.buttonChedrahui);
        btnComercialMexicana = (Button) findViewById(R.id.buttonCMexicana);
        btnHEB = (Button) findViewById(R.id.buttonHeb);
        btnSoriana = (Button) findViewById(R.id.buttonSoriana);
        btnSuperama = (Button) findViewById(R.id.buttonSuperama);
        btnWalmart = (Button) findViewById(R.id.buttonWalmart);




        //El viewFlipper
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);


        intent = getIntent();
        nombreSupervisor = intent.getStringExtra("nombreSupervisor");
        refernciaTienda = intent.getStringExtra("tiendaReferencia");
        ubicacionSupervisor = intent.getStringExtra("ubicacionSupervisor");
        porcentaje = Integer.parseInt(intent.getStringExtra("porcentaje"));




        //Datos del supervisor
        supervisor = new Supervisor();
        supervisor.setNombreSupervisor(nombreSupervisor);
        supervisor.setUbicacionSupervisor(ubicacionSupervisor);
        supervisor.setTiendaSuper(refernciaTienda);

        //TextView's
        nombreSuper = (TextView) findViewById(R.id.textViewNombreSprueba);
        ubicacionSuper = (TextView) findViewById(R.id.textViewUbicacionSPrueba);



        //Si se da click en cualquier bototn de estos
        btnNombre.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnOtro.setOnClickListener(this);
        btnTelefono.setOnClickListener(this);
        btnFechaNaci.setOnClickListener(this);
        btnOtroVip.setOnClickListener(this);
        btnSuerte.setOnClickListener(this);
        btnS.setOnClickListener(this);
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
        btnTicket.setOnClickListener(this);
        btnApellido.setOnClickListener(this);
        btnDelegacion.setOnClickListener(this);
        btnApellidoMaterno.setOnClickListener(this);
        btnTelefonoSecund.setOnClickListener(this);
        btnNocancelar.setOnClickListener(this);
        btnSiCancelar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        atrasCondiciones.setOnClickListener(this);
        atrasPoliticas.setOnClickListener(this);
        btnCondiciones.setOnClickListener(this);
        btnPoliticas.setOnClickListener(this);
        btnTerminos.setOnClickListener(this);
        btnBodegaAhorrera.setOnClickListener(this);
        btnCasaLey.setOnClickListener(this);
        btnChedrahui.setOnClickListener(this);
        btnComercialMexicana.setOnClickListener(this);
        btnHEB.setOnClickListener(this);
        btnSoriana.setOnClickListener(this);
        btnSuperama.setOnClickListener(this);
        btnWalmart.setOnClickListener(this);
        btnNoHay.setOnClickListener(this);
        btnSiHay.setOnClickListener(this);
        btnAtras.setOnClickListener(this);




        checked = false;
        politicas = false;

    }




    public void terminosClicked(View v) {
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            checked = true;
        }
        else
        {
            checked = false;
        }
    }


    public void politicasClicked(View v) {
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            politicas = true;
        }
        else
        {
            politicas = false;
        }
    }


    public void cambiar()
    {

        preview=6;
        atras=5;
        viewFlipper.setDisplayedChild(6);
        btnCancelar.setVisibility(View.INVISIBLE);

        System.out.println("<<<<<<<<<<< WillPhoto");
        dispatchTakePictureIntent();
        System.out.println("<<<<<<<<<<< DidPhoto");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBAurrera:
                tiendaCompra = "Bodega Aurrerá";
                cambiar();
                break;

            case R.id.buttonCasaLey:
                tiendaCompra="Casa ley";
                cambiar();
                break;

            case R.id.buttonChedrahui:
                tiendaCompra="Chedrahui";
                cambiar();
                break;

            case R.id.buttonCMexicana:
                tiendaCompra="Comercial Mexicana ";
                cambiar();
                break;

            case R.id.buttonHeb:

                tiendaCompra="HEB";
                cambiar();
                break;

            case R.id.buttonSoriana:


                tiendaCompra="Soriana";
                cambiar();
                break;

            case R.id.buttonSuperama:

                tiendaCompra="Superama";
                cambiar();
                break;

            case R.id.buttonWalmart:

                tiendaCompra="Walmart";
               cambiar();
                break;

            case R.id.buttonCondiciones:
                if(checked ==true&& politicas ==true) {
                    viewFlipper.setDisplayedChild(1);
                    preview = 1;
                    atras =0;
                    btnCancelar.setVisibility(View.VISIBLE);
                }

                else
                {
                    checked = false;
                    politicas= false;
                    Toast.makeText(this,"No haz aceptado",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonAtrasTermi:

                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonAtrasPoliticas:

                viewFlipper.setDisplayedChild(0);
                break;

            case  R.id.buttonTerminos:
                btnCancelar.setVisibility(View.INVISIBLE);
                viewFlipper.setDisplayedChild(26);
                break;

            case R.id.buttonPoliticas:
                btnCancelar.setVisibility(View.INVISIBLE);
                viewFlipper.setDisplayedChild(25);
                break;


            case R.id.buttonCancelar:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnCancelar.getWindowToken(), 0);
                viewFlipper.setDisplayedChild(20);
                btnCancelar.setVisibility(View.INVISIBLE);
                btnAtras.setVisibility(View.INVISIBLE);
                break;

            case R.id.buttonSiCancelar:
                finish();
                btnCancelar.setVisibility(View.VISIBLE);
                break;

            case R.id.buttonNoCancelar:
                viewFlipper.setDisplayedChild(preview);
                btnCancelar.setVisibility(View.VISIBLE);
                btnAtras.setVisibility(View.VISIBLE);
                break;


            case R.id.buttonNombre:
                preview=21;
                atras = 1;
                EditText cNombre= (EditText) findViewById(R.id.editTextNombrePersona);
                String comNombre = cNombre.getText().toString();
                int length = comNombre.length();
                if(length>=2) {
                    viewFlipper.setDisplayedChild(21);
                    btnAtras.setVisibility(View.VISIBLE);
                }
                else
                    Toast.makeText(Registro.this, "Ingresa tu nombre completo.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonApellidos:
                preview=23;
                atras =21;

                EditText cApellidos= (EditText) findViewById(R.id.editTextApellidosPersona);
                String comApellidos = cApellidos.getText().toString();
                int lengtha = comApellidos.length();
                if(lengtha>=2)
                    viewFlipper.setDisplayedChild(23);
                else
                    Toast.makeText(Registro.this, "Ingresa tu apellido completo.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonApellidoMaterno:
                atras =23;
                preview=2;
                viewFlipper.setDisplayedChild(2);
                break;

            case R.id.buttonEmail:
                preview=3;
                atras =2;
                EditText cEmail = (EditText) findViewById(R.id.editTextEmail);
                String comEmial = cEmail.getText().toString();
                int longitud = comEmial.length();
                char valor;

                if(comEmial.equals(""))
                {
                    viewFlipper.setDisplayedChild(3);
                }
                    else{
                for (char i = 0; i < longitud; i++) {
                    valor = comEmial.charAt(i);
                    if (valor == '@' && i == 0) {
                        Toast.makeText(Registro.this, "Escribe un correo valido", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (valor == '@') {
                        viewFlipper.setDisplayedChild(3);
                        break;
                    } else if (i == longitud - 1) {
                        Toast.makeText(Registro.this, "Escribe un correo valido", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
                break;

            case R.id.buttonTelefono:
                atras =3;
                preview=24;
                EditText cTelefono= (EditText) findViewById(R.id.editTextTelefono);
                String comTelefono = cTelefono.getText().toString();
                int lengthTelefono = comTelefono.length();

                if(lengthTelefono>=5)
                    viewFlipper.setDisplayedChild(24);

                else
                    Toast.makeText(Registro.this, "Ingresa un numero de telefono valido.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonTelefonoSecundario:
                atras =24;
                preview=5;
                viewFlipper.setDisplayedChild(5);
                break;


            case R.id.buttonFechaNaci:
                atras =5;
                preview=18;
                EditText dia = (EditText) findViewById(R.id.editTextDia);
                EditText mes = (EditText) findViewById(R.id.editTextMes);
                EditText ano = (EditText) findViewById(R.id.editTextAno);
                diaS = dia.getText().toString();
                mesS = mes.getText().toString();
                anoS = ano.getText().toString();
                int diaL= diaS.length();
                int mesL = mesS.length();
                int anoL = anoS.length();

                if(diaS.equals(""))
                    Toast.makeText(Registro.this,"Coloca un dia.",Toast.LENGTH_SHORT).show();

                else if(mesS.equals(""))
                    Toast.makeText(Registro.this,"Coloca un mes.",Toast.LENGTH_SHORT).show();

                else if(anoS.equals(""))
                    Toast.makeText(Registro.this, "Coloca un año.", Toast.LENGTH_SHORT).show();

                else if(diaL<2)
                    Toast.makeText(Registro.this,"Ingresa un dia valido.",Toast.LENGTH_SHORT).show();

                else if(mesL<2)
                    Toast.makeText(Registro.this,"Ingresa un mes valido.",Toast.LENGTH_SHORT).show();

                else if(anoL<4)
                    Toast.makeText(Registro.this, "Ingresa un año valido.", Toast.LENGTH_SHORT).show();

                else
                {
                    validacionFecha();
                }
                break;

            case R.id.buttonSuerte:
                if (mCurrentPhotoURI == null) {
                    System.out.println("<<<<<<<<<<< WillPhoto");
                    dispatchTakePictureIntent();
                    System.out.println("<<<<<<<<<<< DidPhoto");
                    break;
                }

                ganadorAleatorio = new Random();
                ganador = 1 + ganadorAleatorio.nextInt(porcentaje);

                if (ganador == porcentaje) {

                    premio = "playera";

                        enBlanco = true;
                        preview=8;
                        atras =18;
                        viewFlipper.setDisplayedChild(8);
                        btnCancelar.setVisibility(View.INVISIBLE);


                } else {
                    preview=7;
                    atras =18;
                    viewFlipper.setDisplayedChild(7);
                    premio = "vip";
                    premio();
                    sumaRegistros();
                    btnCancelar.setVisibility(View.INVISIBLE);
                }
                break;



            case R.id.buttonVipOtro:
                finish();
                btnCancelar.setVisibility(View.VISIBLE);
                break;


            case R.id.buttonSmall:

                medida = "S/M";
                preview=9;
                viewFlipper.setDisplayedChild(9);



                break;


            case R.id.buttonLarge:
                medida="XL/L";

                preview=9;
                viewFlipper.setDisplayedChild(9);


                break;

            case R.id.buttonSI:

                preview=10;
                viewFlipper.setDisplayedChild(10);
                btnCancelar.setVisibility(View.VISIBLE);
                break;


            case R.id.buttonNO:


                viewFlipper.setDisplayedChild(28);
                btnCancelar.setVisibility(View.INVISIBLE);
                break;

            case  R.id.buttonSiHay:
                btnCancelar.setVisibility(View.VISIBLE);
                  premio();
                  sumaRegistros();
                  finish();
                break;

            case  R.id.buttonNoHay:
                btnCancelar.setVisibility(View.VISIBLE);
                preview=10;
                viewFlipper.setDisplayedChild(10);
                btnCancelar.setVisibility(View.VISIBLE);

                break;






            case R.id.buttonSigPersonalizacion:

                EditText cPersonalizacion= (EditText) findViewById(R.id.editTextPersonalizacion);
                String comPersonalizaion = cPersonalizacion.getText().toString();
                int lenghtPers = comPersonalizaion.length();

                if(lenghtPers<=8) {
                    atras =10;
                    preview = 11;
                    viewFlipper.setDisplayedChild(11);
                    btnAtras.setVisibility(View.VISIBLE);
                }
                else
                    Toast.makeText(Registro.this, "Máximo 8 caracteres", Toast.LENGTH_SHORT).show();
                break;


            case R.id.buttonCalle:
                EditText cCalle= (EditText) findViewById(R.id.editTextCalle);
                String comCalle = cCalle.getText().toString();
                int lenghtCall = comCalle.length();

                if(lenghtCall>=4) {
                    atras =11;
                    preview=12;
                    viewFlipper.setDisplayedChild(12);
                }
                else
                    Toast.makeText(Registro.this, "Ingresa una calle correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonExterior:
                EditText cExterior= (EditText) findViewById(R.id.editTextExterior);
                String comExterior = cExterior.getText().toString();
                int lenghtExt = comExterior.length();

                if(lenghtExt>=1) {
                    atras =12;
                    preview=13;
                    viewFlipper.setDisplayedChild(13);
                }

                else
                    Toast.makeText(Registro.this, "Ingresa un numero correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonInterior:
                EditText cInterior= (EditText) findViewById(R.id.editTextInterior);
                String comInterior = cInterior.getText().toString();
                atras =13;
                    preview=14;
                    viewFlipper.setDisplayedChild(14);


                break;

            case R.id.buttonColonia:
                EditText cColonia= (EditText) findViewById(R.id.editTextColonia);
                String comColonia = cColonia.getText().toString();
                int lenghtCol = comColonia.length();

                if(lenghtCol>=5) {
                    atras =14;
                    preview=15;
                    viewFlipper.setDisplayedChild(15);
                }

                else
                    Toast.makeText(Registro.this, "Ingresa una colonia correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonCiudad:
                EditText cCiudad= (EditText) findViewById(R.id.editTextCiudad);
                String comCiudad = cCiudad.getText().toString();
                int lenghtCiu = comCiudad.length();

                if(lenghtCiu>=4) {
                    atras =15;
                    preview=16;
                    viewFlipper.setDisplayedChild(16);
                }


                else
                    Toast.makeText(Registro.this, "Ingresa una ciudad correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonCodigoPostal:
                EditText cCodigoPos= (EditText) findViewById(R.id.editTextCodigoPostal);
                String comCodigoPos = cCodigoPos.getText().toString();
                int lenghtCodPos = comCodigoPos.length();

                if(lenghtCodPos>=3) {
                    atras =16;
                    preview=17;
                    viewFlipper.setDisplayedChild(17);
                }

                else
                    Toast.makeText(Registro.this, "Ingresa un codigo postal correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonEstado:


                EditText cEstado= (EditText) findViewById(R.id.editTextEstado);
                String comEstado = cEstado.getText().toString();
                int lenghtEst = comEstado.length();
                if(lenghtEst>=2){
                    atras =17;
                    preview=22;
                    viewFlipper.setDisplayedChild(22);
                }
                else
                    Toast.makeText(Registro.this, "Ingresa un estado correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonDelegacion:
                EditText cDelegacion= (EditText) findViewById(R.id.editTextDelegacion);
                String comDelegacion = cDelegacion.getText().toString();
                int lenghtDel = comDelegacion.length();

                if(lenghtDel>=4){
                    InputMethodManager inputM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputM.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    premio();
                    sumaRegistros();
                    finish();
                }
                else
                    Toast.makeText(Registro.this, "Ingresa un Municipio o delegacion correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonTicket:

                EditText cTicket= (EditText) findViewById(R.id.editTextTicket);
                String comTicket = cTicket.getText().toString();
                int lenghtTicket = comTicket.length();

                if(lenghtTicket>=6) {
                    DataBase admin = new DataBase(this);
                    SQLiteDatabase bd = admin.getReadableDatabase();
                    String Query = "Select nticket from numeroTicket where nticket ="+comTicket;
                    Cursor cursor = bd.rawQuery(Query, null);
                    if (cursor.moveToFirst()) {
                     // record exists
                        Toast.makeText(Registro.this, "Este numero de ticket ya fue registrado.", Toast.LENGTH_SHORT).show();
                    } else {
                       // record not found
                        InputMethodManager inputManagerT = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManagerT.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        atras =22;
                        preview=27;
                        viewFlipper.setDisplayedChild(27);
                        btnCancelar.setVisibility(View.INVISIBLE);
                        btnAtras.setVisibility(View.INVISIBLE);
                    }



                }

                else
                    Toast.makeText(Registro.this, "Ingresa un numero de ticket correcto.", Toast.LENGTH_SHORT).show();

                break;

            case  R.id.buttonAtras:
                viewFlipper.setDisplayedChild(atras);
                break;
        }
    }

    public void alta(){

        String nombreSupervisor = supervisor.getNombreSupervisor();
        String ubicacionSupervisor = supervisor.getUbicacionSupervisor();
        String referenciaTiendita = supervisor.getTiendaSuper();

        //Todos los editText utilizados
        etNombre = (EditText) findViewById(R.id.editTextNombrePersona);
        etApellidos = (EditText) findViewById(R.id.editTextApellidosPersona);
        etApellidoMaterno = (EditText) findViewById(R.id.editTextApellidoMaterno);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etTelefono = (EditText) findViewById(R.id.editTextTelefono);
        etTelefonoSecundario = (EditText) findViewById(R.id.editTextTelefonoSecundario);
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
        etDelegacion = (EditText) findViewById(R.id.editTextDelegacion);
        etTicket =(EditText) findViewById(R.id.editTextTicket);

        String nombre = etNombre.getText().toString();
        String apellidos =  etApellidos.getText().toString();
        String apellidoMaterno = etApellidoMaterno.getText().toString();
        String email = etEmail.getText().toString();
        String telefono =etTelefono.getText().toString();
        String telefonoSecundario = etTelefonoSecundario.getText().toString();
        String dia = etDia.getText().toString();
        String mes = etMes.getText().toString();
        String ano = etAno.getText().toString();
        String frase = etFrase.getText().toString();
        String calle = etCalle.getText().toString();
        String noExterior  = etExterior.getText().toString();
        String noInterior = etInterior.getText().toString();
        String colonia = etColonia.getText().toString();
        String ciudad = etCiudad.getText().toString();
        String codigoPostal = etCodigoPostal.getText().toString();
        String estado = etEstado.getText().toString();
        String delegacion = etDelegacion.getText().toString();
        String ticket = etTicket.getText().toString();

        prueba = (TextView) findViewById(R.id.textPrueba);
        pruebaFin = (TextView) findViewById(R.id.textViewPruebaFin);
        baseDatos.abrir();
        baseDatos.insertarReg(nombreSupervisor, ubicacionSupervisor, referenciaTiendita, nombre, apellidos, apellidoMaterno, email,
                telefono, telefonoSecundario, dia, mes, ano, ticket, mCurrentPhotoPath, tiendaCompra);
        baseDatos.insertarTicket(ticket);
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
        etTicket.setText("");
        etApellidos.setText("");
        etDelegacion.setText("");
        etTelefonoSecundario.setText("");
        etApellidoMaterno.setText("");

        Toast.makeText(Registro.this,"Se guardo el registro.",Toast.LENGTH_SHORT).show();
    }

    public void premio(){
        //Todos los editText utilizados
        etFrase = (EditText) findViewById(R.id.editTextPersonalizacion);
        etCalle = (EditText) findViewById(R.id.editTextCalle);
        etExterior = (EditText) findViewById(R.id.editTextExterior);
        etInterior = (EditText) findViewById(R.id.editTextInterior);
        etColonia = (EditText) findViewById(R.id.editTextColonia);
        etCiudad = (EditText) findViewById(R.id.editTextCiudad);
        etCodigoPostal = (EditText) findViewById(R.id.editTextCodigoPostal);
        etEstado = (EditText) findViewById(R.id.editTextEstado);
        etDelegacion = (EditText) findViewById(R.id.editTextDelegacion);
        etTicket =(EditText) findViewById(R.id.editTextTicket);

        String frase = etFrase.getText().toString();
        String calle = etCalle.getText().toString();
        String noExterior  = etExterior.getText().toString();
        String noInterior = etInterior.getText().toString();
        String colonia = etColonia.getText().toString();
        String ciudad = etCiudad.getText().toString();
        String codigoPostal = etCodigoPostal.getText().toString();
        String estado = etEstado.getText().toString();
        String delegacion = etDelegacion.getText().toString();

        prueba = (TextView) findViewById(R.id.textPrueba);
        pruebaFin= (TextView) findViewById(R.id.textViewPruebaFin);
        baseDatos.abrir();
        baseDatos.premioReg(premio, medida, frase, calle, noExterior, noInterior, colonia, ciudad, delegacion, estado, codigoPostal);
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
        etTicket.setText("");
        etApellidos.setText("");
        etDelegacion.setText("");
        etTelefonoSecundario.setText("");
        etApellidoMaterno.setText("");

        Toast.makeText(Registro.this,"Se actualizo el registro.",Toast.LENGTH_SHORT).show();
    }

    public  void validacionFecha() {
        diaI = Integer.parseInt(diaS);
        mesI = Integer.parseInt(mesS);
        anoI = Integer.parseInt(anoS);

        if(anoI>=2000)
            Toast.makeText(Registro.this, "Solo mayores de 16 años pueden participar.", Toast.LENGTH_SHORT).show();


        else if (diaI > 0 && diaI < 32 && mesI > 0 && mesI<13 && anoI >1900 && anoI<2016) {
            viewFlipper.setDisplayedChild(18);
            atras =5;
        }

        else
            Toast.makeText(Registro.this, "Ingresa una fecha valida.", Toast.LENGTH_SHORT).show();

    }



    int lastCameraPhotoID;
    Uri mCurrentPhotoURI;
    String mCurrentPhotoPath;

    static final int REQUEST_TAKE_PHOTO = 501;

    private void dispatchTakePictureIntent() {
        mCurrentPhotoPath = "";
        mCurrentPhotoURI = null;
        lastCameraPhotoID = -1;

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));

                lastCameraPhotoID = getLastImageID();

                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                alta();

                Bitmap smallImage = decodeSampledBitmapAtPath(mCurrentPhotoPath, 1000,1000);
                OutputStream fOut = null;
                File file = new File(mCurrentPhotoPath); // the File to save to
                try {
                    fOut = new FileOutputStream(file);
                    smallImage.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                    fOut.flush();
                    fOut.close();
                } catch (IOException ex) {
                    System.out.println(">>> Error downsampling file. <<<");
                }

                if (lastCameraPhotoID >= 0) {
                    removeCameraPhotosAfterID(lastCameraPhotoID);
                }
            } else {
                mCurrentPhotoPath = "";
                mCurrentPhotoURI = null;
                Toast.makeText(Registro.this, "Debe agregar una imagen al ticket.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoURI = Uri.fromFile(image);
        return image;
    }

    public static Bitmap decodeSampledBitmapAtPath(String path, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private int getLastImageID() {
        final String[] imageColumns = { MediaStore.Images.Media._ID };
        final String imageOrderBy = MediaStore.Images.Media._ID + " DESC";
        final String imageWhere = null;
        final String[] imageArguments = null;
        Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns, imageWhere, imageArguments, imageOrderBy);
        if(imageCursor.moveToFirst()){
            return imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.Media._ID));
        }else{
            return 0;
        }
    }

    private void removeCameraPhotosAfterID(int lastPhotoID) {
        final String[] imageColumns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_TAKEN, MediaStore.Images.Media.SIZE, MediaStore.Images.Media._ID };
        final String imageOrderBy = MediaStore.Images.Media._ID + " DESC";
        final String imageWhere = MediaStore.Images.Media._ID + ">?";
        final String[] imageArguments = { Integer.toString(lastPhotoID) };
        Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns, imageWhere, imageArguments, imageOrderBy);
        if(imageCursor.getCount() > 0){
            while(imageCursor.moveToNext()){
                int id = imageCursor.getInt(imageCursor.getColumnIndex(MediaStore.Images.Media._ID));
                String path = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                Long size = imageCursor.getLong(imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE));
//                System.out.println(">>> CameraPhoto: " + path + " <<<");
                if(path.length() > 0){
                    // Remove it
                    ContentResolver cr = getContentResolver();
                    cr.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media._ID + "=?", new String[]{ Long.toString(id) } );
                    break;
                }
            }
        }
    }

    private void sumaRegistros()
    {
        totales +=1;
        nuevos +=1;
        SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = sharedPref.edit();
        sharedEditor.putInt("Nuevos", nuevos);
        sharedEditor.putInt("totales",totales);
        sharedEditor.commit();

    }

}
