package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.catalogo.MtlOnhandQuantitiesCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlOnHandQuantitiesRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public class MtlOnhandQuantitiesDaoImpl extends GenericDao<MtlOnhandQuantities> implements IMtlOnhandQuantitiesDao {

    @Override
    public void insert(MtlOnhandQuantities mtlOnhandQuantities) throws DaoException {
        try {
            ContentValues values = new ContentValues();
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlOnhandQuantities.getInventoryItemId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_ORGANIZATION_ID, mtlOnhandQuantities.getOrganizationId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_SUBINVENTORY_CODE, mtlOnhandQuantities.getSubinventoryCode());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_LOCATOR_ID, mtlOnhandQuantities.getLocatorId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_LOT_NUMBER, mtlOnhandQuantities.getLotNumber());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_SERIAL_NUMBER, mtlOnhandQuantities.getSerialNumber());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_PRIMARY_TRANSACTION_QUANTITY, mtlOnhandQuantities.getPrimaryTransactionQuantity());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_USER_ID, mtlOnhandQuantities.getUserId());
            values.put(MtlOnhandQuantitiesCatalogo.COLUMN_STATUS_ID, mtlOnhandQuantities.getStatusId());
            super.insert(MtlOnhandQuantitiesCatalogo.TABLE, values);
        } catch (Exception ex) {
            throw new DaoException(1, ex.getMessage());
        }
    }

    @Override
    public void deleteAll() throws DaoException {
        super.delete(MtlOnhandQuantitiesCatalogo.TABLE, null, null);
    }

    @Override
    public MtlOnhandQuantities get(String articulo, String lote, String subinventario, String localizador) throws DaoException {
        return super.selectOne(MtlOnhandQuantitiesCatalogo.SELECT, new MtlOnHandQuantitiesRowMapper(), articulo, lote, subinventario, localizador);
    }

    @Override
    public MtlOnhandQuantities validaSerie(String articulo, String lote, String subinventario, String localizador, String serie) throws DaoException {
        return super.selectOne(MtlOnhandQuantitiesCatalogo.SELECT_SERIE, new MtlOnHandQuantitiesRowMapper(), articulo, lote, subinventario, localizador, serie);
    }

    @Override
    public List<MtlOnhandQuantities> getAll(String articulo, String lote, String subinventario, String localizador) throws DaoException {
        return super.selectMany(MtlOnhandQuantitiesCatalogo.SELECT_ALL, new MtlOnHandQuantitiesRowMapper(), articulo, lote, subinventario, localizador);
    }

    /*
    @Override
    public MtlOnhandQuantities get(String articulo, String lote, String subinventario) throws DaoException {
        return super.selectOne(MtlOnhandQuantitiesCatalogo.SELECT, new MtlOnHandQuantitiesRowMapper(), articulo,lote, subinventario);
    }

     */
}
