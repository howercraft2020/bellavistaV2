package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlSystemItems;

public interface IMtlPhysicalInventoriesDao {

    public void insert(MtlPhysicalInventories mtlPhysicalInventories) throws DaoException;
    public void update(MtlPhysicalInventories mtlPhysicalInventories) throws DaoException;
    public void delete(Long id) throws DaoException;
    public MtlPhysicalInventories get(Long id) throws DaoException;
    public List<MtlPhysicalInventories> getAll() throws DaoException;


}
