package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public MtlPhysicalInventoryTags getTag(Long tagId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getTag");
        Log.d(TAG, "InventarioFisicoService::getTag::tagId : " + tagId);

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            return mtlPhysicalInventoryTagsDao.get(tagId);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void updateTag(Long tagId, long cantidad) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::updateTag");
        Log.d(TAG, "InventarioFisicoService::updateTag::tagId : " + tagId);

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            MtlPhysicalInventoryTags tag = mtlPhysicalInventoryTagsDao.get(tagId);
            if (tag == null) {
                throw new ServiceException(1, "Tag " + tagId + " no existe en inventario.");
            }
            tag.setCount(cantidad);
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String strLastUpdate = dateFormat.format(new Date());
            tag.setLastUpdated(strLastUpdate.toUpperCase());
            mtlPhysicalInventoryTagsDao.update(tag);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void deleteTag(Long tagId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::deleteTag");
        Log.d(TAG, "InventarioFisicoService::updateTag::tagId : " + tagId);

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            MtlPhysicalInventoryTags tag = mtlPhysicalInventoryTagsDao.get(tagId);
            if (tag == null) {
                throw new ServiceException(1, "Tag " + tagId + " no existe en inventario.");
            }
            tag.setCount(null);
            tag.setLastUpdated(null);
            mtlPhysicalInventoryTagsDao.update(tag);
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
    public List<MtlPhysicalInventoryTags> getAllTagsByInventory(Long physicalInventoryId) throws ServiceException {
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<MtlPhysicalInventoryTags> tags = mtlPhysicalInventoryTagsDao.getAllByInventory(physicalInventoryId );
            Log.d(TAG, "size" + tags.size());
            return tags;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }

    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllTagsByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException {
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<MtlPhysicalInventoryTags> tags = mtlPhysicalInventoryTagsDao.getAllByInventorySubinventory(physicalInventoryId, subinventory);
            Log.d(TAG, "size" + tags.size());
            return tags;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllTagsInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException {
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<MtlPhysicalInventoryTags> tags = mtlPhysicalInventoryTagsDao.getAllInventariadosByInventorySubinventory(physicalInventoryId, subinventory);
            Log.d(TAG, "size" + tags.size());
            return tags;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlPhysicalInventoryTags> getAllTagsNoInventariadosByInventorySubinventory(Long physicalInventoryId, String subinventory) throws ServiceException {
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<MtlPhysicalInventoryTags> tags = mtlPhysicalInventoryTagsDao.getAllNoInventariadosByInventorySubinventory(physicalInventoryId, subinventory);
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

    @Override
    public List<String> getLocator(Long physicalInventoryId, String subinventory) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getLocator");

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            return mtlPhysicalInventoryTagsDao.getLocatorByInventorySubinventory(physicalInventoryId, subinventory);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<String> getSegment1(Long physicalInventoryId, String subinventory, Long locatorId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getSegment1");

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<String> segments;
            if (locatorId == null) {
                segments = mtlPhysicalInventoryTagsDao.getSegment1ByInventorySubinventory(physicalInventoryId, subinventory);
            } else {
                segments = mtlPhysicalInventoryTagsDao.getSegment1ByInventorySubinventoryLocator(physicalInventoryId, subinventory, locatorId);
            }
            return segments;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<String> getSeries(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getSeries");

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<String> series;
            series = mtlPhysicalInventoryTagsDao.getSeriesByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment);
            /*
            if (locatorId == null) {
                series = mtlPhysicalInventoryTagsDao.getSeriesByInventorySubinventory(physicalInventoryId, subinventory);
            } else {
                series = mtlPhysicalInventoryTagsDao.getSeriesByInventorySubinventoryLocator(physicalInventoryId, subinventory, locatorId);
            }
             */
            return series;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<String> getLotes(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getLotes");

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<String> lotes;
            lotes = mtlPhysicalInventoryTagsDao.getLotesByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment);
            /*
            if (locatorId == null) {
                lotes = mtlPhysicalInventoryTagsDao.getLotesByInventorySubinventory(physicalInventoryId, subinventory);
            } else {
                lotes = mtlPhysicalInventoryTagsDao.getLotesByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment);
            }
             */
            return lotes;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<String> getVencimientos(Long physicalInventoryId, String subinventory, Long locatorId, String segment) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getVencimientos");

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {
            List<String> vencimientos;
            vencimientos = mtlPhysicalInventoryTagsDao.getVencimientosByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment);
            return vencimientos;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            return mtlSystemItemsDao.getBySegment(segment);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Long cantidad) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::grabarInventario");

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        try {

            List<MtlPhysicalInventoryTags> tags = mtlPhysicalInventoryTagsDao.getAllByInventorySubinventorySegmentSerieLote(inventarioId, subinventarioId, locatorId, segment, serie, lote, vencimiento);

            // Valida tag
            if (tags == null) {
                throw new ServiceException(1, "Tag no encontrado en inventario");
            }

            if (tags.size() == 0) {
                throw new ServiceException(1, "Tag no encontrado en inventario");
            }

            if (tags.size() > 1) {
                throw new ServiceException(1, "Se encontro mas de un Tag en inventario");
            }

            // Update Tag Count
            MtlPhysicalInventoryTags tag = tags.get(0);
            tag.setCount(cantidad);

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String strLastUpdate = dateFormat.format(new Date());
            tag.setLastUpdated(strLastUpdate.toUpperCase());
            mtlPhysicalInventoryTagsDao.update(tag);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }
}
