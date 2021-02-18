package cl.clsoft.bave.service;

import java.util.List;

import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.RcvTransactionsInterface;

public interface ITransSubinvService {

    public List<RcvTransactionsInterface> getTransSubinv() throws ServiceException;
}
