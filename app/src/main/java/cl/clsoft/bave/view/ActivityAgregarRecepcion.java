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
import cl.clsoft.bave.presenter.AgregarRecepcionPresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;

public class ActivityAgregarRecepcion extends BaseActivity<AgregarRecepcionPresenter> {

    private EditText numeroParte;
    private EditText codigoSigle;
    private EditText cantidad;
    private EditText udm;
    String numeroOc;
    String poHeaderId;
    Long numeroRecep;




    @NonNull
    @Override
    protected AgregarRecepcionPresenter createPresenter(@NonNull Context context) {
        return new AgregarRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_agregar_recepcion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numeroParte = (EditText) findViewById(R.id.numeroParte);
        codigoSigle = (EditText) findViewById(R.id.codigoSigle);
        cantidad = (EditText) findViewById(R.id.cantidad);
        udm = (EditText) findViewById(R.id.udm);

        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        poHeaderId = getIntent().getStringExtra("poHeaderId");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String numeroParte;
        String codigoSigle;
        Long cantidad = 0L;
        String udm;

        numeroParte = this.numeroParte.getText().toString();
        codigoSigle = this.codigoSigle.getText().toString();

        if(this.cantidad.getText().toString().trim().length() != 0) {
            cantidad = Long.parseLong(this.cantidad.getText().toString());
        }
        udm = this.udm.getText().toString();

        switch (item.getItemId()){
            case R.id.action_save:
                mPresenter.cargaRecepcion(codigoSigle,Long.parseLong(poHeaderId),numeroOc,numeroRecep, cantidad);
                return true;
            case android.R.id.home:
                Intent i = new Intent(this, ActivityArticulosRecepcion.class);
                i.putExtra("numeroOc", numeroOc);
                i.putExtra("NumeroRecep", numeroRecep);
                i.putExtra("poHeaderId", poHeaderId);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}