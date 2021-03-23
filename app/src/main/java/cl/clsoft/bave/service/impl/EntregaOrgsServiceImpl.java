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

import cl.clsoft.bave.dao.IEntregaOrgsHeaderDao;
import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlMaterialTransactionsDao;
import cl.clsoft.bave.dao.IMtlSerialNumbersDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IMtlTransactionLotNumbersDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.EntregaOrgsHeaderDaoImpl;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlMaterialTransactionsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSerialNumbersDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotNumbersDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSerialNumbers;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IEntregaOrgsService;

public class EntregaOrgsServiceImpl implements IEntregaOrgsService {

    private static final String TAG = "SERVICE";

    @Override
    public List<EntregaOrgsHeader> getEntregas() throws ServiceException {
        IEntregaOrgsHeaderDao entregaOrgsHeaderDao = new EntregaOrgsHeaderDaoImpl();

        try {
            return entregaOrgsHeaderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public EntregaOrgsHeader getEntrega(Long shipmentHeaderId) throws ServiceException {
        IEntregaOrgsHeaderDao entregaOrgsHeaderDao = new EntregaOrgsHeaderDaoImpl();

        try {
            return entregaOrgsHeaderDao.get(shipmentHeaderId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlMaterialTransactions getTransactionById(Long transactionId) throws ServiceException {
        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();

        try {
            return mtlMaterialTransactionsDao.get(transactionId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::getMtlSystemItemsBySegment");

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
        Log.d(TAG, "EntregaOrgsServiceImpl::getMtlSystemItemsById");

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
    public List<MtlMaterialTransactions> getTransaccionsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransaccionsByShipmentInventory");
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransaccionsByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransaccionsByShipmentInventory::inventoryItemId: " + inventoryItemId);

        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        try {
            return mtlMaterialTransactionsDao.getAllByShipmentInventory(shipmentHeaderId, inventoryItemId);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Subinventario> getSubinventarios() throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::getSubinventarios");

        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try {
            return subinventarioDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::getLocalizadoresBySubinventario");
        Log.d(TAG, "EntregaOrgsServiceImpl::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

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
    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode, String locatorCode, String lote, String categoria, String atributo1, String atributo2, String atributo3, List<String> series, Double cantidad) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::addTransacctionInterface");
        Log.d(TAG, "EntregaOrgsServiceImpl::addTransacctionInterface::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaOrgsServiceImpl::addTransacctionInterface::transactionId: " + transactionId);
        Log.d(TAG, "EntregaOrgsServiceImpl::addTransacctionInterface::subinventoryCode: " + subinventoryCode);
        Log.d(TAG, "EntregaOrgsServiceImpl::addTransacctionInterface::locatorCode: " + locatorCode);
        Log.d(TAG, "EntregaOrgsServiceImpl::addTransacctionInterface::lote: " + lote);

        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlTransactionLotNumbersDao mtlTransactionLotNumbersDao = new MtlTransactionLotNumbersDaoImpl();
        try {
            boolean isControlLote = false;
            boolean isControlSerie = false;
            Localizador localizador = null;
            MtlTransactionLotNumbers mtlTransactionLotNumbers = null;

            // Fecha de hoy
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String sysDate = dateFormat.format(new Date());

            // Transactions
            MtlMaterialTransactions transaction = mtlMaterialTransactionsDao.get(transactionId);
            if (transaction == null) {
                throw new ServiceException(1, "Transaction con Id " + transactionId + " no existe en el sistema");
            }

            // Item
            MtlSystemItems item = mtlSystemItemsDao.get(transaction.getInventoryItemId());
            if (item == null) {
                throw new ServiceException(1, "SystemItem con Id " + transaction.getInventoryItemId() + " no existe en el sistema");
            }
            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
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
            if (transaction.getEntregaCreationDate() != null) {
                throw new ServiceException(1, "Producto ya fue ingresado");
            }

            if (cantidad.doubleValue() > transaction.getTransactionQuantity().doubleValue()) {
                throw new ServiceException(1, "la cantidad es mayor a lo especificado " + transaction.getTransactionQuantity());
            }

            if (isControlLote && lote == null) {
                throw new ServiceException(1, "Debe indicar el lote");
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

            if (isControlLote) {
                mtlTransactionLotNumbers = mtlTransactionLotNumbersDao.get(transactionId);
                if (!lote.equalsIgnoreCase(mtlTransactionLotNumbers.getLotNumber())) {
                    throw new ServiceException(1, "Lote " + lote + " no existe");
                } else {
                    mtlTransactionLotNumbers.setLotAttributeCategory(categoria);
                    mtlTransactionLotNumbers.setcAttribute1(atributo1);
                    mtlTransactionLotNumbers.setcAttribute2(atributo2);
                    mtlTransactionLotNumbers.setcAttribute3(atributo3);
                    mtlTransactionLotNumbers.setEntregaCreationDate(sysDate);
                }
            }

            transaction.setEntregaCreationDate(sysDate);
            transaction.setEntregaQuantity(cantidad);
            transaction.setSubinventory(subinventoryCode);
            if (localizador != null)
                transaction.setLocatorId(localizador.getIdLocalizador());
            if (isControlLote)
                transaction.setUseMtlLot(1L);
            else
                transaction.setUseMtlLot(0L);
            if (isControlSerie)
                transaction.setUseMtlSerial(1L);
            else
                transaction.setUseMtlSerial(0L);

            mtlMaterialTransactionsDao.update(transaction);
            if (isControlLote) {
                mtlTransactionLotNumbersDao.update(mtlTransactionLotNumbers);
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
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransactionsInterfaceByShipmentHeader");
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransactionsInterfaceByShipmentHeader::shipmentHeaderId: " + shipmentHeaderId);

        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        try {
            List<TransactionsDto> salida = new ArrayList<>();
            List<MtlMaterialTransactions> transactions = mtlMaterialTransactionsDao.getAllByShipmentEntrega(shipmentHeaderId);
            Log.d(TAG, "transactions size: " + transactions.size());
            for (MtlMaterialTransactions transaction : transactions) {
                TransactionsDto dto = new TransactionsDto();
                dto.setInterfaceTransactionId(transaction.getTransactionId());
                dto.setSegment1(transaction.getInventoryItemId().toString());
                dto.setCreationDate(transaction.getEntregaCreationDate());
                salida.add(dto);
            }
            return salida;
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public TransactionDetalleDto getTransactionDetalleById(Long transactionId) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransactionDetalleById");
        Log.d(TAG, "EntregaOrgsServiceImpl::getTransactionDetalleById::transactionId: " + transactionId);

        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlTransactionLotNumbersDao mtlTransactionLotNumbersDao = new MtlTransactionLotNumbersDaoImpl();

        try {
            TransactionDetalleDto dto = new TransactionDetalleDto();
            Localizador localizador = null;
            boolean isControlLote = false;
            boolean isControlSerie = false;

            MtlMaterialTransactions transaction = mtlMaterialTransactionsDao.get(transactionId);
            // Item
            MtlSystemItems item = mtlSystemItemsDao.get(transaction.getInventoryItemId());
            if (item == null) {
                throw new ServiceException(1, "SystemItem con Id " + transaction.getInventoryItemId() + " no existe en el sistema");
            }
            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
            }

            // Locator
            if (transaction.getLocatorId() != null) {
                localizador = localizadorDao.get(transaction.getLocatorId());
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + transaction.getLocatorId() + " no existe en el sistema");
                }
            }

            dto.setShipmentHeaderId(transaction.getShipmentHeaderId());
            dto.setTransactionId(transaction.getTransactionId());
            dto.setCantidad(transaction.getEntregaQuantity());
            dto.setSubinventoryCode(transaction.getSubinventory());
            dto.setLocatorCode(localizador != null ? localizador.getCodLocalizador() : null);
            dto.setLote(isControlLote);
            dto.setSerie(isControlSerie);

            if (isControlLote) {
                MtlTransactionLotNumbers lote = mtlTransactionLotNumbersDao.get(transactionId);
                if (lote != null) {
                    dto.setLote(lote.getLotNumber());
                    dto.setCategoria(lote.getLotAttributeCategory());
                    dto.setAtributo1(lote.getcAttribute1());
                    dto.setAtributo2(lote.getcAttribute2());
                    dto.setAtributo3(lote.getcAttribute3());
                }
            }
            return dto;

        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void deleteTransactionsById(Long transactionId) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::deleteTransactionsById");
        Log.d(TAG, "EntregaOrgsServiceImpl::deleteTransactionsById::transactionId: " + transactionId);

        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        try {
            MtlMaterialTransactions transaction = mtlMaterialTransactionsDao.get(transactionId);
            if (transaction == null) {
                throw new ServiceException(1, "Transacción " + transactionId + " no existe");
            }
            transaction.setEntregaCreationDate(null);
            transaction.setEntregaQuantity(null);
            transaction.setSubinventory(null);
            transaction.setLocatorId(null);
            mtlMaterialTransactionsDao.update(transaction);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void closeEntrega(Long shipmentHeaderId) throws ServiceException {
        Log.d(TAG, "EntregaOrgsServiceImpl::closeEntrega");
        Log.d(TAG, "EntregaOrgsServiceImpl::closeEntrega::shipmentHeaderId: " + shipmentHeaderId);

        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        IMtlTransactionLotNumbersDao mtlTransactionLotNumbersDao = new MtlTransactionLotNumbersDaoImpl();
        IMtlSerialNumbersDao mtlSerialNumbersDao = new MtlSerialNumbersDaoImpl();
        try {
            // SYSDATE
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String sysDate = dateFormat.format(new Date());

            List <MtlMaterialTransactions> transactions = mtlMaterialTransactionsDao.getAllByShipmentEntrega(shipmentHeaderId);
            if (transactions.size() > 0) {

                String shipmentNumber = transactions.get(0).getShipmentNumber();
                Long userId = transactions.get(0).getUserId();
                String receiptNum = transactions.get(0).getReceipNum();
                Long organizationId = transactions.get(0).getOrganizationId();
                Long shipToLocationId = transactions.get(0).getShipToLocationId();
                String transactionDate = transactions.get(0).getTransactionDate();
                Long groupId = transactions.get(0).getShipmentLineId();

                // Genera archivo Conteo
                String nombreArchivo = "I_R_" + shipmentNumber + ".csv";
                File tarjetaSD = Environment.getExternalStorageDirectory();
                File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
                File archivo = new File(Dir, nombreArchivo);
                FileWriter writer = new FileWriter(archivo);
                writer.write(
                        "HDR;"
                        + shipmentHeaderId.toString() + ";"  // HEADER_INTERFACE_ID
                        + "PENDING;"                         // PROCESSING_STATUS_CODE
                        + "INVENTORY;"                       // RECEIPT_SOURCE_CODE
                        + "NEW;"                             // TRANSACTION_TYPE
                        + sysDate + ";"                      // LAST_UPDATE_DATE
                        + userId.toString() + ";"            // LAST_UPDATED_BY
                        + sysDate + ";"                      // CREATION_DATE
                        + userId.toString() + ";"            // CREATED_BY
                        + shipmentNumber + ";"               // SHIPMENT_NUM
                        + receiptNum + ";"                   // RECEIPT_NUM
                        + organizationId.toString()          // FROM_ORGANIZATION_ID
                        + "288;"                             // SHIP_TO_ORGANIZATION_ID
                        + shipToLocationId.toString() + ";"  // LOCATION_ID
                        + transactionDate + ";"              // SHIPPED_DATE
                        + ";"                                // COMMENTS
                        + sysDate + ";"                      // TRANSACTION_DATE
                        + "288;"                             // ORG_ID
                        + groupId.toString() + ";"           // GROUP_ID
                        + "Y;"                               // VALIDATION_FLAG
                        + "FIN\r\n"
                );
                for (MtlMaterialTransactions transaction : transactions) {
                    if (transaction.getEntregaCreationDate() != null) {
                        writer.write(
                                "TRX;"
                                        + transaction.getTransactionId(). toString() + ";"     // INTERFACE_TRANSACTION_ID
                                        + sysDate + ";"                                        // LAST_UPDATE_DATE
                                        + userId + ";"                                         // LAST_UPDATED_BY
                                        + sysDate + ";"                                        // CREATION_DATE
                                        + userId + ";"                                         // CREATED_BY
                                        + "RECEIVE;"                                           // TRANSACTION_TYPE
                                        + sysDate + ";"                                        // TRANSACTION_DATE
                                        + "PENDING;"                                           // PROCESSING_STATUS_CODE
                                        + "BATCH;"                                             // PROCESSING_MODE_CODE
                                        + "PENDING;"                                           // TRANSACTION_STATUS_CODE
                                        + transaction.getEntregaQuantity().toString() + ";"    // QUANTITY
                                        + transaction.getTransactionUom() + ";"                // UNIT_OF_MEASURE
                                        + "RCV;"                                               // INTERFACE_SOURCE_CODE
                                        + "DELIVER;"                                           // AUTO_TRANSACT_CODE
                                        + "INVENTORY;"                                         // RECEIPT_SOURCE_CODE
                                        + transaction.getOrganizationId() + ";"                // FROM_ORGANIZATION_ID
                                        + "CLP;"                                               // CURRENCY_CODE
                                        + "NOT INSPECTED;"                                     // INSPECTION_STATUS_CODE
                                        + transaction.getSubinventory() + ";"                  // SUBINVENTORY
                                        + transaction.getLocatorId() + ";"                     // LOCATOR_ID
                                        + shipmentNumber + ";"                                 // SHIPMENT_NUM
                                        + transaction.getActualCost().toString() + ";"         // ACTUAL_COST
                                        + transaction.getUseMtlLot().toString() + ";"          // USE_MTL_LOT
                                        + transaction.getUseMtlSerial() + ";"                  // USE_MTL_SERIAL
                                        + groupId.toString() + ";"                             // GROUP_ID
                                        + "Y;"                                                 // VALIDATION_FLAG
                                        + transaction.getInventoryItemId().toString() + ";"    // ITEM_ID
                                        + transaction.getTransactionUom() + ";"                // UOM_CODE
                                        + userId.toString() + ";"                              // EMPLOYEE_ID
                                        + "INVENTORY;"                                         // SOURCE_DOCUMENT_CODE
                                        + "0;"                                                 // HEADER_INTERFACE_ID
                                        + shipmentHeaderId.toString() + ";"                    // SHIPMENT_HEADER_ID
                                        + "0;"                                                 // SHIPMENT_LINE_ID
                                        + "N;"                                                 // SERIE_RANGO
                                        + "FIN\r\n"
                        );
                    }
                }

                // Lotes
                List<MtlTransactionLotNumbers> lotes = mtlTransactionLotNumbersDao.getAllByShipmentHeaderId(shipmentHeaderId);
                for (MtlTransactionLotNumbers lote : lotes) {
                    if (lote.getEntregaCreationDate() != null) {
                        Double cantidadLote = 0D;
                        for (MtlMaterialTransactions transaction : transactions) {
                            if (transaction.getTransactionId().longValue() == lote.getTransactionId().longValue()) {
                                cantidadLote = transaction.getEntregaQuantity();
                            }
                        }
                        writer.write(
                                "LOT;"
                                + lote.getTransactionId() + ";"                       // TRANSACTION_INTERFACE_ID
                                + sysDate + ";"                                       // LAST_UPDATE_DATE
                                + userId + ";"                                        // LAST_UPDATED_BY
                                + sysDate + ";"                                       // CREATION_DATE
                                + "-1;"                                               // LAST_UPDATE_LOGIN
                                + lote.getLotNumber() + ";"                           // LOT_NUMBER
                                + cantidadLote + ";"                                  // TRANSACTION_QUANTITY
                                + cantidadLote + ";"                                  // PRIMARY_QUANTITY
                                + "NULL;"                                             // SERIAL_TRANSACTION_TEMP_ID
                                + "RCV;"                                              // PRODUCT_CODE
                                + lote.getTransactionId() + ";"                       // PRODUCT_TRANSACTION_ID
                                + ";"                                                 // SUPPLIER_LOT_NUMBER
                                + ";"                                                 // LOT_EXPIRATION_DATE
                                + lote.getLotAttributeCategory() + ";"                // ATTRIBUTE_CATEGORY
                                + lote.getcAttribute1() + ";"                         // ATTRIBUTE1
                                + lote.getcAttribute2() + ";"                         // ATTRIBUTE2
                                + lote.getcAttribute3() + ";"                         // ATTRIBUTE3
                                + "FIN\r\n"
                        );
                    }
                }

                // Series
                List<MtlSerialNumbers> serials = mtlSerialNumbersDao.getAllByShipmentHeaderId(shipmentHeaderId);
                for (MtlSerialNumbers serial : serials) {
                    if (serial.getEntregaCreationDate() != null) {
                        Long serialTransactionId = 0L;
                        for (MtlMaterialTransactions transaction : transactions) {
                            if (transaction.getInventoryItemId().longValue() == serial.getInventoryItemId().longValue()) {
                                serialTransactionId = transaction.getTransactionId();
                            }
                        }
                        writer.write(
                                "SER;"
                                + serialTransactionId + ";"                            // TRANSACTION_INTERFACE_ID
                                + sysDate + ";"                                        // LAST_UPDATE_DATE
                                + userId + ";"                                         // LAST_UPDATED_BY
                                + sysDate + ";"                                        // CREATION_DATE
                                + userId + ";"                                         // CREATED_BY
                                + "-1;"                                                // LAST_UPDATE_LOGIN
                                + serial.getSerialNumber() + ";"                       // FM_SERIAL_NUMBER
                                + serial.getSerialNumber() + ";"                       // TO_SERIAL_NUMBER
                                + "RCV;"                                               // PRODUCT_CODE
                                + serialTransactionId + ";"                            // PRODUCT_TRANSACTION_ID
                        );
                    }
                }

                // Elimina registros
                mtlSerialNumbersDao.deleteBySHipmentHeaderId(shipmentHeaderId);
                mtlTransactionLotNumbersDao.deleteByShipmentHeaderId(shipmentHeaderId);
                mtlMaterialTransactionsDao.deleteByShipmentHeaderId(shipmentHeaderId);

            }

        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        } catch(IOException e){
            throw new ServiceException(2, e.getMessage());
        }
    }

}
