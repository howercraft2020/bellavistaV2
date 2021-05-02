package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlMaterialTransactions;

public interface IMtlMaterialTransactionsDao {

    void insert(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException;
    void update(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException;
    void delete(Long transactionId) throws DaoException;
    void deleteByShipmentHeaderId(Long shipmentHeaderId) throws DaoException;
    MtlMaterialTransactions get(Long transactionId) throws DaoException;
    List<MtlMaterialTransactions> getAllByShipmentHeaderId(Long shipmentHeaderId) throws DaoException;
    List<MtlMaterialTransactions> getAllByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws DaoException;
    List<MtlMaterialTransactions> getAllByShipmentEntrega(Long shipmentHeaderId) throws DaoException;
    List<String> getSegmentsByShipment(Long shipmentHeaderId) throws DaoException;

}
