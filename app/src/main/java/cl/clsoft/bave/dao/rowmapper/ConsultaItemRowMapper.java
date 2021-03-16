package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.ConsultaItemCatalogo;
import cl.clsoft.bave.model.ConsultaItem;

public class ConsultaItemRowMapper implements RowMapper<ConsultaItem> {

    @Override
    public ConsultaItem mapRow(Cursor cursor, int position) throws SQLException {
        ConsultaItem entity = new ConsultaItem();
        entity.setSubinventoryCode(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_SUBINVENTORY_CODE)));
        entity.setLocatorCode(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_LOCATOR_CODE)));
        entity.setLotNumber(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_LOT_NUMBER)));
        entity.setSerialNumber(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_SERIAL_NUMBER)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setSigle(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_SEGMENT1)));
        entity.setDescription(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_DESCRIPTION)));
        entity.setUnidad(cursor.getString(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_UNIDAD)));
        entity.setCantidad(cursor.getDouble(cursor.getColumnIndex(ConsultaItemCatalogo.COLUMN_QUANTITY)));
        return entity;
    }

}
