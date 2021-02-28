package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlTransactionLotsInterfaceDao;
import cl.clsoft.bave.dao.catalogo.MtlTransactionsLotsIfaceCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;

public class MtlTransactionLotsIfaceImpl extends GenericDao<MtlTransactionsLotsIface> implements IMtlTransactionLotsInterfaceDao {
    @Override
    public void insert(MtlTransactionsLotsIface mtlTransactionsLotsIface) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_MTL_TRANSACTION_LOTS_IFACE, mtlTransactionsLotsIface.getTransactionInterfaceId());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_LAST_UPDATE_DATE, mtlTransactionsLotsIface.getLastUpdateDate());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_LAST_UPDATE_BY, mtlTransactionsLotsIface.getLastUpdateBy());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_CREATION_DATE, mtlTransactionsLotsIface.getCreationDate());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_CREATED_BY, mtlTransactionsLotsIface.getCreatedBy());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_PO_HEADER_ID, mtlTransactionsLotsIface.getPoHeaderId());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_PO_LINE_ID, mtlTransactionsLotsIface.getPoLineId());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlTransactionsLotsIface.getInventoryItemId());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_LAST_UPDATE_LOGIN, mtlTransactionsLotsIface.getLastUpdateLogin());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_LOT_NUMBER, mtlTransactionsLotsIface.getLotNumber());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_TRANSACTION_QUANTITY, mtlTransactionsLotsIface.getTransactionQuantity());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_PRIMARY_QUANTITY, mtlTransactionsLotsIface.getPrimaryQuantity());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_SERIAL_TRANSACTION_TEMP_ID, mtlTransactionsLotsIface.getSerialTransactionTempId());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_PRODUCT_CODE, mtlTransactionsLotsIface.getProductCode());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_PRODUCT_TRANSACTION_ID,mtlTransactionsLotsIface.getProductTransactionId());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_SUPPLIER_LOT_NUMBER, mtlTransactionsLotsIface.getSupplierLotNumber());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_LOT_EXPIRATION_DATE, mtlTransactionsLotsIface.getLotExpirationDate());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_CATEGORY, mtlTransactionsLotsIface.getAttributeCategory());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_1, mtlTransactionsLotsIface.getAttrubute1());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_2, mtlTransactionsLotsIface.getAttrubute2());
        values.put(MtlTransactionsLotsIfaceCatalogo.COLUMN_ATTRIBUTE_3, mtlTransactionsLotsIface.getAttrubute3());
        super.insert(MtlTransactionsLotsIfaceCatalogo.TABLE, values);
    }
}
