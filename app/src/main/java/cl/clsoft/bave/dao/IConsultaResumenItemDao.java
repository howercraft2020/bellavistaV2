package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.ConsultaResumenItem;

public interface IConsultaResumenItemDao {

    public List<ConsultaResumenItem> getAllBySubinventory(String subinventoryCode) throws DaoException;

}
