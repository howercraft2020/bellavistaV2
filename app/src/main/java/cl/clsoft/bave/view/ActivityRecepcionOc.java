package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoheadersAll;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.presenter.RecepcionOcPresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;
import cl.clsoft.bave.viewmodel.PoHeadersViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRecepcionOc extends BaseActivity<RecepcionOcPresenter> {

    //Variables
    private static final String TAG = "ActivityRecepcionOc";
    private List<PoHeadersAll> recepciones;


    //Variables API REST
    private PoHeadersViewModel poHeadersViewModel;
    private IRestPoheadersAll iRestPoheadersAll;


    //Controls
     private RecyclerView recyclerViewRecepciones;
     private  AdapterItemRecepcionOc adapter;

    @NonNull
    @Override
    protected RecepcionOcPresenter createPresenter(@NonNull Context context) {
        return new RecepcionOcPresenter(this, new RecepcionOcService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_recepcion_oc);
        setContentView(R.layout.activity_recepcion_oc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewRecepciones = findViewById(R.id.recyclerViewRecepciones);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        // Set Controls
        this.recyclerViewRecepciones.setHasFixedSize(true);
        this.recyclerViewRecepciones.setLayoutManager(new LinearLayoutManager(this));



        //CONEXION SQLITE
        /*
        recepciones = mPresenter.getRecepcionesOc();
        this.adapter = new AdapterItemRecepcionOc(getApplicationContext(),recepciones);
        this.recyclerViewRecepciones.setAdapter(this.adapter);
        */

        //CONEXION VIEWMODEL APIREST

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityRecepcionOc.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando OC....");
        progressDoalog.setTitle("Recepciones");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();



        recepciones = new ArrayList<PoHeadersAll>();


        poHeadersViewModel = new ViewModelProvider(this).get(PoHeadersViewModel.class);

        poHeadersViewModel.init();


        poHeadersViewModel.PoHeadersgetAll();
        poHeadersViewModel.getAllLiveData().observe(this, new Observer<List<PoHeadersAll>>() {
            @Override
            public void onChanged(List<PoHeadersAll> poHeadersAlls) {
                Log.d("CALL", "LLAMANDO SERVICIO ");
                if(poHeadersAlls != null){
                    progressDoalog.dismiss();
                    adapter = new AdapterItemRecepcionOc(getApplicationContext(), poHeadersAlls);
                    recyclerViewRecepciones.setAdapter(adapter);

                    recepciones = poHeadersAlls;

                    Log.d("PO_HEADERS", "Cantidad OC:"+poHeadersAlls.size());
                }

                else {
                    progressDoalog.dismiss();
                    Log.d("NULO", "VIENE NULO");
                }
            }
        });



        this.recyclerViewRecepciones.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {

                try {
                    View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if(child != null && mGestureDetector.onTouchEvent(motionEvent) ){
                        int position = rv.getChildAdapterPosition(child);
                        Intent i = null;
                        i = new Intent(ActivityRecepcionOc.this, ActivityArticulosRecepcion.class);


                        i.putExtra("numeroOc", recepciones.get(position).getSegment1().toString());
                        i.putExtra("poHeaderId", recepciones.get(position).getPoHeaderId().toString());
                        i.putExtra("NumeroRecep", recepciones.get(position).getReceiptNum());
                        startActivity(i);
                        finish();
                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}