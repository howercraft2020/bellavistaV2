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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.presenter.FisicoDetallePresenter;
import cl.clsoft.bave.R;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityFisicoDetalle extends BaseActivity<FisicoDetallePresenter> {

    // Variables
    private String TAG = "ActivityFisicoDetalle";
    private Long inventarioId;
    private String subinventarioId;
    MtlPhysicalInventories inventario;
    private List<MtlPhysicalInventoryTags> tags;

    //Controls
    private TextView textId;
    private TextView textNombre;
    private TextView textDescription;
    private TextView textSubinventario;
    private TextView textFechaCreacion;
    private RecyclerView recyclerViewFisicoDetalle;
    private AdapterInventarioFisicoDetalle adapter;

    @Override
    protected FisicoDetallePresenter createPresenter(@NonNull Context context) {
        return new FisicoDetallePresenter(this, new InventarioFisicoService());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fisico_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controlls
        this.textId = findViewById(R.id.textId);
        this.textNombre = findViewById(R.id.textNombre);
        this.textDescription = findViewById(R.id.textDescription);
        this.textSubinventario = findViewById(R.id.textSubinventario);
        this.textFechaCreacion = findViewById(R.id.textFechaCreacion);
        this.recyclerViewFisicoDetalle = findViewById(R.id.itemFisicoDetalle);

        //set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        subinventarioId = this.getIntent().getStringExtra("SubinventarioId");
        inventario = mPresenter.getPreviousInventarioFisicos(inventarioId);

        this.recyclerViewFisicoDetalle.setHasFixedSize(true);
        this.recyclerViewFisicoDetalle.setLayoutManager(new LinearLayoutManager(this));

        if(inventario != null){
            this.textId.setText(inventario.getPhysicalInventoryId().toString());
            this.textNombre.setText(inventario.getPhysicalInventoryName());
            this.textDescription.setText(inventario.getDescription());
            this.textSubinventario.setText(subinventarioId);
            this.textFechaCreacion.setText(inventario.getCreationDate());
        }

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewFisicoDetalle.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

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
                        i = new Intent(getApplicationContext(), ActivityFisicoEditar.class);
                        i.putExtra("tagId", tags.get(position).getTagId());
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
        tags = mPresenter.getTagsInventariados(inventarioId, subinventarioId);
        this.adapter = new AdapterInventarioFisicoDetalle(tags);
        this.recyclerViewFisicoDetalle.setAdapter(this.adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inventario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.agregarInventarioButton:
                Log.d(TAG, "Agregar inventario");
                Intent o = new Intent(this, ActivityFisicoAgregar.class);
                o.putExtra("InventarioId", inventarioId);
                o.putExtra("SubinventarioId", subinventarioId);
                startActivity(o);
                this.finish();
                return true;
            case android.R.id.home:
                Intent i = new Intent(this, ActivityFisicoSub.class);
                i.putExtra("InventarioId", inventarioId);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
