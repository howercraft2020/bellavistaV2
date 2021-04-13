package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.Organizacion;

public interface IOrganizacionDao {

    public void insert(Organizacion organizacion) throws DaoException;
    public void delete(Long idOrganizacion) throws DaoException;
    public Organizacion get(Long idOrganizacion, String code, String transferencia) throws DaoException;
    public Organizacion getByCodeDestino(String code) throws DaoException;
    public Organizacion getByiDDestino(Long idOrganizacion) throws DaoException;
    public List<Organizacion> getAllDestino() throws DaoException;

}
