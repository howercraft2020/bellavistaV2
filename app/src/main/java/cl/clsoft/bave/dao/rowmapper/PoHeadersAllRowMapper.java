package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.PoHeadersAllCatalogo;
import cl.clsoft.bave.model.PoHeadersAll;

public class PoHeadersAllRowMapper implements RowMapper<PoHeadersAll>{
    @Override
    public PoHeadersAll mapRow(Cursor cursor, int position) throws SQLException {
        PoHeadersAll entity = new PoHeadersAll();
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_CREATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_CREATION_DATE)));
        entity.setVendorName(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_VENDOR_NAME)));
        entity.setVendorId(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_VENDOR_ID)));
        entity.setVendorSiteId(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_VENDOR_SITE_ID)));
        entity.setVendorSiteCode(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_VENDOR_SITE_CODE)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setSegment1(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_SEGMENT1)));
        entity.setOrgId(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_ORG_ID)));
        entity.setApprovedDate(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_APPROVED_DATE)));
        entity.setOperatingUnit(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_OPERATING_UNIT)));
        entity.setTermsId(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_TERMS_ID)));
        entity.setCurrencyCode(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_CURRENCY_CODE)));
        entity.setRateType(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_RATE_TYPE)));
        entity.setRateDate(cursor.getString(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_RATE_DATE)));
        entity.setRate(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_RATE)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_USER_ID)));
        entity.setReceiptNum(cursor.getLong(cursor.getColumnIndex(PoHeadersAllCatalogo.COLUMN_RECEIPT_NUM)));

        return entity;
    }
}
