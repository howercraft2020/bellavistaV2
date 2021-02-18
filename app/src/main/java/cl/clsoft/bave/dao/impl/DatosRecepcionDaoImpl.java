package cl.clsoft.bave.dao.impl;

import cl.clsoft.bave.dao.IDatosRecepcionDao;
import cl.clsoft.bave.dao.catalogo.DatosRecepcionCatalogo;
import cl.clsoft.bave.dao.rowmapper.DatosRecepcionRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.DatosRecepcion;

public class DatosRecepcionDaoImpl extends GenericDao<DatosRecepcion> implements IDatosRecepcionDao {
    @Override
    public DatosRecepcion get(String segment1, String oc, Long receiptNum) throws DaoException {
        return super.selectOne(DatosRecepcionCatalogo.SELECT, new DatosRecepcionRowMapper(), segment1,oc,receiptNum);
    }
}
