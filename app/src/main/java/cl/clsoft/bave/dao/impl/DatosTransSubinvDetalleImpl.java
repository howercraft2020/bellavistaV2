package cl.clsoft.bave.dao.impl;

import java.util.List;

import cl.clsoft.bave.dao.IDatosTransSubinvDetalleDao;
import cl.clsoft.bave.dao.catalogo.DatosTransSubinvDetalleCatalogo;
import cl.clsoft.bave.dao.rowmapper.DatosTransSubinvDetalleRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;

public class DatosTransSubinvDetalleImpl extends GenericDao<DatosTransSubinvDetalle> implements IDatosTransSubinvDetalleDao {
    @Override
    public List<DatosTransSubinvDetalle> getTransferencias(String transactionReference) throws DaoException {
        return super.selectMany(DatosTransSubinvDetalleCatalogo.SELECT,new DatosTransSubinvDetalleRowMapper(),transactionReference);
    }
}
