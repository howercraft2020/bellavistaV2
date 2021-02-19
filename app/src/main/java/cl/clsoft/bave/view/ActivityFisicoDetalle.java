package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    // Variables
    private String TAG = "ActivityFisicoDetalle";
    private Long inventarioId;
    private String subinventarioId;
    MtlPhysicalInventories inventario;
    private List<MtlPhysicalInventoryTags> tags;

    //Controls
    private TextView inventarioIdText;
    private TextView nombreInventarioText;
    private TextView descriptionInventarioText;
    private TextView subinventarioIdText;
    private TextView fechaCreacionText;
    private RecyclerView recyclerViewFisicoDetalle;
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
        this.inventarioIdText = findViewById(R.id.inventarioIdText);
        this.nombreInventarioText = findViewById(R.id.nombreInventarioText);
        this.descriptionInventarioText = findViewById(R.id.descriptionInventarioText);
        this.subinventarioIdText = findViewById(R.id.subinventarioDetalleText);
        this.fechaCreacionText = findViewById(R.id.fechacreacionDetalleText);
        this.recyclerViewFisicoDetalle = findViewById(R.id.itemFisicoDetalle);

        //set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        subinventarioId = this.getIntent().getStringExtra("SubinventarioId");
        inventario = mPresenter.getPreviousInventarioFisicos(inventarioId);
        //Log.d(TAG, "tags: " + tags.size());

        this.recyclerViewFisicoDetalle.setHasFixedSize(true);
        this.recyclerViewFisicoDetalle.setLayoutManager(new LinearLayoutManager(this));

        Log.d("+++++++++++++++++++++", inventario.getPhysicalInventoryId().toString());
        if(inventario != null){
            this.inventarioIdText.setText("ID: " + inventario.getPhysicalInventoryId().toString());
            this.nombreInventarioText.setText("NOMBRE: " + inventario.getPhysicalInventoryName());
            this.descriptionInventarioText.setText("DESCRIPTION: " + inventario.getDescription());
            this.subinventarioIdText.setText("SUBINVENTARIO: " + subinventarioId);
            this.fechaCreacionText.setText("FECHA CREACION: " + inventario.getCreationDate());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        tags = mPresenter.getTagsByInventorySubinventory(inventarioId, subinventarioId);
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
                Intent o = new Intent(this, ActivityAgregarFisicoInventario.class);
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
