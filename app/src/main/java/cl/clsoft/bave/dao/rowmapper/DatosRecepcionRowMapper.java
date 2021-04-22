package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.DatosRecepcionCatalogo;
import cl.clsoft.bave.model.DatosRecepcion;

public class  DatosRecepcionRowMapper implements RowMapper<DatosRecepcion> {
    @Override
    public DatosRecepcion mapRow(Cursor cursor, int position) throws SQLException {
        DatosRecepcion entity = new DatosRecepcion();
        entity.setUnitMeasLookupCode(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_UNIT_MEAS_LOOKUP_CODE)));
        entity.setItemId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_ITEM_ID)));
        entity.setItemDescription(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_ITEM_DESCRIPTION)));
        entity.setPrimaryUomCode(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_PRIMARY_UOM_CODE)));
        entity.setVendorId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_VENDOR_ID)));
        entity.setVendorSiteId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_VENDOR_SITE_ID)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_PO_LINE_ID)));
        entity.setLineLocationId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_LINE_LOCATION_ID)));
        entity.setUnitPrice(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_UNIT_PRICE)));
        entity.setCurrencyCode(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_CURRENCY_CODE)));
        entity.setRateType(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_RATE_TYPE)));
        entity.setRate(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_RATE)));
        entity.setRateDate(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_RATE_DATE)));
        entity.setPoDistributionId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_PO_DISTRIBUTION_ID)));
        entity.setVendorSiteCode(cursor.getString(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_VENDOR_SITE_CODE)));
        entity.setQuantity(cursor.getDouble(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_QUANTITY)));
        entity.setQuantityReceived(cursor.getDouble(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_QUANTITY_RECEIVED)));
        entity.setQuantityCancelled(cursor.getDouble(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_QUANTITY_CANCELLED)));
        entity.setQtyRcvTolerance(cursor.getDouble(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_QTY_RCV_TOLERANCE)));
        entity.setUserId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_USER_ID)));
        entity.setOrgId(cursor.getLong(cursor.getColumnIndex(DatosRecepcionCatalogo.COLUMN_ORG_ID)));

        return entity;

    }
}
