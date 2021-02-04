package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlPhysicalSubinventoriesDao;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalSubinventoriesCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;

public class MtlPhysicalSubinventoriesDaoImpl extends GenericDao<MtlPhysicalSubinventories> implements IMtlPhysicalSubinventoriesDao {

    @Override
    public void insert(MtlPhysicalSubinventories mtlPhysicalSubinventories) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlPhysicalSubinventoriesCatalogo.COLUMN_ORGANIZATION_ID, mtlPhysicalSubinventories.getOrganizationId());
        values.put(MtlPhysicalSubinventoriesCatalogo.COLUMN_PHYSICAL_INVENTORY_ID, mtlPhysicalSubinventories.getPhyshicalInventoryId());
        values.put(MtlPhysicalSubinventoriesCatalogo.COLUMN_SUBINVENTORY, mtlPhysicalSubinventories.getSubinventory());
        super.insert(MtlPhysicalSubinventoriesCatalogo.TABLE, values);
    }

    @Override
    public void deleteByPhysicalInventory(Long physicalInventoryId) throws DaoException {
        super.delete(MtlPhysicalSubinventoriesCatalogo.TABLE, MtlPhysicalSubinventoriesCatalogo.DELETE_BY_PHYSICAL_INVENTORY_ID, physicalInventoryId);
    }

}
