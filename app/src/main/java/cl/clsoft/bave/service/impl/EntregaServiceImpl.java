package cl.clsoft.bave.service.impl;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsByShipmentInventory::inventoryItemId: " + inventoryItemId);

        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        try {
            return rcvTransactionsDao.getAllByShipmentInventory(shipmentHeaderId, inventoryItemId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<RcvTransactions> getTransaccionsDisponiblesByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsDisponiblesByShipmentInventory");
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsDisponiblesByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaServiceImpl::getTransaccionsDisponiblesByShipmentInventory::inventoryItemId: " + inventoryItemId);

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
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface");
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::transactionId: " + transactionId);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::subinventoryCode: " + subinventoryCode);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::locatorCode: " + locatorCode);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::lote: " + lote);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::loteProveedor: " + loteProveedor);

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
            RcvTransactionsInterface rcvTransactionsInterface;
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
            if (locatorCode != null && !locatorCode.isEmpty()) {
                Log.d(TAG, "locatorCode: " + locatorCode);
                localizador = localizadorDao.getByCodigo(locatorCode);
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + locatorCode + " no existe en el sistema");
                }
            }

            // Validaciones
            RcvTransactionsInterface transactionsInterfaceExiste = rcvTransactionsInterfaceDao.getByTransactionId(transactionId);
            if (transactionsInterfaceExiste != null) {
                throw new ServiceException(1, "Producto ya fue ingresado");
            }

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
            rcvTransactionsInterface = new RcvTransactionsInterface();
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
                Log.d(TAG, "Agregando Lote " + transactionInterfaceId);
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
                transactionInterfaceId = this.getNextTransactionInterfaceId(rcvShipmentHeaders.getTransactionInterfaceId());
                for (String serie : series) {
                    Log.d(TAG, "Agregando Serie " + transactionInterfaceId);
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

            if (isControlSerie) {
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

    @Override
    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::deleteTransactionsInterfaceById");
        Log.d(TAG, "EntregaServiceImpl::deleteTransactionsInterfaceById::interfaceTransactionId: " + interfaceTransactionId);

        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();

        try {
            rcvTransactionsInterfaceDao.delete(interfaceTransactionId);
            mtlTransactionLotsInterfaceDao.deleteByInterfaceTransactionId(interfaceTransactionId);
            mtlSerialNumbersInterfaceDao.deleteByInterfaceTransactionId(interfaceTransactionId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void closeEntrega(Long shipmentHeaderId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::closeEntrega");
        Log.d(TAG, "EntregaServiceImpl::closeEntrega::shipmentHeaderId: " + shipmentHeaderId);

        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        IRcvHeadersInterfaceDao rcvHeadersInterfaceDao = new RcvHeadersInterfaceDaoImpl();
        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        try {
            RcvShipmentHeaders rcvShipmentHeaders = rcvShipmentHeadersDao.get(shipmentHeaderId);
            if (rcvShipmentHeaders == null) {
                throw new ServiceException(1, "Entrega " + shipmentHeaderId + " no existe en el sistema");
            }

            RcvHeadersInterface rcvHeadersInterface = rcvHeadersInterfaceDao.get(rcvShipmentHeaders.getHeaderInterfaceId());
            if (rcvHeadersInterface == null) {
                throw new ServiceException(1, "Headers Interface " + rcvShipmentHeaders.getHeaderInterfaceId() + " no existe en el sistema");
            }

            List<RcvTransactionsInterface> trxs = rcvTransactionsInterfaceDao.getAllByHeader(rcvHeadersInterface.getHeaderInterfaceId());
            if (trxs.size() == 0) {
                throw new ServiceException(1, "No hay transacciones ingresadas para esta entrega");
            }

            // Genera archivo Conteo
            String nombreArchivo = "I_2_" + shipmentHeaderId + ".txt";
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
            File archivo = new File(Dir, nombreArchivo);
            FileWriter writer = new FileWriter(archivo);
            writer.write("RECIBO;FIN" +"\r\n");
            writer.write(
                    "HDR;"
                    + (rcvHeadersInterface.getHeaderInterfaceId() == null ? "null" : rcvHeadersInterface.getHeaderInterfaceId()) + ";"
                    + (rcvHeadersInterface.getGroupId() == null ? "null" : rcvHeadersInterface.getGroupId()) + ";"
                    + (rcvHeadersInterface.getProcessingStatusCode() == null ? "null" : (rcvHeadersInterface.getProcessingStatusCode().isEmpty() ? "null" : rcvHeadersInterface.getProcessingStatusCode())) + ";"
                    + (rcvHeadersInterface.getReciptSourceCode() == null ? "null" : (rcvHeadersInterface.getReciptSourceCode().isEmpty() ? "null" : rcvHeadersInterface.getReciptSourceCode())) + ";"
                    + (rcvHeadersInterface.getTransactionType() == null ? "null" : (rcvHeadersInterface.getTransactionType().isEmpty() ? "null" : rcvHeadersInterface.getTransactionType())) + ";"
                    + (rcvHeadersInterface.getAutoTransactCode() == null ? "null" : (rcvHeadersInterface.getAutoTransactCode().isEmpty() ? "null" : rcvHeadersInterface.getAutoTransactCode())) + ";"
                    + (rcvHeadersInterface.getLastUpdateDate() == null ? "null" : (rcvHeadersInterface.getLastUpdateDate().isEmpty() ? "null" : rcvHeadersInterface.getLastUpdateDate())) + ";"
                    + (rcvHeadersInterface.getLastUpdateBy() == null ? "null" : rcvHeadersInterface.getLastUpdateBy()) + ";"
                    + "0;"
                    + (rcvHeadersInterface.getCreatedBy() == null ? "null" : rcvHeadersInterface.getCreatedBy()) + ";"
                    + (rcvHeadersInterface.getVendorId() == null ? "null" : rcvHeadersInterface.getVendorId()) + ";"
                    + (rcvHeadersInterface.getShipToOrganizationCode() == null ? "null" : (rcvHeadersInterface.getShipToOrganizationCode().isEmpty() ? "null" : rcvHeadersInterface.getShipToOrganizationCode())) + ";"
                    + (rcvHeadersInterface.getValidationFlag() == null ? "null" : (rcvHeadersInterface.getValidationFlag().isEmpty() ? "null" : rcvHeadersInterface.getValidationFlag())) + ";"
                    + "FIN\r\n"
            );
            for (RcvTransactionsInterface trx : trxs) {
                writer.write(
                        "TRX;"
                        + (trx.getInterfaceTransactionId() == null ? "null" : trx.getInterfaceTransactionId()) + ";"
                        + (trx.getLastUpdatedDate() == null ? "null" : (trx.getLastUpdatedDate().isEmpty() ? "null" : trx.getLastUpdatedDate())) + ";"
                        + (trx.getLastUpdatedBy() == null ? "null" : trx.getLastUpdatedBy()) + ";"
                        + (trx.getCreationDate() == null ? "null" : (trx.getCreationDate().isEmpty() ? "null" : trx.getCreationDate())) + ";"
                        + (trx.getCreatedBy() == null ? "null" : trx.getCreatedBy()) + ";"
                        + (trx.getTransactionType() == null ? "null" : (trx.getTransactionType().isEmpty() ? "null" : trx.getTransactionType())) + ";"
                        + (trx.getTransactionDate() == null ? "null" : (trx.getTransactionDate().isEmpty() ? "null" : trx.getTransactionDate())) + ";"
                        + (trx.getProcessingStatusCode() == null ? "null" : (trx.getProcessingStatusCode().isEmpty() ? "null" : trx.getProcessingStatusCode())) + ";"
                        + (trx.getProcessingModeCode() == null ? "null" : (trx.getProcessingModeCode().isEmpty() ? "null" : trx.getProcessingModeCode())) + ";"
                        + (trx.getQuantity() == null ? "null" : trx.getQuantity()) + ";"
                        + (trx.getUnitOfMeasure() == null ? "null" : (trx.getUnitOfMeasure().isEmpty() ? "null" : trx.getUnitOfMeasure())) + ";"
                        + (trx.getItemId() == null ? "null" : trx.getItemId()) + ";"
                        + (trx.getItemDescription() == null ? "null" : (trx.getItemDescription().isEmpty() ? "null" : trx.getItemDescription())) + ";"
                        + (trx.getUomCode() == null ? "null" : (trx.getUomCode().isEmpty() ? "null" : trx.getUomCode())) + ";"
                        + (trx.getEmployeeId() == null ? "null" : trx.getEmployeeId()) + ";"
                        + (trx.getShipmentHeaderId() == null ? "null" : trx.getShipmentHeaderId()) + ";"
                        + (trx.getShipmentLineId() == null ? "null" : trx.getShipmentLineId()) + ";"
                        + (trx.getShipToLocationId() == null ? "null" : trx.getShipToLocationId()) + ";"
                        + (trx.getVendorId() == null ? "null" : trx.getVendorId()) + ";"
                        + (trx.getVendorSiteCode() == null ? "null" : (trx.getVendorSiteCode().isEmpty() ? "null" : trx.getVendorSiteCode())) + ";"
                        + (trx.getToOrganizationId() == null ? "null" : trx.getToOrganizationId()) + ";"
                        + (trx.getSourceDocumentCode() == null ? "null" : (trx.getSourceDocumentCode().isEmpty() ? "null" : trx.getSourceDocumentCode())) + ";"
                        + (trx.getParentTransactionId() == null ? "null" : trx.getParentTransactionId()) + ";"
                        + (trx.getPoHeaderId() == null ? "null" : trx.getPoHeaderId()) + ";"
                        + (trx.getPoLineId() == null ? "null" : trx.getPoLineId()) + ";"
                        + (trx.getPoLineLocation() == null ? "null" : trx.getPoLineLocation()) + ";"
                        + (trx.getPoUnitPrice() == null ? "null" : trx.getPoUnitPrice()) + ";"
                        + (trx.getCurrencyCode() == null ? "null" : (trx.getCurrencyCode().isEmpty() ? "null" : trx.getCurrencyCode())) + ";"
                        + (trx.getCurrencyConversionType() == null ? "null" : (trx.getCurrencyConversionType().isEmpty() ? "null" : trx.getCurrencyConversionType())) + ";"
                        + (trx.getCurrencyConversionRate() == null ? "null" : trx.getCurrencyConversionRate()) + ";"
                        + (trx.getCurrencyConversionDate() == null ? "null" : (trx.getCurrencyConversionDate().isEmpty() ? "null" : trx.getCurrencyConversionDate())) + ";"
                        + (trx.getPoDistributionId() == null ? "null" : trx.getPoDistributionId()) + ";"
                        + (trx.getDestinationTypeCode() == null ? "null" : (trx.getDestinationTypeCode().isEmpty() ? "null" : trx.getDestinationTypeCode())) + ";"
                        + (trx.getLocationId() == null ? "null" : trx.getLocationId()) + ";"
                        + (trx.getDeliverToLocationId() == null ? "null" : trx.getDeliverToLocationId()) + ";"
                        + (trx.getInspectionStatusCode() == null ? "null" : (trx.getInspectionStatusCode().isEmpty() ? "null" : trx.getInspectionStatusCode())) + ";"
                        + (trx.getSubinventory() == null ? "null" : (trx.getSubinventory().isEmpty() ? "null" : trx.getSubinventory())) + ";"
                        + (trx.getLocatorId() == null ? "null" : trx.getLocatorId()) + ";"
                        + (trx.getShipmentNum() == null ? "null" : (trx.getShipmentNum().isEmpty() ? "null" : trx.getShipmentNum())) + ";"
                        + (trx.getUseMtlLot() == null ? "null" : trx.getUseMtlLot()) + ";"
                        + (trx.getUseMtlSerial() == null ? "null" : trx.getUseMtlSerial()) + ";"
                        + (trx.getGroupId() == null ? "null" : trx.getGroupId()) + ";"
                        + (trx.getTransactionStatusCode() == null ? "null" : (trx.getTransactionStatusCode().isEmpty() ? "null" : trx.getTransactionStatusCode())) + ";"
                        + (trx.getAutoTransactCode() == null ? "null" : (trx.getAutoTransactCode().isEmpty() ? "null" : trx.getAutoTransactCode())) + ";"
                        + (trx.getReceiptSourceCode() == null ? "null" : (trx.getReceiptSourceCode().isEmpty() ? "null" : trx.getReceiptSourceCode())) + ";"
                        + (trx.getValidationFlag() == null ? "null" : (trx.getValidationFlag().isEmpty() ? "null" : trx.getValidationFlag())) + ";"
                        + (trx.getOrgId() == null ? "null" : trx.getOrgId()) + ";"
                        + "FIN\r\n"
                );
            }
            for (RcvTransactionsInterface trx : trxs) {
                MtlTransactionsLotsIface lote = mtlTransactionLotsInterfaceDao.getByInterfaceTransactionId(trx.getInterfaceTransactionId());
                if (lote != null) {
                    writer.write(
                            "LOT;"
                            + (lote.getTransactionInterfaceId() == null ? "null" : lote.getTransactionInterfaceId()) + ";"
                            + (lote.getLastUpdateDate() == null ? "null" : (lote.getLastUpdateDate().isEmpty() ? "null" : lote.getLastUpdateDate())) + ";"
                            + (lote.getLastUpdateBy() == null ? "null" : lote.getLastUpdateBy()) + ";"
                            + (lote.getCreationDate() == null ? "null" : (lote.getCreationDate().isEmpty() ? "null" : lote.getCreationDate())) + ";"
                            + (lote.getCreatedBy() == null ? "null" : lote.getCreatedBy()) + ";"
                            + (lote.getLastUpdateLogin() == null ? "null" : lote.getLastUpdateLogin()) + ";"
                            + (lote.getLotNumber() == null ? "null" : (lote.getLotNumber().isEmpty() ? "null" : lote.getLotNumber())) + ";"
                            + (lote.getTransactionQuantity() == null ? "null" : lote.getTransactionQuantity()) + ";"
                            + (lote.getPrimaryQuantity() == null ? "null" : lote.getPrimaryQuantity()) + ";"
                            + (lote.getSerialTransactionTempId() == null ? "null" : lote.getSerialTransactionTempId()) + ";"
                            + (lote.getProductCode() == null ? "null" : (lote.getProductCode().isEmpty() ? "null" : lote.getProductCode())) + ";"
                            + (lote.getProductTransactionId() == null ? "null" : lote.getProductTransactionId()) + ";"
                            + (lote.getSupplierLotNumber() == null ? "null" : (lote.getSupplierLotNumber().isEmpty() ? "null" : lote.getSupplierLotNumber())) + ";"
                            + (lote.getLotExpirationDate() == null ? "null" : (lote.getLotExpirationDate().isEmpty() ? "null" : lote.getLotExpirationDate())) + ";"
                            + (lote.getAttributeCategory() == null ? "null" : (lote.getAttributeCategory().isEmpty() ? "null" : lote.getAttributeCategory())) + ";"
                            + (lote.getAttrubute1() == null ? "null" : (lote.getAttrubute1().isEmpty() ? "null" : lote.getAttrubute1())) + ";"
                            + (lote.getAttrubute2() == null ? "null" : (lote.getAttrubute2().isEmpty() ? "null" : lote.getAttrubute2())) + ";"
                            + (lote.getAttrubute3() == null ? "null" : (lote.getAttrubute3().isEmpty() ? "null" : lote.getAttrubute3())) + ";"
                            + "FIN\r\n"
                    );
                }
            }
            for (RcvTransactionsInterface trx : trxs) {
                List<MtlSerialNumbersInterface> series = mtlSerialNumbersInterfaceDao.getAllByInterfaceTransactionId(trx.getInterfaceTransactionId());
                for (MtlSerialNumbersInterface serie : series) {
                    writer.write(
                            "SER;"
                            + (serie.getTransactionInterfaceId() == null ? "null" : serie.getTransactionInterfaceId()) + ";"
                            + (serie.getLastUpdateDate() == null ? "null" : (serie.getLastUpdateDate().isEmpty() ? "null" : serie.getLastUpdateDate())) + ";"
                            + (serie.getLastUpdatedBy() == null ? "null" : serie.getLastUpdatedBy()) + ";"
                            + (serie.getCreationDate() == null ? "null" : (serie.getCreationDate().isEmpty() ? "null" : serie.getCreationDate())) + ";"
                            + (serie.getCreatedBy() == null ? "null" : serie.getCreatedBy()) + ";"
                            + (serie.getLastUpdateLogin() == null ? "null" : serie.getLastUpdateLogin()) + ";"
                            + (serie.getFmSerialNumber() == null ? "null" : (serie.getFmSerialNumber().isEmpty() ? "null" : serie.getFmSerialNumber())) + ";"
                            + (serie.getToSerialNumber() == null ? "null" : (serie.getToSerialNumber().isEmpty() ? "null" : serie.getToSerialNumber())) + ";"
                            + (serie.getProductCode() == null ? "null" : (serie.getProductCode().isEmpty() ? "null" : serie.getProductCode())) + ";"
                            + (serie.getProductTransactionId() == null ? "null" : serie.getProductTransactionId()) + ";"
                            + "FIN\r\n"
                    );

                }
            }
            writer.flush();
            writer.close();

            // Elimina datos de la BD
            for (RcvTransactionsInterface trx : trxs) {
                mtlSerialNumbersInterfaceDao.deleteByInterfaceTransactionId(trx.getInterfaceTransactionId());
                mtlTransactionLotsInterfaceDao.deleteByInterfaceTransactionId(rcvShipmentHeaders.getInterfaceTransactionId());
                rcvTransactionsInterfaceDao.delete(trx.getInterfaceTransactionId());
            }
            rcvHeadersInterfaceDao.delete(rcvShipmentHeaders.getHeaderInterfaceId());
            rcvTransactionsDao.deleteByShipmenHeader(shipmentHeaderId);
            rcvShipmentHeadersDao.delete(shipmentHeaderId);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        } catch(IOException e){
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public List<String> getSegmentsByShipment(Long shipmentHeaderId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getSegmentsByShipment");
        Log.d(TAG, "EntregaServiceImpl::getSegmentsByShipment::shipmentHeaderId: " + shipmentHeaderId);

        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        try {
            return rcvTransactionsDao.getSegmentsByShipment(shipmentHeaderId);
        } catch(DaoException e){
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
