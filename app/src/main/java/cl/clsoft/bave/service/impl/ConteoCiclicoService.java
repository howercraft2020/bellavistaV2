package cl.clsoft.bave.service.impl;

import java.util.List;

import cl.clsoft.bave.dao.IMtlCycleCountHeadersDao;
import cl.clsoft.bave.dao.impl.MtlCycleCountHeadersDaoImpl;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.service.IConteoCiclicoService;

public class ConteoCiclicoService implements IConteoCiclicoService {
    @Override
    public List<MtlCycleCountHeaders> getAllConteosCiclicos() throws ServiceException {
        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        try {
            return mtlCycleCountHeadersDao.getAll();
        } catch(Exception e) {

        }
        return null;
    }
}
