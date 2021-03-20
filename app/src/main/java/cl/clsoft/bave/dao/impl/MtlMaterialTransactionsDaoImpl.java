package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlMaterialTransactionsDao;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountHeadersCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlMaterialTransactionsCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlMaterialTransactions;

public class MtlMaterialTransactionsDaoImpl extends GenericDao<MtlMaterialTransactions> implements IMtlMaterialTransactionsDao {

    @Override
    public void insert(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ID, mtlMaterialTransactions.getTransactionId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlMaterialTransactions.getInventoryItemId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ORGANIZATION_ID, mtlMaterialTransactions.getOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_TYPE_ID, mtlMaterialTransactions.getTransactionTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_ACTION_ID, mtlMaterialTransactions.getTransactionActionId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID, mtlMaterialTransactions.getTransactionSourceTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_NAME, mtlMaterialTransactions.getTransactionSourceName());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_QUANTITY, mtlMaterialTransactions.getTransactionQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_UOM, mtlMaterialTransactions.getTransactionUom());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_PRIMARY_QUANTITY, mtlMaterialTransactions.getPrimaryQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_DATE, mtlMaterialTransactions.getTransactionDate());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ACTUAL_COST, mtlMaterialTransactions.getActualCost());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSFER_ORGANIZATION_ID, mtlMaterialTransactions.getTransferOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIP_TO_LOCATION_ID, mtlMaterialTransactions.getShipToLocationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_RECEIPT_NUM, mtlMaterialTransactions.getReceipNum());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USER_ID, mtlMaterialTransactions.getUserId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_NUMBER, mtlMaterialTransactions.getShipmentNumber());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlMaterialTransactions.getShipmentHeaderId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID, mtlMaterialTransactions.getShipmentLineId());
        super.insert(MtlMaterialTransactionsCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlMaterialTransactions.getInventoryItemId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ORGANIZATION_ID, mtlMaterialTransactions.getOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_TYPE_ID, mtlMaterialTransactions.getTransactionTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_ACTION_ID, mtlMaterialTransactions.getTransactionActionId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID, mtlMaterialTransactions.getTransactionSourceTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_NAME, mtlMaterialTransactions.getTransactionSourceName());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_QUANTITY, mtlMaterialTransactions.getTransactionQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_UOM, mtlMaterialTransactions.getTransactionUom());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_PRIMARY_QUANTITY, mtlMaterialTransactions.getPrimaryQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_DATE, mtlMaterialTransactions.getTransactionDate());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ACTUAL_COST, mtlMaterialTransactions.getActualCost());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSFER_ORGANIZATION_ID, mtlMaterialTransactions.getTransferOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIP_TO_LOCATION_ID, mtlMaterialTransactions.getShipToLocationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_RECEIPT_NUM, mtlMaterialTransactions.getReceipNum());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USER_ID, mtlMaterialTransactions.getUserId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_NUMBER, mtlMaterialTransactions.getShipmentNumber());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlMaterialTransactions.getShipmentHeaderId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID, mtlMaterialTransactions.getShipmentLineId());
        super.update(MtlMaterialTransactionsCatalogo.TABLE, values, MtlMaterialTransactionsCatalogo.UPDATE, mtlMaterialTransactions.getTransactionId());
    }

    @Override
    public void delete(Long transactionId) throws DaoException {
        super.delete(MtlMaterialTransactionsCatalogo.TABLE, MtlMaterialTransactionsCatalogo.DELETE, transactionId);
    }

}
