package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvHeadersInterface;

public interface IRcvHeadersInterfaceDao
{
    void insert(final RcvHeadersInterface p0) throws DaoException;

    RcvHeadersInterface get(final Long p0) throws DaoException;
}