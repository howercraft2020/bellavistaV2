package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public interface IRcvTransactionsInterfaceDao {

    public List<RcvTransactionsInterface> getArticulos(Long id) throws DaoException;
    public void insert(RcvTransactionsInterface rcvTransactionsInterface) throws DaoException;
    public RcvTransactionsInterface get(Long headerInterfaceId, String codigoSigle) throws DaoException;
}
