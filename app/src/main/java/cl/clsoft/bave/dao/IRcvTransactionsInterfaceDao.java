package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public interface IRcvTransactionsInterfaceDao {

    public void insert(RcvTransactionsInterface rcvTransactionsInterface) throws DaoException;
    public void update(RcvTransactionsInterface rcvTransactionsInterface) throws DaoException;
    public void delete(Long interfaceTransactionId) throws DaoException;
    public void deletebyHeaderInterface(Long headerInterfaceId) throws DaoException;

    public List<RcvTransactionsInterface> getArticulos(Long id) throws DaoException;
    public RcvTransactionsInterface get(Long headerInterfaceId, String codigoSigle, Long poLineId) throws DaoException;
    public RcvTransactionsInterface getByTransactionId(Long transactionId) throws DaoException;
    public RcvTransactionsInterface getByInterfaceTransactionId(Long interfaceTransactionId) throws DaoException;
    public List<RcvTransactionsInterface> getAllByHeader(Long headerInterfaceId) throws DaoException;

}
