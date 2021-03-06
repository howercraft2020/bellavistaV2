package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.presenter.EntregaDetallePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregaDetalle extends BaseActivity<EntregaDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityEntregaDetalle";
    private Long shipmentHeaderId;
    private RcvShipmentHeaders rcvShipmentHeaders;

    // Controls
    private TextView textShipmentHeaderId;
    private TextView textReceiptNum;
    private TextView textCreationDate;

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

        // Set Controls
        this.rcvShipmentHeaders = mPresenter.getEntrega(this.shipmentHeaderId);
        if (this.rcvShipmentHeaders != null) {
            this.textShipmentHeaderId.setText(rcvShipmentHeaders.getShipmentHeaderId().toString());
            this.textReceiptNum.setText(rcvShipmentHeaders.getReceiptNum());
            this.textCreationDate.setText(rcvShipmentHeaders.getCreationDate());
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
            //mPresenter.closeInventory(this.inventarioId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}
