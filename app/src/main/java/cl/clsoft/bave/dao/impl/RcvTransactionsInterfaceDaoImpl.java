package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IRcvTransactionsInterfaceDao;
import cl.clsoft.bave.dao.catalogo.RcvTransactionsInterfaceCatalogo;
import cl.clsoft.bave.dao.rowmapper.RcvTransactionsInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public class RcvTransactionsInterfaceDaoImpl extends GenericDao<RcvTransactionsInterface> implements IRcvTransactionsInterfaceDao {

    @Override
    public List<RcvTransactionsInterface> getArticulos(Long id) throws DaoException {
        return super.selectMany(RcvTransactionsInterfaceCatalogo.SELECT, new RcvTransactionsInterfaceRowMapper(),id);
    }

    @Override
    public void insert(RcvTransactionsInterface rcvTransactionsInterface) {
        ContentValues values = new ContentValues();
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_INTERFACE_TRANSACTION_ID, rcvTransactionsInterface.getInterfaceTransactionId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE, rcvTransactionsInterface.getLastUpdatedDate());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATED_BY, rcvTransactionsInterface.getLastUpdatedBy());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_CREATION_DATE, rcvTransactionsInterface.getCreationDate());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_CREATED_BY, rcvTransactionsInterface.getCreatedBy());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_TYPE, rcvTransactionsInterface.getTransactionType());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_DATE, rcvTransactionsInterface.getTransactionDate());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_QUANTITY, rcvTransactionsInterface.getQuantity());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_UNIT_OF_MEASURE, rcvTransactionsInterface.getUnitOfMeasure());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_ITEM_ID, rcvTransactionsInterface.getItemId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_ITEM_DESCRIPTION, rcvTransactionsInterface.getItemDescription());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_UOM_CODE, rcvTransactionsInterface.getUomCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_SHIP_TO_LOCATION_ID, rcvTransactionsInterface.getShipToLocationId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PRIMARY_QUANTITY, rcvTransactionsInterface.getPrimaryQuantity());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_RECEIPT_SOURCE_CODE, rcvTransactionsInterface.getReceiptSourceCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_VENDOR_ID, rcvTransactionsInterface.getVendorId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_VENDOR_SITE_ID, rcvTransactionsInterface.getVendorSiteId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_TO_ORGANIZATION_ID, rcvTransactionsInterface.getToOrganizationId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PO_HEADER_ID, rcvTransactionsInterface.getPoHeaderId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PO_LINE_ID, rcvTransactionsInterface.getPoLineId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PO_LINE_LOCATION_ID, rcvTransactionsInterface.getPoLineLocation());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PO_UNIT_PRICE, rcvTransactionsInterface.getPoUnitPrice());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CODE, rcvTransactionsInterface.getCurrencyCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_SOURCE_DOCUMENT_CODE, rcvTransactionsInterface.getSourceDocumentCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CONVERSION_TYPE, rcvTransactionsInterface.getCurrencyConversionType());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CONVERSION_RATE, rcvTransactionsInterface.getCurrencyConversionRate());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CONVERSION_DATE, rcvTransactionsInterface.getCurrencyConversionDate());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PO_DISTRIBUTION_ID, rcvTransactionsInterface.getPoDistributionId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_DESTINATION_TYPE_CODE, rcvTransactionsInterface.getDestinationTypeCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_LOCATION_ID, rcvTransactionsInterface.getLocationId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_DELIVER_TO_LOCATION_ID, rcvTransactionsInterface.getDeliverToLocationId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_INSPECTION_STATUS_CODE, rcvTransactionsInterface.getInspectionStatusCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_HEADER_INTERFACE_ID, rcvTransactionsInterface.getHeaderInterfaceId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_VENDOR_SITE_CODE, rcvTransactionsInterface.getVendorSiteCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_PROCESSING_STATUS_CODE, rcvTransactionsInterface.getProcessingStatusCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_COMMENTS, rcvTransactionsInterface.getComments());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_USE_MTL_LOT, rcvTransactionsInterface.getUseMtlLot());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_USE_MTL_SERIAL, rcvTransactionsInterface.getUseMtlSerial());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_STATUS_CODE, rcvTransactionsInterface.getTransactionStatusCode());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_VALIDATION_FLAG, rcvTransactionsInterface.getValidationFlag());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_ORG_ID, rcvTransactionsInterface.getOrgId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_GROUP_ID, rcvTransactionsInterface.getGroupId());
        values.put(RcvTransactionsInterfaceCatalogo.COLUMN_AUTO_TRANSACT_CODE, rcvTransactionsInterface.getAutoTransactCode());
        super.insert(RcvTransactionsInterfaceCatalogo.TABLE, values);

    }
}
