package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSerialNumbers;

public interface IMtlSerialNumbersDao {

    public void insert(MtlSerialNumbers mtlSerialNumbers) throws DaoException;
    public void update(MtlSerialNumbers mtlSerialNumbers) throws DaoException;

}
