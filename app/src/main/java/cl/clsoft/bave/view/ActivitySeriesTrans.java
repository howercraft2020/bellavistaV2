package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.presenter.SeriesTransPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivitySeriesTrans extends BaseActivity<SeriesTransPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    //Variables
    private String nroTraspaso;
    private String codigoSigle;
    private String subinvDesde;
    private String localizador;
    private String nroLote;
    private String glosa;
    private String subinventarioHasta;
    private String localizadorHasta;
    private String id;
    private String serie;
    private Double cantidad;
    private String currentSerie = "";
    private List<String> series = new ArrayList<>();

    //Controls
    private TextInputLayout layoutSerie;
    private AutoCompleteTextView textSerie;


    //private List<MtlSerialNumbersInterface> series;


    //Controls
    private RecyclerView recyclerViewSeries;
    private AdapterSeriesTrans adapter;

    @NonNull
    @Override
    protected SeriesTransPresenter createPresenter(@NonNull Context context) {
        return new SeriesTransPresenter(this, new TransSubinvService());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutSerie = findViewById(R.id.layoutSerie);
        textSerie = findViewById(R.id.textSerie);

        this.textSerie.setThreshold(1);

        nroTraspaso = getIntent().getStringExtra("nroTraspaso");
        glosa = getIntent().getStringExtra("glosa");
        codigoSigle = getIntent().getStringExtra("codigoSigle");
        subinvDesde = getIntent().getStringExtra("subinvDesde");
        localizador = getIntent().getStringExtra("localizador");
        subinventarioHasta = getIntent().getStringExtra("subinvHasta");
        localizadorHasta = getIntent().getStringExtra("localHasta");
        nroLote = getIntent().getStringExtra("nroLote");
        cantidad = getIntent().getDoubleExtra("cantidad",0.0);
        id = getIntent().getStringExtra("id");
        this.series = this.getIntent().getStringArrayListExtra("series");
        if (this.series == null)
            this.series = new ArrayList<>();

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewSeries = findViewById(R.id.recyclerViewSeries);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        // Set Controls
        this.recyclerViewSeries.setHasFixedSize(true);
        this.recyclerViewSeries.setLayoutManager(new LinearLayoutManager(this));

        //this.series = mPresenter.getAllSeries(Long.parseLong(id));
        this.adapter = new AdapterSeriesTrans(series);
        this.recyclerViewSeries.setAdapter(this.adapter);

        this.textSerie.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;

                if(actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        String newSerie = textView.getText().toString();
                        MtlOnhandQuantities transferencia = mPresenter.getSerieIngresada(codigoSigle,nroLote,subinvDesde,localizador,newSerie);

                        if (transferencia == null){
                             showWarning("Serie : " + newSerie + " ingresada no es valida.");
                            }
                        else {
                            if (!newSerie.equalsIgnoreCase(currentSerie)) {
                                currentSerie = newSerie;

                                // Valida que no exista la nueva serie.
                                boolean exist = false;
                                for (String serie : adapter.getSeries()){
                                    if(serie.equalsIgnoreCase(newSerie)){
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

                    }

                    action = true;
                }

                return action;
            }
        });

        this.mPresenter.getSeries(this.codigoSigle,this.nroLote,this.subinvDesde,this.localizador);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_serie_trans_subinv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(this,ActivityTransSubinvDest.class);
                i.putExtra("nroTraspaso", nroTraspaso);
                i.putExtra("glosa", glosa);
                i.putExtra("codigoSigle", codigoSigle);
                i.putExtra("subinvDesde", subinvDesde);
                i.putExtra("localizador", localizador);
                i.putExtra("subinvHasta", subinventarioHasta);
                i.putExtra("localHasta", localizadorHasta);
                i.putExtra("nroLote", nroLote);
                i.putExtra("cantidad", cantidad);
                i.putExtra("id", id);
                i.putStringArrayListExtra("series", (ArrayList<String>) adapter.getSeries());
                startActivity(i);
                this.finish();
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

    public void fillSerie(List<MtlOnhandQuantities> series) {
        if (series != null) {
            if (series.size() > 0){
                String[] seriesArray = new String[series.size()];
                for (int i = 0; i <series.size();i++){
                    seriesArray[i] = series.get(i).getSerialNumber();
                }
                ArrayAdapter<String> adapterSeries = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, seriesArray);
                this.textSerie.setAdapter(adapterSeries);
                this.textSerie.setText("");
            }
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("delete")) {
            this.adapter.deleteSeleccionados();
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}