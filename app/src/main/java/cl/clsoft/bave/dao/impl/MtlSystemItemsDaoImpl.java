package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.catalogo.MtlSystemItemsCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlSystemItemsRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSystemItems;

public class MtlSystemItemsDaoImpl extends GenericDao<MtlSystemItems> implements IMtlSystemItemsDao {

    @Override
    public void insert(MtlSystemItems mtlSystemItems) throws DaoException {
        try {
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
        } catch (Exception ex) {
            throw new DaoException(1, ex.getMessage());
        }
    }

    @Override
    public MtlSystemItems getBySegment(String segment) throws DaoException {
        return super.selectOne(MtlSystemItemsCatalogo.SELECT_BY_SEGMENT, new MtlSystemItemsRowMapper(), segment);
    }

    @Override
    public List<MtlSystemItems> getAll() throws DaoException {

        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL, new MtlSystemItemsRowMapper());
    }

    @Override
    public List<MtlSystemItems> getAllByDescription(String pattern) throws DaoException {
        return super.selectMany(MtlSystemItemsCatalogo.SELECT_ALL_BY_DESCRIPTION, new MtlSystemItemsRowMapper(), pattern);
    }

}
