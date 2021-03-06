package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlCycleCountEntriesCatalogo;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public class MtlCycleCountEntriesRowMapper implements RowMapper<MtlCycleCountEntries>{
    @Override
    public MtlCycleCountEntries mapRow(Cursor cursor, int position) throws SQLException {
        MtlCycleCountEntries entity = new MtlCycleCountEntries();
        entity.setCycleCountEntryId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_ID)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setSubinventory(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_SUBINVENTORY)));
        entity.setEntryStatusCode(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_ENTRY_STATUS_CODE)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setCycleCountHeaderId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_CYCLE_COUNT_HEADER_ID)));
        entity.setLocatorId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_LOCATOR_ID)));
        entity.setLotNumber(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_LOT_NUMBER)));
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_SEGMENT1)));
        entity.setPrimaryUomCode(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_PRIMARY_UOM_CODE)));
        entity.setSerialNumber(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_SERIAL_NUMBER)));
        entity.setCount(cursor.getDouble(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_COUNT)));
        entity.setLastUpdated(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_LAST_UPDATED)));
        entity.setLocatorCode(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_LOCATOR_CODE)));
        entity.setDescription(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_DESCRIPTION)));
        entity.setLongDescription(cursor.getString(cursor.getColumnIndex(MtlCycleCountEntriesCatalogo.COLUMN_LONG_DESCRIPTION)));
        return entity;
    }
}
