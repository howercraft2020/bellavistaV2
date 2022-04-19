package cl.clsoft.bave.dao.rowmapper.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.ConsultaResumenItem;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Subinventario;

public interface IConsultaService {

    public List<ConsultaItem> getAllByItem(Long inventoryItemId) throws ServiceException;
    public List<ConsultaItem> getAllBySubinventory(String subinventoryCode) throws ServiceException;
    public List<ConsultaItem> getAllBySubinventoryInventoryItem(String subinventoryCode, Long inventoryItemId) throws ServiceException;
    public List<ConsultaResumenItem> getAllResumenBySubinventory(String subinventoryCode) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException;
    public List<Subinventario> getSubinventarios() throws ServiceException;

}
