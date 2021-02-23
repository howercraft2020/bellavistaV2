package cl.clsoft.bave.dao.rowmapper;

import java.sql.SQLException;
import android.database.Cursor;

import cl.clsoft.bave.dao.catalogo.DatosCabeceraRecepcionCatalogo;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;

public class DatosCabeceraRecepcionRowMapper implements RowMapper<DatosCabeceraRecepcion>
{
    public DatosCabeceraRecepcion mapRow(final Cursor cursor, final int position) throws SQLException {
        final DatosCabeceraRecepcion entity = new DatosCabeceraRecepcion();
        entity.setUserId(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_USER_ID))));
        entity.setReceiptNum(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_USER_ID))));
        entity.setVendorId(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_VENDOR_ID))));
        entity.setVendorSiteCode(cursor.getString(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_VENDOR_SITE_CODE)));
        entity.setVendorSiteId(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_VENDOR_SITE_CODE))));
        entity.setCurrencyCode(cursor.getString(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_CURRENCY_CODE)));
        entity.setRateType(cursor.getString(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_RATE_TYPE)));
        entity.setRate(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_RATE))));
        entity.setRateDate(cursor.getString(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_RATE_DATE)));
        entity.setTermsId(Long.valueOf(cursor.getLong(cursor.getColumnIndex(DatosCabeceraRecepcionCatalogo.COLUMN_TERMS_ID))));
        return entity;
    }
}