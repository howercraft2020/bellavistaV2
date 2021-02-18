package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoLinesAll;

public interface IPoLinesAllDao {
    public void insert(PoLinesAll poLinesAll) throws DaoException;
}
