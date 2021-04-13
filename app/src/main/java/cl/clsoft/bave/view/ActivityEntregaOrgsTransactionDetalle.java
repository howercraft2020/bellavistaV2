package cl.clsoft.bave.view;

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

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.EntregaOrgsTransactionDetallePresenter;
import cl.clsoft.bave.service.impl.EntregaOrgsServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityEntregaOrgsTransactionDetalle extends BaseActivity<EntregaOrgsTransactionDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "EntregaOrgsAgregar";
    private Long shipmentHeaderId;
    private Long transactionId;

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
    private TextView textCategoria;
    private TextView textAtributo1;
    private TextView textAtributo2;
    private TextView textAtributo3;
    private TextView textSeries;
    private RelativeLayout rlayoutLote;
    private RelativeLayout rlayoutSeries;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected EntregaOrgsTransactionDetallePresenter createPresenter(@NonNull Context context) {
        return new EntregaOrgsTransactionDetallePresenter(this, new AppTaskExecutor(this), new EntregaOrgsServiceImpl());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_orgs_transaction_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

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
        this.textCategoria = findViewById(R.id.textCategoria);
        this.textAtributo1 = findViewById(R.id.textAtributo1);
        this.textAtributo2 = findViewById(R.id.textAtributo2);
        this.textAtributo3 = findViewById(R.id.textAtributo3);
        this.textSeries = findViewById(R.id.textSeries);
        this.rlayoutLote = findViewById(R.id.rlayoutLote);
        this.rlayoutSeries = findViewById(R.id.rlayoutSeries);

        // Set Controls
        this.transactionId = this.getIntent().getLongExtra("TransactionId", 0);
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);

        TransactionDetalleDto dto = mPresenter.getTransactionsInterfaceById(this.transactionId);
        if (dto != null) {
            MtlMaterialTransactions transaction = this.mPresenter.getTransactionById(dto.getTransactionId());
            if (transaction != null) {
                MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getInventoryItemId());
                if (item != null) {
                    this.textProductoDescription.setText(item.getDescription());
                    this.textProductoSigle.setText(item.getSegment1());
                    this.textProductoCantidad.setText(dto.getCantidad().toString());
                    this.textProductoLote.setText(dto.isLote() ? "SI" : "NO");
                    this.textProductoSerie.setText(dto.isSerie() ? "SI" : "NO");
                    this.textSubinventoryCode.setText(dto.getSubinventoryCode());
                    this.textLocatorCode.setText(dto.getLocatorCode());
                    this.textLote.setText(dto.getLote());
                    this.textCategoria.setText(dto.getCategoria());
                    this.textAtributo1.setText(dto.getAtributo1());
                    this.textAtributo2.setText(dto.getAtributo2());
                    this.textAtributo3.setText(dto.getAtributo3());

                    if (dto.isLote()) {
                        this.rlayoutLote.setVisibility(View.VISIBLE);
                    } else {
                        this.rlayoutLote.setVisibility(View.GONE);
                    }

                    if (dto.isSerie()) {
                        this.rlayoutSeries.setVisibility(View.VISIBLE);
                        this.fillSeries(dto.getSeries());
                    } else {
                        this.textSeries.setText("");
                        this.rlayoutSeries.setVisibility(View.GONE);
                    }
                }
            }
        }
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
                Intent i = new Intent(this, ActivityEntregasOrgsDetalle.class);
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
            mPresenter.deleteTransactionsById(this.transactionId);
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
                        Intent i = new Intent(getApplicationContext(), ActivityEntregasOrgsDetalle.class);
                        i.putExtra("ShipmentHeaderId", shipmentHeaderId);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

    private void fillSeries(List<String> series) {
        String strSeries = "";

        if (series != null) {
            for (String serie : series) {
                strSeries = strSeries + serie + ", ";
            }
        }
        this.textSeries.setText(strSeries);
        this.rlayoutSeries.setVisibility(View.VISIBLE);
    }

}
