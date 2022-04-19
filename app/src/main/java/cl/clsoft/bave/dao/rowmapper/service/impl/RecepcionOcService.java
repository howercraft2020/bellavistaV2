package cl.clsoft.bave.dao.rowmapper.service.impl;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.dao.IDatosCabeceraRecepcionDao;
import cl.clsoft.bave.dao.IDatosRecepcionDao;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.IOrganizacionPrincipalDao;
import cl.clsoft.bave.dao.IPoHeadersAllDao;
import cl.clsoft.bave.dao.IPoLineLocationsAllDao;
import cl.clsoft.bave.dao.IPoLinesAllDao;
import cl.clsoft.bave.dao.IRcvHeadersInterfaceDao;
import cl.clsoft.bave.dao.IRcvTransactionsInterfaceDao;
import cl.clsoft.bave.dao.impl.DatosCabeceraRecepcionImpl;
import cl.clsoft.bave.dao.impl.DatosRecepcionDaoImpl;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.OrganizacionPrincipalDaoImpl;
import cl.clsoft.bave.dao.impl.PoHeadersAllDaoImpl;
import cl.clsoft.bave.dao.impl.PoLineLocationsAllDaoImpl;
import cl.clsoft.bave.dao.impl.PoLinesAllDaoImpl;
import cl.clsoft.bave.dao.impl.RcvHeadersInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.RcvTransactionsInterfaceDaoImpl;
import cl.clsoft.bave.dao.rowmapper.service.IRecepcionOcService;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;
import cl.clsoft.bave.model.DatosRecepcion;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.model.PoLineLocationsAll;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.repository.RcvHeaderInterfaceRepository;
import cl.clsoft.bave.repository.RcvTransactionsInterfaceRepository;

public class RecepcionOcService implements IRecepcionOcService {

    private static final String TAG = "RecepcionOcService";
    private RcvHeaderInterfaceRepository rcvHeaderInterfaceRepository;
    private RcvTransactionsInterfaceRepository rcvTransactionsInterfaceRepository;

    private  IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    private IRestRcvHeadersInterface iRestRcvHeadersInterface;

    @Override
    public List<PoHeadersAll> getAllRecepcionesOc() throws ServiceException {
        IPoHeadersAllDao poHeadersAllDao = new PoHeadersAllDaoImpl();
        try {
            return poHeadersAllDao.getAll();
        } catch(Exception e) {

        }
        return null;
    }

    @Override
    public List<RcvTransactionsInterface> getAllArticulos(Long headerInterfaceId) throws ServiceException {
        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        try {
            return rcvTransactionsInterfaceDao.getArticulos(headerInterfaceId);
        } catch(Exception e){
            Log.d("JPINTO", e.getMessage());
        }
        return null;
    }

