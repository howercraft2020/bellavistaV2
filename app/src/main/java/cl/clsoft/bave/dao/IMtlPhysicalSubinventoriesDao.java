package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;

public interface IMtlPhysicalSubinventoriesDao {

    public void insert(MtlPhysicalSubinventories mtlPhysicalSubinventories) throws DaoException;
    public void deleteByPhysicalInventory(Long physicalInventoryId) throws DaoException;
    public List<MtlPhysicalSubinventories> getSubinventories(Long physicalInventoryId) throws DaoException;

}
