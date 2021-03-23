package cl.clsoft.bave.view;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.presenter.EntregasOrgsDetallePresenter;
import cl.clsoft.bave.service.impl.EntregaOrgsServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityEntregasOrgsDetalle extends BaseActivity<EntregasOrgsDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityEntregasOrgsDetalle";
    private Long shipmentHeaderId;
    private EntregaOrgsHeader entregaOrgsHeader;
    private List<TransactionsDto> transactions;

    // Controls
    private TextView textShipmentNumber;
    private TextView textReceiptNum;
    private TextView textCreationDate;
    private RecyclerView recyclerViewTransactions;
    private AdapterItemTransactionInterface adapter;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected EntregasOrgsDetallePresenter createPresenter(@NonNull Context context) {
        return new EntregasOrgsDetallePresenter(this, new AppTaskExecutor(this), new EntregaOrgsServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_orgs_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.textShipmentNumber = findViewById(R.id.textShipmentNumber);
        this.textReceiptNum = findViewById(R.id.textReceiptNum);
        this.textCreationDate = findViewById(R.id.textCreationDate);
        this.recyclerViewTransactions = findViewById(R.id.recyclerViewTransactions);

        // Set Controls
        this.entregaOrgsHeader = this.mPresenter.getEntrega(this.shipmentHeaderId);
        if (this.entregaOrgsHeader != null) {
            this.textShipmentNumber.setText(this.entregaOrgsHeader.getShipmentNumber());
            this.textReceiptNum.setText(this.entregaOrgsHeader.getReceiptNumber());
            this.textCreationDate.setText(this.entregaOrgsHeader.getCreationDate());
        }
        this.recyclerViewTransactions.setHasFixedSize(true);
        this.recyclerViewTransactions.setLayoutManager(new LinearLayoutManager(this));
        this.transactions = mPresenter.getTransactions(this.shipmentHeaderId);
        Log.d(TAG, "transactions size: " + this.transactions.size());
        this.adapter = new AdapterItemTransactionInterface(this.transactions);
        this.recyclerViewTransactions.setAdapter(adapter);
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
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityEntregaOrgsTransactionDetalle.class);
                        i.putExtra("TransactionId", transactions.get(position).getInterfaceTransactionId());
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
                Intent i = new Intent(this, ActivityEntregasOrgs.class);
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
                Intent iAgregar = new Intent(this, ActivityEntregaOrgsAgregar.class);
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
                    Intent i = new Intent(getApplicationContext(), ActivityEntregasOrgs.class);
                    startActivity(i);
                    finish();
                }
            })
            .show();
    }

}
