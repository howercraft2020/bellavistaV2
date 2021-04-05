package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;

public interface IInventarioFisicoService {

    public List<MtlPhysicalInventories> getAllInventariosFisicos() throws ServiceException;
    public MtlPhysicalInventories getInventarioFisico(Long physicalInventoryId) throws ServiceException;
    public MtlPhysicalInventoryTags getTag(Long tagId) throws ServiceException;
    public void updateTag(Long tagId, long cantidad) throws ServiceException;
    public void deleteTag(Long tagId) throws ServiceException;

    public List<MtlSystemItems> getAllItems() throws ServiceException;
    public List<MtlSystemItems> getItemsByDescription(String pattern) throws ServiceException;
    public List<MtlSystemItems> getItemsEntregaByDescription(String pattern, Long shipmentHeaderId) throws ServiceException;
    public List<MtlSystemItems> getItemsEntregaOrganizacionesByDescription(String pattern, Long shipmentHeaderId) throws ServiceException;
    public List<MtlSystemItems> getItemsCiclicoByDescription(String pattern, Long countHeaderId, Long locatorId) throws ServiceException;

    public List<MtlPhysicalInventoryTags> getAllTagsByInventory(Long physicalInventoryId) throws ServiceException;
    public List<MtlPhysicalInventoryTags> getAllTagsByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException;
    public List<MtlPhysicalInventoryTags> getAllTagsInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException;
    public List<MtlPhysicalInventoryTags> getAllTagsNoInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException;
    public List<MtlPhysicalSubinventories> getSubinventories(Long physicalInventoryId) throws ServiceException;

    public List<String> getLocator(Long physicalInventoryId, String subinventory) throws ServiceException;
    public List<String> getSegment1(Long physicalInventoryId, String subinventory, String locatorCodigo) throws ServiceException;
    public List<String> getSeries(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws ServiceException;
    public List<String> getLotes(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws ServiceException;
    public List<String> getVencimientos(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws ServiceException;

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Long cantidad) throws ServiceException;
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;

    public Long closeInventory(Long inventoryId) throws ServiceException;

}
