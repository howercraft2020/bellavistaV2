package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.catalogo.SubinventarioCatalogo;
import cl.clsoft.bave.dao.rowmapper.SubinventarioRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Subinventario;

public class SubinventarioDaoImpl extends GenericDao<Subinventario> implements ISubinventarioDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(Subinventario subinventario) throws DaoException {
        Log.d(TAG, "SubinventarioDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(SubinventarioCatalogo.COLUMN_ORG_ID, subinventario.getOrganizationId());
        values.put(SubinventarioCatalogo.COLUMN_COD_SUB, subinventario.getCodSubinventario());
        values.put(SubinventarioCatalogo.COLUMN_DESCRIPTION, subinventario.getDescription());
        values.put(SubinventarioCatalogo.COLUMN_COD_LOC, subinventario.getCodLocalizador());
        super.insert(SubinventarioCatalogo.TABLE, values);
    }

    @Override
    public void update(Subinventario subinventario) throws DaoException {
        Log.d(TAG, "SubinventarioDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(SubinventarioCatalogo.COLUMN_DESCRIPTION, subinventario.getDescription());
        values.put(SubinventarioCatalogo.COLUMN_COD_LOC, subinventario.getCodLocalizador());
        super.update(SubinventarioCatalogo.TABLE, values, SubinventarioCatalogo.UPDATE, subinventario.getOrganizationId(), subinventario.getCodSubinventario());
    }

    @Override
    public Subinventario get(Long organizacionId, String codigo) throws DaoException {
        Log.d(TAG, "SubinventarioDaoImpl::get");

        return super.selectOne(SubinventarioCatalogo.SELECT, new SubinventarioRowMapper(), organizacionId, codigo);
    }

    @Override
    public Subinventario getByCodigo(String codigo) throws DaoException {
        return super.selectOne(SubinventarioCatalogo.SELECT_BY_CODIGO, new SubinventarioRowMapper(), codigo);
    }

    @Override
    public List<Subinventario> getAll() throws DaoException {
        return super.selectMany(SubinventarioCatalogo.SELECT_ALL, new SubinventarioRowMapper());
    }

    @Override
    public List<Subinventario> getAllByCiclico(Long conteoCiclicoId) throws DaoException {
        return super.selectMany(SubinventarioCatalogo.SELECT_ALL_BY_CICLICOID, new SubinventarioRowMapper(), conteoCiclicoId);
    }

}
