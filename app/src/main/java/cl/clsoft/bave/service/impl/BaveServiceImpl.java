package cl.clsoft.bave.service.impl;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlCycleCountEntriesDao;
import cl.clsoft.bave.dao.IMtlCycleCountHeadersDao;
import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoriesDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoryTagsDao;
import cl.clsoft.bave.dao.IMtlPhysicalSubinventoriesDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountEntriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountHeadersDaoImpl;
import cl.clsoft.bave.dao.impl.MtlOnhandQuantitiesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoryTagsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalSubinventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IBaveService;

public class BaveServiceImpl implements IBaveService {

    private static final String TAG = "BaveServiceImpl";

    @Override
    public void cargarArchivoSetup(File archivo) throws ServiceException {
        Log.d(TAG, "cargarArchivoSetup");

        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IOrganizacionDao organizacionDao = new OrganizacionDaoImpl();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|");
                if (extraccion[0].equals("2")) {
                    Subinventario subinventario = new Subinventario();
                    subinventario.setOrganizationId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    subinventario.setCodSubinventario(extraccion[2]);
                    subinventario.setCodLocalizador(extraccion[3]);
                    subinventarioDao.insert(subinventario);
                } else if (extraccion[0].equals("3")) {
                    Localizador localizador = new Localizador();
                    localizador.setIdLocalizador(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    localizador.setOrganizationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    localizador.setCodSubinventario(extraccion[3]);
                    localizador.setCodLocalizador(extraccion[4]);
                    localizador.setCodSeg1(extraccion[5]);
                    localizador.setCodSeg2(extraccion[6]);
                    localizador.setCodSeg3(extraccion[7]);
                    localizador.setCodSeg4(extraccion[8]);
                    localizador.setCodSeg5(extraccion[9]);
                    localizador.setCodSeg6(extraccion[10]);
                    localizadorDao.insert(localizador);
                } else if (extraccion[0].equals("4")) {
                    Organizacion organizacion = new Organizacion();
                    organizacion.setIdOrganizacion(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    organizacion.setCode(extraccion[2]);
                    organizacionDao.insert(organizacion);
                }
                linea = leerArchivo.readLine();
            }
            archivo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarArchivoStock(File archivo) throws ServiceException {
        Log.d(TAG, "cargarArchivoStock");

        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|");
                if (extraccion[0].equals("1")) {
                    MtlOnhandQuantities mtlOnhandQuantities = new MtlOnhandQuantities();
                    mtlOnhandQuantities.setInventoryItemId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlOnhandQuantities.setOrganizationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlOnhandQuantities.setPrimaryTransactionQuantity(extraccion[3].equalsIgnoreCase("") ? null : new Long(extraccion[3]));
                    mtlOnhandQuantities.setSubinventoryCode(extraccion[4]);
                    mtlOnhandQuantities.setLocatorId(extraccion[5].equalsIgnoreCase("") ? null : new Long(extraccion[5]));
                    mtlOnhandQuantities.setLotNumber(extraccion[6]);
                    mtlOnhandQuantities.setSerialNumber(extraccion[7]);
                    mtlOnhandQuantities.setUserId(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    //mtlOnhandQuantities.setStatusId(new Long(extraccion[9]));
                    mtlOnhandQuantitiesDao.insert(mtlOnhandQuantities);
                } else if (extraccion[0].equals("2")) {
                    MtlSystemItems mtlSystemItems = new MtlSystemItems();
                    mtlSystemItems.setInventoryItemId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlSystemItems.setDescription(extraccion[2]);
                    mtlSystemItems.setLongDescription(extraccion[3]);
                    mtlSystemItems.setSegment1(extraccion[4]);
                    mtlSystemItems.setPrimaryUomCode(extraccion[5]);
                    mtlSystemItems.setLotControlCode(extraccion[6]);
                    mtlSystemItems.setShelfLifeCode(extraccion[7]);
                    mtlSystemItems.setSerialNumberControlCode(extraccion[8]);
                    mtlSystemItemsDao.insert(mtlSystemItems);
                }
                linea = leerArchivo.readLine();
            }
            archivo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarArchivoCiclico(File archivo) throws ServiceException {
        Log.d(TAG, "cargarArchivoCiclico");

        IMtlCycleCountHeadersDao mtlCycleCountHeadersDao = new MtlCycleCountHeadersDaoImpl();
        IMtlCycleCountEntriesDao mtlCycleCountEntriesDao = new MtlCycleCountEntriesDaoImpl();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|");
                if (extraccion[0].equals("1")) {
                    boolean existe = false;

                    // Valida si ya existe
                    Long id = new Long(extraccion[1]);
                    MtlCycleCountHeaders mtlCycleCountHeaders = mtlCycleCountHeadersDao.get(id);
                    if (mtlCycleCountHeaders != null) {
                        existe = true;
                        mtlCycleCountHeadersDao.delete(id);
                        mtlCycleCountEntriesDao.deleteByHeader(id);
                    }

                    mtlCycleCountHeaders = new MtlCycleCountHeaders();
                    mtlCycleCountHeaders.setCycleCountHeaderId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlCycleCountHeaders.setOrganizationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlCycleCountHeaders.setLastUpdateDate(extraccion[3]);
                    mtlCycleCountHeaders.setLastUpdatedBy(extraccion[4].equalsIgnoreCase("") ? null : new Long(extraccion[4]));
                    mtlCycleCountHeaders.setCreationDate(extraccion[5]);
                    mtlCycleCountHeaders.setCreatedBy(extraccion[6].equalsIgnoreCase("") ? null : new Long(extraccion[6]));
                    mtlCycleCountHeaders.setCycleCountHeaderName(extraccion[7]);
                    mtlCycleCountHeaders.setUserId(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    mtlCycleCountHeaders.setEmployeeId(extraccion[9].equalsIgnoreCase("") ? null : new Long(extraccion[9]));
                    mtlCycleCountHeaders.setDescription(extraccion[10]);
                    mtlCycleCountHeadersDao.insert(mtlCycleCountHeaders);
                } else if (extraccion[0].equals("2")) {
                    MtlCycleCountEntries mtlCycleCountEntries = new MtlCycleCountEntries();
                    mtlCycleCountEntries.setCycleCountEntryId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlCycleCountEntries.setInventoryItemId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlCycleCountEntries.setSubinventory(extraccion[3]);
                    mtlCycleCountEntries.setEntryStatusCode(extraccion[4]);
                    mtlCycleCountEntries.setOrganizationId(extraccion[5].equalsIgnoreCase("") ? null : new Long(extraccion[5]));
                    mtlCycleCountEntries.setCycleCountHeaderId(extraccion[6].equalsIgnoreCase("") ? null : new Long(extraccion[6]));
                    mtlCycleCountEntries.setLocatorId(extraccion[7].equalsIgnoreCase("") ? null : new Long(extraccion[7]));
                    mtlCycleCountEntries.setLotNumber(extraccion[8]);
                    mtlCycleCountEntries.setSegment1(extraccion[9]);
                    mtlCycleCountEntries.setPrimaryUomCode(extraccion[10]);
                    Log.d(TAG, "Size: " + extraccion.length);
                    if (extraccion.length == 12 )
                        mtlCycleCountEntries.setSerialNumber(extraccion[11]);
                    mtlCycleCountEntriesDao.insert(mtlCycleCountEntries);
                }
                linea = leerArchivo.readLine();
            }
            archivo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarArchivoFisico(File archivo) throws ServiceException {
        Log.d(TAG, "cargarArchivoFisico");

        IMtlPhysicalInventoriesDao mtlPhysicalInventoriesDao = new MtlPhysicalInventoriesDaoImpl();
        IMtlPhysicalSubinventoriesDao mtlPhysicalSubinventoriesDao = new MtlPhysicalSubinventoriesDaoImpl();
        IMtlPhysicalInventoryTagsDao mtlPhysicalInventoryTagsDao = new MtlPhysicalInventoryTagsDaoImpl();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|");
                if (extraccion[0].equals("1")) {
                    // Valida si ya existe
                    Long id = new Long(extraccion[1]);
                    MtlPhysicalInventories mtlPhysicalInventories = mtlPhysicalInventoriesDao.get(id);
                    if (mtlPhysicalInventories != null) {
                        mtlPhysicalInventoriesDao.delete(id);
                        mtlPhysicalSubinventoriesDao.deleteByPhysicalInventory(id);
                        mtlPhysicalInventoryTagsDao.deleteByPhysicalInventory(id);
                    }
                    mtlPhysicalInventories = new MtlPhysicalInventories();
                    mtlPhysicalInventories.setPhysicalInventoryId(id);
                    mtlPhysicalInventories.setOrganizationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlPhysicalInventories.setLastUpdateDate(extraccion[3]);
                    mtlPhysicalInventories.setLastUpdatedBy(extraccion[4].equalsIgnoreCase("") ? null : new Long(extraccion[4]));
                    mtlPhysicalInventories.setCreationDate(extraccion[5]);
                    mtlPhysicalInventories.setCreatedBy(extraccion[6].equalsIgnoreCase("") ? null : new Long(extraccion[6]));
                    mtlPhysicalInventories.setPhysicalInventoryDate(extraccion[7]);
                    mtlPhysicalInventories.setDescription(extraccion[8]);
                    mtlPhysicalInventories.setFreezeDate(extraccion[9]);
                    mtlPhysicalInventories.setPhysicalInventoryName(extraccion[10]);
                    mtlPhysicalInventories.setUserId(extraccion[11].equalsIgnoreCase("") ? null : new Long(extraccion[11]));
                    mtlPhysicalInventories.setEmployeeId(extraccion[12].equalsIgnoreCase("") ? null : new Long(extraccion[12]));
                    mtlPhysicalInventories.setApprovalRequired(extraccion[13].equalsIgnoreCase("") ? null : new Long(extraccion[13]));
                    if (extraccion.length == 15)
                        mtlPhysicalInventories.setAllSubinventoriesFlag(extraccion[14].equalsIgnoreCase("") ? null : new Long(extraccion[14]));
                    mtlPhysicalInventoriesDao.insert(mtlPhysicalInventories);
                } else if (extraccion[0].equals("2")) {
                    MtlPhysicalSubinventories mtlPhysicalSubinventories = new MtlPhysicalSubinventories();
                    mtlPhysicalSubinventories.setOrganizationId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlPhysicalSubinventories.setPhyshicalInventoryId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlPhysicalSubinventories.setSubinventory(extraccion[3]);
                    mtlPhysicalSubinventoriesDao.insert(mtlPhysicalSubinventories);
                } else if (extraccion[0].equals("3")) {
                    MtlPhysicalInventoryTags mtlPhysicalInventoryTags = new MtlPhysicalInventoryTags();
                    mtlPhysicalInventoryTags.setTagId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlPhysicalInventoryTags.setPhysicalInventoryId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlPhysicalInventoryTags.setOrganizationId(extraccion[3].equalsIgnoreCase("") ? null : new Long(extraccion[3]));
                    mtlPhysicalInventoryTags.setInventoryItemId(extraccion[4].equalsIgnoreCase("") ? null : new Long(extraccion[4]));
                    mtlPhysicalInventoryTags.setSubinventory(extraccion[5]);
                    mtlPhysicalInventoryTags.setLocatorId(extraccion[6].equalsIgnoreCase("") ? null : new Long(extraccion[6]));
                    mtlPhysicalInventoryTags.setLotNumber(extraccion[7]);
                    mtlPhysicalInventoryTags.setLotExpirationDate(extraccion[8]);
                    mtlPhysicalInventoryTags.setSerialNum(extraccion[9]);
                    mtlPhysicalInventoryTags.setSegment1(extraccion[10]);
                    if (extraccion.length == 12)
                        mtlPhysicalInventoryTags.setPrimaryUomCode(extraccion[11]);
                    mtlPhysicalInventoryTagsDao.insert(mtlPhysicalInventoryTags);
                }
                linea = leerArchivo.readLine();
            }
            archivo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

}
