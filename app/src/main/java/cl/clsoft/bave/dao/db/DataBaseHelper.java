package cl.clsoft.bave.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import cl.clsoft.bave.InventarioApplication;
import cl.clsoft.bave.dao.catalogo.LocalizadorCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountEntriesCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountHeadersCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlOnhandQuantitiesCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoriesCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoryTagsCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalSubinventoriesCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlSystemItemsCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlTransactionsInterfaceCatalogo;
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

    public static synchronized DataBaseHelper getInstance() {
        if (sInstance == null) {
            sInstance = new DataBaseHelper(InventarioApplication.getAppContext());
        }
        return sInstance;
    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(OrganizacionCatalogo.CREATE_TABLE);
        db.execSQL(LocalizadorCatalogo.CREATE_TABLE);
        db.execSQL(SubinventarioCatalogo.CREATE_TABLE);
        db.execSQL(PoHeadersAllCatalogo.CREATE_TABLE);
        db.execSQL(PoLinesAllCatalogo.CREATE_TABLE);
        db.execSQL(PoLineLocationsAllCatalogo.CREATE_TABLE);
        db.execSQL(PoDistributionsAllCatalogo.CREATE_TABLE);
        db.execSQL(MtlSystemItemsCatalogo.CREATE_TABLE);
        db.execSQL(RcvHeadersInterfaceCatalogo.CREATE_TABLE);
        db.execSQL(RcvTransactionsInterfaceCatalogo.CREATE_TABLE);
        db.execSQL(RcvShipmentHeadersCatalogo.CREATE_TABLE);
        db.execSQL(RcvTransactionsCatalogo.CREATE_TABLE);
        db.execSQL(MtlTransactionsLotsIfaceCatalogo.CREATE_TABLE);
        db.execSQL(MtlOnhandQuantitiesCatalogo.CREATE_TABLE);
        db.execSQL(MtlCycleCountHeadersCatalogo.CREATE_TABLE);
        db.execSQL(MtlCycleCountEntriesCatalogo.CREATE_TABLE);
        db.execSQL(MtlPhysicalInventoriesCatalogo.CREATE_TABLE);
        db.execSQL(MtlPhysicalSubinventoriesCatalogo.CREATE_TABLE);
        db.execSQL(MtlPhysicalInventoryTagsCatalogo.CREATE_TABLE);
        db.execSQL(MtlTransactionsInterfaceCatalogo.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
