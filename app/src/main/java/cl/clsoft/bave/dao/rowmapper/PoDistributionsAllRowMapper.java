package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.PoDistributionsAllCatalogo;
import cl.clsoft.bave.model.PoDistributionsAll;

public class PoDistributionsAllRowMapper implements RowMapper<PoDistributionsAll>{
    @Override
    public PoDistributionsAll mapRow(Cursor cursor, int position) throws SQLException {
        PoDistributionsAll entity = new PoDistributionsAll();
        entity.setPoDistributionId(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_ID)));
        entity.setLineLocationId(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_LINE_LOCATION_ID)));
        entity.setPoLineId(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_PO_LINE_ID)));
        entity.setPoHeaderId(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_PO_HEADER_ID)));
        entity.setDistributionNum(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_DISTRIBUTION_NUM)));
        entity.setRateDate(cursor.getString(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_RATE_DATE)));
        entity.setRate(cursor.getString(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_RATE)));
        entity.setDestinationSubinventory(cursor.getString(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_DESTINATION_SUBINVENTORY)));
        entity.setDeliverToLocationId(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_DELIVER_TO_LOCATION_ID)));
        entity.setQuantityOrder(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_QUANTITY_ORDERED)));
        entity.setQuantityDelivered(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_QUANTITY_DELIVERED)));
        entity.setQuantityBilled(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_QUANTITY_BILLED)));
        entity.setQuantityCancelled(cursor.getLong(cursor.getColumnIndex(PoDistributionsAllCatalogo.COLUMN_QUANTITY_CANCELLED)));

        return entity;
    }
}
