package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public interface IMtlPhysicalInventoryTagsDao {

    public void insert(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) throws DaoException;
    public void update(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) throws DaoException;
    public void delete(Long tagId) throws DaoException;
    public MtlPhysicalInventoryTags get(Long tagId) throws DaoException;

    public void deleteByPhysicalInventory(Long physicalInventoryId) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllByInventory(Long physicalInventoryId) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllInventariadosByInventory(Long physicalInventoryId) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllNoInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllByInventorySubinventorySegmentSerieLote(Long physicalInventoryId, String subinventory, Long locatorId, String segment, String serie, String lote, String vencimiento) throws DaoException;

    public List<String> getLocatorByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<String> getSegment1ByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<String> getSegment1ByInventorySubinventoryLocator(Long physicalInventoryId, String subinventory, Long locatorId) throws DaoException;
    public List<String> getSeriesByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<String> getSeriesByInventorySubinventoryLocator(Long physicalInventoryId, String subinventory, Long locatorId) throws DaoException;
    public List<String> getSeriesByInventorySubinventoryLocatorSegment(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws DaoException;
    public List<String> getLotesByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException;
    public List<String> getLotesByInventorySubinventoryLocatorSegment(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws DaoException;
    public List<String> getVencimientosByInventorySubinventoryLocatorSegment(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws DaoException;

}
