package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.presenter.CiclicosPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;

public class ActivityCiclicos extends BaseActivity<CiclicosPresenter> {

    // Variables
    private static final String TAG = "ActivityCiclicos";
    private List<MtlCycleCountHeaders> ciclicos;

    // Controls
    private RecyclerView recyclerViewCiclicos;
    private AdapterItemConteoCiclico adapter;
    private AdapterItemConteoCiclico.RecyclerViewClickListener listener;


    @NonNull
    @Override
    protected CiclicosPresenter createPresenter(@NonNull Context context) {
        return new CiclicosPresenter(this, new ConteoCiclicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclicos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewCiclicos = findViewById(R.id.recyclerViewCiclicos);

        // Set Controls
        this.recyclerViewCiclicos.setHasFixedSize(true);
        this.recyclerViewCiclicos.setLayoutManager(new LinearLayoutManager(this));

        this.ciclicos = mPresenter.getConteosCiclicos();
        this.adapter = new AdapterItemConteoCiclico(ciclicos, listener);
        this.recyclerViewCiclicos.setAdapter(this.adapter);
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewCiclicos.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                        i = new Intent(getApplicationContext(), ActivityCiclicoSub.class);
                        i.putExtra("ciclicosId", ciclicos.get(position).getCycleCountHeaderId());
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
