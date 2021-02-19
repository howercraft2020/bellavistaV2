package cl.clsoft.bave.service.impl;

import java.util.List;

import cl.clsoft.bave.dao.IMtlTransactionsInterfaceDao;
import cl.clsoft.bave.dao.impl.MtlTransactionInterfaceDaoImpl;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.ITransSubinvService;

public class TransSubinvService implements ITransSubinvService {
    @Override
    public List<MtlTransactionsInterface> getTransSubinv() throws ServiceException {
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        try {
            return mtlTransactionsInterfaceDao.getTransSubinv();
        }catch (Exception e){

        }
        return null;
    }
}
