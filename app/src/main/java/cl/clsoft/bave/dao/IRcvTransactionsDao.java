package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactions;

public interface IRcvTransactionsDao {

    public void insert(RcvTransactions rcvTransactions) throws DaoException;
    public void update(RcvTransactions rcvTransactions) throws DaoException;

}
