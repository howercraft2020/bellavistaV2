package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.PoLinesAllCatalogo;
import cl.clsoft.bave.model.PoLinesAll;

public class PoLinesAllRowMapper implements RowMapper<PoLinesAll>{

    @Override
    public PoLinesAll mapRow(Cursor cursor, int position) throws SQLException {
        PoLinesAll entity = new PoLinesAll();
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_PO_LINE_ID)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_PO_HEADER_ID)));
        entity.setLineNum(cursor.getString(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_LINE_NUM)));
        entity.setItemId(cursor.getLong(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_ITEM_ID)));
        entity.setItemDescripcion(cursor.getString(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_ITEM_DESCRIPTION)));
        entity.setUnitPrice(cursor.getLong(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_UNIT_PRICE)));
        entity.setQuantity(cursor.getLong(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_QUANTITY)));
        entity.setUnitMeasLookupCode(cursor.getString(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_UNIT_MEAS_LOOKUP_CODE)));
        entity.setBaseUom(cursor.getString(cursor.getColumnIndex(PoLinesAllCatalogo.CAMPO_BASE_UOM)));

        return entity;
    }
}
