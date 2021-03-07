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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.EntityReference;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.presenter.EntregaAgregarSeriePresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregaAgregarSerie extends BaseActivity<EntregaAgregarSeriePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "EntregaAgregarSerie";
    private Long shipmentHeaderId;
    private Long transactionId;
    private String subinventoryCode;
    private String locatorCode;
    private String lote;
    private String vencimiento;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private List<String> series = new ArrayList<>();
    private String currentSerie = "";
    private boolean isLote = false;
    private boolean isSerie = false;
    private Long cantidad;

    // Controls
    private TextInputLayout layoutSerie;
    private TextInputEditText textSerie;
    private RecyclerView recyclerViewSeries;
    private AdapterItemSerie adapter;

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
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSerie = findViewById(R.id.layoutSerie);
        this.textSerie = findViewById(R.id.textSerie);
        this.recyclerViewSeries = findViewById(R.id.recyclerViewSeries);

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
        if (this.series == null)
            this.series = new ArrayList<>();

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
                cantidad = transaction.getQuantity();
            }
        }

        this.textSerie.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        String newSerie = textView.getText().toString();
                        if (!newSerie.equalsIgnoreCase(currentSerie)) {
                            currentSerie = newSerie;

                            // Valida que no exista la nueva serie.
                            boolean exist = false;
                            for (String serie : adapter.getSeries()) {
                                if (serie.equalsIgnoreCase(newSerie)) {
                                    exist = true;
                                }
                            }
                            if (exist) {
                                showWarning("Serie " + newSerie + " ya existe.");
                                textSerie.setText("");
                                return true;
                            }

                            if (adapter.getSeries().size() == cantidad.intValue()) {
                                showWarning("Ya ingreso la cantidad de series necesarias: " + cantidad);
                                textSerie.setText("");
                                return true;
                            }
                            adapter.addSerie(textView.getText().toString());
                            textSerie.setText("");
                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        this.recyclerViewSeries.setHasFixedSize(true);
        this.recyclerViewSeries.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterItemSerie(this.series);
        this.recyclerViewSeries.setAdapter(adapter);
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
                ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
                dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
                return true;
            case R.id.next:
                Log.d(TAG, "next");
                Intent iResumen = new Intent(this, ActivityEntregaAgregarResumen.class);
                iResumen.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                iResumen.putExtra("TransactionId", this.transactionId);
                iResumen.putExtra("SubinventoryCode", this.subinventoryCode);
                iResumen.putExtra("LocatorCode", this.locatorCode);
                iResumen.putExtra("Lote", this.lote);
                iResumen.putExtra("Vencimiento", this.vencimiento);
                iResumen.putExtra("Atributo1", this.atributo1);
                iResumen.putExtra("Atributo2", this.atributo2);
                iResumen.putExtra("Atributo3", this.atributo3);
                iResumen.putStringArrayListExtra("series", (ArrayList<String>) adapter.getSeries());
                startActivity(iResumen);
                this.finish();
                return true;
            case R.id.back:
                Log.d(TAG, "back");
                if (this.isLote) {
                    Intent iLote = new Intent(this, ActivityEntregaAgregarLote.class);
                    iLote.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iLote.putExtra("TransactionId", this.transactionId);
                    iLote.putExtra("SubinventoryCode", this.subinventoryCode);
                    iLote.putExtra("LocatorCode", this.locatorCode);
                    iLote.putExtra("Lote", this.lote);
                    iLote.putExtra("Vencimiento", this.vencimiento);
                    iLote.putExtra("Atributo1", this.atributo1);
                    iLote.putExtra("Atributo2", this.atributo2);
                    iLote.putExtra("Atributo3", this.atributo3);
                    iLote.putStringArrayListExtra("series", (ArrayList<String>) adapter.getSeries());
                    startActivity(iLote);
                    this.finish();
                } else {
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
                    iAgregar.putStringArrayListExtra("series", (ArrayList<String>) adapter.getSeries());
                    startActivity(iAgregar);
                    this.finish();
                }
                return true;
            case R.id.delete:
                Log.d(TAG, "seleccionados " + adapter.getSeriesSeleccionados().size());
                if (adapter.getSeriesSeleccionados().size() > 0) {
                    ConfirmationDialog dialogDelete = ConfirmationDialog.newInstance("Esta seguro de eliminar las serie(s) seleccionada(s)?", "Confirmación", "delete");
                    dialogDelete.show(getSupportFragmentManager(), "deleteSerieConfirm");
                } else {
                    showWarning("Debe seleccionar al menos una serie.");
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("delete")) {
            this.adapter.deleteSeleccionados();
        } else if (tipo.equalsIgnoreCase("exit")) {
            Intent i = new Intent(this, ActivityEntregaDetalle.class);
            i.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
            startActivity(i);
            this.finish();
        }

    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}
