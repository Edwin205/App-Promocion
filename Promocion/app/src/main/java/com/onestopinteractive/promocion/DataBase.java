package com.onestopinteractive.promocion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
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
                + "nombreSupervisor TEXT, ubicacionSupervisor TEXT, nombre TEXT, email TEXT, telefono TEXT," +
                "fechaNacimiento TEXT,numeroDeTicket TEXT,premio TEXT,personalizada TEXT,personalizacion TEXT,calle TEXT,noExterior TEXT,noInterior TEXT,colonia TEXT,ciudad TEXT,codigoPostal TEXT,estado TEXT,timeStamp TEXT)";

        db.execSQL(Query);
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Registro");
        db.execSQL("create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombreSupervisor TEXT, ubicacionSupervisor TEXT, nombre TEXT, email TEXT, telefono TEXT," +
                "fechaNacimiento TEXT,numeroDeTicket TEXT,premio TEXT,personalizada TEXT,personalizacion TEXT,calle TEXT,noExterior TEXT,noInterior TEXT,colonia TEXT,ciudad TEXT,codigoPostal TEXT,estado TEXT,timeStamp TEXT)");

    }

    public void insertarReg(String nombreSupervisor,String ubicacionSupervisor,
                            String nombre,String email,
                            String telefono,String fechaNacimiento,String numeroTicket,String premio,String personalizada,String personalizacion,String calle,String noExterior,String noInterior,String colonia,String ciudad,String codigoPostal
                          ,String estado)
    {
        ContentValues valores = new ContentValues();

        valores.put("nombreSupervisor",nombreSupervisor);
        valores.put("ubicacionSupervisor",ubicacionSupervisor);
        valores.put("nombre",nombre);
        valores.put("email",email);
        valores.put("telefono", telefono);
        valores.put("fechaNacimiento",fechaNacimiento);
        valores.put("numeroDeTicket",numeroTicket);
        valores.put("premio",premio);
        valores.put("personalizada",personalizada);
        valores.put("personalizacion",personalizacion);
        valores.put("calle",calle);
        valores.put("noExterior",noExterior);
        valores.put("noInterior",noInterior);
        valores.put("colonia",colonia);
        valores.put("ciudad",ciudad);
        valores.put("codigoPostal",codigoPostal);
        valores.put("estado",estado);
        valores.put("timeStamp",getDateTime());
        this.getWritableDatabase().insert("Registro", null, valores);


    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
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
