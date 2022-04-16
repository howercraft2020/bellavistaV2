package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.model.Subinventario;

public interface IConteoCiclicoService {

    public List<MtlCycleCountHeaders> getAllConteosCiclicos() throws ServiceException;
    public MtlCycleCountHeaders getConteoCiclico(Long cycleCountHeaderId) throws ServiceException;
    public List<Subinventario> getAllSubinventariosByConteoCiclico(Long cycleCountHeaderId) throws ServiceException;
    public List<MtlCycleCountEntries> getAllEntriesInventariadas(Long countHeaderId, String subinventory) throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventarioCountheaderId(String subinventarioCodigo, Long countHeaderId) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException;
    public void grabarInventario(Long cycleCountEntrieId, String subinventarioId, Long locatorId, String segment, String serie, String lote, Double cantidad) throws ServiceException;
    public MtlCycleCountEntries getEntry(Long entryId) throws ServiceException;
    public void updateEntry(Long entryId, Double cantidad) throws ServiceException;
    public void deleteEntry(Long entryId) throws ServiceException;
    public String closeConteoCiclico(Long cycleCountHeaderId) throws ServiceException;

    public List<MtlCycleCountEntries> getAllSigleInformation(Long idSigle) throws ServiceException;

    public List<MtlCycleCountEntries> getAllSigleDetalle() throws ServiceException;

    public MtlCycleCountEntries getCiclicoSigleDetalle(Long cycleCountEntrieId) throws ServiceException;
    public List<String> getSegmentsByCountHeaderIdLocatorId(Long cycleCountEntrieId, Long locatorId) throws ServiceException;
    public List<String> getSegmentsByCountHeaderIdSubinventoryLocator(Long cycleCountEntrieId, String subinventory, Long locatorId) throws ServiceException;
    public List<String> getLotesByCountHeaderIdSubinventoryLocatorIdSegment(Long cycleCountHeaderId, String subinventory, Long locatorId, String segment) throws ServiceException;
    public List<String> getSerialByCountHeaderIdSubinventoryLocatorIdSegment(Long cycleCountHeaderId, String subinventory, Long locatorId, String segment) throws ServiceException;

    public String closeConteoCiclicov2(Long cycleCountHeaderId, MtlCycleCountHeaders header, List<MtlCycleCountEntries> entries, OrganizacionPrincipal organizacionPrincipal, MtlSystemItems mtlSystemItems) throws ServiceException;


}
