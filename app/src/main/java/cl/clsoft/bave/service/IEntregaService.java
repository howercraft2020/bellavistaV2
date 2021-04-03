package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.exception.DaoException;
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
    public List<RcvTransactions> getTransaccionsDisponiblesByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException;
    public RcvTransactions getTransactionById(Long transactionId) throws ServiceException;
    public List<Subinventario> getSubinventarios() throws ServiceException;
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;
    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode,
                                         String locatorCode, String lote, String loteProveedor, String vencimiento, String categoria, String atributo1,
                                         String atributo2, String atributo3, List<String> series, Double cantidad) throws ServiceException;
    public List<TransactionsDto> getTransactionsInterfaceByShipmentHeader(Long shipmentHeaderId) throws ServiceException;
    public TransactionDetalleDto getTransactionsInterfaceById(Long interfaceTransactionId) throws ServiceException;
    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) throws ServiceException;
    public void closeEntrega(Long shipmentHeaderId) throws ServiceException;
    public List<String> getSegmentsByShipment(Long shipmentHeaderId) throws ServiceException;

}
