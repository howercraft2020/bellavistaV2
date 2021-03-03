package cl.clsoft.bave.service;

import java.util.List;


import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;


public interface ITransSubinvService {

    public List<DatosTransSubinv> getTransSubinv() throws ServiceException;
    public void cargaTransferencia(String articulo, String lote, String subinventario, String localizador, Long cantidad) throws ServiceException;
    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Long cantidad,
                              String subinventarioHasta, String localizadorHasta) throws ServiceException;
    public List<DatosTransSubinvDetalle> getTransferencias(String numeroTraspaso) throws ServiceException;
    public void crearArchivo(String transactionReference) throws ServiceException;
    public void controlSerie (String articulo ) throws ServiceException;

}
