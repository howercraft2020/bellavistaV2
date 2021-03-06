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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.presenter.FisicoSubPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityFisicoSub extends BaseActivity<FisicoSubPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityFisicoSub";
    private Long inventarioId;
    private List<MtlPhysicalSubinventories> subinventories;

    //Controls
    private TextView textId;
    private TextView textNombre;
    private TextView textDescription;
    private TextView textFechaCreacion;
    private RecyclerView recyclerViewFisicoSub;
    private AdapterInventarioFisicoSub adapter;
    private SweetAlertDialog dialog;

    @Override
    protected FisicoSubPresenter createPresenter(@NonNull Context context){
        return new FisicoSubPresenter(this, new AppTaskExecutor(this), new InventarioFisicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_sub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewFisicoSub = findViewById(R.id.itemsFisicoSub);
        this.textId = findViewById(R.id.textId);
        this.textNombre = findViewById(R.id.textNombre);
        this.textDescription = findViewById(R.id.textDescription);
        this.textFechaCreacion = findViewById(R.id.textFechaCreacion);

        //Set controls
        inventarioId = this.getIntent().getLongExtra("InventarioId", 0);
        MtlPhysicalInventories inventario = mPresenter.getPreviousInventarioFisicos(inventarioId);
        subinventories = mPresenter.getSubinventories(inventarioId);
        Log.d("SUBINVENTORY TAMA??O: ", String.valueOf(subinventories.size()));

        this.recyclerViewFisicoSub.setHasFixedSize(true);
        this.recyclerViewFisicoSub.setLayoutManager(new LinearLayoutManager(this));

        this.adapter = new AdapterInventarioFisicoSub(subinventories);
        this.recyclerViewFisicoSub.setAdapter(this.adapter);


        if(inventario != null) {
            this.textId.setText(inventario.getPhysicalInventoryId().toString());
            this.textNombre.setText(inventario.getPhysicalInventoryName());
            this.textDescription.setText(inventario.getDescription());
            this.textFechaCreacion.setText(inventario.getCreationDate());
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
                        i = new Intent(getApplicationContext(), ActivityFisicoTags.class);

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
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inventario_subinventario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cerrar:
                Log.d(TAG, "Cerrar inventario");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar el inventario?", "Confirmaci??n", "close");
                dialogClose.show(getSupportFragmentManager(), "closeInventarioConfirm");
                return true;
            case android.R.id.home:
                Intent o = new Intent(this, ActivityFisicos.class);
                startActivity(o);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("close")) {
            mPresenter.closeInventory(this.inventarioId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void mensajeOkCloseInventory() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("??xito")
        .setContentText("Inventario cerrado con ??xito")
        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                Log.d("CMFA", "CLICK");
                Intent i = new Intent(getApplicationContext(), ActivityFisicos.class);
                startActivity(i);
                finish();
            }
        })
        .show();
    }

    public void mensajeErrorCloseInventory() {
        this.showError("No se pudo cerrar el inventario");
    }
}
