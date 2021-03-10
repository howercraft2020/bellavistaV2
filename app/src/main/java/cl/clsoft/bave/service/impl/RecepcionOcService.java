package cl.clsoft.bave.service.impl;

import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.dao.IDatosCabeceraRecepcionDao;
import cl.clsoft.bave.dao.IDatosRecepcionDao;
import cl.clsoft.bave.dao.IPoHeadersAllDao;
import cl.clsoft.bave.dao.IRcvHeadersInterfaceDao;
import cl.clsoft.bave.dao.IRcvTransactionsInterfaceDao;
import cl.clsoft.bave.dao.impl.DatosCabeceraRecepcionImpl;
import cl.clsoft.bave.dao.impl.DatosRecepcionDaoImpl;
import cl.clsoft.bave.dao.impl.PoHeadersAllDaoImpl;
import cl.clsoft.bave.dao.impl.RcvHeadersInterfaceDaoImpl;
import cl.clsoft.bave.dao.impl.RcvTransactionsInterfaceDaoImpl;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;
import cl.clsoft.bave.model.DatosRecepcion;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.IRecepcionOcService;

public class RecepcionOcService implements IRecepcionOcService {

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
    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Long cantidad) throws ServiceException {
        IRcvTransactionsInterfaceDao ircvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();
        IDatosRecepcionDao iDatosRecepcionDao = new DatosRecepcionDaoImpl();
        IDatosCabeceraRecepcionDao iDatosCabeceraRecepcionDao = new DatosCabeceraRecepcionImpl();
        IRcvHeadersInterfaceDao iRcvHeadersInterfaceDao = new RcvHeadersInterfaceDaoImpl();

        DatosRecepcion datosRecepcion;
        DatosCabeceraRecepcion datosCabeceraRecepcion;
        Long quantity = 0L;
        Long quantityReceived = 0L;
        Long quantityCancelled = 0L;
        Long qtyRcvTolerance = 0L;
        Long qtyPending = 0L;
        Long headerInterfaceId = 0L;

        String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        try{

            //Cabecera
            headerInterfaceId = Long.parseLong(poHeaderId + receiptNum.toString());
            RcvHeadersInterface rcvHeadersInterface = new RcvHeadersInterface();
            RcvTransactionsInterface rcvTransactionsInterface = new RcvTransactionsInterface();

            //Datos Recepcion
            datosRecepcion = iDatosRecepcionDao.get(segment1, oc, receiptNum);
            if (datosRecepcion == null){
                throw new ServiceException(1,"No existe información para articulo :"+segment1);
            }

            rcvTransactionsInterface = ircvTransactionsInterfaceDao.get(headerInterfaceId, segment1);
            if (rcvTransactionsInterface != null){
                throw new ServiceException(1,"El articulo : "+segment1+ " ya ha sido ingresado anteriormente");
            }

            rcvHeadersInterface = iRcvHeadersInterfaceDao.get(headerInterfaceId);
            if(rcvHeadersInterface == null){

                datosCabeceraRecepcion = iDatosCabeceraRecepcionDao.get(poHeaderId,receiptNum);
                if (datosCabeceraRecepcion == null){
                    throw new ServiceException(1,"No se encuentran datos de OC");
                }
                else
                {
                    RcvHeadersInterface rcvHeadersInterface1 = new RcvHeadersInterface();
                    rcvHeadersInterface1.setHeaderInterfaceId(headerInterfaceId);
                    rcvHeadersInterface1.setReciptSourceCode("PDA");
                    rcvHeadersInterface1.setTransactionType("VENDOR");
                    rcvHeadersInterface1.setLastUpdateDate(fecha);
                    rcvHeadersInterface1.setLastUpdateBy(datosCabeceraRecepcion.getUserId());
                    rcvHeadersInterface1.setCreatedBy(datosCabeceraRecepcion.getUserId());
                    rcvHeadersInterface1.setReciptNum(datosCabeceraRecepcion.getReceiptNum());
                    rcvHeadersInterface1.setVendorId(datosCabeceraRecepcion.getVendorId());
                    rcvHeadersInterface1.setVendorSiteCode(datosCabeceraRecepcion.getVendorSiteCode());
                    rcvHeadersInterface1.setVendorSiteId(datosCabeceraRecepcion.getVendorSiteId());
                    rcvHeadersInterface1.setShipToOrganizationCode("Q01");
                    rcvHeadersInterface1.setLocationId(248L);
                    rcvHeadersInterface1.setReceiverId(datosCabeceraRecepcion.getUserId());
                    rcvHeadersInterface1.setCurrencyCode(datosCabeceraRecepcion.getCurrencyCode());
                    rcvHeadersInterface1.setConversionRateType(datosCabeceraRecepcion.getRateType());
                    rcvHeadersInterface1.setConversionRate(datosCabeceraRecepcion.getRate());
                    rcvHeadersInterface1.setConversionRateDate(datosCabeceraRecepcion.getRateDate());
                    rcvHeadersInterface1.setPaymentTermsId(datosCabeceraRecepcion.getTermsId());
                    rcvHeadersInterface1.setTransactionDate(fecha);
                    rcvHeadersInterface1.setComments("");
                    rcvHeadersInterface1.setOrgId(288L);
                    rcvHeadersInterface1.setProcessingStatusCode("PENDING");
                    rcvHeadersInterface1.setValidationFlag("Y");
                    rcvHeadersInterface1.setGroupId(555555L);
                    iRcvHeadersInterfaceDao.insert(rcvHeadersInterface1);

                }

            }

            //Valida Cantidad

            quantity = datosRecepcion.getQuantity();
            quantityReceived = datosRecepcion.getQuantityReceived();
            quantityCancelled = datosRecepcion.getQuantityCancelled();
            qtyRcvTolerance = datosRecepcion.getQtyRcvTolerance();
            qtyPending = (quantity-quantityReceived-quantityCancelled) * (1 + qtyRcvTolerance/100);

            if (cantidad >= qtyPending){
                throw new ServiceException(1,"Cantidad no puede ser mayor a : "+qtyPending);
            }

            RcvTransactionsInterface rcvTransactionsInterface1 = new RcvTransactionsInterface();
            rcvTransactionsInterface1.setInterfaceTransactionId(datosRecepcion.getPoLineId());
            rcvTransactionsInterface1.setLastUpdatedDate(fecha);
            rcvTransactionsInterface1.setCreationDate(fecha);
            rcvTransactionsInterface1.setTransactionType("RECEIVE");
            rcvTransactionsInterface1.setTransactionDate(fecha);
            //rcvTransactionsInterface1.setQuantity(cantidad);
            rcvTransactionsInterface1.setUomCode(datosRecepcion.getUnitMeasLookupCode());
            rcvTransactionsInterface1.setItemId(datosRecepcion.getItemId());
            rcvTransactionsInterface1.setItemDescription(datosRecepcion.getItemDescription());
            rcvTransactionsInterface1.setUomCode(datosRecepcion.getUomCode());
            rcvTransactionsInterface1.setShipToLocationId(248L);
            rcvTransactionsInterface1.setPrimaryQuantity(cantidad);
            rcvTransactionsInterface1.setReceiptSourceCode("VENDOR");
            rcvTransactionsInterface1.setVendorId(datosRecepcion.getVendorId());
            rcvTransactionsInterface1.setVendorSiteId(datosRecepcion.getVendorSiteId());
            rcvTransactionsInterface1.setToOrganizationId(288L);
            rcvTransactionsInterface1.setPoHeaderId(datosRecepcion.getPoHeaderId());
            rcvTransactionsInterface1.setPoLineId(datosRecepcion.getPoLineId());
            rcvTransactionsInterface1.setPoLineLocation(datosRecepcion.getLineLocationId());
            rcvTransactionsInterface1.setPoUnitPrice(datosRecepcion.getUnitPrice());
            rcvTransactionsInterface1.setCurrencyCode(datosRecepcion.getCurrencyCode());
            rcvTransactionsInterface1.setSourceDocumentCode("PO");
            rcvTransactionsInterface1.setCurrencyConversionType(datosRecepcion.getRateType());
            rcvTransactionsInterface1.setCurrencyConversionRate(datosRecepcion.getRate());
            rcvTransactionsInterface1.setCurrencyConversionDate(datosRecepcion.getRateDate());
            rcvTransactionsInterface1.setPoDistributionId(datosRecepcion.getPoDistributionId());
            rcvTransactionsInterface1.setDestinationTypeCode("RECEIVING");
            rcvTransactionsInterface1.setLocationId(248L);
            rcvTransactionsInterface1.setDeliverToLocationId(248L);
            rcvTransactionsInterface1.setInspectionStatusCode("NOT INSPECTED");
            rcvTransactionsInterface1.setHeaderInterfaceId(headerInterfaceId);
            rcvTransactionsInterface1.setVendorSiteCode(datosRecepcion.getVendorSiteCode());
            rcvTransactionsInterface1.setProcessingStatusCode("PENDING");
            rcvTransactionsInterface1.setComments("P/N y S/N ingresado por recepctor en la PDA");
            rcvTransactionsInterface1.setProcessingStatusCode("BATCH");
            rcvTransactionsInterface1.setUseMtlLot(0L);
            rcvTransactionsInterface1.setUseMtlSerial(0L);
            rcvTransactionsInterface1.setTransactionStatusCode("PENDING");
            rcvTransactionsInterface1.setValidationFlag("Y");
            rcvTransactionsInterface1.setOrgId(288L);
            rcvTransactionsInterface1.setGroupId(33444L);
            rcvTransactionsInterface1.setAutoTransactCode("RECEIVE");
            ircvTransactionsInterfaceDao.insert(rcvTransactionsInterface1);

        }catch (ServiceException e){
            throw e;
        }catch (DaoException e){
            throw new ServiceException(2, e.getDescripcion());
        }catch (Exception e){
            throw new ServiceException(2, e.getMessage());
        }
    }

    @Override
    public void crearArchivo(Long interfaceheaderId, String numeroOc, Long receiptNum) throws ServiceException {
     String nomenclatura = "";
     IRcvHeadersInterfaceDao iRcvHeadersInterfaceDao = new RcvHeadersInterfaceDaoImpl();
     IRcvTransactionsInterfaceDao iRcvTransactionsInterfaceDao = new RcvTransactionsInterfaceDaoImpl();


        nomenclatura = "I_1_"+numeroOc+"_"+receiptNum+".csv";

        File tarjetaSD = Environment.getExternalStorageDirectory();
        File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
        File rutaArchivo = new File(Dir, nomenclatura);
        try{

            //Cabecera
            RcvHeadersInterface rcvHeadersInterface = new RcvHeadersInterface();
            List<RcvTransactionsInterface> rcvTransactionsInterface;
            rcvHeadersInterface = iRcvHeadersInterfaceDao.get(interfaceheaderId);
            rcvTransactionsInterface = iRcvTransactionsInterfaceDao.getArticulos(interfaceheaderId);

            if(rcvHeadersInterface == null){
                throw new ServiceException(1,"No se encuentran datos cargados para esta recepcion");
            }

            if(rcvTransactionsInterface == null){
                throw new ServiceException(1,"La recepción no tiene lineas agregadas");
            }

            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write("RECIBO;FIN" +"\r\n");


            writer.write("1;"+rcvHeadersInterface.getHeaderInterfaceId()+";"+rcvHeadersInterface.getReciptSourceCode() +
                              ";"+rcvHeadersInterface.getTransactionType()+";"+rcvHeadersInterface.getLastUpdateDate() +
                              ";"+rcvHeadersInterface.getLastUpdateBy()+";"+rcvHeadersInterface.getCreatedBy() +
                              ";"+rcvHeadersInterface.getReciptNum()+";"+rcvHeadersInterface.getVendorId() +
                              ";"+rcvHeadersInterface.getVendorSiteCode()+";"+rcvHeadersInterface.getVendorSiteId() +
                              ";"+rcvHeadersInterface.getShipToOrganizationCode()+";"+rcvHeadersInterface.getLocationId() +
                              ";"+rcvHeadersInterface.getReceiverId()+";"+rcvHeadersInterface.getCurrencyCode() +
                              ";"+rcvHeadersInterface.getConversionRateType()+";"+rcvHeadersInterface.getConversionRate() +
                              ";"+rcvHeadersInterface.getConversionRateDate()+";"+rcvHeadersInterface.getPaymentTermsId() +
                              ";"+rcvHeadersInterface.getTransactionDate()+";"+rcvHeadersInterface.getComments() +
                              ";"+rcvHeadersInterface.getOrgId()+";"+rcvHeadersInterface.getProcessingStatusCode() +
                              ":"+rcvHeadersInterface.getValidationFlag()+";"+rcvHeadersInterface.getGroupId()+";FIN"+"\r\n");

            for(int i = 0; i <rcvTransactionsInterface.size(); i++)
            {
                writer.write("2;"+rcvTransactionsInterface.get(i).getHeaderInterfaceId()+";"+rcvTransactionsInterface.get(i).getLastUpdatedDate() +
                                  ";"+rcvTransactionsInterface.get(i).getLastUpdatedBy()+";"+rcvTransactionsInterface.get(i).getCreatedBy() +
                                  ";"+rcvTransactionsInterface.get(i).getTransactionType()+";"+rcvTransactionsInterface.get(i).getTransactionDate() +
                                  ";"+rcvTransactionsInterface.get(i).getQuantity()+";"+rcvTransactionsInterface.get(i).getUnitOfMeasure() +
                                  ";"+rcvTransactionsInterface.get(i).getItemId()+";"+rcvTransactionsInterface.get(i).getItemDescription() +
                                  ";"+rcvTransactionsInterface.get(i).getUomCode()+";"+rcvTransactionsInterface.get(i).getShipToLocationId() +
                                  ";"+rcvTransactionsInterface.get(i).getPrimaryQuantity()+";"+rcvTransactionsInterface.get(i).getReceiptSourceCode() +
                                  ";"+rcvTransactionsInterface.get(i).getVendorId()+";"+rcvTransactionsInterface.get(i).getVendorSiteId() +
                                  ";"+rcvTransactionsInterface.get(i).getToOrganizationId()+";"+rcvTransactionsInterface.get(i).getPoHeaderId() +
                                  ";"+rcvTransactionsInterface.get(i).getPoLineId()+";"+rcvTransactionsInterface.get(i).getPoLineLocation() +
                                  ";"+rcvTransactionsInterface.get(i).getPoUnitPrice()+";"+rcvTransactionsInterface.get(i).getCurrencyCode() +
                                  ";"+rcvTransactionsInterface.get(i).getSourceDocumentCode()+";"+rcvTransactionsInterface.get(i).getCurrencyConversionType() +
                                  ";"+rcvTransactionsInterface.get(i).getCurrencyConversionRate()+";"+rcvTransactionsInterface.get(i).getCurrencyConversionDate() +
                                  ";"+rcvTransactionsInterface.get(i).getPoDistributionId()+";"+rcvTransactionsInterface.get(i).getDestinationTypeCode() +
                                  ";"+rcvTransactionsInterface.get(i).getLocationId()+";"+rcvTransactionsInterface.get(i).getDeliverToLocationId() +
                                  ";"+rcvTransactionsInterface.get(i).getInspectionStatusCode()+";"+rcvTransactionsInterface.get(i).getHeaderInterfaceId() +
                                  ";"+rcvTransactionsInterface.get(i).getVendorSiteCode()+";"+rcvTransactionsInterface.get(i).getProcessingStatusCode() +
                                  ";"+rcvTransactionsInterface.get(i).getUseMtlLot()+";"+rcvTransactionsInterface.get(i).getUseMtlSerial() +
                                  ";"+rcvTransactionsInterface.get(i).getTransactionStatusCode()+";"+rcvTransactionsInterface.get(i).getValidationFlag() +
                                  ";"+rcvTransactionsInterface.get(i).getOrgId()+";"+rcvTransactionsInterface.get(i).getGroupId() +
                                  ";"+rcvTransactionsInterface.get(i).getAutoTransactCode()+";FIN"+"\r\n");
            }

            writer.flush();
            writer.close();

        }catch (ServiceException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