    @Override
    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Double cantidad, Long poLineNum,Long polineId,Long itemid) throws ServiceException {
        IRcvTransactionsInterfaceDao ircvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        IDatosRecepcionDao iDatosRecepcionDao = new DatosRecepcionDaoImpl();
        IDatosCabeceraRecepcionDao iDatosCabeceraRecepcionDao = new DatosCabeceraRecepcionImpl();
        IRcvHeadersInterfaceDao iRcvHeadersInterfaceDao = new RcvHeadersInterfaceDaoImpl();
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        IPoLinesAllDao poLinesAllDao = new PoLinesAllDaoImpl();
        IOrganizacionPrincipalDao organizacionPrincipalDao = new OrganizacionPrincipalDaoImpl();

        /// REST API REPOSITORIO INICIALIZACION

        rcvHeaderInterfaceRepository = new RcvHeaderInterfaceRepository();
        rcvTransactionsInterfaceRepository = new RcvTransactionsInterfaceRepository();


        Long inventoryItemId;
        Long poLineId;
        String udm;
        DatosRecepcion datosRecepcion;
        DatosCabeceraRecepcion datosCabeceraRecepcion;
        Double quantity = 0.0;
        Double quantityReceived = 0.0;
        Double quantityCancelled = 0.0;
        Double qtyRcvTolerance = 0.0;
        Double qtyPending = 0.0;
        Long headerInterfaceId = 0L;
        Long groupId = 0L;

        String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        try{

            //Cabecera
            headerInterfaceId = Long.parseLong(poHeaderId + receiptNum.toString());
            groupId = Long.parseLong(receiptNum.toString() + poHeaderId);
            RcvHeadersInterface rcvHeadersInterface = new RcvHeadersInterface();
            RcvTransactionsInterface rcvTransactionsInterface = new RcvTransactionsInterface();

            //Datos Recepcion

            Log.d("RECEP", "Llamando iDatosRecepcionDao.get(segment1, oc, receiptNum, poLineNum)");
            Log.d("Segment1", ""+segment1);
            Log.d("oc", ""+oc);
            Log.d("receiptNum", ""+receiptNum);
            Log.d("poLineNum", ""+poLineNum);
            datosRecepcion = iDatosRecepcionDao.get(segment1, oc, receiptNum, poLineNum);

            /*
            if (datosRecepcion == null){
                throw new ServiceException(1,"No existe información para articulo :"+segment1);
            }



            //Validación de Articulo
            MtlSystemItems sigle = mtlSystemItemsDao.getBySegment(segment1);
            inventoryItemId = sigle.getInventoryItemId();
            udm = sigle.getPrimaryUomCode();

            if (inventoryItemId == null){
                throw new ServiceException(1,"No se encuentra información para el articulo : " + segment1);
            }

            //Validacion Linea
            PoLinesAll linea = poLinesAllDao.getLinea(inventoryItemId,poHeaderId,poLineNum);
            poLineId = linea.getPoLineId();
            if (linea == null){

                throw new ServiceException(1,"Linea ingresada para articulo : " + segment1 + " no es valida.");
            }

            rcvTransactionsInterface = ircvTransactionsInterfaceDao.get(headerInterfaceId, segment1, linea.getPoLineId());
            if (rcvTransactionsInterface != null){
                throw new ServiceException(1,"El articulo : "+segment1+ " ya ha sido ingresado anteriormente");
            }

            //Datos organizacion Principal
            OrganizacionPrincipal organizacionPrincipal = organizacionPrincipalDao.get();

            */


            rcvHeadersInterface = iRcvHeadersInterfaceDao.get(headerInterfaceId);

            /*
            if(rcvHeadersInterface == null){


                datosCabeceraRecepcion = iDatosCabeceraRecepcionDao.get(poHeaderId,receiptNum);
                if (datosCabeceraRecepcion == null){
                    throw new ServiceException(1,"No se encuentran datos de OC");
                }
                else
                {
            */
                    rcvHeadersInterface = new RcvHeadersInterface();
                    rcvHeadersInterface.setHeaderInterfaceId(headerInterfaceId);
                    rcvHeadersInterface.setReciptSourceCode("VENDOR");
                    rcvHeadersInterface.setTransactionType("NEW");
                    rcvHeadersInterface.setAutoTransactCode("");
                    rcvHeadersInterface.setLastUpdateDate(fecha);
                    /*
                    rcvHeadersInterface.setLastUpdateBy(datosCabeceraRecepcion.getUserId());
                    rcvHeadersInterface.setCreatedBy(datosCabeceraRecepcion.getUserId());
                    rcvHeadersInterface.setReciptNum(datosCabeceraRecepcion.getReceiptNum());
                    rcvHeadersInterface.setVendorId(datosCabeceraRecepcion.getVendorId());
                    rcvHeadersInterface.setVendorSiteCode(datosCabeceraRecepcion.getVendorSiteCode());
                    rcvHeadersInterface.setVendorSiteId(datosCabeceraRecepcion.getVendorSiteId());
                    //rcvHeadersInterface.setShipToOrganizationCode(organizacionPrincipal.getCode());
                    rcvHeadersInterface.setShipToOrganizationCode("Q01");
                    */
                    rcvHeadersInterface.setLocationId(248L);
                     /*
                    rcvHeadersInterface.setReceiverId(datosCabeceraRecepcion.getUserId());
                    rcvHeadersInterface.setCurrencyCode(datosCabeceraRecepcion.getCurrencyCode());
                    rcvHeadersInterface.setConversionRateType(datosCabeceraRecepcion.getRateType());
                    rcvHeadersInterface.setConversionRate(datosCabeceraRecepcion.getRate());
                    rcvHeadersInterface.setConversionRateDate(datosCabeceraRecepcion.getRateDate());
                    rcvHeadersInterface.setPaymentTermsId(datosCabeceraRecepcion.getTermsId());
                      */
                    rcvHeadersInterface.setTransactionDate(fecha);

                    rcvHeadersInterface.setComments("");
                    //rcvHeadersInterface.setOrgId(datosCabeceraRecepcion.getOrgId());
                    rcvHeadersInterface.setProcessingStatusCode("PENDING");
                    rcvHeadersInterface.setValidationFlag("Y");
                    rcvHeadersInterface.setGroupId(groupId);
                    //iRcvHeadersInterfaceDao.insert(rcvHeadersInterface);
                    Log.d("HEADER", "INSERTANDO HEADER");

                    rcvHeaderInterfaceRepository.insert(rcvHeadersInterface);

          //      }

          //  }

            //Valida Cantidad

           // quantity = datosRecepcion.getQuantity();
           // quantityReceived = datosRecepcion.getQuantityReceived();
            //quantityCancelled = datosRecepcion.getQuantityCancelled();
            //qtyRcvTolerance = datosRecepcion.getQtyRcvTolerance();
            //qtyPending = (quantity-quantityReceived-quantityCancelled) * (1 + qtyRcvTolerance/100);

            //if (cantidad > qtyPending){
            //    throw new ServiceException(1,"Cantidad no puede ser mayor a : "+qtyPending);
           // }

            rcvTransactionsInterface = new RcvTransactionsInterface();
            rcvTransactionsInterface.setInterfaceTransactionId(polineId);
            rcvTransactionsInterface.setLastUpdatedDate(fecha);
            //rcvTransactionsInterface.setLastUpdatedBy(datosRecepcion.getUserId());
            rcvTransactionsInterface.setCreationDate(fecha);
            //rcvTransactionsInterface.setCreatedBy(datosRecepcion.getUserId());
            rcvTransactionsInterface.setTransactionType("RECEIVE");
            rcvTransactionsInterface.setTransactionDate(fecha);
            rcvTransactionsInterface.setQuantity(cantidad);
            //rcvTransactionsInterface.setUnitOfMeasure(datosRecepcion.getUnitMeasLookupCode());
            //rcvTransactionsInterface.setItemId(datosRecepcion.getItemId());
            rcvTransactionsInterface.setItemId(itemid);
            //rcvTransactionsInterface.setItemDescription(datosRecepcion.getItemDescription());
            //rcvTransactionsInterface.setUomCode(datosRecepcion.getPrimaryUomCode());
            rcvTransactionsInterface.setShipToLocationId(248L);
            rcvTransactionsInterface.setPrimaryQuantity(cantidad);
            rcvTransactionsInterface.setReceiptSourceCode("VENDOR");
            //rcvTransactionsInterface.setVendorId(datosRecepcion.getVendorId());
            //rcvTransactionsInterface.setVendorSiteId(datosRecepcion.getVendorSiteId());
           // rcvTransactionsInterface.setToOrganizationId(organizacionPrincipal.getIdOrganizacion());
            //rcvTransactionsInterface.setPoHeaderId(datosRecepcion.getPoHeaderId());
            //rcvTransactionsInterface.setPoLineId(datosRecepcion.getPoLineId());
            rcvTransactionsInterface.setPoLineId(polineId);
            //rcvTransactionsInterface.setPoLineLocation(datosRecepcion.getLineLocationId());
            //rcvTransactionsInterface.setPoUnitPrice(datosRecepcion.getUnitPrice());
            //rcvTransactionsInterface.setCurrencyCode(datosRecepcion.getCurrencyCode());
            rcvTransactionsInterface.setSourceDocumentCode("PO");
            //rcvTransactionsInterface.setCurrencyConversionType(datosRecepcion.getRateType());
            //rcvTransactionsInterface.setCurrencyConversionRate(datosRecepcion.getRate());
            //rcvTransactionsInterface.setCurrencyConversionDate(datosRecepcion.getRateDate());
            //rcvTransactionsInterface.setPoDistributionId(datosRecepcion.getPoDistributionId());
            rcvTransactionsInterface.setDestinationTypeCode("RECEIVING");
            rcvTransactionsInterface.setLocationId(248L);
            rcvTransactionsInterface.setDeliverToLocationId(248L);
            rcvTransactionsInterface.setInspectionStatusCode("NOT INSPECTED");
            rcvTransactionsInterface.setHeaderInterfaceId(headerInterfaceId);
            //rcvTransactionsInterface.setVendorSiteCode(datosRecepcion.getVendorSiteCode());
            rcvTransactionsInterface.setProcessingStatusCode("PENDING");
            rcvTransactionsInterface.setProcessingModeCode("BATCH");
            rcvTransactionsInterface.setComments("");
            rcvTransactionsInterface.setUseMtlLot(0L);
            rcvTransactionsInterface.setUseMtlSerial(0L);
            rcvTransactionsInterface.setTransactionStatusCode("PENDING");
            rcvTransactionsInterface.setValidationFlag("Y");
            //rcvTransactionsInterface.setOrgId(datosRecepcion.getOrgId());
            rcvTransactionsInterface.setGroupId(groupId);
            rcvTransactionsInterface.setAutoTransactCode("RECEIVE");
            rcvTransactionsInterface.setSegment1(segment1);
            //ircvTransactionsInterfaceDao.insert(rcvTransactionsInterface);
            Log.d("TRX", "INSERTANDO TRANSACTIONS");
            rcvTransactionsInterfaceRepository.RcvInsert(rcvTransactionsInterface);
        } catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }catch (Exception e){
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public String crearArchivo(Long interfaceheaderId, String numeroOc, Long receiptNum, Long poHeaderId, String comentario, Long groupId) throws ServiceException {
     String nomenclatura = "";
     String salida = "";

     /*
     IRcvHeadersInterfaceDao rcvHeadersInterfaceDao = new RcvHeadersInterfaceDaoImpl();
     IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
     IPoHeadersAllDao poHeadersAllDao = new PoHeadersAllDaoImpl();
     IPoLinesAllDao poLinesAllDao = new PoLinesAllDaoImpl();
     IPoLineLocationsAllDao poLineLocationsAllDao = new PoLineLocationsAllDaoImpl();
     IPoDistributionsAllDao poDistributionsAllDao = new PoDistributionsAllDaoImpl();


        nomenclatura = "I_1_"+numeroOc+"_"+receiptNum+".txt";

        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
        File rutaArchivo = new File(Dir, nomenclatura);
        try{

            //Cabecera
            RcvHeadersInterface rcvHeadersInterface = new RcvHeadersInterface();
            List<RcvTransactionsInterface> rcvTransactionsInterface;
            rcvHeadersInterface = rcvHeadersInterfaceDao.get(interfaceheaderId);
            rcvTransactionsInterface = rcvTransactionsInterfaceDao.getArticulos(interfaceheaderId);

            if(rcvHeadersInterface == null){
                throw new ServiceException(1,"No se encuentran datos cargados para esta recepcion");
            }

            if(rcvTransactionsInterface.size() == 0){
                throw new ServiceException(1,"La recepción no tiene lineas agregadas");
            }

            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write("RECIBO;FIN" +"\r\n");


            writer.write("1;"
                        + (rcvHeadersInterface.getHeaderInterfaceId() == null ? "null" : rcvHeadersInterface.getHeaderInterfaceId()) + ";"
                        + (rcvHeadersInterface.getReciptSourceCode() == null ? "null" : (rcvHeadersInterface.getReciptSourceCode().isEmpty() ? "null" : rcvHeadersInterface.getReciptSourceCode())) + ";"
                        + (rcvHeadersInterface.getTransactionType() == null ? "null" : (rcvHeadersInterface.getTransactionType().isEmpty() ? "null" : rcvHeadersInterface.getTransactionType())) + ";"
                        + (rcvHeadersInterface.getAutoTransactCode() == null ? "null" : (rcvHeadersInterface.getAutoTransactCode().isEmpty() ? "null" : rcvHeadersInterface.getAutoTransactCode())) + ";"
                        + (rcvHeadersInterface.getLastUpdateDate() == null ? "null" : (rcvHeadersInterface.getLastUpdateDate().isEmpty() ? "null" : rcvHeadersInterface.getLastUpdateDate())) + ";"
                        + (rcvHeadersInterface.getLastUpdateBy() == null ? "null" : rcvHeadersInterface.getLastUpdateBy()) + ";"
                        + (rcvHeadersInterface.getCreatedBy() == null ? "null" : rcvHeadersInterface.getCreatedBy()) + ";"
                        + (rcvHeadersInterface.getReciptNum() == null ? "null" : rcvHeadersInterface.getReciptNum()) + ";"
                        + (rcvHeadersInterface.getVendorId() == null ? "null" : rcvHeadersInterface.getVendorId()) + ";"
                        + (rcvHeadersInterface.getVendorSiteCode() == null ? "null" : (rcvHeadersInterface.getVendorSiteCode().isEmpty() ? "null" : rcvHeadersInterface.getVendorSiteCode())) + ";"
                        + (rcvHeadersInterface.getVendorSiteId() == null ? "null" : rcvHeadersInterface.getVendorSiteId()) + ";"
                        + (rcvHeadersInterface.getShipToOrganizationCode() == null ? "null" : (rcvHeadersInterface.getShipToOrganizationCode().isEmpty() ? "null" : rcvHeadersInterface.getShipToOrganizationCode())) + ";"
                        + (rcvHeadersInterface.getLocationId() == null ? "null" : rcvHeadersInterface.getLocationId()) + ";"
                        + (rcvHeadersInterface.getReceiverId() == null ? "null" : rcvHeadersInterface.getReceiverId()) + ";"
                        + (rcvHeadersInterface.getCurrencyCode() == null ? "null" : (rcvHeadersInterface.getCurrencyCode().isEmpty() ? "null" : rcvHeadersInterface.getCurrencyCode())) + ";"
                        + (rcvHeadersInterface.getConversionRateType() == null ? "null" : (rcvHeadersInterface.getConversionRateType().isEmpty() ? "null" : rcvHeadersInterface.getConversionRateType())) + ";"
                        + (rcvHeadersInterface.getConversionRate() == null ? "null" : rcvHeadersInterface.getConversionRate()) + ";"
                        + (rcvHeadersInterface.getConversionRateDate() == null ? "null" : (rcvHeadersInterface.getConversionRateDate().isEmpty() ? "null" : rcvHeadersInterface.getConversionRateDate())) + ";"
                        + (rcvHeadersInterface.getPaymentTermsId() == null ? "null" : rcvHeadersInterface.getPaymentTermsId()) + ";"
                        + (rcvHeadersInterface.getTransactionDate() == null ? "null" : (rcvHeadersInterface.getTransactionDate().isEmpty() ? "null" : rcvHeadersInterface.getTransactionDate())) + ";"
                        + (comentario == null ? "null" : (comentario.isEmpty() ? "null" : comentario)) + ";"
                        + (rcvHeadersInterface.getOrgId() == null ? "null" : rcvHeadersInterface.getOrgId()) + ";"
                        + (rcvHeadersInterface.getProcessingStatusCode() == null ? "null" : (rcvHeadersInterface.getProcessingStatusCode().isEmpty() ? "null" : rcvHeadersInterface.getProcessingStatusCode())) + ";"
                        + (rcvHeadersInterface.getValidationFlag() == null ? "null" : (rcvHeadersInterface.getValidationFlag().isEmpty() ? "null" : rcvHeadersInterface.getValidationFlag())) + ";"
                        + (groupId == null ? "null" : groupId) + ";"
                        + (numeroOc == null ? "null" : (numeroOc.isEmpty() ? "null" : numeroOc)) + ";"
                        + "FIN\r\n");



            for(int i = 0; i <rcvTransactionsInterface.size(); i++)
            {
                writer.write("2;"
                        + (rcvTransactionsInterface.get(i).getInterfaceTransactionId() == null ? "null" : rcvTransactionsInterface.get(i).getInterfaceTransactionId()) + ";"
                        + (rcvTransactionsInterface.get(i).getLastUpdatedDate() == null ? "null" : (rcvTransactionsInterface.get(i).getLastUpdatedDate().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getLastUpdatedDate())) + ";"
                        + (rcvTransactionsInterface.get(i).getLastUpdatedBy() == null ? "null" : rcvTransactionsInterface.get(i).getLastUpdatedBy()) + ";"
                        + (rcvTransactionsInterface.get(i).getCreationDate() == null ? "null" : (rcvTransactionsInterface.get(i).getCreationDate().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getCreationDate())) + ";"
                        + (rcvTransactionsInterface.get(i).getCreatedBy() == null ? "null" : rcvTransactionsInterface.get(i).getCreatedBy()) + ";"
                        + (rcvTransactionsInterface.get(i).getTransactionType() == null ? "null" : (rcvTransactionsInterface.get(i).getTransactionType().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getTransactionType())) + ";"
                        + (rcvTransactionsInterface.get(i).getTransactionDate() == null ? "null" : (rcvTransactionsInterface.get(i).getTransactionDate().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getTransactionDate())) + ";"
                        + (rcvTransactionsInterface.get(i).getQuantity() == null ? "null" : rcvTransactionsInterface.get(i).getQuantity()) + ";"
                        + (rcvTransactionsInterface.get(i).getUnitOfMeasure() == null ? "null" : (rcvTransactionsInterface.get(i).getUnitOfMeasure().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getUnitOfMeasure())) + ";"
                        + (rcvTransactionsInterface.get(i).getItemId() == null ? "null" : rcvTransactionsInterface.get(i).getItemId()) + ";"
                        + (rcvTransactionsInterface.get(i).getItemDescription() == null ? "null" : (rcvTransactionsInterface.get(i).getItemDescription().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getItemDescription())) + ";"
                        + (rcvTransactionsInterface.get(i).getUomCode() == null ? "null" : (rcvTransactionsInterface.get(i).getUomCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getUomCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getShipToLocationId() == null ? "null" : rcvTransactionsInterface.get(i).getShipToLocationId()) + ";"
                        + (rcvTransactionsInterface.get(i).getPrimaryQuantity() == null ? "null" : rcvTransactionsInterface.get(i).getPrimaryQuantity()) + ";"
                        + (rcvTransactionsInterface.get(i).getReceiptSourceCode() == null ? "null" : (rcvTransactionsInterface.get(i).getReceiptSourceCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getReceiptSourceCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getVendorId() == null ? "null" : rcvTransactionsInterface.get(i).getVendorId()) + ";"
                        + (rcvTransactionsInterface.get(i).getVendorSiteId() == null ? "null" : rcvTransactionsInterface.get(i).getVendorSiteId()) + ";"
                        + (rcvTransactionsInterface.get(i).getToOrganizationId() == null ? "null" : rcvTransactionsInterface.get(i).getToOrganizationId()) + ";"
                        + (rcvTransactionsInterface.get(i).getPoHeaderId() == null ? "null" : rcvTransactionsInterface.get(i).getPoHeaderId()) + ";"
                        + (rcvTransactionsInterface.get(i).getPoLineId() == null ? "null" : rcvTransactionsInterface.get(i).getPoLineId()) + ";"
                        + (rcvTransactionsInterface.get(i).getPoLineLocation() == null ? "null" : rcvTransactionsInterface.get(i).getPoLineLocation()) + ";"
                        + (rcvTransactionsInterface.get(i).getPoUnitPrice() == null ? "null" : rcvTransactionsInterface.get(i).getPoUnitPrice()) + ";"
                        + (rcvTransactionsInterface.get(i).getCurrencyCode() == null ? "null" : (rcvTransactionsInterface.get(i).getCurrencyCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getCurrencyCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getSourceDocumentCode() == null ? "null" : (rcvTransactionsInterface.get(i).getSourceDocumentCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getSourceDocumentCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getCurrencyConversionType() == null ? "null" : (rcvTransactionsInterface.get(i).getCurrencyConversionType().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getCurrencyConversionType())) + ";"
                        + (rcvTransactionsInterface.get(i).getCurrencyConversionRate() == null ? "null" : rcvTransactionsInterface.get(i).getCurrencyConversionRate()) + ";"
                        + (rcvTransactionsInterface.get(i).getCurrencyConversionDate() == null ? "null" : (rcvTransactionsInterface.get(i).getCurrencyConversionDate().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getCurrencyConversionDate())) + ";"
                        + (rcvTransactionsInterface.get(i).getPoDistributionId() == null ? "null" : rcvTransactionsInterface.get(i).getPoDistributionId()) + ";"
                        + (rcvTransactionsInterface.get(i).getDestinationTypeCode() == null ? "null" : (rcvTransactionsInterface.get(i).getDestinationTypeCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getDestinationTypeCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getLocationId() == null ? "null" : rcvTransactionsInterface.get(i).getLocationId()) + ";"
                        + (rcvTransactionsInterface.get(i).getDeliverToLocationId() == null ? "null" : rcvTransactionsInterface.get(i).getDeliverToLocationId()) + ";"
                        + (rcvTransactionsInterface.get(i).getInspectionStatusCode() == null ? "null" : (rcvTransactionsInterface.get(i).getInspectionStatusCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getInspectionStatusCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getHeaderInterfaceId() == null ? "null" : rcvTransactionsInterface.get(i).getHeaderInterfaceId()) + ";"
                        + (rcvTransactionsInterface.get(i).getVendorSiteCode() == null ? "null" : (rcvTransactionsInterface.get(i).getVendorSiteCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getVendorSiteCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getProcessingStatusCode() == null ? "null" : (rcvTransactionsInterface.get(i).getProcessingStatusCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getProcessingStatusCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getComments() == null ? "null" : (rcvTransactionsInterface.get(i).getComments().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getComments())) + ";"
                        + (rcvTransactionsInterface.get(i).getProcessingModeCode() == null ? "null" : (rcvTransactionsInterface.get(i).getProcessingModeCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getProcessingModeCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getUseMtlLot() == null ? "null" : rcvTransactionsInterface.get(i).getUseMtlLot()) + ";"
                        + (rcvTransactionsInterface.get(i).getUseMtlSerial() == null ? "null" : rcvTransactionsInterface.get(i).getUseMtlSerial()) + ";"
                        + (rcvTransactionsInterface.get(i).getTransactionStatusCode() == null ? "null" : (rcvTransactionsInterface.get(i).getTransactionStatusCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getTransactionStatusCode())) + ";"
                        + (rcvTransactionsInterface.get(i).getValidationFlag() == null ? "null" : (rcvTransactionsInterface.get(i).getValidationFlag().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getValidationFlag())) + ";"
                        + (rcvTransactionsInterface.get(i).getOrgId() == null ? "null" : rcvTransactionsInterface.get(i).getOrgId()) + ";"
                        + (groupId == null ? "null" : groupId) + ";"
                        + (rcvTransactionsInterface.get(i).getAutoTransactCode() == null ? "null" : (rcvTransactionsInterface.get(i).getAutoTransactCode().isEmpty() ? "null" : rcvTransactionsInterface.get(i).getAutoTransactCode())) + ";"
                        + "FIN\r\n");

            }

            writer.flush();
            writer.close();
            */
            //salida = rutaArchivo.getAbsolutePath();
            Log.d(TAG, "salida: " + salida);


            // Elimina datos de la BD
            /*
            for (RcvTransactionsInterface trx : rcvTransactionsInterface) {
                rcvHeadersInterfaceDao.delete(trx.getHeaderInterfaceId());
                rcvTransactionsInterfaceDao.deletebyHeaderInterface(trx.getHeaderInterfaceId());
            }
            */
            //poHeadersAllDao.delete(poHeaderId);
            //poLinesAllDao.delete(poHeaderId);
            //poLineLocationsAllDao.delete(poHeaderId);
            //poDistributionsAllDao.delete(poHeaderId);

/*
        }catch (ServiceException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
  */
        return salida;
    }

    @Override
    public MtlSystemItems getMtlSystemItemsBySegment(String segment) throws ServiceException {
        Log.d(TAG, "RecepcionOcService::getMtlSystemItemsBySegment");

        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try {
            return mtlSystemItemsDao.getBySegment(segment);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public List<MtlSystemItems> getArticulosByOcReceipt(Long poHeaderId, Long receiptNum) throws ServiceException {
        IMtlSystemItemsDao mtlSystemItemsDao = new MtlSystemItemsDaoImpl();
        try{
            return mtlSystemItemsDao.getAllByOcReceipt(poHeaderId, receiptNum);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void updateEntry(Long entryId, Double cantidad, Long lineLocationId) throws ServiceException {
        Log.d(TAG, "RecepcionOcService::updateEntry");
        Log.d(TAG, "RecepcionOcService::updateEntry::entryId: " + entryId);
        Log.d(TAG, "RecepcionOcService::updateEntry::cantidad: " + cantidad);

        Double quantity = 0.0;
        Double quantityReceived = 0.0;
        Double quantityCancelled = 0.0;
        Double qtyRcvTolerance = 0.0;
        Double qtyPending = 0.0;


        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        IPoLineLocationsAllDao poLineLocationsAllDao = new PoLineLocationsAllDaoImpl();
        try {

            RcvTransactionsInterface entry = rcvTransactionsInterfaceDao.getByInterfaceTransactionId(entryId);
            if (entry == null) {
                throw new ServiceException(1, "Entry " + entryId + " no existe.");
            }

            PoLineLocationsAll poLineLocationsAll = poLineLocationsAllDao.getById(lineLocationId);
            if (poLineLocationsAll == null) {
                throw new ServiceException(1, "No se encuentra información para esta linea");
            }

            quantity = poLineLocationsAll.getQuantity().doubleValue();
            quantityReceived = poLineLocationsAll.getQuantityRecived().doubleValue();
            quantityCancelled = poLineLocationsAll.getQuantityCancelled().doubleValue();
            qtyRcvTolerance = poLineLocationsAll.getQtyRcvTolerance().doubleValue();
            qtyPending = (quantity-quantityReceived-quantityCancelled) * (1 + qtyRcvTolerance/100);

            if (cantidad > qtyPending){
                throw new ServiceException(1,"Cantidad no puede ser mayor a : "+qtyPending);
            }


            entry.setQuantity(cantidad);
            entry.setPrimaryQuantity(cantidad);
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String strLastUpdate = dateFormat.format(new Date());
            entry.setLastUpdatedDate(strLastUpdate.toUpperCase());
            rcvTransactionsInterfaceDao.update(entry);
        } catch(DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }
    }

    @Override
    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) throws ServiceException {
        Log.d(TAG, "RecepcionOcServiceImpl::deleteTransactionsInterfaceById");
        Log.d(TAG, "RecepcionOcServiceImpl::deleteTransactionsInterfaceById::interfaceTransactionId: " + interfaceTransactionId);

        IRcvTransactionsInterfaceDao rcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        try {
            rcvTransactionsInterfaceDao.delete(interfaceTransactionId);
        } catch (DaoException e) {
            throw new ServiceException(2, e.getDescripcion());
        }

    }

    @Override
    public List<PoLinesAll> getLines(Long inventoryItemId, Long poHeaderId) throws ServiceException {
        IPoLinesAllDao poLinesAllDao = new PoLinesAllDaoImpl();
        try {
            return poLinesAllDao.getLines(inventoryItemId,poHeaderId);
        }catch (Exception e){

        }
        return null;
    }

}
