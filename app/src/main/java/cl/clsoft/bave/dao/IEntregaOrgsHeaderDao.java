package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.EntregaOrgsHeader;

public interface IEntregaOrgsHeaderDao {

    public List<EntregaOrgsHeader> getAll() throws DaoException;
    public EntregaOrgsHeader get(Long shipmentHeaderId) throws DaoException;

}
