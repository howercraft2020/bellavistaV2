package cl.clsoft.bave.service;

import java.util.List;


import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlTransactionsInterface;


public interface ITransSubinvService {

    public List<MtlTransactionsInterface> getTransSubinv() throws ServiceException;
}
