package cl.clsoft.bave.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    public T mapRow(ResultSet row, int rowNum) throws SQLException;

}
