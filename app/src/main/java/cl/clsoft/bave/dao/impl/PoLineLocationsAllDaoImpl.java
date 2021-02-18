package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IPoLineLocationsAllDao;
import cl.clsoft.bave.dao.catalogo.PoLineLocationsAllCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoLineLocationsAll;

public class PoLineLocationsAllDaoImpl extends GenericDao<PoLineLocationsAll> implements IPoLineLocationsAllDao {

    @Override
    public void insert(PoLineLocationsAll poLineLocationsAll) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(PoLineLocationsAllCatalogo.COLUMN_LINE_LOCATION_ID, poLineLocationsAll.getLineLocationId());
        values.put(PoLineLocationsAllCatalogo.COLUMN_PO_LINE_ID, poLineLocationsAll.getPoLineId());
        values.put(PoLineLocationsAllCatalogo.COLUMN_PO_HEADER_ID, poLineLocationsAll.getPoHeaderId());
        values.put(PoLineLocationsAllCatalogo.COLUMN_QUANTITY, poLineLocationsAll.getQuantity());
        values.put(PoLineLocationsAllCatalogo.COLUMN_QUANTITY_RECEIVED, poLineLocationsAll.getQuantityRecived());
        values.put(PoLineLocationsAllCatalogo.COLUMN_QUANTITY_CANCELLED, poLineLocationsAll.getQuantityCancelled());
        values.put(PoLineLocationsAllCatalogo.COLUMN_QUANTITY_BILLED, poLineLocationsAll.getQuantityBilled());
        values.put(PoLineLocationsAllCatalogo.COLUMN_SHIPMENT_NUM, poLineLocationsAll.getShipmentNum());
        values.put(PoLineLocationsAllCatalogo.COLUMN_SHIP_TO_LOCATION_ID, poLineLocationsAll.getShipToLocationId());
        values.put(PoLineLocationsAllCatalogo.COLUMN_QTY_RCV_TOLERANCE, poLineLocationsAll.getQtyRcvTolerance());
        values.put(PoLineLocationsAllCatalogo.COLUMN_ORG_ID, poLineLocationsAll.getOrgId());
        super.insert(PoLineLocationsAllCatalogo.TABLE, values);
    }
}
