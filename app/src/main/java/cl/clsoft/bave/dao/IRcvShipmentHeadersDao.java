package cl.clsoft.bave.dao;

import java.util.List;

import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.RcvShipmentHeaders;

public interface IRcvShipmentHeadersDao {

    public void insert(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException;
    public void update(RcvShipmentHeaders rcvShipmentHeaders) throws DaoException;
    public void delete(Long shipmentHeaderId) throws DaoException;
    public RcvShipmentHeaders get(Long shipmentHeaderId) throws DaoException;
    public List<RcvShipmentHeaders> getAll() throws DaoException;

}
