package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Subinventario;

public interface ISubinventarioDao {

    public void insert(Subinventario subinventario) throws DaoException;
    public void update(Subinventario subinventario) throws DaoException;
    public Subinventario get(Long organizacionId, String codigo) throws DaoException;
    public Subinventario getByCodigo(String codigo) throws DaoException;
    public List<Subinventario> getAll() throws DaoException;
    public List<Subinventario> getAllByCiclico(Long conteoCiclicoId) throws DaoException;

}
