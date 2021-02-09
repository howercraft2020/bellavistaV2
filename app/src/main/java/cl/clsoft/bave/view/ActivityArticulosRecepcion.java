package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.presenter.ArticulosRecepcionPresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;

public class ActivityArticulosRecepcion extends BaseActivity<ArticulosRecepcionPresenter> {

    // Variables
    private static final String TAG = "ActivityArticulosRecepcion";
    private List<RcvTransactionsInterface> articulos;
    private TextView segment1;
    private TextView receiptNum;
    private TextView creationDate;

    @NonNull
    @Override
    protected ArticulosRecepcionPresenter createPresenter(@NonNull Context context) {
        return new  ArticulosRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_articulos_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        segment1 = (TextView) findViewById(R.id.segment1);
        receiptNum = (TextView) findViewById(R.id.receiptNum);
        creationDate = (TextView) findViewById(R.id.creationDate);

        String numeroOc = getIntent().getStringExtra("numeroOc");
        String numeroRecep = getIntent().getStringExtra("NumeroRecep");
        String fechaCreacion = getIntent().getStringExtra("fechaCreacion");

        segment1.setText(numeroOc);
        receiptNum.setText(numeroRecep);
        creationDate.setText(fechaCreacion);

    }
}