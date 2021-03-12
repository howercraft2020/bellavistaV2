package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactions;

public interface IRcvTransactionsDao {

    public void insert(RcvTransactions rcvTransactions) throws DaoException;
    public void update(RcvTransactions rcvTransactions) throws DaoException;
    public void delete(Long transactionId) throws DaoException;
    public void deleteByShipmenHeader(Long shipmentHeaderId) throws DaoException;
    public RcvTransactions get(Long transactionId) throws DaoException;

    public List<RcvTransactions> getAllByShipment(Long shipmentHeaderId) throws DaoException;
    public List<RcvTransactions> getAllByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws DaoException;
    public List<RcvTransactions> getAllDisponiblesByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws DaoException;

}
