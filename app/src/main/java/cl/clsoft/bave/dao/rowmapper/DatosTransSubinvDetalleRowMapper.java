package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.DatosTransSubinvDetalleCatalogo;
import cl.clsoft.bave.dao.rowmapper.RowMapper;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;

public class DatosTransSubinvDetalleRowMapper implements RowMapper<DatosTransSubinvDetalle> {
    @Override
    public DatosTransSubinvDetalle mapRow(Cursor cursor, int position) throws SQLException {
        DatosTransSubinvDetalle entity = new DatosTransSubinvDetalle();
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(DatosTransSubinvDetalleCatalogo.COLUMN_SEGMENT1)));
        entity.setSubinventoryCode(cursor.getString(cursor.getColumnIndex(DatosTransSubinvDetalleCatalogo.COLUMN_SUBINVENTORY_CODE)));
        entity.setLocalizador(cursor.getString(cursor.getColumnIndex(DatosTransSubinvDetalleCatalogo.COLUMN_LOCALIZADOR)));
        entity.setSubinventarioHasta(cursor.getString(cursor.getColumnIndex(DatosTransSubinvDetalleCatalogo.COLUMN_SUBINVENTARIO_HASTA)));
        entity.setLocalizadorHasta(cursor.getString(cursor.getColumnIndex(DatosTransSubinvDetalleCatalogo.COLUMN_LOCALIZADOR_HASTA)));
        entity.setTransactionQuantity(cursor.getLong(cursor.getColumnIndex(DatosTransSubinvDetalleCatalogo.COLUMN_TRANSACTION_QUANTITY)));

        return entity;
    }
}
