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
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.FisicoAgregarPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityFisicoAgregar extends BaseActivity<FisicoAgregarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityAgregarFisicoInventario";
    private Long inventarioId;
    private String subinventarioCodigo;
    private Long locatorId;
    private String locatorCodigo;
    private String segment;
    private String serie;
    private String lote;
    private String vencimiento;
    private Long cantidad;
    private List<MtlPhysicalInventoryTags> tags;
    private boolean hayLocalizador = false;
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;

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
    private ImageView iconSearch;

    @NonNull
    @Override
    protected FisicoAgregarPresenter createPresenter(@NonNull Context context) {
        return new FisicoAgregarPresenter(this, new AppTaskExecutor(this), new InventarioFisicoService());
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        this.subinventarioCodigo = this.getIntent().getStringExtra("SubinventarioId");
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
        this.iconSearch = findViewById(R.id.iconSearch);

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
                locatorCodigo = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "locatorCodigo: " + locatorCodigo);
                if (locatorCodigo != null && !locatorCodigo.equalsIgnoreCase("SIN LOCALIZADOR")) {
                    Localizador localizador = mPresenter.getLocalizadorbyCodigo(locatorCodigo);
                    if (localizador != null) {
                        locatorId = localizador.getIdLocalizador();
                    }
                }
                loadSigle();

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

        this.iconSearch.setOnClickListener(v -> {
            Intent i = new Intent(this, ActivitySigleSearch.class);
            startActivityForResult(i, LAUNCH_SEARCHSINGLE_ACTIVITY);
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
        //this.fillLocator();
        mPresenter.getLocalizadoresBySubinventario(this.subinventarioCodigo);
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
                this.cleanScreen();
                return true;
            case R.id.guardarInventario:
                this.grabarInventario();
                return true;
            case android.R.id.home:
                if(inventarioId != null && subinventarioCodigo != null){
                    Log.d(TAG, "Agregar inventario tag");
                    Intent i = new Intent(this, ActivityFisicoDetalle.class);
                    i.putExtra("InventarioId", inventarioId);
                    i.putExtra("SubinventarioId", subinventarioCodigo);
                    startActivity(i);
                    this.finish();
                    return true;
                }else if(inventarioId != null && subinventarioCodigo == null){
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SEARCHSINGLE_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                segment = data.getStringExtra("Segment1");
                this.textSigle.setText(segment);
                Log.d(TAG, "sigle: " + segment);
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
            if (resultCode == Activity.RESULT_CANCELED) {
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
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, locators);
                this.textLocator.setAdapter(adapterLocator);
                this.textLocator.setText("");
                this.hayLocalizador = true;
            } else {
                this.textLocator.setVisibility(View.GONE);
                this.layoutLocator.setVisibility(View.GONE);
                this.textSigle.setEnabled(false);
                this.hayLocalizador = false;
                this.loadSigle();
            }
        }
    }

    public void loadSigle() {
        this.mPresenter.getSegment1(inventarioId, subinventarioCodigo, locatorCodigo);
    }

    public void fillSigle(List<String> listSigles) {
        //List<String> listSigles = this.mPresenter.getSegment1(inventarioId, subinventarioCodigo, locatorCodigo);
        if (listSigles != null) {
            if (listSigles.size() > 0) {
                String[] sigles = new String[listSigles.size()];
                for (int i = 0; i < listSigles.size(); i++) {
                    sigles[i] = listSigles.get(i);
                }
                ArrayAdapter<String> adapterSerie = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, sigles);
                this.textSigle.setAdapter(adapterSerie);
                this.textSigle.setEnabled(true);
                this.layoutSigle.setHintEnabled(true);
                this.textSigle.requestFocus();
            } else {
                this.showWarning("No se encontraron productos");
                this.textLocator.setText("");
                this.textLocator.requestFocus();
            }
        } else {
            this.showWarning("No se encontraron productos");
            this.textLocator.setText("");
            this.textLocator.requestFocus();
        }
    }

    public void fillSerie() {
        List<String> listSeries = this.mPresenter.getSeries(inventarioId, subinventarioCodigo, locatorId, segment);
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
        List<String> listLotes = this.mPresenter.getLotes(inventarioId, subinventarioCodigo, locatorId, segment);
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
        List<String> listVencimientos = this.mPresenter.getVencimientos(inventarioId, subinventarioCodigo, locatorId, segment);
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

        this.textLocator.setText("");
        this.locatorCodigo = null;
        this.locatorId = null;

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
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("grabar")) {
            mPresenter.grabarInventario(this.inventarioId, this.subinventarioCodigo, this.locatorId, this.segment, this.serie, this.lote, this.vencimiento, this.cantidad);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}
