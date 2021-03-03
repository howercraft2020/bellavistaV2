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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.CiclicoAgregarPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityCiclicoAgregar extends BaseActivity<CiclicoAgregarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private static final String TAG = "CiclicoAgregar";
    private Long inventarioCiclicoId;
    private String subinventarioId;
    private Long locatorId;
    private String locatorCodigo;
    private String segment;
    private String serie;
    private String lote;
    private Double cantidad;
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
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private ImageView iconSearch;

    @NonNull
    @Override
    protected CiclicoAgregarPresenter createPresenter(@NonNull Context context) {
        return new CiclicoAgregarPresenter(this, new AppTaskExecutor(this), new ConteoCiclicoService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutLocator = findViewById(R.id.layoutLocator);
        this.textLocator = findViewById(R.id.textLocator);
        this.layoutSigle = findViewById(R.id.layoutSigle);
        this.textSigle = findViewById(R.id.textSigle);
        this.layoutSerie = findViewById(R.id.layoutSerie);
        this.textSerie = findViewById(R.id.textSerie);
        this.layoutLote = findViewById(R.id.layoutLote);
        this.textLote = findViewById(R.id.textLote);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);
        this.iconSearch = findViewById(R.id.iconSearch);

        // set controls
        this.inventarioCiclicoId = this.getIntent().getLongExtra("ciclicosId",0);
        this.subinventarioId = this.getIntent().getStringExtra("subinventarioId");
        this.textLocator.setThreshold(1);
        this.textSigle.setThreshold(1);
        this.textSerie.setThreshold(1);
        this.textLote.setThreshold(1);

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
                        layoutSigle.setHintEnabled(true);
                        textSigle.setEnabled(true);
                        textSigle.requestFocus();
                    }
                }
            }
        });

        this.textSigle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        segment = textView.getText().toString();
                        MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                        if (item != null) {

                            textSigle.setEnabled(false);

                            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                                textLote.setEnabled(true);
                                layoutLote.setHintEnabled(true);
                                //fillLote();
                            } else {
                                textLote.setEnabled(false);
                                layoutLote.setHintEnabled(false);
                            }
                            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                                textSerie.setEnabled(true);
                                layoutSerie.setHintEnabled(true);
                                //fillSerie();
                            } else {
                                textSerie.setEnabled(false);
                                layoutSerie.setHintEnabled(false);
                            }
                            layoutCantidad.setHintEnabled(true);
                            textCantidad.setEnabled(true);
                            layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                        } else {
                            showWarning("Item " + segment + " no encontrado en tabla maestra");
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

        this.cleanScreen();
        this.mPresenter.getLocalizadoresBySubinventario(this.subinventarioId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_agregar_fisico_inventario, menu);
        return super.onCreateOptionsMenu(menu);
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
                Intent i = new Intent(this, ActivityCiclicoDetalle.class);
                i.putExtra("ciclicosId", this.inventarioCiclicoId);
                i.putExtra("subinventarioId", this.subinventarioId);
                startActivity(i);
                this.finish();
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
                this.textSigle.setText(segment);
                Log.d(TAG, "sigle: " + segment);
                MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                if (item != null) {
                    textSigle.setEnabled(false);

                    if (item.getLotControlCode().equalsIgnoreCase("2")) {
                        this.textLote.setEnabled(true);
                        this.layoutLote.setHintEnabled(true);
                        //fillLote();
                    } else {
                        this.textLote.setEnabled(false);
                        this.layoutLote.setHintEnabled(false);
                    }
                    if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                        this.textSerie.setEnabled(true);
                        this.layoutSerie.setHintEnabled(true);
                        //fillSerie();
                    } else {
                        this.textSerie.setEnabled(false);
                        this.layoutSerie.setHintEnabled(false);
                    }
                    layoutCantidad.setHintEnabled(true);
                    textCantidad.setEnabled(true);
                    layoutCantidad.setHint("Cantidad (" + item.getPrimaryUomCode() + ")");
                } else {
                    showWarning("Item " + segment + " no encontrado en tabla maestra");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                this.textSigle.setText("");
            }
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("grabar")) {
            mPresenter.grabarInventario(this.inventarioCiclicoId, this.subinventarioId, this.locatorId, this.segment, this.serie, this.lote, this.cantidad);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

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
                //this.loadSigle();
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
        Double cantidad = Double.valueOf(strCantidad);
        if (cantidad < 0) {
            this.textCantidad.setError("Ingrese una cantidad válida");
            return;
        }

        this.serie = this.textSerie.getText().toString();
        this.lote = this.textLote.getText().toString();
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

        this.layoutCantidad.setHintEnabled(false);
        this.textCantidad.setEnabled(false);
        this.textCantidad.setText("");
    }
}
