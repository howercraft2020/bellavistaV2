package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IRcvShipmentHeadersDao;
import cl.clsoft.bave.dao.catalogo.RcvShipmentHeadersCatalogo;
import cl.clsoft.bave.dao.rowmapper.RcvShipmentHeadersRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvShipmentHeaders;

public class RcvShipmentHeadersDaoImpl extends GenericDao<RcvShipmentHeaders> implements IRcvShipmentHeadersDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException {
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::insert");

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
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::update");

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

    @Override
    public void delete(Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::delete");
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::delete::shipmentHeaderId: " + shipmentHeaderId);

        super.delete(RcvShipmentHeadersCatalogo.TABLE, RcvShipmentHeadersCatalogo.DELETE, shipmentHeaderId);
    }

    @Override
    public RcvShipmentHeaders get(Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::get");
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::get::shipmentHeaderId: " + shipmentHeaderId);

        return super.selectOne(RcvShipmentHeadersCatalogo.SELECT, new RcvShipmentHeadersRowMapper(), shipmentHeaderId);
    }

    @Override
    public List<RcvShipmentHeaders> getAll() throws DaoException {
        Log.d(TAG, "RcvShipmentHeadersDaoImpl::getAll");

        return super.selectMany(RcvShipmentHeadersCatalogo.SELECT_ALL, new RcvShipmentHeadersRowMapper());
    }

}
