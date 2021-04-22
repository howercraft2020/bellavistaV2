package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IPoHeadersAllDao;
import cl.clsoft.bave.dao.catalogo.PoHeadersAllCatalogo;
import cl.clsoft.bave.dao.rowmapper.PoHeadersAllRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoHeadersAll;

public class PoHeadersAllDaoImpl extends GenericDao<PoHeadersAll> implements IPoHeadersAllDao {

    @Override
    public void insert(PoHeadersAll poHeadersAll) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(PoHeadersAllCatalogo.COLUMN_CREATED_BY, poHeadersAll.getCreatedBy());
        values.put(PoHeadersAllCatalogo.COLUMN_CREATION_DATE, poHeadersAll.getCreationDate());
        values.put(PoHeadersAllCatalogo.COLUMN_VENDOR_NAME, poHeadersAll.getVendorName());
        values.put(PoHeadersAllCatalogo.COLUMN_VENDOR_SITE_ID, poHeadersAll.getVendorSiteId());
        values.put(PoHeadersAllCatalogo.COLUMN_VENDOR_ID,poHeadersAll.getVendorId());
        values.put(PoHeadersAllCatalogo.COLUMN_VENDOR_SITE_CODE, poHeadersAll.getVendorSiteCode());
        values.put(PoHeadersAllCatalogo.COLUMN_PO_HEADER_ID, poHeadersAll.getPoHeaderId());
        values.put(PoHeadersAllCatalogo.COLUMN_SEGMENT1, poHeadersAll.getSegment1());
        values.put(PoHeadersAllCatalogo.COLUMN_ORG_ID, poHeadersAll.getOrgId());
        values.put(PoHeadersAllCatalogo.COLUMN_APPROVED_DATE, poHeadersAll. getApprovedDate());
        values.put(PoHeadersAllCatalogo.COLUMN_OPERATING_UNIT, poHeadersAll.getOperatingUnit());
        values.put(PoHeadersAllCatalogo.COLUMN_TERMS_ID, poHeadersAll.getTermsId());
        values.put(PoHeadersAllCatalogo.COLUMN_CURRENCY_CODE, poHeadersAll.getCurrencyCode());
        values.put(PoHeadersAllCatalogo.COLUMN_RATE_TYPE, poHeadersAll.getRateType());
        values.put(PoHeadersAllCatalogo.COLUMN_RATE_DATE, poHeadersAll.getRateDate());
        values.put(PoHeadersAllCatalogo.COLUMN_RATE, poHeadersAll.getRate());
        values.put(PoHeadersAllCatalogo.COLUMN_USER_ID, poHeadersAll.getUserId());
        values.put(PoHeadersAllCatalogo.COLUMN_RECEIPT_NUM, poHeadersAll.getReceiptNum());
        super.insert(PoHeadersAllCatalogo.TABLE, values);



    }

    @Override
    public List<PoHeadersAll> getAll() throws DaoException {
        return super.selectMany(PoHeadersAllCatalogo.SELECT_ALL, new PoHeadersAllRowMapper());
    }

    @Override
    public PoHeadersAll getbyId(Long poHeaderId) throws DaoException {
        return super.selectOne(PoHeadersAllCatalogo.SELECT_BY_ID, new PoHeadersAllRowMapper(), poHeaderId);
    }

    @Override
    public void delete(Long poHeaderId) throws DaoException {
        super.delete(PoHeadersAllCatalogo.TABLE, PoHeadersAllCatalogo.DELETE, poHeaderId);
    }
}
