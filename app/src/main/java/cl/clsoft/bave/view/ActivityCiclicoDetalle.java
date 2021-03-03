package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
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
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.presenter.CiclicoDetallePresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityCiclicoDetalle extends BaseActivity<CiclicoDetallePresenter> {

    // Variables
    private static final String TAG = "CiclicoDetalle";
    private Long inventarioCiclicoId;
    private String subinventarioId;
    private List<MtlCycleCountEntries> entries;

    // Controls
    private TextView textCycleCountHeaderId;
    private TextView textCycleCountHeaderName;
    private TextView textSubinventario;
    private TextView textCreationDate;
    private RecyclerView recyclerViewEntries;
    private AdapterItemEntry adapter;

    @NonNull
    @Override
    protected CiclicoDetallePresenter createPresenter(@NonNull Context context) {
        return new CiclicoDetallePresenter(this, new AppTaskExecutor(this), new ConteoCiclicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textCycleCountHeaderId = findViewById(R.id.textCycleCountHeaderId);
        this.textCycleCountHeaderName = findViewById(R.id.textCycleCountHeaderName);
        this.textSubinventario = findViewById(R.id.textSubinventario);
        this.textCreationDate = findViewById(R.id.textCreationDate);
        this.recyclerViewEntries = findViewById(R.id.recyclerViewEntries);

        // Set Controls
        inventarioCiclicoId = this.getIntent().getLongExtra("ciclicosId",0);
        subinventarioId = this.getIntent().getStringExtra("subinventarioId");
        MtlCycleCountHeaders mtlCycleCountHeaders = this.mPresenter.getMtlCycleCountHeaders(this.inventarioCiclicoId);
        if (mtlCycleCountHeaders != null) {

            this.textCycleCountHeaderId.setText(mtlCycleCountHeaders.getCycleCountHeaderId().toString());
            this.textCycleCountHeaderName.setText(mtlCycleCountHeaders.getCycleCountHeaderName());
            this.textSubinventario.setText(this.subinventarioId);
            this.textCreationDate.setText(mtlCycleCountHeaders.getCreationDate());

            this.recyclerViewEntries.setHasFixedSize(true);
            this.recyclerViewEntries.setLayoutManager(new LinearLayoutManager(this));
        }

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewEntries.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                        o = new Intent(getApplicationContext(), ActivityCiclicoEditar.class);
                        o.putExtra("entryId", entries.get(position).getCycleCountEntryId());
                        startActivity(o);
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
        this.mPresenter.loadEntries(this.inventarioCiclicoId, this.subinventarioId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ciclico_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), ActivityCiclicoSub.class);
                i.putExtra("ciclicosId", this.inventarioCiclicoId);
                startActivity(i);
                this.finish();
                return true;
            case R.id.agregar:
                Log.d(TAG, "Agregar");
                Intent o = new Intent(this, ActivityCiclicoAgregar.class);
                o.putExtra("ciclicosId", this.inventarioCiclicoId);
                o.putExtra("subinventarioId", subinventarioId);
                startActivity(o);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillEntries(List<MtlCycleCountEntries> entries) {
        if (entries != null) {
            Log.d(TAG, "enties size: " + entries.size());
            this.entries = entries;
            this.adapter = new AdapterItemEntry(entries);
            this.recyclerViewEntries.setAdapter(adapter);
        }
    }
}