package com.onestopinteractive.promocion;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
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



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    EditText superUbicacion,superNombre,tallaS,tallaL,porcentajeGanador;
    TextView nombreSuper,ubicacionSuper,etNuevo,etTotales,etSincronizados,etTimeStamp;
    String cantidadS,cantidadL,porcentaje;
    String ubicacion,supervisor,nombre,apellidos,apellidoMaterno,email,telefono,telefonoSecundario,dia,mes,ano,
            ticket,premio,medida,personalizacion,calle,nExterior,nInterior,
    colonia,ciudad,estado,delegacion,postal,timestamp;
    int cant;
    int totales;
    int sincronizados;
    DataBase baseDatos;
    int nuevos;
    Button btnSelectienda,btnBodegaAhorrera,btnCasaLey,btnChedrahui,btnComercialMexicana,btnHEB,btnSoriana,btnSuperama,btnWalmart;
    TextView tVTiendaSelec;
    EditText etTiendaRef;
    TextView tvrefencia;
    String referencia;
    String tiendaCompra;

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


                        cantidadS = tallaS.getText().toString();
                        cantidadL = tallaL.getText().toString();
                        porcentaje = porcentajeGanador.getText().toString();




                        Intent intent = new Intent(Portada.this, Registro.class);

                        intent.putExtra("cantidadS", cantidadS);
                        intent.putExtra("cantidadL", cantidadL);
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

                viewFlipper.setDisplayedChild(4);

                break;

            case R.id.buttonBAurrera:
                btnSelectienda.setText("Bodega Aurrerá");

                tVTiendaSelec.setText("Bodega Aurrerá");
                viewFlipper.setDisplayedChild(1);
                break;

            case R.id.buttonCasaLey:
                btnSelectienda.setText("Casa ley");

                tVTiendaSelec.setText("Casa ley");
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
                if(consulta())
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

                else if ( porcentaje<1){
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
                  Toast.makeText(Portada.this, "Se guardo la sesión.", Toast.LENGTH_SHORT).show();
                  viewFlipper.setDisplayedChild(0);
              }


        }
    }


    Thread thread;

    private void enviarRegistro(){
        Toast.makeText(getApplicationContext(), "Espera mientras se sincroniza.", Toast.LENGTH_SHORT).show();
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    if(consulta()) {
                        String x = request().toString();
                        if (x.equals("OK")) {
                            sincronizados+=1;
                            borrarUltimoRegistro();
                            run();
                        } else {
                            //Error en el servidor
                            runOnUiThread(new Runnable()
                            {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Error al sincronizar los registros vuelva a intentar.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else{
                        // Cuando no hay nada en la BD
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Todos los registros se han sincronizado correctamente.", Toast.LENGTH_SHORT).show();
                                etTimeStamp.setText("Ultima sincronización:" + getDateTime());
                                etSincronizados.setText("Registros Sincronizados:" + sincronizados);
                                etNuevo.setText("Registros nuevos:"+0);
                                totales = sincronizados + nuevos;
                                etTotales.setText("Registros totales:"+totales);


                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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





    @TargetApi(Build.VERSION_CODES.KITKAT)
    private StringBuffer request() {
        // TODO Auto-generated method stub

        StringBuffer chaine = new StringBuffer("");
        try{

            String urlParameters  = "location="+ubicacion+"&location_ref="+referencia+"&supervisor="+supervisor+"&full_name="+nombre+"&surname="+apellidos+"&surname_m="+apellidoMaterno+"&email="+email+"&phone="+telefono+"&phone_alt="+telefonoSecundario+"&bday="+dia+"&bmonth="+mes+"&byear="+ano+"&ticket="+ticket+"&ticket_store="+tiendaCompra+"&prize="+premio+"&prize_size="+medida+"&prize_text="+personalizacion+"&prize_calle="+calle+"&prize_nexterior="+nExterior+"&prize_ninterior="+nInterior+"&prize_colonia="+colonia+"&prize_ciudad="+ciudad+"&prize_postal="+postal+"&prize_estado="+estado+"&prize_delegacion="+delegacion+"&created_at="+timestamp;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            URL url = new URL(" http://promococacola.azteca.click/api/register.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();


            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.write( postData );
            }

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }
        System.out.println(chaine);

        return chaine;

    }

    public boolean consulta() {
        boolean hayDatos = false;
        nombre = "";
        apellidos = "";
        apellidoMaterno = "";
        email = "";
        supervisor = "";
        ubicacion = "";
        telefono = "";
        telefonoSecundario = "";
        dia = "";
        mes = "";
        ano = "";
        ticket = "";
        premio = "";
        medida ="";
        personalizacion = "";
        calle = "";
        nExterior = "";
        nInterior= "";
        colonia = "";
        ciudad = "";
        postal= "";
        estado= "";
        delegacion = "";
        timestamp= "";

        DataBase admin = new DataBase(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT nombreSupervisor,ubicacionSupervisor,nombre,apellidos,apellidoMaterno,email,telefono,telefonoSecundario,dia,mes,ano,numeroDeTicket,premio,medida,personalizacion,calle,noExterior,noInterior,colonia,ciudad,codigoPostal,estado,delegacion,timeStamp,tiendaReferencia,tiendaCompra FROM Registro where _ID=(SELECT MAX(_ID) FROM Registro)",null);
        if(fila.moveToFirst()) {
            hayDatos = true;
            supervisor = fila.getString(0);
            ubicacion = fila.getString(1);
            nombre = fila.getString(2);
            apellidos = fila.getString(3);
            apellidoMaterno = fila.getString(4);
            email = fila.getString(5);
            telefono = fila.getString(6);
            telefonoSecundario = fila.getString(7);
            dia = fila.getString(8);
            mes = fila.getString(9);
            ano = fila.getString(10);
            ticket = fila.getString(1);
            premio = fila.getString(12);
            medida = fila.getString(13);
            personalizacion = fila.getString(14);
            calle = fila.getString(15);
            nExterior = fila.getString(16);
            nInterior = fila.getString(17);
            colonia = fila.getString(18);
            ciudad = fila.getString(19);
            postal = fila.getString(20);
            estado = fila.getString(21);
            delegacion = fila.getString(22);
            timestamp = fila.getString(23);
            referencia = fila.getString(24);
            tiendaCompra = fila.getString(25);


        }else{
            hayDatos = false;
        }
        System.out.println(supervisor+" "+ubicacion+" "+referencia+" "+nombre+" "+apellidos+" "+apellidoMaterno+" "+email+" "+telefono+" "+telefonoSecundario+" "+dia+" "+mes+" "+ano+" "+ticket+" "+tiendaCompra+" "+premio+" "+medida+" "+personalizacion+" "+calle+" "+nExterior+" "+nInterior+" "+colonia+" "+ciudad+" "+postal+" "+estado+" "+delegacion+" "+timestamp);



        bd.close();
        return hayDatos;
    }


    public  void borrarUltimoRegistro()
    {

        DataBase admin = new DataBase(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT _ID FROM Registro where _ID=(SELECT MAX(_ID) FROM Registro)", null);
        if(fila.moveToFirst()) {
            cant = bd.delete("Registro", "_ID=" + fila.getString(0), null);
        }
    }
}
