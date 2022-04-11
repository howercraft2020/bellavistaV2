package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IREstMtlTransactionsLotsIface;
import cl.clsoft.bave.apis.IRestLocalizador;
import cl.clsoft.bave.apis.IRestMtlSerialNumbersInterface;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestMtlTransactionLotNumbers;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvShipmentHeaders;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.apis.IRestSubinventario;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosRecepcion;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaAgregar;
import cl.clsoft.bave.view.ActivityEntregaAgregarResumen;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntregaAgregarResumenPresenter extends BasePresenter {

    private static final String TAG = "EntregaAgregar";
    private ActivityEntregaAgregarResumen mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;




    //Variables REST API
    IRestRcvShipmentHeaders iRestRcvShipmentHeaders;
    IRestRcvTransactions iRestRcvTransactions;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestSubinventario iRestSubinventario;
    IRestLocalizador iRestLocalizador;

    IRestRcvHeadersInterface iRestRcvHeadersInterface;
    IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    IRestRcvStatus iRestRcvStatus;


    IREstMtlTransactionsLotsIface irEstMtlTransactionsLotsIface;
    IRestMtlSerialNumbersInterface iRestMtlSerialNumbersInterface;

    public EntregaAgregarResumenPresenter(final ActivityEntregaAgregarResumen view, @NonNull final TaskExecutor taskExecutor, final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;

        this.iRestRcvShipmentHeaders = ApiUtils.getIRestRcvShipmentHeadersRx();
        this.iRestRcvTransactions = ApiUtils.getIRestRcvTransactionsRx();
        this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        this.iRestSubinventario = ApiUtils.getIRestSubinventarioRx();
        this.iRestLocalizador = ApiUtils.getIRestLocalizadorRx();

        this.iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterface();
        this.iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();
        this.iRestRcvStatus = ApiUtils.getIRestRcvStatus();
        this.irEstMtlTransactionsLotsIface = ApiUtils.getIREstMtlTransactionsLotsIface();
        this.iRestMtlSerialNumbersInterface = ApiUtils.getIRestMtlSerialNumbersInterface();



    }

    public RcvTransactions getTransactionById(Long transactionId) {
        try {
            return this.mService.getTransactionById(transactionId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) {
        try {
            return this.mService.getMtlSystemItemsById(inventoryItemId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode,
                                         String locatorCode, String lote, String loteProveedor, String vencimiento, String categoria, String atributo1,
                                         String atributo2, String atributo3, List<String> series, Double cantidad) {



        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface");
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::shipmentHeaderId: " + shipmentHeaderId);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::transactionId: " + transactionId);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::subinventoryCode: " + subinventoryCode);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::locatorCode: " + locatorCode);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::lote: " + lote);
        Log.d(TAG, "EntregaServiceImpl::addTransacctionInterface::loteProveedor: " + loteProveedor);






        Observable<RcvShipmentHeaders> rcvShipmentHeadersObservable = iRestRcvShipmentHeaders.getRx(shipmentHeaderId);
        Observable<RcvTransactions> rcvTransactionsObservable = iRestRcvTransactions.getRx(transactionId);
        Observable<Subinventario> subinventarioObservable = iRestSubinventario.getByCodigoRx(subinventoryCode);
        Observable<Localizador> localizadorObservable = iRestLocalizador.getByCodigoRx(locatorCode);



        Observable<List<Object>> result = Observable.zip(rcvShipmentHeadersObservable,
                rcvTransactionsObservable,
                subinventarioObservable,
                localizadorObservable,
                new Function4<RcvShipmentHeaders, RcvTransactions, Subinventario,Localizador, List<Object>>() {
                    @NonNull
                    @Override
                    public List<Object> apply(@NonNull RcvShipmentHeaders rcvShipmentHeaders, @NonNull RcvTransactions rcvTransactions, @NonNull Subinventario Subinventario,@NonNull Localizador localizador) throws Exception {

                        List<Object> r = new ArrayList<>();
                        r.add(rcvShipmentHeaders);
                        r.add(rcvTransactions);
                        r.add(Subinventario);
                        r.add(localizador);


                        return r;


                    }
                });


        result.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Object>>() {

            List<Object> r = new ArrayList<>();
            RcvTransactionsInterface rcvTransactionsInterface;

            boolean isControlLote = false;
            boolean isControlSerie = false;
            boolean isControlVencimiento = false;
            Long transactionInterfaceId;
            Long interfaceTransactionId;

            // Fecha de hoy
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
            String sysDate = dateFormat.format(new Date());


            RcvShipmentHeaders rcvShipmentHeaders;
            RcvTransactions rcvTransactions;
            Subinventario subinventario;
            Localizador localizador;
            MtlSystemItems mtlSystemItems;




                                                                                                             @Override
                                                                                                             public void onSubscribe(@NonNull Disposable d) {

                                                                                                             }

                                                                                                             @Override
                                                                                                             public void onNext(@NonNull List<Object> objects) {
                                                                                                                 r = objects;
                                                                                                             }

                                                                                                             @Override
                                                                                                             public void onError(@NonNull Throwable e) {
                                                                                                                 mView.showError(e.getMessage());
                                                                                                             }

                                                                                                             @Override
                                                                                                             public void onComplete() {

                                                                                                                 Log.d(TAG, "COMPLETADO REST API OBSERVABLE CALLS" );
                                                                                                                 rcvShipmentHeaders = RcvShipmentHeaders.class.cast(r.get(0));
                                                                                                                 rcvTransactions = RcvTransactions.class.cast(r.get(1));
                                                                                                                 subinventario = Subinventario.class.cast(r.get(2));
                                                                                                                 localizador = Localizador.class.cast(r.get(3));

                                                                                                                 Gson gson = new Gson();


                                                                                                                 Log.d(TAG, "rcvShipmentHeaders: " + gson.toJson(rcvShipmentHeaders));
                                                                                                                 Log.d(TAG, "rcvTransactions: " + gson.toJson(rcvTransactions));
                                                                                                                 Log.d(TAG, "subinventario: " + gson.toJson(subinventario));
                                                                                                                 Log.d(TAG, "localizador: " + gson.toJson(localizador));


                                                                                                                 iRestMtlSystemItems.get(rcvTransactions.getItemId()).enqueue(new Callback<MtlSystemItems>() {
                                                                                                                     @Override
                                                                                                                     public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {

                                                                                                                         if(response.isSuccessful()==true && response.code()==200 && response.body()!=null) {
                                                                                                                             mtlSystemItems = response.body();

                                                                                                                             if (mtlSystemItems.getLotControlCode().equalsIgnoreCase("2")) {
                                                                                                                                 isControlLote = true;
                                                                                                                             }
                                                                                                                             if (mtlSystemItems.getSerialNumberControlCode().equalsIgnoreCase("2") || mtlSystemItems.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                                                                                                                                 isControlSerie = true;
                                                                                                                             }
                                                                                                                             if (mtlSystemItems.getShelfLifeCode().equalsIgnoreCase("2") || mtlSystemItems.getShelfLifeCode().equalsIgnoreCase("4")) {
                                                                                                                                 isControlVencimiento = true;
                                                                                                                             }


                                                                                                                             // Validaciones


                                                                                                                             if (cantidad.longValue() > rcvTransactions.getQuantity().longValue()) {
                                                                                                                                 mView.showError("la cantidad es mayor a lo especificado " + rcvTransactions.getQuantity());

                                                                                                                             }

                                                                                                                             if (isControlLote && lote == null) {
                                                                                                                                 mView.showError("Debe indicar el lote");
                                                                                                                             }

                                                                                                                             if (isControlVencimiento && vencimiento == null) {

                                                                                                                                 mView.showError("Debe indicar el vencimiento");
                                                                                                                             }

                                                                                                                             if (isControlSerie && series == null) {


                                                                                                                                 mView.showError("Debe indicar las series");
                                                                                                                             }

                                                                                                                             if (isControlSerie && series.size() == 0) {
                                                                                                                                 mView.showError("Debe indicar las series");
                                                                                                                             }

                                                                                                                             if (isControlSerie && series.size() < cantidad.intValue()) {
                                                                                                                                 mView.showError("Faltan series");
                                                                                                                             }


                                                                                                                             else {
                                                                                                                             ///INSERCION DE TABLAS

                                                                                                                             iRestRcvHeadersInterface.existe(rcvShipmentHeaders.getHeaderInterfaceId()).enqueue(new Callback<Long>() {
                                                                                                                                 @Override
                                                                                                                                 public void onResponse(Call<Long> call, Response<Long> response) {
                                                                                                                                     RcvHeadersInterface rcvHeadersInterface;


                                                                                                                                     if (response.isSuccessful() == true && response.code() == 200) {

                                                                                                                                         Log.d(TAG, "Resultado Existe RcvHeadersInterface: " + response.body().intValue());

                                                                                                                                         if (response.body().intValue() == 0) {

                                                                                                                                             rcvHeadersInterface = new RcvHeadersInterface();

                                                                                                                                             rcvHeadersInterface.setHeaderInterfaceId(rcvShipmentHeaders.getHeaderInterfaceId());
                                                                                                                                             rcvHeadersInterface.setGroupId(rcvShipmentHeaders.getGroupId());
                                                                                                                                             rcvHeadersInterface.setProcessingStatusCode("PENDING");
                                                                                                                                             rcvHeadersInterface.setReciptSourceCode("VENDOR");
                                                                                                                                             rcvHeadersInterface.setTransactionType("NEW");
                                                                                                                                             rcvHeadersInterface.setAutoTransactCode("DELIVER");
                                                                                                                                             rcvHeadersInterface.setLastUpdateDate(sysDate);
                                                                                                                                             rcvHeadersInterface.setLastUpdateBy(rcvShipmentHeaders.getUserId());
                                                                                                                                             //rcvHeadersInterface.setla
                                                                                                                                             rcvHeadersInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                             rcvHeadersInterface.setVendorId(rcvShipmentHeaders.getVendorId());
                                                                                                                                             rcvHeadersInterface.setShipToOrganizationCode(rcvShipmentHeaders.getShipToOrgId().toString());
                                                                                                                                             //rcvHeadersInterface.sete
                                                                                                                                             rcvHeadersInterface.setValidationFlag("Y");


                                                                                                                                             iRestRcvHeadersInterface.insert(rcvHeadersInterface).enqueue(new Callback<Void>() {
                                                                                                                                                 @Override
                                                                                                                                                 public void onResponse(Call<Void> call, Response<Void> response) {
                                                                                                                                                     if (response.isSuccessful() == true && response.code() == 200) {
                                                                                                                                                         Log.d(TAG, "Inserción Correcta en RcvHeadersInterface");

                                                                                                                                                     }
                                                                                                                                                 }

                                                                                                                                                 @Override
                                                                                                                                                 public void onFailure(Call<Void> call, Throwable t) {
                                                                                                                                                     Log.d(TAG, "Fallo Llamando iRestRcvHeadersInterface.insert: " + t.getMessage());
                                                                                                                                                 }
                                                                                                                                             });


                                                                                                                                             // Crea RCV_TRANSACTIONS_INTERFACE
                                                                                                                                             interfaceTransactionId = rcvShipmentHeaders.getInterfaceTransactionId() + rcvTransactions.getLineNum() - 1;
                                                                                                                                             rcvTransactionsInterface = new RcvTransactionsInterface();
                                                                                                                                             rcvTransactionsInterface.setInterfaceTransactionId(interfaceTransactionId);
                                                                                                                                             rcvTransactionsInterface.setLastUpdatedDate(sysDate);
                                                                                                                                             rcvTransactionsInterface.setLastUpdatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                             rcvTransactionsInterface.setCreationDate(sysDate);
                                                                                                                                             rcvTransactionsInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                             rcvTransactionsInterface.setTransactionType("DELIVER");
                                                                                                                                             rcvTransactionsInterface.setTransactionDate(sysDate);
                                                                                                                                             rcvTransactionsInterface.setProcessingStatusCode("PENDING");
                                                                                                                                             rcvTransactionsInterface.setProcessingModeCode("BATCH");
                                                                                                                                             rcvTransactionsInterface.setQuantity(cantidad);
                                                                                                                                             rcvTransactionsInterface.setUnitOfMeasure(rcvTransactions.getUnitOfMeasure());
                                                                                                                                             rcvTransactionsInterface.setItemId(mtlSystemItems.getInventoryItemId());
                                                                                                                                             rcvTransactionsInterface.setItemDescription(mtlSystemItems.getDescription());
                                                                                                                                             rcvTransactionsInterface.setUomCode(mtlSystemItems.getPrimaryUomCode());
                                                                                                                                             rcvTransactionsInterface.setEmployeeId(rcvShipmentHeaders.getEmployeeId());
                                                                                                                                             rcvTransactionsInterface.setShipmentHeaderId(rcvTransactions.getShipmentHeaderId());
                                                                                                                                             rcvTransactionsInterface.setShipmentLineId(rcvTransactions.getShipmentLineId());
                                                                                                                                             rcvTransactionsInterface.setShipToLocationId(248L);
                                                                                                                                             rcvTransactionsInterface.setVendorId(rcvTransactions.getVendorId());
                                                                                                                                             rcvTransactionsInterface.setVendorSiteId(rcvTransactions.getVendorSiteId());
                                                                                                                                             rcvTransactionsInterface.setToOrganizationId(288L);
                                                                                                                                             rcvTransactionsInterface.setSourceDocumentCode("PO");
                                                                                                                                             rcvTransactionsInterface.setParentTransactionId(rcvTransactions.getTransactionId());
                                                                                                                                             rcvTransactionsInterface.setPoHeaderId(rcvTransactions.getPoHeaderId());
                                                                                                                                             rcvTransactionsInterface.setPoLineId(rcvTransactions.getPoLineId());
                                                                                                                                             rcvTransactionsInterface.setPoLineLocation(rcvTransactions.getPoLineLocationId());
                                                                                                                                             rcvTransactionsInterface.setPoUnitPrice(rcvTransactions.getPoUnitPrice());
                                                                                                                                             rcvTransactionsInterface.setCurrencyCode(rcvTransactions.getCurrencyCode());
                                                                                                                                             rcvTransactionsInterface.setCurrencyConversionType(rcvTransactions.getCurrencyConversionType());
                                                                                                                                             rcvTransactionsInterface.setCurrencyConversionRate(rcvTransactions.getCurrencyConversionRate());
                                                                                                                                             rcvTransactionsInterface.setCurrencyConversionDate(rcvTransactions.getCurrencyConversionDate());
                                                                                                                                             rcvTransactionsInterface.setPoDistributionId(rcvTransactions.getPoDistributionId());
                                                                                                                                             rcvTransactionsInterface.setDestinationTypeCode("INVENTORY");
                                                                                                                                             rcvTransactionsInterface.setLocationId(rcvTransactions.getLocationId());
                                                                                                                                             rcvTransactionsInterface.setDeliverToLocationId(248L);
                                                                                                                                             rcvTransactionsInterface.setInspectionStatusCode("NOT INSPECTED");
                                                                                                                                             rcvTransactionsInterface.setSubinventory(subinventario.getCodSubinventario());
                                                                                                                                             rcvTransactionsInterface.setLocatorId(localizador != null ? localizador.getIdLocalizador() : null);
                                                                                                                                             rcvTransactionsInterface.setShipmentNum(rcvShipmentHeaders.getShipmentNum());
                                                                                                                                             rcvTransactionsInterface.setPrimaryQuantity(rcvTransactions.getQuantity());
                                                                                                                                             if (isControlLote)
                                                                                                                                                 rcvTransactionsInterface.setUseMtlLot(1L);
                                                                                                                                             else
                                                                                                                                                 rcvTransactionsInterface.setUseMtlLot(0L);
                                                                                                                                             if (isControlSerie)
                                                                                                                                                 rcvTransactionsInterface.setUseMtlSerial(1L);
                                                                                                                                             else
                                                                                                                                                 rcvTransactionsInterface.setUseMtlSerial(0L);
                                                                                                                                             rcvTransactionsInterface.setGroupId(rcvShipmentHeaders.getGroupId());
                                                                                                                                             rcvTransactionsInterface.setTransactionStatusCode("PENDING");
                                                                                                                                             rcvTransactionsInterface.setReceiptSourceCode("VENDOR");
                                                                                                                                             rcvTransactionsInterface.setValidationFlag("Y");
                                                                                                                                             rcvTransactionsInterface.setOrgId(rcvTransactions.getOrganizationId());
                                                                                                                                             rcvTransactionsInterface.setOrgId(82L);
                                                                                                                                             rcvTransactionsInterface.setHeaderInterfaceId(rcvHeadersInterface.getHeaderInterfaceId());
                                                                                                                                             rcvTransactionsInterface.setSegment1(mtlSystemItems.getSegment1());

                                                                                                                                             Gson gson1 = new Gson();

                                                                                                                                             Log.d(TAG, gson1.toJson(rcvTransactionsInterface));

                                                                                                                                             iRestRcvTransactionsInterface.insert(rcvTransactionsInterface).enqueue(new Callback<Void>() {
                                                                                                                                                 @Override
                                                                                                                                                 public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                                                                     if (response.isSuccessful() == true && response.code() == 200) {
                                                                                                                                                         Log.d(TAG, "Inserción Correcta en RcvTransactionsInterface");


                                                                                                                                                         Log.d(TAG, "PO_HEADER_ID: " + rcvTransactions.getPoHeaderId());
                                                                                                                                                         Log.d(TAG, "GROUP_ID: " + rcvShipmentHeaders.getReceiptNum());


                                                                                                                                                         iRestRcvStatus.actualiza_estado(7, rcvTransactions.getPoHeaderId(), Long.parseLong(rcvShipmentHeaders.getReceiptNum())).enqueue(new Callback<Void>() {
                                                                                                                                                             @Override
                                                                                                                                                             public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                                                                                 if (response.isSuccessful() == true && response.code() == 200) {
                                                                                                                                                                     Log.d(TAG, "Estado Actualizado a 7 en RCV_ESTATUS");
                                                                                                                                                                 }

                                                                                                                                                             }

                                                                                                                                                             @Override
                                                                                                                                                             public void onFailure(Call<Void> call, Throwable t) {
                                                                                                                                                                 Log.d(TAG, "Error al actualizar RCV_ESTATUS " + t.getMessage());

                                                                                                                                                             }
                                                                                                                                                         });


                                                                                                                                                         Log.d(TAG, "isControlLote  " + isControlLote);
                                                                                                                                                         // Crea Lote
                                                                                                                                                         if (isControlLote) {

                                                                                                                                                             //irEstMtlTransactionsLotsIface.


                                                                                                                                                             Log.d(TAG, "Agregando Lote " + transactionInterfaceId);
                                                                                                                                                             MtlTransactionsLotsIface mtlTransactionsLotsIface = new MtlTransactionsLotsIface();
                                                                                                                                                             mtlTransactionsLotsIface.setLastUpdateDate(sysDate);
                                                                                                                                                             mtlTransactionsLotsIface.setLastUpdateBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                             mtlTransactionsLotsIface.setCreationDate(sysDate);
                                                                                                                                                             mtlTransactionsLotsIface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                             mtlTransactionsLotsIface.setLastUpdateLogin(-1L);
                                                                                                                                                             mtlTransactionsLotsIface.setLotNumber(lote);
                                                                                                                                                             mtlTransactionsLotsIface.setTransactionQuantity(cantidad);
                                                                                                                                                             mtlTransactionsLotsIface.setPrimaryQuantity(cantidad);
                                                                                                                                                             mtlTransactionsLotsIface.setProductCode("RCV");
                                                                                                                                                             mtlTransactionsLotsIface.setProductTransactionId(interfaceTransactionId);
                                                                                                                                                             mtlTransactionsLotsIface.setSupplierLotNumber(loteProveedor);
                                                                                                                                                             mtlTransactionsLotsIface.setLotExpirationDate(vencimiento);
                                                                                                                                                             mtlTransactionsLotsIface.setAttributeCategory(categoria);
                                                                                                                                                             mtlTransactionsLotsIface.setAttrubute1(atributo1);
                                                                                                                                                             mtlTransactionsLotsIface.setAttrubute2(atributo2);
                                                                                                                                                             mtlTransactionsLotsIface.setAttrubute3(atributo3);
                                                                                                                                                             //mtlTransactionLotsInterfaceDao.insert(mtlTransactionsLotsIface);

                                                                                                                                                             irEstMtlTransactionsLotsIface.insert(mtlTransactionsLotsIface).enqueue(new Callback<Void>() {
                                                                                                                                                                 @Override
                                                                                                                                                                 public void onResponse(Call<Void> call, Response<Void> response) {
                                                                                                                                                                     if (response.isSuccessful() == true && response.code() == 200) {

                                                                                                                                                                         Log.d(TAG, "Inserción en mtlTransactionsLotsIface correcto");

                                                                                                                                                                     }
                                                                                                                                                                 }

                                                                                                                                                                 @Override
                                                                                                                                                                 public void onFailure(Call<Void> call, Throwable t) {

                                                                                                                                                                     mView.showError("Error en irEstMtlTransactionsLotsIface.insert " + t.getMessage());

                                                                                                                                                                 }
                                                                                                                                                             });
                                                                                                                                                         }

                                                                                                                                                         // Crea Series

                                                                                                                                                         Log.d(TAG, "isControlSerie  " + isControlSerie);
                                                                                                                                                         if (isControlSerie) {
                                                                                                                                                             int i = 0;

                                                                                                                                                             for (String serie : series) {


                                                                                                                                                                 Log.d(TAG, "Agregando Serie " + transactionInterfaceId);
                                                                                                                                                                 MtlSerialNumbersInterface mtlSerialNumbersInterface = new MtlSerialNumbersInterface();
                                                                                                                                                                 mtlSerialNumbersInterface.setLastUpdateDate(sysDate);
                                                                                                                                                                 mtlSerialNumbersInterface.setLastUpdatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                                 mtlSerialNumbersInterface.setCreationDate(sysDate);
                                                                                                                                                                 mtlSerialNumbersInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                                 mtlSerialNumbersInterface.setLastUpdateLogin(-1L);
                                                                                                                                                                 mtlSerialNumbersInterface.setFmSerialNumber(serie);
                                                                                                                                                                 mtlSerialNumbersInterface.setToSerialNumber(serie);
                                                                                                                                                                 mtlSerialNumbersInterface.setProductCode("RCV");
                                                                                                                                                                 mtlSerialNumbersInterface.setProductTransactionId(interfaceTransactionId);

                                                                                                                                                                 iRestMtlSerialNumbersInterface.insert(mtlSerialNumbersInterface).enqueue(new Callback<Void>() {
                                                                                                                                                                     @Override
                                                                                                                                                                     public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                                                                                         if (response.isSuccessful() == true && response.code() == 200) {

                                                                                                                                                                             Log.d(TAG, "Inserción en mtlSerialNumbersInterface correcto");

                                                                                                                                                                         }

                                                                                                                                                                     }

                                                                                                                                                                     @Override
                                                                                                                                                                     public void onFailure(Call<Void> call, Throwable t) {

                                                                                                                                                                         mView.showError("Error en iRestMtlSerialNumbersInterface.insert " + t.getMessage());

                                                                                                                                                                     }
                                                                                                                                                                 });

                                                                                                                                                             }

                                                                                                                                                         }


                                                                                                                                                         mView.resultadoOkAddTransaction();

                                                                                                                                                     }


                                                                                                                                                 }

                                                                                                                                                 @Override
                                                                                                                                                 public void onFailure(Call<Void> call, Throwable t) {
                                                                                                                                                     Log.d(TAG, "Fallo Llamando iRestRcvTransactionsInterface.insert: " + t.getMessage());
                                                                                                                                                 }
                                                                                                                                             });


                                                                                                                                         }


                                                                                                                                         if (response.body().intValue() > 0) {


                                                                                                                                             iRestRcvHeadersInterface.get(rcvShipmentHeaders.getHeaderInterfaceId()).enqueue(new Callback<RcvHeadersInterface>() {
                                                                                                                                                 @Override
                                                                                                                                                 public void onResponse(Call<RcvHeadersInterface> call, Response<RcvHeadersInterface> response) {


                                                                                                                                                     if (response.isSuccessful() == true && response.code() == 200) {

                                                                                                                                                         RcvHeadersInterface rcvHeadersInterface = response.body();

                                                                                                                                                         // Crea RCV_TRANSACTIONS_INTERFACE
                                                                                                                                                         interfaceTransactionId = rcvShipmentHeaders.getInterfaceTransactionId() + rcvTransactions.getLineNum() - 1;
                                                                                                                                                         rcvTransactionsInterface = new RcvTransactionsInterface();
                                                                                                                                                         rcvTransactionsInterface.setInterfaceTransactionId(interfaceTransactionId);
                                                                                                                                                         rcvTransactionsInterface.setLastUpdatedDate(sysDate);
                                                                                                                                                         rcvTransactionsInterface.setLastUpdatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                         rcvTransactionsInterface.setCreationDate(sysDate);
                                                                                                                                                         rcvTransactionsInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                         rcvTransactionsInterface.setTransactionType("DELIVER");
                                                                                                                                                         rcvTransactionsInterface.setTransactionDate(sysDate);
                                                                                                                                                         rcvTransactionsInterface.setProcessingStatusCode("PENDING");
                                                                                                                                                         rcvTransactionsInterface.setProcessingModeCode("BATCH");
                                                                                                                                                         rcvTransactionsInterface.setQuantity(cantidad);
                                                                                                                                                         rcvTransactionsInterface.setUnitOfMeasure(rcvTransactions.getUnitOfMeasure());
                                                                                                                                                         rcvTransactionsInterface.setItemId(mtlSystemItems.getInventoryItemId());
                                                                                                                                                         rcvTransactionsInterface.setItemDescription(mtlSystemItems.getDescription());
                                                                                                                                                         rcvTransactionsInterface.setUomCode(mtlSystemItems.getPrimaryUomCode());
                                                                                                                                                         rcvTransactionsInterface.setEmployeeId(rcvShipmentHeaders.getEmployeeId());
                                                                                                                                                         rcvTransactionsInterface.setShipmentHeaderId(rcvTransactions.getShipmentHeaderId());
                                                                                                                                                         rcvTransactionsInterface.setShipmentLineId(rcvTransactions.getShipmentLineId());
                                                                                                                                                         rcvTransactionsInterface.setShipToLocationId(248L);
                                                                                                                                                         rcvTransactionsInterface.setVendorId(rcvTransactions.getVendorId());
                                                                                                                                                         rcvTransactionsInterface.setVendorSiteId(rcvTransactions.getVendorSiteId());
                                                                                                                                                         rcvTransactionsInterface.setToOrganizationId(288L);
                                                                                                                                                         rcvTransactionsInterface.setSourceDocumentCode("PO");
                                                                                                                                                         rcvTransactionsInterface.setParentTransactionId(rcvTransactions.getTransactionId());
                                                                                                                                                         rcvTransactionsInterface.setPoHeaderId(rcvTransactions.getPoHeaderId());
                                                                                                                                                         rcvTransactionsInterface.setPoLineId(rcvTransactions.getPoLineId());
                                                                                                                                                         rcvTransactionsInterface.setPoLineLocation(rcvTransactions.getPoLineLocationId());
                                                                                                                                                         rcvTransactionsInterface.setPoUnitPrice(rcvTransactions.getPoUnitPrice());
                                                                                                                                                         rcvTransactionsInterface.setCurrencyCode(rcvTransactions.getCurrencyCode());
                                                                                                                                                         rcvTransactionsInterface.setCurrencyConversionType(rcvTransactions.getCurrencyConversionType());
                                                                                                                                                         rcvTransactionsInterface.setCurrencyConversionRate(rcvTransactions.getCurrencyConversionRate());
                                                                                                                                                         rcvTransactionsInterface.setCurrencyConversionDate(rcvTransactions.getCurrencyConversionDate());
                                                                                                                                                         rcvTransactionsInterface.setPoDistributionId(rcvTransactions.getPoDistributionId());
                                                                                                                                                         rcvTransactionsInterface.setDestinationTypeCode("INVENTORY");
                                                                                                                                                         rcvTransactionsInterface.setLocationId(rcvTransactions.getLocationId());
                                                                                                                                                         rcvTransactionsInterface.setDeliverToLocationId(248L);
                                                                                                                                                         rcvTransactionsInterface.setInspectionStatusCode("NOT INSPECTED");
                                                                                                                                                         rcvTransactionsInterface.setSubinventory(subinventario.getCodSubinventario());
                                                                                                                                                         rcvTransactionsInterface.setLocatorId(localizador != null ? localizador.getIdLocalizador() : null);
                                                                                                                                                         rcvTransactionsInterface.setShipmentNum(rcvShipmentHeaders.getShipmentNum());
                                                                                                                                                         rcvTransactionsInterface.setPrimaryQuantity(rcvTransactions.getQuantity());
                                                                                                                                                         if (isControlLote)
                                                                                                                                                             rcvTransactionsInterface.setUseMtlLot(1L);
                                                                                                                                                         else
                                                                                                                                                             rcvTransactionsInterface.setUseMtlLot(0L);
                                                                                                                                                         if (isControlSerie)
                                                                                                                                                             rcvTransactionsInterface.setUseMtlSerial(1L);
                                                                                                                                                         else
                                                                                                                                                             rcvTransactionsInterface.setUseMtlSerial(0L);
                                                                                                                                                         rcvTransactionsInterface.setGroupId(rcvShipmentHeaders.getGroupId());
                                                                                                                                                         rcvTransactionsInterface.setTransactionStatusCode("PENDING");
                                                                                                                                                         rcvTransactionsInterface.setReceiptSourceCode("VENDOR");
                                                                                                                                                         rcvTransactionsInterface.setValidationFlag("Y");
                                                                                                                                                         rcvTransactionsInterface.setOrgId(rcvTransactions.getOrganizationId());
                                                                                                                                                         rcvTransactionsInterface.setHeaderInterfaceId(rcvHeadersInterface.getHeaderInterfaceId());
                                                                                                                                                         rcvTransactionsInterface.setSegment1(mtlSystemItems.getSegment1());

                                                                                                                                                         Gson gson1 = new Gson();

                                                                                                                                                         Log.d(TAG, gson1.toJson(rcvTransactionsInterface));

                                                                                                                                                         iRestRcvTransactionsInterface.insert(rcvTransactionsInterface).enqueue(new Callback<Void>() {
                                                                                                                                                             @Override
                                                                                                                                                             public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                                                                                 if (response.isSuccessful() == true && response.code() == 200) {
                                                                                                                                                                     Log.d(TAG, "Inserción Correcta en RcvTransactionsInterface");


                                                                                                                                                                     Log.d(TAG, "PO_HEADER_ID: " + rcvTransactions.getPoHeaderId());
                                                                                                                                                                     Log.d(TAG, "GROUP_ID: " + rcvShipmentHeaders.getGroupId());
                                                                                                                                                                     Log.d(TAG, "ReceiptNum: " + rcvShipmentHeaders.getReceiptNum());

                                                                                                                                                                     Gson gson2 = new Gson();

                                                                                                                                                                     Log.d(TAG, "rcvShipmentHeaders :" + gson2.toJson(rcvShipmentHeaders));


                                                                                                                                                                     iRestRcvStatus.actualiza_estado(7, rcvTransactions.getPoHeaderId(), Long.parseLong(rcvShipmentHeaders.getReceiptNum())).enqueue(new Callback<Void>() {
                                                                                                                                                                         @Override
                                                                                                                                                                         public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                                                                                             if (response.isSuccessful() == true && response.code() == 200) {
                                                                                                                                                                                 Log.d(TAG, "Estado Actualizado a 7 en RCV_ESTATUS");
                                                                                                                                                                             }

                                                                                                                                                                         }

                                                                                                                                                                         @Override
                                                                                                                                                                         public void onFailure(Call<Void> call, Throwable t) {
                                                                                                                                                                             Log.d(TAG, "Error al actualizar RCV_ESTATUS " + t.getMessage());

                                                                                                                                                                         }
                                                                                                                                                                     });


                                                                                                                                                                     Log.d(TAG, "isControlLote  " + isControlLote);
                                                                                                                                                                     // Crea Lote
                                                                                                                                                                     if (isControlLote) {


                                                                                                                                                                         MtlTransactionsLotsIface mtlTransactionsLotsIface = new MtlTransactionsLotsIface();
                                                                                                                                                                         mtlTransactionsLotsIface.setLastUpdateDate(sysDate);
                                                                                                                                                                         mtlTransactionsLotsIface.setLastUpdateBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                                         mtlTransactionsLotsIface.setCreationDate(sysDate);
                                                                                                                                                                         mtlTransactionsLotsIface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                                         mtlTransactionsLotsIface.setLastUpdateLogin(-1L);
                                                                                                                                                                         mtlTransactionsLotsIface.setLotNumber(lote);
                                                                                                                                                                         mtlTransactionsLotsIface.setTransactionQuantity(cantidad);
                                                                                                                                                                         mtlTransactionsLotsIface.setPrimaryQuantity(cantidad);
                                                                                                                                                                         mtlTransactionsLotsIface.setProductCode("RCV");
                                                                                                                                                                         mtlTransactionsLotsIface.setProductTransactionId(interfaceTransactionId);
                                                                                                                                                                         mtlTransactionsLotsIface.setSupplierLotNumber(loteProveedor);
                                                                                                                                                                         mtlTransactionsLotsIface.setLotExpirationDate(vencimiento);
                                                                                                                                                                         mtlTransactionsLotsIface.setAttributeCategory(categoria);
                                                                                                                                                                         mtlTransactionsLotsIface.setAttrubute1(atributo1);
                                                                                                                                                                         mtlTransactionsLotsIface.setAttrubute2(atributo2);
                                                                                                                                                                         mtlTransactionsLotsIface.setAttrubute3(atributo3);

                                                                                                                                                                         irEstMtlTransactionsLotsIface.insert(mtlTransactionsLotsIface).enqueue(new Callback<Void>() {
                                                                                                                                                                             @Override
                                                                                                                                                                             public void onResponse(Call<Void> call, Response<Void> response) {
                                                                                                                                                                                 if (response.isSuccessful() == true && response.code() == 200) {
                                                                                                                                                                                     mView.showSuccess("Insert Correcto mtlTransactionsLotsIface");
                                                                                                                                                                                 }
                                                                                                                                                                             }

                                                                                                                                                                             @Override
                                                                                                                                                                             public void onFailure(Call<Void> call, Throwable t) {
                                                                                                                                                                                 mView.showError("Fallo Insert mtlTransactionsLotsIface" + t.getMessage());
                                                                                                                                                                             }
                                                                                                                                                                         });

                                                                                                                                                                     }
                                                                                                                                                                     Log.d(TAG, "isControlSerie  " + isControlSerie);
                                                                                                                                                                     // Crea Series
                                                                                                                                                                     if (isControlSerie) {

                                                                                                                                                                         for (String serie : series) {

                                                                                                                                                                             MtlSerialNumbersInterface mtlSerialNumbersInterface = new MtlSerialNumbersInterface();
                                                                                                                                                                             mtlSerialNumbersInterface.setLastUpdateDate(sysDate);
                                                                                                                                                                             mtlSerialNumbersInterface.setLastUpdatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                                             mtlSerialNumbersInterface.setCreationDate(sysDate);
                                                                                                                                                                             mtlSerialNumbersInterface.setCreatedBy(rcvShipmentHeaders.getUserId());
                                                                                                                                                                             mtlSerialNumbersInterface.setLastUpdateLogin(-1L);
                                                                                                                                                                             mtlSerialNumbersInterface.setFmSerialNumber(serie);
                                                                                                                                                                             mtlSerialNumbersInterface.setToSerialNumber(serie);
                                                                                                                                                                             mtlSerialNumbersInterface.setProductCode("RCV");
                                                                                                                                                                             mtlSerialNumbersInterface.setProductTransactionId(interfaceTransactionId);

                                                                                                                                                                             iRestMtlSerialNumbersInterface.insert(mtlSerialNumbersInterface).enqueue(new Callback<Void>() {
                                                                                                                                                                                 @Override
                                                                                                                                                                                 public void onResponse(Call<Void> call, Response<Void> response) {

                                                                                                                                                                                     if (response.isSuccessful() == true && response.code() == 200) {

                                                                                                                                                                                         Log.d(TAG, "Inserción en mtlSerialNumbersInterface correcto");

                                                                                                                                                                                     }

                                                                                                                                                                                 }

                                                                                                                                                                                 @Override
                                                                                                                                                                                 public void onFailure(Call<Void> call, Throwable t) {

                                                                                                                                                                                     mView.showError("Error en iRestMtlSerialNumbersInterface.insert " + t.getMessage());

                                                                                                                                                                                 }
                                                                                                                                                                             });

                                                                                                                                                                         }

                                                                                                                                                                     }
                                                                                                                                                                     mView.resultadoOkAddTransaction();

                                                                                                                                                                 }


                                                                                                                                                             }

                                                                                                                                                             @Override
                                                                                                                                                             public void onFailure(Call<Void> call, Throwable t) {
                                                                                                                                                                 Log.d(TAG, "Fallo Llamando iRestRcvTransactionsInterface.insert: " + t.getMessage());
                                                                                                                                                             }
                                                                                                                                                         });


                                                                                                                                                     }

                                                                                                                                                 }

                                                                                                                                                 @Override
                                                                                                                                                 public void onFailure(Call<RcvHeadersInterface> call, Throwable t) {

                                                                                                                                                 }
                                                                                                                                             });


                                                                                                                                         }


                                                                                                                                     }


                                                                                                                                 }

                                                                                                                                 @Override
                                                                                                                                 public void onFailure(Call<Long> call, Throwable t) {
                                                                                                                                     Log.d(TAG, "Fallo Llamando iRestRcvHeadersInterface.get: " + t.getMessage());
                                                                                                                                 }
                                                                                                                             });

                                                                                                                             //ELSE VALIDACIONES

                                                                                                                         }


                                                                                                                         }
                                                                                                                         if(response.body()==null){


                                                                                                                             mView.showError("SystemItem con Id " + rcvTransactions.getItemId() + " no existe en el sistema");

                                                                                                                         }


                                                                                                                     }

                                                                                                                     @Override
                                                                                                                     public void onFailure(Call<MtlSystemItems> call, Throwable t) {

                                                                                                                         Log.d(TAG, "Fallo Llamando iRestMtlSystemItems.get: " + t.getMessage());

                                                                                                                     }
                                                                                                                 });

                                                                                                             }
                                                                                                         });





       /*
        try {
            this.mService.addTransacctionInterface(shipmentHeaderId, transactionId, subinventoryCode, locatorCode,
                    lote, loteProveedor, vencimiento, categoria, atributo1, atributo2, atributo3, series, cantidad);
            mView.resultadoOkAddTransaction();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        */


    }

}
