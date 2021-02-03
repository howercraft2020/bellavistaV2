package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSystemItems;

public interface IMtlSystemItemsDao {

    public void insert(MtlSystemItems mtlSystemItems) throws DaoException;

}
