package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlSystemItemsCatalogo;
import cl.clsoft.bave.model.MtlSystemItems;

public class MtlSystemItemsRowMapper implements RowMapper<MtlSystemItems>{
    @Override
    public MtlSystemItems mapRow(Cursor cursor, int position) throws SQLException {
        MtlSystemItems entity = new MtlSystemItems();
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_ID)));
        entity.setDescription(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_DESCRIPTION)));
        entity.setLongDescription(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_LONG_DESCRIPTION)));
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_SEGMENT1)));
        entity.setPrimaryUomCode(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_PRIMARY_UOM_CODE)));
        entity.setLotControlCode(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_LOT_CONTROL_CODE)));
        entity.setShelfLifeCode(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_SHELF_LIFE_CODE)));
        entity.setSerialNumberControlCode(cursor.getString(cursor.getColumnIndex(MtlSystemItemsCatalogo.COLUMN_SERIAL_NUMBER_CONTROL_CODE)));

        return entity;
    }
}
