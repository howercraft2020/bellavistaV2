package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.catalogo.SubinventarioCatalogo;
import cl.clsoft.bave.dao.rowmapper.SubinventarioRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Subinventario;

public class SubinventarioDaoImpl extends GenericDao<Subinventario> implements ISubinventarioDao {

    @Override
    public void insert(Subinventario subinventario) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(SubinventarioCatalogo.COLUMN_ORG_ID, subinventario.getOrganizationId());
        values.put(SubinventarioCatalogo.COLUMN_COD_SUB, subinventario.getCodSubinventario());
        values.put(SubinventarioCatalogo.COLUMN_DESCRIPTION, subinventario.getDescription());
        values.put(SubinventarioCatalogo.COLUMN_COD_LOC, subinventario.getCodLocalizador());
        super.insert(SubinventarioCatalogo.TABLE, values);
    }

    @Override
    public List<Subinventario> getAllByCiclico(Long conteoCiclicoId) throws DaoException {
        return super.selectMany(SubinventarioCatalogo.SELECT_ALL_BY_CICLICOID, new SubinventarioRowMapper(), conteoCiclicoId);
    }
}
