package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.presenter.CiclicoDetallePresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;

public class ActivityCiclicoDetalle extends BaseActivity<CiclicoDetallePresenter> {

    private AdapterItemCiclicoDetalle adapter;
    private static final String TAG = "CiclicoDetalle";
    private Long inventarioCiclicoId;
    private TextView cycleCountHeaderIdDetalle;
    private TextView cycleCountHeaderNameDetalle;
    private TextView creationDateDetalle;

    private List<MtlCycleCountEntries> entries;
    private RecyclerView recyclerViewDetalle;

    @NonNull
    @Override
    protected CiclicoDetallePresenter createPresenter(@NonNull Context context) {
        return new CiclicoDetallePresenter(this, new ConteoCiclicoService());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.recyclerViewDetalle = findViewById(R.id.itemCiclicoDetalleRecycler);
        this.recyclerViewDetalle.setHasFixedSize(true);
        this.recyclerViewDetalle.setLayoutManager(new LinearLayoutManager(this));

        inventarioCiclicoId = this.getIntent().getLongExtra("ciclicosId",0);
        MtlCycleCountHeaders inventario = mPresenter.getDPreViousDetalleCiclicos(inventarioCiclicoId);
        this.cycleCountHeaderIdDetalle = findViewById(R.id.cycleCountHeaderIdDetalle);
        this.cycleCountHeaderNameDetalle = findViewById(R.id.cycleCountHeaderNameDetalle);
        this.creationDateDetalle = findViewById(R.id.creationDateDetalle);

        entries = mPresenter.getSigleInformation(inventarioCiclicoId);
        this.adapter = new AdapterItemCiclicoDetalle(entries);
        this.recyclerViewDetalle.setAdapter(this.adapter);

        /*Log.d(TAG,inventario.getCycleCountHeaderName());
        Log.d(TAG,inventario.getCreationDate());
        Log.d(TAG,inventario.getCycleCountHeaderId().toString());
        Log.d(TAG,inventario.getDescription().toString());*/

        if(inventario != null){
            this.cycleCountHeaderIdDetalle.setText(inventario.getCycleCountHeaderId().toString());
            this.cycleCountHeaderNameDetalle.setText(inventario.getCycleCountHeaderName());
            this.creationDateDetalle.setText(inventario.getCreationDate());
        }
    }

}