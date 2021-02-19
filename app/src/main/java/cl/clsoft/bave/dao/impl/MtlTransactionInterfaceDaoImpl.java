package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IMtlTransactionsInterfaceDao;
import cl.clsoft.bave.dao.catalogo.MtlTransactionsInterfaceCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlTransactionsInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsInterface;

public class MtlTransactionInterfaceDaoImpl extends GenericDao<MtlTransactionsInterface> implements IMtlTransactionsInterfaceDao {


    @Override
    public List<MtlTransactionsInterface> getTransSubinv() throws DaoException {
        return super.selectMany(MtlTransactionsInterfaceCatalogo.SELECT_ALL, new MtlTransactionsInterfaceRowMapper());
    }
}
