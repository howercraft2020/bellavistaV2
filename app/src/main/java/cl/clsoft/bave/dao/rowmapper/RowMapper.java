package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    public T mapRow(Cursor row, int position) throws SQLException;

}
