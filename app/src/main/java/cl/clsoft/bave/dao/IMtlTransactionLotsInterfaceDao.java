package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;

public interface IMtlTransactionLotsInterfaceDao {

    public void insert (MtlTransactionsLotsIface mtlTransactionsLotsIface) throws DaoException;
    public void update (MtlTransactionsLotsIface mtlTransactionsLotsIface) throws DaoException;
    public void delete (Long transactionInterfaceId) throws DaoException;
    public void deleteByInterfaceTransactionId (Long interfaceTransactionId) throws DaoException;

    public MtlTransactionsLotsIface get(Long transactionInterfaceId) throws DaoException;
    public MtlTransactionsLotsIface getByInterfaceTransactionId(Long interfaceTransactionId) throws DaoException;
    public MtlTransactionsLotsIface getAll(Long transactionInterfaceId) throws DaoException;
}
