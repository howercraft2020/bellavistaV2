package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransOrg;

public interface IDatosTransOrgDao {

    public List<DatosTransOrg> getTransSubinv() throws DaoException;

}
