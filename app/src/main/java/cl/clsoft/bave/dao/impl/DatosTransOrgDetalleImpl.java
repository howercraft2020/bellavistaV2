package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IDatosTransOrgDetalleDao;
import cl.clsoft.bave.dao.IDatosTransSubinvDetalleDao;
import cl.clsoft.bave.dao.catalogo.DatosTransOrgDetalleCatalogo;
import cl.clsoft.bave.dao.rowmapper.DatosTransOrgDetalleRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransOrgDetalle;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;

public class DatosTransOrgDetalleImpl extends GenericDao<DatosTransOrgDetalle> implements IDatosTransOrgDetalleDao {
    @Override
    public List<DatosTransOrgDetalle> getTransferencias(String transactionReference) throws DaoException {
        return super.selectMany(DatosTransOrgDetalleCatalogo.SELECT, new DatosTransOrgDetalleRowMapper(),transactionReference);
    }
}
