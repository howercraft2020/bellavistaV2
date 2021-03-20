package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlSerialNumbersDao;
import cl.clsoft.bave.dao.catalogo.MtlMaterialTransactionsCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlSerialNumbersCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSerialNumbers;

public class MtlSerialNumbersDaoImpl extends GenericDao<MtlSerialNumbers> implements IMtlSerialNumbersDao {

    @Override
    public void insert(MtlSerialNumbers mtlSerialNumbers) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlSerialNumbersCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlSerialNumbers.getInventoryItemId());
        values.put(MtlSerialNumbersCatalogo.COLUMN_SERIAL_NUMBER, mtlSerialNumbers.getSerialNumber());
        values.put(MtlSerialNumbersCatalogo.COLUMN_LAST_UPDATE_DATE, mtlSerialNumbers.getLastUpdateDate());
        values.put(MtlSerialNumbersCatalogo.COLUMN_LAST_UPDATED_BY, mtlSerialNumbers.getLastUpdateBy());
        values.put(MtlSerialNumbersCatalogo.COLUMN_CREATION_DATE, mtlSerialNumbers.getCreationDate());
        values.put(MtlSerialNumbersCatalogo.COLUMN_CREATED_BY, mtlSerialNumbers.getCreatedBy());
        values.put(MtlSerialNumbersCatalogo.COLUMN_LOT_NUMBER, mtlSerialNumbers.getLotNumber());
        values.put(MtlSerialNumbersCatalogo.COLUMN_CURRENT_ORGANIZATION_ID, mtlSerialNumbers.getCurrentOrganizationId());
        super.insert(MtlSerialNumbersCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlSerialNumbers mtlSerialNumbers) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlSerialNumbersCatalogo.COLUMN_LAST_UPDATE_DATE, mtlSerialNumbers.getLastUpdateDate());
        values.put(MtlSerialNumbersCatalogo.COLUMN_LAST_UPDATED_BY, mtlSerialNumbers.getLastUpdateBy());
        values.put(MtlSerialNumbersCatalogo.COLUMN_CREATION_DATE, mtlSerialNumbers.getCreationDate());
        values.put(MtlSerialNumbersCatalogo.COLUMN_CREATED_BY, mtlSerialNumbers.getCreatedBy());
        values.put(MtlSerialNumbersCatalogo.COLUMN_LOT_NUMBER, mtlSerialNumbers.getLotNumber());
        values.put(MtlSerialNumbersCatalogo.COLUMN_CURRENT_ORGANIZATION_ID, mtlSerialNumbers.getCurrentOrganizationId());
        super.update(MtlSerialNumbersCatalogo.TABLE, values, MtlSerialNumbersCatalogo.UPDATE,  mtlSerialNumbers.getInventoryItemId(), mtlSerialNumbers.getSerialNumber());
    }

}
