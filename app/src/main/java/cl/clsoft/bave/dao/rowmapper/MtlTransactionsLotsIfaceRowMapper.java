package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlSystemItemsCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlTransactionsLotsIfaceCatalogo;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;

public class MtlTransactionsLotsIfaceRowMapper implements RowMapper<MtlTransactionsLotsIface>{
    @Override
    public MtlTransactionsLotsIface mapRow(Cursor cursor, int position) throws SQLException {
        MtlTransactionsLotsIface entity = new MtlTransactionsLotsIface();
        entity.setTransactionInterfaceId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_MTL_TRANSACTION_LOTS_IFACE)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdateBy(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_LAST_UPDATE_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_CREATED_BY)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_PO_LINE_ID)));
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setLastUpdateLogin(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_LAST_UPDATE_LOGIN)));
        entity.setLotNumber(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_LOT_NUMBER)));
        entity.setTransactionQuantity(cursor.getDouble(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_TRANSACTION_QUANTITY)));
        entity.setPrimaryQuantity(cursor.getDouble(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_PRIMARY_QUANTITY)));
        entity.setSerialTransactionTempId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_SERIAL_TRANSACTION_TEMP_ID)));
        entity.setProductCode(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_PRODUCT_CODE)));
        entity.setProductTransactionId(cursor.getLong(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_PRODUCT_TRANSACTION_ID)));
        entity.setSupplierLotNumber(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_SUPPLIER_LOT_NUMBER)));
        entity.setLotExpirationDate(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_LOT_EXPIRATION_DATE)));
        entity.setAttributeCategory(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_CATEGORY)));
        entity.setAttrubute1(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_1)));
        entity.setAttrubute2(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_2)));
        entity.setAttrubute3(cursor.getString(cursor.getColumnIndex(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_3)));
        return entity;
    }
}
