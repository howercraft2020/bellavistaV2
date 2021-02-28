package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.SeriesTransPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivitySeriesTrans extends BaseActivity<SeriesTransPresenter> {

    private TextView nroTraspasoEt, glosaEt, codigoSigleEt, subinvDesdeEt, localDesdeEt, loteEt, cantidadEt, subinvHastaEt, localHastaEt;
    private String nroTraspaso, codigoSigle, subinvDesde, localizador, nroLote, glosa, subinventarioHasta, localizadorHasta;
    private Long cantidad;

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

        nroTraspasoEt = (TextView) findViewById(R.id.numeroTraspasoEditText);
        glosaEt = (TextView) findViewById(R.id.glosaEditText);
        codigoSigleEt = (TextView) findViewById(R.id.codigoSigleEditText);
        subinvDesdeEt = (TextView) findViewById(R.id.subinventarioDesdeEditText);
        localDesdeEt = (TextView) findViewById(R.id.localizadorDesdeEditText);
        loteEt = (TextView) findViewById(R.id.nroLoteEditText);
        cantidadEt = (TextView) findViewById(R.id.cantidadEditText);
        subinvHastaEt = (TextView) findViewById(R.id.subinventarioHastaEditText);
        localHastaEt = (TextView) findViewById(R.id.localizadorHastaEditText);

        nroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        glosa = getIntent().getStringExtra("glosa");
        codigoSigle = getIntent().getStringExtra("codigoSigle");
        subinvDesde = getIntent().getStringExtra("subinvDesde");
        localizador = getIntent().getStringExtra("localizador");
        subinventarioHasta = getIntent().getStringExtra("subinventarioHasta");
        localizadorHasta = getIntent().getStringExtra("localizadorHasta");
        nroLote = getIntent().getStringExtra("nroLote");
        cantidad = getIntent().getLongExtra("cantidad",0);

        nroTraspasoEt.setText(nroTraspaso);
        glosaEt.setText(glosa);
        codigoSigleEt.setText(codigoSigle);
        subinvDesdeEt.setText(subinvDesde);
        localDesdeEt.setText(localizador);
        loteEt.setText(nroLote);
        cantidadEt.setText(String.valueOf(cantidad));
        subinvHastaEt.setText(subinventarioHasta);
        localHastaEt.setText(localizadorHasta);



    }
}