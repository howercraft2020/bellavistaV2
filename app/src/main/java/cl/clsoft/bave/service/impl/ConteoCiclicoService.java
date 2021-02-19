package cl.clsoft.bave.service.impl;

import java.util.List;

import cl.clsoft.bave.dao.IMtlCycleCountEntriesDao;
import cl.clsoft.bave.dao.IMtlCycleCountHeadersDao;
import cl.clsoft.bave.dao.impl.MtlCycleCountEntriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountHeadersDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.service.IConteoCiclicoService;

public class ConteoCiclicoService implements IConteoCiclicoService {
    @Override
    public List<MtlCycleCountHeaders> getAllConteosCiclicos() throws ServiceException {
        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        try {
            return mtlCycleCountHeadersDao.getAll();
        } catch(DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlCycleCountHeaders getConteoCiclico(Long cycleCountHeaderId) throws ServiceException {
        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        try{
            return mtlCycleCountHeadersDao.get(cycleCountHeaderId);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlCycleCountEntries> getAllSigleInformation(Long idSigle) throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.getSigle(idSigle);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlCycleCountEntries> getAllSigleDetalle() throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.getAll();
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlCycleCountEntries getCiclicoSigleDetalle(Long cycleCountEntrieId) throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.get(cycleCountEntrieId);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }
}
