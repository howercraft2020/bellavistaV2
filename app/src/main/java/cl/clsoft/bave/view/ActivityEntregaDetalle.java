package cl.clsoft.bave.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.presenter.EntregaDetallePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cl.clsoft.bave.viewmodel.RcvShipmentHeadersViewModel;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEntregaDetalle extends BaseActivity<EntregaDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityEntregaDetalle";
    private Long shipmentHeaderId;
    private RcvShipmentHeaders rcvShipmentHeaders;
     List<TransactionsDto> transactions;



    //REST API
    private RcvShipmentHeadersViewModel rcvShipmentHeadersViewModel;
    private IRestRcvTransactions iRestRcvTransactions;
    private IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    // Controls
    private TextView textShipmentHeaderId;
    private TextView textReceiptNum;
    private TextView textCreationDate;
     RecyclerView recyclerViewTransactions;
     AdapterItemTransactionInterface adapter;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected EntregaDetallePresenter createPresenter(@NonNull Context context) {
        return new EntregaDetallePresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textShipmentHeaderId = findViewById(R.id.textShipmentHeaderId);
        this.textReceiptNum = findViewById(R.id.textReceiptNum);
        this.textCreationDate = findViewById(R.id.textCreationDate);
        this.recyclerViewTransactions = findViewById(R.id.recyclerViewTransactions);

        // Set Controls

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityEntregaDetalle.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando Detalle Entrega...");
        progressDoalog.setTitle("Entregas");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        //this.rcvShipmentHeaders = mPresenter.getEntrega(this.shipmentHeaderId);
        rcvShipmentHeadersViewModel = new ViewModelProvider(this).get(RcvShipmentHeadersViewModel.class);
        rcvShipmentHeadersViewModel.init();
        rcvShipmentHeadersViewModel.Shipmentget(this.shipmentHeaderId);

        rcvShipmentHeadersViewModel.get().observe(this, new Observer<RcvShipmentHeaders>() {
            @Override
            public void onChanged(RcvShipmentHeaders rcvShipmentHeaders) {
                if (rcvShipmentHeaders != null) {
                    progressDoalog.dismiss();
                    textShipmentHeaderId.setText(rcvShipmentHeaders.getPoNumber().toString());
                    textReceiptNum.setText(rcvShipmentHeaders.getReceiptNum());
                    textCreationDate.setText(rcvShipmentHeaders.getCreationDate());
                }
                else {
                    progressDoalog.dismiss();
                    Log.d("NULO", "VIENE NULO");
                }

            }
        });


        /*
        if (this.rcvShipmentHeaders != null) {
            this.textShipmentHeaderId.setText(rcvShipmentHeaders.getPoNumber().toString());
            this.textReceiptNum.setText(rcvShipmentHeaders.getReceiptNum());
            this.textCreationDate.setText(rcvShipmentHeaders.getCreationDate());
        }
        */


        this.recyclerViewTransactions.setHasFixedSize(true);
        this.recyclerViewTransactions.setLayoutManager(new LinearLayoutManager(this));

        /*
        this.transactions = mPresenter.getTransactions(this.shipmentHeaderId);
        this.adapter = new AdapterItemTransactionInterface(this.transactions);
        this.recyclerViewTransactions.setAdapter(adapter);
        */


        transactions = new ArrayList<TransactionsDto>();

        iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();





        iRestRcvTransactions.getAllByShipment(this.shipmentHeaderId).enqueue(new Callback<List<RcvTransactions>>() {
            @Override
            public void onResponse(Call<List<RcvTransactions>> call, Response<List<RcvTransactions>> response) {

                if(response.isSuccessful() && response.code() == 200 && response.body()!=null) {

                    List<RcvTransactions> transactions = response.body();

                    List<TransactionsDto> resultado = new ArrayList<TransactionsDto>();
                    for (RcvTransactions transaction : transactions) {

                        iRestRcvTransactionsInterface.getByTransactionId(transaction.getTransactionId()).enqueue(new Callback<RcvTransactionsInterface>() {
                            @Override
                            public void onResponse(Call<RcvTransactionsInterface> call, Response<RcvTransactionsInterface> response) {

                               if(response.isSuccessful() && response.code()==200 && response.body().getInterfaceTransactionId()!=null ) {
                                   Log.d(TAG, "PANTALLA ENTREGA 2 TOUCH SOBRE ENTREGA");
                                   RcvTransactionsInterface transactioninterface = response.body();
                                   if (transactioninterface != null) {
                                       TransactionsDto dto = new TransactionsDto();
                                       dto.setInterfaceTransactionId(transactioninterface.getInterfaceTransactionId());
                                       dto.setLineNum(transaction.getLineNum());
                                       dto.setCreationDate(transactioninterface.getCreationDate());
                                       dto.setSegment1(transactioninterface.getSegment1());
                                       resultado.add(dto);

                                   }

                               }

                                if(response.isSuccessful() && response.code()==200 && response.body()==null ){
                                    adapter = new AdapterItemTransactionInterface(null);
                                    recyclerViewTransactions.setAdapter(adapter);

                                }

                                adapter = new AdapterItemTransactionInterface(resultado);
                                recyclerViewTransactions.setAdapter(adapter);
                                Gson gson = new Gson();

                                Log.d(TAG,"resultado.add(dto) DTO Object: "+gson.toJson(resultado));


                            }

                            @Override
                            public void onFailure(Call<RcvTransactionsInterface> call, Throwable t) {
                                showError("Call<RcvTransactionsInterface"+t.getMessage());
                                Log.d("TRANSAC", "Call<RcvTransactionsInterface"+t.getMessage());
                            }
                        });


                    }





                }


            }

            @Override
            public void onFailure(Call<List<RcvTransactions>> call, Throwable t) {

                            showError("Call<List<RcvTransactions>>");
                Log.d("TRANSAC", "Call<List<RcvTransactions>>"+t.getMessage());

            }
        });

                //// FIN

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewTransactions.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

                Log.d("TRANSAC", "List<TransactionsDto> resultado Tamaño:"+adapter.getTransactions().size());
                try {

                    //transactions = adapter.getTransactions();

                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

                        transactions = adapter.getTransactions();

                        Log.d("TRANSAC", "Tamaño Transactions:"+transactions.size());
                        int position = recyclerView.getChildAdapterPosition(child);

                        Intent i = new Intent(ActivityEntregaDetalle.this, ActivityEntregaTransactionDetalle.class);

                        Log.d("CALL", "Enviando Variable InterfaceTransactionId:"+transactions.get(position).getInterfaceTransactionId());
                        i.putExtra("InterfaceTransactionId",transactions.get(position).getInterfaceTransactionId());
                        i.putExtra("ShipmentHeaderId", shipmentHeaderId);
                        startActivity(i);
                        finish();
                        return true;



                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
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
        inflater.inflate(R.menu.menu_entrega_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                Intent i = new Intent(this, ActivityEntregas.class);
                startActivity(i);
                this.finish();
                return true;
            case R.id.cerrar:
                Log.d(TAG, "cerrar");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar la entrega?", "Confirmación", "close");
                dialogClose.show(getSupportFragmentManager(), "closeEntregaConfirm");
                return true;
            case R.id.agregar:
                Log.d(TAG, "agregar");
                Intent iAgregar = new Intent(this, ActivityEntregaAgregar.class);
                iAgregar.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                startActivity(iAgregar);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("close")) {
            mPresenter.closeEntrega(this.shipmentHeaderId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void resultadoOkCerrarEntrega() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
            .setContentText("Cierre exitoso")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    Intent i = new Intent(getApplicationContext(), ActivityEntregas.class);
                    startActivity(i);
                    finish();
                }
            })
            .show();
    }

}
