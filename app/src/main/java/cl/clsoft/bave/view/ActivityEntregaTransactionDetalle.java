package cl.clsoft.bave.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

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
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvShipmentHeaders;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.apis.IRestSubinventario;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSerialNumbers;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.MtlTransactionLotNumbers;
import cl.clsoft.bave.model.MtlTransactionsLotsIface;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.presenter.EntregaTransactionDetallePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEntregaTransactionDetalle extends BaseActivity<EntregaTransactionDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "EntregaAgregar";
    private Long shipmentHeaderId;
    private Long interfaceTransactionId;

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


    //Variables REST API
    IRestMtlMaterialTransactions iRestMtlMaterialTransactions;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestLocalizador iRestLocalizador;
    IRestMtlTransactionLotNumbers iRestMtlTransactionLotNumbers;
    IRestMtlSerialNumbers iRestMtlSerialNumbers;
    IRestRcvTransactions iRestRcvTransactions;
    IREstMtlTransactionsLotsIface irEstMtlTransactionsLotsIface;
    IRestMtlSerialNumbersInterface iRestMtlSerialNumbersInterface;
    IRestRcvTransactionsInterface iRestRcvTransactionsInterface;


    TransactionDetalleDto dto = new TransactionDetalleDto();
    Localizador localizador = null;
    boolean isControlLote = false;
    boolean isControlSerie = false;




    boolean isControlVencimiento = false;


    @NonNull
    @Override
    protected EntregaTransactionDetallePresenter createPresenter(@NonNull Context context) {
        return new EntregaTransactionDetallePresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Iniciando Variables API REST
        this.iRestMtlMaterialTransactions = ApiUtils.getIRestMtlMaterialTransactionsRx();
        this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        this.iRestLocalizador = ApiUtils.getIRestLocalizador();
        this.iRestMtlTransactionLotNumbers = ApiUtils.getIRestMtlTransactionLotNumbersRx();
        this.iRestMtlSerialNumbers = ApiUtils.getIRestMtlSerialNumbersRx();
        this.iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();
        this.iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        this.irEstMtlTransactionsLotsIface = ApiUtils.getIREstMtlTransactionsLotsIface();
        this.iRestMtlSerialNumbersInterface = ApiUtils.getIRestMtlSerialNumbersInterface();

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_transaction_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textProductoDescription = findViewById(R.id.textProductoDescription);
        this.textProductoSigle = findViewById(R.id.textProductoSigle);
        this.textProductoCantidad = findViewById(R.id.textProductoCantidad);
        this.textProductoLinea = findViewById(R.id.textProductoLinea);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);
        this.textSubinventoryCode = findViewById(R.id.textSubinventoryCode);
        this.textLocatorCode = findViewById(R.id.textLocatorCode);
        this.textLote = findViewById(R.id.textLote);
        this.textLoteProveedor = findViewById(R.id.textLoteProveedor);
        this.textVencimiento = findViewById(R.id.textVencimiento);
        this.textCategoria = findViewById(R.id.textCategoria);
        this.textAtributo1 = findViewById(R.id.textAtributo1);
        this.textAtributo2 = findViewById(R.id.textAtributo2);
        this.textAtributo3 = findViewById(R.id.textAtributo3);
        this.textSeries = findViewById(R.id.textSeries);
        this.rlayoutLote = findViewById(R.id.rlayoutLote);
        this.rlayoutSeries = findViewById(R.id.rlayoutSeries);

        // Set Controls
        this.interfaceTransactionId = this.getIntent().getLongExtra("InterfaceTransactionId", 0);
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);


        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityEntregaTransactionDetalle.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando Detalle Transacción...");
        progressDoalog.setTitle("Entregas");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();



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

                                                    localizador = response.body();

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
                                                                showError("MtlTransactionsLotsIface Nulo"+t.getMessage());
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
                                                                showError("MtlSerialNumbersInterface Nulo"+t.getMessage());
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
                                                            Log.d(TAG,"Localizador Objeto: "+gson.toJson(localizador));

                                                            textProductoDescription.setText("Descripción");
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



                                                if (dto.isLote()) {

                                                    rlayoutLote.setVisibility(View.VISIBLE);
                                                } else {
                                                    rlayoutLote.setVisibility(View.GONE);
                                                }

                                                if (dto.isSerie()) {
                                                    rlayoutSeries.setVisibility(View.VISIBLE);
                                                } else {
                                                    rlayoutSeries.setVisibility(View.GONE);
                                                }


                                                            progressDoalog.dismiss();
                                                        }
                                                    }


                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Localizador> call, Throwable t) {
                                                showError("Error iRestLocalizador.getId()"+t.getMessage());
                                            }
                                        });
                                    }

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                            showError("Error rcvTransactionsInterface.getItemId()"+t.getMessage());
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<RcvTransactionsInterface> call, Throwable t) {
                showError("interfaceTransactionId No Encontrado"+t.getMessage());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_entrega_agregar_transaction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                Intent i = new Intent(this, ActivityEntregaDetalle.class);
                i.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                startActivity(i);
                this.finish();
                return true;
            case R.id.delete:
                Log.d(TAG, "delete");
                ConfirmationDialog dialogDelete = ConfirmationDialog.newInstance("Esta seguro de eliminar la transacción?", "Confirmación", "delete");
                dialogDelete.show(getSupportFragmentManager(), "deleteTransactionConfirm");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("delete")) {
           // mPresenter.deleteTransactionsInterfaceById(this.interfaceTransactionId);
                iRestRcvTransactionsInterface.delete(this.interfaceTransactionId).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful() == true && response.code()==200){

                            showSuccess("Borrado Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        showError("Fallo"+t.getMessage());

                    }
                });

        }

    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }



    public void resultadoOkDeleteTransaction() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
            .setContentText("Eliminación exitosa")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    Log.d("CMFA", "CLICK");
                    Intent i = new Intent(getApplicationContext(), ActivityEntregaDetalle.class);
                    i.putExtra("ShipmentHeaderId", shipmentHeaderId);
                    startActivity(i);
                    finish();
                }
            })
            .show();
    }

}
