package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IDatosTransOrgDao;
import cl.clsoft.bave.dao.catalogo.DatosTransOrgCatalogo;
import cl.clsoft.bave.dao.rowmapper.DatosTransOrgRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransOrg;

public class DatosTransOrgImpl extends GenericDao<DatosTransOrg> implements IDatosTransOrgDao {
    @Override
    public List<DatosTransOrg> getTransSubinv() throws DaoException {
        return super.selectMany(DatosTransOrgCatalogo.SELECT, new DatosTransOrgRowMapper());
    }
}
