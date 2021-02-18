package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public interface IConteoCiclicoService {

    public List<MtlCycleCountHeaders> getAllConteosCiclicos() throws ServiceException;

    public MtlCycleCountHeaders getConteoCiclico(Long cycleCountHeaderId) throws ServiceException;

    public List<MtlCycleCountEntries> getAllSigleInformation(Long idSigle) throws ServiceException;
}
