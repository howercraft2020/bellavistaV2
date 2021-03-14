package cl.clsoft.bave.dao.impl;

import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IConsultaItemDao;
import cl.clsoft.bave.dao.catalogo.ConsultaItemCatalogo;
import cl.clsoft.bave.dao.rowmapper.ConsultaItemRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.ConsultaItem;

public class ConsultaItemDaoImpl extends GenericDao<ConsultaItem> implements IConsultaItemDao {

    private static final String TAG = "DAO";

    @Override
    public List<ConsultaItem> getAllByItem(Long inventoryItemId) throws DaoException {
        Log.d(TAG, "ConsultaItemDaoImpl::getAllByItem");

        return super.selectMany(ConsultaItemCatalogo.SELECT_ALL_BY_INVENTORY_ITEM_ID, new ConsultaItemRowMapper(), inventoryItemId);
    }

    @Override
    public List<ConsultaItem> getAllBySubinventory(String subinventoryCode) throws DaoException {
        Log.d(TAG, "ConsultaItemDaoImpl::getAllBySubinventory");

        return super.selectMany(ConsultaItemCatalogo.SELECT_ALL_BY_SUBINVENTORY, new ConsultaItemRowMapper(), subinventoryCode);
    }

}
