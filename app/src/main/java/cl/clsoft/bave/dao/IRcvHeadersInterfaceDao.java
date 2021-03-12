package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvHeadersInterface;

public interface IRcvHeadersInterfaceDao
{

    public void insert(RcvHeadersInterface rcvHeadersInterface) throws DaoException;
    public void update(RcvHeadersInterface rcvHeadersInterface) throws DaoException;
    public void delete(Long headerInterfaceId) throws DaoException;
    RcvHeadersInterface get(Long headerInterfaceId) throws DaoException;
}