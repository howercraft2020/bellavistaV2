package cl.clsoft.bave.view;

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
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.FisicoAgregarPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityFisicoAgregar extends BaseActivity<FisicoAgregarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityAgregarFisicoInventario";
    private Long inventarioId;
    private String subinventarioId;
    private Long locatorId;
    private String segment;
    private String serie;
    private String lote;
    private String vencimiento;
    private Long cantidad;
    private List<MtlPhysicalInventoryTags> tags;
    private boolean hayLocalizador = false;

    // Controls
    private TextInputLayout layoutLocator;
    private AutoCompleteTextView textLocator;
    private TextInputLayout layoutSigle;
    private AutoCompleteTextView textSigle;
    private TextInputLayout layoutSerie;
    private AutoCompleteTextView textSerie;
    private TextInputLayout layoutLote;
    private AutoCompleteTextView textLote;
    private TextInputLayout layoutFechaVencimiento;
    private AutoCompleteTextView textFechaVencimiento;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;

    @NonNull
    @Override
    protected FisicoAgregarPresenter createPresenter(@NonNull Context context) {
        return new FisicoAgregarPresenter(this, new InventarioFisicoService());
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        this.subinventarioId = this.getIntent().getStringExtra("SubinventarioId");
        this.layoutLocator = findViewById(R.id.layoutLocator);
        this.textLocator = findViewById(R.id.textLocator);
        this.layoutSigle = findViewById(R.id.layoutSigle);
        this.textSigle = findViewById(R.id.textSigle);
        this.layoutSerie = findViewById(R.id.layoutSerie);
        this.textSerie = findViewById(R.id.textSerie);
        this.layoutLote = findViewById(R.id.layoutLote);
        this.textLote = findViewById(R.id.textLote);
        this.layoutFechaVencimiento = findViewById(R.id.layoutFechaVencimiento);
        this.textFechaVencimiento = findViewById(R.id.textFechaVencimiento);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);

        //set controls
        this.textLocator.setThreshold(1);
        this.textSigle.setThreshold(1);
        this.textSerie.setThreshold(1);
        this.textLote.setThreshold(1);
        this.textFechaVencimiento.setThreshold(1);

        this.textLocator.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Toast.makeText(ActivityFisicoAgregar.this," selected", Toast.LENGTH_LONG).show();

            }
        });

        this.textSigle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                Log.d(TAG, "pos : " + pos);
                textSigle.setEnabled(false);
                segment = parent.getAdapter().getItem(pos).toString();
                MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                if (item != null) {
                    if (item.getLotControlCode().equalsIgnoreCase("2")) {
                        fillLote();
                    }
                    if (item.getShelfLifeCode().equalsIgnoreCase("2") || item.getShelfLifeCode().equalsIgnoreCase("4")) {
                        fillVencimiento();
                    }
                    if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                        fillSerie();
                    }
                    layoutCantidad.setHintEnabled(true);
                    textCantidad.setEnabled(true);
                    layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                } else {
                    showWarning("Item " + segment + " no encontrado en tabla maestra");
                }
            }
        });

        this.layoutSigle.setHintEnabled(false);
        this.textSigle.setEnabled(false);
        this.layoutSerie.setHintEnabled(false);
        this.textSerie.setEnabled(false);
        this.layoutLote.setHintEnabled(false);
        this.textLote.setEnabled(false);
        this.layoutFechaVencimiento.setHintEnabled(false);
        this.textFechaVencimiento.setEnabled(false);
        this.layoutCantidad.setHintEnabled(false);
        this.textCantidad.setEnabled(false);
        this.fillLocator();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_activity_agregar_fisico_inventario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clean:
                String[] vencimientos = new String[0];
                ArrayAdapter<String> adapterClear = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, vencimientos);

                this.layoutSigle.setHintEnabled(false);
                this.textSigle.setEnabled(false);
                this.textSigle.setAdapter(adapterClear);
                this.textSigle.setText("");

                this.layoutSerie.setHintEnabled(false);
                this.textSerie.setEnabled(false);
                this.textSerie.setAdapter(adapterClear);
                this.textSerie.setText("");

                this.layoutLote.setHintEnabled(false);
                this.textLote.setEnabled(false);
                this.textLote.setAdapter(adapterClear);
                this.textLote.setText("");

                this.layoutFechaVencimiento.setHintEnabled(false);
                this.textFechaVencimiento.setEnabled(false);
                this.textFechaVencimiento.setAdapter(adapterClear);
                this.textFechaVencimiento.setText("");

                this.layoutCantidad.setHintEnabled(false);
                this.textCantidad.setEnabled(false);
                this.textCantidad.setText("");

                this.fillLocator();
                return true;
            case R.id.guardarInventario:
                this.grabarInventario();
                return true;
            case android.R.id.home:
                if(inventarioId != null && subinventarioId != null){
                    Log.d(TAG, "Agregar inventario tag");
                    Intent i = new Intent(this, ActivityFisicoDetalle.class);
                    i.putExtra("InventarioId", inventarioId);
                    i.putExtra("SubinventarioId", subinventarioId);
                    startActivity(i);
                    this.finish();
                    return true;
                }else if(inventarioId != null && subinventarioId == null){
                    Log.d(TAG, "Agregar inventario subinventario");
                    Intent i = new Intent(this, ActivityFisicoSub.class);
                    i.putExtra("InventarioId", inventarioId);
                    startActivity(i);
                    this.finish();
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillLocator() {
        List<String> listLocators = this.mPresenter.getLocator(inventarioId, subinventarioId);
        if (listLocators != null) {
            if (listLocators.size() > 0) {
                String[] locators = new String[listLocators.size()];
                for (int i = 0; i < listLocators.size(); i++) {
                    locators[i] = listLocators.get(i);
                }
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, locators);
                this.textLocator.setAdapter(adapterLocator);
                this.hayLocalizador = true;
            } else {
                this.textLocator.setVisibility(View.GONE);
                this.layoutLocator.setVisibility(View.GONE);
                this.hayLocalizador = false;
                this.fillSigle();
            }
        }
    }

    public void fillSigle() {
        List<String> listSigles = this.mPresenter.getSegment1(inventarioId, subinventarioId, locatorId);
        if (listSigles != null) {
            String[] sigles = new String[listSigles.size()];
            for (int i = 0; i < listSigles.size(); i++) {
                sigles[i] = listSigles.get(i);
            }
            ArrayAdapter<String> adapterSerie = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, sigles);
            this.textSigle.setAdapter(adapterSerie);
            this.textSigle.setEnabled(true);
            this.layoutSigle.setHintEnabled(true);
        }
    }

    public void fillSerie() {
        List<String> listSeries = this.mPresenter.getSeries(inventarioId, subinventarioId, locatorId, segment);
        if (listSeries != null) {
            if (listSeries.size() > 0) {
                String[] series = new String[listSeries.size()];
                for (int i = 0; i < listSeries.size(); i++) {
                    series[i] = listSeries.get(i);
                }
                ArrayAdapter<String> adapterSerie = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, series);
                this.textSerie.setAdapter(adapterSerie);
                this.textSerie.setEnabled(true);
                this.layoutSerie.setHintEnabled(true);
            } else {
                this.textSerie.setEnabled(false);
                this.layoutSerie.setHintEnabled(false);
            }
        }
    }

    public void fillLote() {
        List<String> listLotes = this.mPresenter.getLotes(inventarioId, subinventarioId, locatorId, segment);
        if (listLotes != null) {
            if (listLotes.size() > 0) {
                String[] lotes = new String[listLotes.size()];
                for (int i = 0; i < listLotes.size(); i++) {
                    lotes[i] = listLotes.get(i);
                }
                ArrayAdapter<String> adapterLote = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, lotes);
                this.textLote.setAdapter(adapterLote);
                this.textLote.setEnabled(true);
                this.layoutLote.setHintEnabled(true);
            } else {
                this.textLote.setEnabled(false);
                this.layoutLote.setHintEnabled(false);
            }
        }
    }

    public void fillVencimiento() {
        List<String> listVencimientos = this.mPresenter.getVencimientos(inventarioId, subinventarioId, locatorId, segment);
        if (listVencimientos != null) {
            if (listVencimientos.size() > 0) {
                String[] vencimientos = new String[listVencimientos.size()];
                for (int i = 0; i < listVencimientos.size(); i++) {
                    vencimientos[i] = listVencimientos.get(i);
                }
                ArrayAdapter<String> adapterVencimiento = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, vencimientos);
                this.textFechaVencimiento.setAdapter(adapterVencimiento);
                this.textFechaVencimiento.setEnabled(true);
                this.layoutFechaVencimiento.setHintEnabled(true);
            } else {
                this.textFechaVencimiento.setEnabled(false);
                this.layoutFechaVencimiento.setHintEnabled(false);
            }
        }
    }

    public void grabarInventario() {
        // Validaciones

        String strCantidad = this.textCantidad.getText().toString();
        if (strCantidad.isEmpty()) {
            this.textCantidad.setError("Ingrese la cantidad");
            return;
        }
        Long cantidad = Long.valueOf(strCantidad);
        if (cantidad < 0) {
            this.textCantidad.setError("Ingrese una cantidad válida");
            return;
        }

        this.serie = this.textSerie.getText().toString();
        this.lote = this.textLote.getText().toString();
        this.vencimiento = this.textFechaVencimiento.getText().toString();
        this.cantidad = cantidad;
        // Confirmacion
        ConfirmationDialog dialogGrabar = ConfirmationDialog.newInstance("Esta seguro de grabar el inventario?", "Confirmación", "grabar");
        dialogGrabar.show(getSupportFragmentManager(), "grabarinventarioConfirm");

    }

    public void cleanScreen() {
        String[] vencimientos = new String[0];
        ArrayAdapter<String> adapterClear = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, vencimientos);

        this.layoutSigle.setHintEnabled(false);
        this.textSigle.setEnabled(false);
        this.textSigle.setAdapter(adapterClear);
        this.textSigle.setText("");

        this.layoutSerie.setHintEnabled(false);
        this.textSerie.setEnabled(false);
        this.textSerie.setAdapter(adapterClear);
        this.textSerie.setText("");

        this.layoutLote.setHintEnabled(false);
        this.textLote.setEnabled(false);
        this.textLote.setAdapter(adapterClear);
        this.textLote.setText("");

        this.layoutFechaVencimiento.setHintEnabled(false);
        this.textFechaVencimiento.setEnabled(false);
        this.textFechaVencimiento.setAdapter(adapterClear);
        this.textFechaVencimiento.setText("");

        this.layoutCantidad.setHintEnabled(false);
        this.textCantidad.setEnabled(false);
        this.textCantidad.setText("");

        this.fillLocator();
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("grabar")) {
            mPresenter.grabarInventario(this.inventarioId, this.subinventarioId, this.locatorId, this.segment, this.serie, this.lote, this.vencimiento, this.cantidad);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}
