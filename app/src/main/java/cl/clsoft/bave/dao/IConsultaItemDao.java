package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.ConsultaItem;

public interface IConsultaItemDao {

    public List<ConsultaItem> getAllByItem(Long inventoryItemId) throws DaoException;
    public List<ConsultaItem> getAllBySubinventory(String subinventoryCode) throws DaoException;
    public List<ConsultaItem> getAllBySubinventoryItem(String subinventoryCode, Long inventoryItemId) throws DaoException;

}
