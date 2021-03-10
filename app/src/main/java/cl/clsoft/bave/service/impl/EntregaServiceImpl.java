package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlSerialNumbersInterfaceDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IMtlTransactionLotsInterfaceDao;
import cl.clsoft.bave.dao.IRcvHeadersInterfaceDao;
import cl.clsoft.bave.dao.IRcvShipmentHeadersDao;
import cl.clsoft.bave.dao.IRcvTransactionsDao;
import cl.clsoft.bave.dao.IRcvTransactionsInterfaceDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSerialNumbersInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotsIfaceDaoImpl;
import cl.clsoft.bave.dao.impl.RcvHeadersInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.RcvShipmentHeadersDaoImpl;
import cl.clsoft.bave.dao.impl.RcvTransactionsDaoImpl;
import cl.clsoft.bave.dao.impl.RcvTransactionsInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IEntregaService;

public class EntregaServiceImpl implements IEntregaService {

    private static final String TAG = "SERVICE";

    @Override
    public List<RcvShipmentHeaders> getEntregas() throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getEntregas");

        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        try {
            return rcvShipmentHeadersDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public RcvShipmentHeaders getEntrega(Long shipmentHeaderId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getEntrega");
        Log.d(TAG, "EntregaServiceImpl::getEntrega::shipmentHeaderId: " + shipmentHeaderId);

        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        try {
            RcvShipmentHeaders rcvShipmentHeaders = rcvShipmentHeadersDao.get(shipmentHeaderId);
            if (rcvShipmentHeaders == null) {
                throw new ServiceException(1, "Entrega " + shipmentHeaderId + " no existe en el sistema");
            }
            return rcvShipmentHeaders;
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            MtlSystemItems mtlSystemItems = mtlSystemItemsDao.getBySegment(segment);
            if (mtlSystemItems == null) {
                throw new ServiceException(1, "Item " + segment + " no encontrado en tabla maestra");
            }
            return mtlSystemItems;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getMtlSystemItemsById");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(inventoryItemId);
            if (mtlSystemItems == null) {
                throw new ServiceException(1, "Item ID " + inventoryItemId + " no encontrado en tabla maestra");
            }
            return mtlSystemItems;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<RcvTransactions> getTransaccionsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory");
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory");

        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        try {
            return rcvTransactionsDao.getAllByShipmentInventory(shipmentHeaderId, inventoryItemId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public RcvTransactions getTransactionById(Long transactionId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getTransactionById");
        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        try {
            return rcvTransactionsDao.get(transactionId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Subinventario> getSubinventarios() throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getSubinventarios");

        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try {
            return subinventarioDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getLocalizadorByCodigo");

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            Localizador localizador = localizadorDao.getByCodigo(codigo);
            return localizador;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventario");
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<Localizador> salida = localizadorDao.getAllBySubinventario(subinventarioCodigo);
            return salida;
        } catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode,
                                         String locatorCode, String lote, String loteProveedor, String vencimiento, String categoria, String atributo1,
                                         String atributo2, String atributo3, List<String> series, Double cantidad) throws ServiceException {

        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        IRcvHeadersInterfaceDao rcvHeadersInterfaceDao = new RcvHeadersInterfaceDaoImpl();
        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        try {
            Localizador localizador = null;
            boolean isControlLote = false;
            boolean isControlSerie = false;
            boolean isControlVencimiento = false;
            Long transactionInterfaceId;
            Long interfaceTransactionId;

            // Fecha de hoy
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String sysDate = dateFormat.format(new Date());

            // ShipmentHeaders
            RcvShipmentHeaders rcvShipmentHeaders = rcvShipmentHeadersDao.get(shipmentHeaderId);
            if (rcvShipmentHeaders == null) {
                throw new ServiceException(1, "ShipmentHeader con shipmentHeaderId " + shipmentHeaderId + " no existe en el sistema");
            }

            // Transactions
            RcvTransactions rcvTransactions = rcvTransactionsDao.get(transactionId);
            if (rcvTransactions == null) {
                throw new ServiceException(1, "Transaction con Id " + transactionId + " no existe en el sistema");
            }
            //cantidad = rcvTransactions.getQuantity();

            // Item
            MtlSystemItems item = mtlSystemItemsDao.get(rcvTransactions.getItemId());
            if (item == null) {
                throw new ServiceException(1, "SystemItem con Id " + rcvTransactions.getItemId() + " no existe en el sistema");
            }
            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
            }
            if (item.getShelfLifeCode().equalsIgnoreCase("2") || item.getShelfLifeCode().equalsIgnoreCase("4")) {
                isControlVencimiento = true;
            }

            // Subinventory
            Subinventario subinventario = subinventarioDao.getByCodigo(subinventoryCode);
            if (subinventario == null) {
                throw new ServiceException(1, "Subinventario " + subinventoryCode + " no existe en el sistema");
            }

            // Locator
            if (locatorCode != null) {
                localizador = localizadorDao.getByCodigo(locatorCode);
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + locatorCode + " no existe en el sistema");
                }
            }

            // Validaciones
            if (cantidad.longValue() > rcvTransactions.getQuantity().longValue()) {
                throw new ServiceException(1, "la cantidad es mayor a lo especificado " + rcvTransactions.getQuantity());
            }

            if (isControlLote && lote == null) {
                throw new ServiceException(1, "Debe indicar el lote");
            }

            if (isControlVencimiento && vencimiento == null) {
                throw new ServiceException(1, "Debe indicar el vencimiento");
            }

            if (isControlSerie && series == null) {
                throw new ServiceException(1, "Debe indicar las series");
            }

            if (isControlSerie && series.size() == 0) {
                throw new ServiceException(1, "Debe indicar las series");
            }

            if (isControlSerie && series.size() < cantidad.intValue()) {
                throw new ServiceException(1, "Faltan series");
            }


            // Crea RCV_HEADERS_INTERFACE si no existe.
            RcvHeadersInterface rcvHeadersInterface = rcvHeadersInterfaceDao.get(rcvShipmentHeaders.getHeaderInterfaceId());
            if (rcvHeadersInterface == null) {

                rcvHeadersInterface = new RcvHeadersInterface();
                rcvHeadersInterface.setHeaderInterfaceId(rcvShipmentHeaders.getHeaderInterfaceId());
                rcvHeadersInterface.setGroupId(rcvShipmentHeaders.getGroupId());
                rcvHeadersInterface.setProcessingStatusCode("PENDING");
                rcvHeadersInterface.setReciptSourceCode("VENDOR");
                rcvHeadersInterface.setTransactionType("NEW");
                rcvHeadersInterface.setAutoTransactCode("DELIVER");
                rcvHeadersInterface.setLastUpdateDate(sysDate);
                rcvHeadersInterface.setLastUpdateBy(rcvShipmentHeaders.getUserId());
                //rcvHeadersInterface.setla
                rcvHeadersInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                rcvHeadersInterface.setVendorId(rcvShipmentHeaders.getVendorId());
                rcvHeadersInterface.setShipToOrganizationCode(rcvShipmentHeaders.getShipToOrgId().toString());
                //rcvHeadersInterface.sete
                rcvHeadersInterface.setValidationFlag("Y");
                rcvHeadersInterfaceDao.insert(rcvHeadersInterface);
            }

            // Crea RCV_TRANSACTIONS_INTERFACE
            interfaceTransactionId = rcvShipmentHeaders.getInterfaceTransactionId() + rcvTransactions.getLineNum() - 1;
            RcvTransactionsInterface rcvTransactionsInterface = new RcvTransactionsInterface();
            rcvTransactionsInterface.setInterfaceTransactionId(interfaceTransactionId);
            rcvTransactionsInterface.setLastUpdatedDate(sysDate);
            rcvTransactionsInterface.setLastUpdatedBy(rcvShipmentHeaders.getUserId());
            rcvTransactionsInterface.setCreationDate(sysDate);
            rcvTransactionsInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
            rcvTransactionsInterface.setTransactionType("DELIVER");
            rcvTransactionsInterface.setTransactionDate(sysDate);
            rcvTransactionsInterface.setProcessingStatusCode("PENDING");
            rcvTransactionsInterface.setProcessingModeCode("BATCH");
            rcvTransactionsInterface.setQuantity(cantidad);
            rcvTransactionsInterface.setUnitOfMeasure(rcvTransactions.getUnitOfMeasure());
            rcvTransactionsInterface.setItemId(item.getInventoryItemId());
            rcvTransactionsInterface.setItemDescription(item.getDescription());
            rcvTransactionsInterface.setUomCode(item.getPrimaryUomCode());
            rcvTransactionsInterface.setEmployeeId(rcvShipmentHeaders.getEmployeeId());
            rcvTransactionsInterface.setShipmentHeaderId(rcvTransactions.getShipmentHeaderId());
            rcvTransactionsInterface.setShipmentLineId(rcvTransactions.getShipmentLineId());
            rcvTransactionsInterface.setShipToLocationId(248L);
            rcvTransactionsInterface.setVendorId(rcvTransactions.getVendorId());
            rcvTransactionsInterface.setVendorSiteId(rcvTransactions.getVendorSiteId());
            rcvTransactionsInterface.setToOrganizationId(288L);
            rcvTransactionsInterface.setSourceDocumentCode("PO");
            rcvTransactionsInterface.setParentTransactionId(rcvTransactions.getTransactionId());
            rcvTransactionsInterface.setPoHeaderId(rcvTransactions.getPoHeaderId());
            rcvTransactionsInterface.setPoLineId(rcvTransactions.getPoLineId());
            rcvTransactionsInterface.setPoLineLocation(rcvTransactions.getPoLineLocationId());
            rcvTransactionsInterface.setPoUnitPrice(rcvTransactions.getPoUnitPrice());
            rcvTransactionsInterface.setCurrencyCode(rcvTransactions.getCurrencyCode());
            rcvTransactionsInterface.setCurrencyConversionType(rcvTransactions.getCurrencyConversionType());
            rcvTransactionsInterface.setCurrencyConversionRate(rcvTransactions.getCurrencyConversionRate());
            rcvTransactionsInterface.setCurrencyConversionDate(rcvTransactions.getCurrencyConversionDate());
            rcvTransactionsInterface.setPoDistributionId(rcvTransactions.getPoDistributionId());
            rcvTransactionsInterface.setDestinationTypeCode("INVENTORY");
            rcvTransactionsInterface.setLocationId(rcvTransactions.getLocationId());
            rcvTransactionsInterface.setDeliverToLocationId(248L);
            rcvTransactionsInterface.setInspectionStatusCode("NOT INSPECTED");
            rcvTransactionsInterface.setSubinventory(subinventario.getCodSubinventario());
            rcvTransactionsInterface.setLocatorId(localizador != null ?  localizador.getIdLocalizador() : null);
            rcvTransactionsInterface.setShipmentNum(rcvShipmentHeaders.getShipmentNum());
            if (isControlLote)
                rcvTransactionsInterface.setUseMtlLot(1L);
            else
                rcvTransactionsInterface.setUseMtlLot(0L);
            if (isControlSerie)
                rcvTransactionsInterface.setUseMtlSerial(1L);
            else
                rcvTransactionsInterface.setUseMtlSerial(0L);
            rcvTransactionsInterface.setGroupId(rcvShipmentHeaders.getGroupId());
            rcvTransactionsInterface.setTransactionStatusCode("PENDING");
            rcvTransactionsInterface.setReceiptSourceCode("VENDOR");
            rcvTransactionsInterface.setValidationFlag("Y");
            rcvTransactionsInterface.setOrgId(rcvTransactions.getOrganizationId());
            rcvTransactionsInterface.setHeaderInterfaceId(rcvHeadersInterface.getHeaderInterfaceId());
            rcvTransactionsInterfaceDao.insert(rcvTransactionsInterface);

            // Crea Lote
            if (isControlLote) {
                transactionInterfaceId = this.getNextTransactionInterfaceId(rcvShipmentHeaders.getTransactionInterfaceId());
                MtlTransactionsLotsIface mtlTransactionsLotsIface = new MtlTransactionsLotsIface();
                mtlTransactionsLotsIface.setTransactionInterfaceId(transactionInterfaceId);
                mtlTransactionsLotsIface.setLastUpdateDate(sysDate);
                mtlTransactionsLotsIface.setLastUpdateBy(rcvShipmentHeaders.getUserId());
                mtlTransactionsLotsIface.setLastUpdateLogin(-1L);
                mtlTransactionsLotsIface.setLotNumber(lote);
                mtlTransactionsLotsIface.setTransactionQuantity(cantidad);
                mtlTransactionsLotsIface.setPrimaryQuantity(cantidad);
                mtlTransactionsLotsIface.setProductCode("RCV");
                mtlTransactionsLotsIface.setProductTransactionId(interfaceTransactionId);
                mtlTransactionsLotsIface.setSupplierLotNumber(loteProveedor);
                mtlTransactionsLotsIface.setLotExpirationDate(vencimiento);
                mtlTransactionsLotsIface.setAttributeCategory(categoria);
                mtlTransactionsLotsIface.setAttrubute1(atributo1);
                mtlTransactionsLotsIface.setAttrubute2(atributo2);
                mtlTransactionsLotsIface.setAttrubute3(atributo3);
                mtlTransactionLotsInterfaceDao.insert(mtlTransactionsLotsIface);
            }

            // Crea Series
            if (isControlSerie) {
                for (String serie : series) {
                    transactionInterfaceId = this.getNextTransactionInterfaceId(rcvShipmentHeaders.getTransactionInterfaceId());
                    MtlSerialNumbersInterface mtlSerialNumbersInterface = new MtlSerialNumbersInterface();
                    mtlSerialNumbersInterface.setTransactionInterfaceId(transactionInterfaceId);
                    mtlSerialNumbersInterface.setLastUpdateDate(sysDate);
                    mtlSerialNumbersInterface.setLastUpdatedBy(rcvShipmentHeaders.getUserId());
                    mtlSerialNumbersInterface.setCreationDate(sysDate);
                    mtlSerialNumbersInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                    mtlSerialNumbersInterface.setLastUpdateLogin(-1L);
                    mtlSerialNumbersInterface.setFmSerialNumber(serie);
                    mtlSerialNumbersInterface.setToSerialNumber(serie);
                    mtlSerialNumbersInterface.setProductCode("RCV");
                    mtlSerialNumbersInterface.setProductTransactionId(interfaceTransactionId);
                    mtlSerialNumbersInterfaceDao.insert(mtlSerialNumbersInterface);
                }

            }
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public List<TransactionsDto> getTransactionsInterfaceByShipmentHeader(Long shipmentHeaderId) throws ServiceException {

        List<TransactionsDto> salida = new ArrayList<>();
        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        try {
            List<RcvTransactions> transactions = rcvTransactionsDao.getAllByShipment(shipmentHeaderId);
            for (RcvTransactions transaction : transactions) {
                RcvTransactionsInterface transactioninterface = rcvTransactionsInterfaceDao.getByTransactionId(transaction.getTransactionId());
                if (transactioninterface != null) {
                    TransactionsDto dto = new TransactionsDto();
                    dto.setInterfaceTransactionId(transactioninterface.getInterfaceTransactionId());
                    dto.setLineNum(transaction.getLineNum());
                    dto.setCreationDate(transactioninterface.getCreationDate());
                    dto.setSegment1(transactioninterface.getSegment1());
                    salida.add(dto);
                }
            }
            return salida;
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public TransactionDetalleDto getTransactionsInterfaceById(Long interfaceTransactionId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getTransactionsInterfaceById");
        Log.d(TAG, "EntregaServiceImpl::getTransactionsInterfaceById::interfaceTransactionId: " + interfaceTransactionId);

        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();

        try {
            Localizador localizador = null;
            boolean isControlLote = false;
            boolean isControlSerie = false;
            boolean isControlVencimiento = false;
            MtlTransactionsLotsIface mtlTransactionsLotsIface = null;
            List<MtlSerialNumbersInterface> serials;

            TransactionDetalleDto dto = new TransactionDetalleDto();
            RcvTransactionsInterface rcvTransactionsInterface = rcvTransactionsInterfaceDao.getByInterfaceTransactionId(interfaceTransactionId);

            // Item
            MtlSystemItems item = mtlSystemItemsDao.get(rcvTransactionsInterface.getItemId());
            if (item == null) {
                throw new ServiceException(1, "SystemItem con Id " + rcvTransactionsInterface.getItemId() + " no existe en el sistema");
            }
            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
            }
            if (item.getShelfLifeCode().equalsIgnoreCase("2") || item.getShelfLifeCode().equalsIgnoreCase("4")) {
                isControlVencimiento = true;
            }

            // Locator
            if (rcvTransactionsInterface.getLocatorId() != null) {
                localizador = localizadorDao.get(rcvTransactionsInterface.getLocatorId());
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + rcvTransactionsInterface.getLocatorId() + " no existe en el sistema");
                }
            }


            dto.setShipmentHeaderId(rcvTransactionsInterface.getShipmentHeaderId());
            dto.setTransactionId(rcvTransactionsInterface.getParentTransactionId());
            dto.setCantidad(rcvTransactionsInterface.getQuantity());
            dto.setSubinventoryCode(rcvTransactionsInterface.getSubinventory());
            dto.setLocatorCode(localizador != null ? localizador.getCodLocalizador() : null);
            dto.setLote(isControlLote);
            dto.setSerie(isControlSerie);

            if (isControlLote) {
                mtlTransactionsLotsIface = mtlTransactionLotsInterfaceDao.getByInterfaceTransactionId(rcvTransactionsInterface.getInterfaceTransactionId());
                if (mtlTransactionsLotsIface != null) {
                    dto.setLote(mtlTransactionsLotsIface.getLotNumber());
                    dto.setLoteProveedor(mtlTransactionsLotsIface.getSupplierLotNumber());
                    dto.setCategoria(mtlTransactionsLotsIface.getAttributeCategory());
                    dto.setAtributo1(mtlTransactionsLotsIface.getAttrubute1());
                    dto.setAtributo2(mtlTransactionsLotsIface.getAttrubute2());
                    dto.setAtributo3(mtlTransactionsLotsIface.getAttrubute3());

                }
            }

            if (isControlLote) {
                serials = mtlSerialNumbersInterfaceDao.getAllByInterfaceTransactionId(rcvTransactionsInterface.getInterfaceTransactionId());
                List<String> strSerials = new ArrayList<>();
                for (MtlSerialNumbersInterface serial : serials) {
                    strSerials.add(serial.getFmSerialNumber());
                }
                dto.setSeries(strSerials);
            }
            return dto;
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    private Long getNextTransactionInterfaceId(Long startTransactionInterfaceId) throws ServiceException {
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        boolean encontrado = false;
        Long start = new Long(startTransactionInterfaceId.longValue() - 1);

        try {
            while (!encontrado) {
                start++;
                MtlTransactionsLotsIface mtlTransactionsLotsIface = mtlTransactionLotsInterfaceDao.get(start);
                if (mtlTransactionsLotsIface == null) {
                    MtlSerialNumbersInterface mtlSerialNumbersInterface = mtlSerialNumbersInterfaceDao.get(start);
                    if (mtlSerialNumbersInterface == null) {
                        encontrado = true;
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
        return start;

    }

}
