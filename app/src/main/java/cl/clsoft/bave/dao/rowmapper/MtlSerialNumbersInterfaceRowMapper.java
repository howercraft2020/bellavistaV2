package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.MtlSerialNumbersInterfaceCatalogo;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;

public class MtlSerialNumbersInterfaceRowMapper implements RowMapper<MtlSerialNumbersInterface>{
    @Override
    public MtlSerialNumbersInterface mapRow(Cursor cursor, int position) throws SQLException {
        MtlSerialNumbersInterface entity = new MtlSerialNumbersInterface();
        entity.setTransactionInterfaceId(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersInterfaceCatalogo.COLUMN_TRANSACTION_INTERFACE_ID)));
        entity.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATE_DATE)));
        entity.setLastUpdatedBy(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersInterfaceCatalogo.COLUMN_LAST_UPDATED_BY)));
        entity.setCreationDate(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersInterfaceCatalogo.COLUMN_CREATION_DATE)));
        entity.setCreatedBy(cursor.getLong(cursor.getColumnIndex(MtlSerialNumbersInterfaceCatalogo.COLUMN_CREATED_BY)));
        entity.setFmSerialNumber(cursor.getString(cursor.getColumnIndex(MtlSerialNumbersInterfaceCatalogo.COLUMN_FM_SERIAL_NUMBER)));
        return entity;
    }
}
