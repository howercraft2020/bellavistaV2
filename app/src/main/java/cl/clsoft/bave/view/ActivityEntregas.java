package cl.clsoft.bave.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoheadersAll;
import cl.clsoft.bave.apis.IRestRcvHeadersInterfaceEntrega;
import cl.clsoft.bave.apis.IRestRcvShipmentHeaders;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterfaceEntrega;
import cl.clsoft.bave.apis.IRestXXPDA_RCV_HEADERS_INTERFACE;
import cl.clsoft.bave.apis.IRestXXPDA_RCV_TRANS_INTERFACE;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.RcvHeadersInterfaceEntrega;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.RcvTransactionsInterfaceEntrega;
import cl.clsoft.bave.model.XXPDA_RCV_HEADERS_INTERFACE;
import cl.clsoft.bave.presenter.EntregasPresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cl.clsoft.bave.viewmodel.PoHeadersViewModel;
import cl.clsoft.bave.viewmodel.RcvShipmentHeadersViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public class ActivityEntregas extends BaseActivity<EntregasPresenter> {

    // Variables
    private String TAG = "ActivityEntregas";
     List<RcvShipmentHeaders> headers;

    // Controls
    private RecyclerView recyclerViewEntregas;
    private AdapterItemEntrega adapter;


    //REST API
    private RcvShipmentHeadersViewModel rcvShipmentHeadersViewModel;

    @NonNull
    @Override
    protected EntregasPresenter createPresenter(@NonNull Context context) {
      //  return new EntregasPresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
        return new EntregasPresenter(this, new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {




                // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewEntregas = findViewById(R.id.recyclerViewEntregas);

        //Set Controls
        this.recyclerViewEntregas.setHasFixedSize(true);
        this.recyclerViewEntregas.setLayoutManager(new LinearLayoutManager(this));
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });


        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityEntregas.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando Entregas....");
        progressDoalog.setTitle("Entregas");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();




        headers = new ArrayList<RcvShipmentHeaders>();

        rcvShipmentHeadersViewModel = new ViewModelProvider(this).get(RcvShipmentHeadersViewModel.class);


        rcvShipmentHeadersViewModel.init();


        rcvShipmentHeadersViewModel.ShipmentgetAll();

        rcvShipmentHeadersViewModel.getAll().observe(this, new Observer<List<RcvShipmentHeaders>>() {
            @Override
            public void onChanged(List<RcvShipmentHeaders> rcvShipmentHeaders) {
                if(rcvShipmentHeaders != null){
                    progressDoalog.dismiss();


                    adapter = new AdapterItemEntrega(getApplicationContext(),headers);
                    recyclerViewEntregas.setAdapter(adapter);
                    headers = rcvShipmentHeaders;
                    adapter.setEntregas(headers);
                    Log.d("CALL", "PANTALLA 1 ENTREGAS LISTA INICIAL");




                }

                else {
                    progressDoalog.dismiss();
                    Log.d("NULO", "VIENE NULO");
                }
            }
        });


        this.recyclerViewEntregas.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

               // Log.d("CALL", "Enviando Variable ShipmentHeaderID:"+headers.get(0).getShipmentHeaderId());
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        Log.d("CALL", "Enviando Variable ShipmentHeaderID:"+headers.get(0).getShipmentHeaderId());
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityEntregaDetalle.class);
                        i.putExtra("ShipmentHeaderId", headers.get(position).getShipmentHeaderId());
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
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {



            }
        });

    }
    /*
    @Override
    protected void onStart() {
        super.onStart();
        this.mPresenter.getEntregas();
    }
*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
/*
    public void fillEntregas(List<RcvShipmentHeaders> headers) {

        if (headers != null) {



            this.headers = headers;
            this.adapter = new AdapterItemEntrega(headers);
            this.recyclerViewEntregas.setAdapter(this.adapter);
        }




    }

 */

}

