package cl.clsoft.bave.dao.impl;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.dao.rowmapper.RowMapper;
import cl.clsoft.bave.dao.rowmapper.DatosCabeceraRecepcionRowMapper;
import cl.clsoft.bave.dao.IDatosCabeceraRecepcionDao;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;

public class DatosCabeceraRecepcionImpl extends GenericDao<DatosCabeceraRecepcion> implements IDatosCabeceraRecepcionDao
{
    public DatosCabeceraRecepcion get(final Long poHeaderId, final Long receiptNum) throws DaoException {
        return (DatosCabeceraRecepcion)super.selectOne("SELECT user_id, receipt_num, vendor_id, vendor_site_code, vendor_site_id, currency_code, rate_type, rate, rate_date, terms_id FROM po_headers_all where po_header_id = ? AND receipt_num = ?", (RowMapper)new DatosCabeceraRecepcionRowMapper(), new Object[] { poHeaderId, receiptNum });
    }
}