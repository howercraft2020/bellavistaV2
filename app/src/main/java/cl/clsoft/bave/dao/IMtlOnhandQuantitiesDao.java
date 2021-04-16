package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public interface IMtlOnhandQuantitiesDao {

    public void insert(MtlOnhandQuantities mtlOnhandQuantities) throws DaoException;
    public void deleteAll() throws DaoException;
    public MtlOnhandQuantities get(String articulo, String lote, String subinventario, String localizador) throws DaoException;
    public Double getCantidad(String articulo, String lote, String subinventario, String localizador) throws DaoException;
    public MtlOnhandQuantities validaSerie(String articulo, String lote, String subinventario, String localizador, String serie) throws DaoException;
    public List<MtlOnhandQuantities> getAll(String articulo, String lote, String subinventario, String localizador) throws DaoException;
    public List<String> getSegment1BySubinventory(String subinventory) throws DaoException;
    public List<String> getSegment1BySubinventoryLocator(String subinventory, Long locatorId) throws DaoException;
    public List<String> getLoteBySubinventory(String subinventory, String segment1) throws DaoException;
    public List<String> getLoteBySubinventoryLocator(String subinventory, Long locatorId, String segment1) throws DaoException;


}
