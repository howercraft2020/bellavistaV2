package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
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
import android.view.View;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.presenter.TransSubinvPresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.TransSubinvService;

public class ActivityTransSubinv extends BaseActivity<TransSubinvPresenter> {

    //Variables
    private static final String TAG = "ActivityTransSubinv";
    private List<DatosTransSubinv> transferencias;

    //Controls
    private RecyclerView recyclerViewTransSubinv;
    private AdapterTransSubinv adapter;

    @NonNull
    @Override
    protected TransSubinvPresenter createPresenter(@NonNull Context context) {
        return new TransSubinvPresenter(this, new TransSubinvService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_trans_subinv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_trans_subinv);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewTransSubinv = findViewById(R.id.recyclerViewTransSubinv);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewTransSubinv.setHasFixedSize(true);
        this.recyclerViewTransSubinv.setLayoutManager(new LinearLayoutManager(this));

        transferencias = mPresenter.getTransSubinv();
        this.adapter = new AdapterTransSubinv(transferencias);
        this.recyclerViewTransSubinv.setAdapter(this.adapter);

        this.recyclerViewTransSubinv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {
             try {
                     View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                     if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                         int position = rv.getChildAdapterPosition(child);
                         Intent i = null;
                         i = new Intent(ActivityTransSubinv.this, ActivityTransSubinvDetalle.class);
                         i.putExtra("numeroTraspaso", transferencias.get(position).getTransactionReference());
                         i.putExtra("glosa", transferencias.get(position).getTransactionSourceName());
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
            case R.id.action_more:
                Intent i = new Intent(this, ActivityAgregarTransSubinv.class);
                startActivity(i);
                this.finish();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}