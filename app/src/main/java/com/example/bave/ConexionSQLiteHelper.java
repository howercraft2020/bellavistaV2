package com.example.bave;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bave.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_ORGANIZACION);
        db.execSQL(Utilidades.CREAR_TABLA_LOCALIZADOR);
        db.execSQL(Utilidades.CREAR_TABLA_SUBINVENTARIO);
        db.execSQL(Utilidades.CREAR_TABLA_PO_HEADERS_ALL);
        db.execSQL(Utilidades.CREAR_TABLA_PO_LINES_ALL);
        db.execSQL(Utilidades.CREAR_TABLA_PO_LINE_LOCATIONS_ALL);
        db.execSQL(Utilidades.CREAR_TABLA_PO_DISTRIBUTIONS_ALL);
        db.execSQL(Utilidades.CREAR_TABLA_MTL_SYSTEM_ITEMS);
        db.execSQL(Utilidades.CREAR_TABLA_RCV_HEADERS_INTERFACE);
        db.execSQL(Utilidades.CREAR_TABLA_RCV_TRANSACTIONS_INTERFACE);
        db.execSQL(Utilidades.CREAR_TABLA_RCV_SHIPMENT_HEADERS);
        db.execSQL(Utilidades.CREAR_TABLA_RCV_TRANSACTIONS);
        db.execSQL(Utilidades.CREAR_TABLA_MTL_TRANSACTIONS_LOTS_IFACE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS organizacion");
        db.execSQL("DROP TABLE IF EXISTS localizador");
        db.execSQL("DROP TABLE IF EXISTS subinventario");
        onCreate(db);

    }
}
