package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlSystemItems;

public interface IMtlSystemItemsDao {

    public void insert(MtlSystemItems mtlSystemItems) throws DaoException;
    public List<MtlSystemItems> getAll() throws DaoException;
    public MtlSystemItems getBySegment(String segment) throws DaoException;

}
