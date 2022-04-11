package cl.clsoft.bave.presenter;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IREstMtlTransactionsLotsIface;
import cl.clsoft.bave.apis.IRestLocalizador;
import cl.clsoft.bave.apis.IRestMtlMaterialTransactions;
import cl.clsoft.bave.apis.IRestMtlSerialNumbers;
import cl.clsoft.bave.apis.IRestMtlSerialNumbersInterface;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestMtlTransactionLotNumbers;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaDetalle;
import cl.clsoft.bave.view.ActivityEntregaTransactionDetalle;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntregaTransactionDetallePresenter extends BasePresenter {

    private static final String TAG = "EntregaDetalle";
    private ActivityEntregaTransactionDetalle mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;

    //BIND CONTROLS

    // Controls
    private TextView textProductoDescription;
    private TextView textProductoSigle;
    private TextView textProductoCantidad;
    private TextView textProductoLinea;
    private TextView textProductoLote;
    private TextView textProductoSerie;
    private TextView textSubinventoryCode;
    private TextView textLocatorCode;
    private TextView textLote;
    private TextView textLoteProveedor;
    private TextView textVencimiento;
    private TextView textCategoria;
    private TextView textAtributo1;
    private TextView textAtributo2;
    private TextView textAtributo3;
    private TextView textSeries;
    private RelativeLayout rlayoutLote;
    private RelativeLayout rlayoutSeries;
    private SweetAlertDialog dialog;

    //VARIABLES REST API
    IRestMtlMaterialTransactions iRestMtlMaterialTransactions;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestLocalizador iRestLocalizador;
    IRestMtlTransactionLotNumbers iRestMtlTransactionLotNumbers;
    IRestMtlSerialNumbers iRestMtlSerialNumbers;
    IRestRcvTransactions iRestRcvTransactions;
    IREstMtlTransactionsLotsIface irEstMtlTransactionsLotsIface;
    IRestMtlSerialNumbersInterface iRestMtlSerialNumbersInterface;
    IRestRcvTransactionsInterface iRestRcvTransactionsInterface;


    Localizador localizador = null;
    boolean isControlLote = false;
    boolean isControlSerie = false;
    boolean isControlVencimiento = false;

    public EntregaTransactionDetallePresenter(@NonNull final ActivityEntregaTransactionDetalle view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;

        this.iRestMtlMaterialTransactions = ApiUtils.getIRestMtlMaterialTransactionsRx();
        this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        this.iRestLocalizador = ApiUtils.getIRestLocalizador();
        this.iRestMtlTransactionLotNumbers = ApiUtils.getIRestMtlTransactionLotNumbersRx();
        this.iRestMtlSerialNumbers = ApiUtils.getIRestMtlSerialNumbersRx();
        this.iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();
        this.iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        this.irEstMtlTransactionsLotsIface = ApiUtils.getIREstMtlTransactionsLotsIface();
        this.iRestMtlSerialNumbersInterface = ApiUtils.getIRestMtlSerialNumbersInterface();




        this.textProductoDescription = mView.findViewById(R.id.textProductoDescription);
        this.textProductoSigle = mView.findViewById(R.id.textProductoSigle);
        this.textProductoCantidad = mView.findViewById(R.id.textProductoCantidad);
        this.textProductoLinea = mView.findViewById(R.id.textProductoLinea);
        this.textProductoLote = mView.findViewById(R.id.textProductoLote);
        this.textProductoSerie = mView.findViewById(R.id.textProductoSerie);
        this.textSubinventoryCode = mView.findViewById(R.id.textSubinventoryCode);
        this.textLocatorCode = mView.findViewById(R.id.textLocatorCode);
        this.textLote = mView.findViewById(R.id.textLote);
        this.textLoteProveedor = mView.findViewById(R.id.textLoteProveedor);
        this.textVencimiento = mView.findViewById(R.id.textVencimiento);
        this.textCategoria = mView.findViewById(R.id.textCategoria);
        this.textAtributo1 = mView.findViewById(R.id.textAtributo1);
        this.textAtributo2 = mView.findViewById(R.id.textAtributo2);
        this.textAtributo3 = mView.findViewById(R.id.textAtributo3);
        this.textSeries = mView.findViewById(R.id.textSeries);
        this.rlayoutLote = mView.findViewById(R.id.rlayoutLote);
        this.rlayoutSeries = mView.findViewById(R.id.rlayoutSeries);
    }

    public TransactionDetalleDto getTransactionsInterfaceById(Long interfaceTransactionId) {


        Log.d(TAG, "EntregaServiceImpl::getTransactionsInterfaceById");
        Log.d(TAG, "EntregaServiceImpl::getTransactionsInterfaceById::interfaceTransactionId: " + interfaceTransactionId);


        TransactionDetalleDto dto = new TransactionDetalleDto();

        iRestRcvTransactionsInterface.getByInterfaceTransactionId(interfaceTransactionId).enqueue(new Callback<RcvTransactionsInterface>() {
            @Override
            public void onResponse(Call<RcvTransactionsInterface> call, Response<RcvTransactionsInterface> response) {

                    if(response.isSuccessful()==true && response.code()==200){


                        RcvTransactionsInterface rcvTransactionsInterface = response.body();

                    iRestMtlSystemItems.get(rcvTransactionsInterface.getItemId()).enqueue(new Callback<MtlSystemItems>() {
                        @Override
                        public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                            if(response.isSuccessful()==true && response.code()==200){

                                if(response.isSuccessful()==true && response.code()==200){

                                    MtlSystemItems item = response.body();

                                    if (item.getLotControlCode().equalsIgnoreCase("2")) {
                                        isControlLote = true;
                                    }
                                    if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                                        isControlSerie = true;
                                    }
                                    if (item.getShelfLifeCode().equalsIgnoreCase("2") || item.getShelfLifeCode().equalsIgnoreCase("4")) {
                                        isControlVencimiento = true;
                                    }
                                    if (rcvTransactionsInterface.getLocatorId() != null && rcvTransactionsInterface.getLocatorId().longValue() > 0) {
                                        iRestLocalizador.getId(rcvTransactionsInterface.getLocatorId()).enqueue(new Callback<Localizador>() {
                                            @Override
                                            public void onResponse(Call<Localizador> call, Response<Localizador> response) {
                                            if(response.isSuccessful()==true && response.code()==200){

                                                dto.setShipmentHeaderId(rcvTransactionsInterface.getShipmentHeaderId());
                                                dto.setTransactionId(rcvTransactionsInterface.getParentTransactionId());
                                                dto.setCantidad(rcvTransactionsInterface.getQuantity());
                                                dto.setSubinventoryCode(rcvTransactionsInterface.getSubinventory());
                                                dto.setLocatorCode(localizador != null ? localizador.getCodLocalizador() : null);
                                                dto.setLote(isControlLote);
                                                dto.setSerie(isControlSerie);


                                                if (isControlLote) {
                                                    //mtlTransactionsLotsIface = mtlTransactionLotsInterfaceDao.getByInterfaceTransactionId(rcvTransactionsInterface.getInterfaceTransactionId());
                                                    irEstMtlTransactionsLotsIface.getByInterfaceTransactionId(rcvTransactionsInterface.getInterfaceTransactionId()).enqueue(new Callback<MtlTransactionsLotsIface>() {
                                                        @Override
                                                        public void onResponse(Call<MtlTransactionsLotsIface> call, Response<MtlTransactionsLotsIface> response) {
                                                             if(response.isSuccessful()==true && response.code()==200){
                                                                 MtlTransactionsLotsIface mtlTransactionsLotsIface = response.body();

                                                                 dto.setLote(mtlTransactionsLotsIface.getLotNumber());
                                                                 dto.setLoteProveedor(mtlTransactionsLotsIface.getSupplierLotNumber());
                                                                 dto.setCategoria(mtlTransactionsLotsIface.getAttributeCategory());
                                                                 dto.setAtributo1(mtlTransactionsLotsIface.getAttrubute1());
                                                                 dto.setAtributo2(mtlTransactionsLotsIface.getAttrubute2());
                                                                 dto.setAtributo3(mtlTransactionsLotsIface.getAttrubute3());

                                                             }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<MtlTransactionsLotsIface> call, Throwable t) {
                                                            mView.showError("MtlTransactionsLotsIface Nulo"+t.getMessage());
                                                        }
                                                    });

                                                }

                                                if (isControlSerie) {
                                                    //serials = mtlSerialNumbersInterfaceDao.getAllByInterfaceTransactionId(rcvTransactionsInterface.getInterfaceTransactionId());
                                                    List<String> strSerials = new ArrayList<>();

                                                    iRestMtlSerialNumbersInterface.getAllByInterfaceTransactionId(rcvTransactionsInterface.getInterfaceTransactionId()).enqueue(new Callback<List<MtlSerialNumbersInterface>>() {
                                                        @Override
                                                        public void onResponse(Call<List<MtlSerialNumbersInterface>> call, Response<List<MtlSerialNumbersInterface>> response) {

                                                            if(response.isSuccessful()==true && response.code()==200){

                                                                List<MtlSerialNumbersInterface> serials= response.body();
                                                                for (MtlSerialNumbersInterface serial : serials) {
                                                                    strSerials.add(serial.getFmSerialNumber());
                                                                }
                                                               dto.setSeries(strSerials);

                                                            }

                                                        }

                                                        @Override
                                                        public void onFailure(Call<List<MtlSerialNumbersInterface>> call, Throwable t) {
                                                            mView.showError("MtlSerialNumbersInterface Nulo"+t.getMessage());
                                                        }
                                                    });


                                                }

                                                if (dto != null) {

                                                    //RcvTransactions transaction = mPresenter.getTransactionById(dto.getTransactionId());

                                                    //if (transaction != null) {
                                                    // MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getItemId());
                                                    if (item != null) {

                                                        Gson gson = new Gson();

                                                        Log.d(TAG,"DTO Objeto: "+gson.toJson(dto));
                                                        Log.d(TAG,"ITEM Objeto: "+gson.toJson(item));

                                                        textProductoDescription.setText("Descripción");
                                                           /*
                                                        textProductoDescription.setText(item.getDescription());
                                                        textProductoSigle.setText(item.getSegment1());

                                                         textProductoCantidad.setText(dto.getCantidad().toString());

                                                         //textProductoLinea.setText(transaction.getLineNum().toString());

                                                        textProductoLote.setText(dto.isLote() ? "SI" : "NO");
                                                        textProductoSerie.setText(dto.isSerie() ? "SI" : "NO");
                                                        textSubinventoryCode.setText(dto.getSubinventoryCode());
                                                        textLocatorCode.setText(dto.getLocatorCode());
                                                        textLote.setText(dto.getLote());
                                                        textLoteProveedor.setText(dto.getLoteProveedor());
                                                        textVencimiento.setText(dto.getVencimiento());
                                                        textCategoria.setText(dto.getCategoria());
                                                        textAtributo1.setText(dto.getAtributo1());
                                                        textAtributo2.setText(dto.getAtributo2());
                                                        textAtributo3.setText(dto.getAtributo3());

                                                        if (dto.getSeries() != null) {
                                                            String strSeries = "";
                                                            for (String serie : dto.getSeries()) {
                                                                strSeries = strSeries + serie + ", ";
                                                            }
                                                            textSeries.setText(strSeries);
                                                        }

                                                         */
                                                /*
                                                if (dto.isLote()) {

                                                    rlayoutLote.setVisibility(mView.VISIBLE);
                                                } else {
                                                    rlayoutLote.setVisibility(View.GONE);
                                                }

                                                if (dto.isSerie()) {
                                                    this.rlayoutSeries.setVisibility(View.VISIBLE);
                                                } else {
                                                    this.rlayoutSeries.setVisibility(View.GONE);
                                                }

                                                 */

                                                    }
                                                    //}
                                                }


                                            }
                                            }

                                            @Override
                                            public void onFailure(Call<Localizador> call, Throwable t) {
                                                mView.showError("Error iRestLocalizador.getId()"+t.getMessage());
                                            }
                                        });
                                    }

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                            mView.showError("Error rcvTransactionsInterface.getItemId()"+t.getMessage());
                        }
                    });

                    }
            }

            @Override
            public void onFailure(Call<RcvTransactionsInterface> call, Throwable t) {
                mView.showError("interfaceTransactionId No Encontrado"+t.getMessage());
            }
        });

        /*

        try {
            return this.mService.getTransactionsInterfaceById(interfaceTransactionId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }

        */
        return null;
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

    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) {
        try {
            this.mService.deleteTransactionsInterfaceById(interfaceTransactionId);
            mView.resultadoOkDeleteTransaction();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
    }




}
