package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvHeadersInterface;

public interface IRcvHeadersInterfaceDao
{
    void insert(RcvHeadersInterface rcvHeadersInterface) throws DaoException;
    RcvHeadersInterface get( Long interfaceHeaderId) throws DaoException;
}