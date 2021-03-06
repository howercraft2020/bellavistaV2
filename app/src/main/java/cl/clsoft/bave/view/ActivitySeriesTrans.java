package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.presenter.SeriesTransPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivitySeriesTrans extends BaseActivity<SeriesTransPresenter> {

    private TextView nroTraspasoEt, glosaEt, codigoSigleEt, subinvDesdeEt, localDesdeEt, loteEt, cantidadEt, subinvHastaEt, localHastaEt;
    private String nroTraspaso, codigoSigle, subinvDesde, localizador, nroLote, glosa, subinventarioHasta, localizadorHasta, id, serie;
    private Long cantidad;
    private List<MtlSerialNumbersInterface> series;
    private TextInputLayout layoutSerie;
    private AutoCompleteTextView textSerie;

    //Controls
    private RecyclerView recyclerViewSeries;
    private AdapterSeriesTrans adapter;

    @NonNull
    @Override
    protected SeriesTransPresenter createPresenter(@NonNull Context context) {
        return new SeriesTransPresenter(this, new TransSubinvService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_serie_trans_subinv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nroTraspasoEt = (TextView) findViewById(R.id.numeroTraspasoEditText);
        glosaEt = (TextView) findViewById(R.id.glosaEditText);
        codigoSigleEt = (TextView) findViewById(R.id.codigoSigleEditText);
        subinvDesdeEt = (TextView) findViewById(R.id.subinventarioDesdeEditText);
        localDesdeEt = (TextView) findViewById(R.id.localizadorDesdeEditText);
        loteEt = (TextView) findViewById(R.id.nroLoteEditText);
        cantidadEt = (TextView) findViewById(R.id.cantidadEditText);
        subinvHastaEt = (TextView) findViewById(R.id.subinventarioHastaEditText);
        localHastaEt = (TextView) findViewById(R.id.localizadorHastaEditText);
        layoutSerie = findViewById(R.id.layoutSerie);
        textSerie = findViewById(R.id.textSerie);

        this.textSerie.setThreshold(1);

        nroTraspaso = getIntent().getStringExtra("nroTraspaso");
        glosa = getIntent().getStringExtra("glosa");
        codigoSigle = getIntent().getStringExtra("codigoSigle");
        subinvDesde = getIntent().getStringExtra("subinvDesde");
        localizador = getIntent().getStringExtra("localizador");
        subinventarioHasta = getIntent().getStringExtra("subinventarioHasta");
        localizadorHasta = getIntent().getStringExtra("localizadorHasta");
        nroLote = getIntent().getStringExtra("nroLote");
        cantidad = getIntent().getLongExtra("cantidad",0);
        id = getIntent().getStringExtra("id");


        nroTraspasoEt.setText(nroTraspaso);
        glosaEt.setText(glosa);
        codigoSigleEt.setText(codigoSigle);
        subinvDesdeEt.setText(subinvDesde);
        localDesdeEt.setText(localizador);
        loteEt.setText(nroLote);
        cantidadEt.setText(String.valueOf(cantidad));
        subinvHastaEt.setText(subinventarioHasta);
        localHastaEt.setText(localizadorHasta);

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

        this.series = mPresenter.getAllSeries(Long.parseLong(id));
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
                        serie = textView.getText().toString();
                        mPresenter.cargaSerie(codigoSigle,nroLote,subinvDesde,localizador,serie,cantidad,Long.parseLong(id));
                    }

                    action = true;
                }

                return action;
            }
        });

        this.mPresenter.getSeries(this.codigoSigle,this.nroLote,this.subinvDesde,this.localizador);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(this,ActivityTransSubinvDest.class);
                i.putExtra("nroTraspaso", nroTraspaso);
                i.putExtra("glosa", glosa);
                i.putExtra("codSigle", codigoSigle);
                i.putExtra("subinvdesde", subinvDesde);
                i.putExtra("localizador", localizador);
                i.putExtra("subinvHasta", subinventarioHasta);
                i.putExtra("localHasta", localizadorHasta);
                i.putExtra("nroLote", nroLote);
                i.putExtra("cantidad", cantidad);
                i.putExtra("id", id);
                startActivity(i);
                this.finish();
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

}