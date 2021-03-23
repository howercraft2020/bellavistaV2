package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.EntregaOrgsHeaderCatalogo;
import cl.clsoft.bave.model.EntregaOrgsHeader;

public class EntregaOrgsHeaderRowMapper implements RowMapper<EntregaOrgsHeader> {
    @Override
    public EntregaOrgsHeader mapRow(Cursor cursor, int position) throws SQLException {
        EntregaOrgsHeader entity = new EntregaOrgsHeader();
        entity.setShipmentNumber(cursor.getString(cursor.getColumnIndex(EntregaOrgsHeaderCatalogo.COLUMN_SHIPMENT_NUMBER)));
        entity.setReceiptNumber(cursor.getString(cursor.getColumnIndex(EntregaOrgsHeaderCatalogo.COLUMN_RECEIPT_NUMBER)));
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(EntregaOrgsHeaderCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(EntregaOrgsHeaderCatalogo.COLUMN_CREATION_DATE)));
        return entity;
    }
}
