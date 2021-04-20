package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSerialNumbers;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;
import cl.clsoft.bave.model.Subinventario;

public interface IEntregaOrgsService {

    public List<EntregaOrgsHeader> getEntregas() throws ServiceException;
    public EntregaOrgsHeader getEntrega(Long shipmentHeaderId) throws ServiceException;
    public MtlMaterialTransactions getTransactionById(Long transactionId) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException;
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException;
    public List<MtlMaterialTransactions> getTransaccionsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException;
    public List<Subinventario> getSubinventarios() throws ServiceException;
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException;
    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode,
                                         String locatorCode, String lote, String categoria, String atributo1,
                                         String atributo2, String atributo3, List<String> series, Double cantidad) throws ServiceException;
    public List<TransactionsDto> getTransactionsInterfaceByShipmentHeader(Long shipmentHeaderId) throws ServiceException;
    public TransactionDetalleDto getTransactionDetalleById(Long transactionId) throws ServiceException;
    public void deleteTransactionsById(Long transactionId) throws ServiceException;
    public String closeEntrega(Long shipmentHeaderId) throws ServiceException;
    public List<String> getSegmentsByShipment(Long shipmentHeaderId) throws ServiceException;
    public List<MtlTransactionLotNumbers> getLotesByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException;
    public List<MtlSerialNumbers> getSerialsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException;

}
