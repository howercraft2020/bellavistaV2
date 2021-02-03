package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.catalogo.LocalizadorCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Localizador;

public class LocalizadorDaoImpl extends GenericDao<Localizador> implements ILocalizadorDao {

    @Override
    public void insert(Localizador localizador) throws DaoException {
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
}
