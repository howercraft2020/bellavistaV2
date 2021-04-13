package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSerialNumbers;

public interface IMtlSerialNumbersDao {

    void insert(MtlSerialNumbers mtlSerialNumbers) throws DaoException;
    void update(MtlSerialNumbers mtlSerialNumbers) throws DaoException;
    void deleteBySHipmentHeaderId(Long shipmentHeaderId) throws DaoException;
    public List<MtlSerialNumbers> getAllByShipmentHeaderId(Long shipmentHeaderId) throws DaoException;
    public List<MtlSerialNumbers> getAllByShipmentHeaderIdInventoryItemId(Long shipmentHeaderId, Long inventoryItemId) throws DaoException;

}
