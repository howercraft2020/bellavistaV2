package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.OrganizacionCatalogo;
import cl.clsoft.bave.model.Organizacion;

public class OrganizacionRowMapper implements RowMapper<Organizacion> {

    @Override
    public Organizacion mapRow(Cursor cursor, int position) throws SQLException {
        Organizacion entity = new Organizacion();
        entity.setIdOrganizacion(cursor.getLong(cursor.getColumnIndex(OrganizacionCatalogo.CAMPO_ID)));
        entity.setCode(cursor.getString(cursor.getColumnIndex(OrganizacionCatalogo.CAMPO_CODE)));
        return entity;
    }

}
