package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;

public interface IDatosCabeceraRecepcionDao
{
    DatosCabeceraRecepcion get(final Long p0, final Long p1) throws DaoException;
}