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

        super(ctx,"Mibase",null,1);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombreSupervisor TEXT, ubicacionSupervisor TEXT, nombre TEXT, email TEXT, telefono TEXT,fechaNacimiento TEXT,timeStamp TEXT)";
        db.execSQL(Query);
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Registro");
        db.execSQL("create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombreSupervisor TEXT, ubicacionSupervisor TEXT, nombre TEXT, email TEXT, telefono TEXT,fechaNacimiento TEXT,timeStamp TEXT)");
    }

    public void insertarReg(String nombreSupervisor,String ubicacionSupervisor, String nombre,String email, String telefono,String fechaNacimiento)
    {
        ContentValues valores = new ContentValues();

        valores.put("nombreSupervisor",nombreSupervisor);
        valores.put("ubicacionSupervisor",ubicacionSupervisor);
        valores.put("nombre",nombre);
        valores.put("email",email);
        valores.put("telefono", telefono);
        valores.put("fechaNacimiento",fechaNacimiento);
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

    public String leer()
    {
        String result = "";
        String columnas[] = {_ID,"nombreSupervisor","ubicacionSupervisor","nombre","email","telefono,fechaNacimiento,timeStamp"};
        Cursor c = this.getReadableDatabase().query("Registro",columnas,null,null,null,null,null);
        c.moveToLast();
        int id,iu,ip,iq,ins,iubs,ifn,its;
        id = c.getColumnIndex(_ID);
        ins = c.getColumnIndex("nombreSupervisor");
        iubs = c.getColumnIndex("ubicacionSupervisor");
        iu = c.getColumnIndex("nombre");
        ip = c.getColumnIndex("email");
        iq = c.getColumnIndex("telefono");
        ifn = c.getColumnIndex("fechaNacimiento");
        its = c.getColumnIndex("timeStamp");


        result = c.getString(id)+" "+c.getString(ins)+" "+c.getString(iubs)+" "+c.getString(iu)+" "+c.getString(ip)+" "+c.getString(iq)
        +" "+c.getString(ifn)+" "+c.getString(its);

        return result;


    }
}
