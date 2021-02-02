package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlPhysicalSubinventoriesCatalogo;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;

public class MtlPhysicalSubinventoriesRowMapper implements RowMapper<MtlPhysicalSubinventories>{
    @Override
    public MtlPhysicalSubinventories mapRow(Cursor cursor, int position) throws SQLException {
        MtlPhysicalSubinventories entity = new MtlPhysicalSubinventories();
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalSubinventoriesCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setPhyshicalInventoryId(cursor.getLong(cursor.getColumnIndex(MtlPhysicalSubinventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_ID)));
        entity.setSubinventory(cursor.getString(cursor.getColumnIndex(MtlPhysicalSubinventoriesCatalogo.COLUMN_SUBINVENTORY)));

        return entity;
    }
}
