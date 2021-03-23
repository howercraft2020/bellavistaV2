package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosRecepcion;

public interface IDatosRecepcionDao {
    public DatosRecepcion get(String segment1, String oc, Long receiptNum, Long poLineNum) throws DaoException;
}
