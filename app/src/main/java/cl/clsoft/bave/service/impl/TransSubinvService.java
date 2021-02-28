package cl.clsoft.bave.service.impl;

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
import cl.clsoft.bave.dao.impl.DatosTransSubinvDetalleImpl;
import cl.clsoft.bave.dao.impl.DatosTransSubinvImpl;
import cl.clsoft.bave.dao.impl.LocalizadorDaoImpl;
import cl.clsoft.bave.dao.impl.MtlOnhandQuantitiesDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSerialNumbersInterfaceImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.MtlTransactionLotsIfaceImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.ITransSubinvService;

public class TransSubinvService implements ITransSubinvService {
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
        IMtlSerialNumbersInterfaceDao mtlSerialNumbersInterfaceDao = new MtlSerialNumbersInterfaceImpl();

        Long cantidadEnMano;
        String controlLote;
        Long seriesIngresadas;

        try{

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

            //Validación de lote
            MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(articulo);
            controlLote = sigle.getLotControlCode();

            if (controlLote.equals("2") && lote.equals("")){
                throw new ServiceException(1,"Debe ingresar un lote para articulo : " + articulo);
            }

            if (controlLote.equals("1") && !lote.equals("")){
                throw new ServiceException(1,"Articulo : " + articulo + " no tiene control de lote.");
            }

            //Validación Series
            seriesIngresadas = mtlSerialNumbersInterfaceDao.getCantSeries(5555L);


        }catch (ServiceException e){
            throw e;
        }catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }catch (Exception e){
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public void insertarDatos(String articulo, String lote, String subinventario, String localizador, Long cantidad, String subinventarioHasta, String localizadorHasta) throws ServiceException {
        IMtlTransactionsInterfaceDao mtlTransactionsInterfaceDao = new MtlTransactionInterfaceDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        IMtlOnhandQuantitiesDao mtlOnhandQuantitiesDao = new MtlOnhandQuantitiesDaoImpl();
        ILocalizadorDao localizadorDao = new LocalizadorDaoImpl();
        IMtlTransactionLotsInterfaceDao mtlTransactionLotsInterfaceDao = new MtlTransactionLotsIfaceImpl();

        Long inventoryItemId;
        Long existe = 0L;
        String transactionInterfaceId;
        Long locatorId = 0L;
        Long transferLocatorId = 0L;

        try{

                String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String fechaId = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());

                //Validación de Articulo
                MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(articulo);
                inventoryItemId = sigle.getInventoryItemId();

                if (inventoryItemId == null){
                     throw new ServiceException(1,"No se encuentra información para el articulo : " + articulo);
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
                    throw new ServiceException(1," : " + articulo);
                }

                //Datos de mtlOnHandQuantities
                MtlOnhandQuantities mtlOnhandQuantities = mtlOnhandQuantitiesDao.get(articulo,lote, subinventario, localizador);

                if(mtlOnhandQuantities == null){
                    throw new ServiceException(1,"No se encuentra información en mtlonhandquantities");
                }

                //TransactionInterfaceId
                transactionInterfaceId = String.valueOf(inventoryItemId) + String.valueOf(locatorId + String.valueOf(transferLocatorId));

                //Datos localizador
                locatorId = localizadorDao.get(localizador);
                transferLocatorId = localizadorDao.get(localizadorHasta);

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
                mtlTransactionsInterface.setSubinventoryCode(subinventario);
                mtlTransactionsInterface.setLocatorId(locatorId);
                mtlTransactionsInterface.setTransactionSourceName("Glosa"); //Agregar Parametro
                mtlTransactionsInterface.setTransactionSourceTypeId(13L);
                mtlTransactionsInterface.setTransactionActionId(2L);
                mtlTransactionsInterface.setTransactionTypeId(2L);
                mtlTransactionsInterface.setTransactionReference("2"+fechaId);
                mtlTransactionsInterface.setTransferSubinventory(subinventarioHasta);
                mtlTransactionsInterface.setTransferOrganization(288L);
                mtlTransactionsInterface.setTransferLocator(transferLocatorId);
                mtlTransactionsInterface.setSourceCode("PDA TRANSF SUBINV");
                mtlTransactionsInterface.setSourceLineId(1L);
                mtlTransactionsInterface.setSourceHeaderId(1L);
                mtlTransactionsInterfaceDao.insert(mtlTransactionsInterface);

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

}
