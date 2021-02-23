package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.presenter.FisicosPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityFisicos extends BaseActivity<FisicosPresenter> {

    // Vaariables
    private String TAG = "ActivityFisicos";
    private List<MtlPhysicalInventories> inventarios;

    // Controls
    private RecyclerView recyclerViewFisicos;
    private AdapterInventarioFisico adapter;
    private AdapterInventarioFisico.RecyclerViewClickListener listener;

    @NonNull
    @Override
    protected FisicosPresenter createPresenter(@NonNull Context context) {
        return new FisicosPresenter(this, new InventarioFisicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisicos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewFisicos = findViewById(R.id.recyclerViewHdes);

        //Set Controls
        this.recyclerViewFisicos.setHasFixedSize(true);
        this.recyclerViewFisicos.setLayoutManager(new LinearLayoutManager(this));

        inventarios = mPresenter.getInventariosFisicos();
        this.adapter = new AdapterInventarioFisico(inventarios, listener);
        this.recyclerViewFisicos.setAdapter(this.adapter);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewFisicos.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = null;
                        i = new Intent(getApplicationContext(), ActivityFisicoSub.class);

                        i.putExtra("InventarioId", inventarios.get(position).getPhysicalInventoryId());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
