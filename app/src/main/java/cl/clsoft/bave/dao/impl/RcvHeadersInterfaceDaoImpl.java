package cl.clsoft.bave.dao.impl;

import cl.clsoft.bave.dao.rowmapper.RowMapper;
import cl.clsoft.bave.dao.rowmapper.RcvHeadersInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import android.content.ContentValues;
import cl.clsoft.bave.dao.IRcvHeadersInterfaceDao;
import cl.clsoft.bave.model.RcvHeadersInterface;

public class RcvHeadersInterfaceDaoImpl extends GenericDao<RcvHeadersInterface> implements IRcvHeadersInterfaceDao
{
    public void insert(final RcvHeadersInterface rcvHeadersInterface) throws DaoException {
        final ContentValues values = new ContentValues();
        values.put("header_interface_id", rcvHeadersInterface.getHeaderInterfaceId());
        values.put("receipt_source_code", rcvHeadersInterface.getReciptSourceCode());
        values.put("transaction_type", rcvHeadersInterface.getTransactionType());
        values.put("last_update_date", rcvHeadersInterface.getLastUpdateDate());
        values.put("last_updated_by", rcvHeadersInterface.getLastUpdateBy());
        values.put("created_by", rcvHeadersInterface.getCreatedBy());
        values.put("receipt_num", rcvHeadersInterface.getReciptNum());
        values.put("vendor_id", rcvHeadersInterface.getVendorId());
        values.put("vendor_site_code", rcvHeadersInterface.getVendorSiteCode());
        values.put("vendor_site_id", rcvHeadersInterface.getVendorSiteId());
        values.put("ship_to_organization_code", rcvHeadersInterface.getShipToOrganizationCode());
        values.put("location_id", rcvHeadersInterface.getLocationId());
        values.put("receiver_id", rcvHeadersInterface.getReceiverId());
        values.put("currency_code", rcvHeadersInterface.getCurrencyCode());
        values.put("conversion_rate_type", rcvHeadersInterface.getConversionRateType());
        values.put("conversion_rate", rcvHeadersInterface.getConversionRate());
        values.put("conversion_rate_date", rcvHeadersInterface.getConversionRateDate());
        values.put("payment_terms_id", rcvHeadersInterface.getPaymentTermsId());
        values.put("transaction_date", rcvHeadersInterface.getTransactionDate());
        values.put("comments", rcvHeadersInterface.getComments());
        values.put("org_id", rcvHeadersInterface.getOrgId());
        values.put("processing_status_code", rcvHeadersInterface.getProcessingStatusCode());
        values.put("validation_flag", rcvHeadersInterface.getValidationFlag());
        values.put("group_id", rcvHeadersInterface.getGroupId());
        super.insert("rcv_headers_interface", values);
    }

    public RcvHeadersInterface get(final Long interfaceHeaderId) throws DaoException {
        return (RcvHeadersInterface)super.selectOne(" SELECT * FROM rcv_headers_interface WHERE header_interface_id = ? ", (RowMapper)new RcvHeadersInterfaceRowMapper(), new Object[] { interfaceHeaderId });
    }
}