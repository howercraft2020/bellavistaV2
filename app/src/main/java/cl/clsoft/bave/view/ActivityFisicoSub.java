package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.FisicoSubPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityFisicoSub extends BaseActivity<FisicoSubPresenter> {
    private Long inventarioId;
    private TextView inventarioIdEtiqueta;
    private TextView nombreEtiqueta;
    private TextView fechaCreacionEtiqueta;



    //Controls
    private RecyclerView recyclerViewFisicoSub;
    private AdapterInventarioFisicoSub adapter;

    private List<MtlPhysicalSubinventories> subinventories;



    @Override
    protected FisicoSubPresenter createPresenter(@NonNull Context context){
        return new FisicoSubPresenter(this, new InventarioFisicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fisico_sub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controls
        this.recyclerViewFisicoSub = findViewById(R.id.itemsFisicoSub);

        //Set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        MtlPhysicalInventories inventario = mPresenter.getPreviousInventarioFisicos(inventarioId);
        subinventories = mPresenter.getSubinventories(inventarioId);
        Log.d("SUBINVENTORY TAMAÑO: ", String.valueOf(subinventories.size()));

        this.recyclerViewFisicoSub.setHasFixedSize(true);
        this.recyclerViewFisicoSub.setLayoutManager(new LinearLayoutManager(this));

        this.adapter = new AdapterInventarioFisicoSub(subinventories);
        this.recyclerViewFisicoSub.setAdapter(this.adapter);

        this.inventarioIdEtiqueta = findViewById(R.id.idEtiquetaDetalle);
        this.nombreEtiqueta = findViewById(R.id.nombreEtiquetaDetalle);
        this.fechaCreacionEtiqueta = findViewById(R.id.fechaCreacionEtiquetaDetalle);

        if(inventario != null) {
            this.inventarioIdEtiqueta.setText("ID: " + inventario.getPhysicalInventoryId());
            this.nombreEtiqueta.setText("NOMBRE: " + inventario.getPhysicalInventoryName());
            //this.subinventarioEtiqueta.setText("SUBINVENTARIO: ");
            //this.localizadorEtiqueta,setText("LOCALIZADOR: ");
            this.fechaCreacionEtiqueta.setText("FECHA CREACION: " + inventario.getCreationDate());
        }

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewFisicoSub.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

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
                        i = new Intent(getApplicationContext(), ActivityFisicoDetalle.class);

                        i.putExtra("InventarioId", subinventories.get(position).getPhyshicalInventoryId());
                        i.putExtra("SubinventarioId", subinventories.get(position).getSubinventory());
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
                Intent i = new Intent(this, ActivityFisicos.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
