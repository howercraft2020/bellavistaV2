package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlTransactionsInterfaceDao;
import cl.clsoft.bave.dao.catalogo.MtlTransactionsInterfaceCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlTransactionsInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsInterface;

public class MtlTransactionInterfaceDaoImpl extends GenericDao<MtlTransactionsInterface> implements IMtlTransactionsInterfaceDao {


    @Override
    public List<MtlTransactionsInterface> getTransSubinv() throws DaoException {
        return super.selectMany(MtlTransactionsInterfaceCatalogo.SELECT_ALL, new MtlTransactionsInterfaceRowMapper());
    }

    @Override
    public void insert(MtlTransactionsInterface mtlTransactionsInterface) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_INTERFACE_ID,mtlTransactionsInterface.getTransactionInterfaceId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_PROCESS_FLAG, mtlTransactionsInterface.getProcessFlag());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_MODE, mtlTransactionsInterface.getTransactionMode());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE, mtlTransactionsInterface.getLastUpdateDate());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_LAST_UPDATED_BY,mtlTransactionsInterface.getLastUpdatedBy());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_CREATION_DATE,mtlTransactionsInterface.getCreationDate());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_CREATED_BY, mtlTransactionsInterface.getCreatedBy());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlTransactionsInterface.getInventoryItemId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_ORGANIZATION_ID, mtlTransactionsInterface.getOrganizationId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_QUANTITY, mtlTransactionsInterface.getTransactionQuantity());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_PRIMARY_QUANTITY, mtlTransactionsInterface.getPrimaryQuantity());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_UOM, mtlTransactionsInterface.getTransactionUom());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_DATE, mtlTransactionsInterface.getTransactionDate());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_SUBINVENTORY_CODE, mtlTransactionsInterface.getSubinventoryCode());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_LOCATOR_ID, mtlTransactionsInterface.getLocatorId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_SOURCE_NAME, mtlTransactionsInterface.getTransactionSourceName());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_SOURCE_TYPE_ID, mtlTransactionsInterface.getTransactionSourceTypeId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_ACTION_ID, mtlTransactionsInterface.getTransactionActionId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_TYPE_ID, mtlTransactionsInterface.getTransactionTypeId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSACTION_REFERENCE, mtlTransactionsInterface.getTransactionReference());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSFER_SUBINVENTORY, mtlTransactionsInterface.getTransferSubinventory());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSFER_ORGANIZATION, mtlTransactionsInterface.getTransferOrganization());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_TRANSFER_LOCATOR, mtlTransactionsInterface.getTransferLocator());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_SOURCE_CODE, mtlTransactionsInterface.getSourceCode());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_SOURCE_LINE_ID, mtlTransactionsInterface.getSourceLineId());
        values.put(MtlTransactionsInterfaceCatalogo.COLUMN_SOURCE_HEADER_ID, mtlTransactionsInterface.getSourceHeaderId());
        super.insert(MtlTransactionsInterfaceCatalogo.TABLE, values);
    }

    @Override
    public Long getLocOrDesNotNull(Long inventoryItemId, String subinventario, String localizador, String transferSubinventory, String transferLocator) throws DaoException {
        return super.selectLong(MtlTransactionsInterfaceCatalogo.SELECT_LOC_OR_DES_NOT_NULL, inventoryItemId, subinventario, localizador, transferSubinventory, transferLocator);
    }

    @Override
    public Long getLocOrNullDestNotNull(Long inventoryItemId, String subinventario, String transferSubinventory, String transferLocator) throws DaoException {
        return super.selectLong(MtlTransactionsInterfaceCatalogo.SELECT_LOC_OR_NULL_DEST_NOT_NULL, inventoryItemId,subinventario,transferSubinventory,transferLocator);
    }

    @Override
    public Long getLocOrNotNullDestNull(Long inventoryItemId, String subinventario, String localizador, String transferSubinventory) throws DaoException {
        return super.selectLong(MtlTransactionsInterfaceCatalogo.SELECT_LOC_OR_NOT_NULL_DEST_NULL, inventoryItemId, subinventario, localizador, transferSubinventory);
    }

    @Override
    public Long getLocOrNullDestNull(Long inventoryItemId, String subinventario, String transferSubinventory) throws DaoException {
        return super.selectLong(MtlTransactionsInterfaceCatalogo.SELECT_LOC_OR_NULL_DEST_NULL, inventoryItemId, subinventario, transferSubinventory);
    }

    @Override
    public List<MtlTransactionsInterface> getTransferencias(String numeroTraspaso, String glosa) throws DaoException {
        return super.selectMany(MtlTransactionsInterfaceCatalogo.SELECT_TRANSFERENCIAS, new MtlTransactionsInterfaceRowMapper());
    }

    @Override
    public List<MtlTransactionsInterface> getTransferenciasByTraspaso(String numeroTraspaso) throws DaoException {
        return super.selectMany(MtlTransactionsInterfaceCatalogo.SELECT_TRANSFERENCIAS_BY_TRASPASO, new MtlTransactionsInterfaceRowMapper(), numeroTraspaso);
    }


}
