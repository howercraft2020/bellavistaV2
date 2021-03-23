package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IPoLinesAllDao;
import cl.clsoft.bave.dao.catalogo.PoLinesAllCatalogo;
import cl.clsoft.bave.dao.rowmapper.PoLinesAllRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoLinesAll;

public class PoLinesAllDaoImpl extends GenericDao<PoLinesAll> implements IPoLinesAllDao {

    @Override
    public void insert(PoLinesAll poLinesAll) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(PoLinesAllCatalogo.CAMPO_PO_LINE_ID, poLinesAll.getPoLineId());
        values.put(PoLinesAllCatalogo.CAMPO_PO_HEADER_ID, poLinesAll.getPoHeaderId());
        values.put(PoLinesAllCatalogo.CAMPO_LINE_NUM, poLinesAll.getLineNum());
        values.put(PoLinesAllCatalogo.CAMPO_ITEM_ID, poLinesAll.getItemId());
        values.put(PoLinesAllCatalogo.CAMPO_ITEM_DESCRIPTION, poLinesAll.getItemDescripcion());
        values.put(PoLinesAllCatalogo.CAMPO_UNIT_PRICE, poLinesAll.getUnitPrice());
        values.put(PoLinesAllCatalogo.CAMPO_QUANTITY, poLinesAll.getQuantity());
        values.put(PoLinesAllCatalogo.CAMPO_UNIT_MEAS_LOOKUP_CODE, poLinesAll.getUnitMeasLookupCode());
        values.put(PoLinesAllCatalogo.CAMPO_BASE_UOM, poLinesAll.getBaseUom());
        super.insert(PoLinesAllCatalogo.TABLE, values);

    }

    @Override
    public PoLinesAll getById(Long poLineId) throws DaoException {
        return super.selectOne(PoLinesAllCatalogo.SELECT_BY_ID, new PoLinesAllRowMapper(), poLineId);
    }

    @Override
    public void delete(Long poHeaderId) throws DaoException {
        super.delete(PoLinesAllCatalogo.TABLE, PoLinesAllCatalogo.DELETE, poHeaderId);
    }

    @Override
    public List<PoLinesAll> getLines(Long inventorItemId, Long poHeaderId) throws DaoException {
        return super.selectMany(PoLinesAllCatalogo.SELECT_LINES, new PoLinesAllRowMapper(), inventorItemId,poHeaderId);
    }

    @Override
    public PoLinesAll getLinea(Long inventorItemId, Long poHeaderId, Long poLineNum) throws DaoException {
        return super.selectOne(PoLinesAllCatalogo.SELECT_VALIDA_LINEA, new PoLinesAllRowMapper(), inventorItemId, poHeaderId, poLineNum);
    }
}
