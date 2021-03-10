package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.TransSubinvDestPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityTransSubinvDest extends BaseActivity<TransSubinvDestPresenter> {

    //Variables
    private String nroTraspaso;
    private String codigoSigle;
    private String subinvDesde;
    private String localizador;
    private String nroLote;
    private String glosa;
    private String subinvHasta;
    private String localHasta;
    private String codSubinventario;
    private String id;
    private Long cantidad;
    private boolean isLote = false;
    private boolean isSerie = false;
    private List<String> series;

    //Controls
    private TextInputLayout layoutSubinventarioDestino;
    private AutoCompleteTextView textSubinventarioDestino;
    private TextInputLayout layoutLocalizadorDestino;
    private AutoCompleteTextView textLocalizadorDestino;




    @NonNull
    @Override
    protected TransSubinvDestPresenter createPresenter(@NonNull Context context) {
        return new TransSubinvDestPresenter(this,new AppTaskExecutor(this), new TransSubinvService());
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

        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textSubinventarioDestino = findViewById(R.id.textSubinventarioDestino);
        this.textLocalizadorDestino = findViewById(R.id.textLocalizadorDestino);


        this.nroTraspaso = getIntent().getStringExtra("nroTraspaso");
        this.codigoSigle = getIntent().getStringExtra("codigoSigle");
        this.subinvDesde = getIntent().getStringExtra("subinvDesde");
        this.localizador = getIntent().getStringExtra("localizador");
        this.nroLote = getIntent().getStringExtra("nroLote");
        this.glosa = getIntent().getStringExtra("glosa");
        this.cantidad = getIntent().getLongExtra("cantidad",0);
        this.subinvHasta = getIntent().getStringExtra("subinvHasta");
        this.localHasta = getIntent().getStringExtra("localHasta");
        this.series = this.getIntent().getStringArrayListExtra("series");

        if(getIntent().getStringExtra("id") != null){
            id = getIntent().getStringExtra("id");
        }

        textSubinventarioDestino.setText(subinvHasta);
        textLocalizadorDestino.setText(localHasta);

        this.textSubinventarioDestino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                codSubinventario = parent.getAdapter().getItem(position).toString();
                Log.d(TAG, "codSubinventario: " + codSubinventario);
                if (codSubinventario != null && !codSubinventario.equalsIgnoreCase("SIN LOCALIZADOR")) {
                    mPresenter.getLocalizadoresBySubinventario(codSubinventario);
                }
            }
        });

        this.mPresenter.getSubinventarios();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String subinventarioHasta;
        String localizadorHasta;

        subinventarioHasta = this.textSubinventarioDestino.getText().toString();
        localizadorHasta = this.textLocalizadorDestino.getText().toString();


        switch (item.getItemId()){
            case R.id.action_save:
                Intent i = new Intent(this, ActivityTransSubinvResumen.class);
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
                i.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                startActivity(i);
                this.finish();
                return true;
                /*
                if (series != null && !series.isEmpty()) {
                    if (series.size() == cantidad.intValue()) {
                        mPresenter.insertarDatos(id, nroTraspaso, codigoSigle, nroLote, subinvDesde, localizador, cantidad, subinventarioHasta, localizadorHasta,series);
                        Intent salir = new Intent(this,ActivityTransSubinv.class);
                        startActivity(salir);
                        this.finish();
                    }
                    else{
                        long total = cantidad.intValue() - series.size();
                        this.showError("Quedan" + total + " series por ingresar");
                    }
                }else{
                    this.showError("Quedan " + cantidad.intValue() + " series por ingresar");
                }
                return true;

                 */
            case R.id.action_serie:
                if(subinventarioHasta.equals("")){
                    this.showError("Debe Ingresar Subinventario de destino");
                }
                else {
                    if(mPresenter.controlSerie(codigoSigle)) {
                        Intent a = new Intent(this, ActivitySeriesTrans.class);
                        a.putExtra("nroTraspaso", nroTraspaso);
                        a.putExtra("glosa", glosa);
                        a.putExtra("codigoSigle", codigoSigle);
                        a.putExtra("subinvDesde", subinvDesde);
                        a.putExtra("localizador", localizador);
                        a.putExtra("subinvHasta", subinventarioHasta);
                        a.putExtra("localHasta", localizadorHasta);
                        a.putExtra("nroLote", nroLote);
                        a.putExtra("cantidad", cantidad);
                        a.putExtra("id", id);
                        a.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                        startActivity(a);
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
                intentAgregar.putExtra("subinvHasta", subinventarioHasta);
                intentAgregar.putExtra("localHasta", localizadorHasta);
                intentAgregar.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                startActivity(intentAgregar);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void fillSubinventario(List<Subinventario> subinventarios) {
        if (subinventarios != null) {
            if (subinventarios.size() > 0){
                String[] subinventariosArray = new String[subinventarios.size()];
                for (int i = 0; i <subinventarios.size();i++){
                    subinventariosArray[i] = subinventarios.get(i).getCodSubinventario();
                }
                ArrayAdapter<String> adapterSubinventarios = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, subinventariosArray);
                this.textSubinventarioDestino.setAdapter(adapterSubinventarios);
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
                this.textLocalizadorDestino.setAdapter(adapterLocator);
                this.textLocalizadorDestino.setText("");
            } else {
                this.textLocalizadorDestino.setVisibility(View.GONE);
                this.textLocalizadorDestino.setVisibility(View.GONE);
                //this.textSigle.setEnabled(false); JP
                //this.hayLocalizador = false; JP
                //this.loadSigle();
            }
        }
    }
}