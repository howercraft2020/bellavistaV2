package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;

public interface IDatosTransSubinvDetalleDao {

    public List<DatosTransSubinvDetalle> getTransferencias(String transactionReference) throws DaoException;
}
