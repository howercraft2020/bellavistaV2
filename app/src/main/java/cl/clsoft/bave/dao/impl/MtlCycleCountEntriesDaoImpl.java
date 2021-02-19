package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlCycleCountEntriesDao;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountEntriesCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlCycleCountEntriesRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public class MtlCycleCountEntriesDaoImpl extends GenericDao<MtlCycleCountEntries> implements IMtlCycleCountEntriesDao {

    @Override
    public void insert(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException {
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
        super.insert(MtlCycleCountEntriesCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException {
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
        super.update(MtlCycleCountEntriesCatalogo.TABLE, values, MtlCycleCountEntriesCatalogo.UPDATE, mtlCycleCountEntries.getCycleCountEntryId());
    }

    @Override
    public void delete(Long id) throws DaoException {
        super.delete(MtlCycleCountEntriesCatalogo.TABLE, MtlCycleCountEntriesCatalogo.DELETE, id);
    }

    @Override
    public void deleteByHeader(Long headerId) throws DaoException {
        super.delete(MtlCycleCountEntriesCatalogo.TABLE, MtlCycleCountEntriesCatalogo.DELETE_BY_HEADER_ID, headerId);
    }

    @Override
    public MtlCycleCountEntries get(Long id) throws DaoException {
        return super.selectOne(MtlCycleCountEntriesCatalogo.SELECT, new MtlCycleCountEntriesRowMapper(), id);
    }

    @Override
    public List<MtlCycleCountEntries> getSigle(Long idSigle) throws DaoException {
        return super.selectMany(MtlCycleCountEntriesCatalogo.SELECT_BY_HEADER_COUNT_HEADER_ID,new MtlCycleCountEntriesRowMapper(), idSigle);
    }

    @Override
    public List<MtlCycleCountEntries> getAll() throws DaoException {
        return super.selectMany(MtlCycleCountEntriesCatalogo.SELECT_ALL, new MtlCycleCountEntriesRowMapper());
    }

}
