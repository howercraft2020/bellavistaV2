package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.EntregaOrgsHeader;

public interface IEntregaOrgsService {

    public List<EntregaOrgsHeader> getEntregas() throws ServiceException;

}
