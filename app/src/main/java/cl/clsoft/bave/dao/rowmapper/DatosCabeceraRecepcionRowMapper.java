package cl.clsoft.bave.dao.rowmapper;

import java.sql.SQLException;
import android.database.Cursor;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;

public class DatosCabeceraRecepcionRowMapper implements RowMapper<DatosCabeceraRecepcion>
{
    public DatosCabeceraRecepcion mapRow(final Cursor cursor, final int position) throws SQLException {
        final DatosCabeceraRecepcion entity = new DatosCabeceraRecepcion();
        entity.setUserId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("user_id"))));
        entity.setReceiptNum(Long.valueOf(cursor.getLong(cursor.getColumnIndex("receipt_num"))));
        entity.setVendorId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("vendor_id"))));
        entity.setVendorSiteCode(cursor.getString(cursor.getColumnIndex("vendor_site_code")));
        entity.setVendorSiteId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("vendor_site_id"))));
        entity.setCurrencyCode(cursor.getString(cursor.getColumnIndex("currency_code")));
        entity.setRateType(cursor.getString(cursor.getColumnIndex("rate_type")));
        entity.setRate(Long.valueOf(cursor.getLong(cursor.getColumnIndex("rate"))));
        entity.setRateDate(cursor.getString(cursor.getColumnIndex("rate_date")));
        entity.setTermsId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("terms_id"))));
        return entity;
    }
}