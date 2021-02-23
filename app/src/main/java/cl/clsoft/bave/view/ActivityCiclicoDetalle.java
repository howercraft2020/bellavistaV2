package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.presenter.CiclicoDetallePresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;

public class ActivityCiclicoDetalle extends BaseActivity<CiclicoDetallePresenter> {

    // Variables
    private static final String TAG = "CiclicoDetalle";
    private Long inventarioCiclicoId;
    private List<MtlCycleCountEntries> entries;

    // Controls
    private TextView cycleCountHeaderId;
    private TextView cycleCountHeaderName;
    private TextView creationDate;
    private RecyclerView recyclerViewDetalle;
    private AdapterItemCiclicoDetalle adapter;

    @NonNull
    @Override
    protected CiclicoDetallePresenter createPresenter(@NonNull Context context) {
        return new CiclicoDetallePresenter(this, new ConteoCiclicoService());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.cycleCountHeaderId = findViewById(R.id.cycleCountHeaderId);
        this.cycleCountHeaderName = findViewById(R.id.cycleCountHeaderName);
        this.creationDate = findViewById(R.id.creationDate);
        this.recyclerViewDetalle = findViewById(R.id.itemCiclicoDetalleRecycler);

        // Set Controls
        this.recyclerViewDetalle.setHasFixedSize(true);
        this.recyclerViewDetalle.setLayoutManager(new LinearLayoutManager(this));

        //Se reciben datos de la activity anterior y se insertan en la activity actual
        inventarioCiclicoId = this.getIntent().getLongExtra("ciclicosId",0);
        MtlCycleCountHeaders inventario = mPresenter.getDPreViousDetalleCiclicos(inventarioCiclicoId);

        entries = mPresenter.getSigleInformation(inventarioCiclicoId);
        Log.d("++++++++++++++++--+++++", String.valueOf(entries.size()));
        this.adapter = new AdapterItemCiclicoDetalle(entries);
        this.recyclerViewDetalle.setAdapter(this.adapter);


        if(inventario != null){
            this.cycleCountHeaderId.setText(inventario.getCycleCountHeaderId().toString());
            this.cycleCountHeaderName.setText(inventario.getCycleCountHeaderName());
            this.creationDate.setText(inventario.getCreationDate());
        }

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewDetalle.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent o = null;
                        o = new Intent(getApplicationContext(), ActivityCiclicoSigleDetalle.class);
                        o.putExtra("ciclicoDetalleId", entries.get(position).getCycleCountEntryId());
                        //o.putExtra("ciclicoId", inventarioCiclicoId);
                        startActivity(o);
                        //finish();
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

}