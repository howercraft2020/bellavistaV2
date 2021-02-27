package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.TransSubinvDestPresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivityTransSubinvDest extends BaseActivity<TransSubinvDestPresenter> {

    private TextView nroTraspasoEt, glosaEt, codigoSigleEt, subinvDesdeEt, localDesdeEt, loteEt, cantidadEt;
    private String codigoSigle, subinvDesde, localizador, nroLote;
    Long cantidad;

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

        nroTraspasoEt = (TextView) findViewById(R.id.numeroTraspasoEditText);
        glosaEt = (TextView) findViewById(R.id.glosaEditText);
        codigoSigleEt = (TextView) findViewById(R.id.codigoSigleEditText);
        subinvDesdeEt = (TextView) findViewById(R.id.subinventarioDesdeEditText);
        localDesdeEt = (TextView) findViewById(R.id.localizadorDesdeEditText);
        loteEt = (TextView) findViewById(R.id.nroLoteEditText);
        cantidadEt = (TextView) findViewById(R.id.cantidadEditText);

        codigoSigle = getIntent().getStringExtra("codSigle");
        subinvDesde = getIntent().getStringExtra("subinvdesde");
        localizador = getIntent().getStringExtra("localizador");
        nroLote = getIntent().getStringExtra("nroLote");
        cantidad = getIntent().getLongExtra("cantidad",0);

        nroTraspasoEt.setText("");
        glosaEt.setText("");
        codigoSigleEt.setText(codigoSigle);
        subinvDesdeEt.setText(subinvDesde);
        localDesdeEt.setText(localizador);
        loteEt.setText(nroLote);
        cantidadEt.setText(String.valueOf(cantidad));

    }
}