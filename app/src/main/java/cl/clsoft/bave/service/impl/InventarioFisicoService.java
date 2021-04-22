package cl.clsoft.bave.service.impl;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoriesDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoryTagsDao;
import cl.clsoft.bave.dao.IMtlPhysicalSubinventoriesDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.IOrganizacionPrincipalDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoryTagsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalSubinventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionPrincipalDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.OrganizacionPrincipal;
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
    public void updateTag(Long tagId, Double cantidad) throws ServiceException {
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
    public List<MtlSystemItems> getItemsByDescription(String pattern) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescription(pattern);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsEntregaByDescription(String pattern, Long shipmentHeaderId) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionShipment(pattern, shipmentHeaderId);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsRecepcionByDescription(String pattern, Long poHeaderId) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionPoHeaderId(pattern, poHeaderId);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsTransSubinbByDescription(String pattern, String subinventario, String locatorCodigo) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionSubinvLocator(pattern, subinventario, locatorCodigo);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsTransOrgByDescription(String pattern, String subinventario, String locatorCodigo) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionSubinvLocator(pattern, subinventario, locatorCodigo);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsEntregaOrganizacionesByDescription(String pattern, Long shipmentHeaderId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getItemsEntregaOrganizacionesByDescription");
        Log.d(TAG, "InventarioFisicoService::getItemsEntregaOrganizacionesByDescription::pattern: " + pattern);
        Log.d(TAG, "InventarioFisicoService::getItemsEntregaOrganizacionesByDescription::shipmentHeaderId: " + shipmentHeaderId);

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionShipmentOrganizacion(pattern, shipmentHeaderId);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsCiclicoByDescription(String pattern, Long countHeaderId, Long locatorId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getItemsCiclicoByDescription");
        Log.d(TAG, "InventarioFisicoService::getItemsCiclicoByDescription::pattern: " + pattern);
        Log.d(TAG, "InventarioFisicoService::getItemsCiclicoByDescription::countHeaderId: " + countHeaderId);
        Log.d(TAG, "InventarioFisicoService::getItemsCiclicoByDescription::locatorId: " + locatorId);

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionCountHeaderLocator(pattern, countHeaderId, locatorId);
            Log.d(TAG, "size" + items.size());
            return items;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getItemsFisicoByDescription(String pattern, Long inventoryId, Long locatorId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getItemsFisicoByDescription");
        Log.d(TAG, "InventarioFisicoService::getItemsFisicoByDescription::pattern: " + pattern);
        Log.d(TAG, "InventarioFisicoService::getItemsFisicoByDescription::inventoryId: " + inventoryId);
        Log.d(TAG, "InventarioFisicoService::getItemsFisicoByDescription::locatorId: " + locatorId);

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            List<MtlSystemItems> items = mtlSystemItemsDao.getAllByDescriptionInventoryLocator(pattern, inventoryId, locatorId);
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
    public List<String> getSegment1(Long physicalInventoryId, String subinventory, String locatorCodigo) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getSegment1");
        Log.d(TAG, "InventarioFisicoService::getSegment1::physicalInventoryId: " + physicalInventoryId);
        Log.d(TAG, "InventarioFisicoService::getSegment1::subinventory: " + subinventory);
        Log.d(TAG, "InventarioFisicoService::getSegment1::locatorCodigo: " + locatorCodigo);

        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<String> segments;
            if (locatorCodigo == null) {
                segments = mtlPhysicalInventoryTagsDao.getSegment1ByInventorySubinventory(physicalInventoryId, subinventory);
            } else {
                Localizador localizador = localizadorDao.getByCodigo(locatorCodigo);
                if (localizador != null) {
                    segments = mtlPhysicalInventoryTagsDao.getSegment1ByInventorySubinventoryLocator(physicalInventoryId, subinventory, localizador.getIdLocalizador());
                } else {
                    segments = mtlPhysicalInventoryTagsDao.getSegment1ByInventorySubinventoryLocator(physicalInventoryId, subinventory, null);
                }
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
    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Double cantidad) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::grabarInventario");
        Log.d(TAG, "InventarioFisicoService::grabarInventario::inventarioId: " + inventarioId);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::subinventarioId: " + subinventarioId);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::locatorId: " + locatorId);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::segment: " + segment);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::serie: " + serie);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::lote: " + lote);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::vencimiento: " + vencimiento);
        Log.d(TAG, "InventarioFisicoService::grabarInventario::cantidad: " + cantidad);

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
            if (tag.getCount().doubleValue() > 0 && tag.getLastUpdated() == null) {
                throw new ServiceException(1, "Tag ya se encuentra ingresado.");
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
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException {
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();

        try {
            Localizador localizador = localizadorDao.getByCodigo(codigo);
            return localizador;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getLocalizadoresBySubinventario");
        Log.d(TAG, "InventarioFisicoService::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<Localizador> salida = localizadorDao.getAllBySubinventario(subinventarioCodigo);
            return salida;
        } catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventarioInventario(String subinventarioCodigo, Long inventarioId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::getLocalizadoresBySubinventarioInventario");
        Log.d(TAG, "InventarioFisicoService::getLocalizadoresBySubinventarioInventario::subinventarioCodigo: " + subinventarioCodigo);
        Log.d(TAG, "InventarioFisicoService::getLocalizadoresBySubinventarioInventario::inventarioId: " + inventarioId);

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<Localizador> salida = localizadorDao.getAllBySubinventarioInventarioId(subinventarioCodigo, inventarioId);
            return salida;
        } catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public String closeInventory(Long inventoryId) throws ServiceException {
        Log.d(TAG, "InventarioFisicoService::closeInventory");
        Log.d(TAG, "InventarioFisicoService::closeInventory::inventoryId: " + inventoryId);

        String salida = "";
        IMtlPhysicalInventoriesDao mtlPhysicalInventoriesDao = new MtlPhysicalInventoriesDaoImpl();
        IMtlPhysicalSubinventoriesDao mtlPhysicalSubinventoriesDao = new MtlPhysicalSubinventoriesDaoImpl();
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        IOrganizacionPrincipalDao organizacionPrincipalDao = new OrganizacionPrincipalDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            // Recupera Organizacion
            OrganizacionPrincipal organizacionPrincipal = organizacionPrincipalDao.get();
            if (organizacionPrincipal == null) {
                throw new ServiceException(1, "Organizacion Principal no existe");
            }

            // Valida la existencia inventario
            MtlPhysicalInventories inventory = mtlPhysicalInventoriesDao.get(inventoryId);
            if (inventory == null) {
                throw new ServiceException(1, "Inventario " + inventoryId + " no existe en el sistema");
            }

            // Recupera Tags inventariados
            List<MtlPhysicalInventoryTags> tagsInventariados = mtlPhysicalInventoryTagsDao.getAllInventariadosByInventory(inventoryId);
            if (tagsInventariados.size() == 0){
                throw new ServiceException(1, "Inventario " + inventoryId + ": no se encontraron tags inventariados.");
            }

            // Genera archivo Conteo
            String nombreArchivo = "I_F_" + inventoryId + ".txt";

            File tarjetaSD = Environment.getExternalStorageDirectory();
            File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
            File archivo = new File(Dir, nombreArchivo);
            FileWriter writer = new FileWriter(archivo);
            for (MtlPhysicalInventoryTags tag : tagsInventariados) {
                MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(tag.getInventoryItemId());
                if (mtlSystemItems != null) {
                    writer.write(
                            tag.getPhysicalInventoryId() + ";"                                              // PHYSICAL_INVENTORY_ID
                                    + tag.getOrganizationId() + ";"                                             // ORG
                                    + organizacionPrincipal.getCode() + ";"                                     // ORGANIZATION_CODE
                                    + tag.getTagId() + ";"                                                      // TAG_NUMBER
                                    + tag.getInventoryItemId() + ";"                                            // INVENTORY_ITEM_ID
                                    + mtlSystemItems.getSegment1() + ";"                                        // SEGMENT1
                                    + mtlSystemItems.getDescription() + ";"                                     // DESCRIPTION
                                    + tag.getCount() + ";"                                                      // COUNT
                                    + "U;"                                                                      // ACTION
                                    + tag.getPrimaryUomCode() + ";"                                             // TAG_UOM
                                    + tag.getSubinventory() + ";"                                               // SUBINVENTORY
                                    + (tag.getLocatorId() == null ? "null" : tag.getLocatorId()) + ";"          // LOCATOR_ID
                                    + (tag.getLocatorCode() == null ? "null" : (tag.getLocatorCode().isEmpty() ? "null" : tag.getLocatorCode())) + ";"  // LOCATOR
                                    + (tag.getLotNumber() == null ? "null" : (tag.getLotNumber().isEmpty() ? "null" : tag.getLotNumber())) + ";"        // LOT_NUMBER
                                    + (tag.getSerialNum() == null ? "null" : (tag.getSerialNum().isEmpty() ? "null" : tag.getSerialNum()))              // SERIAL_NUM
                                    + ";FIN\r\n"
                    );
                }
            }
            writer.flush();
            writer.close();

            salida = archivo.getAbsolutePath();

            // Elimina datos de la BD
            mtlPhysicalInventoryTagsDao.deleteByPhysicalInventory(inventoryId);
            mtlPhysicalSubinventoriesDao.deleteByPhysicalInventory(inventoryId);
            mtlPhysicalInventoriesDao.delete(inventoryId);
            return salida;

        } catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        } catch(IOException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        }
    }
}
