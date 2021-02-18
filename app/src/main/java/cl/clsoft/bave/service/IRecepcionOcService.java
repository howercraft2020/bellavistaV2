package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public interface IRecepcionOcService {

    public List<PoHeadersAll> getAllRecepcionesOc() throws ServiceException;
    public List<RcvTransactionsInterface> getAllArticulos(Long headerInterfaceId) throws ServiceException;
    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Long cantidad) throws ServiceException;
    public void crearArchivo(Long interfaceheaderId, String numeroOc, Long receiptNum) throws ServiceException;
}
