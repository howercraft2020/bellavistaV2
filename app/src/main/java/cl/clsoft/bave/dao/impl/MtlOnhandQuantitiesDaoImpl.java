package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.catalogo.MtlOnhandQuantitiesCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public class MtlOnhandQuantitiesDaoImpl extends GenericDao<MtlOnhandQuantities> implements IMtlOnhandQuantitiesDao {

    @Override
    public void insert(MtlOnhandQuantities mtlOnhandQuantities) throws DaoException {
        try {
            ContentValues values = new ContentValues();
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlOnhandQuantities.getInventoryItemId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_ORGANIZATION_ID, mtlOnhandQuantities.getOrganizationId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_SUBINVENTORY_CODE, mtlOnhandQuantities.getSubinventoryCode());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_LOCATOR_ID, mtlOnhandQuantities.getLocatorId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_LOT_NUMBER, mtlOnhandQuantities.getLotNumber());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_SERIAL_NUMBER, mtlOnhandQuantities.getSerialNumber());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_PRIMARY_TRANSACTION_QUANTITY, mtlOnhandQuantities.getPrimaryTransactionQuantity());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_USER_ID, mtlOnhandQuantities.getUserId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_STATUS_ID, mtlOnhandQuantities.getStatusId());
            super.insert(MtlOnhandQuantitiesCatalogo.TABLE, values);
        } catch (Exception ex) {
            throw new DaoException(1, ex.getMessage());
        }
    }
}
