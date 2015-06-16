package com.onestopinteractive.promocion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.provider.BaseColumns._ID;


public class DataBase  extends SQLiteOpenHelper {




    public DataBase(Context ctx){

        super(ctx, "Mibase", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombreSupervisor TEXT, ubicacionSupervisor TEXT, nombre TEXT,apellidos TEXT,apellidoMaterno TEXT, email TEXT, telefono TEXT,telefonoSecundario TEXT,dia TEXT,mes TEXT,ano TEXT,numeroDeTicket TEXT,premio TEXT,medida TEXT,personalizacion TEXT,calle TEXT,noExterior TEXT,noInterior TEXT,colonia TEXT,ciudad TEXT,codigoPostal TEXT,estado TEXT,delegacion TEXT,timeStamp TEXT,tiendaReferencia TEXT,tiendaCompra TEXT)";

        db.execSQL(Query);
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Registro");
        db.execSQL("create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombreSupervisor TEXT, ubicacionSupervisor TEXT, nombre TEXT,apellidos TEXT,apellidoMaterno TEXT,email TEXT, telefono TEXT,telefonoSecundario TEXT,dia TEXT,mes TEXT,ano TEXT,numeroDeTicket TEXT,premio TEXT,medida TEXT,personalizacion TEXT,calle TEXT,noExterior TEXT,noInterior TEXT,colonia TEXT,ciudad TEXT,codigoPostal TEXT,estado TEXT,delegacion TEXT,timeStamp TEXT,tiendaReferencia  TEXT,tiendaCompra TEXT)");

    }







    public void insertarReg(String nombreSupervisor,String ubicacionSupervisor,
                            String nombre,String apellidos,String apellidoMaterno,String email,
                            String telefono,String telefonoSecundario,String dia,String mes,String ano,String numeroTicket,String premio,String medida,String personalizacion,String calle,String noExterior,String noInterior,String colonia,String ciudad,String delegacion,String codigoPostal
                          ,String estado,String referenciaTienda,String tiendaCompra)
    {
        ContentValues valores = new ContentValues();

        valores.put("nombreSupervisor",nombreSupervisor);
        valores.put("ubicacionSupervisor",ubicacionSupervisor);
        valores.put("nombre",nombre);
        valores.put("apellidos",apellidos);
        valores.put("apellidoMaterno",apellidoMaterno);
        valores.put("email",email);
        valores.put("telefono", telefono);
        valores.put("telefonoSecundario",telefonoSecundario);
        valores.put("dia",dia);
        valores.put("mes",mes);
        valores.put("ano",ano);
        valores.put("numeroDeTicket",numeroTicket);
        valores.put("premio",premio);
        valores.put("medida",medida);
        valores.put("personalizacion",personalizacion);
        valores.put("calle",calle);
        valores.put("noExterior",noExterior);
        valores.put("noInterior",noInterior);
        valores.put("colonia",colonia);
        valores.put("ciudad",ciudad);
        valores.put("codigoPostal",codigoPostal);
        valores.put("estado",estado);
        valores.put("delegacion",delegacion);
        valores.put("timeStamp",getDateTime());
        valores.put("tiendaReferencia",referenciaTienda);
        valores.put("tiendaCompra",tiendaCompra);
        this.getWritableDatabase().insert("Registro", null, valores);


    }

    private String getDateTime() {
        Date time = new Date();

        long unixTime = time.getTime() / 1000;

        String Date = Long.toString(unixTime);

        return  Date;
    }

    public  void abrir()
    {
        this.getWritableDatabase();
    }

    public void  cerrar()
    {
        this.close();
    }

}
