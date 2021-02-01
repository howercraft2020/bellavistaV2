package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.catalogo.OrganizacionCatalogo;
import cl.clsoft.bave.dao.rowmapper.OrganizacionRowMapper;
import cl.clsoft.bave.model.Organizacion;

public class OrganizacionDaoImpl extends GenericDao<Organizacion> implements IOrganizacionDao {

    @Override
    public void insert(Organizacion organizacion) {
        ContentValues values = new ContentValues();
        values.put(OrganizacionCatalogo.CAMPO_ID, organizacion.getIdOrganizacion());
        values.put(OrganizacionCatalogo.CAMPO_CODE, organizacion.getCode());
        super.insert(OrganizacionCatalogo.TABLA, values);
    }

    @Override
    public void update(Organizacion organizacion) {
        ContentValues values = new ContentValues();
        values.put(OrganizacionCatalogo.CAMPO_CODE, organizacion.getCode());
        super.update(OrganizacionCatalogo.TABLA, values, OrganizacionCatalogo.UPDATE, organizacion.getIdOrganizacion());
    }

    @Override
    public void delete(Long idOrganizacion) {
        super.delete(OrganizacionCatalogo.TABLA, OrganizacionCatalogo.DELETE, idOrganizacion);
    }

    @Override
    public Organizacion get(Long idOrganizacion) {
        final Organizacion organizacion = super.selectOne(OrganizacionCatalogo.SELECT, new OrganizacionRowMapper(), idOrganizacion);
        return organizacion;
    }

}
