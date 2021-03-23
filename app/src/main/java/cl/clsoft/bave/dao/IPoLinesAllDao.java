package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoLinesAll;

public interface IPoLinesAllDao {
    public void insert(PoLinesAll poLinesAll) throws DaoException;
    public PoLinesAll getById (Long poLineId) throws DaoException;
    public void delete(Long poHeaderId) throws DaoException;
    public List<PoLinesAll> getLines (Long inventorItemId, Long poHeaderId) throws DaoException;
    public PoLinesAll getLinea (Long inventorItemId, Long poHeaderId, Long poLineNum) throws DaoException;
}
