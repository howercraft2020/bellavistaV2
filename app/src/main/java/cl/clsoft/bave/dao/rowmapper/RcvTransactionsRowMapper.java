package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.RcvTransactionsCatalogo;
import cl.clsoft.bave.model.RcvTransactions;

public class RcvTransactionsRowMapper implements  RowMapper<RcvTransactions>{
    @Override
    public RcvTransactions mapRow(Cursor cursor, int position) throws SQLException {
        RcvTransactions entity = new RcvTransactions();
        entity.setTransactionId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_TRANSACTION_ID)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_CREATED_BY)));
        entity.setTransactionType(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_TRANSACTION_TYPE)));
        entity.setTransactionDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_TRANSACTION_DATE)));
        entity.setQuantity(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_QUANTITY)));
        entity.setUnitOfMeasure(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_UNIT_OF_MEASURE)));
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setShipmentLineId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID)));
        entity.setSourceDocumentCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_SOURCE_DOCUMENT_CODE)));
        entity.setDestinationTypeCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_DESTINATION_TYPE_CODE)));
        entity.setPrimaryUnitOfMeasure(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_PRIMARY_UNIT_OF_MEASURE)));
        entity.setUomCode(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_UOM_CODE)));
        entity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_EMPLOYEE_ID)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_PO_LINE_ID)));
        entity.setPoLineLocationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_PO_LINE_LOCATION_ID)));
        entity.setPoDistributionId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_PO_DISTRIBUTION_ID)));
        entity.setPoUnitPrice(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_PO_UNIT_PRICE)));
        entity.setCurrencyConversionType(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_TYPE)));
        entity.setCurrencyConversionRate(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_RATE)));
        entity.setCurrencyConversionDate(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_DATE)));
        entity.setDeliverToLocationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_DELIVER_TO_LOCATION_ID)));
        entity.setVendorId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_VENDOR_ID)));
        entity.setVendorSiteId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_VENDOR_SITE_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setLocationId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_LOCATION_ID)));
        entity.setInspectionStatusCode(cursor.getColumnName(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_INSPECTION_STATUS_CODE)));
        entity.setDestinationContext(cursor.getString(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_DESTINATION_CONTEXT)));
        entity.setInterfaceTransactionId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_INTERFACE_TRANSACTION_ID)));
        entity.setItemId(cursor.getLong(cursor.getColumnIndex(RcvTransactionsCatalogo.COLUMN_ITEM_ID)));

        return entity;
    }
}
