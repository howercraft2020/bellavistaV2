package cl.clsoft.bave.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bave.utilidades.Utilidades;

import cl.clsoft.bave.dao.catalogo.LocalizadorCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlSystemItemsCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlTransactionsLotsIfaceCatalogo;
import cl.clsoft.bave.dao.catalogo.OrganizacionCatalogo;
import cl.clsoft.bave.dao.catalogo.PoDistributionsAllCatalogo;
import cl.clsoft.bave.dao.catalogo.PoHeadersAllCatalogo;
import cl.clsoft.bave.dao.catalogo.PoLineLocationsAllCatalogo;
import cl.clsoft.bave.dao.catalogo.PoLinesAllCatalogo;
import cl.clsoft.bave.dao.catalogo.RcvHeadersInterfaceCatalogo;
import cl.clsoft.bave.dao.catalogo.RcvShipmentHeadersCatalogo;
import cl.clsoft.bave.dao.catalogo.RcvTransactionsCatalogo;
import cl.clsoft.bave.dao.catalogo.RcvTransactionsInterfaceCatalogo;
import cl.clsoft.bave.dao.catalogo.SubinventarioCatalogo;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "invetarioDatabase";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHelper sInstance;

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(OrganizacionCatalogo.CREAR_TABLA);
        db.execSQL(LocalizadorCatalogo.CREAR_TABLA);
        db.execSQL(SubinventarioCatalogo.CREAR_TABLA);
        db.execSQL(PoHeadersAllCatalogo.CREAR_TABLA);
        db.execSQL(PoLinesAllCatalogo.CREAR_TABLA);
        db.execSQL(PoLineLocationsAllCatalogo.CREAR_TABLA);
        db.execSQL(PoDistributionsAllCatalogo.CREAR_TABLA);
        db.execSQL(MtlSystemItemsCatalogo.CREAR_TABLA);
        db.execSQL(RcvHeadersInterfaceCatalogo.CREAR_TABLA);
        db.execSQL(RcvTransactionsInterfaceCatalogo.CREAR_TABLA);
        db.execSQL(RcvShipmentHeadersCatalogo.CREAR_TABLA);
        db.execSQL(RcvTransactionsCatalogo.CREAR_TABLA);
        db.execSQL(MtlTransactionsLotsIfaceCatalogo.CREAR_TABLA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
