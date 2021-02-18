package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlPhysicalInventoriesDao;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoriesCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlPhysicalInventoriesRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlSystemItems;

public class MtlPhysicalInventoriesDaoImpl extends GenericDao<MtlPhysicalInventories> implements IMtlPhysicalInventoriesDao {

    @Override
    public void insert(MtlPhysicalInventories mtlPhysicalInventories) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_ID, mtlPhysicalInventories.getPhysicalInventoryId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_ORGANIZATION_ID, mtlPhysicalInventories.getOrganizationId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_LAST_UPDATE_DATE, mtlPhysicalInventories.getLastUpdateDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_LAST_UPDATED_BY, mtlPhysicalInventories.getLastUpdatedBy());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_CREATION_DATE, mtlPhysicalInventories.getCreationDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_CREATED_BY, mtlPhysicalInventories.getCreatedBy());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_DATE, mtlPhysicalInventories.getPhysicalInventoryDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_DESCRIPTION, mtlPhysicalInventories.getDescription());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_FREEZE_DATE, mtlPhysicalInventories.getFreezeDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_NAME, mtlPhysicalInventories.getPhysicalInventoryName());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_USER_ID, mtlPhysicalInventories.getUserId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_EMPLOYEE_ID, mtlPhysicalInventories.getEmployeeId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_APPROVAL_REQUIRED, mtlPhysicalInventories.getApprovalRequired());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_ALL_SUBINVENTORIES_FLAG, mtlPhysicalInventories.getAllSubinventoriesFlag());
        super.insert(MtlPhysicalInventoriesCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlPhysicalInventories mtlPhysicalInventories) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_ORGANIZATION_ID, mtlPhysicalInventories.getOrganizationId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_LAST_UPDATE_DATE, mtlPhysicalInventories.getLastUpdateDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_LAST_UPDATED_BY, mtlPhysicalInventories.getLastUpdatedBy());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_CREATION_DATE, mtlPhysicalInventories.getCreationDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_CREATED_BY, mtlPhysicalInventories.getCreatedBy());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_DATE, mtlPhysicalInventories.getPhysicalInventoryDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_DESCRIPTION, mtlPhysicalInventories.getDescription());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_FREEZE_DATE, mtlPhysicalInventories.getFreezeDate());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_NAME, mtlPhysicalInventories.getPhysicalInventoryName());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_USER_ID, mtlPhysicalInventories.getUserId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_EMPLOYEE_ID, mtlPhysicalInventories.getEmployeeId());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_APPROVAL_REQUIRED, mtlPhysicalInventories.getApprovalRequired());
        values.put(MtlPhysicalInventoriesCatalogo.COLUMN_ALL_SUBINVENTORIES_FLAG, mtlPhysicalInventories.getAllSubinventoriesFlag());
        super.update(MtlPhysicalInventoriesCatalogo.TABLE, values, MtlPhysicalInventoriesCatalogo.UPDATE, mtlPhysicalInventories.getPhysicalInventoryId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        super.delete(MtlPhysicalInventoriesCatalogo.TABLE, MtlPhysicalInventoriesCatalogo.DELETE, id);
    }

    @Override
    public MtlPhysicalInventories get(Long id) throws DaoException {
        return super.selectOne(MtlPhysicalInventoriesCatalogo.SELECT, new MtlPhysicalInventoriesRowMapper(), id);
    }

    @Override
    public List<MtlPhysicalInventories> getAll() throws DaoException {
        return super.selectMany(MtlPhysicalInventoriesCatalogo.SELECT_ALL, new MtlPhysicalInventoriesRowMapper());
    }




}
