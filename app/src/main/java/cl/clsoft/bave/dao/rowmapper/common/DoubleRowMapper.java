package cl.clsoft.bave.dao.rowmapper.common;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.rowmapper.RowMapper;

public class DoubleRowMapper implements RowMapper<Double> {

    @Override
    public Double mapRow(Cursor cursor, int position) throws SQLException {
        return cursor.getDouble(0);
    }

}
