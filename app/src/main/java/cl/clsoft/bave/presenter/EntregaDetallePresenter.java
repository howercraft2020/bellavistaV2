package cl.clsoft.bave.presenter;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IREstMtlTransactionsLotsIface;
import cl.clsoft.bave.apis.IRestMtlSerialNumbersInterface;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvHeadersInterfaceEntrega;
import cl.clsoft.bave.apis.IRestRcvShipmentHeaders;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterfaceEntrega;
import cl.clsoft.bave.apis.IRestXXPDA_MTL_SERIAL_NUM_IFACE;
import cl.clsoft.bave.apis.IRestXXPDA_MTL_TRANS_LOTS_IFACE;
import cl.clsoft.bave.apis.IRestXXPDA_RCV_HEADERS_INTERFACE;
import cl.clsoft.bave.apis.IRestXXPDA_RCV_TRANS_INTERFACE;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvHeadersInterfaceEntrega;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.RcvTransactionsInterfaceEntrega;
import cl.clsoft.bave.model.XXPDA_MTL_SERIAL_NUM_IFACE;
import cl.clsoft.bave.model.XXPDA_MTL_TRANS_LOTS_IFACE;
import cl.clsoft.bave.model.XXPDA_RCV_HEADERS_INTERFACE;
import cl.clsoft.bave.model.XXPDA_RCV_TRANS_INTERFACE;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaDetalle;
import cl.clsoft.bave.viewmodel.RcvShipmentHeadersViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntregaDetallePresenter extends BasePresenter {

    private static final String TAG = "EntregaDetalle";
    private ActivityEntregaDetalle mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;



    //VARIABLES REST APIS
    private IRestRcvShipmentHeaders iRestRcvShipmentHeaders;
    private IRestRcvHeadersInterface iRestRcvHeadersInterface;
    private IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    private IRestRcvTransactions iRestRcvTransactions;

    private IRestMtlSerialNumbersInterface iRestMtlSerialNumbersInterface;
    private IREstMtlTransactionsLotsIface irEstMtlTransactionsLotsIface;




    private IRestXXPDA_MTL_SERIAL_NUM_IFACE iRestXXPDA_mtl_serial_num_iface;
    private IRestXXPDA_MTL_TRANS_LOTS_IFACE iRestXXPDA_mtl_trans_lots_iface;
    private IRestXXPDA_RCV_TRANS_INTERFACE iRestXXPDA_rcv_trans_interface;
    private IRestXXPDA_RCV_HEADERS_INTERFACE iRestXXPDA_rcv_headers_interface;

    private IRestRcvHeadersInterfaceEntrega iRestRcvHeadersInterfaceEntrega;
    private IRestRcvTransactionsInterfaceEntrega iRestRcvTransactionsInterfaceEntrega;


    private IRestRcvStatus iRestRcvStatus;



    public EntregaDetallePresenter(@NonNull final ActivityEntregaDetalle view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;


        //Inicializamos iterfaces API REST

        this.iRestRcvHeadersInterfaceEntrega = ApiUtils.getIRestRcvHeadersInterfaceEntrega();
        this.iRestRcvTransactionsInterfaceEntrega = ApiUtils.getIRestRcvTransactionsInterfaceEntrega();

        this.iRestRcvShipmentHeaders = ApiUtils.getIRestRcvShipmentHeaders();
        this.iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterface();
        this.iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();

        this.iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        this.iRestMtlSerialNumbersInterface = ApiUtils.getIRestMtlSerialNumbersInterface();
        this.irEstMtlTransactionsLotsIface = ApiUtils.getIREstMtlTransactionsLotsIface();

        this.iRestXXPDA_mtl_serial_num_iface = ApiUtils.getIRestXXPDA_MTL_SERIAL_NUM_IFACERx();
        this.iRestXXPDA_mtl_trans_lots_iface = ApiUtils.getIRestXXPDA_MTL_TRANS_LOTS_IFACERx();
        this.iRestXXPDA_rcv_trans_interface = ApiUtils.getIRestXXPDA_RCV_TRANS_INTERFACERx();
        this.iRestXXPDA_rcv_headers_interface = ApiUtils.getIRestXXPDA_RCV_HEADERS_INTERFACERx();

        this.iRestRcvStatus = ApiUtils.getIRestRcvStatus();


    }

    public RcvShipmentHeaders getEntrega(Long shipmentHeaderId) {


        try {
            return this.mService.getEntrega(shipmentHeaderId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;



    }


    public List<TransactionsDto> getTransactions(Long shipmentHeaderId) {
        try {
            return this.mService.getTransactionsInterfaceByShipmentHeader(shipmentHeaderId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void closeEntrega(Long shipmentHeaderId){


        // Llamado 1
        iRestRcvShipmentHeaders.get(shipmentHeaderId).enqueue(new Callback<RcvShipmentHeaders>() {
            @Override
            public void onResponse(Call<RcvShipmentHeaders> call, Response<RcvShipmentHeaders> response) {
                Gson gson = new Gson();
                if(response.isSuccessful() == true && response.code()==200){

                    RcvShipmentHeaders rcvShipmentHeaders = response.body();

                    Log.d(TAG,"Llamando iRestRcvShipmentHeaders.get(shipmentHeaderId)"+gson.toJson(rcvShipmentHeaders));

                    // Llamado 2
                    iRestRcvHeadersInterface.get(rcvShipmentHeaders.getHeaderInterfaceId()).enqueue(new Callback<RcvHeadersInterface>() {
                        @Override
                        public void onResponse(Call<RcvHeadersInterface> call, Response<RcvHeadersInterface> response) {
                            if(response.isSuccessful() == true && response.code()==200) {

                                RcvHeadersInterface rcvHeadersInterface = response.body();

                                Log.d(TAG,"Llamando iRestRcvHeadersInterface.get(rcvShipmentHeaders.getHeaderInterfaceId()) "+gson.toJson(rcvHeadersInterface));

                                // Llamado 3
                                iRestRcvTransactionsInterface.getAllByHeader(rcvHeadersInterface.getHeaderInterfaceId()).enqueue(new Callback<List<RcvTransactionsInterface>>() {
                                    @Override
                                    public void onResponse(Call<List<RcvTransactionsInterface>> call, Response<List<RcvTransactionsInterface>> response) {

                                        List<RcvTransactionsInterface> trxs  =  response.body();
                                        Log.d(TAG,"Llamando iRestRcvTransactionsInterface.getAllByHeader(rcvHeadersInterface.getHeaderInterfaceId()) "+gson.toJson(trxs));

                                        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                                        String sysDate = dateFormat.format(new Date());

                                        try {
                                            Date fecha_actual = dateFormat.parse(sysDate);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        RcvHeadersInterfaceEntrega rcvHeadersInterfaceEntrega = new RcvHeadersInterfaceEntrega();
                                        RcvTransactionsInterfaceEntrega rcvTransactionsInterfaceEntrega = new RcvTransactionsInterfaceEntrega();

                                        rcvHeadersInterfaceEntrega.setHeaderInterfaceId(rcvHeadersInterface.getHeaderInterfaceId());
                                        rcvHeadersInterfaceEntrega.setGroupId(rcvHeadersInterface.getGroupId());
                                        rcvHeadersInterfaceEntrega.setProcessingStatusCode(rcvHeadersInterface.getProcessingStatusCode());
                                        rcvHeadersInterfaceEntrega.setReciptSourceCode(rcvHeadersInterface.getReciptSourceCode());
                                        //rcvHeadersInterfaceEntrega.setTransactionType(rcvHeadersInterface.getTransactionType());
                                        rcvHeadersInterfaceEntrega.setTransactionType("NEW");
                                        rcvHeadersInterfaceEntrega.setAutoTransactCode("DELIVER");
                                        //rcvHeadersInterfaceEntrega.setLastUpdateDate(fecha_actual);
                                        rcvHeadersInterfaceEntrega.setLastUpdateBy(rcvHeadersInterface.getLastUpdateBy());
                                        rcvHeadersInterfaceEntrega.setLastUpdateLogin(Long.parseLong("0"));
                                        //rcvHeadersInterfaceEntrega.setCreationDate(new Date());
                                        rcvHeadersInterfaceEntrega.setCreatedBy(rcvHeadersInterface.getCreatedBy());
                                        rcvHeadersInterfaceEntrega.setVendorId(rcvHeadersInterface.getVendorId());
                                        rcvHeadersInterfaceEntrega.setShipToOrganizationId(221L);
                                        //rcvHeadersInterfaceEntrega.setEXPECTED_RECEIPT_DATE(new Date());
                                        //rcvHeadersInterfaceEntrega.setValidationFlag(rcvHeadersInterface.getValidationFlag());
                                        rcvHeadersInterfaceEntrega.setValidationFlag("Y");
                                        //rcvHeadersInterfaceEntrega.setEstado(Long.toString(rcvShipmentHeaders.getPoNumber()));
                                        rcvHeadersInterfaceEntrega.setEstado("PENDING");


                                        Gson gson1 = new Gson();
                                        Log.d(TAG,"Print rcvHeadersInterfaceEntrega :"+gson1.toJson(rcvHeadersInterfaceEntrega));


                                        iRestRcvHeadersInterfaceEntrega.insert(rcvHeadersInterfaceEntrega).enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if(response.isSuccessful() ==true && response.code()==200){
                                                    mView.showSuccess("Paso Headers");
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                mView.showError(t.getMessage());

                                            }
                                        });

                                        for (RcvTransactionsInterface trx : trxs) {


                                            rcvTransactionsInterfaceEntrega.setTransactionInterfaceId(trx.getInterfaceTransactionId());
                                            //rcvTransactionsInterfaceEntrega.setLAST_UPDATE_DATE(new Date());
                                            rcvTransactionsInterfaceEntrega.setLastUpdatedBy(trx.getLastUpdatedBy());
                                            rcvTransactionsInterfaceEntrega.setLastUpdatedBy(trx.getLastUpdatedBy());
                                            rcvTransactionsInterfaceEntrega.setCreatedBy(trx.getCreatedBy());
                                            rcvTransactionsInterfaceEntrega.setTransactionType(trx.getTransactionType());
                                            rcvTransactionsInterfaceEntrega.setProcessingStatusCode(trx.getProcessingStatusCode());
                                            rcvTransactionsInterfaceEntrega.setProcessingModeCode(trx.getProcessingModeCode());
                                            rcvTransactionsInterfaceEntrega.setQuantity(trx.getQuantity());
                                            rcvTransactionsInterfaceEntrega.setUnitOfMeasure(trx.getUnitOfMeasure());
                                            rcvTransactionsInterfaceEntrega.setItemId(trx.getItemId());
                                            rcvTransactionsInterfaceEntrega.setItemDescription(trx.getItemDescription());
                                            rcvTransactionsInterfaceEntrega.setUomCode(trx.getUomCode());
                                            //rcvTransactionsInterfaceEntrega.setEmployeeId(trx.getEmployeeId());
                                            rcvTransactionsInterfaceEntrega.setEmployeeId(0L);
                                            rcvTransactionsInterfaceEntrega.setShipmentHeaderId(trx.getShipmentHeaderId());
                                            rcvTransactionsInterfaceEntrega.setShipmentLineId(trx.getShipmentLineId());
                                            rcvTransactionsInterfaceEntrega.setShipToLocationId(trx.getShipToLocationId());
                                            rcvTransactionsInterfaceEntrega.setShipToLocationId(356L);
                                            rcvTransactionsInterfaceEntrega.setVendorId(trx.getVendorId());
                                            rcvTransactionsInterfaceEntrega.setVendorSiteId(trx.getVendorSiteId());
                                            //rcvTransactionsInterfaceEntrega.setToOrganizationId(trx.getToOrganizationId());
                                            rcvTransactionsInterfaceEntrega.setToOrganizationId(221L);
                                            rcvTransactionsInterfaceEntrega.setSourceDocumentCode(trx.getSourceDocumentCode());
                                            rcvTransactionsInterfaceEntrega.setParentTransactionId(trx.getParentTransactionId());
                                            rcvTransactionsInterfaceEntrega.setPoHeaderId(trx.getPoHeaderId());
                                            rcvTransactionsInterfaceEntrega.setPoLineId(trx.getPoLineId());
                                            rcvTransactionsInterfaceEntrega.setPoLineLocation(trx.getPoLineLocation());
                                            rcvTransactionsInterfaceEntrega.setPoUnitPrice(trx.getPoUnitPrice());
                                            rcvTransactionsInterfaceEntrega.setCurrencyCode(trx.getCurrencyCode());
                                            rcvTransactionsInterfaceEntrega.setCurrencyConversionType(trx.getCurrencyConversionType());
                                            rcvTransactionsInterfaceEntrega.setCurrencyConversionRate(trx.getCurrencyConversionRate());
                                            rcvTransactionsInterfaceEntrega.setCurrencyConversionDate(trx.getCurrencyConversionDate());
                                            rcvTransactionsInterfaceEntrega.setPoDistributionId(trx.getPoDistributionId());
                                            rcvTransactionsInterfaceEntrega.setDestinationTypeCode(trx.getDestinationTypeCode());
                                            rcvTransactionsInterfaceEntrega.setLocationId(trx.getLocationId());
                                            //rcvTransactionsInterfaceEntrega.setDeliverToLocationId(trx.getDeliverToLocationId());
                                            rcvTransactionsInterfaceEntrega.setDeliverToLocationId(356L);
                                            rcvTransactionsInterfaceEntrega.setInspectionStatusCode(trx.getInspectionStatusCode());
                                            rcvTransactionsInterfaceEntrega.setSubinventory(trx.getSubinventory());
                                            rcvTransactionsInterfaceEntrega.setLocatorId(trx.getLocatorId());
                                            rcvTransactionsInterfaceEntrega.setShipmentNum(trx.getShipmentNum());
                                            rcvTransactionsInterfaceEntrega.setLocationId(trx.getLocationId());
                                            rcvTransactionsInterfaceEntrega.setUseMtlLot(trx.getUseMtlLot());
                                            rcvTransactionsInterfaceEntrega.setUseMtlSerial(trx.getUseMtlSerial());

                                            rcvTransactionsInterfaceEntrega.setGroupId(trx.getGroupId());
                                            rcvTransactionsInterfaceEntrega.setTransactionStatusCode(trx.getTransactionStatusCode().isEmpty() ? "null" : trx.getTransactionStatusCode());
                                            rcvTransactionsInterfaceEntrega.setAutoTransactCode("null");
                                            //rcvTransactionsInterfaceEntrega.setAutoTransactCode("DELIVER");
                                            rcvTransactionsInterfaceEntrega.setReceiptSourceCode(trx.getReceiptSourceCode().isEmpty() ? "null" : trx.getReceiptSourceCode());
                                            rcvTransactionsInterfaceEntrega.setValidationFlag(trx.getValidationFlag().isEmpty() ? "null" : trx.getValidationFlag());
                                            rcvTransactionsInterfaceEntrega.setOrgId(82L);
                                            rcvTransactionsInterfaceEntrega.setEstado("PENDING");
                                            rcvTransactionsInterfaceEntrega.setPrimaryQuantity(trx.getPrimaryQuantity());
                                            rcvTransactionsInterfaceEntrega.setPoUnitPrice(trx.getPoUnitPrice());
                                            rcvTransactionsInterfaceEntrega.setAttribute15("N");

                                            Gson gson2 = new Gson();
                                            Log.d(TAG,"Print rcvTransactionsInterfaceEntrega :"+gson1.toJson(rcvTransactionsInterfaceEntrega));
                                            Log.d(TAG,"Print trx :"+gson1.toJson(trx));
                                            Log.d(TAG,"trx.getPrimaryQuantity()"+trx.getPrimaryQuantity());

                                            Log.d(TAG,"trx.getUseMtlSerial()"+trx.getUseMtlSerial());

                                            iRestRcvTransactionsInterfaceEntrega.insert(rcvTransactionsInterfaceEntrega).enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call, Response<Void> response) {
                                                    if(response.isSuccessful() ==true && response.code()==200){
                                                        mView.showSuccess("Paso Trx");
                                                    }

                                                }

                                                @Override
                                                public void onFailure(Call<Void> call, Throwable t) {
                                                       mView.showError(t.getMessage());
                                                }
                                            });
                                        }

                                        iRestRcvTransactions.getAllByShipment(rcvShipmentHeaders.getShipmentHeaderId()).enqueue(new Callback<List<RcvTransactions>>() {
                                            @Override
                                            public void onResponse(Call<List<RcvTransactions>> call, Response<List<RcvTransactions>> response) {
                                                if(response.isSuccessful()==true && response.code()==200){

                                                    iRestRcvStatus.actualiza_estado(8,response.body().get(0).getPoHeaderId(),Long.parseLong(rcvShipmentHeaders.getReceiptNum())).enqueue(new Callback<Void>() {
                                                        @Override
                                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                                            if(response.isSuccessful() ==true && response.code()==200){
                                                                mView.showSuccess("Paso Estado");
                                                            }

                                                        }

                                                        @Override
                                                        public void onFailure(Call<Void> call, Throwable t) {

                                                        }
                                                    });


                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<List<RcvTransactions>> call, Throwable t) {

                                            }
                                        });




                                    }

                                    @Override
                                    public void onFailure(Call<List<RcvTransactionsInterface>> call, Throwable t) {

                                        mView.showError(" List<RcvTransactionsInterface>> "  + " no existe en el sistema");

                                    }

                                    ///

                                });
////FIN 3

                            }
                        }

                        @Override
                        public void onFailure(Call<RcvHeadersInterface> call, Throwable t) {
                            mView.showError("Entrega " + rcvShipmentHeaders.getHeaderInterfaceId() + " no existe en el sistema");
                        }
                    });

                    }
                }
            @Override
            public void onFailure(Call<RcvShipmentHeaders> call, Throwable t) {
                mView.showError("Entrega " + shipmentHeaderId + " no existe en el sistema");
            }
        });


//////FIN NUEVO CODIGO


        /*
        iRestRcvShipmentHeaders.get(shipmentHeaderId).enqueue(new Callback<RcvShipmentHeaders>() {
            @Override
            public void onResponse(Call<RcvShipmentHeaders> call, Response<RcvShipmentHeaders> response) {

            if(response.isSuccessful() == true && response.code()==200){


                if(response.body() == null){

                    mView.showError("Entrega " + shipmentHeaderId + " no existe en el sistema");

                }
                if(response.body()!=null){

                   RcvShipmentHeaders rcvShipmentHeaders = response.body();


                    iRestRcvHeadersInterface.get(rcvShipmentHeaders.getHeaderInterfaceId()).enqueue(new Callback<RcvHeadersInterface>() {
                        @Override
                        public void onResponse(Call<RcvHeadersInterface> call, Response<RcvHeadersInterface> response) {

                            if(response.isSuccessful() == true && response.code()==200){



                                if(response.body() == null){

                                    mView.showError("Headers Interface " + rcvShipmentHeaders.getHeaderInterfaceId() + " no existe en el sistema");

                                }

                                if(response.body()!=null){

                                    RcvHeadersInterface rcvHeadersInterface = response.body();


                                    iRestRcvTransactionsInterface.getAllByHeader(rcvHeadersInterface.getHeaderInterfaceId()).enqueue(new Callback<List<RcvTransactionsInterface>>() {
                                        @Override
                                        public void onResponse(Call<List<RcvTransactionsInterface>> call, Response<List<RcvTransactionsInterface>> response) {

                                            if(response.isSuccessful() == true && response.code()==200) {

                                                if(response.body() == null){

                                                    mView.showError( "No hay transacciones ingresadas para esta entrega");

                                                }
                                                if(response.body()!=null){
                                                    List<RcvTransactionsInterface> trxs  =  response.body();


                                                    // SYSDATE
                                                    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                                                    String sysDate = dateFormat.format(new Date());



                                                    RcvHeadersInterfaceEntrega rcvHeadersInterfaceEntrega = new RcvHeadersInterfaceEntrega();
                                                    RcvTransactionsInterfaceEntrega rcvTransactionsInterfaceEntrega = new RcvTransactionsInterfaceEntrega();

                                                    rcvHeadersInterfaceEntrega.setHeaderInterfaceId(rcvHeadersInterface.getHeaderInterfaceId());
                                                    rcvHeadersInterfaceEntrega.setGroupId(rcvHeadersInterface.getGroupId());
                                                    rcvHeadersInterfaceEntrega.setProcessingStatusCode(rcvHeadersInterface.getProcessingStatusCode());
                                                    rcvHeadersInterfaceEntrega.setReciptSourceCode(rcvHeadersInterface.getReciptSourceCode());
                                                    rcvHeadersInterfaceEntrega.setTransactionType(rcvHeadersInterface.getTransactionType());
                                                    rcvHeadersInterfaceEntrega.setAutoTransactCode("DELIVER");
                                                    //rcvHeadersInterfaceEntrega.setLastUpdateDate(new Date());
                                                    rcvHeadersInterfaceEntrega.setLastUpdateBy(rcvHeadersInterface.getLastUpdateBy());
                                                    rcvHeadersInterfaceEntrega.setLastUpdateLogin(Long.parseLong("0"));
                                                    //rcvHeadersInterfaceEntrega.setCreationDate(new Date());
                                                    rcvHeadersInterfaceEntrega.setCreatedBy(rcvHeadersInterface.getCreatedBy());
                                                    rcvHeadersInterfaceEntrega.setVendorId(rcvHeadersInterface.getVendorId());
                                                    rcvHeadersInterfaceEntrega.setShipToOrganizationId(221L);
                                                    //rcvHeadersInterfaceEntrega.setEXPECTED_RECEIPT_DATE(new Date());
                                                    rcvHeadersInterfaceEntrega.setValidationFlag(rcvHeadersInterface.getValidationFlag());
                                                    rcvHeadersInterfaceEntrega.setEstado(Long.toString(rcvShipmentHeaders.getPoNumber()));



                                                    for (RcvTransactionsInterface trx : trxs) {


                                                        rcvTransactionsInterfaceEntrega.setTransactionInterfaceId(trx.getInterfaceTransactionId());
                                                        //rcvTransactionsInterfaceEntrega.setLAST_UPDATE_DATE(new Date());
                                                        rcvTransactionsInterfaceEntrega.setLastUpdatedBy(trx.getLastUpdatedBy());
                                                        rcvTransactionsInterfaceEntrega.setLastUpdatedBy(trx.getLastUpdatedBy());
                                                        rcvTransactionsInterfaceEntrega.setCreatedBy(trx.getCreatedBy());
                                                        rcvTransactionsInterfaceEntrega.setTransactionType(trx.getTransactionType());
                                                        rcvTransactionsInterfaceEntrega.setProcessingStatusCode(trx.getProcessingStatusCode());
                                                        rcvTransactionsInterfaceEntrega.setProcessingModeCode(trx.getProcessingModeCode());
                                                        rcvTransactionsInterfaceEntrega.setQuantity(trx.getQuantity());
                                                        rcvTransactionsInterfaceEntrega.setUnitOfMeasure(trx.getUnitOfMeasure());
                                                        rcvTransactionsInterfaceEntrega.setItemId(trx.getItemId());
                                                        rcvTransactionsInterfaceEntrega.setItemDescription(trx.getItemDescription());
                                                        rcvTransactionsInterfaceEntrega.setUomCode(trx.getUomCode());
                                                        rcvTransactionsInterfaceEntrega.setEmployeeId(trx.getEmployeeId());






                                                        Boolean isLote = false;
                                                        Boolean isSerie = false;

                                                        //SI es de tipo Lote
                                                        if(isLote) {
                                                            irEstMtlTransactionsLotsIface.getByInterfaceTransactionId(trx.getInterfaceTransactionId()).enqueue(new Callback<MtlTransactionsLotsIface>() {
                                                                @Override
                                                                public void onResponse(Call<MtlTransactionsLotsIface> call, Response<MtlTransactionsLotsIface> response) {
                                                                    if (response.isSuccessful() == true && response.code() == 200 && response.body() != null) {

                                                                        MtlTransactionsLotsIface lote = response.body();

                                                                        //xxpda_mtl_trans_lots_iface.setTRANSACTION_INTERFACE_ID(lote.getTransactionInterfaceId());

                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<MtlTransactionsLotsIface> call, Throwable t) {
                                                                    mView.showError(t.getMessage());
                                                                }
                                                            });
                                                        }
                                                        //SI es de tipo Serie


                                                        if(isSerie) {
                                                            iRestMtlSerialNumbersInterface.getAllByInterfaceTransactionId(trx.getInterfaceTransactionId()).enqueue(new Callback<List<MtlSerialNumbersInterface>>() {
                                                                @Override
                                                                public void onResponse(Call<List<MtlSerialNumbersInterface>> call, Response<List<MtlSerialNumbersInterface>> response) {
                                                                    if (response.isSuccessful() == true && response.code() == 200 && response.body() != null) {

                                                                        List<MtlSerialNumbersInterface> series = response.body();

                                                                        for (MtlSerialNumbersInterface serie : series) {


                                                                            //xxpdaMtlSerialNumIface.setTRANSACTION_INTERFACE_ID(serie.getTransactionInterfaceId());

                                                                        }

                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<List<MtlSerialNumbersInterface>> call, Throwable t) {
                                                                    mView.showError(t.getMessage());
                                                                }
                                                            });
                                                        }

                                                        //Insertamos Objetos en la base de datos

                                                        Gson gson = new Gson();


                                                        Log.d("JSON", ""+gson.toJson(rcvHeadersInterface));

                                                        //Log.d("JSON", ""+gson.toJson(xxpda_rcv_headers_interface));

                                                        iRestRcvHeadersInterfaceEntrega.insert(rcvHeadersInterfaceEntrega).enqueue(new Callback<Void>() {
                                                            @Override
                                                            public void onResponse(Call<Void> call, Response<Void> response) {


                                                                Log.d(TAG, ""+gson.toJson(rcvHeadersInterfaceEntrega));

                                                                mView.showSuccess("Paso Headers");

                                                                iRestRcvTransactionsInterfaceEntrega.insert(rcvTransactionsInterfaceEntrega).enqueue(new Callback<Void>() {
                                                                    @Override
                                                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                                                        Log.d(TAG, ""+gson.toJson(rcvTransactionsInterfaceEntrega));
                                                                        mView.showSuccess("Paso Trx");

                                                                        iRestRcvStatus.actualiza_estado(8,Long.parseLong("721093"),Long.parseLong("5570")).enqueue(new Callback<Void>() {
                                                                            @Override
                                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                                mView.showSuccess("Paso Estado");

                                                                            }

                                                                            @Override
                                                                            public void onFailure(Call<Void> call, Throwable t) {

                                                                            }
                                                                        });


                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<Void> call, Throwable t) {

                                                                    }
                                                                });


                                                            }

                                                            @Override
                                                            public void onFailure(Call<Void> call, Throwable t) {

                                                            }
                                                        });


                                                    }

                                                    Observable<Void> Insert_Headers_observable = iRestRcvHeadersInterfaceEntrega.insertRx(rcvHeadersInterfaceEntrega);
                                                    Observable<Void> Insert_Trx_observable = iRestRcvTransactionsInterfaceEntrega.insertRx(rcvTransactionsInterfaceEntrega);


                                                    //String PO_HEADER_ID = rcvHeadersInterface.getGroupId().toString().substring(rcvHeadersInterface.getGroupId().toString().lastIndexOf(rcvHeadersInterface.getReciptNum().toString())+1);

                                                    Observable<Void> INSERT_RCV_STATUS = iRestRcvStatus.actualiza_estadoRx(8,Long.parseLong("721093"),rcvHeadersInterface.getReciptNum());

                                                            Observable<Void> result = Observable.zip(
                                                                    Insert_Headers_observable,
                                                                    Insert_Trx_observable,
                                                                    INSERT_RCV_STATUS,
                                                            new Function3<Void, Void, Void, Void>() {
                                                                @NonNull
                                                                @Override
                                                                public Void apply(@NonNull Void unused, @NonNull Void unused2, @NonNull Void unused3) throws Exception {
                                                                    return null;
                                                                }
                                                            });

                                                    result.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Void>() {
                                                        @Override
                                                        public void onSubscribe(@NonNull Disposable d) {

                                                        }

                                                        @Override
                                                        public void onNext(@NonNull Void unused) {

                                                        }

                                                        @Override
                                                        public void onError(@NonNull Throwable e) {
                                                            mView.showError("Error al guardar: "+e.getMessage());
                                                        }

                                                        @Override
                                                        public void onComplete() {
                                                            mView.showSuccess("Entrega Cerrada");
                                                        }
                                                    });
/////FIN



                                                }


                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<RcvTransactionsInterface>> call, Throwable t) {
                                            mView.showError(t.getMessage());
                                        }
                                    });


                                }


                            }


                        }

                        @Override
                        public void onFailure(Call<RcvHeadersInterface> call, Throwable t) {

                            mView.showError(t.getMessage());

                        }
                    });


                }


            }


            }

            @Override
            public void onFailure(Call<RcvShipmentHeaders> call, Throwable t) {

                mView.showError(t.getMessage());

            }
        });

*/



    }


}
