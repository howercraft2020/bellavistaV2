package cl.clsoft.bave.service;

import java.util.List;


import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Subinventario;


public interface ITransSubinvService {

    public List<DatosTransSubinv> getTransSubinv() throws ServiceException;
    public void cargaTransferencia(String articulo, String lote, String subinventario, String localizador, Long cantidad) throws ServiceException;
    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Long cantidad,
                              String subinventarioHasta, String localizadorHasta, List<String> series) throws ServiceException;
    public List<DatosTransSubinvDetalle> getTransferencias(String numeroTraspaso) throws ServiceException;
    public void crearArchivo(String transactionReference) throws ServiceException;
    public void controlSerie(String articulo) throws ServiceException;
    public List<MtlSerialNumbersInterface> getAllSeries(Long transactionInterfaceId) throws ServiceException;
    public List<MtlOnhandQuantities> getSeries(String sigle, String lote, String subinventario, String localizador) throws ServiceException;
    public MtlOnhandQuantities getSerieIngresada(String sigle, String lote, String subinventario, String localizador, String serie) throws ServiceException;
    public List<Subinventario> getSubinventarios() throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
}
