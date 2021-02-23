package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IPoDistributionsAllDao;
import cl.clsoft.bave.dao.catalogo.PoDistributionsAllCatalogo;
import cl.clsoft.bave.dao.catalogo.PoHeadersAllCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoDistributionsAll;

public class PoDistributionsAllDaoImpl extends GenericDao<PoDistributionsAll> implements IPoDistributionsAllDao {

    @Override
    public void insert(PoDistributionsAll poDistributionsAll) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(PoDistributionsAllCatalogo.COLUMN_PO_DISTRIBUTION_ID, poDistributionsAll.getPoDistributionId());
        values.put(PoDistributionsAllCatalogo.COLUMN_LINE_LOCATION_ID, poDistributionsAll.getLineLocationId());
        values.put(PoDistributionsAllCatalogo.COLUMN_PO_LINE_ID, poDistributionsAll.getPoLineId());
        values.put(PoDistributionsAllCatalogo.COLUMN_PO_HEADER_ID, poDistributionsAll.getPoHeaderId());
        values.put(PoDistributionsAllCatalogo.COLUMN_DISTRIBUTION_NUM, poDistributionsAll.getDistributionNum());
        values.put(PoDistributionsAllCatalogo.COLUMN_RATE_DATE, poDistributionsAll.getRateDate());
        values.put(PoDistributionsAllCatalogo.COLUMN_RATE, poDistributionsAll.getRate());
        values.put(PoDistributionsAllCatalogo.COLUMN_DESTINATION_SUBINVENTORY, poDistributionsAll.getDestinationSubinventory());
        values.put(PoDistributionsAllCatalogo.COLUMN_DELIVER_TO_LOCATION_ID, poDistributionsAll.getDeliverToLocationId());
        values.put(PoDistributionsAllCatalogo.COLUMN_QUANTITY_ORDERED, poDistributionsAll.getQuantityOrdered());
        values.put(PoDistributionsAllCatalogo.COLUMN_QUANTITY_DELIVERED, poDistributionsAll.getQuantityDelivered());
        values.put(PoDistributionsAllCatalogo.COLUMN_QUANTITY_BILLED, poDistributionsAll.getQuantityBilled());
        values.put(PoDistributionsAllCatalogo.COLUMN_QUANTITY_CANCELLED, poDistributionsAll.getQuantityCancelled());
        super.insert(PoDistributionsAllCatalogo.TABLE, values);

    }
}
