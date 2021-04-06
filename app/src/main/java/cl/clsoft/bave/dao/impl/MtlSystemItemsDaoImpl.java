package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.catalogo.MtlSystemItemsCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlSystemItemsRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSystemItems;

public class MtlSystemItemsDaoImpl extends GenericDao<MtlSystemItems> implements IMtlSystemItemsDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(MtlSystemItems mtlSystemItems) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(MtlSystemItemsCatalogo.COLUMN_ID, mtlSystemItems.getInventoryItemId());
        values.put(MtlSystemItemsCatalogo.COLUMN_DESCRIPTION, mtlSystemItems.getDescription());
        values.put(MtlSystemItemsCatalogo.COLUMN_LONG_DESCRIPTION, mtlSystemItems.getLongDescription());
        values.put(MtlSystemItemsCatalogo.COLUMN_SEGMENT1, mtlSystemItems.getSegment1());
        values.put(MtlSystemItemsCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlSystemItems.getPrimaryUomCode());
        values.put(MtlSystemItemsCatalogo.COLUMN_LOT_CONTROL_CODE, mtlSystemItems.getLotControlCode());
        values.put(MtlSystemItemsCatalogo.COLUMN_SHELF_LIFE_CODE, mtlSystemItems.getShelfLifeCode());
        values.put(MtlSystemItemsCatalogo.COLUMN_SERIAL_NUMBER_CONTROL_CODE, mtlSystemItems.getSerialNumberControlCode());
        super.insert(MtlSystemItemsCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlSystemItems mtlSystemItems) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(MtlSystemItemsCatalogo.COLUMN_DESCRIPTION, mtlSystemItems.getDescription());
        values.put(MtlSystemItemsCatalogo.COLUMN_LONG_DESCRIPTION, mtlSystemItems.getLongDescription());
        values.put(MtlSystemItemsCatalogo.COLUMN_SEGMENT1, mtlSystemItems.getSegment1());
        values.put(MtlSystemItemsCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlSystemItems.getPrimaryUomCode());
        values.put(MtlSystemItemsCatalogo.COLUMN_LOT_CONTROL_CODE, mtlSystemItems.getLotControlCode());
        values.put(MtlSystemItemsCatalogo.COLUMN_SHELF_LIFE_CODE, mtlSystemItems.getShelfLifeCode());
        values.put(MtlSystemItemsCatalogo.COLUMN_SERIAL_NUMBER_CONTROL_CODE, mtlSystemItems.getSerialNumberControlCode());
        super.update(MtlSystemItemsCatalogo.TABLE, values, MtlSystemItemsCatalogo.UPDATE, mtlSystemItems.getInventoryItemId());
    }

    @Override
    public void delete(Long inventoryItemId) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::delete");
        Log.d(TAG, "MtlSystemItemsDaoImpl::delete::inventoryItemId: " + inventoryItemId);

        super.delete(MtlSystemItemsCatalogo.TABLE, MtlSystemItemsCatalogo.DELETE, inventoryItemId);
    }

    @Override
    public void deleteAll() throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::deleteAll");

        super.delete(MtlSystemItemsCatalogo.TABLE, null, null);
    }

    @Override
    public MtlSystemItems get(Long inventoryItemId) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::get");
        Log.d(TAG, "MtlSystemItemsDaoImpl::get::inventoryItemId: " + inventoryItemId);

        return super.selectOne(MtlSystemItemsCatalogo.SELECT, new MtlSystemItemsRowMapper(), inventoryItemId);
    }

    @Override
    public MtlSystemItems getBySegment(String segment) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getBySegment");
        Log.d(TAG, "MtlSystemItemsDaoImpl::getBySegment::segment: " + segment);

        return super.selectOne(MtlSystemItemsCatalogo.SELECT_BY_SEGMENT, new MtlSystemItemsRowMapper(), segment);
    }

    @Override
    public List<MtlSystemItems> getAll() throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAll");

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL, new MtlSystemItemsRowMapper());
    }

    @Override
    public List<MtlSystemItems> getAllByDescription(String pattern) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescription");
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescription::pattern: " + pattern);

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL_BY_DESCRIPTION, new MtlSystemItemsRowMapper(), pattern);
    }

    @Override
    public List<MtlSystemItems> getAllByDescriptionShipment(String pattern, Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionShipment");
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionShipment::pattern: " + pattern);
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionShipment::shipmentHeaderId: " + shipmentHeaderId);

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL_BY_DESCRIPTION_SHIPMENT, new MtlSystemItemsRowMapper(), pattern, shipmentHeaderId);
    }

    @Override
    public List<MtlSystemItems> getAllByDescriptionPoHeaderId(String pattern, Long poHeaderId) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionPoHeaderId");
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionPoHeaderId::pattern: " + pattern);
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionPoHeaderId::poHeaderId: " + poHeaderId);

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL_BY_DESCRIPTION_POHEADERID, new MtlSystemItemsRowMapper(), pattern, poHeaderId);
    }

    @Override
    public List<MtlSystemItems> getAllByDescriptionShipmentOrganizacion(String pattern, Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionShipmentOrganizacion");
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionShipmentOrganizacion::pattern: " + pattern);
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionShipmentOrganizacion::shipmentHeaderId: " + shipmentHeaderId);

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL_BY_DESCRIPTION_SHIPMENT_ORGANIZACION, new MtlSystemItemsRowMapper(), pattern, shipmentHeaderId);
    }

    @Override
    public List<MtlSystemItems> getAllByDescriptionCountHeaderLocator(String pattern, Long countHeaderId, Long locatorId) throws DaoException {
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionCountHeaderLocator");
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionCountHeaderLocator::pattern: " + pattern);
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionCountHeaderLocator::countHeaderId: " + countHeaderId);
        Log.d(TAG, "MtlSystemItemsDaoImpl::getAllByDescriptionCountHeaderLocator::locatorId: " + locatorId);

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL_BY_DESCRIPTION_COUNTEHEADER_LOCATOR, new MtlSystemItemsRowMapper(), pattern, countHeaderId, locatorId);
    }

    @Override
    public List<MtlSystemItems> getAllByOcReceipt(Long poHeaderId, Long receiptNum) throws DaoException {
        return super.selectMany(MtlSystemItemsCatalogo.SELECT_BY_OC_RECEIPT, new MtlSystemItemsRowMapper(), poHeaderId, receiptNum);
    }

}
