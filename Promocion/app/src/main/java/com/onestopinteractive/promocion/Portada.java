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
    EditText superUbicacion,superNombre,tallaS,tallaM,tallaL,porcentajeGanador;
    TextView nombreSuper,ubicacionSuper;
    String cantidadS,cantidadM,cantidadL,porcentaje;
    String nombre,email;
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

            case R.id.buttonSync:
                consulta();
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            request();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                break;
            case R.id.buttonSettings:
                borrarUltimoRegistro();
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


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private StringBuffer request() {
        // TODO Auto-generated method stub

        StringBuffer chaine = new StringBuffer("");
        try{

            String urlParameters  = "full_name="+nombre+"&email="+email+"&phone=6645298250&bday=20&bmonth=01&byear=1997";
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

    public  void consulta()
    {

        DataBase admin = new DataBase(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT nombre,email FROM Registro where _ID=(SELECT MAX(_ID) FROM Registro)",null);
        if(fila.moveToFirst())
        {
            nombre = fila.getString(0);
            email = fila.getString(1);
        }
        System.out.println(nombre);

        bd.close();

    }

    public  void borrarUltimoRegistro()
    {

        DataBase admin = new DataBase(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT _ID FROM Registro where _ID=(SELECT MAX(_ID) FROM Registro)", null);
        if(fila.moveToFirst()) {
            cant = bd.delete("Registro", "_ID=" + fila.getString(0), null);
        }
        if (cant ==1 )
        {
            Toast.makeText(this,"Se borraron los datos correctamente",Toast.LENGTH_SHORT).show();
        }


    }
}
