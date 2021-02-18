package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IPoLinesAllDao;
import cl.clsoft.bave.dao.catalogo.PoLinesAllCatalogo;
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
}
