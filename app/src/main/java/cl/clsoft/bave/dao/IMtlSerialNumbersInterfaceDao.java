package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;

public interface IMtlSerialNumbersInterfaceDao {

    public List<MtlSerialNumbersInterface> getAll(Long transactionInterfaceId) throws DaoException;
    public void insert (MtlSerialNumbersInterface mtlSerialNumbersInterface) throws DaoException;
    public MtlSerialNumbersInterface get(Long transactionInterfaceId) throws DaoException;

}
