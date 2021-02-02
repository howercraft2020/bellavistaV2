package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlCycleCountHeadersCatalogo;
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public class MtlCycleCountHeadersRowMapper implements RowMapper<MtlCycleCountHeaders>{
    @Override
    public MtlCycleCountHeaders mapRow(Cursor cursor, int position) throws SQLException {
        MtlCycleCountHeaders entity = new MtlCycleCountHeaders();
        entity.setCycleCountHeaderId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_CREATED_BY)));
        entity.setCycleCountHeaderName(cursor.getString(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_CYCLE_COUNT_HEADER_NAME)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_USER_ID)));
        entity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_EMPLOYEE_ID)));
        entity.setDescription(cursor.getString(cursor.getColumnIndex(MtlCycleCountHeadersCatalogo.COLUMN_DESCRIPTION)));

        return entity;
    }
}
