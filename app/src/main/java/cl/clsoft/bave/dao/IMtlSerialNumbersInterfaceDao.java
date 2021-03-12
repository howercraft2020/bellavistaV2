package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;

public interface IMtlSerialNumbersInterfaceDao {

    public void insert (MtlSerialNumbersInterface mtlSerialNumbersInterface) throws DaoException;
    public void update (MtlSerialNumbersInterface mtlSerialNumbersInterface) throws DaoException;
    public void delete (Long transactionInterfaceId) throws DaoException;
    public void deleteByInterfaceTransactionId (Long interfaceTransactionId) throws DaoException;

    public List<MtlSerialNumbersInterface> getAll(Long transactionInterfaceId) throws DaoException;
    public MtlSerialNumbersInterface get(Long transactionInterfaceId) throws DaoException;
    public List<MtlSerialNumbersInterface> getAllByInterfaceTransactionId(Long interfaceTransactionId) throws DaoException;

}
