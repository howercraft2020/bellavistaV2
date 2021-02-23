package cl.clsoft.bave.dao.rowmapper.common;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.rowmapper.RowMapper;

public class StringRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(Cursor cursor, int position) throws SQLException {
        return cursor.getString(0);
    }
}
