package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoDistributionsAll;

public interface IPoDistributionsAllDao {
    public void insert(PoDistributionsAll poDistributionsAll) throws DaoException;
    public PoDistributionsAll getById (Long poDistributionId) throws DaoException;
    public void delete(Long poHeaderId) throws DaoException;
}
