package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public interface IMtlOnhandQuantitiesDao {

    public void insert(MtlOnhandQuantities mtlOnhandQuantities) throws DaoException;

}