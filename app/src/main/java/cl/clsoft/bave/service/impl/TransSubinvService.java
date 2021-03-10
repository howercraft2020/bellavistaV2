package cl.clsoft.bave.service.impl;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.dao.IDatosTransSubInvDao;
import cl.clsoft.bave.dao.IDatosTransSubinvDetalleDao;
import cl.clsoft.bave.dao.ILocalizadorDao;
import cl.clsoft.bave.dao.IMtlOnhandQuantitiesDao;
import cl.clsoft.bave.dao.IMtlSerialNumbersInterfaceDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IMtlTransactionLotsInterfaceDao;
import cl.clsoft.bave.dao.IMtlTransactionsInterfaceDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.DatosTransSubinvDetalleImpl;
import cl.clsoft.bave.dao.impl.DatosTransSubinvImpl;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlOnhandQuantitiesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSerialNumbersInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotsIfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotsIfaceDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.ITransSubinvService;

public class TransSubinvService implements ITransSubinvService {

    private static final String TAG = "TransSubinvService";

    @Override
    public List<DatosTransSubinv> getTransSubinv() throws ServiceException {
        IDatosTransSubInvDao iDatosTransSubInvDao = new DatosTransSubinvImpl();
        try {
            return iDatosTransSubInvDao.getTransSubinv();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void cargaTransferencia(String articulo, String lote, String subinventario, String localizador, Long cantidad) throws ServiceException{
        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();

        Double cantidadEnMano;
        String controlLote;

        try{

            //Validación de lote
            MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(articulo);
            controlLote = sigle.getLotControlCode();

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


            //Validación Cantidad en Mano
            cantidadEnMano = cantidades.getPrimaryTransactionQuantity();

            if (cantidadEnMano < cantidad) {
                throw new ServiceException(1,"Cantidad no puede ser mayor a : " + cantidadEnMano);
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
    public void insertarDatos(String id,String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Long cantidad, String subinventarioHasta, String localizadorHasta, List<String> series) throws ServiceException {
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();

        Long inventoryItemId;
        Long existe = 0L;
        String transactionInterfaceId;
        Long locatorId = 0L;
        Long transferLocatorId = 0L;
        String fechaId = "";
        Long seriesIngresadas;
        boolean isControlLote = false;
        boolean isControlSerie = false;
        Localizador localizadorDesde = null;
        Localizador localizadorDestino = null;

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

                if(!localizador.equals("")  && !localizadorHasta.equals("") ){
                    existe = mtlTransactionsInterfaceDao.getLocOrDesNotNull(inventoryItemId,subinventario,localizador,subinventarioHasta, localizadorHasta);
                }else if (localizador.equals("") && !localizadorHasta.equals("") ){
                    existe = mtlTransactionsInterfaceDao.getLocOrNullDestNotNull(inventoryItemId,subinventario,subinventarioHasta, localizadorHasta);
                }else if (!localizador.equals("") && localizadorHasta.equals("")) {
                    existe = mtlTransactionsInterfaceDao.getLocOrNotNullDestNull(inventoryItemId,subinventario,localizador,subinventarioHasta);
                }else{
                    existe = mtlTransactionsInterfaceDao.getLocOrNullDestNull(inventoryItemId,subinventario,subinventarioHasta);
                }

                if (existe != 0){
                    throw new ServiceException(1,articulo + "ya esta ingresado en interfaz");
                }

                // Subinventory Desde
                Subinventario subinventarioDesde = subinventarioDao.getByCodigo(subinventario);
                if (subinventarioDesde == null) {
                    throw new ServiceException(1, "Subinventario " + subinventario + " no existe en el sistema");
                }

                //Subinventario Hasta
                Subinventario subinventarioDestino = subinventarioDao.getByCodigo(subinventarioHasta);
                if (subinventarioDestino == null) {
                    throw new ServiceException(1, "Subinventario " + subinventarioHasta + " no existe en el sistema");
                }

                // Locator
                if (localizador != null) {
                    localizadorDesde = localizadorDao.getByCodigo(localizador);
                if (localizador == null) {
                    throw new ServiceException(1, "Localizador " + localizador + " no existe en el sistema");
                }
                }

                // Locator Dest
                if (localizadorHasta != null) {
                    localizadorDestino = localizadorDao.getByCodigo(localizadorHasta);
                    if (localizador == null) {
                        throw new ServiceException(1, "Localizador Destino " + localizadorHasta + " no existe en el sistema");
                    }
                }

                //Datos de mtlOnHandQuantities
                MtlOnhandQuantities mtlOnhandQuantities = mtlOnhandQuantitiesDao.get(articulo,lote, subinventario, localizador);

                if(mtlOnhandQuantities == null){
                    throw new ServiceException(1,"No se encuentra información en mtlonhandquantities");
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

                if (isControlSerie && series.size() < cantidad) {
                    throw new ServiceException(1, "Faltan series");
                }

                //Datos localizador
                //locatorId = localizadorDao.get(localizador);
                //transferLocatorId = localizadorDao.get(localizadorHasta);

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
                mtlTransactionsInterface.setTransactionUom("UND"); //UOM
                mtlTransactionsInterface.setTransactionDate(fecha);
                mtlTransactionsInterface.setSubinventoryCode(subinventarioDesde.getCodSubinventario());
                mtlTransactionsInterface.setLocatorId(localizadorDesde.getIdLocalizador());
                mtlTransactionsInterface.setTransactionSourceName("Glosa"); //Agregar Parametro
                mtlTransactionsInterface.setTransactionSourceTypeId(13L);
                mtlTransactionsInterface.setTransactionActionId(2L);
                mtlTransactionsInterface.setTransactionTypeId(2L);
                mtlTransactionsInterface.setTransactionReference(fechaId);
                mtlTransactionsInterface.setTransferSubinventory(subinventarioDestino.getCodSubinventario());
                mtlTransactionsInterface.setTransferOrganization(288L);
                mtlTransactionsInterface.setTransferLocator(localizadorDestino.getIdLocalizador());
                mtlTransactionsInterface.setSourceCode("PDA TRANSF SUBINV");
                mtlTransactionsInterface.setSourceLineId(1L);
                mtlTransactionsInterface.setSourceHeaderId(1L);
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
                    //mtlTransactionsLotsIface.setTransactionQuantity(cantidad);
                    //mtlTransactionsLotsIface.setPrimaryQuantity(cantidad);
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
    public List<DatosTransSubinvDetalle> getTransferencias(String numeroTraspaso) throws ServiceException {
        IDatosTransSubinvDetalleDao datosTransSubinvDetalleDao = new DatosTransSubinvDetalleImpl();
        try{
            return datosTransSubinvDetalleDao.getTransferencias(numeroTraspaso);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void crearArchivo(String transactionReference) throws ServiceException {
        String nomenclatura;
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceDaoImpl();
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();

        nomenclatura = "I_"+transactionReference+".csv";

        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
        File rutaArchivo = new File(Dir, nomenclatura);
        try{

            //Transferencias
            MtlTransactionsLotsIface mtlTransactionsLotsIface = new MtlTransactionsLotsIface();
            List<MtlSerialNumbersInterface> mtlSerialNumbersInterface;
            List<MtlTransactionsInterface> mtlTransactionsInterface;

            mtlTransactionsInterface = mtlTransactionsInterfaceDao.getTransferenciasByTraspaso(transactionReference);

            if(mtlTransactionsInterface == null){
                throw new ServiceException(1,"No se encuentran datos para este numero de traspaso");
            }

            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write("RECIBO;FIN" +"\r\n");

            for(int i = 0; i <mtlTransactionsInterface.size(); i++){

                writer.write("1;"+mtlTransactionsInterface.get(i).getTransactionInterfaceId()+";"+mtlTransactionsInterface.get(i).getProcessFlag() +
                                  ";"+mtlTransactionsInterface.get(i).getTransactionMode()+";"+mtlTransactionsInterface.get(i).getLastUpdateDate() +
                                  ";"+mtlTransactionsInterface.get(i).getLastUpdatedBy()+";"+mtlTransactionsInterface.get(i).getCreationDate() +
                                  ";"+mtlTransactionsInterface.get(i).getCreatedBy()+";"+mtlTransactionsInterface.get(i).getInventoryItemId() +
                                  ";"+mtlTransactionsInterface.get(i).getOrganizationId()+";"+mtlTransactionsInterface.get(i).getTransactionQuantity() +
                                  ";"+mtlTransactionsInterface.get(i).getPrimaryQuantity()+";"+mtlTransactionsInterface.get(i).getTransactionUom() +
                                  ";"+mtlTransactionsInterface.get(i).getTransactionDate()+";"+mtlTransactionsInterface.get(i).getSubinventoryCode() +
                                  ";"+mtlTransactionsInterface.get(i).getLocatorId()+";"+mtlTransactionsInterface.get(i).getTransactionSourceName() +
                                  ";"+mtlTransactionsInterface.get(i).getTransactionSourceTypeId()+";"+mtlTransactionsInterface.get(i).getTransactionActionId() +
                                  ";"+mtlTransactionsInterface.get(i).getTransactionTypeId()+";"+mtlTransactionsInterface.get(i).getTransactionReference() +
                                  ";"+mtlTransactionsInterface.get(i).getTransferSubinventory()+";"+mtlTransactionsInterface.get(i).getTransferOrganization() +
                                  ";"+mtlTransactionsInterface.get(i).getTransferLocator()+";"+mtlTransactionsInterface.get(i).getSourceCode() +
                                  ";"+mtlTransactionsInterface.get(i).getSourceLineId()+";"+mtlTransactionsInterface.get(i).getSourceHeaderId()+";FIN"+"\r\n");

                //Busqueda de lote
                mtlTransactionsLotsIface = mtlTransactionLotsInterfaceDao.getAll(mtlTransactionsInterface.get(i).getTransactionInterfaceId());

                writer.write("2;"+mtlTransactionsLotsIface.getTransactionInterfaceId()+";"+mtlTransactionsLotsIface.getLastUpdateDate() +
                                  ";"+mtlTransactionsLotsIface.getLastUpdateBy()+";"+mtlTransactionsLotsIface.getCreationDate() +
                                  ";"+mtlTransactionsLotsIface.getCreatedBy()+";"+mtlTransactionsLotsIface.getLotNumber() +
                                  ";"+mtlTransactionsLotsIface.getLotExpirationDate()+";"+mtlTransactionsLotsIface.getTransactionQuantity() +
                                  ";"+mtlTransactionsLotsIface.getPrimaryQuantity()+";"+mtlTransactionsLotsIface.getSerialTransactionTempId() +
                                  ";"+mtlTransactionsLotsIface.getAttributeCategory()+";"+mtlTransactionsLotsIface.getAttrubute1() +
                                  ";"+mtlTransactionsLotsIface.getAttrubute2()+";"+mtlTransactionsLotsIface.getAttrubute3()+";FIN"+"\r\n");

                //Busqueda de serie
                mtlSerialNumbersInterface = mtlSerialNumbersInterfaceDao.getAll(mtlTransactionsInterface.get(i).getTransactionInterfaceId());
                for(int x = 0; i <mtlSerialNumbersInterface.size(); x++){
                    writer.write("3;"+mtlSerialNumbersInterface.get(x).getTransactionInterfaceId()+";"+mtlSerialNumbersInterface.get(x).getLastUpdateDate() +
                                      ";"+mtlSerialNumbersInterface.get(x).getLastUpdatedBy()+";"+mtlSerialNumbersInterface.get(x).getCreationDate() +
                                      ";"+mtlSerialNumbersInterface.get(x).getCreatedBy()+";"+mtlSerialNumbersInterface.get(x).getFmSerialNumber()+";FIN"+"\r\n");

                }

            }

            writer.flush();
            writer.close();

        }catch (ServiceException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void controlSerie(String articulo) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();

        String controlSerie;

        try{

            //Validación de Articulo
            MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(articulo);
            controlSerie = sigle.getSerialNumberControlCode();

            if (sigle.equals("")){
                throw new ServiceException(1,"No se encuentra información de serie para el articulo : " + articulo);
            }

            if (controlSerie.equals("1")){
                throw new ServiceException(1,"Este articulo no tiene control de serie");
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
    public List<MtlSerialNumbersInterface> getAllSeries(Long transactionInterfaceId) throws ServiceException {
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceDaoImpl();
        try{
            return mtlSerialNumbersInterfaceDao.getAll(transactionInterfaceId);
        }catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
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
    public List<Subinventario> getSubinventarios() throws ServiceException {
        ISubinventarioDao subinventarioDao = new SubinventarioDaoImpl();
        try{
            return subinventarioDao.getAll();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public List<Localizador> getLocalizadoresBySubinventario(String subinventarioCodigo) throws ServiceException {
        Log.d(TAG, "TransSubninvService::getLocalizadoresBySubinventario");
        Log.d(TAG, "TransSubninvService::getLocalizadoresBySubinventario::subinventarioCodigo: " + subinventarioCodigo);

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
        Log.d(TAG, "ConteoCiclicoService::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            return mtlSystemItemsDao.getBySegment(segment);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
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
}
