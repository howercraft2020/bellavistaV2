package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlTransactionsInterfaceCatalogo;
import cl.clsoft.bave.model.MtlTransactionsInterface;

public class MtlTransactionsInterfaceRowMapper implements RowMapper<MtlTransactionsInterface> {
    @Override
    public MtlTransactionsInterface mapRow(Cursor cursor, int position) throws SQLException {
        MtlTransactionsInterface entity = new MtlTransactionsInterface();
        entity.setTransactionInterfaceId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_INTERFACE_ID)));
        entity.setProcessFlag(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_PROCESS_FLAG)));
        entity.setTransactionMode(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_MODE)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_CREATED_BY)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setTransactionQuantity(cursor.getDouble(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_QUANTITY)));
        entity.setPrimaryQuantity(cursor.getDouble(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_PRIMARY_QUANTITY)));
        entity.setTransactionUom(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_UOM)));
        entity.setTransactionDate(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_DATE)));
        entity.setSubinventoryCode(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_SUBINVENTORY_CODE)));
        entity.setLocatorId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_LOCATOR_ID)));
        entity.setTransactionSourceName(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_SOURCE_NAME)));
        entity.setTransactionSourceTypeId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID)));
        entity.setTransactionActionId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_ACTION_ID)));
        entity.setTransactionTypeId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_TYPE_ID)));
        entity.setTransactionReference(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_REFERENCE)));
        entity.setTransferSubinventory(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSFER_SUBINVENTORY)));
        entity.setTransferOrganization(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSFER_ORGANIZATION)));
        entity.setTransferLocator(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSFER_LOCATOR)));
        entity.setSourceCode(cursor.getString(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_SOURCE_CODE)));
        entity.setSourceLineId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_SOURCE_LINE_ID)));
        entity.setSourceHeaderId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsInterfaceCatalogo.COLUMN_SOURCE_HEADER_ID)));

        return entity;
    }


}
