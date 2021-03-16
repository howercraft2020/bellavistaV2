package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.dto.MtlTransactionDetalleDto;
import cl.clsoft.bave.dto.MtlTransactionOrgDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransOrg;
import cl.clsoft.bave.model.DatosTransOrgDetalle;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.model.Subinventario;

public interface ITransOrgService {

    public List<DatosTransOrg> getTransSubinv() throws ServiceException;
    public List<DatosTransOrgDetalle> getTransferencias(String numeroTraspaso) throws ServiceException;
    public List<Organizacion> getOrganizacionesHacia() throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public List<Subinventario> getSubinventarios() throws ServiceException;
    public void validaTransferencia(String articulo, String lote, String subinventario, String localizador, Double cantidad, String orgDestino, List<String> series) throws ServiceException;
    public List<MtlOnhandQuantities> getSeries(String sigle, String lote, String subinventario, String localizador) throws ServiceException;
    public MtlOnhandQuantities getSerieIngresada(String sigle, String lote, String subinventario, String localizador, String serie) throws ServiceException;
    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Double cantidad,
                              String glosa, List<String> series, String orgDestino) throws ServiceException;
    public MtlTransactionOrgDto getTransactionsInterfaceById(Long transactionInterfaceId) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException;
    public void deleteTransactionsInterfaceById(Long transactionInterfaceId) throws ServiceException;
    public void crearArchivo(String transactionReference) throws ServiceException;

}
