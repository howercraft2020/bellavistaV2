package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.util.List;

import cl.clsoft.bave.dao.IMtlPhysicalInventoriesDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoryTagsDao;
import cl.clsoft.bave.dao.IMtlPhysicalSubinventoriesDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoryTagsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalSubinventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IInventarioFisicoService;

public class InventarioFisicoService implements IInventarioFisicoService {

    private static final String TAG = "InventarioFisicoService";

    @Override
    public List<MtlPhysicalInventories> getAllInventariosFisicos() throws ServiceException {
        IMtlPhysicalInventoriesDao mtlPhysicalInventoriesDao = new MtlPhysicalInventoriesDaoImpl();
        try {
            List<MtlPhysicalInventories> inventarios = mtlPhysicalInventoriesDao.getAll();
            Log.d(TAG, "size: " + inventarios.size());
            return inventarios;
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlPhysicalInventories getInventarioFisico(Long physicalInventoryId) throws ServiceException {
        IMtlPhysicalInventoriesDao mtlPhysicalInventoriesDao = new MtlPhysicalInventoriesDaoImpl();
        try {

            return mtlPhysicalInventoriesDao.get(physicalInventoryId);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getAllItems() throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAll();
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }

    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllTags(Long physicalInventoryId) throws ServiceException {
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<MtlPhysicalInventoryTags> tags = mtlPhysicalInventoryTagsDao.getAllTags(physicalInventoryId );
            Log.d(TAG, "size" + tags.size());
            return tags;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }

    }

    @Override
    public List<MtlPhysicalSubinventories> getSubinventories(Long physicalInventoryId) throws ServiceException {
        IMtlPhysicalSubinventoriesDao mtlPhysicalSubinventoriesDao = new MtlPhysicalSubinventoriesDaoImpl();
        try {
            List<MtlPhysicalSubinventories> subinventories = mtlPhysicalSubinventoriesDao.getSubinventories(physicalInventoryId);
            return subinventories;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }

    }
}
