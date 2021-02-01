package cl.clsoft.bave.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.OrganizacionCatalogo;
import cl.clsoft.bave.model.Organizacion;

public class OrganizacionRowMapper implements RowMapper<Organizacion> {

    @Override
    public Organizacion mapRow(ResultSet row, int rowNum) throws SQLException {
        Organizacion entity = new Organizacion();
        entity.setIdOrganizacion(row.getLong(OrganizacionCatalogo.CAMPO_ID));
        entity.setCode(row.getString(OrganizacionCatalogo.CAMPO_CODE));
        return entity;
    }

}
