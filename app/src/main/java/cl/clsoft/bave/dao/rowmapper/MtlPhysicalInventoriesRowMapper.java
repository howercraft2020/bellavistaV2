package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoriesCatalogo;
import cl.clsoft.bave.model.MtlPhysicalInventories;

public class MtlPhysicalInventoriesRowMapper implements RowMapper<MtlPhysicalInventories>{
    @Override
    public MtlPhysicalInventories mapRow(Cursor cursor, int position) throws SQLException {
        MtlPhysicalInventories entity = new MtlPhysicalInventories();
        entity.setPhysicalInventoryId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_CREATED_BY)));
        entity.setPhysicalInventoryDate(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_DATE)));
        entity.setDescription(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_DESCRIPTION)));
        entity.setFreezeDate(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_FREEZE_DATE)));
        entity.setPhysicalInventoryName(cursor.getString(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_NAME)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_USER_ID)));
        entity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_EMPLOYEE_ID)));
        entity.setApprovalRequired(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_APPROVAL_REQUIRED)));
        entity.setAllSubinventoriesFlag(cursor.getLong(cursor.getColumnIndex(MtlPhysicalInventoriesCatalogo.COLUMN_ALL_SUBINVENTORIES_FLAG)));

        return entity;
    }
}
