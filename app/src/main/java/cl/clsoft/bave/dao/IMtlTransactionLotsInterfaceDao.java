package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;

public interface IMtlTransactionLotsInterfaceDao {

    public void insert (MtlTransactionsLotsIface mtlTransactionsLotsIface) throws DaoException;
    public MtlTransactionsLotsIface get(Long transactionInterfaceId) throws DaoException;
    public MtlTransactionsLotsIface getAll(Long transactionInterfaceId) throws DaoException;
}
