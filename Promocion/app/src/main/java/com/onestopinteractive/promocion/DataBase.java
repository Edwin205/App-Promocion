package com.onestopinteractive.promocion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;


public class DataBase  extends SQLiteOpenHelper {




    public DataBase(Context ctx){

        super(ctx,"Mibase",null,1);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "nombre TEXT, email TEXT, telefono TEXT)";
        db.execSQL(Query);
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Registro");
        db.execSQL("create table Registro(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "nombre TEXT, email TEXT, telefono TEXT)");
    }

    public void insertarReg(String nombre,String email, String telefono)
    {
        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("email",email);
        valores.put("telefono", telefono);
        this.getWritableDatabase().insert("Registro", null, valores);

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
        String columnas[] = {_ID,"nombre","email","telefono"};
        Cursor c = this.getReadableDatabase().query("Registro",columnas,null,null,null,null,null);
        c.moveToLast();
        int id,iu,ip,iq,ins,iubs;
        id = c.getColumnIndex(_ID);
        iu = c.getColumnIndex("nombre");
        ip = c.getColumnIndex("email");
        iq = c.getColumnIndex("telefono");


        result = c.getString(id)+" "+c.getString(iu)+" "+c.getString(ip)+" "+c.getString(iq);

        return result;


    }
}
