package cl.clsoft.bave.dao.impl;

import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IConsultaResumenItemDao;
import cl.clsoft.bave.dao.catalogo.ConsultaResumenItemCatalogo;
import cl.clsoft.bave.dao.rowmapper.ConsultaResumenItemRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.ConsultaResumenItem;

public class ConsultaResumenItemDaoImpl extends GenericDao<ConsultaResumenItem> implements IConsultaResumenItemDao {

    private static final String TAG = "DAO";

    @Override
    public List<ConsultaResumenItem> getAllBySubinventory(String subinventoryCode) throws DaoException {
        Log.d(TAG, "ConsultaResumenItemDaoImpl::getAllBySubinventory");

        return super.selectMany(ConsultaResumenItemCatalogo.SELECT_ALL_BY_SUBINVENTORY, new ConsultaResumenItemRowMapper(), subinventoryCode);
    }

}
