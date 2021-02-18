package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.RcvHeadersInterfaceCatalogo;
import cl.clsoft.bave.model.RcvHeadersInterface;

public class RcvHeadersInterfaceRowMapper implements RowMapper<RcvHeadersInterface>{


    @Override
    public RcvHeadersInterface mapRow(Cursor cursor, int position) throws SQLException {
        RcvHeadersInterface entity = new RcvHeadersInterface();
        entity.setHeaderInterfaceId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_HEADER_INTERFACE_ID)));
        entity.setReciptSourceCode(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_RECEIPT_SOURCE_CODE)));
        entity.setTransactionType(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_TRANSACTION_TYPE)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdateBy(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_CREATED_BY)));
        entity.setReciptNum(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_RECEIPT_NUM)));
        entity.setVendorId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_VENDOR_ID)));
        entity.setVendorSiteCode(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_VENDOR_SITE_CODE)));
        entity.setVendorSiteId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_VENDOR_SITE_ID)));
        entity.setShipToOrganizationCode(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_SHIP_TO_ORGANIZATION_CODE)));
        entity.setLocationId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_LOCATION_ID)));
        entity.setReceiverId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_RECEIVER_ID)));
        entity.setCurrencyCode(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_CURRENCY_CODE)));
        entity.setConversionRateType(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_CONVERSION_RATE_TYPE)));
        entity.setConversionRate(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_CONVERSION_RATE)));
        entity.setConversionRateDate(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_CONVERSION_RATE_DATE)));
        entity.setPaymentTermsId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_PAYMENT_TERMS_ID)));
        entity.setTransactionDate(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_TRANSACTION_DATE)));
        entity.setComments(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_COMMENTS)));
        entity.setOrgId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_ORG_ID)));
        entity.setProcessingStatusCode(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_PROCESSING_STATUS_CODE)));
        entity.setValidationFlag(cursor.getString(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_VALIDATION_FLAG)));
        entity.setGroupId(cursor.getLong(cursor.getColumnIndex(RcvHeadersInterfaceCatalogo.COLUMN_GROUP_ID)));

        return entity;
    }
}
