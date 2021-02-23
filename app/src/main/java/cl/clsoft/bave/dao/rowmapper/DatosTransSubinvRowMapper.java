package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.DatosTransSubinvCatalogo;
import cl.clsoft.bave.model.DatosTransSubinv;

public class DatosTransSubinvRowMapper implements RowMapper<DatosTransSubinv> {
    @Override
    public DatosTransSubinv mapRow(Cursor cursor, int position) throws SQLException {
        DatosTransSubinv entity = new DatosTransSubinv();
        entity.setTransactionReference(cursor.getString(cursor.getColumnIndex(DatosTransSubinvCatalogo.COLUMN_TRANSACTION_REFERENCE)));
        entity.setTransactionSourceName(cursor.getString(cursor.getColumnIndex(DatosTransSubinvCatalogo.COLUMN_TRANSACTION_SOURCE_NAME)));

        return entity;
    }
}
