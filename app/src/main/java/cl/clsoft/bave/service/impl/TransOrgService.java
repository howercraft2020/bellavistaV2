package cl.clsoft.bave.service.impl;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.dao.IDatosTransOrgDao;
import cl.clsoft.bave.dao.IDatosTransOrgDetalleDao;
import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.IMtlSerialNumbersInterfaceDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IMtlTransactionLotsInterfaceDao;
import cl.clsoft.bave.dao.IMtlTransactionsInterfaceDao;
import cl.clsoft.bave.dao.IOrganizacionDao;
import cl.clsoft.bave.dao.IOrganizacionPrincipalDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.DatosTransOrgDetalleImpl;
import cl.clsoft.bave.dao.impl.DatosTransOrgImpl;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlOnhandQuantitiesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSerialNumbersInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotsIfaceDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionPrincipalDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.dto.MtlTransactionDetalleDto;
import cl.clsoft.bave.dto.MtlTransactionOrgDto;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransOrg;
import cl.clsoft.bave.model.DatosTransOrgDetalle;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.ITransOrgService;

public class TransOrgService  implements ITransOrgService {

    private static final String TAG = "TransOrgService";

    @Override
    public List<DatosTransOrg> getTransSubinv() throws ServiceException {
        IDatosTransOrgDao datosTransOrgDao = new DatosTransOrgImpl();
        try {
            return datosTransOrgDao.getTransSubinv();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public List<DatosTransOrgDetalle> getTransferencias(String numeroTraspaso) throws ServiceException {
        IDatosTransOrgDetalleDao datosTransOrgDetalleDao = new DatosTransOrgDetalleImpl();
        try{
            return datosTransOrgDetalleDao.getTransferencias(numeroTraspaso);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public List<Organizacion> getOrganizacionesHacia() throws ServiceException {
        IOrganizacionDao organizacionDao = new OrganizacionDaoImpl();
        try {
                return organizacionDao.getAllDestino();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "TransOrgService::getLocalizadoresBySubinventario");
        Log.d(TAG, "TransOrgService::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

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
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "TransOrgService::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            return mtlSystemItemsDao.getBySegment(segment);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<Subinventario> getSubinventarios() throws ServiceException {
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try{
            return subinventarioDao.getAll();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public List<MtlOnhandQuantities> getSeries(String sigle, String lote, String subinventario, String localizador) throws ServiceException {
        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        try{
            return mtlOnhandQuantitiesDao.getAll(sigle, lote, subinventario, localizador);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public MtlOnhandQuantities getSerieIngresada(String sigle, String lote, String subinventario, String localizador, String serie) throws ServiceException {
        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();

        try {
            return mtlOnhandQuantitiesDao.validaSerie(sigle,lote,subinventario,localizador, serie);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Double cantidad, String glosa, List<String> series, String orgDestino) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        IOrganizacionDao organizacionDao = new OrganizacionDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        IOrganizacionPrincipalDao organizacionPrincipalDao = new OrganizacionPrincipalDaoImpl();

        Long inventoryItemId;
        Long existe = 0L;
        String transactionInterfaceId;

        String fechaId = "";
        String sigleUomCode = "";
        boolean isControlLote = false;
        boolean isControlSerie = false;
        Localizador localizadorDesde = null;

        try{

            transactionInterfaceId = id;
            String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

            if(nroTraspaso.equals("")) {
                fechaId = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());
            }
            else {
                fechaId = nroTraspaso;
            }
            //Validación de Articulo
            MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(articulo);
            inventoryItemId = sigle.getInventoryItemId();
            sigleUomCode = sigle.getPrimaryUomCode();

            if (inventoryItemId == null){
                throw new ServiceException(1,"No se encuentra información para el articulo : " + articulo);
            }

            if (sigle.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (sigle.getSerialNumberControlCode().equalsIgnoreCase("2") || sigle.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
            }

            //Valida si existe en interfaz
            existe = mtlTransactionsInterfaceDao.getLocNull(inventoryItemId,subinventario,localizador);

            if (existe != 0){
                throw new ServiceException(1,articulo + "ya esta ingresado en interfaz");
            }

            // Subinventory Desde
            Subinventario subinventarioDesde = subinventarioDao.getByCodigo(subinventario);
            if (subinventarioDesde == null) {
                throw new ServiceException(1, "Subinventario " + subinventario + " no existe en el sistema");
            }

            // Locator
            if (localizador != null) {
                localizadorDesde = localizadorDao.getByCodigo(localizador);
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + localizador + " no existe en el sistema");
                }
            }

            //Datos de mtlOnHandQuantities
            MtlOnhandQuantities mtlOnhandQuantities = mtlOnhandQuantitiesDao.get(articulo,lote, subinventario, localizador);

            if(mtlOnhandQuantities == null){
                throw new ServiceException(1,"No se encuentra información en mtlonhandquantities");
            }

            //Organizacion Destino
            Organizacion organizacionDestino = organizacionDao.getByCodeDestino(orgDestino);
            if (organizacionDestino == null){
                throw new ServiceException(1, "Organizacion Destino " + orgDestino + " no existe en el sistema");
            }

            //Validaciones
            if (isControlLote && lote == null) {
                throw new ServiceException(1, "Debe indicar el lote");
            }

            if (isControlSerie && series == null) {
                throw new ServiceException(1, "Debe indicar las series");
            }

            if (isControlSerie && series.size() == 0) {
                throw new ServiceException(1, "Debe indicar las series");
            }

            if (isControlSerie && series.size() < cantidad.intValue()) {
                throw new ServiceException(1, "Faltan series");
            }

            //Datos organizacion Principal
            OrganizacionPrincipal organizacionPrincipal = organizacionPrincipalDao.get();

            //Inserta datos en mtlTransactionsInterface
            MtlTransactionsInterface mtlTransactionsInterface = new MtlTransactionsInterface();
            mtlTransactionsInterface.setTransactionInterfaceId(Long.parseLong(transactionInterfaceId));
            mtlTransactionsInterface.setProcessFlag(1L);
            mtlTransactionsInterface.setTransactionMode(2L);
            mtlTransactionsInterface.setLastUpdateDate(fecha);
            mtlTransactionsInterface.setCreatedBy(mtlOnhandQuantities.getUserId());
            mtlTransactionsInterface.setInventoryItemId(inventoryItemId);
            mtlTransactionsInterface.setOrganizationId(mtlOnhandQuantities.getOrganizationId());
            mtlTransactionsInterface.setTransactionQuantity(cantidad);
            mtlTransactionsInterface.setPrimaryQuantity(cantidad);
            mtlTransactionsInterface.setTransactionUom(sigleUomCode);
            mtlTransactionsInterface.setTransactionDate(fecha);
            mtlTransactionsInterface.setSubinventoryCode(subinventarioDesde.getCodSubinventario());
            mtlTransactionsInterface.setLocatorId(localizadorDesde.getIdLocalizador());
            mtlTransactionsInterface.setTransactionSourceName(glosa);
            mtlTransactionsInterface.setTransactionSourceTypeId(13L);
            mtlTransactionsInterface.setTransactionActionId(21L);
            mtlTransactionsInterface.setTransactionTypeId(21L);
            mtlTransactionsInterface.setTransferOrganization(organizacionDestino.getIdOrganizacion());
            mtlTransactionsInterface.setShipmentNumber(organizacionPrincipal.getCode()+fechaId);
            mtlTransactionsInterfaceDao.insert(mtlTransactionsInterface);

            if(isControlLote) {
                //Inserta datos en tabla de lotes
                MtlTransactionsLotsIface mtlTransactionsLotsIface = new MtlTransactionsLotsIface();
                mtlTransactionsLotsIface.setTransactionInterfaceId(Long.parseLong(transactionInterfaceId));
                mtlTransactionsLotsIface.setLastUpdateDate(fecha);
                mtlTransactionsLotsIface.setLastUpdateBy(mtlOnhandQuantities.getUserId());
                mtlTransactionsLotsIface.setCreationDate(fecha);
                mtlTransactionsLotsIface.setCreatedBy(mtlOnhandQuantities.getUserId());
                mtlTransactionsLotsIface.setInventoryItemId(inventoryItemId);
                mtlTransactionsLotsIface.setLastUpdateLogin(mtlOnhandQuantities.getUserId());
                mtlTransactionsLotsIface.setLotNumber(lote);
                mtlTransactionsLotsIface.setTransactionQuantity(cantidad);
                mtlTransactionsLotsIface.setPrimaryQuantity(cantidad);
                mtlTransactionsLotsIface.setSerialTransactionTempId(Long.parseLong(transactionInterfaceId));
                mtlTransactionLotsInterfaceDao.insert(mtlTransactionsLotsIface);
            }

            if(isControlSerie) {

                for (String serie : series) {
                    MtlSerialNumbersInterface mtlSerialNumbersInterface = new MtlSerialNumbersInterface();
                    mtlSerialNumbersInterface.setTransactionInterfaceId(Long.parseLong(transactionInterfaceId));
                    mtlSerialNumbersInterface.setLastUpdateDate(fecha);
                    mtlSerialNumbersInterface.setLastUpdatedBy(mtlOnhandQuantities.getUserId());
                    mtlSerialNumbersInterface.setCreationDate(fecha);
                    mtlSerialNumbersInterface.setCreatedBy(mtlOnhandQuantities.getUserId());
                    mtlSerialNumbersInterface.setFmSerialNumber(serie);
                    mtlSerialNumbersInterface.setProductTransactionId(Long.parseLong(transactionInterfaceId));
                    mtlSerialNumbersInterfaceDao.insert(mtlSerialNumbersInterface);
                }
            }

        }catch (ServiceException e){
            throw e;
        }catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }catch (Exception e){
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public void validaTransferencia(String articulo, String lote, String subinventario, String localizador, Double cantidad, String orgDestino, List<String> series) throws ServiceException {

        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IOrganizacionDao organizacionDao = new OrganizacionDaoImpl();

        Double cantidadEnMano;
        String controlLote;
        Localizador localizadorDesde = null;
        boolean isControlLote = false;
        boolean isControlSerie = false;

        try{


            //Validación de lote
            MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(articulo);
            controlLote = sigle.getLotControlCode();

            if (sigle == null){
                throw new ServiceException(1,"No se encuentra información para el articulo : " + articulo);
            }

            if (sigle.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (sigle.getSerialNumberControlCode().equalsIgnoreCase("2") || sigle.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
            }

            if (controlLote.equals("2") && lote.equals("")){
                throw new ServiceException(1,"Debe ingresar un lote para articulo : " + articulo);
            }

            if (controlLote.equals("1") && !lote.equals("")){
                throw new ServiceException(1,"Articulo : " + articulo + " no tiene control de lote.");
            }

            //Validación datos en mtlonhandquantities
            MtlOnhandQuantities cantidades = mtlOnhandQuantitiesDao.get(articulo,lote, subinventario, localizador);
            if(cantidades == null){
                throw new ServiceException(1,"No se encuentra información de cantidad de stock");
            }

            //Organizacion Destino
            Organizacion organizacionDestino = organizacionDao.getByCodeDestino(orgDestino);
            if (organizacionDestino == null){
                throw new ServiceException(1, "Organizacion Destino " + orgDestino + " no existe en el sistema");
            }

            // Subinventory Desde
            Subinventario subinventarioDesde = subinventarioDao.getByCodigo(subinventario);
            if (subinventarioDesde == null) {
                throw new ServiceException(1, "Subinventario " + subinventario + " no existe en el sistema");
            }

            // Locator
            if (localizador != null) {
                localizadorDesde = localizadorDao.getByCodigo(localizador);
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + localizador + " no existe en el sistema");
                }
            }

            //Validación Cantidad en Mano
            cantidadEnMano = cantidades.getPrimaryTransactionQuantity();

            if (cantidadEnMano < cantidad) {
                throw new ServiceException(1,"Cantidad no puede ser mayor a : " + cantidadEnMano);
            }

            //Validaciones
            if (isControlLote && lote == null) {
                throw new ServiceException(1, "Debe indicar el lote");
            }

            if (isControlSerie && series == null) {
                throw new ServiceException(1, "Debe indicar las series");
            }

            if (isControlSerie && series.size() == 0) {
                throw new ServiceException(1, "Debe indicar las series");
            }

            if (isControlSerie && series.size() < cantidad.intValue()) {
                throw new ServiceException(1, "Faltan series");
            }


        }catch (ServiceException e){
            throw e;
        }catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }catch (Exception e){
            throw new ServiceException(2, e.getMessage());
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
    public void deleteTransactionsInterfaceById(Long transactionInterfaceId) throws ServiceException {
        Log.d(TAG, "TransOrgService::deleteTransactionsInterfaceById");
        Log.d(TAG, "TransOrgService::deleteTransactionsInterfaceById::transactionInterfaceId: " + transactionInterfaceId);

        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();

        try {
            mtlTransactionsInterfaceDao.delete(transactionInterfaceId);
            mtlTransactionLotsInterfaceDao.delete(transactionInterfaceId);
            mtlSerialNumbersInterfaceDao.delete(transactionInterfaceId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public MtlTransactionOrgDto getTransactionsInterfaceById(Long transactionInterfaceId) throws ServiceException {
        Log.d(TAG, "TransSubvinvService::getTransactionsInterfaceById");
        Log.d(TAG, "TransSubvinvService::getTransactionsInterfaceById::interfaceTransactionId: " + transactionInterfaceId);
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IOrganizacionDao organizacionDao = new OrganizacionDaoImpl();



        try{
            Localizador localizador = null;
            boolean isControlLote = false;
            boolean isControlSerie = false;
            MtlTransactionsLotsIface mtlTransactionsLotsIface = null;
            List<MtlSerialNumbersInterface> serials;

            MtlTransactionOrgDto dto  = new MtlTransactionOrgDto();
            MtlTransactionsInterface mtlTransactionsInterface = mtlTransactionsInterfaceDao.getTransferenciasById(transactionInterfaceId);

            // Item
            MtlSystemItems item = mtlSystemItemsDao.get(mtlTransactionsInterface.getInventoryItemId());
            if (item == null) {
                throw new ServiceException(1, "SystemItem con Id " + mtlTransactionsInterface.getInventoryItemId() + " no existe en el sistema");
            }
            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                isControlLote = true;
            }
            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                isControlSerie = true;
            }

            // Locator
            if (mtlTransactionsInterface.getLocatorId() != null) {
                localizador = localizadorDao.get(mtlTransactionsInterface.getLocatorId());
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + mtlTransactionsInterface.getLocatorId() + " no existe en el sistema");
                }
            }

            //Organizacion Destino
            Organizacion organizacionDestino = organizacionDao.getByiDDestino(mtlTransactionsInterface.getTransferOrganization());
            if (organizacionDestino == null){
                throw new ServiceException(1, "Organizacion Destino " + organizacionDestino + " no existe en el sistema");
            }



            dto.setInventoryItemId(mtlTransactionsInterface.getInventoryItemId());
            dto.setCantidad(mtlTransactionsInterface.getPrimaryQuantity());
            dto.setGlosa(mtlTransactionsInterface.getTransactionSourceName());
            dto.setSubinvDesde(mtlTransactionsInterface.getSubinventoryCode());
            dto.setLocalizador(localizador != null ? localizador.getCodLocalizador() : null);
            dto.setLote(isControlLote);
            dto.setSerie(isControlSerie);
            dto.setOrgDestino(organizacionDestino.getCode());

            if (isControlLote) {
                mtlTransactionsLotsIface = mtlTransactionLotsInterfaceDao.getAll(mtlTransactionsInterface.getTransactionInterfaceId());
                if (mtlTransactionsLotsIface != null) {
                    dto.setLote(mtlTransactionsLotsIface.getLotNumber());
                }
            }

            if (isControlSerie) {
                serials = mtlSerialNumbersInterfaceDao.getAllByInterfaceTransactionId(mtlTransactionsInterface.getTransactionInterfaceId());
                List<String> strSerials = new ArrayList<>();
                for (MtlSerialNumbersInterface serial : serials) {
                    strSerials.add(serial.getFmSerialNumber());
                }
                dto.setSeries(strSerials);
            }
            return dto;


        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void crearArchivo(String transactionReference) throws ServiceException {
        String nomenclatura;
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();

        try{

            List<MtlTransactionsInterface> trxs = mtlTransactionsInterfaceDao.getTransferenciasByShipment(transactionReference);

            if(trxs.size() == 0){
                throw new ServiceException(1,"No se encuentran datos para este numero de traspaso");
            }

            nomenclatura = "I_O_"+transactionReference+".csv";

            File tarjetaSD = Environment.getExternalStorageDirectory();
            File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
            File rutaArchivo = new File(Dir, nomenclatura);

            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write("SUBINV;FIN" +"\r\n");

            for (MtlTransactionsInterface trx : trxs) {
                writer.write("1;"
                        + (trx.getTransactionInterfaceId() == null ? "null" : trx.getTransactionInterfaceId()) + ";"
                        + (trx.getProcessFlag() == null ? "null" : trx.getProcessFlag()) + ";"
                        + (trx.getTransactionMode() == null ? "null" : trx.getTransactionMode()) + ";"
                        + (trx.getLastUpdateDate() == null ? "null" : (trx.getLastUpdateDate().isEmpty() ? "null" : trx.getLastUpdateDate())) + ";"
                        + (trx.getLastUpdatedBy() == null ? "null" : trx.getLastUpdatedBy()) + ";"
                        + (trx.getCreationDate() == null ? "null" : (trx.getCreationDate().isEmpty() ? "null" : trx.getCreationDate())) + ";"
                        + (trx.getCreatedBy() == null ? "null" : trx.getCreatedBy()) + ";"
                        + (trx.getInventoryItemId() == null ? "null" : trx.getInventoryItemId()) + ";"
                        + (trx.getOrganizationId() == null ? "null" : trx.getOrganizationId()) + ";"
                        + (trx.getTransactionQuantity() == null ? "null" : trx.getTransactionQuantity()) + ";"
                        //+ (trx.getPrimaryQuantity() == null ? "null" : trx.getPrimaryQuantity()) + ";"
                        + (trx.getTransactionUom() == null ? "null" : (trx.getTransactionUom().isEmpty() ? "null" : trx.getTransactionUom())) + ";"
                        + (trx.getTransactionDate() == null ? "null" : (trx.getTransactionDate().isEmpty() ? "null" : trx.getTransactionDate())) + ";"
                        + (trx.getSubinventoryCode() == null ? "null" : (trx.getSubinventoryCode().isEmpty() ? "null" : trx.getSubinventoryCode())) + ";"
                        + (trx.getLocatorId() == null ? "null" : trx.getLocatorId()) + ";"
                        + (trx.getTransactionSourceName() == null ? "null" : (trx.getTransactionSourceName().isEmpty() ? "null" : trx.getTransactionSourceName())) + ";"
                        + (trx.getTransactionSourceTypeId() == null ? "null" : trx.getTransactionSourceTypeId()) + ";"
                        + (trx.getTransactionActionId() == null ? "null" : trx.getTransactionActionId()) + ";"
                        + (trx.getTransactionTypeId() == null ? "null" : trx.getTransactionTypeId()) + ";"
                        + (trx.getTransactionReference() == null ? "null" : (trx.getTransactionReference().isEmpty() ? "null" : trx.getTransactionReference())) + ";"
                        + (trx.getTransferOrganization() == null ? "null" : trx.getTransferOrganization()) + ";"
                        + (trx.getShipmentNumber() == null ? "null" : (trx.getShipmentNumber().isEmpty() ? "null" : trx.getShipmentNumber())) + ";"
                        + "FIN\r\n");
            }

            for (MtlTransactionsInterface trx : trxs) {
                MtlTransactionsLotsIface lote = mtlTransactionLotsInterfaceDao.get(trx.getTransactionInterfaceId());
                if (lote != null) {
                    writer.write("2;"
                            + (lote.getTransactionInterfaceId() == null ? "null" : lote.getTransactionInterfaceId()) + ";"
                            + (lote.getLastUpdateDate() == null ? "null" : (lote.getLastUpdateDate().isEmpty() ? "null" : lote.getLastUpdateDate())) + ";"
                            + (lote.getLastUpdateBy() == null ? "null" : lote.getLastUpdateBy()) + ";"
                            + (lote.getCreationDate() == null ? "null" : (lote.getCreationDate().isEmpty() ? "null" : lote.getCreationDate())) + ";"
                            + (lote.getCreatedBy() == null ? "null" : lote.getCreatedBy()) + ";"
                            + (lote.getLotNumber() == null ? "null" : (lote.getLotNumber().isEmpty() ? "null" : lote.getLotNumber())) + ";"
                            + (lote.getLotExpirationDate() == null ? "null" : (lote.getLotExpirationDate().isEmpty() ? "null" : lote.getLotExpirationDate())) + ";"
                            + (lote.getTransactionQuantity() == null ? "null" : lote.getTransactionQuantity()) + ";"
                            + (lote.getPrimaryQuantity() == null ? "null" : lote.getPrimaryQuantity()) + ";"
                            + (lote.getSerialTransactionTempId() == null ? "null" : lote.getSerialTransactionTempId()) + ";"
                            + (lote.getAttributeCategory() == null ? "null" : (lote.getAttributeCategory().isEmpty() ? "null" : lote.getAttributeCategory())) + ";"
                            + (lote.getAttrubute1() == null ? "null" : (lote.getAttrubute1().isEmpty() ? "null" : lote.getAttrubute1())) + ";"
                            + (lote.getAttrubute2() == null ? "null" : (lote.getAttrubute2().isEmpty() ? "null" : lote.getAttrubute2())) + ";"
                            + (lote.getAttrubute3() == null ? "null" : (lote.getAttrubute3().isEmpty() ? "null" : lote.getAttrubute3())) + ";"
                            + "FIN\r\n");
                }

            }

            for (MtlTransactionsInterface trx : trxs) {
                List<MtlSerialNumbersInterface> series = mtlSerialNumbersInterfaceDao.getAll(trx.getTransactionInterfaceId());
                for (MtlSerialNumbersInterface serie : series) {
                    writer.write("3;"
                            + (serie.getTransactionInterfaceId() == null ? "null" : serie.getTransactionInterfaceId()) + ";"
                            + (serie.getLastUpdateDate() == null ? "null" : (serie.getLastUpdateDate().isEmpty() ? "null" : serie.getLastUpdateDate())) + ";"
                            + (serie.getLastUpdatedBy() == null ? "null" : serie.getLastUpdatedBy()) + ";"
                            + (serie.getCreationDate() == null ? "null" : (serie.getCreationDate().isEmpty() ? "null" : serie.getCreationDate())) + ";"
                            + (serie.getCreatedBy() == null ? "null" : serie.getCreatedBy()) + ";"
                            + (serie.getFmSerialNumber() == null ? "null" : (serie.getFmSerialNumber().isEmpty() ? "null" : serie.getFmSerialNumber())) + ";"
                            + "FIN\r\n");
                }
            }

            writer.flush();
            writer.close();

            // Elimina datos de la BD
            for (MtlTransactionsInterface trx : trxs) {
                mtlSerialNumbersInterfaceDao.delete(trx.getTransactionInterfaceId());
                mtlTransactionLotsInterfaceDao.delete(trx.getTransactionInterfaceId());
                mtlTransactionsInterfaceDao.delete(trx.getTransactionInterfaceId());
            }

        }catch (ServiceException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
