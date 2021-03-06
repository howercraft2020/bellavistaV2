package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

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
    private String lote;
    private String vencimiento;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private List<String> series = new ArrayList<>();

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
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

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
        this.lote = this.getIntent().getStringExtra("Lote");
        this.vencimiento = this.getIntent().getStringExtra("Vencimiento");
        this.atributo1 = this.getIntent().getStringExtra("Atributo1");
        this.atributo2 = this.getIntent().getStringExtra("Atributo2");
        this.atributo3 = this.getIntent().getStringExtra("Atributo3");
        this.series = this.getIntent().getStringArrayListExtra("series");
        Log.d(TAG, "Lote: " + this.lote);

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
                this.textLote.setText(this.lote);
                this.textAtributo1.setText(this.atributo1);
                this.textAtributo2.setText(this.atributo2);
                this.textAtributo3.setText(this.atributo3);
            }
        }

        this.textLote.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                //Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        lote = textView.getText().toString();

                    }
                    action = true;
                }
                return action;
            }
        });

        this.textVencimiento.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                //Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        vencimiento = textView.getText().toString();
                        Log.d(TAG, "vencimiento: " + vencimiento);
                    }
                    action = true;
                }
                return action;
            }
        });

        this.textAtributo1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                //Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        atributo1 = textView.getText().toString();

                    }
                    action = true;
                }
                return action;
            }
        });

        this.textAtributo2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                //Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        atributo2 = textView.getText().toString();

                    }
                    action = true;
                }
                return action;
            }
        });

        this.textAtributo3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                //Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        atributo3 = textView.getText().toString();

                    }
                    action = true;
                }
                return action;
            }
        });

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
                iSerie.putExtra("Lote", this.lote);
                iSerie.putExtra("Vencimiento", this.vencimiento);
                iSerie.putExtra("Atributo1", this.atributo1);
                iSerie.putExtra("Atributo2", this.atributo2);
                iSerie.putExtra("Atributo3", this.atributo3);
                iSerie.putStringArrayListExtra("series", (ArrayList<String>) this.series);
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
                iAgregar.putExtra("Lote", this.lote);
                iAgregar.putExtra("Vencimiento", this.vencimiento);
                iAgregar.putExtra("Atributo1", this.atributo1);
                iAgregar.putExtra("Atributo2", this.atributo2);
                iAgregar.putExtra("Atributo3", this.atributo3);
                iAgregar.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                startActivity(iAgregar);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
