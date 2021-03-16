package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.catalogo.OrganizacionCatalogo;
import cl.clsoft.bave.dao.rowmapper.OrganizacionRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Organizacion;

public class OrganizacionDaoImpl extends GenericDao<Organizacion> implements IOrganizacionDao {

    @Override
    public void insert(Organizacion organizacion) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(OrganizacionCatalogo.COLUMN_ID, organizacion.getIdOrganizacion());
        values.put(OrganizacionCatalogo.COLUMN_CODE, organizacion.getCode());
        values.put(OrganizacionCatalogo.COLUMN_TRANSFERENCIA, organizacion.getTransferencia());
        super.insert(OrganizacionCatalogo.TABLE, values);
    }

    @Override
    public void update(Organizacion organizacion) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(OrganizacionCatalogo.COLUMN_CODE, organizacion.getCode());
        super.update(OrganizacionCatalogo.TABLE, values, OrganizacionCatalogo.UPDATE, organizacion.getIdOrganizacion());
    }

    @Override
    public void delete(Long idOrganizacion) throws DaoException {
        super.delete(OrganizacionCatalogo.TABLE, OrganizacionCatalogo.DELETE, idOrganizacion);
    }

    @Override
    public Organizacion get(Long idOrganizacion) throws DaoException {
        final Organizacion organizacion = super.selectOne(OrganizacionCatalogo.SELECT, new OrganizacionRowMapper(), idOrganizacion);
        return organizacion;
    }

    @Override
    public Organizacion getByCodeDestino(String code) throws DaoException {
        final Organizacion organizacion = super.selectOne(OrganizacionCatalogo.SELECT_BY_CODE, new OrganizacionRowMapper(), code);
        return organizacion;
    }

    @Override
    public Organizacion getByiDDestino(Long idOrganizacion) throws DaoException {
        final Organizacion organizacion = super.selectOne(OrganizacionCatalogo.SELECT_BY_ID, new OrganizacionRowMapper(), idOrganizacion);
        return organizacion;
    }

    @Override
    public List<Organizacion> getAllDestino() throws DaoException {
        final List<Organizacion> organizacion = super.selectMany(OrganizacionCatalogo.SELECT_HACIA, new OrganizacionRowMapper());
        return organizacion;
    }

}
