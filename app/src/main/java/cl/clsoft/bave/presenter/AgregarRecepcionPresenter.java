package cl.clsoft.bave.presenter;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestDatosRecepcion;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestOrganizacionPrincipal;
import cl.clsoft.bave.apis.IRestPoLinesAll;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;
import cl.clsoft.bave.model.DatosRecepcion;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityAgregarRecepcion;
import cl.clsoft.bave.view.ActivityArticulosRecepcion;
import cl.clsoft.bave.viewmodel.MtlSystemItemsViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarRecepcionPresenter extends BasePresenter {

    private static final String TAG = "AgregarRecepcionPresenter";
    private ActivityAgregarRecepcion mview;
    private IRecepcionOcService mService;
    private MtlSystemItemsViewModel mtlSystemItemsViewModel;


    //REST API VARIABLES

    IRestDatosRecepcion iRestDatosRecepcion;
    IRestPoLinesAll iRestPoLinesAll;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestRcvHeadersInterface iRestRcvHeadersInterface;
    IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    IRestRcvStatus iRestRcvStatus;
    IRestOrganizacionPrincipal iRestOrganizacionPrincipal;


    public AgregarRecepcionPresenter(ActivityAgregarRecepcion mview, IRecepcionOcService mService) {
        this.mview = mview;
        this.mService = mService;


        this.iRestDatosRecepcion = ApiUtils.getIRestDatosRecepcionV2();
        this.iRestPoLinesAll = ApiUtils.getIRestPoLinesAll();
        this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        this.iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterface();
        this.iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();
        this.iRestRcvStatus = ApiUtils.getIRestRcvStatus();
        this.iRestOrganizacionPrincipal = ApiUtils.getIRestOrganizacionPrincipal();
    }

    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Double cantidad, Long poLineNum, Long poLineid,Long itemid) {


        if(segment1.equals("")) {
            mview.showError("Debe Ingresar un Codigo Sigle");
        }
        else if(cantidad == 0){
            mview.showError("Debe Ingresar una cantidad mayor que 0");
        }
        else {
            //this.mService.cargaRecepcion(segment1, poHeaderId, oc, receiptNum, cantidad,poLineNum,poLineid,itemid);


            iRestDatosRecepcion.getDatosRecepcionV2(segment1, oc, receiptNum, poLineNum).enqueue(new Callback<DatosRecepcion>() {
                @Override
                public void onResponse(Call<DatosRecepcion> call, Response<DatosRecepcion> response) {


                    Long poLineId;

                    DatosRecepcion datosRecepcion;



                    String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


                    //REV 1 CORRECTO DATOS RECEPCION

                    if(response.isSuccessful()==true && response.body()!=null && response.code()==200){

                        datosRecepcion = response.body();


                        //REV2 CORRECTO ARTICULO MTL_SYSTEM_ITEM
                         iRestMtlSystemItems.getBySegment(segment1).enqueue(new Callback<MtlSystemItems>() {
                             @Override
                             public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {

                              if(response.isSuccessful() ==true && response.body()!=null && response.code()==200) {
                                  Long inventoryItemId;
                                  String udm;

                                  MtlSystemItems sigle = response.body();
                                  inventoryItemId = sigle.getInventoryItemId();
                                  udm = sigle.getPrimaryUomCode();


                                  //REV3 CORRECTO PO_LINES_ALL
                                  iRestPoLinesAll.getLinea(inventoryItemId,poHeaderId,poLineNum).enqueue(new Callback<PoLinesAll>() {
                                      @Override
                                      public void onResponse(Call<PoLinesAll> call, Response<PoLinesAll> response) {

                                       if(response.isSuccessful()==true && response.body()!=null && response.code()==200){
                                            PoLinesAll poLinesAll = response.body();


                                        //REV4 CORRECTO RCV_TRANSACTIONS_INTERFACE
                                           Long headerInterfaceId = 0L;
                                           Long groupId = 0L;
                                           headerInterfaceId = Long.parseLong(poHeaderId + receiptNum.toString());
                                           groupId = Long.parseLong(receiptNum.toString() + poHeaderId);
                                        iRestRcvTransactionsInterface.get(headerInterfaceId,segment1,poLinesAll.getPoLineId()).enqueue(new Callback<RcvTransactionsInterface>() {
                                            @Override
                                            public void onResponse(Call<RcvTransactionsInterface> call, Response<RcvTransactionsInterface> response) {

                                                mview.showError("RCV_TRANSACTIONS_INTERFACE EJECUTANDO");

                                              if(response.isSuccessful()==true && response.code()==200){




                                                  //NO INSERTAR POR QUE YA EXISTE EN RCV_TRANSACTIONS_INTERFACE
                                                  if(response.body().getInterfaceTransactionId()!=0){

                                                    mview.showError("El articulo : "+segment1+ " ya ha sido ingresado anteriormente");
                                                  }
                                                  //INSERTAR POR QUE NO EXISTE EN RCV_TRANSACTIONS_INTERFACE
                                                  if(response.body().getInterfaceTransactionId()==0){




                                                      iRestOrganizacionPrincipal.getAll().enqueue(new Callback<OrganizacionPrincipal>() {
                                                          @Override
                                                          public void onResponse(Call<OrganizacionPrincipal> call, Response<OrganizacionPrincipal> response) {

                                                              if(response.isSuccessful()==true && response.body()!=null){

                                                                  OrganizacionPrincipal organizacionPrincipal = response.body();

                                                                  iRestDatosRecepcion.getV2(poHeaderId,receiptNum).enqueue(new Callback<DatosCabeceraRecepcion>() {
                                                                      @Override
                                                                      public void onResponse(Call<DatosCabeceraRecepcion> call, Response<DatosCabeceraRecepcion> response) {

                                                                          if(response.isSuccessful()==true && response.body()!=null){



                                                                              DatosCabeceraRecepcion datosCabeceraRecepcion = response.body();
                                                                              RcvHeadersInterface  rcvHeadersInterface = new RcvHeadersInterface();


                                                                              rcvHeadersInterface.setHeaderInterfaceId(Long.parseLong(poHeaderId + receiptNum.toString()));
                                                                              rcvHeadersInterface.setReciptSourceCode("VENDOR");
                                                                              rcvHeadersInterface.setTransactionType("NEW");
                                                                              rcvHeadersInterface.setAutoTransactCode("");
                                                                              rcvHeadersInterface.setLastUpdateDate(fecha);
                                                                              rcvHeadersInterface.setLastUpdateBy(datosCabeceraRecepcion.getUserId());
                                                                              rcvHeadersInterface.setCreatedBy(datosCabeceraRecepcion.getUserId());
                                                                              rcvHeadersInterface.setReciptNum(datosCabeceraRecepcion.getReceiptNum());
                                                                              rcvHeadersInterface.setVendorId(datosCabeceraRecepcion.getVendorId());
                                                                              rcvHeadersInterface.setVendorSiteCode(datosCabeceraRecepcion.getVendorSiteCode());
                                                                              rcvHeadersInterface.setVendorSiteId(datosCabeceraRecepcion.getVendorSiteId());
                                                                              rcvHeadersInterface.setShipToOrganizationCode(organizacionPrincipal.getCode());
                                                                              rcvHeadersInterface.setShipToOrganizationCode("Q01");
                                                                              rcvHeadersInterface.setLocationId(248L);
                                                                              rcvHeadersInterface.setReceiverId(datosCabeceraRecepcion.getUserId());
                                                                              rcvHeadersInterface.setCurrencyCode(datosCabeceraRecepcion.getCurrencyCode());
                                                                              rcvHeadersInterface.setConversionRateType(datosCabeceraRecepcion.getRateType());
                                                                              rcvHeadersInterface.setConversionRate(datosCabeceraRecepcion.getRate());
                                                                              rcvHeadersInterface.setConversionRateDate(datosCabeceraRecepcion.getRateDate());
                                                                              rcvHeadersInterface.setPaymentTermsId(datosCabeceraRecepcion.getTermsId());
                                                                              rcvHeadersInterface.setTransactionDate(fecha);

                                                                              rcvHeadersInterface.setComments("");
                                                                              rcvHeadersInterface.setOrgId(datosCabeceraRecepcion.getOrgId());
                                                                              rcvHeadersInterface.setProcessingStatusCode("PENDING");
                                                                              rcvHeadersInterface.setValidationFlag("Y");
                                                                              rcvHeadersInterface.setGroupId(Long.parseLong(receiptNum.toString() + poHeaderId));
                                                                              Log.d("HEADER", "INSERTANDO HEADER");




                                                                              Double quantity = 0.0;
                                                                              Double quantityReceived = 0.0;
                                                                              Double quantityCancelled = 0.0;
                                                                              Double qtyRcvTolerance = 0.0;
                                                                              Double qtyPending = 0.0;


                                                                              quantity = datosRecepcion.getQuantity();
                                                                              quantityReceived = datosRecepcion.getQuantityReceived();
                                                                              quantityCancelled = datosRecepcion.getQuantityCancelled();
                                                                              qtyRcvTolerance = datosRecepcion.getQtyRcvTolerance();
                                                                              qtyPending = (quantity - quantityReceived - quantityCancelled) * (1 + qtyRcvTolerance / 100);



                                                                              RcvTransactionsInterface rcvTransactionsInterface = new RcvTransactionsInterface();
                                                                              rcvTransactionsInterface.setInterfaceTransactionId(Long.parseLong(poHeaderId + receiptNum.toString()));
                                                                              rcvTransactionsInterface.setLastUpdatedDate(fecha);
                                                                              rcvTransactionsInterface.setLastUpdatedBy(datosRecepcion.getUserId());
                                                                              rcvTransactionsInterface.setCreationDate(fecha);
                                                                              rcvTransactionsInterface.setCreatedBy(datosRecepcion.getUserId());
                                                                              rcvTransactionsInterface.setTransactionType("RECEIVE");
                                                                              rcvTransactionsInterface.setTransactionDate(fecha);
                                                                              rcvTransactionsInterface.setQuantity(cantidad);
                                                                              rcvTransactionsInterface.setUnitOfMeasure(datosRecepcion.getUnitMeasLookupCode());
                                                                              rcvTransactionsInterface.setItemId(datosRecepcion.getItemId());
                                                                              rcvTransactionsInterface.setItemId(itemid);
                                                                              rcvTransactionsInterface.setItemDescription(datosRecepcion.getItemDescription());
                                                                              rcvTransactionsInterface.setUomCode(datosRecepcion.getPrimaryUomCode());
                                                                              rcvTransactionsInterface.setShipToLocationId(248L);
                                                                              rcvTransactionsInterface.setPrimaryQuantity(cantidad);
                                                                              rcvTransactionsInterface.setReceiptSourceCode("VENDOR");
                                                                              rcvTransactionsInterface.setVendorId(datosRecepcion.getVendorId());
                                                                              rcvTransactionsInterface.setVendorSiteId(datosRecepcion.getVendorSiteId());
                                                                              rcvTransactionsInterface.setToOrganizationId(organizacionPrincipal.getIdOrganizacion());
                                                                              rcvTransactionsInterface.setPoHeaderId(datosRecepcion.getPoHeaderId());
                                                                              rcvTransactionsInterface.setPoLineId(datosRecepcion.getPoLineId());
                                                                              rcvTransactionsInterface.setPoLineId(poLinesAll.getPoLineId());
                                                                              rcvTransactionsInterface.setPoLineLocation(datosRecepcion.getLineLocationId());
                                                                              rcvTransactionsInterface.setPoUnitPrice(datosRecepcion.getUnitPrice());
                                                                              rcvTransactionsInterface.setCurrencyCode(datosRecepcion.getCurrencyCode());
                                                                              rcvTransactionsInterface.setSourceDocumentCode("PO");
                                                                              rcvTransactionsInterface.setCurrencyConversionType(datosRecepcion.getRateType());
                                                                              rcvTransactionsInterface.setCurrencyConversionRate(datosRecepcion.getRate());
                                                                              rcvTransactionsInterface.setCurrencyConversionDate(datosRecepcion.getRateDate());
                                                                              rcvTransactionsInterface.setPoDistributionId(datosRecepcion.getPoDistributionId());
                                                                              rcvTransactionsInterface.setDestinationTypeCode("RECEIVING");
                                                                              rcvTransactionsInterface.setLocationId(248L);
                                                                              rcvTransactionsInterface.setDeliverToLocationId(248L);
                                                                              rcvTransactionsInterface.setInspectionStatusCode("NOT INSPECTED");
                                                                              rcvTransactionsInterface.setHeaderInterfaceId(Long.parseLong(poHeaderId + receiptNum.toString()));
                                                                              rcvTransactionsInterface.setVendorSiteCode(datosRecepcion.getVendorSiteCode());
                                                                              rcvTransactionsInterface.setProcessingStatusCode("PENDING");
                                                                              rcvTransactionsInterface.setProcessingModeCode("BATCH");
                                                                              rcvTransactionsInterface.setComments("");
                                                                              rcvTransactionsInterface.setUseMtlLot(0L);
                                                                              rcvTransactionsInterface.setUseMtlSerial(0L);
                                                                              rcvTransactionsInterface.setTransactionStatusCode("PENDING");
                                                                              rcvTransactionsInterface.setValidationFlag("Y");
                                                                              rcvTransactionsInterface.setOrgId(datosRecepcion.getOrgId());
                                                                              rcvTransactionsInterface.setGroupId(Long.parseLong(receiptNum.toString() + poHeaderId));
                                                                              rcvTransactionsInterface.setAutoTransactCode("RECEIVE");
                                                                              rcvTransactionsInterface.setSegment1(sigle.getSegment1());
                                                                              Log.d("TRX", "INSERTANDO TRANSACTIONS");




                                                                              iRestRcvHeadersInterface.insert(rcvHeadersInterface).enqueue(new Callback<Void>() {
                                                                                  @Override
                                                                                  public void onResponse(Call<Void> call, Response<Void> response) {



                                                                                  }

                                                                                  @Override
                                                                                  public void onFailure(Call<Void> call, Throwable t) {

                                                                                  }
                                                                              });

                                                                              iRestRcvTransactionsInterface.insert(rcvTransactionsInterface).enqueue(new Callback<Void>() {
                                                                                  @Override
                                                                                  public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                    mview.showSuccess("EXITO");
                                                                                      mview.resultadoOkAddTransaction();
                                                                                  }

                                                                                  @Override
                                                                                  public void onFailure(Call<Void> call, Throwable t) {

                                                                                  }
                                                                              });

                                                                          }


                                                                      }

                                                                      @Override
                                                                      public void onFailure(Call<DatosCabeceraRecepcion> call, Throwable t) {

                                                                      }
                                                                  });

                                                              }


                                                          }

                                                          @Override
                                                          public void onFailure(Call<OrganizacionPrincipal> call, Throwable t) {

                                                          }
                                                      });


                                                  }




                                              }



                                            }
                                            //REV4 FALLO
                                            @Override
                                            public void onFailure(Call<RcvTransactionsInterface> call, Throwable t) {
                                                mview.showError("REV4 FALLO"+t.getMessage());
                                                mview.showError("REV4 FALLO"+t.fillInStackTrace().getMessage());


                                            }
                                        }
                                        );



                                       }

                                      //REV3 ES NULO
                                      if(response.body()==null){

                                          mview.showError("Linea ingresada para articulo : " + segment1 + " no es valida.");


                                      }

                                      }

                                      //REV3 FALLO
                                      @Override
                                      public void onFailure(Call<PoLinesAll> call, Throwable t) {
                                          mview.showError("REV3 FALLO"+t.getMessage());



                                      }
                                  });

                              }

                             //REV2 NULL
                             if(response.body()==null){

                                 mview.showError("No se encuentra información para el articulo : " + segment1);

                             }

                             }

                             //REV2 FALLO
                             @Override
                             public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                                 mview.showError("REV2 FALLO"+t.getMessage());

                             }
                         });


                    }

                    //REV 1 NULL

                    if(response.body()==null){
                        mview.showError("No existe información para articulo :"+segment1);

                    }

                }

                //REV 1 FALLO
                @Override
                public void onFailure(Call<DatosRecepcion> call, Throwable t) {

                    mview.showError("REV1 FALLO"+t.getMessage());

                }
            });






            //mview.resultadoOkAddTransaction();
        }






        /*
        try{

            if(segment1.equals("")) {
                mview.showError("Debe Ingresar un Codigo Sigle");
            }
            else if(cantidad == 0){
                mview.showError("Debe Ingresar una cantidad mayor que 0");
            }
            else {
                this.mService.cargaRecepcion(segment1, poHeaderId, oc, receiptNum, cantidad,poLineNum,poLineid,itemid);
                mview.resultadoOkAddTransaction();
            }
        }catch (ServiceException e){
            e.printStackTrace();
            if(e.getCodigo() == 1){
                mview.showWarning(e.getDescripcion());
            }else if (e.getCodigo() == 2){
                mview.showError(e.getDescripcion());
            }
        }*/


    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {

        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        }
        return null;

    }

    public void getArticulosByOcReceipt(Long poHeaderId, Long receiptNum){

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(mview);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando sigle....");
        progressDoalog.setTitle("Recepciones");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();


        List<MtlSystemItems> articulos;
        mtlSystemItemsViewModel = new ViewModelProvider(mview).get(MtlSystemItemsViewModel.class);
        mtlSystemItemsViewModel.init();

        mtlSystemItemsViewModel.MtlgetAllByOcReceipt(poHeaderId,receiptNum);


       mtlSystemItemsViewModel.getAllByOcReceipt().observe(this.mview, new Observer<List<MtlSystemItems>>() {
           @Override
           public void onChanged(List<MtlSystemItems> mtlSystemItems) {
               progressDoalog.dismiss();
               mview.fillSigle(mtlSystemItems);
           }
       });


        }

    public List<PoLinesAll> getLines(Long inventoryItemId, Long poHeaderId){
        try{
            return this.mService.getLines(inventoryItemId,poHeaderId);
            //mview.fillLines(lineas);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        }
        return null;
    }


}
