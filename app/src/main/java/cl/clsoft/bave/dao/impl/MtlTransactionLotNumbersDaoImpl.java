package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlTransactionLotNumbersDao;
import cl.clsoft.bave.dao.catalogo.MtlSerialNumbersCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlTransactionLotNumbersCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;

public class MtlTransactionLotNumbersDaoImpl extends GenericDao<MtlTransactionLotNumbers> implements IMtlTransactionLotNumbersDao {

    @Override
    public void insert(MtlTransactionLotNumbers mtlTransactionLotNumbers) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_ID, mtlTransactionLotNumbers.getTransactionId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlTransactionLotNumbers.getInventoryItemId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_ORGANIZATION_ID, mtlTransactionLotNumbers.getOrganizationId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_LOT_NUMBER, mtlTransactionLotNumbers.getLotNumber());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_SERIAL_TRANSACTION_ID, mtlTransactionLotNumbers.getSerialTransactionId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_LOT_ATTRIBUTE_CATEGORY, mtlTransactionLotNumbers.getLotAttributeCategory());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE1, mtlTransactionLotNumbers.getcAttribute1());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE2, mtlTransactionLotNumbers.getcAttribute2());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE3, mtlTransactionLotNumbers.getcAttribute3());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlTransactionLotNumbers.getShipmentHeaderId());
        super.insert(MtlTransactionLotNumbersCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlTransactionLotNumbers mtlTransactionLotNumbers) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_ID, mtlTransactionLotNumbers.getTransactionId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlTransactionLotNumbers.getInventoryItemId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_ORGANIZATION_ID, mtlTransactionLotNumbers.getOrganizationId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_LOT_NUMBER, mtlTransactionLotNumbers.getLotNumber());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_SERIAL_TRANSACTION_ID, mtlTransactionLotNumbers.getSerialTransactionId());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_LOT_ATTRIBUTE_CATEGORY, mtlTransactionLotNumbers.getLotAttributeCategory());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE1, mtlTransactionLotNumbers.getcAttribute1());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE2, mtlTransactionLotNumbers.getcAttribute2());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_C_ATTRIBUTE3, mtlTransactionLotNumbers.getcAttribute3());
        values.put(MtlTransactionLotNumbersCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlTransactionLotNumbers.getShipmentHeaderId());
        super.update(MtlTransactionLotNumbersCatalogo.TABLE, values, MtlTransactionLotNumbersCatalogo.UPDATE, mtlTransactionLotNumbers.getTransactionId());
    }

}
