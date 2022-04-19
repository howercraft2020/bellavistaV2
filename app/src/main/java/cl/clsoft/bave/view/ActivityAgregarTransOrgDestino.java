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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.presenter.AgregarTransOrgDestinoPresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.TransOrgService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityAgregarTransOrgDestino extends BaseActivity<AgregarTransOrgDestinoPresenter> implements ConfirmationDialog.ConfirmationDialogListener{

    //Variables
    private String nroTraspaso;
    private String codigoSigle;
    private String subinvDesde;
    private String localizador;
    private String nroLote;
    private Double cantidad;
    private List<String> series;
    private String id;
    private String glosa;
    private String orgDestino;

    //Controls
    private TextInputLayout layoutOrgDestino;
    private AutoCompleteTextView textOrgDestino;
    private EditText nroTraspasoEt;
    private EditText glosaEt;

    @NonNull
    @Override
    protected AgregarTransOrgDestinoPresenter createPresenter(@NonNull Context context) {
        return new AgregarTransOrgDestinoPresenter(this,new TransOrgService(), new AppTaskExecutor(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_agregar_trans_org_destino, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trans_org_destino);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        id = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());

        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutOrgDestino = findViewById(R.id.layoutOrgDestino);
        this.textOrgDestino = findViewById(R.id.textOrgDestino);
        this.glosaEt = (EditText) findViewById(R.id.glosaEditText);
        this.nroTraspasoEt = (EditText) findViewById(R.id.traspasoEditText);

        this.codigoSigle = getIntent().getStringExtra("codigoSigle");
        this.nroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        this.subinvDesde = getIntent().getStringExtra("subinvDesde");
        this.localizador = getIntent().getStringExtra("localizador");
        this.nroLote = getIntent().getStringExtra("nroLote");
        this.glosa = getIntent().getStringExtra("glosa");
        this.cantidad = getIntent().getDoubleExtra("cantidad",0.0);
        this.orgDestino = getIntent().getStringExtra("orgDestino");
        this.series = this.getIntent().getStringArrayListExtra("series");

        if(getIntent().getStringExtra("id") != null){
            id = getIntent().getStringExtra("id");
        }

        nroTraspasoEt.setText(nroTraspaso);
        glosaEt.setText(glosa);
        textOrgDestino.setText(orgDestino);

        this.mPresenter.getOrganizacionesDestino();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String orgDestino;
        String glosa;

        orgDestino = this.textOrgDestino.getText().toString();
        glosa = this.glosaEt.getText().toString();

        switch (item.getItemId()) {
            case R.id.action_more:
                if (orgDestino.equals("")) {
                    this.showError("Debe Ingresar Organización de destino");
                } else {
                    Intent i = new Intent(this, ActivityAgregarTransOrg.class);
                    i.putExtra("orgDestino", orgDestino);
                    i.putExtra("numeroTraspaso", nroTraspaso);
                    i.putExtra("glosa", glosa);
                    i.putExtra("codigoSigle", codigoSigle);
                    i.putExtra("subinvDesde", subinvDesde);
                    i.putExtra("localizador", localizador);
                    i.putExtra("nroLote", nroLote);
                    i.putExtra("cantidad", cantidad);
                    i.putExtra("id", id);
                    i.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                    startActivity(i);
                    this.finish();
                    return true;
                }
            return true;
            case android.R.id.home:
                Log.d(TAG, "home");
                this.confirmacionSalir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillOrganizaciones(List<Organizacion> organizaciones) {
        if (organizaciones != null) {
            if (organizaciones.size() > 0) {
                String[] organizations = new String[organizaciones.size()];
                for (int i = 0; i < organizaciones.size(); i++) {
                    organizations[i] = organizaciones.get(i).getCode();
                }
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item, organizations);
                this.textOrgDestino.setAdapter(adapterLocator);
            } else {
                this.textOrgDestino.setVisibility(View.GONE);
                this.layoutOrgDestino.setVisibility(View.GONE);

            }
        }
    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        Intent i = new Intent(this, ActivityTransOrg.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}