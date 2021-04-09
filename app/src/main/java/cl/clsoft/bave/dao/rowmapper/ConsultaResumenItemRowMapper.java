package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.ConsultaItemCatalogo;
import cl.clsoft.bave.dao.catalogo.ConsultaResumenItemCatalogo;
import cl.clsoft.bave.model.ConsultaResumenItem;

public class ConsultaResumenItemRowMapper implements RowMapper<ConsultaResumenItem> {
    @Override
    public ConsultaResumenItem mapRow(Cursor cursor, int position) throws SQLException {
        ConsultaResumenItem entity = new ConsultaResumenItem();
        entity.setInventoryItemId(cursor.getLong(cursor.getColumnIndex(ConsultaResumenItemCatalogo.COLUMN_INVENTORY_ITEM_ID)));
        entity.setSigle(cursor.getString(cursor.getColumnIndex(ConsultaResumenItemCatalogo.COLUMN_SEGMENT1)));
        entity.setDescription(cursor.getString(cursor.getColumnIndex(ConsultaResumenItemCatalogo.COLUMN_DESCRIPTION)));
        entity.setUnidad(cursor.getString(cursor.getColumnIndex(ConsultaResumenItemCatalogo.COLUMN_UNIDAD)));
        entity.setCantidad(cursor.getDouble(cursor.getColumnIndex(ConsultaResumenItemCatalogo.COLUMN_QUANTITY)));
        return entity;
    }
}
