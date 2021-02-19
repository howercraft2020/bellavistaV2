package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;

public interface IInventarioFisicoService {

    public List<MtlPhysicalInventories> getAllInventariosFisicos() throws ServiceException;
    public MtlPhysicalInventories getInventarioFisico(Long physicalInventoryId) throws ServiceException;
    public List<MtlSystemItems> getAllItems() throws ServiceException;
    public List<MtlPhysicalInventoryTags> getAllTagsByInventory(Long physicalInventoryId) throws ServiceException;
    public List<MtlPhysicalInventoryTags> getAllTagsByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException;
    public List<MtlPhysicalSubinventories> getSubinventories(Long physicalInventoryId) throws ServiceException;
}
