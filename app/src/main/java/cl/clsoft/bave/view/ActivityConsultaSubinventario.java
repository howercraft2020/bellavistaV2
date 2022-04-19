package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.ConsultaResumenItem;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.ConsultaSubinventarioPresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.ConsultaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import fr.ganfra.materialspinner.MaterialSpinner;

public class ActivityConsultaSubinventario extends BaseActivity<ConsultaSubinventarioPresenter> {

    // Variables
    private String TAG = "ConsultaSubinventario";
    private String subinventoryCode;
    private List<ConsultaResumenItem> resumenes;

    // Controls
    private MaterialSpinner spinnerSubinventory;
    private RecyclerView recyclerViewItems;

    @NonNull
    @Override
    protected ConsultaSubinventarioPresenter createPresenter(@NonNull Context context) {
        return new ConsultaSubinventarioPresenter(this, new AppTaskExecutor(this), new ConsultaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_subinventario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.spinnerSubinventory = findViewById(R.id.spinnerSubinventory);
        this.recyclerViewItems = findViewById(R.id.recyclerViewItems);

        // Set Controls
        this.recyclerViewItems.setHasFixedSize(true);
        this.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        this.recyclerViewItems.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityConsultaSubinventarioDetalle.class);
                        i.putExtra("InventoryItemId", resumenes.get(position).getInventoryItemId());
                        i.putExtra("SubinventoryCode", subinventoryCode);
                        startActivity(i);
                        //finish();
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

        List<Subinventario> subinventarios = this.mPresenter.getSubinventarios();
        if (subinventarios != null) {
            if (subinventarios.size() > 0) {
                String[] arrSubinventarios = new String[subinventarios.size()];
                for (int i = 0; i < subinventarios.size(); i++) {
                    arrSubinventarios[i] = subinventarios.get(i).getCodSubinventario();
                }
                ArrayAdapter<String> adapterSubinventario = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrSubinventarios);
                this.spinnerSubinventory.setAdapter(adapterSubinventario);
            }
        }

        this.spinnerSubinventory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subinventoryCode = (String) adapterView.getSelectedItem();
                Log.d(TAG, "subinventoryCode: " + subinventoryCode);
                if (subinventoryCode != null) {
                    mPresenter.getStock(subinventoryCode);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

    public void fillStock(List<ConsultaResumenItem> items) {
        resumenes = items;
        AdapterItemConsultaResumenSigle adapterItemConsultaResumenSigle = new AdapterItemConsultaResumenSigle(items);
        this.recyclerViewItems.setAdapter(adapterItemConsultaResumenSigle);
        if (items.size() == 0) {
            showWarning("No se encontró Stock para " + subinventoryCode);
        }
        //this.spinnerSubinventory.setText("");
    }

}
