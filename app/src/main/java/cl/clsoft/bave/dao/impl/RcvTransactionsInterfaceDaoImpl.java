package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IRcvTransactionsInterfaceDao;
import cl.clsoft.bave.dao.catalogo.RcvTransactionsInterfaceCatalogo;
import cl.clsoft.bave.dao.rowmapper.RcvTransactionsInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public class RcvTransactionsInterfaceDaoImpl extends GenericDao<RcvTransactionsInterface> implements IRcvTransactionsInterfaceDao {

    @Override
    public List<RcvTransactionsInterface> getArticulos(Long id) throws DaoException {
        return super.selectMany(RcvTransactionsInterfaceCatalogo.SELECT, new RcvTransactionsInterfaceRowMapper(), id);
    }
}
