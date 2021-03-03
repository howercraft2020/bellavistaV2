package cl.clsoft.bave.dao;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvShipmentHeaders;

public interface IRcvShipmentHeadersDao {

    public void insert(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException;
    public void update(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException;

}
