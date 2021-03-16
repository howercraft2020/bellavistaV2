package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransOrgDetalle;

public interface IDatosTransOrgDetalleDao {

    public List<DatosTransOrgDetalle> getTransferencias(String transactionReference) throws DaoException;
}
