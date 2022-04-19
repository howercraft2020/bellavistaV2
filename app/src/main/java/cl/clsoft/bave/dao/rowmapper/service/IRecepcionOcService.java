package cl.clsoft.bave.dao.rowmapper.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public interface IRecepcionOcService {

    public List<PoHeadersAll> getAllRecepcionesOc() throws ServiceException;
    public List<RcvTransactionsInterface> getAllArticulos(Long headerInterfaceId) throws ServiceException;
    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Double cantidad, Long lineNum, Long poLineNum,Long itemid) throws ServiceException;
    public String crearArchivo(Long interfaceheaderId, String numeroOc, Long receiptNum, Long poHeaderId, String comentario, Long groupId) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public List<MtlSystemItems> getArticulosByOcReceipt(Long poHeaderId, Long receiptNum) throws ServiceException;
    public List<PoLinesAll> getLines(Long inventoryItemId, Long poHeaderId) throws ServiceException;
    public void updateEntry(Long entryId, Double cantidad, Long lineLocationId) throws ServiceException;
    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) throws ServiceException;
}
