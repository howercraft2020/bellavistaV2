package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IMtlCycleCountEntriesDao;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountEntriesCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlCycleCountEntriesRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public class MtlCycleCountEntriesDaoImpl extends GenericDao<MtlCycleCountEntries> implements IMtlCycleCountEntriesDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_ID, mtlCycleCountEntries.getCycleCountEntryId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlCycleCountEntries.getInventoryItemId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_SUBINVENTORY, mtlCycleCountEntries.getSubinventory());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_ENTRY_STATUS_CODE, mtlCycleCountEntries.getEntryStatusCode());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_ORGANIZATION_ID, mtlCycleCountEntries.getOrganizationId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_CYCLE_COUNT_HEADER_ID, mtlCycleCountEntries.getCycleCountHeaderId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_LOCATOR_ID, mtlCycleCountEntries.getLocatorId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_LOT_NUMBER, mtlCycleCountEntries.getLotNumber());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_SEGMENT1, mtlCycleCountEntries.getSegment1());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlCycleCountEntries.getPrimaryUomCode());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_SERIAL_NUMBER, mtlCycleCountEntries.getSerialNumber());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_COUNT, mtlCycleCountEntries.getCount());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_LAST_UPDATED, mtlCycleCountEntries.getLastUpdated());
        super.insert(MtlCycleCountEntriesCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlCycleCountEntries.getInventoryItemId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_SUBINVENTORY, mtlCycleCountEntries.getSubinventory());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_ENTRY_STATUS_CODE, mtlCycleCountEntries.getEntryStatusCode());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_ORGANIZATION_ID, mtlCycleCountEntries.getOrganizationId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_CYCLE_COUNT_HEADER_ID, mtlCycleCountEntries.getCycleCountHeaderId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_LOCATOR_ID, mtlCycleCountEntries.getLocatorId());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_LOT_NUMBER, mtlCycleCountEntries.getLotNumber());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_SEGMENT1, mtlCycleCountEntries.getSegment1());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlCycleCountEntries.getPrimaryUomCode());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_SERIAL_NUMBER, mtlCycleCountEntries.getSerialNumber());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_COUNT, mtlCycleCountEntries.getCount());
        values.put(MtlCycleCountEntriesCatalogo.COLUMN_LAST_UPDATED, mtlCycleCountEntries.getLastUpdated());
        super.update(MtlCycleCountEntriesCatalogo.TABLE, values, MtlCycleCountEntriesCatalogo.UPDATE, mtlCycleCountEntries.getCycleCountEntryId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::delete");

        super.delete(MtlCycleCountEntriesCatalogo.TABLE, MtlCycleCountEntriesCatalogo.DELETE, id);
    }

    @Override
    public void deleteByHeader(Long headerId) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::deleteByHeader");

        super.delete(MtlCycleCountEntriesCatalogo.TABLE, MtlCycleCountEntriesCatalogo.DELETE_BY_HEADER_ID, headerId);
    }

    @Override
    public MtlCycleCountEntries get(Long id) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::get");

        return super.selectOne(MtlCycleCountEntriesCatalogo.SELECT, new MtlCycleCountEntriesRowMapper(), id);
    }

    @Override
    public List<MtlCycleCountEntries> getSigle(Long idSigle) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSigle");

        return super.selectMany(MtlCycleCountEntriesCatalogo.SELECT_BY_HEADER_COUNT_HEADER_ID,new MtlCycleCountEntriesRowMapper(), idSigle);
    }

    @Override
    public List<MtlCycleCountEntries> getAll() throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAll");

        return super.selectMany(MtlCycleCountEntriesCatalogo.SELECT_ALL, new MtlCycleCountEntriesRowMapper());
    }

    @Override
    public List<MtlCycleCountEntries> getAllBySubinventarioLocatorSegmentLoteSerie(Long countHeaderId, String subinventory, Long locatorId, String segment, String serie, String lote) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie");
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie::countHeaderId: " + countHeaderId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie::subinventory: " + subinventory);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie::locatorId: " + locatorId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie::segment: " + segment);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie::serie: " + serie);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioLocatorSegmentLoteSerie::lote: " + lote);

        return selectMany(MtlCycleCountEntriesCatalogo.SELECT_ALL_BY_CYCLECOUNT_SUBINVENTORY_LOCATOR_SEGMENT_LOTE_SERIE, new MtlCycleCountEntriesRowMapper(), countHeaderId, subinventory, locatorId, segment, lote, serie);
    }

    @Override
    public List<MtlCycleCountEntries> getAllBySubinventarioSegmentLoteSerie(Long countHeaderId, String subinventory, String segment, String serie, String lote) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioSegmentLoteSerie");
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioSegmentLoteSerie::countHeaderId: " + countHeaderId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioSegmentLoteSerie::subinventory: " + subinventory);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioSegmentLoteSerie::segment: " + segment);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioSegmentLoteSerie::serie: " + serie);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllBySubinventarioSegmentLoteSerie::lote: " + lote);

        return selectMany(MtlCycleCountEntriesCatalogo.SELECT_ALL_BY_CYCLECOUNT_SUBINVENTORY_SEGMENT_LOTE_SERIE, new MtlCycleCountEntriesRowMapper(), countHeaderId, subinventory, segment, lote, serie);
    }

    @Override
    public List<MtlCycleCountEntries> getAllInventariadosByHeader(Long countHeaderId) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllInventariadosByHeader");

        return super.selectMany(MtlCycleCountEntriesCatalogo.SELECT_ALL_INVENTARIADOS_BY_CYCLECOUNT, new MtlCycleCountEntriesRowMapper(), countHeaderId);
    }

    @Override
    public List<MtlCycleCountEntries> getAllInventariadosBySubinventario(Long countHeaderId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getAllInventariadosBySubinventario");

        return super.selectMany(MtlCycleCountEntriesCatalogo.SELECT_ALL_INVENTARIADOS_BY_CYCLECOUNT_SUBINVENTORY, new MtlCycleCountEntriesRowMapper(), countHeaderId, subinventory);
    }

    @Override
    public List<String> getSegmentsByCountHeaderLocator(Long countHeaderId, Long locatorId) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSegmentsByCountHeaderLocator");

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_SEGMENT_BY_CYCLECOUNTEHEADER_LOCATOR, countHeaderId, locatorId);
    }

    @Override
    public List<String> getSegmentsByCountHeaderSubinventory(Long countHeaderId, String subinventory) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSegmentsByCountHeaderSubinventory");

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_SEGMENT_BY_CYCLECOUNTEHEADER_SUBINVENTORY, countHeaderId, subinventory);
    }

    @Override
    public List<String> getSegmentsByCountHeaderSubinventoryLocator(Long countHeaderId, String subinventory, Long locatorId) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSegmentsByCountHeaderSubinventoryLocator");

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_SEGMENT_BY_CYCLECOUNTEHEADER_SUBINVENTORY_LOCATOR, countHeaderId, subinventory, locatorId);
    }

    @Override
    public List<String> getLoteByCountHeaderLocatorSegment(Long cycleCountHeaderId, Long locatorId, String segment) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderLocatorSegment");

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_LOTE_BY_CYCLECOUNTEHEADER_LOCATOR_SEGMENT, cycleCountHeaderId, locatorId, segment);
    }

    @Override
    public List<String> getLoteByCountHeaderSubinventoryLocatorSegment(Long cycleCountHeaderId, String subinventory, Long locatorId, String segment) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventoryLocatorSegment");
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventoryLocatorSegment::cycleCountHeaderId: " + cycleCountHeaderId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventoryLocatorSegment::subinventory: " + subinventory);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventoryLocatorSegment::locatorId: " + locatorId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventoryLocatorSegment::segment: " + segment);

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_LOTE_BY_CYCLECOUNTEHEADER_SUBINVENTORY_LOCATOR_SEGMENT, cycleCountHeaderId, subinventory, locatorId, segment);
    }

    @Override
    public List<String> getLoteByCountHeaderSubinventorySegment(Long cycleCountHeaderId, String subinventory, String segment) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventorySegment");
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventorySegment::cycleCountHeaderId: " + cycleCountHeaderId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventorySegment::subinventory: " + subinventory);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getLoteByCountHeaderSubinventorySegment::segment: " + segment);

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_LOTE_BY_CYCLECOUNTEHEADER_SUBINVENTORY_SEGMENT, cycleCountHeaderId, subinventory, segment);
    }

    @Override
    public List<String> getSerialByCountHeaderSubinventoryLocatorSegment(Long cycleCountHeaderId, String subinventory, Long locatorId, String segment) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventoryLocatorSegment");
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventoryLocatorSegment::cycleCountHeaderId: " + cycleCountHeaderId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventoryLocatorSegment::subinventory: " + subinventory);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventoryLocatorSegment::locatorId: " + locatorId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventoryLocatorSegment::segment: " + segment);

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_SERIAL_BY_CYCLECOUNTEHEADER_SUBINVENTORY_LOCATOR_SEGMENT, cycleCountHeaderId, subinventory, locatorId, segment);
    }

    @Override
    public List<String> getSerialByCountHeaderSubinventorySegment(Long cycleCountHeaderId, String subinventory, String segment) throws DaoException {
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventorySegment");
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventorySegment::cycleCountHeaderId: " + cycleCountHeaderId);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventorySegment::subinventory: " + subinventory);
        Log.d(TAG, "MtlCycleCountEntriesDaoImpl::getSerialByCountHeaderSubinventorySegment::segment: " + segment);

        return super.selectManyString(MtlCycleCountEntriesCatalogo.SELECT_SERIAL_BY_CYCLECOUNTEHEADER_SUBINVENTORY_SEGMENT, cycleCountHeaderId, subinventory, segment);
    }

}
