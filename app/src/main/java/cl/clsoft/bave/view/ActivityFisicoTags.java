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
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.presenter.FisicoTagsPresenter;
import cl.clsoft.bave.R;
import cl.clsoft.bave.dao.rowmapper.service.impl.InventarioFisicoService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityFisicoTags extends BaseActivity<FisicoTagsPresenter> {

    // Variables
    private String TAG = "FISICO_TAGS";
    private Long inventarioId;
    private String subinventarioId;
    private MtlPhysicalInventories inventario;
    private List<MtlPhysicalInventoryTags> tagsInventariados;
    private List<MtlPhysicalInventoryTags> tagsNoInventariados;
    private boolean cargandoTagsInventariado = false;
    private boolean cargandoTagsNoInventariado = false;

    //Controls
    private TextView textId;
    private TextView textNombre;
    private TextView textDescription;
    private TextView textSubinventario;
    private TextView textFechaCreacion;
    private ViewPager viewPagerDetalle;
    private FragmentPagerAdapterTags fragmentPagerAdapterTags;

    @Override
    protected FisicoTagsPresenter createPresenter(@NonNull Context context) {
        return new FisicoTagsPresenter(this, new AppTaskExecutor(this), new InventarioFisicoService());
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

        mPresenter.getTagsInventariados(this.inventarioId, this.subinventarioId);
        mPresenter.getTagsNoInventariados(this.inventarioId, this.subinventarioId);
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    public void fillTags(List<MtlPhysicalInventoryTags> tags) {
        Log.d(TAG, "fillTags");

        if (tags != null) {
            this.tagsInventariados = tags;
            this.fragmentPagerAdapterTags.setTagsInventariados(this.tagsInventariados);
        }
    }

    public void fillTagsNoInventariados(List<MtlPhysicalInventoryTags> tags) {
        Log.d(TAG, "fillTagsNoInventariados");

        if (tags != null) {
            this.tagsNoInventariados = tags;
            this.fragmentPagerAdapterTags.setTagsNoInventariados(this.tagsNoInventariados);
        }
    }

    public boolean getCargandoTagsInventariado() {
        return cargandoTagsInventariado;
    }

    public void setCargandoTagsInventariado(boolean cargandoTagsInventariado) {
        this.cargandoTagsInventariado = cargandoTagsInventariado;
    }

    public boolean getCargandoTagsNoInventariado() {
        return cargandoTagsNoInventariado;
    }

    public void setCargandoTagsNoInventariado(boolean cargandoTagsNoInventariado) {
        this.cargandoTagsNoInventariado = cargandoTagsNoInventariado;
    }

}
