package cl.clsoft.bave.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.control.InstantAutoCompleteTextView;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.EntregaAgregarPresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityEntregaAgregar extends BaseActivity<EntregaAgregarPresenter> {

    // Variables
    private String TAG = "EntregaAgregar";
    private Long shipmentHeaderId;
    private Long transactionId;
    private String subinventoryCode;
    private String locatorCode;
    private List<String> series;
    private boolean isLote = false;
    private boolean isSerie = false;
    private String segment;
    private Long inventoryItemId;
    private String currentSigle = "";
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;

    // Controls
    private TextInputLayout layoutSigle;
    private TextInputEditText textSigle;
    private ImageView iconSearch;
    private RelativeLayout rlayoutSigle;
    private RelativeLayout rlayoutItem;
    private TextView textProductoSigle;
    private TextView textProductoDescription;
    private TextView textProductoCantidad;
    private TextView textProductoLinea;
    private TextInputLayout layoutSubinventory;
    private InstantAutoCompleteTextView textSubinventory;
    private TextInputLayout layoutLocator;
    private InstantAutoCompleteTextView textLocator;
    private TextView textProductoLote;
    private TextView textProductoSerie;

    @NonNull
    @Override
    protected EntregaAgregarPresenter createPresenter(@NonNull Context context) {
        return new EntregaAgregarPresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSigle = findViewById(R.id.layoutSerie);
        this.textSigle = findViewById(R.id.textSigle);
        this.iconSearch = findViewById(R.id.iconSearch);
        this.rlayoutSigle = findViewById(R.id.rlayoutSigle);
        this.rlayoutItem = findViewById(R.id.rlayoutItem);
        this.textProductoSigle = findViewById(R.id.textProductoSigle);
        this.textProductoDescription = findViewById(R.id.textProductoDescription);
        this.textProductoCantidad = findViewById(R.id.textProductoCantidad);
        this.textProductoLinea = findViewById(R.id.textProductoLinea);
        this.layoutSubinventory = findViewById(R.id.layoutSubinventory);
        this.textSubinventory = findViewById(R.id.textSubinventory);
        this.layoutLocator = findViewById(R.id.layoutLocator);
        this.textLocator = findViewById(R.id.textLocator);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);

        // Set Controls
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.transactionId = this.getIntent().getLongExtra("TransactionId", 0);
        this.subinventoryCode = this.getIntent().getStringExtra("SubinventoryCode");
        this.locatorCode = this.getIntent().getStringExtra("LocatorCode");
        this.series = this.getIntent().getStringArrayListExtra("series");

        if (transactionId > 0) {
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
                    this.fillProducto(item.getDescription(), item.getSegment1(), transaction.getQuantity(),transaction.getLineNum(), this.isLote, this.isSerie);
                    this.layoutSubinventory.setVisibility(View.VISIBLE);
                    this.layoutLocator.setVisibility(View.VISIBLE);

                }
            }
        } else {
            this.rlayoutSigle.setVisibility(View.VISIBLE);
            this.rlayoutItem.setVisibility(View.GONE);
            this.layoutSubinventory.setVisibility(View.GONE);
            this.layoutLocator.setVisibility(View.GONE);
        }

        this.textSigle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        segment = textView.getText().toString();
                        Log.d(TAG, "sigle: " + segment);
                        Log.d(TAG, "currentSigle: " + currentSigle);

                        if (!segment.equalsIgnoreCase(currentSigle)) {
                            currentSigle = segment;
                            MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                            if (item != null) {
                                inventoryItemId = item.getInventoryItemId();
                            } else {
                                textSigle.setText("");
                                currentSigle = "";
                            }

                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        this.iconSearch.setOnClickListener(v -> {
            Intent i = new Intent(this, ActivitySigleSearch.class);
            startActivityForResult(i, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });

        this.textSubinventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                subinventoryCode = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "subinventoryCode: " + subinventoryCode);
                if (subinventoryCode != null) {
                    mPresenter.getLocalizadoresBySubinventario(subinventoryCode);
                }
            }
        });

        this.textLocator.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                locatorCode = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "locatorCode: " + locatorCode);
            }
        });

        List<Subinventario> subinventarios = this.mPresenter.getSubinventarios();
        if (subinventarios != null) {
            if (subinventarios.size() > 0) {
                String[] arrSubinventarios = new String[subinventarios.size()];
                for (int i = 0; i < subinventarios.size(); i++) {
                    arrSubinventarios[i] = subinventarios.get(i).getCodSubinventario();
                }
                ArrayAdapter<String> adapterSubinventario = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrSubinventarios);
                this.textSubinventory.setAdapter(adapterSubinventario);
                this.textSubinventory.setText(this.subinventoryCode);
                if (this.subinventoryCode != null && !this.subinventoryCode.isEmpty()) {
                    mPresenter.getLocalizadoresBySubinventario(subinventoryCode);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_entrega_agregar, menu);
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
            case R.id.limpiar:
                Log.d(TAG, "limpiar");
                this.cleanScreen();
                return true;
            case R.id.next:
                Log.d(TAG, "next");
                if (this.isLote) {
                    Intent iLote = new Intent(this, ActivityEntregaAgregarLote.class);
                    iLote.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iLote.putExtra("TransactionId", this.transactionId);
                    iLote.putExtra("SubinventoryCode", this.subinventoryCode);
                    iLote.putExtra("LocatorCode", this.locatorCode);
                    iLote.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                    startActivity(iLote);
                    this.finish();
                } else if (isSerie) {
                    Intent iSerie = new Intent(this, ActivityEntregaAgregarSerie.class);
                    iSerie.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iSerie.putExtra("TransactionId", this.transactionId);
                    iSerie.putExtra("SubinventoryCode", this.subinventoryCode);
                    iSerie.putExtra("LocatorCode", this.locatorCode);
                    iSerie.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                    startActivity(iSerie);
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SEARCHSINGLE_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                segment = data.getStringExtra("Segment1");
                inventoryItemId = data.getLongExtra("InventoryItemId", 0);
                this.textSigle.setText(segment);
                validaSigle();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    private void validaSigle() {
        if (this.inventoryItemId != null) {
            List<RcvTransactions> transactions = mPresenter.getTransaccionsByShipmentInventory(shipmentHeaderId, inventoryItemId);
            if (transactions.size() == 0) {
                this.textSigle.setText("");
                showWarning("Sigle " + segment + " no encontrado en OC");
                this.segment = null;
                this.inventoryItemId = null;
            } else {
                RcvTransactions rcvTransactions = transactions.get(0);
                this.transactionId = rcvTransactions.getTransactionId();
                MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                this.textProductoSigle.setText(segment);
                this.textProductoDescription.setText(item.getDescription());
                this.textProductoCantidad.setText(rcvTransactions.getQuantity().toString());
                this.textProductoLinea.setText(rcvTransactions.getLineNum().toString());
                if (item.getLotControlCode().equalsIgnoreCase("2")) {
                    this.isLote = true;
                    this.textProductoLote.setText("SI");
                } else {
                    this.isLote = false;
                    this.textProductoLote.setText("NO");
                }
                if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                    this.isSerie = true;
                    this.textProductoSerie.setText("SI");
                } else {
                    this.isSerie = false;
                    this.textProductoSerie.setText("NO");
                }

                this.rlayoutItem.setVisibility(View.VISIBLE);
                this.rlayoutSigle.setVisibility(View.GONE);
                this.layoutSubinventory.setVisibility(View.VISIBLE);
                this.layoutLocator.setVisibility(View.VISIBLE);
            }
        }
    }

    public void fillLocator(List<Localizador> localizadores) {
        if (localizadores != null) {
            if (localizadores.size() > 0) {
                String[] locators = new String[localizadores.size()];
                for (int i = 0; i < localizadores.size(); i++) {
                    locators[i] = localizadores.get(i).getCodLocalizador();
                }
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, locators);
                this.textLocator.setAdapter(adapterLocator);
                this.textLocator.setText(this.locatorCode);
                //this.hayLocalizador = true;
            } else {
                //this.textLocator.setVisibility(View.GONE);
                //this.layoutLocator.setVisibility(View.GONE);
                //this.textSigle.setEnabled(false);
                //this.hayLocalizador = false;
                //this.loadSigle();
            }
        }
    }

    private void fillProducto(String descripcion, String segment, Long cantidad, Long linea, boolean isLote, boolean isSerie) {
        this.rlayoutSigle.setVisibility(View.GONE);
        this.rlayoutItem.setVisibility(View.VISIBLE);
        this.textProductoDescription.setText(descripcion);
        this.textProductoSigle.setText(segment);
        this.textProductoCantidad.setText(cantidad.toString());
        this.textProductoLinea.setText(linea.toString());
        if (isLote)
            this.textProductoLote.setText("SI");
        else
            this.textProductoLote.setText("NO");
        if (isSerie)
            this.textProductoSerie.setText("SI");
        else
            this.textProductoSerie.setText("NO");
    }

    public void cleanScreen() {
        this.rlayoutSigle.setVisibility(View.VISIBLE);
        this.rlayoutItem.setVisibility(View.GONE);
        this.layoutSubinventory.setVisibility(View.GONE);
        this.layoutLocator.setVisibility(View.GONE);
        this.textSigle.setText("");

        this.segment = null;
        this.inventoryItemId = null;

    }
}
