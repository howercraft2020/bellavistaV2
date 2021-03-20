package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;

public interface IMtlTransactionLotNumbersDao {

    public void insert(MtlTransactionLotNumbers mtlTransactionLotNumbers) throws DaoException;
    public void update(MtlTransactionLotNumbers mtlTransactionLotNumbers) throws DaoException;

}
