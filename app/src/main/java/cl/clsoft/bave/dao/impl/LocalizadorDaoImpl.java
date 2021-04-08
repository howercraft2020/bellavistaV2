package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.catalogo.LocalizadorCatalogo;
import cl.clsoft.bave.dao.rowmapper.LocalizadorRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Localizador;

public class LocalizadorDaoImpl extends GenericDao<Localizador> implements ILocalizadorDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(Localizador localizador) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(LocalizadorCatalogo.COLUMN_ID, localizador.getIdLocalizador());
        values.put(LocalizadorCatalogo.COLUMN_ORG_ID, localizador.getOrganizationId());
        values.put(LocalizadorCatalogo.COLUMN_COD_SUBINV, localizador.getCodSubinventario());
        values.put(LocalizadorCatalogo.COLUMN_COD_LOC, localizador.getCodLocalizador());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG1, localizador.getCodSeg1());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG2, localizador.getCodSeg2());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG3, localizador.getCodSeg3());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG4, localizador.getCodSeg4());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG5, localizador.getCodSeg5());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG6, localizador.getCodSeg6());
        super.insert(LocalizadorCatalogo.TABLE, values);
    }

    @Override
    public void update(Localizador localizador) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(LocalizadorCatalogo.COLUMN_ORG_ID, localizador.getOrganizationId());
        values.put(LocalizadorCatalogo.COLUMN_COD_SUBINV, localizador.getCodSubinventario());
        values.put(LocalizadorCatalogo.COLUMN_COD_LOC, localizador.getCodLocalizador());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG1, localizador.getCodSeg1());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG2, localizador.getCodSeg2());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG3, localizador.getCodSeg3());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG4, localizador.getCodSeg4());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG5, localizador.getCodSeg5());
        values.put(LocalizadorCatalogo.COLUMN_COD_SEG6, localizador.getCodSeg6());
        super.update(LocalizadorCatalogo.TABLE, values, LocalizadorCatalogo.UPDATE, localizador.getIdLocalizador());
    }

    @Override
    public Long get(String localizador) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::get");

        return super.selectLong(LocalizadorCatalogo.SELECT_CODE_LOCALIZADOR,localizador);
    }

    @Override
    public Localizador get(Long id) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::get");

        return super.selectOne(LocalizadorCatalogo.SELECT, new LocalizadorRowMapper(), id);
    }

    @Override
    public Localizador getByCodigo(String codigo) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::getByCodigo");

        return super.selectOne(LocalizadorCatalogo.SELECT_BY_CODIGO, new LocalizadorRowMapper(), codigo);
    }

    @Override
    public List<Localizador> getAllBySubinventario(String subinventarioCodigo) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::getAllBySubinventario");
        Log.d(TAG, "LocalizadorDaoImpl::getAllBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

        return super.selectMany(LocalizadorCatalogo.SELECT_ALL_BY_SUBINVENTORY, new LocalizadorRowMapper(), subinventarioCodigo);
    }

    @Override
    public List<Localizador> getAllBySubinventarioCountheaderId(String subinventarioCodigo, Long cycleCountHeaderId) throws DaoException {
        Log.d(TAG, "LocalizadorDaoImpl::getAllBySubinventarioCountheaderId");
        Log.d(TAG, "LocalizadorDaoImpl::getAllBySubinventarioCountheaderId::subinventarioCodigo: " + subinventarioCodigo);
        Log.d(TAG, "LocalizadorDaoImpl::getAllBySubinventarioCountheaderId::cycleCountHeaderId: " + cycleCountHeaderId);

        return super.selectMany(LocalizadorCatalogo.SELECT_ALL_BY_SUBINVENTORY_COUNTHEADERID, new LocalizadorRowMapper(), subinventarioCodigo, cycleCountHeaderId);
    }
}
