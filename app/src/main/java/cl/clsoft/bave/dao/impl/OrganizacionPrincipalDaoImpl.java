package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import cl.clsoft.bave.dao.IOrganizacionPrincipalDao;
import cl.clsoft.bave.dao.catalogo.OrganizacionPrincipalCatalogo;
import cl.clsoft.bave.dao.rowmapper.OrganizacionPrincipalRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.OrganizacionPrincipal;

public class OrganizacionPrincipalDaoImpl extends GenericDao<OrganizacionPrincipal> implements IOrganizacionPrincipalDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(OrganizacionPrincipal organizacionPrincipal) throws DaoException {
        Log.d(TAG, "OrganizacionPrincipalDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(OrganizacionPrincipalCatalogo.COLUMN_NOMBRE, organizacionPrincipal.getNombre());
        values.put(OrganizacionPrincipalCatalogo.COLUMN_NOMBRE_CORTO, organizacionPrincipal.getNombreCorto());
        values.put(OrganizacionPrincipalCatalogo.COLUMN_CODE, organizacionPrincipal.getCode());
        values.put(OrganizacionPrincipalCatalogo.COLUMN_ID, organizacionPrincipal.getIdOrganizacion());
        super.insert(OrganizacionPrincipalCatalogo.TABLE, values);
    }

    @Override
    public void update(OrganizacionPrincipal organizacionPrincipal) throws DaoException {
        Log.d(TAG, "OrganizacionPrincipalDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(OrganizacionPrincipalCatalogo.COLUMN_NOMBRE, organizacionPrincipal.getNombre());
        values.put(OrganizacionPrincipalCatalogo.COLUMN_NOMBRE_CORTO, organizacionPrincipal.getNombreCorto());
        values.put(OrganizacionPrincipalCatalogo.COLUMN_CODE, organizacionPrincipal.getCode());
        super.update(OrganizacionPrincipalCatalogo.TABLE, values, OrganizacionPrincipalCatalogo.UPDATE, organizacionPrincipal.getIdOrganizacion());
    }

    @Override
    public OrganizacionPrincipal get(Long id) throws DaoException {
        Log.d(TAG, "OrganizacionPrincipalDaoImpl::get");

        return super.selectOne(OrganizacionPrincipalCatalogo.SELECT, new OrganizacionPrincipalRowMapper(), id);
    }

    @Override
    public OrganizacionPrincipal get() throws DaoException {
        Log.d(TAG, "OrganizacionPrincipalDaoImpl::get");

        return super.selectOne(OrganizacionPrincipalCatalogo.SELECT, new OrganizacionPrincipalRowMapper());
    }
}
