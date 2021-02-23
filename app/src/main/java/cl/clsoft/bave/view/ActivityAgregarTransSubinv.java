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

    private EditText articulo, lote, localizador, subinventario;

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

        articulo = (EditText) findViewById(R.id.sigleEditText);
        lote = (EditText) findViewById(R.id.loteEditText);
        localizador = (EditText) findViewById(R.id.localizadorDesdeEditText);
        subinventario = (EditText) findViewById(R.id.subinventarioDesdeEditText);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String articulo;
        String lote;
        String localizador;
        String subinventario;

        articulo = this.articulo.getText().toString();
        lote = this.lote.getText().toString();
        localizador = this.localizador.getText().toString();
        subinventario = this.subinventario.getText().toString();

        switch (item.getItemId()) {
            case R.id.action_save:
                mPresenter.cargaTransferencia(articulo,lote,subinventario);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}