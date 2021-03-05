package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.EntityReference;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.presenter.EntregaAgregarSeriePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregaAgregarSerie extends BaseActivity<EntregaAgregarSeriePresenter> {

    // Variables
    private String TAG = "EntregaAgregarSerie";
    private Long shipmentHeaderId;
    private Long transactionId;
    private boolean isLote = false;
    private boolean isSerie = false;

    // Controls
    private TextInputLayout layoutSerie;
    private TextInputEditText textSerie;
    private RecyclerView recyclerViewSeries;

    @NonNull
    @Override
    protected EntregaAgregarSeriePresenter createPresenter(@NonNull Context context) {
        return new EntregaAgregarSeriePresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_agregar_serie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSerie = findViewById(R.id.layoutSerie);
        this.textSerie = findViewById(R.id.textSerie);
        this.recyclerViewSeries = findViewById(R.id.recyclerViewSeries);

        // Set Controls
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.transactionId = this.getIntent().getLongExtra("TransactionId", 0);
        RcvTransactions transaction = mPresenter.getTransactionById(this.transactionId);
        if (transaction != null) {
            MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getItemId());
            if (item != null) {
                if (item.getLotControlCode().equalsIgnoreCase("2")) {
                    this.isLote = true;
                } else {
                    this.isLote = false;
                }
                if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                    this.isSerie = true;
                } else {
                    this.isSerie = false;
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_entrega_agregar_serie, menu);
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
            case R.id.next:
                Log.d(TAG, "next");
                return true;
            case R.id.back:
                Log.d(TAG, "back");
                if (this.isLote) {
                    Intent iLote = new Intent(this, ActivityEntregaAgregarLote.class);
                    iLote.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iLote.putExtra("TransactionId", this.transactionId);
                    startActivity(iLote);
                    this.finish();
                } else {
                    Intent iAgregar = new Intent(this, ActivityEntregaAgregar.class);
                    iAgregar.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iAgregar.putExtra("TransactionId", this.transactionId);
                    startActivity(iAgregar);
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
