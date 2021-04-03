package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IRcvTransactionsDao;
import cl.clsoft.bave.dao.catalogo.RcvTransactionsCatalogo;
import cl.clsoft.bave.dao.rowmapper.RcvTransactionsRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactions;

public class RcvTransactionsDaoImpl extends GenericDao<RcvTransactions> implements IRcvTransactionsDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(RcvTransactions rcvTransactions) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(RcvTransactionsCatalogo.COLUMN_TRANSACTION_ID, rcvTransactions.getTransactionId());
        values.put(RcvTransactionsCatalogo.COLUMN_LAST_UPDATE_DATE, rcvTransactions.getLastUpdateDate());
        values.put(RcvTransactionsCatalogo.COLUMN_LAST_UPDATED_BY, rcvTransactions.getLastUpdatedBy());
        values.put(RcvTransactionsCatalogo.COLUMN_CREATION_DATE, rcvTransactions.getCreationDate());
        values.put(RcvTransactionsCatalogo.COLUMN_CREATED_BY, rcvTransactions.getCreatedBy());
        values.put(RcvTransactionsCatalogo.COLUMN_TRANSACTION_TYPE, rcvTransactions.getTransactionType());
        values.put(RcvTransactionsCatalogo.COLUMN_TRANSACTION_DATE, rcvTransactions.getTransactionDate());
        values.put(RcvTransactionsCatalogo.COLUMN_QUANTITY, rcvTransactions.getQuantity());
        values.put(RcvTransactionsCatalogo.COLUMN_UNIT_OF_MEASURE, rcvTransactions.getUnitOfMeasure());
        values.put(RcvTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID, rcvTransactions.getShipmentHeaderId());
        values.put(RcvTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID, rcvTransactions.getShipmentLineId());
        values.put(RcvTransactionsCatalogo.COLUMN_SOURCE_DOCUMENT_CODE, rcvTransactions.getSourceDocumentCode());
        values.put(RcvTransactionsCatalogo.COLUMN_DESTINATION_TYPE_CODE, rcvTransactions.getDestinationTypeCode());
        values.put(RcvTransactionsCatalogo.COLUMN_PRIMARY_UNIT_OF_MEASURE, rcvTransactions.getPrimaryUnitOfMeasure());
        values.put(RcvTransactionsCatalogo.COLUMN_UOM_CODE, rcvTransactions.getUomCode());
        values.put(RcvTransactionsCatalogo.COLUMN_EMPLOYEE_ID, rcvTransactions.getEmployeeId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_HEADER_ID, rcvTransactions.getPoHeaderId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_LINE_ID, rcvTransactions.getPoLineId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_LINE_LOCATION_ID, rcvTransactions.getPoLineLocationId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_DISTRIBUTION_ID, rcvTransactions.getPoDistributionId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_UNIT_PRICE, rcvTransactions.getPoUnitPrice());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CODE, rcvTransactions.getCurrencyCode());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_TYPE, rcvTransactions.getCurrencyConversionType());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_RATE, rcvTransactions.getCurrencyConversionRate());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_DATE, rcvTransactions.getCurrencyConversionDate());
        values.put(RcvTransactionsCatalogo.COLUMN_DELIVER_TO_LOCATION_ID, rcvTransactions.getDeliverToLocationId());
        values.put(RcvTransactionsCatalogo.COLUMN_VENDOR_ID, rcvTransactions.getVendorId());
        values.put(RcvTransactionsCatalogo.COLUMN_VENDOR_SITE_ID, rcvTransactions.getVendorSiteId());
        values.put(RcvTransactionsCatalogo.COLUMN_ORGANIZATION_ID, rcvTransactions.getOrganizationId());
        values.put(RcvTransactionsCatalogo.COLUMN_LOCATION_ID, rcvTransactions.getLocationId());
        values.put(RcvTransactionsCatalogo.COLUMN_INSPECTION_STATUS_CODE, rcvTransactions.getInspectionStatusCode());
        values.put(RcvTransactionsCatalogo.COLUMN_DESTINATION_CONTEXT, rcvTransactions.getDestinationContext());
        values.put(RcvTransactionsCatalogo.COLUMN_INTERFACE_TRANSACTION_ID, rcvTransactions.getInterfaceTransactionId());
        values.put(RcvTransactionsCatalogo.COLUMN_ITEM_ID, rcvTransactions.getItemId());
        values.put(RcvTransactionsCatalogo.COLUMN_LINE_NUM, rcvTransactions.getLineNum());
        super.insert(RcvTransactionsCatalogo.TABLE, values);
    }

    @Override
    public void update(RcvTransactions rcvTransactions) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(RcvTransactionsCatalogo.COLUMN_LAST_UPDATE_DATE, rcvTransactions.getLastUpdateDate());
        values.put(RcvTransactionsCatalogo.COLUMN_LAST_UPDATED_BY, rcvTransactions.getLastUpdatedBy());
        values.put(RcvTransactionsCatalogo.COLUMN_CREATION_DATE, rcvTransactions.getCreationDate());
        values.put(RcvTransactionsCatalogo.COLUMN_CREATED_BY, rcvTransactions.getCreatedBy());
        values.put(RcvTransactionsCatalogo.COLUMN_TRANSACTION_TYPE, rcvTransactions.getTransactionType());
        values.put(RcvTransactionsCatalogo.COLUMN_TRANSACTION_DATE, rcvTransactions.getTransactionDate());
        values.put(RcvTransactionsCatalogo.COLUMN_QUANTITY, rcvTransactions.getQuantity());
        values.put(RcvTransactionsCatalogo.COLUMN_UNIT_OF_MEASURE, rcvTransactions.getUnitOfMeasure());
        values.put(RcvTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID, rcvTransactions.getShipmentHeaderId());
        values.put(RcvTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID, rcvTransactions.getShipmentLineId());
        values.put(RcvTransactionsCatalogo.COLUMN_SOURCE_DOCUMENT_CODE, rcvTransactions.getSourceDocumentCode());
        values.put(RcvTransactionsCatalogo.COLUMN_DESTINATION_TYPE_CODE, rcvTransactions.getDestinationTypeCode());
        values.put(RcvTransactionsCatalogo.COLUMN_PRIMARY_UNIT_OF_MEASURE, rcvTransactions.getPrimaryUnitOfMeasure());
        values.put(RcvTransactionsCatalogo.COLUMN_UOM_CODE, rcvTransactions.getUomCode());
        values.put(RcvTransactionsCatalogo.COLUMN_EMPLOYEE_ID, rcvTransactions.getEmployeeId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_HEADER_ID, rcvTransactions.getPoHeaderId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_LINE_ID, rcvTransactions.getPoLineId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_LINE_LOCATION_ID, rcvTransactions.getPoLineLocationId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_DISTRIBUTION_ID, rcvTransactions.getPoDistributionId());
        values.put(RcvTransactionsCatalogo.COLUMN_PO_UNIT_PRICE, rcvTransactions.getPoUnitPrice());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CODE, rcvTransactions.getCurrencyCode());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_TYPE, rcvTransactions.getCurrencyConversionType());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_RATE, rcvTransactions.getCurrencyConversionRate());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_DATE, rcvTransactions.getCurrencyConversionDate());
        values.put(RcvTransactionsCatalogo.COLUMN_DELIVER_TO_LOCATION_ID, rcvTransactions.getDeliverToLocationId());
        values.put(RcvTransactionsCatalogo.COLUMN_VENDOR_ID, rcvTransactions.getVendorId());
        values.put(RcvTransactionsCatalogo.COLUMN_VENDOR_SITE_ID, rcvTransactions.getVendorSiteId());
        values.put(RcvTransactionsCatalogo.COLUMN_ORGANIZATION_ID, rcvTransactions.getOrganizationId());
        values.put(RcvTransactionsCatalogo.COLUMN_LOCATION_ID, rcvTransactions.getLocationId());
        values.put(RcvTransactionsCatalogo.COLUMN_INSPECTION_STATUS_CODE, rcvTransactions.getInspectionStatusCode());
        values.put(RcvTransactionsCatalogo.COLUMN_DESTINATION_CONTEXT, rcvTransactions.getDestinationContext());
        values.put(RcvTransactionsCatalogo.COLUMN_INTERFACE_TRANSACTION_ID, rcvTransactions.getInterfaceTransactionId());
        values.put(RcvTransactionsCatalogo.COLUMN_ITEM_ID, rcvTransactions.getItemId());
        values.put(RcvTransactionsCatalogo.COLUMN_LINE_NUM, rcvTransactions.getLineNum());
        super.update(RcvTransactionsCatalogo.TABLE, values, RcvTransactionsCatalogo.UPDATE, rcvTransactions.getTransactionId());
    }

    @Override
    public void delete(Long transactionId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::delete");
        Log.d(TAG, "RcvTransactionsDaoImpl::delete::transactionId: " + transactionId);

        super.delete(RcvTransactionsCatalogo.TABLE, RcvTransactionsCatalogo.DELETE, transactionId);
    }

    @Override
    public void deleteByShipmenHeader(Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::deleteByShipmenHeader");
        Log.d(TAG, "RcvTransactionsDaoImpl::deleteByShipmenHeader::shipmentHeaderId: " + shipmentHeaderId);

        super.delete(RcvTransactionsCatalogo.TABLE, RcvTransactionsCatalogo.DELETE_BY_SHIPMENT_HEADER_ID, shipmentHeaderId);
    }

    @Override
    public RcvTransactions get(Long transactionId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::get");
        Log.d(TAG, "RcvTransactionsDaoImpl::get::transactionId: " + transactionId);

        return super.selectOne(RcvTransactionsCatalogo.SELECT, new RcvTransactionsRowMapper(), transactionId);
    }

    @Override
    public List<RcvTransactions> getAllByShipment(Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllByShipment");
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllByShipment::shipmentHeaderId: " + shipmentHeaderId);

        return super.selectMany(RcvTransactionsCatalogo.SELECT_ALL_BY_SHIPMENT, new RcvTransactionsRowMapper(), shipmentHeaderId);
    }

    @Override
    public List<RcvTransactions> getAllByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllByShipmentInventory");
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllByShipmentInventory::inventoryItemId: " + inventoryItemId);

        return super.selectMany(RcvTransactionsCatalogo.SELECT_ALL_BY_SHIPMENT_INVENTORY, new RcvTransactionsRowMapper(), shipmentHeaderId, inventoryItemId);
    }

    @Override
    public List<RcvTransactions> getAllDisponiblesByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllDisponiblesByShipmentInventory");
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllDisponiblesByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "RcvTransactionsDaoImpl::getAllDisponiblesByShipmentInventory::inventoryItemId: " + inventoryItemId);

        return super.selectMany(RcvTransactionsCatalogo.SELECT_ALL_DISPONIBLES_BY_SHIPMENT_INVENTORY, new RcvTransactionsRowMapper(), shipmentHeaderId, inventoryItemId, shipmentHeaderId);
    }

    @Override
    public List<String> getSegmentsByShipment(Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "RcvTransactionsDaoImpl::getSegmentsByShipment");
        Log.d(TAG, "RcvTransactionsDaoImpl::getSegmentsByShipment::shipmentHeaderId: " + shipmentHeaderId);

        return super.selectManyString(RcvTransactionsCatalogo.SELECT_SEGMENT_BY_SHIPMENT, shipmentHeaderId);
    }

}
