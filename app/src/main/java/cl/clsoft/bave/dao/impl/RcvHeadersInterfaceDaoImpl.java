package cl.clsoft.bave.dao.impl;

import cl.clsoft.bave.dao.catalogo.MtlCycleCountHeadersCatalogo;
import cl.clsoft.bave.dao.catalogo.RcvHeadersInterfaceCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlCycleCountHeadersRowMapper;
import cl.clsoft.bave.dao.rowmapper.RowMapper;
import cl.clsoft.bave.dao.rowmapper.RcvHeadersInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import android.content.ContentValues;
import cl.clsoft.bave.dao.IRcvHeadersInterfaceDao;
import cl.clsoft.bave.model.RcvHeadersInterface;

public class RcvHeadersInterfaceDaoImpl extends GenericDao<RcvHeadersInterface> implements IRcvHeadersInterfaceDao
{
    public void insert(RcvHeadersInterface rcvHeadersInterface) throws DaoException {
        final ContentValues values = new ContentValues();
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_HEADER_INTERFACE_ID, rcvHeadersInterface.getHeaderInterfaceId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_RECEIPT_SOURCE_CODE, rcvHeadersInterface.getReciptSourceCode());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_TRANSACTION_TYPE, rcvHeadersInterface.getTransactionType());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_AUTO_TRANSACT_CODE, rcvHeadersInterface.getAutoTransactCode());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE, rcvHeadersInterface.getLastUpdateDate());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_LAST_UPDATED_BY, rcvHeadersInterface.getLastUpdateBy());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_CREATED_BY, rcvHeadersInterface.getCreatedBy());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_RECEIPT_NUM, rcvHeadersInterface.getReciptNum());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_VENDOR_ID, rcvHeadersInterface.getVendorId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_VENDOR_SITE_CODE, rcvHeadersInterface.getVendorSiteCode());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_VENDOR_SITE_ID, rcvHeadersInterface.getVendorSiteId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_SHIP_TO_ORGANIZATION_CODE, rcvHeadersInterface.getShipToOrganizationCode());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_LOCATION_ID, rcvHeadersInterface.getLocationId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_RECEIVER_ID, rcvHeadersInterface.getReceiverId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_CURRENCY_CODE, rcvHeadersInterface.getCurrencyCode());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_CONVERSION_RATE_TYPE, rcvHeadersInterface.getConversionRateType());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_CONVERSION_RATE, rcvHeadersInterface.getConversionRate());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_CONVERSION_RATE_DATE, rcvHeadersInterface.getConversionRateDate());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_PAYMENT_TERMS_ID, rcvHeadersInterface.getPaymentTermsId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_TRANSACTION_DATE, rcvHeadersInterface.getTransactionDate());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_COMMENTS, rcvHeadersInterface.getComments());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_ORG_ID, rcvHeadersInterface.getOrgId());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_PROCESSING_STATUS_CODE, rcvHeadersInterface.getProcessingStatusCode());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_VALIDATION_FLAG, rcvHeadersInterface.getValidationFlag());
        values.put(RcvHeadersInterfaceCatalogo.COLUMN_GROUP_ID, rcvHeadersInterface.getGroupId());
        super.insert(RcvHeadersInterfaceCatalogo.TABLE, values);
    }

    public RcvHeadersInterface get(Long interfaceHeaderId) throws DaoException {
        return super.selectOne(" SELECT * FROM rcv_headers_interface WHERE header_interface_id = ? ", new RcvHeadersInterfaceRowMapper(), interfaceHeaderId);
    }
}