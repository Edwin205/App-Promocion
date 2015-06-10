package com.onestopinteractive.promocion;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
    Button btnNombre,btnApellidos,btnEmail,btnTelefono,btnFechaNaci,btnOtro,btnFinalizar
            ,btnOtroVip,btnFinalizarVIp,btnSuerte,btnS,btnL,btnSI
            ,btnNO,btnSigPersonalizada,btnDelegacion,btnCalle,btnExterior,btnInterior,btnColonia,btnCiudad,btnCodigoPostal,btnEstado,btnTicket,btnSifin,btnNOfin;

    EditText etNombre,etApellidos,etEmail,etTelefono,etDia,etMes,etAno,etFrase,etCalle,etExterior,etInterior,etColonia ,etCiudad,etCodigoPostal,etEstado,etDelegacion,etTicket;
    String nombreSupervisor;
    String ubicacionSupervisor,premio;

    Supervisor supervisor;
    TextView nombreSuper,ubicacionSuper,prueba,pruebaFin;
    Random ganadorAleatorio;
    int ganador;
    int cantidadSmall,cantidadLarge,porcentaje;
    DataBase baseDatos;
    TextView sqlprueba;
    int diaI,mesI,anoI;
    String diaS,mesS,anoS;
    String medida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseDatos = new DataBase(this);
        sqlprueba = (TextView) findViewById(R.id.textViewSQLprueba);

        medida = "";



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
        btnNOfin = (Button) findViewById(R.id.buttonNOSeguro);
        btnSifin = (Button) findViewById(R.id.buttonSiSeg);
        btnApellidos = (Button) findViewById(R.id.buttonApellidos);
        btnDelegacion = (Button) findViewById(R.id.buttonDelegacion);

        //El viewFlipper
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);


        Intent intent = getIntent();
        cantidadSmall = Integer.parseInt(intent.getStringExtra("cantidadS"));
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
        btnNOfin.setOnClickListener(this);
        btnSifin.setOnClickListener(this);
        btnApellidos.setOnClickListener(this);
        btnDelegacion.setOnClickListener(this);








    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.buttonNombre:
                EditText cNombre= (EditText) findViewById(R.id.editTextNombrePersona);
                String comNombre = cNombre.getText().toString();
                int length = comNombre.length();
                if(length>=4)
                    viewFlipper.setDisplayedChild(19);
                else
                    Toast.makeText(Registro.this, "Ingresa tu nombre completo.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonApellidos:

                EditText cApellidos= (EditText) findViewById(R.id.editTextApellidosPersona);
                String comApellidos = cApellidos.getText().toString();
                int lengtha = comApellidos.length();
                if(lengtha>=4)
                    viewFlipper.setDisplayedChild(1);
                else
                    Toast.makeText(Registro.this, "Ingresa tu apellido completo.", Toast.LENGTH_SHORT).show();

                break;

            case R.id.buttonEmail:

                EditText cEmail = (EditText) findViewById(R.id.editTextEmail);
                String comEmial = cEmail.getText().toString();
                int longitud = comEmial.length();
                char valor;

                for(char i = 0; i < longitud; i++)
                {
                    valor = comEmial.charAt(i);
                    if(valor == '@' && i == 0){
                        Toast.makeText(Registro.this, "Escribe un correo valido.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(valor == '@')
                    {
                        viewFlipper.setDisplayedChild(2);
                        break;
                    }
                    else if(i == longitud-1){
                        Toast.makeText(Registro.this, "Escribe un correo valido.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                break;

            case R.id.buttonTelefono:
                EditText cTelefono= (EditText) findViewById(R.id.editTextTelefono);
                String comTelefono = cTelefono.getText().toString();
                int lengthTelefono = comTelefono.length();

                if(lengthTelefono>=5)
                    viewFlipper.setDisplayedChild(4);

                else
                    Toast.makeText(Registro.this, "Ingresa un numero de telefono valido.", Toast.LENGTH_SHORT).show();
                break;


            case R.id.buttonFechaNaci:
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

                        ganadorAleatorio = new Random();
                        ganador = 1 + ganadorAleatorio.nextInt(porcentaje);

                        if (ganador == porcentaje) {
                            viewFlipper.setDisplayedChild(7);
                            premio = "playera";

                        } else {
                            viewFlipper.setDisplayedChild(6);
                            premio = "vip";
                            alta();
                        }


                break;

            case R.id.buttonOtro:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonFinalizar:
                viewFlipper.setDisplayedChild(18);

                break;
            case R.id.buttonNOSeguro:
                viewFlipper.setDisplayedChild(3);
                break;

            case R.id.buttonSiSeg:
                Intent intent = new Intent(Registro.this,Portada.class);
                startActivity(intent);
                break;

            case R.id.buttonVipOtro:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonVipFinalizar:
                viewFlipper.setDisplayedChild(18);

                break;

            case R.id.buttonSmall:

                medida = "S/M";
                if(cantidadSmall == 1) {
                    Toast.makeText(Registro.this, "Es la ultima playera M/S.", Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                    cantidadSmall -= 1;
                }

                else if(cantidadSmall<=0)
                    Toast.makeText(Registro.this,"Ya no quedan mas playeras M/S.",Toast.LENGTH_SHORT).show();

                else {
                    viewFlipper.setDisplayedChild(8);
                    Toast.makeText(Registro.this,"Quedan"+cantidadSmall+" "+"playeras S/M.",Toast.LENGTH_SHORT).show();
                    cantidadSmall -= 1;
                }

                break;


            case R.id.buttonLarge:
                medida="XL/L";


                if(cantidadLarge == 1) {
                    Toast.makeText(Registro.this, "Es la ultima playera XL/L.", Toast.LENGTH_SHORT).show();
                    viewFlipper.setDisplayedChild(8);
                    cantidadLarge -= 1;
                }

                else if(cantidadLarge<=0)
                    Toast.makeText(Registro.this,"Ya no quedan mas playeras XL/L.",Toast.LENGTH_SHORT).show();

                else {

                    viewFlipper.setDisplayedChild(8);
                    cantidadLarge -= 1;
                    Toast.makeText(Registro.this,"Quedan"+cantidadLarge+" "+"playeras XL/L.",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonSI:
                viewFlipper.setDisplayedChild(9);
                break;


            case R.id.buttonNO:
                viewFlipper.setDisplayedChild(3);

                alta();
                break;

            case R.id.buttonSigPersonalizacion:

                EditText cPersonaliza= (EditText) findViewById(R.id.editTextPersonalizacion);
                String comPersonaliza = cPersonaliza.getText().toString();
                int lengthPer = comPersonaliza.length();

                if(lengthPer>=4)
                    viewFlipper.setDisplayedChild(10);

                else
                    Toast.makeText(Registro.this, "Ingresa tu nombre completo.", Toast.LENGTH_SHORT).show();

                break;

            case R.id.buttonCalle:
                EditText cCalle= (EditText) findViewById(R.id.editTextCalle);
                String comCalle = cCalle.getText().toString();
                int lenghtCall = comCalle.length();

                if(lenghtCall>=4)
                    viewFlipper.setDisplayedChild(11);

                else
                    Toast.makeText(Registro.this, "Ingresa una calle correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonExterior:
                EditText cExterior= (EditText) findViewById(R.id.editTextExterior);
                String comExterior = cExterior.getText().toString();
                int lenghtExt = comExterior.length();

                if(lenghtExt>=1)
                    viewFlipper.setDisplayedChild(12);

                else
                    Toast.makeText(Registro.this, "Ingresa un numero correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonInterior:
                EditText cInterior= (EditText) findViewById(R.id.editTextInterior);
                String comInterior = cInterior.getText().toString();
                int lenghtInt = comInterior.length();

                if(lenghtInt>=1)
                    viewFlipper.setDisplayedChild(13);

                else
                    Toast.makeText(Registro.this, "Ingresa un numero correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonColonia:
                EditText cColonia= (EditText) findViewById(R.id.editTextColonia);
                String comColonia = cColonia.getText().toString();
                int lenghtCol = comColonia.length();

                if(lenghtCol>=5)
                    viewFlipper.setDisplayedChild(14);

                else
                    Toast.makeText(Registro.this, "Ingresa una colonia correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonCiudad:
                EditText cCiudad= (EditText) findViewById(R.id.editTextCiudad);
                String comCiudad = cCiudad.getText().toString();
                int lenghtCiu = comCiudad.length();

                if(lenghtCiu>=4)
                    viewFlipper.setDisplayedChild(15);

                else
                    Toast.makeText(Registro.this, "Ingresa una ciudad correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonCodigoPostal:
                EditText cCodigoPos= (EditText) findViewById(R.id.editTextCodigoPostal);
                String comCodigoPos = cCodigoPos.getText().toString();
                int lenghtCodPos = comCodigoPos.length();

                if(lenghtCodPos>=3)
                    viewFlipper.setDisplayedChild(16);

                else
                    Toast.makeText(Registro.this, "Ingresa un codigo postal correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonEstado:


                EditText cEstado= (EditText) findViewById(R.id.editTextEstado);
                String comEstado = cEstado.getText().toString();
                int lenghtEst = comEstado.length();
                if(lenghtEst>=4){
                    viewFlipper.setDisplayedChild(20);
                }
                else
                    Toast.makeText(Registro.this, "Ingresa un estado correcto.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonDelegacion:
                EditText cDelegacion= (EditText) findViewById(R.id.editTextEstado);
                String comDelegacion = cDelegacion.getText().toString();
                int lenghtDel = comDelegacion.length();

                if(lenghtDel>=4){
                    InputMethodManager inputM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputM.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    alta();
                    viewFlipper.setDisplayedChild(3);
                    Toast.makeText(Registro.this, "Se completo el registro.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Registro.this, "Ingresa un Municipio o delegacion correcta.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonTicket:

                EditText cTicket= (EditText) findViewById(R.id.editTextTicket);
                String comTicket = cTicket.getText().toString();
                int lenghtTicket = comTicket.length();

                if(lenghtTicket>=6)
                {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    viewFlipper.setDisplayedChild(5);
                }

                else
                    Toast.makeText(Registro.this, "Ingresa un numero de ticket correcto.", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    public void alta(){

        String nombreSupervisor = supervisor.getNombreSupervisor();
        String ubicacionSupervisor = supervisor.getUbicacionSupervisor();


        //Todos los editText utilizados
        etNombre = (EditText) findViewById(R.id.editTextNombrePersona);
        etApellidos = (EditText) findViewById(R.id.editTextApellidosPersona);
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
        etDelegacion = (EditText) findViewById(R.id.editTextDelegacion);
        etTicket =(EditText) findViewById(R.id.editTextTicket);

        String nombre = etNombre.getText().toString();
        String apellidos =  etApellidos.getText().toString();
        String email = etEmail.getText().toString();
        String telefono =etTelefono.getText().toString();
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
        pruebaFin= (TextView) findViewById(R.id.textViewPruebaFin);
        baseDatos.abrir();
        baseDatos.insertarReg(nombreSupervisor, ubicacionSupervisor, nombre, apellidos, email,
                telefono, dia, mes, ano, ticket, premio,medida,frase, calle, noExterior, noInterior, colonia, ciudad, codigoPostal, estado, delegacion);
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

        Toast.makeText(Registro.this,"Se guardo el registro.",Toast.LENGTH_SHORT).show();
    }

    public  void validacionFecha() {
        diaI = Integer.parseInt(diaS);
        mesI = Integer.parseInt(mesS);
        anoI = Integer.parseInt(anoS);

        if(anoI>2000)
            Toast.makeText(Registro.this, "Solo mayores de 16 años pueden participar.", Toast.LENGTH_SHORT).show();


        else if (diaI > 0 && diaI < 32 && mesI > 0 && mesI<13 && anoI >1900 && anoI<2016) {
            viewFlipper.setDisplayedChild(17);
        }

        else
            Toast.makeText(Registro.this, "Ingresa una fecha valida.", Toast.LENGTH_SHORT).show();




    }

}
