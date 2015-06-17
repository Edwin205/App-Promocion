package com.onestopinteractive.promocion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import static android.provider.BaseColumns._ID;

public class Sync {
    public DataBase dbAdmin;
    public SQLiteDatabase sqliteDB;
    public int recordsCount;

    public Sync(Context ctx) {
        super();

        dbAdmin = new DataBase(ctx);
        sqliteDB = dbAdmin.getWritableDatabase();
        recordsCount = 0;
    }

    public void close() {
        dbAdmin.cerrar();
    }

    public int countRecords() {
        Cursor row = sqliteDB.rawQuery("SELECT COUNT(" + _ID + ") FROM Registro;", null);
        if (row.moveToFirst()) {
            return row.getInt(0);
        }
        return 0;
    }

    public int nextRecord() {
        Cursor row = sqliteDB.rawQuery("SELECT " + _ID + " FROM Registro ORDER BY " + _ID + " ASC LIMIT 1", null);
        if (row.moveToFirst()) {
            return row.getInt(0);
        }
        return 0;
    }

    public void deleteRecord(int recordID) {
        System.out.println("Deleting: " + Integer.toString(recordID));
        sqliteDB.delete("Registro", _ID + " = " + Integer.toString(recordID), null);
    }

    public boolean postRecord(int recordID) {
        String uploadID = uploadRecordPhoto(recordID);
        if (uploadID.length() == 0) { return false; }

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://promococacola.azteca.click/api/register.php");

        Cursor row;
        row = sqliteDB.rawQuery("SELECT nombreSupervisor,ubicacionSupervisor,nombre,apellidos,apellidoMaterno,email,telefono,telefonoSecundario,dia,mes,ano,numeroDeTicket,premio,medida,personalizacion,calle,noExterior,noInterior,colonia,ciudad,codigoPostal,estado,delegacion,timeStamp,tiendaReferencia,tiendaCompra FROM Registro WHERE " + _ID + " = " + Integer.toString(recordID) + ";", null);
        if (row.moveToFirst()) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("supervisor", row.getString(0)));
            nameValuePairs.add(new BasicNameValuePair("location", row.getString(1)));
            nameValuePairs.add(new BasicNameValuePair("location_ref", row.getString(24)));

            nameValuePairs.add(new BasicNameValuePair("full_name", row.getString(2)));
            nameValuePairs.add(new BasicNameValuePair("surname", row.getString(3)));
            nameValuePairs.add(new BasicNameValuePair("surname_m", row.getString(4)));
            nameValuePairs.add(new BasicNameValuePair("email", row.getString(5)));
            nameValuePairs.add(new BasicNameValuePair("phone", row.getString(6)));
            nameValuePairs.add(new BasicNameValuePair("phone_alt", row.getString(7)));
            nameValuePairs.add(new BasicNameValuePair("bday", row.getString(8)));
            nameValuePairs.add(new BasicNameValuePair("bmonth", row.getString(9)));
            nameValuePairs.add(new BasicNameValuePair("byear", row.getString(10)));
            nameValuePairs.add(new BasicNameValuePair("ticket", row.getString(11)));
            nameValuePairs.add(new BasicNameValuePair("ticket_store", row.getString(25)));
            nameValuePairs.add(new BasicNameValuePair("ticket_image", uploadID));

            nameValuePairs.add(new BasicNameValuePair("prize", row.getString(12)));
            nameValuePairs.add(new BasicNameValuePair("prize_size", row.getString(13)));
            nameValuePairs.add(new BasicNameValuePair("prize_text", row.getString(14)));
            nameValuePairs.add(new BasicNameValuePair("prize_calle", row.getString(15)));
            nameValuePairs.add(new BasicNameValuePair("prize_nexterior", row.getString(16)));
            nameValuePairs.add(new BasicNameValuePair("prize_ninterior", row.getString(17)));
            nameValuePairs.add(new BasicNameValuePair("prize_colonia", row.getString(18)));
            nameValuePairs.add(new BasicNameValuePair("prize_ciudad", row.getString(19)));
            nameValuePairs.add(new BasicNameValuePair("prize_postal", row.getString(20)));
            nameValuePairs.add(new BasicNameValuePair("prize_estado", row.getString(21)));
            nameValuePairs.add(new BasicNameValuePair("prize_delegacion", row.getString(22)));

            nameValuePairs.add(new BasicNameValuePair("created_at", row.getString(23)));
            System.out.println("CreatedAt: " + row.getString(23));

            try {
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpclient.execute(httppost);
                HttpEntity httpEntity = httpResponse.getEntity();
                String status = EntityUtils.toString(httpEntity);
                System.out.println(">>>" + status + "<<<");
                if (status.equals("OK")) {
                    System.out.println("DidSyncRecord: " + Integer.toString(recordID));
                    return true;
                }
            } catch (ClientProtocolException e) {
                // process execption
                System.out.println("SyncRecordCPEx: " + Integer.toString(recordID));
                return false;
            } catch (java.io.IOException e) {
                // process execption
                System.out.println("SyncRecordEx: " + Integer.toString(recordID));
                return false;
            }
        }
        System.out.println("SyncRecordError: " + Integer.toString(recordID));
        return false;
    }

    public String uploadRecordPhoto(int recordID) {
        Cursor row;
        String status = "";
        row = sqliteDB.rawQuery("SELECT imagenTicket FROM Registro WHERE " + _ID + " = " + Integer.toString(recordID) + ";", null);
        if (row.moveToFirst()) {
            status = row.getString(0);
        }
        return status;
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost("http://promococacola.azteca.click/api/upload.php");
//
//        Cursor row;
//        String status = "";
//        row = sqliteDB.rawQuery("SELECT imagenTicket FROM Registro WHERE " + _ID + " = " + Integer.toString(recordID) + ";", null);
//        if (row.moveToFirst()) {
//            if (row.getInt(0) != recordID) { return ""; }
//
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//
//            final File file = new File(fileName);
//            FileBody fb = new FileBody(file);
//
//            builder.addPart("file", fb);
//            final HttpEntity mpEntity = builder.build();
//
//            try {
//                httppost.setEntity(mpEntity);
//
//                HttpResponse httpResponse = httpclient.execute(httppost);
//                HttpEntity httpEntity = httpResponse.getEntity();
//                status = EntityUtils.toString(httpEntity);
//                System.out.println(">>>" + status + "<<<");
//                if (status.equals("ERROR")) {
//                    System.out.println("UploadRecordError: " + Integer.toString(recordID));
//                    return "";
//                }
//            } catch (ClientProtocolException e) {
//                // process execption
//                System.out.println("UploadRecordCPEx: " + Integer.toString(recordID));
//                return "";
//            } catch (java.io.IOException e) {
//                // process execption
//                System.out.println("UploadRecordEx: " + Integer.toString(recordID));
//                return "";
//            }
//        }
//        System.out.println("UploadRecord: " + Integer.toString(recordID));
//        return status;
    }
}