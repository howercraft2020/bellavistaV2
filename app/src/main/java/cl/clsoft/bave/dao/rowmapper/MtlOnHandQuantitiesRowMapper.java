package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlOnhandQuantitiesCatalogo;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public class MtlOnHandQuantitiesRowMapper implements RowMapper<MtlOnhandQuantities>{

    @Override
    public MtlOnhandQuantities mapRow(Cursor cursor, int position) throws SQLException {
        MtlOnhandQuantities entity = new MtlOnhandQuantities();
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setSubinventoryCode(cursor.getString(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_SUBINVENTORY_CODE)));
        entity.setLocatorId(cursor.getLong(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_LOCATOR_ID)));
        entity.setLotNumber(cursor.getString(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_LOT_NUMBER)));
        entity.setSerialNumber(cursor.getString(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_SERIAL_NUMBER)));
        entity.setPrimaryTransactionQuantity(cursor.getDouble(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_PRIMARY_TRANSACTION_QUANTITY)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_USER_ID)));
        entity.setStatusId(cursor.getLong(cursor.getColumnIndex(MtlOnhandQuantitiesCatalogo.COLUMN_STATUS_ID)));

        return entity;
    }

}
