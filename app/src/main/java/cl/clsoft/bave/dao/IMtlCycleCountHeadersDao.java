package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public interface IMtlCycleCountHeadersDao {

    public void insert(MtlCycleCountHeaders mtlCycleCountHeaders) throws DaoException;
    public void update(MtlCycleCountHeaders mtlCycleCountHeaders) throws DaoException;
    public void delete(Long id) throws DaoException;
    public MtlCycleCountHeaders get(Long id) throws DaoException;
    public List<MtlCycleCountHeaders> getAll() throws DaoException;

}
