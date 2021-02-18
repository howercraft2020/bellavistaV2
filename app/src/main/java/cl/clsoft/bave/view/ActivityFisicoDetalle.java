package cl.clsoft.bave.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.presenter.DetalleFisicoPresenter;
import cl.clsoft.bave.R;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityFisicoDetalle extends BaseActivity<DetalleFisicoPresenter> {

    private Long inventarioId;
    private Long subinventarioId;

    private TextView inventarioIdText;
    private TextView nombreInventarioText;
    private TextView descriptionInventarioText;
    private TextView subinventarioIdText;
    private TextView fechaCreacionText;

    //Controls
    private RecyclerView recyclerViewFisicoDetalle;
    private List<MtlPhysicalInventoryTags> tags;
    private AdapterInventarioFisicoDetalle adapter;

    @Override
    protected DetalleFisicoPresenter createPresenter(@NonNull Context context) {
        return new DetalleFisicoPresenter(this, new InventarioFisicoService());
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fisico_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controlls
        this.recyclerViewFisicoDetalle = findViewById(R.id.itemFisicoDetalle);

        //set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        MtlPhysicalInventories inventario = mPresenter.getPreviousInventarioFisicos(inventarioId);
        subinventarioId = this.getIntent().getLongExtra("SubinventarioId", 0);
        tags = mPresenter.getTags(inventarioId);

        this.recyclerViewFisicoDetalle.setHasFixedSize(true);
        this.recyclerViewFisicoDetalle.setLayoutManager(new LinearLayoutManager(this));

        this.adapter = new AdapterInventarioFisicoDetalle(tags);
        this.recyclerViewFisicoDetalle.setAdapter(this.adapter);

        this.inventarioIdText = findViewById(R.id.inventarioIdText);
        this.nombreInventarioText = findViewById(R.id.nombreInventarioText);
        this.descriptionInventarioText = findViewById(R.id.descriptionInventarioText);
        this.subinventarioIdText = findViewById(R.id.subinventarioDetalleText);
        this.fechaCreacionText = findViewById(R.id.fechacreacionDetalleText);

        Log.d("+++++++++++++++++++++", inventario.getPhysicalInventoryId().toString());
        if(inventario != null){
            this.inventarioIdText.setText("ID: " + inventario.getPhysicalInventoryId().toString());
            this.nombreInventarioText.setText("NOMBRE: " + inventario.getPhysicalInventoryName());
            this.descriptionInventarioText.setText("DESCRIPTION: " + inventario.getDescription());
            this.subinventarioIdText.setText("SUBINVENTARIO: " + subinventarioId.toString());
            this.fechaCreacionText.setText("FECHA CREACION: " + inventario.getCreationDate());
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
