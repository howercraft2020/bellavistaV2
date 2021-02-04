package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public interface IMtlCycleCountEntriesDao {

    public void insert(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException;
    public void update(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException;
    public void delete(Long id) throws DaoException;
    public void deleteByHeader(Long headerId) throws DaoException;
    public MtlCycleCountEntries get(Long id) throws DaoException;
}
