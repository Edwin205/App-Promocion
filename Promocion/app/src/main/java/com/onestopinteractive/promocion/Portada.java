package com.onestopinteractive.promocion;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
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
;


public class Portada extends ActionBarActivity implements View.OnClickListener {

    Button siguiente;
    ViewFlipper viewFlipper;
    ImageButton buttonSettings;
    Button buttonGuardar;
    ImageButton buttonSync ;
    EditText superUbicacion,superNombre,tallaS,tallaL,porcentajeGanador;
    TextView nombreSuper,ubicacionSuper;
    String cantidadS,cantidadL,porcentaje;
    String ubicacion,supervisor,nombre,apellidos,email,telefono,dia,mes,ano,
            ticket,premio,medida,personalizacion,calle,nExterior,nInterior,
    colonia,ciudad,estado,delegacion,postal,timestamp;
    int cant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);






        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipperPortada);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
        buttonSettings = (ImageButton) findViewById(R.id.buttonSettings);
        buttonSync = (ImageButton) findViewById(R.id.buttonSync);
        buttonSettings.setOnClickListener(this);
        buttonGuardar.setOnClickListener(this);
        buttonSync.setOnClickListener(this);

        siguiente = (Button) findViewById(R.id.btnPortada);

            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nombreSuper = (TextView) findViewById(R.id.textviewSuperNombre);
                    ubicacionSuper = (TextView) findViewById(R.id.textViewSuperUbicacion);
                    String ubicacionSuperv = ubicacionSuper.getText().toString();
                    String nombreSuperv = nombreSuper.getText().toString();
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
                        startActivity(intent);
                    }

                }
            });



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



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonSync:
                enviarRegistro();


                break;
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
                EditText validacionGr = (EditText) findViewById(R.id.editTextLarge);
                String validacionL = validacionGr.getText().toString();
                EditText validacionPo = (EditText) findViewById(R.id.editTextPorcentaje);
                String validacionP = validacionPo.getText().toString();
                boolean botonOff = true;


                if(validacionS.equals("")) {
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


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private StringBuffer request() {
        // TODO Auto-generated method stub

        StringBuffer chaine = new StringBuffer("");
        try{

            String urlParameters  = "location="+ubicacion+"&supervisor="+supervisor+"&full_name="+nombre+"&surname="+apellidos+"&email="+email+"&phone="+telefono+"&bday="+dia+"&bmonth="+mes+"&byear="+ano+"&ticket="+ticket+"&prize="+premio+"&prize_size="+medida+"&prize_text="+personalizacion+"&prize_calle="+calle+"&prize_nexterior="+nExterior+"&prize_ninterior="+nInterior+"&prize_colonia="+colonia+"&prize_ciudad="+ciudad+"&prize_postal="+postal+"&prize_estado="+estado+"&prize_delegacion="+delegacion+"&created_at="+timestamp;
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
        email = "";
        supervisor = "";
        ubicacion = "";
        telefono = "";
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
        Cursor fila = bd.rawQuery("SELECT nombreSupervisor,ubicacionSupervisor,nombre,apellidos,email,telefono,dia,mes,ano,numeroDeTicket,premio,medida,personalizacion,calle,noExterior,noInterior,colonia,ciudad,codigoPostal,estado,delegacion,timeStamp FROM Registro where _ID=(SELECT MAX(_ID) FROM Registro)",null);
        if(fila.moveToFirst()) {
            hayDatos = true;
            supervisor = fila.getString(0);
            ubicacion = fila.getString(1);
            nombre = fila.getString(2);
            apellidos = fila.getString(3);
            email = fila.getString(4);
            telefono = fila.getString(5);
            dia = fila.getString(6);
            mes = fila.getString(7);
            ano = fila.getString(8);
            ticket = fila.getString(9);
            premio = fila.getString(10);
            medida = fila.getString(11);
            personalizacion = fila.getString(12);
            calle = fila.getString(13);
            nExterior = fila.getString(14);
            nInterior = fila.getString(15);
            colonia = fila.getString(16);
            ciudad = fila.getString(17);
            postal = fila.getString(18);
            estado = fila.getString(19);
            delegacion = fila.getString(20);
            timestamp = fila.getString(21);


        }else{
            hayDatos = false;
        }
        System.out.println(supervisor+" "+ubicacion+" "+nombre+" "+apellidos+" "+email+" "+telefono+" "+dia+" "+mes+" "+ano+" "+ticket+" "+premio+" "+medida+" "+personalizacion+" "+calle+" "+nExterior+" "+nInterior+" "+colonia+" "+ciudad+" "+postal+" "+estado+" "+delegacion+" "+timestamp);



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
