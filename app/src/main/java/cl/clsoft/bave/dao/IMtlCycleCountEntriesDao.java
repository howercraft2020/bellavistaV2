package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlCycleCountEntries;

public interface IMtlCycleCountEntriesDao {

    public void insert(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException;
    public void update(MtlCycleCountEntries mtlCycleCountEntries) throws DaoException;
    public void delete(Long id) throws DaoException;
    public void deleteByHeader(Long headerId) throws DaoException;
    public MtlCycleCountEntries get(Long id) throws DaoException;
    public List<MtlCycleCountEntries> getSigle(Long idSigle) throws DaoException;
    public List<MtlCycleCountEntries> getAll() throws DaoException;
    public List<MtlCycleCountEntries> getAllBySubinventarioLocatorSegmentLoteSerie(Long countHeaderId, String subinventory, Long locatorId, String segment, String serie, String lote) throws DaoException;
    public List<MtlCycleCountEntries> getAllInventariadosByHeader(Long countHeaderId) throws DaoException;
    public List<MtlCycleCountEntries> getAllInventariadosBySubinventario(Long countHeaderId, String subinventory) throws DaoException;
}
