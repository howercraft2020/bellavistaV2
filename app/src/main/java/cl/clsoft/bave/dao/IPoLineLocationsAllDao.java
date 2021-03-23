package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.PoLineLocationsAll;

public interface IPoLineLocationsAllDao {
    public void insert(PoLineLocationsAll poLineLocationsAll) throws DaoException;
    public PoLineLocationsAll getById (Long lineLocationId) throws DaoException;
    public void delete(Long poHeaderId) throws DaoException;
}

