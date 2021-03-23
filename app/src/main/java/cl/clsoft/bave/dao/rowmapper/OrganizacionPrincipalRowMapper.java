package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.OrganizacionPrincipalCatalogo;
import cl.clsoft.bave.model.OrganizacionPrincipal;

public class OrganizacionPrincipalRowMapper implements RowMapper<OrganizacionPrincipal> {
    @Override
    public OrganizacionPrincipal mapRow(Cursor cursor, int position) throws SQLException {
        OrganizacionPrincipal entity = new OrganizacionPrincipal();
        entity.setNombre(cursor.getString(cursor.getColumnIndex(OrganizacionPrincipalCatalogo.COLUMN_NOMBRE)));
        entity.setNombreCorto(cursor.getString(cursor.getColumnIndex(OrganizacionPrincipalCatalogo.COLUMN_NOMBRE_CORTO)));
        entity.setCode(cursor.getString(cursor.getColumnIndex(OrganizacionPrincipalCatalogo.COLUMN_CODE)));
        entity.setIdOrganizacion(cursor.getLong(cursor.getColumnIndex(OrganizacionPrincipalCatalogo.COLUMN_ID)));
        return entity;
    }
}
