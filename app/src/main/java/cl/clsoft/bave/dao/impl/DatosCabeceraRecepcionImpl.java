package cl.clsoft.bave.dao.impl;

import cl.clsoft.bave.dao.catalogo.DatosCabeceraRecepcionCatalogo;
import cl.clsoft.bave.dao.catalogo.DatosRecepcionCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.dao.rowmapper.RowMapper;
import cl.clsoft.bave.dao.rowmapper.DatosCabeceraRecepcionRowMapper;
import cl.clsoft.bave.dao.IDatosCabeceraRecepcionDao;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;

public class DatosCabeceraRecepcionImpl extends GenericDao<DatosCabeceraRecepcion> implements IDatosCabeceraRecepcionDao
{
    public DatosCabeceraRecepcion get(final Long poHeaderId, final Long receiptNum) throws DaoException {
        return (DatosCabeceraRecepcion)super.selectOne(DatosCabeceraRecepcionCatalogo.SELECT, (RowMapper)new DatosCabeceraRecepcionRowMapper(), new Object[] { poHeaderId, receiptNum });
    }
}