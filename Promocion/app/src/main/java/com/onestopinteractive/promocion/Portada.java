package com.onestopinteractive.promocion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
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

import com.android.volley.VolleyError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
;


public class Portada extends ActionBarActivity implements View.OnClickListener, SyncListener {

    Button siguiente;
    ViewFlipper viewFlipper;
    ImageButton buttonSettings;
    Button buttonGuardar,btnCerrarSecion,btnSi,btnNo,btnInfo,btnAtras;
    ImageButton buttonSync,btnSyncInfo ;
    EditText superUbicacion,superNombre,porcentajeGanador;
    TextView nombreSuper,ubicacionSuper,etNuevo,etTotales,etSincronizados,etTimeStamp;
    
    String porcentaje;

    int totales;
    int sincronizados;
    int nuevos;

    Button btnSelectienda,btnBodegaAhorrera,btnCasaLey,btnChedrahui,btnComercialMexicana,btnHEB,btnSoriana,btnSuperama,btnWalmart;
    TextView tVTiendaSelec;
    EditText etTiendaRef;
    TextView tvrefencia;
    String time;
    String nombreSupervisor,ubicacionSupervisor,referenciaSupervisor,validacionP;
    EditText registrador,ubicacion,referencia,indice;
    String btnTienda;
    boolean sync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);
        sync=false;

        registrador = (EditText) findViewById(R.id.editTextNombreSupervisor);
        ubicacion = (EditText) findViewById(R.id.editTextUbicacion);
        referencia= (EditText) findViewById(R.id.editTextReferencia);
        indice = (EditText) findViewById(R.id.editTextPorcentaje);
        etTiendaRef =(EditText) findViewById(R.id.editTextReferencia);
        tVTiendaSelec = (TextView) findViewById(R.id.textViewEleccionTienda);
        nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
        ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
        tvrefencia = (TextView) findViewById(R.id.textViewReferencia);

        SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
        String regi=sharedPref.getString("registrador", "");
        String ubic=sharedPref.getString("ubicacion", "");
        String refe=sharedPref.getString("referencia", "");
        String indi=sharedPref.getString("indice", "2");

        if (ubic == "Selecciona tienda") {
            ubic = "";
        }

        registrador.setText(regi);
        ubicacion.setText(ubic);
        referencia.setText(refe);
        indice.setText(indi);

        nombreSuper.setText(regi);
        ubicacionSuper.setText(ubic);
        tvrefencia.setText(refe);

        if(sincronizados==0)
        {
            SharedPreferences sharedPrefsss=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
            sincronizados = sharedPref.getInt("sincronizados",0);
        }


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

        btnTienda= sharedPref.getString("btntienda", "Selecciona tienda");;
        btnSelectienda.setText(btnTienda);
        tVTiendaSelec = (TextView) findViewById(R.id.textViewEleccionTienda);
        tVTiendaSelec.setText(btnTienda);

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

                    if (ubicacionSuperv == "Selecciona tienda") {
                        ubicacionSuperv = "";
                    }

                    if (nombreSuperv != "" && ubicacionSuperv != "" && referenciaSuperv != "")
                        darError = false;

                    else
                        Toast.makeText(Portada.this, "Ingresa tus datos antes de continuar.", Toast.LENGTH_SHORT).show();

                    if (darError == false) {
                        porcentajeGanador = (EditText) findViewById(R.id.editTextPorcentaje);
                        porcentaje = porcentajeGanador.getText().toString();

                        Intent intent = new Intent(Portada.this, Registro.class);

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
                btnSetText("Bodega Aurrera");
                tVTiendaSelec.setText("Bodega Aurrera");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonCasaLey:
                btnSelectienda.setText("Casa Ley");
                btnSetText("Casa Ley");

                tVTiendaSelec.setText("Casa Ley");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonChedrahui:
                btnSelectienda.setText("Chedrahui");
                btnSetText("Chedrahui");

                tVTiendaSelec.setText("Chedrahui");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonCMexicana:
                btnSelectienda.setText("Comercial Mexicana");
                btnSetText("Comercial Mexicana");

                tVTiendaSelec.setText("Comercial Mexicana ");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonHeb:
                btnSelectienda.setText("HEB");
                btnSetText("HEB");

                tVTiendaSelec.setText("HEB");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonSoriana:
                btnSelectienda.setText("Soriana");
                btnSetText("Soriana");

                tVTiendaSelec.setText("Soriana ");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonSuperama:
                btnSelectienda.setText("Superama");
                btnSetText("Superama");
                tVTiendaSelec.setText("Superama");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonWalmart:
                btnSelectienda.setText("Walmart");
                btnSetText("Walmart");
                tVTiendaSelec.setText("Walmart");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonAtras:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonInfo:

                    SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
                    nuevos =  sharedPref.getInt("Nuevos",0);
                    totales = sharedPref.getInt("totales",0);

                    etSincronizados.setText("Registros Sincronizados:" + sincronizados);
                    etTimeStamp.setText("Ultima sincronización:" + setTimeStamp());
                    etNuevo.setText("Registros nuevos:"+nuevos);
                    etTotales.setText("Registros totales:"+totales);
                    viewFlipper.setDisplayedChild(3);

                break;

            case R.id.buttonSyncInfo:

                if(sync==false) {
                    enviarRegistro();
                }
                break;

            case R.id.buttonSync:

                SharedPreferences sharedPrefs=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
                nuevos =  sharedPrefs.getInt("Nuevos",0);
                totales = sharedPrefs.getInt("totales",0);

                etSincronizados.setText("Registros Sincronizados:" + sincronizados);
                etTimeStamp.setText("Ultima sincronización:" + setTimeStamp());
                etNuevo.setText("Registros nuevos:"+nuevos);
                etTotales.setText("Registros totales:"+totales);

                if(sync==false) {
                    enviarRegistro();
                }

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
                tVTiendaSelec.setText("");
                btnSelectienda.setText("Selecciona tienda");
                etTiendaRef.setText("");
                superNombre.setText("");
                superUbicacion.setText("");
                nombreSuper.setText("");
                ubicacionSuper.setText("");
                porcentajeGanador.setText("2");



                btnSetText("Selecciona tienda");
                SharedPreferences sharedPrefS=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
                SharedPreferences.Editor sharedEditorS = sharedPrefS.edit();
                sharedEditorS.putString("registrador", "");
                sharedEditorS.putString("ubicacion", "");
                sharedEditorS.putString("referencia", "");
                sharedEditorS.putString("indice", "2");
                sharedEditorS.commit();
                Toast.makeText(this,"Se cerro la sesión correctamente.",Toast.LENGTH_SHORT).show();
                viewFlipper.setDisplayedChild(0);

                break;

            case R.id.buttonNOSeguro:
                viewFlipper.setDisplayedChild(0);
                break;

            case R.id.buttonGuardar:



                    etTiendaRef = (EditText) findViewById(R.id.editTextReferencia);
                    superNombre = (EditText) findViewById(R.id.editTextNombreSupervisor);
                    tVTiendaSelec = (TextView) findViewById(R.id.textViewEleccionTienda);
                    nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                    ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
                    tvrefencia = (TextView) findViewById(R.id.textViewReferencia);
                     nombreSupervisor = superNombre.getText().toString();
                     ubicacionSupervisor = tVTiendaSelec.getText().toString();
                     referenciaSupervisor = etTiendaRef.getText().toString();
                    nombreSuper.setText(nombreSupervisor);
                    ubicacionSuper.setText(ubicacionSupervisor);
                    tvrefencia.setText(referenciaSupervisor);

                 EditText validacionPo = (EditText) findViewById(R.id.editTextPorcentaje);
                 validacionP = validacionPo.getText().toString();


                boolean botonOff = true;
                int porcentaje = Integer.parseInt(validacionP);



                 if (porcentaje > 10 || porcentaje < 2){
                    Toast.makeText(Portada.this, "Ingresa una probabilidad de 2 a 10 .", Toast.LENGTH_SHORT).show();
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
                 else if(referenciaSupervisor.equals(""))
                 {
                     Toast.makeText(Portada.this, "Ha dejado campos vacios.", Toast.LENGTH_SHORT).show();
                     botonOff = false;

                 }

                else
                  botonOff = true;

                if(botonOff == true) {
                    SharedPreferences sharedPrefss=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedEditor = sharedPrefss.edit();
                    sharedEditor.putString("registrador", nombreSupervisor);
                    sharedEditor.putString("ubicacion", ubicacionSupervisor);
                    sharedEditor.putString("referencia", referenciaSupervisor);
                    sharedEditor.putString("indice", validacionP);
                    sharedEditor.commit();
                  Toast.makeText(Portada.this, "Se guardo la sesión.", Toast.LENGTH_SHORT).show();

                  viewFlipper.setDisplayedChild(0);
              }


        }
    }


    Sync osiSync;
    private void enviarRegistro() {
        sync = true;
        osiSync = Sync.getSharedInstance(this);
        int recordsCount = osiSync.countRecords();

        if (recordsCount <= 0) {
            Toast.makeText(getApplicationContext(), "Todos los registros se han sincronizado correctamente.", Toast.LENGTH_SHORT).show();
            sync = false;
            return;
        }

        osiSync.beginSync(this);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String setTimeStamp()
    {
        SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
        time =  sharedPref.getString("timeStamp","");
        return time;
    }

    private void timeStamp() {
        SharedPreferences sharedPref=getSharedPreferences("promoSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = sharedPref.edit();
        sharedEditor.putString("timeStamp", getDateTime());
        sharedEditor.commit();

    }

    private void sincronizados()
    {
        SharedPreferences sharedPref=getSharedPreferences("promoSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = sharedPref.edit();
        sharedEditor.putInt("sincronizados", sincronizados);
        sharedEditor.commit();
    }
    private void borrarNuevos()
    {
        SharedPreferences sharedPref=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = sharedPref.edit();
        sharedEditor.putInt("Nuevos", nuevos);
        sharedEditor.commit();
    }

    public void btnSetText(String ubicacion)
    {
        SharedPreferences sharedPrefX=getSharedPreferences("promoSettings",Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = sharedPrefX.edit();
        sharedEditor.putString("btntienda", ubicacion);
        sharedEditor.commit();
    }

    ProgressDialog progressDialog;
    public void syncStarted(int count) {
        nuevos = count;

        progressDialog = ProgressDialog.show(this, "Sincronizar", "Espere mientras se sincronizan " + Integer.toString(nuevos) + " registros.", true);
    }
    public void syncProgress(int count) {
        sincronizados += 1;
        nuevos = count;

        progressDialog.setMessage("Espere mientras se sincronizan " + Integer.toString(nuevos) + " registros.");
    }
    public void syncCompleted() {
        etTimeStamp.setText("Ultima sincronización:" + getDateTime());
        etSincronizados.setText("Registros Sincronizados:" + sincronizados);
        etNuevo.setText("Registros nuevos:"+nuevos);
        sincronizados();
        borrarNuevos();
        timeStamp();

        progressDialog.setMessage("Todos los registros se han sincronizado correctamente.");
        dismissProgressDialog();

        sync = false;
    }
    public void syncEndedWithError(VolleyError error) {
        nuevos = osiSync.countRecords();

        etTimeStamp.setText("Ultima sincronización:" + getDateTime());
        etSincronizados.setText("Registros Sincronizados:" + sincronizados);
        etNuevo.setText("Registros nuevos:"+nuevos);
        sincronizados();
        borrarNuevos();
        timeStamp();

        progressDialog.setMessage("Error al sincronizar los registros. Vuelva a intentar.");
        dismissProgressDialog();

        sync = false;
    }

    public void dismissProgressDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 3000);
    }

}
