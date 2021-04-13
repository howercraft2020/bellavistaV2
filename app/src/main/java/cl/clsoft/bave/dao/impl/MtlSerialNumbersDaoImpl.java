package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlSerialNumbersDao;
import cl.clsoft.bave.dao.catalogo.MtlMaterialTransactionsCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlSerialNumbersCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlSerialNumbersRowMapper;
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
        values.put(MtlSerialNumbersCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlSerialNumbers.getShipmentHeaderId());
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
        values.put(MtlSerialNumbersCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlSerialNumbers.getShipmentHeaderId());
        values.put(MtlSerialNumbersCatalogo.COLUMN_ENTREGA_CREATION_DATE, mtlSerialNumbers.getEntregaCreationDate());
        super.update(MtlSerialNumbersCatalogo.TABLE, values, MtlSerialNumbersCatalogo.UPDATE,  mtlSerialNumbers.getInventoryItemId(), mtlSerialNumbers.getSerialNumber());
    }

    @Override
    public void deleteBySHipmentHeaderId(Long shipmentHeaderId) throws DaoException {
        super.delete(MtlSerialNumbersCatalogo.TABLE, MtlSerialNumbersCatalogo.DELETE_BY_SHIPMENT_HEADER_ID, shipmentHeaderId);
    }

    @Override
    public List<MtlSerialNumbers> getAllByShipmentHeaderId(Long shipmentHeaderId) throws DaoException {
        return super.selectMany(MtlSerialNumbersCatalogo.SELECT_BY_SHIPMENT_HEADER_ID, new MtlSerialNumbersRowMapper(), shipmentHeaderId);
    }

    @Override
    public List<MtlSerialNumbers> getAllByShipmentHeaderIdInventoryItemId(Long shipmentHeaderId, Long inventoryItemId) throws DaoException {
        return super.selectMany(MtlSerialNumbersCatalogo.SELECT_BY_SHIPMENT_HEADER_ID_INVENTORY_ITEM_ID, new MtlSerialNumbersRowMapper(), shipmentHeaderId, inventoryItemId);
    }

}
