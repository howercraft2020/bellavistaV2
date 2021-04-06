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
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.presenter.FisicoDetallePresenter;
import cl.clsoft.bave.R;
import cl.clsoft.bave.service.impl.InventarioFisicoService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityFisicoDetalle extends BaseActivity<FisicoDetallePresenter> {

    // Variables
    private String TAG = "ActivityFisicoDetalle";
    private Long inventarioId;
    private String subinventarioId;
    MtlPhysicalInventories inventario;
    private List<MtlPhysicalInventoryTags> tagsInventariados;
    private List<MtlPhysicalInventoryTags> tagsNoInventariados;

    //Controls
    private TextView textId;
    private TextView textNombre;
    private TextView textDescription;
    private TextView textSubinventario;
    private TextView textFechaCreacion;
    private ViewPager viewPagerDetalle;
    private FragmentPagerAdapterTags fragmentPagerAdapterTags;

    @Override
    protected FisicoDetallePresenter createPresenter(@NonNull Context context) {
        return new FisicoDetallePresenter(this, new InventarioFisicoService());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fisico_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controlls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textId = findViewById(R.id.textId);
        this.textNombre = findViewById(R.id.textNombre);
        this.textDescription = findViewById(R.id.textDescription);
        this.textSubinventario = findViewById(R.id.textSubinventario);
        this.textFechaCreacion = findViewById(R.id.textFechaCreacion);
        this.viewPagerDetalle = findViewById(R.id.viewPagerDetalle);

        //set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        subinventarioId = this.getIntent().getStringExtra("SubinventarioId");
        inventario = mPresenter.getPreviousInventarioFisicos(inventarioId);

        if(inventario != null){
            this.textId.setText(inventario.getPhysicalInventoryId().toString());
            this.textNombre.setText(inventario.getPhysicalInventoryName());
            this.textDescription.setText(inventario.getDescription());
            this.textSubinventario.setText(subinventarioId);
            this.textFechaCreacion.setText(inventario.getCreationDate());
        }

        this.fragmentPagerAdapterTags = new FragmentPagerAdapterTags(getSupportFragmentManager());
        this.viewPagerDetalle.setAdapter(this.fragmentPagerAdapterTags);

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.tagsInventariados = mPresenter.getTagsInventariados(inventarioId, subinventarioId);
        this.tagsNoInventariados = mPresenter.getTagsNoInventariados(inventarioId, subinventarioId);
        this.fragmentPagerAdapterTags.setTagsInventariados(this.tagsInventariados);
        this.fragmentPagerAdapterTags.setTagsNoInventariados(this.tagsNoInventariados);
        //this.adapter = new AdapterInventarioFisicoDetalle(tagsInventariados);
        //this.recyclerViewFisicoDetalle.setAdapter(this.adapter);
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
