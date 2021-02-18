package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public interface IMtlPhysicalInventoryTagsDao {

    public void insert(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) throws DaoException;
    public void deleteByPhysicalInventory(Long physicalInventoryId) throws DaoException;
    public List<MtlPhysicalInventoryTags> getAllTags(Long physicalInventoryId) throws DaoException;

}