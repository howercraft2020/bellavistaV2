package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.DatosTransOrgCatalogo;
import cl.clsoft.bave.dao.catalogo.DatosTransSubinvCatalogo;
import cl.clsoft.bave.model.DatosTransOrg;

public class DatosTransOrgRowMapper implements RowMapper<DatosTransOrg> {
    @Override
    public DatosTransOrg mapRow(Cursor cursor, int position) throws SQLException {
        DatosTransOrg entity = new DatosTransOrg();
        entity.setShipmentNumber(cursor.getString(cursor.getColumnIndex(DatosTransOrgCatalogo.COLUMN_SHIPMENT_NUMBER)));
        entity.setTransactionSourceName(cursor.getString(cursor.getColumnIndex(DatosTransOrgCatalogo.COLUMN_TRANSACTION_SOURCE_NAME)));

        return entity;
    }
}
