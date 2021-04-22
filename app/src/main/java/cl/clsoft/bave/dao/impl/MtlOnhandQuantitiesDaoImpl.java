package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.catalogo.MtlOnhandQuantitiesCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoryTagsCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlOnHandQuantitiesRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public class MtlOnhandQuantitiesDaoImpl extends GenericDao<MtlOnhandQuantities> implements IMtlOnhandQuantitiesDao {

    private static final String TAG = "Dao";

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
    public MtlOnhandQuantities getLocatorNull(String articulo, String lote, String subinventario) throws DaoException {
        return super.selectOne(MtlOnhandQuantitiesCatalogo.SELECT_LOCATOR_NULL, new MtlOnHandQuantitiesRowMapper(), articulo, lote, subinventario);
    }

    @Override
    public Double getCantidad(String articulo, String lote, String subinventario, String localizador) throws DaoException {
        return super.selectDouble(MtlOnhandQuantitiesCatalogo.SELECT_QUANTITY, articulo, lote,subinventario, localizador);
    }

    @Override
    public Double getCantidadLocatorNull(String articulo, String lote, String subinventario) throws DaoException {
        return super.selectDouble(MtlOnhandQuantitiesCatalogo.SELECT_QUANTITY_LOCATOR_NULL, articulo, lote,subinventario);
    }

    @Override
    public MtlOnhandQuantities validaSerie(String articulo, String lote, String subinventario, String localizador, String serie) throws DaoException {
        return super.selectOne(MtlOnhandQuantitiesCatalogo.SELECT_SERIE, new MtlOnHandQuantitiesRowMapper(), articulo, lote, subinventario, localizador, serie);
    }

    @Override
    public List<MtlOnhandQuantities> getAll(String articulo, String lote, String subinventario, String localizador) throws DaoException {
        return super.selectMany(MtlOnhandQuantitiesCatalogo.SELECT_ALL, new MtlOnHandQuantitiesRowMapper(), articulo, lote, subinventario, localizador);
    }

    @Override
    public List<String> getSegment1BySubinventory(String subinventory) throws DaoException {
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getSegment1BySubinventory");
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getSegment1BySubinventory::subinventory : " + subinventory);

        return super.selectManyString(MtlOnhandQuantitiesCatalogo.SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY, subinventory);
    }

    @Override
    public List<String> getSegment1BySubinventoryLocator(String subinventory, Long locatorId) throws DaoException {
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getSegment1ByInventorySubinventoryLocator");
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getSegment1BySubinventoryLocator::subinventory : " + subinventory);
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getSegment1BySubinventoryLocator::locatorId : " + locatorId);

        List<String> salida;
        if (locatorId != null) {
            salida = super.selectManyString(MtlOnhandQuantitiesCatalogo.SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATOR, subinventory, locatorId);
        } else {
            salida = super.selectManyString(MtlOnhandQuantitiesCatalogo.SELECT_SEGMENT1_BY_INVENTORY_SUBINVENTORY_LOCATORNULL, subinventory);
        }
        return salida;
    }

    @Override
    public List<String> getLoteBySubinventory(String subinventory, String segment1) throws DaoException {
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getLoteBySubinventory");
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getLoteBySubinventory::subinventory : " + subinventory);

        return super.selectManyString(MtlOnhandQuantitiesCatalogo.SELECT_LOT_BY_INVENTORY_SUBINVENTORY, subinventory, segment1);
    }

    @Override
    public List<String> getLoteBySubinventoryLocator(String subinventory, Long locatorId, String segment1) throws DaoException {
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getLoteBySubinventoryLocator");
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getLoteBySubinventoryLocator::subinventory : " + subinventory);
        Log.d(TAG, "MtlOnhandQuantitiesDaoImpl::getLoteBySubinventoryLocator::locatorId : " + locatorId);

        List<String> salida;
        if (locatorId != null) {
            salida = super.selectManyString(MtlOnhandQuantitiesCatalogo.SELECT_LOT_BY_INVENTORY_SUBINVENTORY_LOCATOR, subinventory, locatorId, segment1);
        } else {
            salida = super.selectManyString(MtlOnhandQuantitiesCatalogo.SELECT_LOT_BY_INVENTORY_SUBINVENTORY_LOCATORNULL, subinventory, segment1);
        }
        return salida;
    }

    /*
    @Override
    public MtlOnhandQuantities get(String articulo, String lote, String subinventario) throws DaoException {
        return super.selectOne(MtlOnhandQuantitiesCatalogo.SELECT, new MtlOnHandQuantitiesRowMapper(), articulo,lote, subinventario);
    }

     */
}
