package com.onestopinteractive.promocion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static android.provider.BaseColumns._ID;

public class Sync {
    public int recordsCount;
    public SyncListener mSyncListener;

    private DataBase dbAdmin;
    private SQLiteDatabase sqliteDB;

    private static Context _actionCtx;
    private static Sync _sharedInstance;
    private RequestQueue _requestQueue;

    public Sync(Context actionCtx) {
        super();

        _actionCtx = actionCtx;

        dbAdmin = new DataBase(_actionCtx);
        sqliteDB = dbAdmin.getWritableDatabase();
        recordsCount = 0;
    }

    public static synchronized Sync getSharedInstance(Context actionCtx) {
        if (_sharedInstance != null && actionCtx != _actionCtx) {
            _sharedInstance.dbAdmin.cerrar();
            _sharedInstance = null;
        }
        if (_sharedInstance == null) {
            _sharedInstance = new Sync(actionCtx);
        }
        return _sharedInstance;
    }

    public RequestQueue getRequestQueue() {
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(_actionCtx.getApplicationContext());
        }
        return _requestQueue;
    }

    public void close() {
        dbAdmin.cerrar();
    }

    public int countRecords() {
        Cursor row = sqliteDB.rawQuery("SELECT COUNT(" + _ID + ") FROM Registro;", null);
        if (row.moveToFirst()) {
            recordsCount = row.getInt(0);
        } else {
            recordsCount = 0;
        }
        return recordsCount;
    }

    public boolean hasRecordWithTicket(String ticket) {
        Cursor row;
        String status = "";
        row = sqliteDB.rawQuery("SELECT " + _ID + " FROM Registro WHERE numeroDeTicket = '" + ticket + "';", null);
        if (row.moveToFirst()) {
            return true;
        }
        return false;
    }

    public void deleteRecord(int recordID) {
        System.out.println("Deleting: " + Integer.toString(recordID));

        String imagePath = imagePathForRecord(recordID);
        if (imagePath != null && imagePath.length() > 0) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imageFile.delete();
                System.out.println("Deleted Image " + Integer.toString(recordID));
            }
        }

        sqliteDB.delete("Registro", _ID + " = " + Integer.toString(recordID), null);
    }

    private boolean shouldContinue;
    public void beginSync(SyncListener listener) {
        mSyncListener = listener;
        shouldContinue = (countRecords() > 0);

        System.out.println(">>> Begin Sync: " + Integer.toString(recordsCount) + "<<<");

        int recordID = nextRecord();
        if (recordID > 0) {
            mSyncListener.syncStarted(recordsCount);

            postRecord(nextRecord());
        }
    }

    public int nextRecord() {
        if (!shouldContinue) { return 0; }

        Cursor row = sqliteDB.rawQuery("SELECT " + _ID + " FROM Registro ORDER BY " + _ID + " ASC LIMIT 1", null);
        if (row.moveToFirst()) {
            return row.getInt(0);
        }
        return 0;
    }

    public void postRecord(int recordID) {
        uploadRecordPhoto(recordID);
    }

    public boolean uploadRecord(int recordID) {
        final int _recordID = recordID;
        final Map<String, String> params = paramsForRecord(recordID);
        if (params == null) { return false; }

        System.out.println(">>> Record: " + Integer.toString(recordID) + " <<<");

        StringRequest sr = new StringRequest(Request.Method.POST, "http://promococacola.azteca.click/api/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>" + response + "<<<");
                if (response.equals("OK")) {
                    deleteRecord(_recordID);

                    if (countRecords() > 0) {
                        mSyncListener.syncProgress(recordsCount);
                        postRecord(nextRecord());
                    } else {
                        mSyncListener.syncCompleted();
                    }
                } else {
                    shouldContinue = false;

                    mSyncListener.syncEndedWithError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(">>>" + error.toString() + "<<<");
                shouldContinue = false;
                mSyncListener.syncEndedWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        getRequestQueue().add(sr);
        return true;
    }

    public void uploadRecordPhoto(int recordID) {
        String fileName = imagePathForRecord(recordID);
        if (fileName == null || fileName.length() <= 0) {
            mSyncListener.syncEndedWithError(null);
            return;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println(">>> Image missing: " + file.getName() + " <<<");
            mSyncListener.syncEndedWithError(null);
        }

        System.out.println(">>> Image: " + Integer.toString(recordID) + " <<<");

        final int _recordID = recordID;

        ImageUploadRequest iur = new ImageUploadRequest("http://promococacola.azteca.click/api/upload.php", file, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>" + response + "<<<");
                if (response.equals("OK")) {
                    uploadRecord(_recordID);
                } else {
                    shouldContinue = false;

                    mSyncListener.syncEndedWithError(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(">>>" + error.toString() + "<<<");
                shouldContinue = false;
                mSyncListener.syncEndedWithError(error);
            }
        });

        getRequestQueue().add(iur);
        return;
    }

    public String imagePathForRecord(int recordID) {
        Cursor row = sqliteDB.rawQuery("SELECT imagenTicket FROM Registro WHERE " + _ID + " = " + Integer.toString(recordID) + ";", null);
        if (!row.moveToFirst()) {
            return null;
        }
        return row.getString(0);
    }

    public Map<String, String> paramsForRecord(int recordID) {
        Map<String, String> params = null;
        Cursor row;
        row = sqliteDB.rawQuery("SELECT nombreSupervisor,ubicacionSupervisor,nombre,apellidos,apellidoMaterno,email,telefono,telefonoSecundario,dia,mes,ano,numeroDeTicket,premio,medida,personalizacion,calle,noExterior,noInterior,colonia,ciudad,codigoPostal,estado,delegacion,timeStamp,tiendaReferencia,tiendaCompra,imagenTicket FROM Registro WHERE " + _ID + " = " + Integer.toString(recordID) + ";", null);
        if (row.moveToFirst()) {
            params = new HashMap<String, String>();
            params.put("supervisor", RowGetStringSafe(row, 0));
            params.put("location", RowGetStringSafe(row, 1));
            params.put("location_ref", RowGetStringSafe(row, 24));

            params.put("full_name", RowGetStringSafe(row, 2));
            params.put("surname", RowGetStringSafe(row, 3));
            params.put("surname_m", RowGetStringSafe(row, 4));
            params.put("email", RowGetStringSafe(row, 5));
            params.put("phone", RowGetStringSafe(row, 6));
            params.put("phone_alt", RowGetStringSafe(row, 7));
            params.put("bday", RowGetStringSafe(row, 8));
            params.put("bmonth", RowGetStringSafe(row, 9));
            params.put("byear", RowGetStringSafe(row, 10));
            params.put("ticket", RowGetStringSafe(row, 11));
            params.put("ticket_store", RowGetStringSafe(row, 25));

            String imageFullPath = RowGetStringSafe(row, 26);
            String[] imagePath = imageFullPath.split("/");
            params.put("ticket_image", imagePath[imagePath.length - 1]);

            params.put("prize", RowGetStringSafe(row, 12));
            params.put("prize_size", RowGetStringSafe(row, 13));
            params.put("prize_text", RowGetStringSafe(row, 14));
            params.put("prize_calle", RowGetStringSafe(row, 15));
            params.put("prize_nexterior", RowGetStringSafe(row, 16));
            params.put("prize_ninterior", RowGetStringSafe(row, 17));
            params.put("prize_colonia", RowGetStringSafe(row, 18));
            params.put("prize_ciudad", RowGetStringSafe(row, 19));
            params.put("prize_postal", RowGetStringSafe(row, 20));
            params.put("prize_estado", RowGetStringSafe(row, 21));
            params.put("prize_delegacion", RowGetStringSafe(row, 22));

            params.put("created_at", RowGetStringSafe(row, 23));
        }
        return params;
    }

    public String RowGetStringSafe(Cursor row, Integer pos) {
        String attrValue = row.getString(pos);
        if (attrValue == null) { attrValue = ""; }
        if (pos == 12 && attrValue == "") { attrValue = "vip"; } // Prize can't be empty
//        System.out.println(">>> Attr: " + Integer.toString(pos) + " - " + attrValue + " <<<");
        return attrValue;
    }
}