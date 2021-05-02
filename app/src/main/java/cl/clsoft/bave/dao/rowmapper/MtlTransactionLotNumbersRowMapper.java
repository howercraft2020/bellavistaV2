package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlTransactionLotNumbersCatalogo;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;

public class MtlTransactionLotNumbersRowMapper implements RowMapper<MtlTransactionLotNumbers> {

    @Override
    public MtlTransactionLotNumbers mapRow(Cursor cursor, int position) throws SQLException {
        MtlTransactionLotNumbers entity = new MtlTransactionLotNumbers();
        entity.setTransactionId(cursor.getLong(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_ID)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setLotNumber(cursor.getString(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_LOT_NUMBER)));
        entity.setSerialTransactionId(cursor.getLong(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_SERIAL_TRANSACTION_ID)));
        entity.setLotAttributeCategory(cursor.getString(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_LOT_ATTRIBUTE_CATEGORY)));
        entity.setcAttribute1(cursor.getString(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE1)));
        entity.setcAttribute2(cursor.getString(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE2)));
        entity.setcAttribute3(cursor.getString(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE3)));
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setTransactionInterfaceId(cursor.getLong(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_TRANSACTION_INTERFACE_ID)));
        entity.setEntregaCreationDate(cursor.getString(cursor.getColumnIndex(MtlTransactionLotNumbersCatalogo.COLUMN_ENTREGA_CREATION_DATE)));
        return entity;
    }

}
