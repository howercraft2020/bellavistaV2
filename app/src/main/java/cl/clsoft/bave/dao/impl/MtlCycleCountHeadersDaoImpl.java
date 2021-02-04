package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlCycleCountHeadersDao;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountHeadersCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlCycleCountHeadersRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public class MtlCycleCountHeadersDaoImpl extends GenericDao<MtlCycleCountHeaders> implements IMtlCycleCountHeadersDao {

    @Override
    public void insert(MtlCycleCountHeaders mtlCycleCountHeaders) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_ID, mtlCycleCountHeaders.getCycleCountHeaderId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_ORGANIZATION_ID, mtlCycleCountHeaders.getOrganizationId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_LAST_UPDATE_DATE, mtlCycleCountHeaders.getLastUpdateDate());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_LAST_UPDATED_BY, mtlCycleCountHeaders.getLastUpdatedBy());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_CREATION_DATE, mtlCycleCountHeaders.getCreationDate());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_CREATED_BY, mtlCycleCountHeaders.getCreatedBy());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_CYCLE_COUNT_HEADER_NAME, mtlCycleCountHeaders.getCycleCountHeaderName());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_USER_ID, mtlCycleCountHeaders.getUserId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_EMPLOYEE_ID, mtlCycleCountHeaders.getEmployeeId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_DESCRIPTION, mtlCycleCountHeaders.getDescription());
        super.insert(MtlCycleCountHeadersCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlCycleCountHeaders mtlCycleCountHeaders) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_ORGANIZATION_ID, mtlCycleCountHeaders.getOrganizationId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_LAST_UPDATE_DATE, mtlCycleCountHeaders.getLastUpdateDate());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_LAST_UPDATED_BY, mtlCycleCountHeaders.getLastUpdatedBy());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_CREATION_DATE, mtlCycleCountHeaders.getCreationDate());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_CREATED_BY, mtlCycleCountHeaders.getCreatedBy());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_CYCLE_COUNT_HEADER_NAME, mtlCycleCountHeaders.getCycleCountHeaderName());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_USER_ID, mtlCycleCountHeaders.getUserId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_EMPLOYEE_ID, mtlCycleCountHeaders.getEmployeeId());
        values.put(MtlCycleCountHeadersCatalogo.COLUMN_DESCRIPTION, mtlCycleCountHeaders.getDescription());
        super.update(MtlCycleCountHeadersCatalogo.TABLE, values, MtlCycleCountHeadersCatalogo.UPDATE, mtlCycleCountHeaders.getCycleCountHeaderId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        super.delete(MtlCycleCountHeadersCatalogo.TABLE, MtlCycleCountHeadersCatalogo.DELETE, id);
    }

    @Override
    public MtlCycleCountHeaders get(Long id) throws DaoException {
        return super.selectOne(MtlCycleCountHeadersCatalogo.SELECT, new MtlCycleCountHeadersRowMapper(), id);
    }
}
