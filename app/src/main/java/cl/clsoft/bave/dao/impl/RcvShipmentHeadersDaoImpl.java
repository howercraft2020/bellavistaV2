package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IRcvShipmentHeadersDao;
import cl.clsoft.bave.dao.catalogo.RcvShipmentHeadersCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvShipmentHeaders;

public class RcvShipmentHeadersDaoImpl extends GenericDao<RcvShipmentHeaders> implements IRcvShipmentHeadersDao {

    @Override
    public void insert(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(RcvShipmentHeadersCatalogo.COLUMN_SHIPMENT_HEADER_ID, rcvShipmentHeaders.getShipmentHeaderId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_LAST_UPDATE_DATE, rcvShipmentHeaders.getLastUpdateDate());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_LAST_UPDATED_BY, rcvShipmentHeaders.getLastUpdatedBy());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_CREATION_DATE, rcvShipmentHeaders.getCreationDate());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_CREATED_BY, rcvShipmentHeaders.getCreatedBy());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_USER_ID, rcvShipmentHeaders.getUserId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_VENDOR_ID, rcvShipmentHeaders.getVendorId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_VENDOR_SITE_ID, rcvShipmentHeaders.getVendorSiteId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_ORGANIZATION_ID, rcvShipmentHeaders.getOrganizationId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_SHIPMENT_NUM, rcvShipmentHeaders.getShipmentNum());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_RECEIPT_NUM, rcvShipmentHeaders.getReceiptNum());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_EMPLOYEE_ID, rcvShipmentHeaders.getEmployeeId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_SHIP_TO_ORG_ID, rcvShipmentHeaders.getShipToOrgId());
        super.insert(RcvShipmentHeadersCatalogo.TABLE, values);
    }

    @Override
    public void update(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(RcvShipmentHeadersCatalogo.COLUMN_LAST_UPDATE_DATE, rcvShipmentHeaders.getLastUpdateDate());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_LAST_UPDATED_BY, rcvShipmentHeaders.getLastUpdatedBy());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_CREATION_DATE, rcvShipmentHeaders.getCreationDate());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_CREATED_BY, rcvShipmentHeaders.getCreatedBy());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_USER_ID, rcvShipmentHeaders.getUserId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_VENDOR_ID, rcvShipmentHeaders.getVendorId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_VENDOR_SITE_ID, rcvShipmentHeaders.getVendorSiteId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_ORGANIZATION_ID, rcvShipmentHeaders.getOrganizationId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_SHIPMENT_NUM, rcvShipmentHeaders.getShipmentNum());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_RECEIPT_NUM, rcvShipmentHeaders.getReceiptNum());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_EMPLOYEE_ID, rcvShipmentHeaders.getEmployeeId());
        values.put(RcvShipmentHeadersCatalogo.COLUMN_SHIP_TO_ORG_ID, rcvShipmentHeaders.getShipToOrgId());
        super.update(RcvShipmentHeadersCatalogo.TABLE, values, RcvShipmentHeadersCatalogo.UPDATE, rcvShipmentHeaders.getShipmentHeaderId());
    }

}
