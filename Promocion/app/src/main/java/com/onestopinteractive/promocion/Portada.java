package com.onestopinteractive.promocion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
;


public class Portada extends ActionBarActivity implements View.OnClickListener {

    Button siguiente;
    ViewFlipper viewFlipper;
    ImageButton buttonSettings;
    Button buttonGuardar,btnCerrarSecion,btnSi,btnNo,btnInfo,btnAtras;
    ImageButton buttonSync,btnSyncInfo ;
    EditText superUbicacion,superNombre,porcentajeGanador;
    TextView nombreSuper,ubicacionSuper,etNuevo,etTotales,etSincronizados,etTimeStamp;

    int cantidadS,cantidadL;
    String porcentaje;

    int cant;
    int totales;
    int sincronizados;
    int nuevos;

    Button btnSelectienda,btnBodegaAhorrera,btnCasaLey,btnChedrahui,btnComercialMexicana,btnHEB,btnSoriana,btnSuperama,btnWalmart;
    TextView tVTiendaSelec;
    EditText etTiendaRef;
    TextView tvrefencia;
    EditText tallaS,tallaL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);

        tVTiendaSelec = (TextView) findViewById(R.id.textViewEleccionTienda);
        btnSelectienda = (Button) findViewById(R.id.buttonSelecTienda);
        btnBodegaAhorrera = (Button) findViewById(R.id.buttonBAurrera);
        btnCasaLey = (Button) findViewById(R.id.buttonCasaLey);
        btnChedrahui = (Button) findViewById(R.id.buttonChedrahui);
        btnComercialMexicana = (Button) findViewById(R.id.buttonCMexicana);
        btnHEB = (Button) findViewById(R.id.buttonHeb);
        btnSoriana = (Button) findViewById(R.id.buttonSoriana);
        btnSuperama = (Button) findViewById(R.id.buttonSuperama);
        btnWalmart = (Button) findViewById(R.id.buttonWalmart);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipperPortada);
        etNuevo = (TextView) findViewById(R.id.textViewNuevos);
        etTotales =(TextView) findViewById(R.id.textViewTotales);
        etSincronizados =(TextView) findViewById(R.id.textViewSincronizados);
        etTimeStamp = (TextView) findViewById(R.id.textViewUltima);
        btnNo = (Button) findViewById(R.id.buttonNOSeguro);
        btnSi = (Button) findViewById(R.id.buttonSiSeg);
        btnSyncInfo = (ImageButton) findViewById(R.id.buttonSyncInfo);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
        buttonSettings = (ImageButton) findViewById(R.id.buttonSettings);
        buttonSync = (ImageButton) findViewById(R.id.buttonSync);
        btnInfo = (Button) findViewById(R.id.buttonInfo);
        btnCerrarSecion = (Button) findViewById(R.id.buttonCerrarSesion);
        btnAtras = (Button) findViewById(R.id.buttonAtras) ;
        btnInfo.setOnClickListener(this);
        btnCerrarSecion.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
        buttonGuardar.setOnClickListener(this);
        buttonSync.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnSi.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        btnSyncInfo.setOnClickListener(this);
        btnSelectienda.setOnClickListener(this);
        btnBodegaAhorrera.setOnClickListener(this);
        btnCasaLey.setOnClickListener(this);
        btnChedrahui.setOnClickListener(this);
        btnComercialMexicana.setOnClickListener(this);
        btnHEB.setOnClickListener(this);
        btnSoriana.setOnClickListener(this);
        btnSuperama.setOnClickListener(this);
        btnWalmart.setOnClickListener(this);

        siguiente = (Button) findViewById(R.id.btnPortada);

            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                    ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
                    tvrefencia = (TextView) findViewById(R.id.textViewReferencia);

                    String ubicacionSuperv = ubicacionSuper.getText().toString();
                    String nombreSuperv = nombreSuper.getText().toString();
                    String referenciaSuperv = tvrefencia.getText().toString();
                    boolean darError = true;

                    if (nombreSuperv != "" && ubicacionSuperv != "")
                        darError = false;

                    else
                        Toast.makeText(Portada.this, "Ingresa tus datos antes de continuar.", Toast.LENGTH_SHORT).show();

                    if (darError == false) {

                        tallaS = (EditText) findViewById(R.id.editTextSmall);
                        tallaL = (EditText) findViewById(R.id.editTextLarge);
                        porcentajeGanador = (EditText) findViewById(R.id.editTextPorcentaje);


                        cantidadS = Integer.parseInt(tallaS.getText().toString());
                        cantidadL = Integer.parseInt(tallaL.getText().toString());
                        porcentaje = porcentajeGanador.getText().toString();




                        Intent intent = new Intent(Portada.this, Registro.class);




                        /*SharedPreferences sharedPref = getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
                        SharedPreferences.Editor sharedEditor = sharedPref.edit();
                        sharedEditor.putInt("playeraS", cantidadS);
                        sharedEditor.putInt("playeraM", cantidadL);
                        sharedEditor.commit();*/

                        //intent.putExtra("cantidadS", cantidadS);
                        //intent.putExtra("cantidadL", cantidadL);
                        intent.putExtra("porcentaje", porcentaje);
                        intent.putExtra("nombreSupervisor", nombreSuperv);
                        intent.putExtra("ubicacionSupervisor", ubicacionSuperv);
                        intent.putExtra("tiendaReferencia",referenciaSuperv);
                        startActivity(intent);
                    }

                }
            });



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonSelecTienda:

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnSelectienda.getWindowToken(), 0);
                viewFlipper.setDisplayedChild(4);

                break;

            case R.id.buttonBAurrera:
                btnSelectienda.setText("Bodega Aurrera");

                tVTiendaSelec.setText("Bodega Aurrera");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonCasaLey:
                btnSelectienda.setText("Casa Ley");

                tVTiendaSelec.setText("Casa Ley");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonChedrahui:
                btnSelectienda.setText("Chedrahui");

                tVTiendaSelec.setText("Chedrahui");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonCMexicana:
                btnSelectienda.setText("Comercial Mexicana");

                tVTiendaSelec.setText("Comercial Mexicana ");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonHeb:
                btnSelectienda.setText("HEB");

                tVTiendaSelec.setText("HEB");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonSoriana:
                btnSelectienda.setText("Soriana");

                tVTiendaSelec.setText("Soriana ");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonSuperama:
                btnSelectienda.setText("Superama");
                tVTiendaSelec.setText("Superama");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonWalmart:
                btnSelectienda.setText("Walmart");
                tVTiendaSelec.setText("Walmart");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonAtras:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonInfo:
                Sync osiSync = new Sync(this);
                if(osiSync.countRecords() > 0)
                {
                    Toast.makeText(this,"Sincroniza antes de verificar",Toast.LENGTH_SHORT).show();
                }
                else {

                    etTimeStamp.setText("Ultima sincronización:" + getDateTime());
                    etSincronizados.setText("Registros Sincronizados:" + sincronizados);
                    etNuevo.setText("Registros nuevos:"+0);
                    totales = sincronizados + nuevos;
                    etTotales.setText("Registros totales:" + totales);
                    if(sincronizados ==0)
                    {
                        Toast.makeText(this,"No hay datos que verificar",Toast.LENGTH_SHORT).show();
                    }
                    else
                    viewFlipper.setDisplayedChild(3);
                }
                break;

            case R.id.buttonSyncInfo:
                enviarRegistro();
                break;

            case R.id.buttonSync:
                enviarRegistro();

                break;
            case R.id.buttonSettings:
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonCerrarSesion:
                nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                String comprobacion = nombreSuper.getText().toString();

                if(comprobacion.equals(""))
                    Toast.makeText(this,"No hay ninguna sesión iniciada.",Toast.LENGTH_SHORT).show();

                else
                viewFlipper.setDisplayedChild(2);
                break;

            case R.id.buttonSiSeg:
                superNombre = (EditText) findViewById(R.id.editTextNombreSupervisor);
                superUbicacion = (EditText) findViewById(R.id.editTextUbicacion);
                nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
                porcentajeGanador = (EditText) findViewById(R.id.editTextPorcentaje);
                tallaL = (EditText) findViewById(R.id.editTextLarge);
                tallaS = (EditText) findViewById(R.id.editTextSmall);
                tVTiendaSelec.setText("");
                btnSelectienda.setText("Seleciona tienda");

                superNombre.setText("");
                superUbicacion.setText("");
                nombreSuper.setText("");
                ubicacionSuper.setText("");
                tallaS.setText("");
                tallaL.setText("");
                porcentajeGanador.setText("5");
                viewFlipper.setDisplayedChild(0);
                Toast.makeText(this,"Se cerro la sesión correctamente.",Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonNOSeguro:
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonGuardar:

                    etTiendaRef = (EditText) findViewById(R.id.editTextReferencia);
                    superNombre = (EditText) findViewById(R.id.editTextNombreSupervisor);
                    tVTiendaSelec = (TextView) findViewById(R.id.textViewEleccionTienda);
                    nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                    ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
                    tvrefencia = (TextView) findViewById(R.id.textViewReferencia);



                    String nombreSupervisor = superNombre.getText().toString();
                    String ubicacionSupervisor = tVTiendaSelec.getText().toString();
                    String referenciaSupervisor = etTiendaRef.getText().toString();


                    nombreSuper.setText(nombreSupervisor);
                    ubicacionSuper.setText(ubicacionSupervisor);
                    tvrefencia.setText(referenciaSupervisor);

                EditText validacionSm = (EditText) findViewById(R.id.editTextSmall);
                String validacionS = validacionSm.getText().toString();
                EditText validacionGr = (EditText) findViewById(R.id.editTextLarge);
                String validacionL = validacionGr.getText().toString();
                EditText validacionPo = (EditText) findViewById(R.id.editTextPorcentaje);
                String validacionP = validacionPo.getText().toString();


                int nSmall = Integer.parseInt(validacionS);
                int nMedium = Integer.parseInt(validacionL);

                boolean botonOff = true;
                int porcentaje = Integer.parseInt(validacionP);


                if(validacionS.equals("")) {
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }

                else if(validacionL.equals("")){
                    Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                    botonOff = false;
                }

                else if (porcentaje>10){
                    Toast.makeText(Portada.this, "Ingresa una probabilidad de 5 a 10 .", Toast.LENGTH_SHORT).show();
                    botonOff = false;
               }

                else if ( porcentaje<5){
                    Toast.makeText(Portada.this, "Ingresa una probabilidad de 5 a 10 .", Toast.LENGTH_SHORT).show();
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
                    SharedPreferences sharedPref = getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
                        SharedPreferences.Editor sharedEditor = sharedPref.edit();
                        sharedEditor.putInt("playeraS", nSmall);
                        sharedEditor.putInt("playeraM", nMedium);
                        sharedEditor.commit();
                  Toast.makeText(Portada.this, "Se guardo la sesión.", Toast.LENGTH_SHORT).show();
                  viewFlipper.setDisplayedChild(0);
              }


        }
    }


    Thread thread;

    private void enviarRegistro(){
        final Sync osiSync = new Sync(this);
        int recordsCount = osiSync.countRecords();

        if (recordsCount <= 0) {
            Toast.makeText(getApplicationContext(), "Todos los registros se han sincronizado correctamente.", Toast.LENGTH_SHORT).show();
            etTimeStamp.setText("Ultima sincronización:" + getDateTime());
            etSincronizados.setText("Registros Sincronizados:" + sincronizados);
            etNuevo.setText("Registros nuevos:"+0);
            totales = sincronizados + nuevos;
            etTotales.setText("Registros totales:"+totales);
            return;
        }

        Toast.makeText(getApplicationContext(), "Espere mientras se sincronizan " + Integer.toString(recordsCount) + " registros.", Toast.LENGTH_SHORT).show();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int recordID = osiSync.nextRecord();
                while (recordID > 0) {
                    if (osiSync.postRecord(recordID)) {
                        sincronizados += 1;
                        osiSync.deleteRecord(recordID);
                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Error al sincronizar los registros vuelva a intentar.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    }
                    recordID = osiSync.nextRecord();
                }

                if (osiSync.countRecords() <= 0) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Todos los registros se han sincronizado correctamente.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        thread.start();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
