package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;

public interface IEntregaService {

    public List<RcvShipmentHeaders> getEntregas() throws ServiceException;
    public RcvShipmentHeaders getEntrega(Long shipmentHeaderId) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException;
    public List<RcvTransactions> getTransaccionsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException;
    public RcvTransactions getTransactionById(Long transactionId) throws ServiceException;
    public List<Subinventario> getSubinventarios() throws ServiceException;
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;
    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode,
                                         String locatorCode, String lote, String vencimiento, String atributo1,
                                         String atributo2, String atributo3, List<String> series, Long cantidad) throws ServiceException;

}
