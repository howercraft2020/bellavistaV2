package cl.clsoft.bave.dao.rowmapper.common;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.rowmapper.RowMapper;

public class LongRowMapper implements RowMapper<Long> {

    @Override
    public Long mapRow(Cursor cursor, int position) throws SQLException {
        return cursor.getLong(0);
    }

}
