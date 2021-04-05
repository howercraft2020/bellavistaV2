package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.presenter.EntregasOrgsPresenter;
import cl.clsoft.bave.service.impl.EntregaOrgsServiceImpl;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregasOrgs extends BaseActivity<EntregasOrgsPresenter> {

    // Variables
    private String TAG = "ActivityEntregasOrgs";
    private List<EntregaOrgsHeader> headers;

    // Controls
    private RecyclerView recyclerViewEntregas;
    private AdapterItemEntregaOrgs adapter;

    @NonNull
    @Override
    protected EntregasOrgsPresenter createPresenter(@NonNull Context context) {
        return new EntregasOrgsPresenter(this, new AppTaskExecutor(this), new EntregaOrgsServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas_orgs);
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

        this.recyclerViewEntregas.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityEntregasOrgsDetalle.class);
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

    @Override
    protected void onStart() {
        super.onStart();
        this.mPresenter.getEntregas();
    }

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

    public void fillEntregas(List<EntregaOrgsHeader> headers) {
        if (headers != null) {
            if (headers.size() == 1 && headers.get(0).getShipmentNumber() == null)
                return;
            Log.d(TAG, "headers size: " + headers.size());
            this.headers = headers;
            this.adapter = new AdapterItemEntregaOrgs(headers);
            this.recyclerViewEntregas.setAdapter(this.adapter);
        }
    }

}
