package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoryTagsCatalogo;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public class MtlPhysicalInventoryTagsRowMapper implements RowMapper<MtlPhysicalInventoryTags>{
    @Override
    public MtlPhysicalInventoryTags mapRow(Cursor cursor, int position) throws SQLException {
        MtlPhysicalInventoryTags entity = new MtlPhysicalInventoryTags();
        entity.setTagId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_TAG_ID)));
        entity.setPhysicalInventoryId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_PHYSICAL_INVENTORY_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setSubinventory(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_SUBINVENTORY)));
        entity.setLocatorId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOCATOR_ID)));
        entity.setLotNumner(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_NUMBER)));
        entity.setLotExpirationDate(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_EXPIRATION_DATE)));
        entity.setSerialNum(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_SERIAL_NUM)));
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_SEGMENT1)));
        entity.setPrimaryUomCode(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoryTagsCatalogo.COLUMN_PRIMARY_UOM_CODE)));

        return null;
    }
}
