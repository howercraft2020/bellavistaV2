package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IConsultaItemDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.ConsultaItemDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IConsultaService;

public class ConsultaServiceImpl implements IConsultaService {

    private static final String TAG = "SERVICE";

    @Override
    public List<ConsultaItem> getAllByItem(Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "ConsultaServiceImpl::getAllByItem");
        Log.d(TAG, "ConsultaServiceImpl::getAllByItem::inventoryItemId: " + inventoryItemId);

        IConsultaItemDao consultaItemDao = new ConsultaItemDaoImpl();
        try {
            return consultaItemDao.getAllByItem(inventoryItemId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<ConsultaItem> getAllBySubinventory(String subinventoryCode) throws ServiceException {
        Log.d(TAG, "ConsultaServiceImpl::getAllBySubinventory");
        Log.d(TAG, "ConsultaServiceImpl::getAllBySubinventory::subinventoryCode: " + subinventoryCode);

        IConsultaItemDao consultaItemDao = new ConsultaItemDaoImpl();
        try {
            return consultaItemDao.getAllBySubinventory(subinventoryCode);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            MtlSystemItems mtlSystemItems = mtlSystemItemsDao.getBySegment(segment);
            if (mtlSystemItems == null) {
                throw new ServiceException(1, "Item " + segment + " no encontrado en tabla maestra");
            }
            return mtlSystemItems;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getMtlSystemItemsById");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(inventoryItemId);
            if (mtlSystemItems == null) {
                throw new ServiceException(1, "Item ID " + inventoryItemId + " no encontrado en tabla maestra");
            }
            return mtlSystemItems;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Subinventario> getSubinventarios() throws ServiceException {
        Log.d(TAG, "EntregaServiceImpl::getSubinventarios");

        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try {
            return subinventarioDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

}
