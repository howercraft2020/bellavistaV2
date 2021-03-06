package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.presenter.EntregaAgregarLotePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregaAgregarLote extends BaseActivity<EntregaAgregarLotePresenter> {

    // Variables
    private String TAG = "EntregaAgregarLote";
    private Long shipmentHeaderId;
    private Long transactionId;
    private String subinventoryCode;
    private String locatorCode;
    private boolean isLote = false;
    private boolean isSerie = false;
    private boolean isVencimiento = false;

    // Controls
    private TextInputLayout layoutLote;
    private TextInputEditText textLote;
    private TextInputLayout layoutVencimiento;
    private TextInputEditText textVencimiento;
    private TextInputLayout layoutAtributo1;
    private TextInputEditText textAtributo1;
    private TextInputLayout layoutAtributo2;
    private TextInputEditText textAtributo2;
    private TextInputLayout layoutAtributo3;
    private TextInputEditText textAtributo3;

    @NonNull
    @Override
    protected EntregaAgregarLotePresenter createPresenter(@NonNull Context context) {
        return new EntregaAgregarLotePresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_agregar_lote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutLote = findViewById(R.id.layoutLote);
        this.textLote = findViewById(R.id.textLote);
        this.layoutVencimiento = findViewById(R.id.layoutVencimiento);
        this.textVencimiento = findViewById(R.id.textVencimiento);
        this.layoutAtributo1 = findViewById(R.id.layoutAtributo1);
        this.textAtributo1 = findViewById(R.id.textAtributo1);
        this.layoutAtributo2 = findViewById(R.id.layoutAtributo2);
        this.textAtributo2 = findViewById(R.id.textAtributo2);
        this.layoutAtributo3 = findViewById(R.id.layoutAtributo3);
        this.textAtributo3 = findViewById(R.id.textAtributo3);

        // Set Controls
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.transactionId = this.getIntent().getLongExtra("TransactionId", 0);
        this.subinventoryCode = this.getIntent().getStringExtra("SubinventoryCode");
        this.locatorCode = this.getIntent().getStringExtra("LocatorCode");
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
                if (item.getShelfLifeCode().equalsIgnoreCase("2") || item.getShelfLifeCode().equalsIgnoreCase("4")) {
                    this.isVencimiento = true;
                } else {
                    this.isVencimiento = false;
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_entrega_agregar_lote, menu);
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
                Intent iSerie = new Intent(this, ActivityEntregaAgregarSerie.class);
                iSerie.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                iSerie.putExtra("TransactionId", this.transactionId);
                iSerie.putExtra("SubinventoryCode", this.subinventoryCode);
                iSerie.putExtra("LocatorCode", this.locatorCode);
                startActivity(iSerie);
                this.finish();
                return true;
            case R.id.back:
                Log.d(TAG, "back");
                Intent iAgregar = new Intent(this, ActivityEntregaAgregar.class);
                iAgregar.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                iAgregar.putExtra("TransactionId", this.transactionId);
                iAgregar.putExtra("SubinventoryCode", this.subinventoryCode);
                iAgregar.putExtra("LocatorCode", this.locatorCode);
                startActivity(iAgregar);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
