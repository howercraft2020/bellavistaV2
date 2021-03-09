package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.RcvTransactionsInterfaceCatalogo;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public class RcvTransactionsInterfaceRowMapper implements RowMapper<RcvTransactionsInterface> {

    @Override
    public RcvTransactionsInterface mapRow(Cursor cursor, int position) throws SQLException {
        RcvTransactionsInterface entity = new RcvTransactionsInterface();
        entity.setInterfaceTransactionId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_INTERFACE_TRANSACTION_ID)));
        entity.setLastUpdatedDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CREATED_BY)));
        entity.setTransactionType(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_TYPE)));
        entity.setTransactionDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CREATION_DATE)));
        entity.setProcessingStatusCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PROCESSING_STATUS_CODE)));
        entity.setProcessingModeCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PROCESSING_MODE_CODE)));
        entity.setQuantity(cursor.getDouble(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_QUANTITY)));
        entity.setUnitOfMeasure(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_UNIT_OF_MEASURE)));
        entity.setItemId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_ITEM_ID)));
        entity.setItemDescription(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_ITEM_DESCRIPTION)));
        entity.setUomCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_UOM_CODE)));
        entity.setItemDescription(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_ITEM_DESCRIPTION)));
        entity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_EMPLOYEE_ID)));
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setShipmentLineId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SHIPMENT_LINE_ID)));
        entity.setShipToLocationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SHIP_TO_LOCATION_ID)));
        entity.setVendorId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_VENDOR_ID)));
        entity.setVendorSiteId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_VENDOR_SITE_ID)));
        entity.setToOrganizationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_TO_ORGANIZATION_ID)));
        entity.setSourceDocumentCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SOURCE_DOCUMENT_CODE)));
        entity.setParentTransactionId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PARENT_TRANSACTION_ID)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PO_LINE_ID)));
        entity.setPoLineLocation(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PO_LINE_LOCATION_ID)));
        entity.setPoUnitPrice(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PO_UNIT_PRICE)));
        entity.setCurrencyCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CODE)));
        entity.setCurrencyConversionType(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CONVERSION_TYPE)));
        entity.setCurrencyConversionRate(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CONVERSION_RATE)));
        entity.setCurrencyConversionDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_CURRENCY_CONVERSION_DATE)));
        entity.setPoDistributionId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PO_DISTRIBUTION_ID)));
        entity.setDestinationTypeCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_DESTINATION_TYPE_CODE)));
        entity.setLocationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_LOCATION_ID)));
        entity.setDeliverToLocationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_DELIVER_TO_LOCATION_ID)));
        entity.setInspectionStatusCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_INSPECTION_STATUS_CODE)));
        entity.setSubinventory(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SUBINVENTORY)));
        entity.setLocatorId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_LOCATOR_ID)));
        entity.setShipmentNum(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SHIPMENT_NUM)));
        entity.setUseMtlLot(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_USE_MTL_LOT)));
        entity.setUseMtlSerial(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_USE_MTL_SERIAL)));
        entity.setGroupId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_GROUP_ID)));
        entity.setTransactionStatusCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_STATUS_CODE)));
        entity.setAutoTransactCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_AUTO_TRANSACT_CODE)));
        entity.setReceiptSourceCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_RECEIPT_SOURCE_CODE)));
        entity.setValidationFlag(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_VALIDATION_FLAG)));
        entity.setOrgId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_ORG_ID)));
        entity.setPrimaryQuantity(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_PRIMARY_QUANTITY)));
        entity.setHeaderInterfaceId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_HEADER_INTERFACE_ID)));
        entity.setVendorSiteCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_VENDOR_SITE_CODE)));
        entity.setComments(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_COMMENTS)));
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(RcvTransactionsInterfaceCatalogo.COLUMN_SEGMENT1)));
        return entity;
    }

}
