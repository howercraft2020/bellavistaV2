package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.OrganizacionPrincipal;

public interface IOrganizacionPrincipalDao {

    public void insert(OrganizacionPrincipal organizacionPrincipal) throws DaoException;
    public OrganizacionPrincipal get() throws DaoException;
}
