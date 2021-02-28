package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IMtlPhysicalInventoryTagsDao;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoriesCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoryTagsCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlPhysicalInventoryTagsRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public class MtlPhysicalInventoryTagsDaoImpl extends GenericDao<MtlPhysicalInventoryTags> implements IMtlPhysicalInventoryTagsDao {

    private static final String TAG = "Dao";

    @Override
    public void insert(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_ID, mtlPhysicalInventoryTags.getTagId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_PHYSICAL_INVENTORY_ID, mtlPhysicalInventoryTags.getPhysicalInventoryId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_ORGANIZATION_ID, mtlPhysicalInventoryTags.getOrganizationId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlPhysicalInventoryTags.getInventoryItemId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SUBINVENTORY, mtlPhysicalInventoryTags.getSubinventory());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOCATOR_ID, mtlPhysicalInventoryTags.getLocatorId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_NUMBER, mtlPhysicalInventoryTags.getLotNumber());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_EXPIRATION_DATE, mtlPhysicalInventoryTags.getLotExpirationDate());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SERIAL_NUM, mtlPhysicalInventoryTags.getSerialNum());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SEGMENT1, mtlPhysicalInventoryTags.getSegment1());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlPhysicalInventoryTags.getPrimaryUomCode());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_COUNT, mtlPhysicalInventoryTags.getCount());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LAST_UPDATED, mtlPhysicalInventoryTags.getLastUpdated());
        super.insert(MtlPhysicalInventoryTagsCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_PHYSICAL_INVENTORY_ID, mtlPhysicalInventoryTags.getPhysicalInventoryId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_ORGANIZATION_ID, mtlPhysicalInventoryTags.getOrganizationId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlPhysicalInventoryTags.getInventoryItemId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SUBINVENTORY, mtlPhysicalInventoryTags.getSubinventory());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOCATOR_ID, mtlPhysicalInventoryTags.getLocatorId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_NUMBER, mtlPhysicalInventoryTags.getLotNumber());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_EXPIRATION_DATE, mtlPhysicalInventoryTags.getLotExpirationDate());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SERIAL_NUM, mtlPhysicalInventoryTags.getSerialNum());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SEGMENT1, mtlPhysicalInventoryTags.getSegment1());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlPhysicalInventoryTags.getPrimaryUomCode());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_COUNT, mtlPhysicalInventoryTags.getCount());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LAST_UPDATED, mtlPhysicalInventoryTags.getLastUpdated());
        super.update(MtlPhysicalInventoryTagsCatalogo.TABLE, values, MtlPhysicalInventoryTagsCatalogo.UPDATE, mtlPhysicalInventoryTags.getTagId());

    }

    @Override
    public void delete(Long tagId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::delete");

        super.delete(MtlPhysicalInventoryTagsCatalogo.TABLE, MtlPhysicalInventoryTagsCatalogo.DELETE, tagId);
    }

    @Override
    public MtlPhysicalInventoryTags get(Long tagId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::get");

        return this.selectOne(MtlPhysicalInventoryTagsCatalogo.SELECT, new MtlPhysicalInventoryTagsRowMapper(), tagId);
    }

    @Override
    public void deleteByPhysicalInventory(Long physicalInventoryId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::deleteByPhysicalInventory");

        super.delete(MtlPhysicalInventoryTagsCatalogo.TABLE, MtlPhysicalInventoryTagsCatalogo.DELETE_BY_PHYSICAL_INVENTORY_ID, physicalInventoryId);
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllByInventory(Long physicalInventoryId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getAllByInventory");

        return super.selectMany(MtlPhysicalInventoryTagsCatalogo.SELECT_ALL_BY_PHYSICAL_INVENTORY_ID, new MtlPhysicalInventoryTagsRowMapper(), physicalInventoryId);
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getAllByInventorySubinventory");

        return super.selectMany(MtlPhysicalInventoryTagsCatalogo.SELECT_ALL_BY_PHYSICAL_INVENTORY_ID_SUBINVENTORY, new MtlPhysicalInventoryTagsRowMapper(), physicalInventoryId, subinventory);
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllInventariadosByInventory(Long physicalInventoryId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getAllInventariadosByInventory");
        return super.selectMany(MtlPhysicalInventoryTagsCatalogo.SELECT_ALL_INVENTARIADOS_BY_INVENTORY, new MtlPhysicalInventoryTagsRowMapper(), physicalInventoryId);
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getAllInventariadosByInventorySubinventory");

        return super.selectMany(MtlPhysicalInventoryTagsCatalogo.SELECT_ALL_INVENTARIADOS_BY_INVENTORY_SUBINVENTORY, new MtlPhysicalInventoryTagsRowMapper(), physicalInventoryId, subinventory);
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllNoInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getAllNoInventariadosByInventorySubinventory");

        return super.selectMany(MtlPhysicalInventoryTagsCatalogo.SELECT_ALL_NOINVENTARIADOS_BY_INVENTORY_SUBINVENTORY, new MtlPhysicalInventoryTagsRowMapper(), physicalInventoryId, subinventory);
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllByInventorySubinventorySegmentSerieLote(Long physicalInventoryId, String subinventory, Long locatorId, String segment, String serie, String lote, String vencimiento) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getByInventorySubinventorySegmentSerieLote");

        return super.selectMany(MtlPhysicalInventoryTagsCatalogo.SELECT_ALL_BY_INVENTORY_SUBINVENTORY_SEGMENT_SERIE_LOTE, new MtlPhysicalInventoryTagsRowMapper(), physicalInventoryId, subinventory, locatorId, segment, serie, lote, vencimiento);
    }

    @Override
    public List<String> getLocatorByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLocatorByInventorySubinventory");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLocatorByInventorySubinventory::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLocatorByInventorySubinventory::subinventory : " + subinventory);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_LOCATOR_BY_INVENTORY_SUBINVENTORY, physicalInventoryId, subinventory);
    }

    @Override
    public List<String> getSegment1ByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventory");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventory::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventory::subinventory : " + subinventory);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY, physicalInventoryId, subinventory);
    }

    @Override
    public List<String> getSegment1ByInventorySubinventoryLocator(Long physicalInventoryId, String subinventory, Long locatorId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventoryLocator");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventoryLocator::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventoryLocator::subinventory : " + subinventory);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSegment1ByInventorySubinventoryLocator::locatorId : " + locatorId);

        List<String> salida;
        if (locatorId != null) {
            salida = super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATOR, physicalInventoryId, subinventory, locatorId);
        } else {
            salida = super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATORNULL, physicalInventoryId, subinventory);
        }
        return salida;
    }

    @Override
    public List<String> getSeriesByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventory");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventory::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventory::subinventory : " + subinventory);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_SERIES_BY_INVENTORY_SUBINVENTORY, physicalInventoryId, subinventory);
    }

    @Override
    public List<String> getSeriesByInventorySubinventoryLocator(Long physicalInventoryId, String subinventory, Long locatorId) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocator");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocator::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocator::subinventory : " + subinventory);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocator::locatorId : " + locatorId);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_SERIES_BY_INVENTORY_SUBINVENTORY_LOCATOR, physicalInventoryId, subinventory, locatorId);
    }

    @Override
    public List<String> getSeriesByInventorySubinventoryLocatorSegment(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocatorSegment");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocatorSegment::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocatorSegment::subinventory : " + subinventory);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocatorSegment::locatorId : " + locatorId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getSeriesByInventorySubinventoryLocatorSegment::segment : " + segment);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_SERIES_BY_INVENTORY_SUBINVENTORY_LOCATOR_SEGMENT, physicalInventoryId, subinventory, locatorId, segment);
    }

    @Override
    public List<String> getLotesByInventorySubinventory(Long physicalInventoryId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventory");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventory::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventory::subinventory : " + subinventory);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_LOTES_BY_INVENTORY_SUBINVENTORY, physicalInventoryId, subinventory);
    }

    @Override
    public List<String> getLotesByInventorySubinventoryLocatorSegment(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventoryLocator");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventoryLocator::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventoryLocator::subinventory : " + subinventory);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getLotesByInventorySubinventoryLocator::locatorId : " + locatorId);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_LOTES_BY_INVENTORY_SUBINVENTORY_LOCATOR_SEGMENT, physicalInventoryId, subinventory, locatorId, segment);
    }

    @Override
    public List<String> getVencimientosByInventorySubinventoryLocatorSegment(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws DaoException {
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getVencimientosByInventorySubinventoryLocatorSegment");
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getVencimientosByInventorySubinventoryLocatorSegment::physicalInventoryId : " + physicalInventoryId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getVencimientosByInventorySubinventoryLocatorSegment::subinventory : " + subinventory);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getVencimientosByInventorySubinventoryLocatorSegment::locatorId : " + locatorId);
        Log.d(TAG, "MtlPhysicalInventoryTagsDaoImpl::getVencimientosByInventorySubinventoryLocatorSegment::segment : " + segment);

        return super.selectManyString(MtlPhysicalInventoryTagsCatalogo.SELECT_VENCIMIENTO_BY_INVENTORY_SUBINVENTORY_LOCATOR_SEGMENT, physicalInventoryId, subinventory, locatorId, segment);
    }

}
