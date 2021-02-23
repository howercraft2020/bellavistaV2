package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransSubinv;

public interface IDatosTransSubInvDao {

    public List<DatosTransSubinv> getTransSubinv() throws DaoException;
}
