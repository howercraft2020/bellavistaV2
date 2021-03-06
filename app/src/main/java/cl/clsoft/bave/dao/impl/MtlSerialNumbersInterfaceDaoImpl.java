package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import java.util.List;

import cl.clsoft.bave.dao.IMtlSerialNumbersInterfaceDao;
import cl.clsoft.bave.dao.catalogo.MtlSerialNumbersInterfaceCatalogo;
import cl.clsoft.bave.dao.rowmapper.MtlSerialNumbersInterfaceRowMapper;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;

public class MtlSerialNumbersInterfaceDaoImpl extends GenericDao<MtlSerialNumbersInterface> implements IMtlSerialNumbersInterfaceDao {

    @Override
    public void insert(MtlSerialNumbersInterface mtlSerialNumbersInterface) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_TRANSACTION_INTERFACE_ID, mtlSerialNumbersInterface.getTransactionInterfaceId());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE, mtlSerialNumbersInterface.getLastUpdateDate());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATED_BY, mtlSerialNumbersInterface.getLastUpdatedBy());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_CREATION_DATE, mtlSerialNumbersInterface.getCreationDate());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_CREATED_BY, mtlSerialNumbersInterface.getCreatedBy());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATE_LOGIN, mtlSerialNumbersInterface.getLastUpdateLogin());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_FM_SERIAL_NUMBER, mtlSerialNumbersInterface.getFmSerialNumber());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_TO_SERIAL_NUMBER, mtlSerialNumbersInterface.getToSerialNumber());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_PRODUCT_CODE, mtlSerialNumbersInterface.getProductCode());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_PRODUCT_TRANSACTION_ID, mtlSerialNumbersInterface.getProductTransactionId());
        super.insert(MtlSerialNumbersInterfaceCatalogo.TABLE,values);
    }

    @Override
    public void update(MtlSerialNumbersInterface mtlSerialNumbersInterface) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE, mtlSerialNumbersInterface.getLastUpdateDate());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATED_BY, mtlSerialNumbersInterface.getLastUpdatedBy());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_CREATION_DATE, mtlSerialNumbersInterface.getCreationDate());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_CREATED_BY, mtlSerialNumbersInterface.getCreatedBy());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATE_LOGIN, mtlSerialNumbersInterface.getLastUpdateLogin());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_FM_SERIAL_NUMBER, mtlSerialNumbersInterface.getFmSerialNumber());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_TO_SERIAL_NUMBER, mtlSerialNumbersInterface.getToSerialNumber());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_PRODUCT_CODE, mtlSerialNumbersInterface.getProductCode());
        values.put(MtlSerialNumbersInterfaceCatalogo.COLUMN_PRODUCT_TRANSACTION_ID, mtlSerialNumbersInterface.getProductTransactionId());
        super.update(MtlSerialNumbersInterfaceCatalogo.TABLE, values, MtlSerialNumbersInterfaceCatalogo.UPDATE, mtlSerialNumbersInterface.getTransactionInterfaceId());
    }

    @Override
    public void delete(Long transactionInterfaceId) throws DaoException {
        super.delete(MtlSerialNumbersInterfaceCatalogo.TABLE, MtlSerialNumbersInterfaceCatalogo.DELETE, transactionInterfaceId);
    }

    @Override
    public void deleteByInterfaceTransactionId(Long interfaceTransactionId) throws DaoException {
        super.delete(MtlSerialNumbersInterfaceCatalogo.TABLE, MtlSerialNumbersInterfaceCatalogo.DELETE_BY_PRODUCT_TRANSACTION_ID, interfaceTransactionId);
    }

    @Override
    public List<MtlSerialNumbersInterface> getAll(Long transactionInterfaceId) throws DaoException {
        return super.selectMany(MtlSerialNumbersInterfaceCatalogo.SELECT_ALL_BY_ID, new MtlSerialNumbersInterfaceRowMapper(), transactionInterfaceId);
    }

    @Override
    public MtlSerialNumbersInterface get(Long transactionInterfaceId) throws DaoException {
        return super.selectOne(MtlSerialNumbersInterfaceCatalogo.SELECT, new MtlSerialNumbersInterfaceRowMapper(), transactionInterfaceId);
    }

    @Override
    public List<MtlSerialNumbersInterface> getAllByInterfaceTransactionId(Long interfaceTransactionId) throws DaoException {
        return super.selectMany(MtlSerialNumbersInterfaceCatalogo.SELECT_BY_PRODUCT_TRANSACTION_ID, new MtlSerialNumbersInterfaceRowMapper(), interfaceTransactionId);
    }


}
