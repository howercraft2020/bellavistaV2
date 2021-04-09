package cl.clsoft.bave.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.ConsultaResumenItem;
import cl.clsoft.bave.presenter.ConsultaSubinventarioDetallePresenter;
import cl.clsoft.bave.service.impl.ConsultaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import fr.ganfra.materialspinner.MaterialSpinner;

public class ActivityConsultaSubinventarioDetalle extends BaseActivity<ConsultaSubinventarioDetallePresenter> {

    // Variables
    private String TAG = "ConsultaSubinventario";
    private String subinventoryCode;
    private Long inventoryItemId;
    private List<ConsultaItem> items;

    // Controls
    private RecyclerView recyclerViewItems;

    @NonNull
    @Override
    protected ConsultaSubinventarioDetallePresenter createPresenter(@NonNull Context context) {
        return new ConsultaSubinventarioDetallePresenter(this, new AppTaskExecutor(this), new ConsultaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_subinventario_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewItems = findViewById(R.id.recyclerViewItems);

        // Set Controls
        this.subinventoryCode = this.getIntent().getStringExtra("SubinventoryCode");
        this.inventoryItemId = this.getIntent().getLongExtra("InventoryItemId", 0);

        this.recyclerViewItems.setHasFixedSize(true);
        this.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        this.mPresenter.getStock(this.subinventoryCode, this.inventoryItemId);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillStock(List<ConsultaItem> items) {
        this.items = items;
        AdapterItemConsultaSigle adapterItemConsultaSigle = new AdapterItemConsultaSigle(items);
        this.recyclerViewItems.setAdapter(adapterItemConsultaSigle);
        if (items.size() == 0) {
            showWarning("No se encontró Stock para " + subinventoryCode);
        }
    }

}
