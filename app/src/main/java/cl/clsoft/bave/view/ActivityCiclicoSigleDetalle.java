package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.presenter.CiclicoSigleDetallePresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;

public class ActivityCiclicoSigleDetalle extends BaseActivity<CiclicoSigleDetallePresenter> {

    private static final String TAG = "CiclicoSigleDetalle";
    private AdapterItemSigleDetalle adapter;
    private Long sigleDetalle;
    private TextView idSigle;
    private TextView sigleDescripcion;
    //private Button btn;

    private List<MtlCycleCountEntries> entries;
    private RecyclerView recyclerViewSigleDetalle;

    @NonNull
    @Override
    protected CiclicoSigleDetallePresenter createPresenter(@NonNull Context context) {
        return new CiclicoSigleDetallePresenter(this, new ConteoCiclicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_sigle_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewSigleDetalle = findViewById(R.id.itemRecyclerViewSigleDetalle);

        // Set Controls
        this.recyclerViewSigleDetalle.setHasFixedSize(true);
        this.recyclerViewSigleDetalle.setLayoutManager(new LinearLayoutManager(this));

        //Se reciben datos de la activity anterior y se insertan en la activity actual
        sigleDetalle = this.getIntent().getLongExtra("ciclicoDetalleId",0);
        MtlCycleCountEntries inventario = mPresenter.getPreViousDetalleCiclicoSigle(sigleDetalle);
        this.sigleDescripcion = findViewById(R.id.sigleDescripcion);
        this.idSigle = findViewById(R.id.idSigle);

        entries = mPresenter.getSigleInformation(sigleDetalle);
        Log.d("++++++++++++++++--+++++", String.valueOf(entries.size()));
        this.adapter = new AdapterItemSigleDetalle(entries);
        this.recyclerViewSigleDetalle.setAdapter(this.adapter);


        if (inventario != null){
            this.idSigle.setText(inventario.getSegment1());
            this.sigleDescripcion.setText(inventario.getEntryStatusCode());
        }

        FloatingActionButton btn = findViewById(R.id.PlusButton);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent plus = new Intent(v.getContext(), ActivityAgregarCiclico.class);
                startActivityForResult(plus,0);
            }
        });

    }


}