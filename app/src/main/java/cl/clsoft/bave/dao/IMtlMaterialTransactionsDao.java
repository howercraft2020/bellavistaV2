package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlMaterialTransactions;

public interface IMtlMaterialTransactionsDao {

    public void insert(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException;
    public void update(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException;
    public void delete(Long transactionId) throws DaoException;
}
