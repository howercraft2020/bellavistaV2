package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountHeaders;

public interface IConteoCiclicoService {

    public List<MtlCycleCountHeaders> getAllConteosCiclicos() throws ServiceException;

}
