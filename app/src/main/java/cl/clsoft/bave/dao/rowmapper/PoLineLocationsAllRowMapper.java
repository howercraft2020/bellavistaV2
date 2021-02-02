package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.PoLineLocationsAllCatalogo;
import cl.clsoft.bave.model.PoLineLocationsAll;

public class PoLineLocationsAllRowMapper implements RowMapper<PoLineLocationsAll>{
    @Override
    public PoLineLocationsAll mapRow(Cursor cursor, int position) throws SQLException {
        PoLineLocationsAll entity = new PoLineLocationsAll();
        entity.setLineLocationId(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_LINE_LOCATION_ID)));
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_PO_LINE_ID)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setQuantity(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_QUANTITY)));
        entity.setQuantityRecived(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_QUANTITY_RECEIVED)));
        entity.setQuantityCancelled(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_QUANTITY_CANCELLED)));
        entity.setQuantityBilled(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_QUANTITY_BILLED)));
        entity.setShipmentNum(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_SHIPMENT_NUM)));
        entity.setShipToLocationId(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_SHIP_TO_LOCATION_ID)));
        entity.setQtyRcvTolerance(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_QTY_RCV_TOLERANCE)));
        entity.setOrgId(cursor.getLong(cursor.getColumnIndex(PoLineLocationsAllCatalogo.COLUMN_ORG_ID)));

        return entity;
    }
}
