package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;

public interface IMtlTransactionLotNumbersDao {

    void insert(MtlTransactionLotNumbers mtlTransactionLotNumbers) throws DaoException;
    void update(MtlTransactionLotNumbers mtlTransactionLotNumbers) throws DaoException;
    void deleteByShipmentHeaderId(Long shipmentHeaderId) throws DaoException;
    public MtlTransactionLotNumbers get(Long transactionId) throws DaoException;
    public List<MtlTransactionLotNumbers> getAllByShipmentHeaderId(Long shipmentHeaderId) throws DaoException;

}
