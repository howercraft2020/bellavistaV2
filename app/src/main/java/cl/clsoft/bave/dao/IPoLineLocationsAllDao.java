package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoLineLocationsAll;

public interface IPoLineLocationsAllDao {
    public void insert(PoLineLocationsAll poLineLocationsAll) throws DaoException;
}
