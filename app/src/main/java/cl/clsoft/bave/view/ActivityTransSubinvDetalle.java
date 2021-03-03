package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.presenter.TransSubinvDetallePresenter;
import cl.clsoft.bave.service.impl.TransSubinvService;

public class ActivityTransSubinvDetalle extends BaseActivity<TransSubinvDetallePresenter> {

    //Variables
    private static final String TAG = "ActivityTransSubinvDetalle";
    private List<DatosTransSubinvDetalle> transferencias;
    private String numeroTraspaso, glosa;
    private TextView numeroTraspasoEt, glosaEt;

    //Controls
    private RecyclerView recyclerViewTransSubinvDetalle;
    private AdapterTransSubinvDetalle adapter;

    @NonNull
    @Override
    protected TransSubinvDetallePresenter createPresenter(@NonNull Context context) {
        return new TransSubinvDetallePresenter(this, new TransSubinvService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_trans_subinv_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_subinv_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numeroTraspasoEt = (TextView) findViewById(R.id.numeroTraspaso);
        glosaEt = (TextView) findViewById(R.id.glosa);

        numeroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        glosa = getIntent().getStringExtra("glosa");

        numeroTraspasoEt.setText(numeroTraspaso);
        glosaEt.setText(glosa);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewTransSubinvDetalle = findViewById(R.id.recyclerViewTransSubinvDetalle);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewTransSubinvDetalle.setHasFixedSize(true);
        this.recyclerViewTransSubinvDetalle.setLayoutManager(new LinearLayoutManager(this));

        transferencias = mPresenter.getTransferencias(numeroTraspaso);
        this.adapter = new AdapterTransSubinvDetalle(transferencias);
        this.recyclerViewTransSubinvDetalle.setAdapter(this.adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_create_file:
                mPresenter.crearArchivo(numeroTraspaso);
                return true;
            case R.id.action_more:
                Intent i = new Intent(this, ActivityAgregarTransSubinv.class);
                i.putExtra("nroTraspaso", numeroTraspaso);
                i.putExtra("glosa", glosa);
                startActivity(i);
                this.finish();
                return true;
            case android.R.id.home:
                Intent ihome = new Intent(this, ActivityTransSubinv.class);
                startActivity(ihome);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}