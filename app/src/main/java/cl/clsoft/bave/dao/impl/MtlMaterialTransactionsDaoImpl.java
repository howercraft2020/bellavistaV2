package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IMtlMaterialTransactionsDao;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountHeadersCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlMaterialTransactionsCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlMaterialTransactionsRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlMaterialTransactions;

public class MtlMaterialTransactionsDaoImpl extends GenericDao<MtlMaterialTransactions> implements IMtlMaterialTransactionsDao {

    private static final String TAG = "DAO";

    @Override
    public void insert(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException {
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::insert");

        ContentValues values = new ContentValues();
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ID, mtlMaterialTransactions.getTransactionId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlMaterialTransactions.getInventoryItemId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ORGANIZATION_ID, mtlMaterialTransactions.getOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_TYPE_ID, mtlMaterialTransactions.getTransactionTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_ACTION_ID, mtlMaterialTransactions.getTransactionActionId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID, mtlMaterialTransactions.getTransactionSourceTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_NAME, mtlMaterialTransactions.getTransactionSourceName());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_QUANTITY, mtlMaterialTransactions.getTransactionQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_UOM, mtlMaterialTransactions.getTransactionUom());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_PRIMARY_QUANTITY, mtlMaterialTransactions.getPrimaryQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_DATE, mtlMaterialTransactions.getTransactionDate());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ACTUAL_COST, mtlMaterialTransactions.getActualCost());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSFER_ORGANIZATION_ID, mtlMaterialTransactions.getTransferOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIP_TO_LOCATION_ID, mtlMaterialTransactions.getShipToLocationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_RECEIPT_NUM, mtlMaterialTransactions.getReceipNum());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USER_ID, mtlMaterialTransactions.getUserId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_NUMBER, mtlMaterialTransactions.getShipmentNumber());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlMaterialTransactions.getShipmentHeaderId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID, mtlMaterialTransactions.getShipmentLineId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ENTREGA_CREATION_DATE, mtlMaterialTransactions.getEntregaCreationDate());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ENTREGA_QUANTITY, mtlMaterialTransactions.getEntregaQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SUBINVENTORY, mtlMaterialTransactions.getSubinventory());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_LOCATOR_ID, mtlMaterialTransactions.getLocatorId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USE_MTL_LOT, mtlMaterialTransactions.getUseMtlLot());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USE_MTL_SERIAL, mtlMaterialTransactions.getUseMtlSerial());
        super.insert(MtlMaterialTransactionsCatalogo.TABLE, values);
    }

    @Override
    public void update(MtlMaterialTransactions mtlMaterialTransactions) throws DaoException {
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::update");

        ContentValues values = new ContentValues();
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlMaterialTransactions.getInventoryItemId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ORGANIZATION_ID, mtlMaterialTransactions.getOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_TYPE_ID, mtlMaterialTransactions.getTransactionTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_ACTION_ID, mtlMaterialTransactions.getTransactionActionId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID, mtlMaterialTransactions.getTransactionSourceTypeId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_SOURCE_NAME, mtlMaterialTransactions.getTransactionSourceName());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_QUANTITY, mtlMaterialTransactions.getTransactionQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_UOM, mtlMaterialTransactions.getTransactionUom());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_PRIMARY_QUANTITY, mtlMaterialTransactions.getPrimaryQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSACTION_DATE, mtlMaterialTransactions.getTransactionDate());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ACTUAL_COST, mtlMaterialTransactions.getActualCost());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_TRANSFER_ORGANIZATION_ID, mtlMaterialTransactions.getTransferOrganizationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIP_TO_LOCATION_ID, mtlMaterialTransactions.getShipToLocationId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_RECEIPT_NUM, mtlMaterialTransactions.getReceipNum());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USER_ID, mtlMaterialTransactions.getUserId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_NUMBER, mtlMaterialTransactions.getShipmentNumber());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_HEADER_ID, mtlMaterialTransactions.getShipmentHeaderId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SHIPMENT_LINE_ID, mtlMaterialTransactions.getShipmentLineId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ENTREGA_CREATION_DATE, mtlMaterialTransactions.getEntregaCreationDate());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_ENTREGA_QUANTITY, mtlMaterialTransactions.getEntregaQuantity());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_SUBINVENTORY, mtlMaterialTransactions.getSubinventory());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_LOCATOR_ID, mtlMaterialTransactions.getLocatorId());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USE_MTL_LOT, mtlMaterialTransactions.getUseMtlLot());
        values.put(MtlMaterialTransactionsCatalogo.COLUMN_USE_MTL_SERIAL, mtlMaterialTransactions.getUseMtlSerial());
        super.update(MtlMaterialTransactionsCatalogo.TABLE, values, MtlMaterialTransactionsCatalogo.UPDATE, mtlMaterialTransactions.getTransactionId());
    }

    @Override
    public void delete(Long transactionId) throws DaoException {
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::delete");
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::delete::transactionId: " + transactionId);

        super.delete(MtlMaterialTransactionsCatalogo.TABLE, MtlMaterialTransactionsCatalogo.DELETE, transactionId);
    }

    @Override
    public void deleteByShipmentHeaderId(Long shipmentHeaderId) throws DaoException {
        super.delete(MtlMaterialTransactionsCatalogo.TABLE, MtlMaterialTransactionsCatalogo.DELETE_BY_SHIPMENT_HEADER_ID, shipmentHeaderId);
    }

    @Override
    public MtlMaterialTransactions get(Long transactionId) throws DaoException {
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::get");
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::get::transactionId: " + transactionId);

        return super.selectOne(MtlMaterialTransactionsCatalogo.SELECT, new MtlMaterialTransactionsRowMapper(), transactionId);
    }

    @Override
    public List<MtlMaterialTransactions> getAllByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) throws DaoException {
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::getAllByShipmentInventory");
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::getAllByShipmentInventory::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::getAllByShipmentInventory::inventoryItemId: " + inventoryItemId);

        return super.selectMany(MtlMaterialTransactionsCatalogo.SELECT_ALL_BY_SHIPMENT_HEADER_ID__INVENTORY_ITEM_ID, new MtlMaterialTransactionsRowMapper(), shipmentHeaderId, inventoryItemId);
    }

    @Override
    public List<MtlMaterialTransactions> getAllByShipmentEntrega(Long shipmentHeaderId) throws DaoException {
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::getAllByShipmentEntrega");
        Log.d(TAG, "MtlMaterialTransactionsDaoImpl::getAllByShipmentEntrega::shipmentHeaderId: " + shipmentHeaderId);

        return super.selectMany(MtlMaterialTransactionsCatalogo.SELECT_ALL_BY_SHIPMENT_HEADER_ID__ENTREGA, new MtlMaterialTransactionsRowMapper(), shipmentHeaderId);
    }

}
