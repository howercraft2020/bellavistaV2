package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlOnhandQuantities;

public interface IMtlOnhandQuantitiesDao {

    public void insert(MtlOnhandQuantities mtlOnhandQuantities) throws DaoException;
    public MtlOnhandQuantities get(String articulo, String lote, String subinventario, String localizador) throws DaoException;
    public MtlOnhandQuantities validaSerie(String articulo, String lote, String subinventario, String localizador, String serie) throws DaoException;
    public List<MtlOnhandQuantities> getAll(String articulo, String lote, String subinventario, String localizador) throws DaoException;

}
