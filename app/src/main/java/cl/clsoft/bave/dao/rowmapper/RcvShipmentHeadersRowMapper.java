package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.RcvShipmentHeadersCatalogo;
import cl.clsoft.bave.model.RcvShipmentHeaders;

public class RcvShipmentHeadersRowMapper implements RowMapper<RcvShipmentHeaders>{
    @Override
    public RcvShipmentHeaders mapRow(Cursor cursor, int position) throws SQLException {
        RcvShipmentHeaders entity  = new RcvShipmentHeaders();
        entity.setShipmentHeaderId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_SHIPMENT_HEADER_ID)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_CREATION_DATE)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_USER_ID)));
        entity.setVendorId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_VENDOR_ID)));
        entity.setVendorSiteId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_VENDOR_SITE_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_ORGANIZATION_ID)));
        entity.setShipmentNum(cursor.getString(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_SHIPMENT_NUM)));
        entity.setReceiptNum(cursor.getString(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_RECEIPT_NUM)));
        entity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_EMPLOYEE_ID)));
        entity.setShipToOrgId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_SHIP_TO_ORG_ID)));
        entity.setPoNumber(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_PO_NUMBER)));
        entity.setHeaderInterfaceId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_HEADER_INTERFACE_ID)));
        entity.setInterfaceTransactionId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_INTERFACE_TRANSACTION_ID)));
        entity.setGroupId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_GROUP_ID)));
        entity.setTransactionInterfaceId(cursor.getLong(cursor.getColumnIndex(RcvShipmentHeadersCatalogo.COLUMN_TRANSACTION_INTERFACE_ID)));
        return entity;
    }
}
