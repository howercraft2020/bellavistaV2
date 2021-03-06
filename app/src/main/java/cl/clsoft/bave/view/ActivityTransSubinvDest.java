package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.TransSubinvDestPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivityTransSubinvDest extends BaseActivity<TransSubinvDestPresenter> {

    private TextView nroTraspasoEt, glosaEt, codigoSigleEt, subinvDesdeEt, localDesdeEt, loteEt, cantidadEt;
    private String nroTraspaso, codigoSigle, subinvDesde, localizador, nroLote, glosa, subinvHasta, localHasta, id;
    Long cantidad;
    private TextInputLayout layoutSubinventarioDestino;
    private AutoCompleteTextView textSubinventarioDestino;
    private TextInputLayout layoutLocalizadorDestino;
    private AutoCompleteTextView textLocalizadorDestino;

    @NonNull
    @Override
    protected TransSubinvDestPresenter createPresenter(@NonNull Context context) {
        return new TransSubinvDestPresenter(this, new TransSubinvService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_trans_subinv_dest, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_subinv_dest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());

        this.nroTraspasoEt = (TextView) findViewById(R.id.numeroTraspasoEditText);
        this.glosaEt = (TextView) findViewById(R.id.glosaEditText);
        this.codigoSigleEt = (TextView) findViewById(R.id.codigoSigleEditText);
        this.subinvDesdeEt = (TextView) findViewById(R.id.subinventarioDesdeEditText);
        this.localDesdeEt = (TextView) findViewById(R.id.localizadorDesdeEditText);
        this.loteEt = (TextView) findViewById(R.id.nroLoteEditText);
        this.cantidadEt = (TextView) findViewById(R.id.cantidadEditText);
        this.textSubinventarioDestino = findViewById(R.id.textSubinventarioDestino);
        this.textLocalizadorDestino = findViewById(R.id.textLocalizadorDestino);


        nroTraspaso = getIntent().getStringExtra("nroTraspaso");
        codigoSigle = getIntent().getStringExtra("codSigle");
        subinvDesde = getIntent().getStringExtra("subinvdesde");
        localizador = getIntent().getStringExtra("localizador");
        nroLote = getIntent().getStringExtra("nroLote");
        glosa = getIntent().getStringExtra("glosa");
        cantidad = getIntent().getLongExtra("cantidad",0);
        subinvHasta = getIntent().getStringExtra("subinvHasta");
        localHasta = getIntent().getStringExtra("localHasta");

        if(getIntent().getStringExtra("id") != null){
            id = getIntent().getStringExtra("id");
        }

        nroTraspasoEt.setText(nroTraspaso);
        codigoSigleEt.setText(codigoSigle);
        subinvDesdeEt.setText(subinvDesde);
        localDesdeEt.setText(localizador);
        loteEt.setText(nroLote);
        glosaEt.setText(glosa);
        cantidadEt.setText(String.valueOf(cantidad));
        textSubinventarioDestino.setText(subinvHasta);
        textLocalizadorDestino.setText(localHasta);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String subinventarioHasta;
        String localizadorHasta;

        subinventarioHasta = this.textSubinventarioDestino.getText().toString();
        localizadorHasta = this.textLocalizadorDestino.getText().toString();


        switch (item.getItemId()){
            case R.id.action_save:
                mPresenter.insertarDatos(id, nroTraspaso, codigoSigle,nroLote,subinvDesde,localizador,cantidad,subinventarioHasta,localizadorHasta);
                return true;
            case R.id.action_serie:
                if(subinventarioHasta.equals("")){
                    this.showError("Debe Ingresar Subinventario de destino");
                }
                else {
                    if(mPresenter.controlSerie(codigoSigle)) {
                        Intent i = new Intent(this, ActivitySeriesTrans.class);
                        i.putExtra("nroTraspaso", nroTraspaso);
                        i.putExtra("glosa", glosa);
                        i.putExtra("codigoSigle", codigoSigle);
                        i.putExtra("subinvDesde", subinvDesde);
                        i.putExtra("localizador", localizador);
                        i.putExtra("subinventarioHasta", subinventarioHasta);
                        i.putExtra("localizadorHasta", localizadorHasta);
                        i.putExtra("nroLote", nroLote);
                        i.putExtra("cantidad", cantidad);
                        i.putExtra("id", id);
                        startActivity(i);
                        this.finish();
                        return true;
                    }
                }
                return true;
            case android.R.id.home:
                Intent intentAgregar = new Intent(this, ActivityAgregarTransSubinv.class);
                intentAgregar.putExtra("nroTraspaso", nroTraspaso);
                intentAgregar.putExtra("glosa", glosa);
                intentAgregar.putExtra("codigoSigle", codigoSigle);
                intentAgregar.putExtra("subinvDesde", subinvDesde);
                intentAgregar.putExtra("localizador", localizador);
                intentAgregar.putExtra("nroLote", nroLote);
                intentAgregar.putExtra("cantidad", cantidad);
                intentAgregar.putExtra("id", id);
                startActivity(intentAgregar);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}