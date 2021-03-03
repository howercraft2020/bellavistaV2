package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlTransactionsInterface;

public interface IMtlTransactionsInterfaceDao {
    public List<MtlTransactionsInterface> getTransSubinv() throws DaoException;
    public void insert(MtlTransactionsInterface mtlTransactionsInterface) throws DaoException;
    public Long getLocOrDesNotNull(Long inventoryItemId, String subinventario, String localizador, String transferSubinventory, String transferLocator) throws DaoException;
    public Long getLocOrNullDestNotNull(Long inventoryItemId, String subinventario, String transferSubinventory, String transferLocator) throws DaoException;
    public Long getLocOrNotNullDestNull(Long inventoryItemId, String subinventario, String localizador, String transferSubinventory) throws DaoException;
    public Long getLocOrNullDestNull(Long inventoryItemId, String subinventario, String transferSubinventory) throws DaoException;
    public List<MtlTransactionsInterface> getTransferencias(String numeroTraspaso, String glosa) throws DaoException;
    public List<MtlTransactionsInterface> getTransferenciasByTraspaso(String numeroTraspaso) throws DaoException;
}




