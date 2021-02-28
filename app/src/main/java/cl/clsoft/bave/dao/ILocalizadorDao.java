package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Localizador;

public interface ILocalizadorDao {

    public void insert(Localizador localizador) throws DaoException;
    public Localizador get(Long id) throws DaoException;
    public Localizador getByCodigo(String codigo) throws DaoException;
    public List<Localizador> getAllBySubinventario(String subinventarioCodigo) throws DaoException;

}
