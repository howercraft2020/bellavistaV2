package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoDistributionsAll;

public interface IPoDistributionsAllDao {
    public void insert(PoDistributionsAll poDistributionsAll) throws DaoException;
}
