package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSystemItems;

public interface IMtlSystemItemsDao {

    public void insert(MtlSystemItems mtlSystemItems) throws DaoException;
    public void update(MtlSystemItems mtlSystemItems) throws DaoException;
    public void delete(Long inventoryItemId) throws DaoException;
    public void deleteAll() throws DaoException;
    public MtlSystemItems get(Long inventoryItemId) throws DaoException;
    public MtlSystemItems getBySegment(String segment) throws DaoException;
    public List<MtlSystemItems> getAll() throws DaoException;
    public List<MtlSystemItems> getAllByDescription(String pattern) throws DaoException;
    public List<MtlSystemItems> getAllByDescriptionShipment(String pattern, Long shipmentHeaderId) throws DaoException;
    public List<MtlSystemItems> getAllByDescriptionPoHeaderId(String pattern, Long poHeaderId) throws DaoException;
    public List<MtlSystemItems> getAllByDescriptionShipmentOrganizacion(String pattern, Long shipmentHeaderId) throws DaoException;
    public List<MtlSystemItems> getAllByDescriptionCountHeaderLocator(String pattern, Long countHeaderId, Long locatorId) throws DaoException;
    public List<MtlSystemItems> getAllByOcReceipt(Long poHeaderId, Long receiptNum) throws DaoException;

}
