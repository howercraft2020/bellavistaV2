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
import cl.clsoft.bave.dao.IMtlMaterialTransactionsDao;
import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoriesDao;
import cl.clsoft.bave.dao.IMtlPhysicalInventoryTagsDao;
import cl.clsoft.bave.dao.IMtlPhysicalSubinventoriesDao;
import cl.clsoft.bave.dao.IMtlSerialNumbersDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IMtlTransactionLotNumbersDao;
import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.IPoDistributionsAllDao;
import cl.clsoft.bave.dao.IPoHeadersAllDao;
import cl.clsoft.bave.dao.IPoLineLocationsAllDao;
import cl.clsoft.bave.dao.IPoLinesAllDao;
import cl.clsoft.bave.dao.IRcvShipmentHeadersDao;
import cl.clsoft.bave.dao.IRcvTransactionsDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountEntriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlCycleCountHeadersDaoImpl;
import cl.clsoft.bave.dao.impl.MtlMaterialTransactionsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlOnhandQuantitiesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalInventoryTagsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlPhysicalSubinventoriesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSerialNumbersDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotNumbersDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionDaoImpl;
import cl.clsoft.bave.dao.impl.PoDistributionsAllDaoImpl;
import cl.clsoft.bave.dao.impl.PoHeadersAllDaoImpl;
import cl.clsoft.bave.dao.impl.PoLineLocationsAllDaoImpl;
import cl.clsoft.bave.dao.impl.PoLinesAllDaoImpl;
import cl.clsoft.bave.dao.impl.RcvShipmentHeadersDaoImpl;
import cl.clsoft.bave.dao.impl.RcvTransactionsDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSerialNumbers;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.model.PoDistributionsAll;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.model.PoLineLocationsAll;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
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
                String [] extraccion = linea.split("\\|",-1);
                if (extraccion[0].equals("2")) {
                    Subinventario subinventario = new Subinventario();
                    subinventario.setOrganizationId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    subinventario.setCodSubinventario(extraccion[2]);
                    subinventario.setDescription(extraccion[3]);
                    subinventario.setCodLocalizador(extraccion[4]);
                    subinventarioDao.insert(subinventario);
                } else if (extraccion[0].equals("3")) {
                    Localizador localizador = new Localizador();
                    localizador.setIdLocalizador(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    localizador.setOrganizationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    if (extraccion.length >= 4)
                        localizador.setCodSubinventario(extraccion[3]);
                    if (extraccion.length >= 5)
                        localizador.setCodLocalizador(extraccion[4]);
                    if (extraccion.length >= 6)
                        localizador.setCodSeg1(extraccion[5]);
                    if (extraccion.length >= 7)
                        localizador.setCodSeg2(extraccion[6]);
                    if (extraccion.length >= 8)
                        localizador.setCodSeg3(extraccion[7]);
                    if (extraccion.length >= 9)
                        localizador.setCodSeg4(extraccion[8]);
                    if (extraccion.length >= 10)
                        localizador.setCodSeg5(extraccion[9]);
                    if (extraccion.length >= 11)
                        localizador.setCodSeg6(extraccion[10]);
                    localizadorDao.insert(localizador);
                } else if (extraccion[0].equals("4")) {
                    Organizacion organizacion = new Organizacion();
                    organizacion.setIdOrganizacion(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    organizacion.setCode(extraccion[2]);
                    organizacion.setTransferencia(extraccion[3]);
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
        BufferedReader leerArchivo = null;
        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);

            // Borra Registros existentes
            mtlOnhandQuantitiesDao.deleteAll();
            mtlSystemItemsDao.deleteAll();

            // Carga Archivo
            leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|");
                if (extraccion[0].equals("1")) {
                    MtlOnhandQuantities mtlOnhandQuantities = new MtlOnhandQuantities();
                    mtlOnhandQuantities.setInventoryItemId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlOnhandQuantities.setOrganizationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlOnhandQuantities.setPrimaryTransactionQuantity(extraccion[3].equalsIgnoreCase("") ? null : Double.valueOf(extraccion[3].replace(",", ".")));
                    if (extraccion.length >= 5)
                        mtlOnhandQuantities.setSubinventoryCode(extraccion[4]);
                    if (extraccion.length >= 6)
                            mtlOnhandQuantities.setLocatorId(extraccion[5].equalsIgnoreCase("") ? null : new Long(extraccion[5]));
                    if (extraccion.length >= 7)
                            mtlOnhandQuantities.setLotNumber(extraccion[6]);
                    if (extraccion.length >= 8)
                        mtlOnhandQuantities.setSerialNumber(extraccion[7]);
                    if (extraccion.length >= 9)
                        mtlOnhandQuantities.setUserId(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    if (extraccion.length >= 10)
                        mtlOnhandQuantities.setStatusId(new Long(extraccion[9]));
                    mtlOnhandQuantitiesDao.insert(mtlOnhandQuantities);
                } else if (extraccion[0].equals("2")) {
                    MtlSystemItems mtlSystemItems = new MtlSystemItems();
                    mtlSystemItems.setInventoryItemId(extraccion[1].equalsIgnoreCase("") ? null : new Long(extraccion[1]));
                    mtlSystemItems.setDescription(extraccion[2]);
                    if (extraccion.length >= 4)
                        mtlSystemItems.setLongDescription(extraccion[3]);
                    if (extraccion.length >= 5)
                        mtlSystemItems.setSegment1(extraccion[4]);
                    if (extraccion.length >= 6)
                       mtlSystemItems.setPrimaryUomCode(extraccion[5]);
                    if (extraccion.length >= 7)
                        mtlSystemItems.setLotControlCode(extraccion[6]);
                    if (extraccion.length >= 8)
                        mtlSystemItems.setShelfLifeCode(extraccion[7]);
                    if (extraccion.length >= 9)
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

    @Override
    public void cargarArchivoRecepcion(File archivo) throws ServiceException {
        IPoHeadersAllDao poHeadersAllDao = new PoHeadersAllDaoImpl();
        IPoLinesAllDao poLinesAllDao = new PoLinesAllDaoImpl();
        IPoLineLocationsAllDao poLineLocationsAllDao = new PoLineLocationsAllDaoImpl();
        IPoDistributionsAllDao poDistributionsAllDao = new PoDistributionsAllDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = null;
            linea = leerArchivo.readLine();
            while(linea != null){
                String [] extraccion = linea.split("\\|", -1);
                if (extraccion[0].equals("CAB")) {
                    PoHeadersAll poHeadersAll = new PoHeadersAll();
                    poHeadersAll.setCreatedBy(extraccion[4].equalsIgnoreCase("") ? null : new Long(extraccion[4]));
                    poHeadersAll.setCreationDate(extraccion[5]);
                    poHeadersAll.setVendorName(extraccion[6]);
                    poHeadersAll.setVendorId(extraccion[7].equalsIgnoreCase("") ? null : new Long(extraccion[7]));
                    poHeadersAll.setVendorSiteId(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    poHeadersAll.setVendorSiteCode(extraccion[9]);
                    poHeadersAll.setPoHeaderId(extraccion[10].equalsIgnoreCase("") ? null : new Long(extraccion[10]));
                    poHeadersAll.setSegment1(extraccion[11]);
                    poHeadersAll.setOrgId(extraccion[12].equalsIgnoreCase("") ? null : new Long(extraccion[12]));
                    poHeadersAll.setApprovedDate(extraccion[13]);
                    poHeadersAll.setOperatingUnit(extraccion[14]);
                    poHeadersAll.setTermsId(extraccion[15].equalsIgnoreCase("") ? null : new Long(extraccion[15]));
                    poHeadersAll.setCurrencyCode(extraccion[16]);
                    poHeadersAll.setRateType(extraccion[17]);
                    poHeadersAll.setRateDate(extraccion[18]);
                    poHeadersAll.setRate(extraccion[19].equalsIgnoreCase("") ? null : new Long(extraccion[19]));
                    poHeadersAll.setUserId(extraccion[20].equalsIgnoreCase("") ? null : new Long(extraccion[20]));
                    poHeadersAll.setReceiptNum(extraccion[21].equalsIgnoreCase("") ? null : new Long(extraccion[21]));
                    poHeadersAllDao.insert(poHeadersAll);

                } else if (extraccion[0].equals("LIN")) {
                    PoLinesAll poLinesAll = new PoLinesAll();
                    poLinesAll.setPoLineId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    poLinesAll.setPoHeaderId(extraccion[3].equalsIgnoreCase("") ? null : new Long(extraccion[3]));
                    poLinesAll.setLineNum(extraccion[4]);
                    poLinesAll.setItemId(extraccion[5].equalsIgnoreCase("") ? null : new Long(extraccion[5]));
                    poLinesAll.setItemDescripcion(extraccion[6]);
                    poLinesAll.setUnitPrice(extraccion[7].equalsIgnoreCase("") ? null : new Long(extraccion[7]));
                    poLinesAll.setQuantity(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    poLinesAll.setUnitMeasLookupCode(extraccion[9]);
                    poLinesAll.setBaseUom(extraccion[10]);
                    poLinesAllDao.insert(poLinesAll);
                } else if (extraccion[0].equals("ENV")) {
                    PoLineLocationsAll poLineLocationsAll = new PoLineLocationsAll();
                    poLineLocationsAll.setLineLocationId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    poLineLocationsAll.setPoLineId(extraccion[3].equalsIgnoreCase("") ? null : new Long(extraccion[3]));
                    poLineLocationsAll.setPoHeaderId(extraccion[4].equalsIgnoreCase("") ? null : new Long(extraccion[4]));
                    poLineLocationsAll.setQuantity(extraccion[5].equalsIgnoreCase("") ? null : new Long(extraccion[5]));
                    poLineLocationsAll.setQuantityRecived(extraccion[6].equalsIgnoreCase("") ? null : new Long(extraccion[6]));
                    poLineLocationsAll.setQuantityCancelled(extraccion[7].equalsIgnoreCase("") ? null : new Long(extraccion[7]));
                    poLineLocationsAll.setQuantityBilled(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    poLineLocationsAll.setShipmentNum(extraccion[9].equalsIgnoreCase("") ? null : new Long(extraccion[9]));
                    poLineLocationsAll.setShipToLocationId(extraccion[10].equalsIgnoreCase("") ? null : new Long(extraccion[10]));
                    poLineLocationsAll.setQtyRcvTolerance(extraccion[11].equalsIgnoreCase("") ? null : new Long(extraccion[11]));
                    poLineLocationsAll.setOrgId(extraccion[12].equalsIgnoreCase("") ? null : new Long(extraccion[12]));
                    poLineLocationsAllDao.insert(poLineLocationsAll);
                } else if (extraccion[0].equals("DIS")) {
                    PoDistributionsAll poDistributionsAll = new PoDistributionsAll();
                    poDistributionsAll.setPoDistributionId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    poDistributionsAll.setLineLocationId(extraccion[3].equalsIgnoreCase("") ? null : new Long(extraccion[3]));
                    poDistributionsAll.setPoLineId(extraccion[4].equalsIgnoreCase("") ? null : new Long(extraccion[4]));
                    poDistributionsAll.setPoHeaderId(extraccion[5].equalsIgnoreCase("") ? null : new Long(extraccion[5]));
                    poDistributionsAll.setDistributionNum(extraccion[6].equalsIgnoreCase("") ? null : new Long(extraccion[6]));
                    poDistributionsAll.setRateDate(extraccion[7]);
                    poDistributionsAll.setRate(extraccion[8].equalsIgnoreCase("") ? null : new Long(extraccion[8]));
                    poDistributionsAll.setDestinationSubinventory(extraccion[9]);
                    poDistributionsAll.setDeliverToLocationId(extraccion[10].equalsIgnoreCase("") ? null : new Long(extraccion[10]));
                    poDistributionsAll.setQuantityOrdered(extraccion[11].equalsIgnoreCase("") ? null : new Long(extraccion[11]));
                    poDistributionsAll.setQuantityBilled(extraccion[13].equalsIgnoreCase("") ? null : new Long(extraccion[13]));
                    poDistributionsAll.setQuantityCancelled(extraccion[14].equalsIgnoreCase("") ? null : new Long(extraccion[14]));
                    poDistributionsAllDao.insert(poDistributionsAll);
                } else if (extraccion[0].equals("ITM")) {
                    MtlSystemItems mtlSystemItems = new MtlSystemItems();
                    mtlSystemItems.setInventoryItemId(extraccion[2].equalsIgnoreCase("") ? null : new Long(extraccion[2]));
                    mtlSystemItems.setDescription(extraccion[3]);
                    mtlSystemItems.setLongDescription(extraccion[4]);
                    mtlSystemItems.setSegment1(extraccion[5]);
                    mtlSystemItems.setPrimaryUomCode(extraccion[6]);
                    mtlSystemItems.setLotControlCode(extraccion[7]);
                    mtlSystemItems.setShelfLifeCode(extraccion[8]);
                    mtlSystemItems.setSerialNumberControlCode(extraccion[9]);
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
    public void cargarArchivoEntrega(File archivo) throws ServiceException {
        IRcvShipmentHeadersDao rcvShipmentHeadersDao = new RcvShipmentHeadersDaoImpl();
        IRcvTransactionsDao rcvTransactionsDao = new RcvTransactionsDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();

        try {
            Long shipmentHeaderId = 0L;
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            while(linea != null) {
                String[] extraccion = linea.split("\\|", -1);
                if (extraccion[0].equals("SHP")) {
                    shipmentHeaderId = Long.valueOf(extraccion[1]);
                    RcvShipmentHeaders rcvShipmentHeaders = rcvShipmentHeadersDao.get(Long.valueOf(extraccion[1]));
                    if (rcvShipmentHeaders != null) {
                        rcvShipmentHeadersDao.delete(Long.valueOf(extraccion[1]));
                        rcvTransactionsDao.deleteByShipmenHeader(Long.valueOf(extraccion[1]));
                    } else {
                        rcvShipmentHeaders = new RcvShipmentHeaders();
                    }

                    if (extraccion.length >= 2)
                        rcvShipmentHeaders.setShipmentHeaderId(extraccion[1].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[1]));
                    if (extraccion.length >= 3)
                        rcvShipmentHeaders.setLastUpdateDate(extraccion[2]);
                    if (extraccion.length >= 4)
                        rcvShipmentHeaders.setLastUpdatedBy(extraccion[3].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[3]));
                    if (extraccion.length >= 5)
                        rcvShipmentHeaders.setCreationDate(extraccion[4]);
                    if (extraccion.length >= 6)
                        rcvShipmentHeaders.setCreatedBy(extraccion[5].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[5]));
                    if (extraccion.length >= 7)
                        rcvShipmentHeaders.setUserId(extraccion[6].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[6]));
                    if (extraccion.length >= 8)
                        rcvShipmentHeaders.setVendorId(extraccion[7].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[7]));
                    if (extraccion.length >= 9)
                        rcvShipmentHeaders.setVendorSiteId(extraccion[8].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[8]));
                    if (extraccion.length >= 10)
                        rcvShipmentHeaders.setOrganizationId(extraccion[9].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[9]));
                    if (extraccion.length >= 11)
                        rcvShipmentHeaders.setShipmentNum(extraccion[10]);
                    if (extraccion.length >= 12)
                        rcvShipmentHeaders.setReceiptNum(extraccion[11]);
                    if (extraccion.length >= 13)
                        rcvShipmentHeaders.setEmployeeId(extraccion[12].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[12]));
                    if (extraccion.length >= 14)
                        rcvShipmentHeaders.setShipToOrgId(extraccion[13].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[13]));
                    if (extraccion.length >= 15)
                        rcvShipmentHeaders.setPoNumber(extraccion[14].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[14]));
                    rcvShipmentHeadersDao.insert(rcvShipmentHeaders);
                } else if (extraccion[0].equals("TRX")) {
                    RcvTransactions rcvTransactions = new RcvTransactions();
                    if (extraccion.length >= 2)
                        rcvTransactions.setTransactionId(extraccion[1].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[1]));
                    if (extraccion.length >= 3)
                        rcvTransactions.setLastUpdateDate(extraccion[2]);
                    if (extraccion.length >= 4)
                        rcvTransactions.setLastUpdatedBy(extraccion[3].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[3]));
                    if (extraccion.length >= 5)
                        rcvTransactions.setCreationDate(extraccion[4]);
                    if (extraccion.length >= 6)
                        rcvTransactions.setCreatedBy(extraccion[5].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[5]));
                    if (extraccion.length >= 7)
                        rcvTransactions.setTransactionType(extraccion[6]);
                    if (extraccion.length >= 8)
                        rcvTransactions.setTransactionDate(extraccion[7]);
                    if (extraccion.length >= 9)
                        rcvTransactions.setQuantity(extraccion[8].equalsIgnoreCase("") ? null : Double.valueOf(extraccion[8]));
                    if (extraccion.length >= 10)
                        rcvTransactions.setUnitOfMeasure(extraccion[9]);
                    if (extraccion.length >= 11)
                        rcvTransactions.setShipmentHeaderId(extraccion[10].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[10]));
                    if (extraccion.length >= 12)
                        rcvTransactions.setShipmentLineId(extraccion[11].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[11]));
                    if (extraccion.length >= 13)
                        rcvTransactions.setSourceDocumentCode(extraccion[12]);
                    if (extraccion.length >= 14)
                        rcvTransactions.setDestinationTypeCode(extraccion[13]);
                    if (extraccion.length >= 15)
                        rcvTransactions.setUnitOfMeasure(extraccion[14]);
                    if (extraccion.length >= 16)
                        rcvTransactions.setUomCode(extraccion[15]);
                    if (extraccion.length >= 17)
                        rcvTransactions.setEmployeeId(extraccion[16].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[16]));
                    if (extraccion.length >= 18)
                        rcvTransactions.setPoHeaderId(extraccion[17].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[17]));
                    if (extraccion.length >= 19)
                        rcvTransactions.setPoLineId(extraccion[18].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[18]));
                    if (extraccion.length >= 20)
                        rcvTransactions.setPoLineLocationId(extraccion[19].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[19]));
                    if (extraccion.length >= 21)
                        rcvTransactions.setPoDistributionId(extraccion[20].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[20]));
                    if (extraccion.length >= 22)
                        rcvTransactions.setPoUnitPrice(extraccion[21].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[21]));
                    if (extraccion.length >= 23)
                        rcvTransactions.setCurrencyCode(extraccion[22]);
                    if (extraccion.length >= 24)
                        rcvTransactions.setCurrencyConversionType(extraccion[23]);
                    if (extraccion.length >= 25)
                        rcvTransactions.setCurrencyConversionRate(extraccion[24].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[24]));
                    if (extraccion.length >= 26)
                        rcvTransactions.setCurrencyConversionDate(extraccion[25]);
                    if (extraccion.length >= 27)
                        rcvTransactions.setDeliverToLocationId(extraccion[26].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[26]));
                    if (extraccion.length >= 28)
                        rcvTransactions.setVendorId(extraccion[27].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[27]));
                    if (extraccion.length >= 29)
                        rcvTransactions.setVendorSiteId(extraccion[28].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[28]));
                    if (extraccion.length >= 30)
                        rcvTransactions.setOrganizationId(extraccion[29].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[29]));
                    if (extraccion.length >= 31)
                        rcvTransactions.setLocationId(extraccion[30].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[30]));
                    if (extraccion.length >= 32)
                        rcvTransactions.setInspectionStatusCode(extraccion[31]);
                    if (extraccion.length >= 33)
                        rcvTransactions.setDestinationContext(extraccion[32]);
                    if (extraccion.length >= 34)
                        rcvTransactions.setInterfaceTransactionId(extraccion[33].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[33]));
                    if (extraccion.length >= 35)
                        rcvTransactions.setItemId(extraccion[34].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[34]));
                    if (extraccion.length >= 36)
                        rcvTransactions.setLineNum(extraccion[35].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[35]));
                    rcvTransactionsDao.insert(rcvTransactions);
                } else if (extraccion[0].equals("ITM")) {
                    boolean existe = true;
                    MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(Long.valueOf(extraccion[1]));
                    if (mtlSystemItems == null) {
                        mtlSystemItems = new MtlSystemItems();
                        existe = false;
                    }
                    mtlSystemItems.setInventoryItemId(extraccion[1].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[1]));
                    if (extraccion.length >= 3)
                        mtlSystemItems.setDescription(extraccion[2]);
                    if (extraccion.length >= 4)
                        mtlSystemItems.setLongDescription(extraccion[3]);
                    if (extraccion.length >= 5)
                        mtlSystemItems.setSegment1(extraccion[4]);
                    if (extraccion.length >= 6)
                        mtlSystemItems.setPrimaryUomCode(extraccion[5]);
                    if (extraccion.length >= 7)
                        mtlSystemItems.setLotControlCode(extraccion[6]);
                    if (extraccion.length >= 8)
                        mtlSystemItems.setShelfLifeCode(extraccion[7]);
                    if (extraccion.length >= 9)
                        mtlSystemItems.setSerialNumberControlCode(extraccion[8]);
                    if (existe) {
                        mtlSystemItemsDao.update(mtlSystemItems);
                    } else {
                        mtlSystemItemsDao.insert(mtlSystemItems);
                    }
                } else if (extraccion[0].equals("IDI")) {
                    RcvShipmentHeaders rcvShipmentHeaders = rcvShipmentHeadersDao.get(shipmentHeaderId);
                    if (rcvShipmentHeaders != null) {
                        rcvShipmentHeaders.setHeaderInterfaceId(Long.valueOf(extraccion[1]));
                        rcvShipmentHeaders.setInterfaceTransactionId(Long.valueOf(extraccion[2]));
                        rcvShipmentHeaders.setGroupId(Long.valueOf(extraccion[3]));
                        rcvShipmentHeaders.setTransactionInterfaceId(Long.valueOf(extraccion[4]));
                        rcvShipmentHeadersDao.update(rcvShipmentHeaders);
                    }
                }
                linea = leerArchivo.readLine();
            }
            archivo.delete();
        } catch (DaoException e) {
            Log.d(TAG, "DaoException");
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        } catch (FileNotFoundException e) {
            Log.d(TAG, "FileNotFoundException");
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "IOException");
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        } catch (Exception e) {
            Log.d(TAG, "Exception");
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public void cargarArchivoEntregaOrgs(File archivo) throws ServiceException {
        IMtlMaterialTransactionsDao mtlMaterialTransactionsDao = new MtlMaterialTransactionsDaoImpl();
        IMtlTransactionLotNumbersDao mtlTransactionLotNumbersDao = new MtlTransactionLotNumbersDaoImpl();
        IMtlSerialNumbersDao mtlSerialNumbersDao = new MtlSerialNumbersDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();

        try {
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader abrirArchivo = new InputStreamReader(fis);
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            Long shipmentHeaderId = null;
            while(linea != null) {
                String[] extraccion = linea.split("\\|", -1);
                if (extraccion[0].equals("1")) {
                    MtlMaterialTransactions mtlMaterialTransactions = new MtlMaterialTransactions();
                    if (extraccion.length >= 2)
                        mtlMaterialTransactions.setTransactionId(extraccion[1].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[1]));
                    if (extraccion.length >= 3)
                        mtlMaterialTransactions.setInventoryItemId(extraccion[2].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[2]));
                    if (extraccion.length >= 4)
                        mtlMaterialTransactions.setOrganizationId(extraccion[3].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[3]));
                    if (extraccion.length >= 5)
                        mtlMaterialTransactions.setTransactionTypeId(extraccion[4].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[4]));
                    if (extraccion.length >= 6)
                        mtlMaterialTransactions.setTransactionActionId(extraccion[5].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[5]));
                    if (extraccion.length >= 7)
                        mtlMaterialTransactions.setTransactionSourceTypeId(extraccion[6].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[6]));
                    if (extraccion.length >= 8)
                        mtlMaterialTransactions.setTransactionSourceName(extraccion[7]);
                    if (extraccion.length >= 9)
                        mtlMaterialTransactions.setTransactionQuantity(extraccion[8].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[8]));
                    if (extraccion.length >= 10)
                        mtlMaterialTransactions.setTransactionUom(extraccion[9]);
                    if (extraccion.length >= 11)
                        mtlMaterialTransactions.setPrimaryQuantity(extraccion[10].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[10]));
                    if (extraccion.length >= 12)
                        mtlMaterialTransactions.setTransactionDate(extraccion[11]);
                    if (extraccion.length >= 13)
                        mtlMaterialTransactions.setActualCost(extraccion[12].equalsIgnoreCase("") ? null : Double.valueOf(extraccion[12].replace(",", ".")));
                    if (extraccion.length >= 14)
                        mtlMaterialTransactions.setTransferOrganizationId(extraccion[13].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[13]));
                    if (extraccion.length >= 15)
                        mtlMaterialTransactions.setShipToLocationId(extraccion[14].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[14]));
                    if (extraccion.length >= 16)
                        mtlMaterialTransactions.setReceipNum(extraccion[15]);
                    if (extraccion.length >= 17)
                        mtlMaterialTransactions.setUserId(extraccion[16].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[16]));
                    if (extraccion.length >= 18)
                        mtlMaterialTransactions.setShipmentNumber(extraccion[17]);
                    if (extraccion.length >= 19)
                        mtlMaterialTransactions.setShipmentHeaderId(extraccion[18].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[18]));
                    if (extraccion.length >= 20) {
                        shipmentHeaderId = Long.valueOf(extraccion[19]);
                        mtlMaterialTransactions.setShipmentLineId(extraccion[19].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[19]));
                    }
                    mtlMaterialTransactionsDao.insert(mtlMaterialTransactions);
                } else if (extraccion[0].equals("2")) {
                    MtlTransactionLotNumbers mtlTransactionLotNumbers = new MtlTransactionLotNumbers();
                    if (extraccion.length >= 2)
                        mtlTransactionLotNumbers.setTransactionId(extraccion[1].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[1]));
                    if (extraccion.length >= 3)
                        mtlTransactionLotNumbers.setInventoryItemId(extraccion[2].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[2]));
                    if (extraccion.length >= 4)
                        mtlTransactionLotNumbers.setOrganizationId(extraccion[3].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[3]));
                    if (extraccion.length >= 5)
                        mtlTransactionLotNumbers.setLotNumber(extraccion[4]);
                    if (extraccion.length >= 6)
                        mtlTransactionLotNumbers.setSerialTransactionId(extraccion[5].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[5]));
                    if (extraccion.length >= 7)
                        mtlTransactionLotNumbers.setLotAttributeCategory(extraccion[6]);
                    if (extraccion.length >= 8)
                        mtlTransactionLotNumbers.setcAttribute1(extraccion[7]);
                    if (extraccion.length >= 9)
                        mtlTransactionLotNumbers.setcAttribute2(extraccion[8]);
                    if (extraccion.length >= 10)
                        mtlTransactionLotNumbers.setcAttribute3(extraccion[9]);
                    mtlTransactionLotNumbers.setShipmentHeaderId(shipmentHeaderId);
                    mtlTransactionLotNumbersDao.insert(mtlTransactionLotNumbers);
                } else if (extraccion[0].equals("3")) {
                    MtlSerialNumbers mtlSerialNumbers = new MtlSerialNumbers();
                    if (extraccion.length >= 2)
                        mtlSerialNumbers.setInventoryItemId(extraccion[1].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[1]));
                    if (extraccion.length >= 3)
                        mtlSerialNumbers.setSerialNumber(extraccion[2]);
                    if (extraccion.length >= 4)
                        mtlSerialNumbers.setLastUpdateDate(extraccion[3]);
                    if (extraccion.length >= 5)
                        mtlSerialNumbers.setLastUpdateBy(extraccion[4].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[4]));
                    if (extraccion.length >= 6)
                        mtlSerialNumbers.setCreationDate(extraccion[5]);
                    if (extraccion.length >= 7)
                        mtlSerialNumbers.setCreatedBy(extraccion[6].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[6]));
                    if (extraccion.length >= 8)
                        mtlSerialNumbers.setLotNumber(extraccion[7]);
                    if (extraccion.length >= 9)
                        mtlSerialNumbers.setCurrentOrganizationId(extraccion[8].equalsIgnoreCase("") ? null : Long.valueOf(extraccion[8]));
                    mtlSerialNumbersDao.insert(mtlSerialNumbers);
                } else if (extraccion[0].equals("4")) {
                    boolean existe = true;
                    Long inventoryItemId = Long.valueOf(extraccion[1]);
                    MtlSystemItems mtlSystemItems = mtlSystemItemsDao.get(inventoryItemId);
                    if (mtlSystemItems == null) {
                        existe = false;
                        mtlSystemItems = new MtlSystemItems();
                    }
                    mtlSystemItems.setInventoryItemId(inventoryItemId);
                    if (extraccion.length >= 3)
                        mtlSystemItems.setDescription(extraccion[2]);
                    if (extraccion.length >= 4)
                        mtlSystemItems.setLongDescription(extraccion[3]);
                    if (extraccion.length >= 5)
                        mtlSystemItems.setSegment1(extraccion[4]);
                    if (extraccion.length >= 6)
                       mtlSystemItems.setPrimaryUomCode(extraccion[5]);
                    if (extraccion.length >= 7)
                        mtlSystemItems.setLotControlCode(extraccion[6]);
                    if (extraccion.length >= 8)
                    mtlSystemItems.setShelfLifeCode(extraccion[7]);
                    if (extraccion.length >= 9)
                        mtlSystemItems.setSerialNumberControlCode(extraccion[8]);

                    if (existe) {
                        mtlSystemItemsDao.update(mtlSystemItems);
                    } else {
                        mtlSystemItemsDao.insert(mtlSystemItems);
                    }
                }
                linea = leerArchivo.readLine();
            }
            archivo.delete();
        } catch (DaoException e) {
            Log.d(TAG, "DaoException");
            e.printStackTrace();
            throw new ServiceException(2, e.getDescripcion());
        } catch (FileNotFoundException e) {
            Log.d(TAG, "FileNotFoundException");
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "IOException");
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        } catch (Exception e) {
            Log.d(TAG, "Exception");
            e.printStackTrace();
            throw new ServiceException(2, e.getMessage());
        }
    }
}
