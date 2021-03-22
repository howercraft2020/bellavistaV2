package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import cl.clsoft.bave.model.DatosTransOrg;
import cl.clsoft.bave.presenter.TransOrgPresenter;
import cl.clsoft.bave.service.impl.TransOrgService;

public class ActivityTransOrg extends BaseActivity<TransOrgPresenter> {

    //Controls
    private static final String TAG = "ActivityTransOrg";
    private List<DatosTransOrg> transferencias;

    //Controls
    private RecyclerView recyclerViewTransOrg;
    private AdapterTransOrg adapter;

    @NonNull
    @Override
    protected TransOrgPresenter createPresenter(@NonNull Context context) {
        return new TransOrgPresenter(this, new TransOrgService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_trans_org, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_org);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewTransOrg = findViewById(R.id.recyclerViewTransOrg);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewTransOrg.setHasFixedSize(true);
        this.recyclerViewTransOrg.setLayoutManager(new LinearLayoutManager(this));

        transferencias = mPresenter.getTransSubinv();
        this.adapter = new AdapterTransOrg(transferencias);
        this.recyclerViewTransOrg.setAdapter(this.adapter);

        this.recyclerViewTransOrg.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {
                try{
                    View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = rv.getChildAdapterPosition(child);
                        Intent i = null;
                        i = new Intent(ActivityTransOrg.this, ActivityTransOrgDetalle.class);
                        i.putExtra("numeroTraspaso", transferencias.get(position).getShipmentNumber());
                        i.putExtra("glosa", transferencias.get(position).getTransactionSourceName());
                        startActivity(i);
                        finish();
                        return true;
                    }

                }catch (Exception e) {
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
                Intent i = new Intent(this, ActivityAgregarTransOrgDestino.class);
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