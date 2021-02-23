package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IDatosTransSubInvDao;
import cl.clsoft.bave.dao.catalogo.DatosTransSubinvCatalogo;
import cl.clsoft.bave.dao.rowmapper.DatosTransSubinvRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransSubinv;

public class DatosTransSubinvImpl extends GenericDao<DatosTransSubinv> implements IDatosTransSubInvDao {
    @Override
    public List<DatosTransSubinv> getTransSubinv() throws DaoException {
        return super.selectMany(DatosTransSubinvCatalogo.SELECT, new DatosTransSubinvRowMapper());
    }
}
