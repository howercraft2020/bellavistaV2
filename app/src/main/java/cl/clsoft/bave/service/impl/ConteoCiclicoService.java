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
import cl.clsoft.bave.dao.IMtlCycleCountEntriesDao;
import cl.clsoft.bave.dao.IMtlCycleCountHeadersDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IOrganizacionPrincipalDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountEntriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountHeadersDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionPrincipalDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IConteoCiclicoService;

public class ConteoCiclicoService implements IConteoCiclicoService {

    private static final String TAG = "ConteoCiclicoService";

    @Override
    public List<MtlCycleCountHeaders> getAllConteosCiclicos() throws ServiceException {
        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        try {
            return mtlCycleCountHeadersDao.getAll();
        } catch(DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlCycleCountHeaders getConteoCiclico(Long cycleCountHeaderId) throws ServiceException {
        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        try{
            return mtlCycleCountHeadersDao.get(cycleCountHeaderId);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Subinventario> getAllSubinventariosByConteoCiclico(Long cycleCountHeaderId) throws ServiceException {
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try {
            return subinventarioDao.getAllByCiclico(cycleCountHeaderId);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlCycleCountEntries> getAllEntriesInventariadas(Long countHeaderId, String subinventory) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getAllEntriesInventariadas::countHeaderId: " + countHeaderId);
        Log.d(TAG, "ConteoCiclicoService::getAllEntriesInventariadas::subinventory: " + subinventory);

        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            List<MtlCycleCountEntries> salida = mtlCycleCountEntriesDao.getAllInventariadosBySubinventario(countHeaderId, subinventory);
            Log.d(TAG, "ConteoCiclicoService::getAllEntriesInventariadas::size: " + salida.size());
            return salida;
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventario");
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

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
    public List<Localizador> getLocalizadoresBySubinventarioCountheaderId(String subinventarioCodigo, Long countHeaderId) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventarioCountheaderId");
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventarioCountheaderId::subinventarioCodigo: " + subinventarioCodigo);
        Log.d(TAG, "ConteoCiclicoService::getLocalizadoresBySubinventarioCountheaderId::countHeaderId: " + countHeaderId);

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            List<Localizador> salida = localizadorDao.getAllBySubinventarioCountheaderId(subinventarioCodigo, countHeaderId);
            return salida;
        } catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            return mtlSystemItemsDao.getBySegment(segment);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public Localizador getLocalizadorByCodigo(String codigo) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getLocalizadorByCodigo");

        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        try {
            Localizador localizador = localizadorDao.getByCodigo(codigo);
            return localizador;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void grabarInventario(Long cycleCountEntrieId, String subinventarioId, Long locatorId, String segment, String serie, String lote, Double cantidad) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::grabarInventario");

        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try {
            List<MtlCycleCountEntries> entries = mtlCycleCountEntriesDao.getAllBySubinventarioLocatorSegmentLoteSerie(cycleCountEntrieId, subinventarioId, locatorId, segment, serie, lote);
            // Valida tag
            if (entries == null) {
                throw new ServiceException(1, "Entrada no encontrada en conteo");
            }

            if (entries.size() == 0) {
                throw new ServiceException(1, "Entrada no encontrada en conteo");
            }

            if (entries.size() > 1) {
                throw new ServiceException(1, "Se encontro mas de una entrada en conteo");
            }

            // Update Entry
            MtlCycleCountEntries entry = entries.get(0);
            entry.setCount(cantidad);

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String strLastUpdate = dateFormat.format(new Date());
            entry.setLastUpdated(strLastUpdate.toUpperCase());
            mtlCycleCountEntriesDao.update(entry);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlCycleCountEntries getEntry(Long entryId) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::getEntry");

        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try {
            return mtlCycleCountEntriesDao.get(entryId);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void updateEntry(Long entryId, Double cantidad) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::updateEntry");
        Log.d(TAG, "ConteoCiclicoService::updateEntry::entryId: " + entryId);
        Log.d(TAG, "ConteoCiclicoService::updateEntry::cantidad: " + cantidad);

        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try {
            MtlCycleCountEntries entry = mtlCycleCountEntriesDao.get(entryId);
            if (entry == null) {
                throw new ServiceException(1, "Entry " + entryId + " no existe.");
            }
            entry.setCount(cantidad);
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String strLastUpdate = dateFormat.format(new Date());
            entry.setLastUpdated(strLastUpdate.toUpperCase());
            mtlCycleCountEntriesDao.update(entry);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void deleteEntry(Long entryId) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::deleteEntry");
        Log.d(TAG, "ConteoCiclicoService::deleteEntry::entryId: " + entryId);

        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try {
            MtlCycleCountEntries entry = mtlCycleCountEntriesDao.get(entryId);
            if (entry == null) {
                throw new ServiceException(1, "Entry " + entryId + " no existe.");
            }
            entry.setCount(null);
            entry.setLastUpdated(null);
            mtlCycleCountEntriesDao.update(entry);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public Long closeConteoCiclico(Long cycleCountHeaderId) throws ServiceException {
        Log.d(TAG, "ConteoCiclicoService::closeConteoCiclico");
        Log.d(TAG, "ConteoCiclicoService::closeConteoCiclico::cycleCountHeaderId: " + cycleCountHeaderId);

        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        IOrganizacionPrincipalDao organizacionPrincipalDao = new OrganizacionPrincipalDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            // Recupera Organizacion
            OrganizacionPrincipal organizacionPrincipal = organizacionPrincipalDao.get();
            if (organizacionPrincipal == null) {
                throw new ServiceException(1, "Organizacion Principal no existe");
            }

            MtlCycleCountHeaders header = mtlCycleCountHeadersDao.get(cycleCountHeaderId);
            if (header == null) {
                throw new ServiceException(1, "Conteo Ciclico " + cycleCountHeaderId + " no existe en el sistema");
            }

            List<MtlCycleCountEntries> entries = mtlCycleCountEntriesDao.getAllInventariadosByHeader(cycleCountHeaderId);
            if (entries.size() == 0){
                throw new ServiceException(1, "Conteo Ciclico " + cycleCountHeaderId + ": no se encontraron entries inventariados.");
            }

            // Genera archivo Conteo
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String strLastUpdate = dateFormat.format(new Date());
            String nombreArchivo = "I_C_" + cycleCountHeaderId + ".txt";

            File tarjetaSD = Environment.getExternalStorageDirectory();
            File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
            File archivo = new File(Dir, nombreArchivo);
            FileWriter writer = new FileWriter(archivo);
            for (MtlCycleCountEntries entry : entries) {
                MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(entry.getInventoryItemId());
                writer.write(
                        header.getOrganizationId() + ";" +              // ORGANIZATION_ID
                                organizacionPrincipal.getCode() + ";" +     // ORGANIZATION_CODE
                                header.getLastUpdateDate() + ";" +          // LAST_UPDATE_DATE
                                header.getLastUpdatedBy() + ";" +           // LAST_UPDATED_BY
                                header.getCreationDate() + ";" +            // CREATION_DATE
                                header.getCreatedBy() + ";" +               // CREATED_BY
                                entry.getCycleCountEntryId() + ";" +        // CYCLE_COUNT_ENTRY_ID
                                "2;" +                                      // ACTION_CODE
                                header.getCycleCountHeaderId() + ";" +      // CYCLE_COUNT_HEADER_ID
                                header.getCycleCountHeaderName() + ";" +    // CYCLE_COUNT_HEADER_NAME
                                entry.getInventoryItemId() + ";" +          // INVENTORY_ITEM_ID
                                mtlSystemItems.getSegment1() + ";" +        // SEGMENT1
                                mtlSystemItems.getDescription() + ";" +     // DESCRIPTION
                                entry.getSubinventory() + ";" +             // SUBINVENTORY
                                entry.getLocatorId() + ";" +                // LOCATOR_ID
                                entry.getLocatorCode() + ";" +              // LOCATOR
                                entry.getLotNumber() + ";" +                // LOT_NUMBER
                                entry.getSerialNumber() + ";" +             // SERIAL_NUMBER
                                entry.getCount() + ";" +                    // PRIMARY_UOM_QUANTITY
                                entry.getPrimaryUomCode() + ";" +           // COUNT_UOM
                                entry.getCount() + ";" +                    // COUNT_QUANTITY
                                entry.getLastUpdated() + ";" +              // COUNT_DATE
                                header.getEmployeeId() + ";" + "" +         // EMPLOYEE_ID
                                "1;" +                                      // LOCK_FLAG
                                "1;" +                                      // DELETE_FLAG
                                "FIN\r\n");
            }
            writer.flush();
            writer.close();

            // Elimina datos de la BD
            mtlCycleCountEntriesDao.deleteByHeader(cycleCountHeaderId);
            mtlCycleCountHeadersDao.delete(cycleCountHeaderId);

            return 0L;
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        } catch(IOException e){
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public List<MtlCycleCountEntries> getAllSigleInformation(Long idSigle) throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.getSigle(idSigle);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlCycleCountEntries> getAllSigleDetalle() throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.getAll();
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlCycleCountEntries getCiclicoSigleDetalle(Long cycleCountEntrieId) throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.get(cycleCountEntrieId);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<String> getSegmentsByCountHeaderIdLocatorId(Long cycleCountEntrieId, Long locatorId) throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.getSegmentsByCountHeaderLocator(cycleCountEntrieId, locatorId);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<String> getLotesByCountHeaderIdLocatorIdSegment(Long cycleCountHeaderId, Long locatorId, String segment) throws ServiceException {
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        try{
            return mtlCycleCountEntriesDao.getLoteByCountHeaderLocatorSegment(cycleCountHeaderId, locatorId, segment);
        }catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }
}
