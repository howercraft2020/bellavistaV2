package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    private TextView poHeaderId;
    private Long interfaceHeaderId;
    String numeroOc;
    Long numeroRecep;
    String id;

    //Controls
    private RecyclerView recyclerViewArticulosRecepcion;
    private AdapterItemArticulosRecepcion adapter;

    @NonNull
    @Override
    protected ArticulosRecepcionPresenter createPresenter(@NonNull Context context) {
        return new ArticulosRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_articulos_recepcion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_more:
                //Log.d(TAG, "Agregar Recepcion");
                Intent i = new Intent(this, ActivityAgregarRecepcion.class);
                i.putExtra("numeroOc", numeroOc);
                i.putExtra("NumeroRecep", numeroRecep);
                i.putExtra("poHeaderId", id);

                startActivity(i);
                this.finish();
                return true;
            case android.R.id.home:
                Intent imain = new Intent(this, ActivityRecepcionOc.class);
                startActivity(imain);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        segment1 = (TextView) findViewById(R.id.segment1);
        receiptNum = (TextView) findViewById(R.id.receiptNum);
        poHeaderId = (TextView) findViewById(R.id.poHeaderId);

        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        id = getIntent().getStringExtra("poHeaderId");

        interfaceHeaderId = Long.parseLong(id+numeroRecep);

        segment1.setText(numeroOc);
        receiptNum.setText(numeroRecep.toString());
        poHeaderId.setText(id);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewArticulosRecepcion = findViewById(R.id.recyclerViewArticulosRecepcion);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        // Set Controls
        this.recyclerViewArticulosRecepcion.setHasFixedSize(true);
        this.recyclerViewArticulosRecepcion.setLayoutManager(new LinearLayoutManager(this));

         this.articulos = mPresenter.getAllArticulos(interfaceHeaderId);
        this.adapter = new AdapterItemArticulosRecepcion(articulos);
        this.recyclerViewArticulosRecepcion.setAdapter(this.adapter);

    }
}