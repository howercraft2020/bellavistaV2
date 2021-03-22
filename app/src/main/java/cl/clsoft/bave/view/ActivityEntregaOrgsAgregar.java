package cl.clsoft.bave.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.control.InstantAutoCompleteTextView;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.EntregaOrgsAgregarPresenter;
import cl.clsoft.bave.service.impl.EntregaOrgsServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregaOrgsAgregar extends BaseActivity<EntregaOrgsAgregarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "EntregaOrgsAgregar";
    private Long shipmentHeaderId;
    private Long transactionId;
    private String subinventoryCode;
    private String locatorCode;
    private Double cantidad;
    private String lote;
    private String categoria;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private List<String> series;
    private boolean isLote = false;
    private boolean isSerie = false;
    private String segment;
    private Long inventoryItemId;
    private String currentSigle = "";
    CharSequence[] items;
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
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private TextInputLayout layoutSubinventory;
    private InstantAutoCompleteTextView textSubinventory;
    private TextInputLayout layoutLocator;
    private InstantAutoCompleteTextView textLocator;
    private TextView textProductoLote;
    private TextView textProductoSerie;

    @NonNull
    @Override
    protected EntregaOrgsAgregarPresenter createPresenter(@NonNull Context context) {
        return new EntregaOrgsAgregarPresenter(this, new AppTaskExecutor(this), new EntregaOrgsServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_orgs_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

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
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);
        this.layoutSubinventory = findViewById(R.id.layoutSubinventory);
        this.textSubinventory = findViewById(R.id.textSubinventory);
        this.layoutLocator = findViewById(R.id.layoutLocator);
        this.textLocator = findViewById(R.id.textLocator);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);

        // Set Controls
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.transactionId = this.getIntent().getLongExtra("TransactionId", 0);
        this.cantidad = this.getIntent().getDoubleExtra("Cantidad", 0);
        this.subinventoryCode = this.getIntent().getStringExtra("SubinventoryCode");
        this.locatorCode = this.getIntent().getStringExtra("LocatorCode");
        this.lote = this.getIntent().getStringExtra("Lote");
        this.categoria = this.getIntent().getStringExtra("Categoria");
        this.atributo1 = this.getIntent().getStringExtra("Atributo1");
        this.atributo2 = this.getIntent().getStringExtra("Atributo2");
        this.atributo3 = this.getIntent().getStringExtra("Atributo3");
        this.series = this.getIntent().getStringArrayListExtra("series");

        if (transactionId > 0) {
            MtlMaterialTransactions transaction = mPresenter.getTransactionById(this.transactionId);
            if (transaction != null) {
                MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getInventoryItemId());
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
                    this.fillProducto(item.getDescription(), item.getSegment1(), transaction.getTransactionQuantity(), this.isLote, this.isSerie);
                    this.layoutCantidad.setVisibility(View.VISIBLE);
                    this.layoutSubinventory.setVisibility(View.VISIBLE);
                    this.layoutLocator.setVisibility(View.VISIBLE);

                }
            }
        } else {
            this.rlayoutSigle.setVisibility(View.VISIBLE);
            this.rlayoutItem.setVisibility(View.GONE);
            this.layoutCantidad.setVisibility(View.GONE);
            this.layoutSubinventory.setVisibility(View.GONE);
            this.layoutLocator.setVisibility(View.GONE);
        }

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

        this.textCantidad.setText(this.cantidad.toString());

    }

    @Override
    public void onBackPressed() {
        this.confirmacionSalir();
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
                this.confirmacionSalir();
                return true;
            case R.id.limpiar:
                Log.d(TAG, "limpiar");
                this.cleanScreen();
                return true;
            case R.id.next:
                Log.d(TAG, "next");
                if (this.validaDatos()) {
                    if (this.isLote) {
                        Intent iLote = new Intent(this, ActivityEntregaOrgsAgregarLote.class);
                        iLote.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                        iLote.putExtra("TransactionId", this.transactionId);
                        iLote.putExtra("Cantidad", this.cantidad);
                        iLote.putExtra("SubinventoryCode", this.subinventoryCode);
                        iLote.putExtra("LocatorCode", this.locatorCode);
                        iLote.putExtra("Lote", this.lote);
                        iLote.putExtra("Categoria", this.categoria);
                        iLote.putExtra("Atributo1", this.atributo1);
                        iLote.putExtra("Atributo2", this.atributo2);
                        iLote.putExtra("Atributo3", this.atributo3);
                        iLote.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                        startActivity(iLote);
                        this.finish();
                    } else if (isSerie) {
                        Intent iSerie = new Intent(this, ActivityEntregaOrgsAgregarSerie.class);
                        iSerie.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                        iSerie.putExtra("TransactionId", this.transactionId);
                        iSerie.putExtra("Cantidad", this.cantidad);
                        iSerie.putExtra("SubinventoryCode", this.subinventoryCode);
                        iSerie.putExtra("LocatorCode", this.locatorCode);
                        iSerie.putExtra("Lote", this.lote);
                        iSerie.putExtra("Categoria", this.categoria);
                        iSerie.putExtra("Atributo1", this.atributo1);
                        iSerie.putExtra("Atributo2", this.atributo2);
                        iSerie.putExtra("Atributo3", this.atributo3);
                        iSerie.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                        startActivity(iSerie);
                        this.finish();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("exit")) {
            Intent i = new Intent(this, ActivityEntregasOrgsDetalle.class);
            i.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
            startActivity(i);
            this.finish();
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

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

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    private void validaSigle() {
        if (this.inventoryItemId != null) {
            List<MtlMaterialTransactions> transactions = mPresenter.getTransaccionsByShipmentInventory(shipmentHeaderId, inventoryItemId);
            if (transactions.size() == 0) {
                this.textSigle.setText("");
                showWarning("Sigle " + segment + " no encontrado en Recepción");
                this.segment = null;
                this.inventoryItemId = null;
            } else {
                this.setSigle(transactions.get(0));
            }
        }
    }

    private void setSigle(MtlMaterialTransactions transaction) {
        this.transactionId = transaction.getTransactionId();
        MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
        if (item != null) {
            this.textProductoSigle.setText(segment);
            this.textProductoDescription.setText(item.getDescription());
            this.textProductoCantidad.setText(transaction.getTransactionQuantity().toString());
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
            this.layoutCantidad.setVisibility(View.VISIBLE);
            this.layoutSubinventory.setVisibility(View.VISIBLE);
            this.layoutLocator.setVisibility(View.VISIBLE);
            this.textCantidad.setText(transaction.getTransactionQuantity().toString());
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

    private boolean validaDatos() {

        // Valida Sigle
        if (this.transactionId == null) {
            return false;
        }

        // Valida Cantidad.
        if (this.textCantidad.getText() == null) {
            this.textCantidad.setError("ingrese la cantidad");
            return false;
        }
        if (this.textCantidad.getText().toString().isEmpty()) {
            this.textCantidad.setError("ingrese la cantidad");
            return false;
        }
        this.cantidad = Double.valueOf(this.textCantidad.getText().toString());

        // Valida Subinventario
        if (this.textSubinventory.getText() == null) {
            this.textSubinventory.setError("ingrese el subinventario");
            return false;
        }
        if (this.textSubinventory.getText().toString().isEmpty()) {
            this.textSubinventory.setError("ingrese el subinventario");
            return false;
        }
        this.subinventoryCode = this.textSubinventory.getText().toString();

        // Valida Localizador
        if (this.textLocator.getText() == null) {
            this.textLocator.setError("ingrese el localizador");
            return false;
        }
        if (this.textLocator.getText().toString().isEmpty()) {
            this.textLocator.setError("ingrese el localizador");
            return false;
        }
        this.locatorCode = this.textLocator.getText().toString();

        return true;
    }

    private void fillProducto(String descripcion, String segment, Double cantidad, boolean isLote, boolean isSerie) {
        this.rlayoutSigle.setVisibility(View.GONE);
        this.rlayoutItem.setVisibility(View.VISIBLE);
        this.textProductoDescription.setText(descripcion);
        this.textProductoSigle.setText(segment);
        this.textProductoCantidad.setText(cantidad.toString());
        if (isLote)
            this.textProductoLote.setText("SI");
        else
            this.textProductoLote.setText("NO");
        if (isSerie)
            this.textProductoSerie.setText("SI");
        else
            this.textProductoSerie.setText("NO");
    }

    private void cleanScreen() {
        this.rlayoutSigle.setVisibility(View.VISIBLE);
        this.rlayoutItem.setVisibility(View.GONE);
        this.layoutCantidad.setVisibility(View.GONE);
        this.layoutSubinventory.setVisibility(View.GONE);
        this.layoutLocator.setVisibility(View.GONE);
        this.textSigle.setText("");

        // Reinicia Variables
        this.transactionId = null;
        this.segment = null;
        this.inventoryItemId = null;
        this.subinventoryCode = null;
        this.textSubinventory.setText("");
        this.locatorCode = null;
        this.textLocator.setText("");
        this.cantidad = null;
        this.textCantidad.setText("");

        this.lote = null;
        this.categoria = null;
        this.atributo1 = null;
        this.atributo2 = null;
        this.atributo3 = null;

        this.series = new ArrayList<>();

    }

}
