package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;


import cl.clsoft.bave.dao.catalogo.DatosTransOrgDetalleCatalogo;
import cl.clsoft.bave.model.DatosTransOrgDetalle;

public class DatosTransOrgDetalleRowMapper implements RowMapper<DatosTransOrgDetalle> {
    @Override
    public DatosTransOrgDetalle mapRow(Cursor cursor, int position) throws SQLException {
        DatosTransOrgDetalle entity = new DatosTransOrgDetalle();
        entity.setOrganizationCode(cursor.getString(cursor.getColumnIndex(DatosTransOrgDetalleCatalogo.COLUMN_ORG_DESTINO)));
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(DatosTransOrgDetalleCatalogo.COLUMN_SEGMENT1)));
        entity.setSubinventoryCode(cursor.getString(cursor.getColumnIndex(DatosTransOrgDetalleCatalogo.COLUMN_SUBINVENTORY_CODE)));
        entity.setLocalizador(cursor.getString(cursor.getColumnIndex(DatosTransOrgDetalleCatalogo.COLUMN_LOCALIZADOR)));
        entity.setTransactionQuantity(cursor.getLong(cursor.getColumnIndex(DatosTransOrgDetalleCatalogo.COLUMN_TRANSACTION_QUANTITY)));
        entity.setTransactionInterfaceId(cursor.getLong(cursor.getColumnIndex(DatosTransOrgDetalleCatalogo.COLUMN_TRANSACTION_INTERFACE_ID)));

        return entity;
    }
}
