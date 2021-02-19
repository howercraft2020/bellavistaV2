package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsInterface;

public interface IMtlTransactionsInterfaceDao {
    public List<MtlTransactionsInterface> getTransSubinv() throws DaoException;
}
