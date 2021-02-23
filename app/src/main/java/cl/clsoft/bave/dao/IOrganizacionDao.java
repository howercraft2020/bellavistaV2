package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Organizacion;

public interface IOrganizacionDao {

    public void insert(Organizacion organizacion) throws DaoException;
    public void update(Organizacion organizacion) throws DaoException;
    public void delete(Long idOrganizacion) throws DaoException;
    public Organizacion get(Long idOrganizacion) throws DaoException;

}
