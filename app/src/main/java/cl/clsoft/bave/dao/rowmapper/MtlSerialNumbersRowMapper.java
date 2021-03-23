package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlSerialNumbersCatalogo;
import cl.clsoft.bave.model.MtlSerialNumbers;

public class MtlSerialNumbersRowMapper implements RowMapper<MtlSerialNumbers> {

    @Override
    public MtlSerialNumbers mapRow(Cursor cursor, int position) throws SQLException {
        MtlSerialNumbers entity = new MtlSerialNumbers();
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setSerialNumber(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_SERIAL_NUMBER)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdateBy(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_CREATED_BY)));
        entity.setLotNumber(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_LOT_NUMBER)));
        entity.setCurrentOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_CURRENT_ORGANIZATION_ID)));
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setEntregaCreationDate(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersCatalogo.COLUMN_ENTREGA_CREATION_DATE)));
        return entity;
    }

}
