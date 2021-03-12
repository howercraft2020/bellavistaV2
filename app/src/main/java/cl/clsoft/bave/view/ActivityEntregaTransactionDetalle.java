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

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.presenter.EntregaTransactionDetallePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

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

    @NonNull
    @Override
    protected EntregaTransactionDetallePresenter createPresenter(@NonNull Context context) {
        return new EntregaTransactionDetallePresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        TransactionDetalleDto dto = mPresenter.getTransactionsInterfaceById(this.interfaceTransactionId);
        if (dto != null) {

            RcvTransactions transaction = mPresenter.getTransactionById(dto.getTransactionId());
            if (transaction != null) {
                MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getItemId());
                if (item != null) {
                    this.textProductoDescription.setText(item.getDescription());
                    this.textProductoSigle.setText(item.getSegment1());
                    this.textProductoCantidad.setText(dto.getCantidad().toString());
                    this.textProductoLinea.setText(transaction.getLineNum().toString());
                    this.textProductoLote.setText(dto.isLote() ? "SI" : "NO");
                    this.textProductoSerie.setText(dto.isSerie() ? "SI" : "NO");
                    this.textSubinventoryCode.setText(dto.getSubinventoryCode());
                    this.textLocatorCode.setText(dto.getLocatorCode());
                    this.textLote.setText(dto.getLote());
                    this.textLoteProveedor.setText(dto.getLoteProveedor());
                    this.textVencimiento.setText(dto.getVencimiento());
                    this.textCategoria.setText(dto.getCategoria());
                    this.textAtributo1.setText(dto.getAtributo1());
                    this.textAtributo2.setText(dto.getAtributo2());
                    this.textAtributo3.setText(dto.getAtributo3());

                    if (dto.getSeries() != null) {
                        String strSeries = "";
                        for (String serie : dto.getSeries()) {
                            strSeries = strSeries + serie + ", ";
                        }
                        this.textSeries.setText(strSeries);
                    }

                    if (dto.isLote()) {
                        this.rlayoutLote.setVisibility(View.VISIBLE);
                    } else {
                        this.rlayoutLote.setVisibility(View.GONE);
                    }

                    if (dto.isSerie()) {
                        this.rlayoutSeries.setVisibility(View.VISIBLE);
                    } else {
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
            mPresenter.deleteTransactionsInterfaceById(this.interfaceTransactionId);
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
