package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IRcvTransactionsDao;
import cl.clsoft.bave.dao.catalogo.RcvShipmentHeadersCatalogo;
import cl.clsoft.bave.dao.catalogo.RcvTransactionsCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvTransactions;

public class RcvTransactionsDaoImpl extends GenericDao<RcvTransactions> implements IRcvTransactionsDao {
    @Override
    public void insert(RcvTransactions rcvTransactions) throws DaoException {
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
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_TYPE, rcvTransactions.getCurrencyConversionType());
        values.put(RcvTransactionsCatalogo.COLUMN_CURRENCY_CONVERSION_RATE, rcvTransactions.getCurrencyConversionRate());
        super.insert(RcvTransactionsCatalogo.TABLE, values);
    }

    @Override
    public void update(RcvTransactions rcvTransactions) throws DaoException {
        ContentValues values = new ContentValues();
        super.update(RcvTransactionsCatalogo.TABLE, values, RcvTransactionsCatalogo.UPDATE, rcvTransactions.getTransactionId());
    }
}
