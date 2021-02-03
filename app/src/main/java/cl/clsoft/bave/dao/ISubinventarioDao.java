package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Subinventario;

public interface ISubinventarioDao {

    public void insert(Subinventario subinventario) throws DaoException;

}
