package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.AgregarTransSubinvPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivityAgregarTransSubinv extends BaseActivity<AgregarTransSubinvPresenter> {

    private EditText nroTraspasoEt, articuloEt, loteEt, localizadorEt, subinventarioEt, cantidadEt, glosaEt;
    private String numeroTraspaso, glosa, codigoSigle, subinvDesde,localizador,nroLote, id;
    private Long cantidad;

    @NonNull
    @Override
    protected AgregarTransSubinvPresenter createPresenter(@NonNull Context context) {
        return new AgregarTransSubinvPresenter(this, new TransSubinvService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_agregar_trans_subinv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trans_subinv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        articuloEt = (EditText) findViewById(R.id.sigleEditText);
        loteEt = (EditText) findViewById(R.id.loteEditText);
        localizadorEt = (EditText) findViewById(R.id.localizadorDesdeEditText);
        subinventarioEt = (EditText) findViewById(R.id.subinventarioDesdeEditText);
        cantidadEt = (EditText) findViewById(R.id.cantidadEditText);
        glosaEt = (EditText) findViewById(R.id.glosaEditText);
        nroTraspasoEt = (EditText) findViewById(R.id.traspasoEditText);

        numeroTraspaso = getIntent().getStringExtra("nroTraspaso");
        glosa = getIntent().getStringExtra("glosa");
        codigoSigle = getIntent().getStringExtra("codigoSigle");
        subinvDesde = getIntent().getStringExtra("subinvDesde");
        localizador = getIntent().getStringExtra("localizador");
        nroLote = getIntent().getStringExtra("nroLote");
        cantidad = getIntent().getLongExtra("cantidad",0L);
        id = getIntent().getStringExtra("id");

        nroTraspasoEt.setText(numeroTraspaso);
        glosaEt.setText(glosa);
        articuloEt.setText(codigoSigle);
        subinventarioEt.setText(subinvDesde);
        localizadorEt.setText(localizador);
        loteEt.setText(nroLote);
        cantidadEt.setText(String.valueOf(cantidad));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String nroTraspaso;
        String articulo;
        String lote;
        String localizador;
        String subinventario;
        String glosa;
        Long cantidad = 0L;

        if(this.cantidadEt.getText().toString().trim().length() != 0) {
            cantidad = Long.parseLong(this.cantidadEt.getText().toString());
        }

        nroTraspaso = this.nroTraspasoEt.getText().toString();
        articulo = this.articuloEt.getText().toString();
        lote = this.loteEt.getText().toString();
        localizador = this.localizadorEt.getText().toString();
        subinventario = this.subinventarioEt.getText().toString();
        glosa = this.glosaEt.getText().toString();

        switch (item.getItemId()) {
            case R.id.action_more:
                if( mPresenter.cargaTransferencia(articulo, lote, subinventario, localizador, cantidad)) {
                    Intent i = new Intent(this, ActivityTransSubinvDest.class);
                    i.putExtra("codSigle", articulo);
                    i.putExtra("subinvdesde", subinventario);
                    i.putExtra("localizador", localizador);
                    i.putExtra("nroLote", lote);
                    i.putExtra("cantidad", cantidad);
                    i.putExtra("glosa", glosa);
                    i.putExtra("nroTraspaso",nroTraspaso);
                    i.putExtra("id",id);
                    startActivity(i);
                    this.finish();
                    return true;
                }
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}