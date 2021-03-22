package cl.clsoft.bave.service.impl;

import java.util.List;

import cl.clsoft.bave.dao.IEntregaOrgsHeaderDao;
import cl.clsoft.bave.dao.impl.EntregaOrgsHeaderDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.service.IEntregaOrgsService;

public class EntregaOrgsServiceImpl implements IEntregaOrgsService {

    @Override
    public List<EntregaOrgsHeader> getEntregas() throws ServiceException {
        IEntregaOrgsHeaderDao entregaOrgsHeaderDao = new EntregaOrgsHeaderDaoImpl();

        try {
            return entregaOrgsHeaderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

}
