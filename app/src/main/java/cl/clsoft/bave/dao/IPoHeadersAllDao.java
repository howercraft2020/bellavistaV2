package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoHeadersAll;

public interface IPoHeadersAllDao {

    public void insert(PoHeadersAll poHeadersAll) throws DaoException;
    public List<PoHeadersAll> getAll() throws DaoException;
    public PoHeadersAll getbyId(Long poHeaderId) throws DaoException;
    public void delete(Long poHeaderId) throws DaoException;
}
