package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.SubinventarioCatalogo;
import cl.clsoft.bave.model.Subinventario;

public class SubinventarioRowMapper implements RowMapper<Subinventario>{
    @Override
    public Subinventario mapRow(Cursor cursor, int position) throws SQLException {
        Subinventario entity = new Subinventario();
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(SubinventarioCatalogo.COLUMN_ORG_ID)));
        entity.setCodSubinventario(cursor.getString(cursor.getColumnIndex(SubinventarioCatalogo.COLUMN_COD_SUB)));
        entity.setCodLocalizador(cursor.getString(cursor.getColumnIndex(SubinventarioCatalogo.COLUMN_COD_LOC)));

        return entity;
    }
}
