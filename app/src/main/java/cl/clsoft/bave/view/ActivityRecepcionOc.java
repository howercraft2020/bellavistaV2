package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.presenter.RecepcionOcPresenter;
import cl.clsoft.bave.service.impl.RecepcionOcService;

public class ActivityRecepcionOc extends BaseActivity<RecepcionOcPresenter> {

    //Variables
    private static final String TAG = "ActivityRecepcionOc";
    private List<PoHeadersAll> recepciones;

    //Controls
    private RecyclerView recyclerViewRecepciones;
    private AdapterItemRecepcionOc adapter;

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

        recepciones = mPresenter.getRecepcionesOc();
        this.adapter = new AdapterItemRecepcionOc(recepciones);
        this.recyclerViewRecepciones.setAdapter(this.adapter);

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
                        i.putExtra("NumeroRecep", recepciones.get(position).getReceiptNum());
                        i.putExtra("fechaCreacion", recepciones.get(position).getCreationDate());
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