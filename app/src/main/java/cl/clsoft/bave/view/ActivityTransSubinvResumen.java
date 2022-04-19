package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.TransSubinvResumenPresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.TransSubinvService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityTransSubinvResumen extends BaseActivity<TransSubinvResumenPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    //Variables
    private String nroTraspaso;
    private String codigoSigle;
    private String subinvDesde;
    private String localizador;
    private String nroLote;
    private String glosa;
    private String subinvHasta;
    private String localHasta;
    private String id;
    private Double cantidad;
    private boolean isLote = false;
    private boolean isSerie = false;
    private List<String> series;

    // Controls
    private TextView textProductoDescription;
    private TextView textProductoSigle;
    private TextView textProductoCantidad;
    private TextView textProductoLote;
    private TextView textProductoSerie;
    private TextView textSubinventoryCode;
    private TextView textDestSubinventoryCode;
    private TextView textDestLocatorCode;
    private TextView textLocatorCode;
    private TextView textLote;
    private TextView textGlosa;
    private TextView textSeries;
    private RelativeLayout rlayoutLote;
    private RelativeLayout rlayoutSeries;


    @NonNull
    @Override
    protected TransSubinvResumenPresenter createPresenter(@NonNull Context context) {
        return new TransSubinvResumenPresenter(this, new TransSubinvService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_subinv_resumen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textProductoDescription = findViewById(R.id.textProductoDescription);
        this.textProductoSigle = findViewById(R.id.textProductoSigle);
        this.textProductoCantidad = findViewById(R.id.textProductoCantidad);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);
        this.textSubinventoryCode = findViewById(R.id.textSubinventoryCode);
        this.textDestSubinventoryCode = findViewById(R.id.textDestSubinventoryCode);
        this.textDestLocatorCode = findViewById(R.id.textDestLocatorCode);
        this.textLocatorCode = findViewById(R.id.textLocatorCode);
        this.textLote = findViewById(R.id.textLote);
        this.textGlosa = findViewById(R.id.textGlosa);
        this.textSeries = findViewById(R.id.textSeries);
        this.rlayoutLote = findViewById(R.id.rlayoutLote);
        this.rlayoutSeries = findViewById(R.id.rlayoutSeries);

        // Set Controls
        this.rlayoutLote.setVisibility(View.GONE);
        this.rlayoutSeries.setVisibility(View.GONE);
        this.nroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        this.codigoSigle = getIntent().getStringExtra("codigoSigle");
        this.subinvDesde = getIntent().getStringExtra("subinvDesde");
        this.localizador = getIntent().getStringExtra("localizador");
        this.nroLote = getIntent().getStringExtra("nroLote");
        this.glosa = getIntent().getStringExtra("glosa");
        this.cantidad = getIntent().getDoubleExtra("cantidad",0.0);
        this.subinvHasta = getIntent().getStringExtra("subinvHasta");
        this.localHasta = getIntent().getStringExtra("localHasta");
        this.series = this.getIntent().getStringArrayListExtra("series");

        if(getIntent().getStringExtra("id") != null){
            id = getIntent().getStringExtra("id");
        }

        MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(this.codigoSigle);
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
            this.fillProducto(item.getDescription(), item.getSegment1(), this.cantidad,
                    this.isLote, this.isSerie, this.subinvDesde, this.localizador, this.subinvHasta, this.localHasta, this.glosa);

            if (this.isLote) {
                this.fillLote(this.nroLote);
            }
            if (this.isSerie) {
                this.fillSeries(this.series);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intentAgregar = new Intent(this, ActivityAgregarTransSubinv.class);
                intentAgregar.putExtra("numeroTraspaso", nroTraspaso);
                intentAgregar.putExtra("glosa", glosa);
                intentAgregar.putExtra("codigoSigle", codigoSigle);
                intentAgregar.putExtra("subinvDesde", subinvDesde);
                intentAgregar.putExtra("localizador", localizador);
                intentAgregar.putExtra("nroLote", nroLote);
                intentAgregar.putExtra("cantidad", cantidad);
                intentAgregar.putExtra("id", id);
                intentAgregar.putExtra("subinvHasta", subinvHasta);
                intentAgregar.putExtra("localHasta", localHasta);
                intentAgregar.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                startActivity(intentAgregar);
                this.finish();
                return true;
            case R.id.grabar:
                Log.d(TAG, "grabar");
                ConfirmationDialog dialogSave = ConfirmationDialog.newInstance("Desea ingresar este producto a la transferencia?", "Confirmación", "save");
                dialogSave.show(getSupportFragmentManager(), "saveAgregarConfirm");
                return true;
            case R.id.back:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trans_subinv_resumen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void fillProducto(String descripcion, String segment, Double cantidad,
                              boolean isLote, boolean isSerie, String subinventoryCode, String locatorCode, String destSubinventoryCode,
                              String destLocatorCode, String glosa) {
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
        this.textSubinventoryCode.setText(subinventoryCode);
        this.textLocatorCode.setText(locatorCode);
        this.textDestSubinventoryCode.setText(destSubinventoryCode);
        this.textDestLocatorCode.setText(destLocatorCode);
        this.textGlosa.setText(glosa);
    }

    private void fillLote(String lote) {
        this.textLote.setText(lote);
        this.rlayoutLote.setVisibility(View.VISIBLE);
    }

    private void fillSeries(List<String> series) {
        String strSeries = "";

        if (series != null) {
            for (String serie : series) {
                strSeries = strSeries + serie + ", ";
            }
        }
        this.textSeries.setText(strSeries);
        this.rlayoutSeries.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        this.mPresenter.insertarDatos(this.id,this.nroTraspaso,this.codigoSigle,this.nroLote,
                this.subinvDesde,this.localizador,this.cantidad,this.subinvHasta, this.localHasta, this.glosa, series);
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void resultadoOkAddTransaction() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText("Creación exitosa")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Log.d("CMFA", "CLICK");
                        Intent i = new Intent(getApplicationContext(), ActivityTransSubinv.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }
}