package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Localizador;

public interface ILocalizadorDao {

    public void insert(Localizador localizador) throws DaoException;

}
