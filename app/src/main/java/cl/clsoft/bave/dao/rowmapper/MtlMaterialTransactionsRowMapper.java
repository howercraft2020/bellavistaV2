package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlMaterialTransactionsCatalogo;
import cl.clsoft.bave.model.MtlMaterialTransactions;

public class MtlMaterialTransactionsRowMapper implements RowMapper<MtlMaterialTransactions> {
    @Override
    public MtlMaterialTransactions mapRow(Cursor cursor, int position) throws SQLException {
        MtlMaterialTransactions entity = new MtlMaterialTransactions();
        entity.setTransactionId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_ID)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setTransactionTypeId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_TYPE_ID)));
        entity.setTransactionActionId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_ACTION_ID)));
        entity.setTransactionSourceTypeId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID)));
        entity.setTransactionSourceName(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_NAME)));
        entity.setTransactionQuantity(cursor.getDouble(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_QUANTITY)));
        entity.setTransactionUom(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_UOM)));
        entity.setPrimaryQuantity(cursor.getDouble(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_PRIMARY_QUANTITY)));
        entity.setTransactionDate(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_DATE)));
        entity.setActualCost(cursor.getDouble(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_ACTUAL_COST)));
        entity.setTransferOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_TRANSFER_ORGANIZATION_ID)));
        entity.setShipToLocationId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_SHIP_TO_LOCATION_ID)));
        entity.setReceipNum(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_RECEIPT_NUM)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_USER_ID)));
        entity.setShipmentNumber(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_NUMBER)));
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setShipmentLineId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID)));
        entity.setEntregaCreationDate(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_ENTREGA_CREATION_DATE)));
        entity.setEntregaQuantity(cursor.getDouble(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_ENTREGA_QUANTITY)));
        entity.setSubinventory(cursor.getString(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_SUBINVENTORY)));
        entity.setLocatorId(cursor.getLong(cursor.getColumnIndex(MtlMaterialTransactionsCatalogo.COLUMN_LOCATOR_ID)));
        return entity;
    }
}
