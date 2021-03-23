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
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.CiclicoSubPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityCiclicoSub extends BaseActivity<CiclicoSubPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityAgregarFisicoInventario";
    private Long inventarioCiclicoId;
    private List<Subinventario> subinventories;

    // Controls
    private TextView textCycleCountHeaderId;
    private TextView textCycleCountHeaderName;
    private TextView textCreationDate;
    private RecyclerView recyclerViewCiclicoSub;
    private AdapterConteoCiclicoSub adapter;

    @NonNull
    @Override
    protected CiclicoSubPresenter createPresenter(@NonNull Context context) {
        return new CiclicoSubPresenter(this, new AppTaskExecutor(this), new ConteoCiclicoService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_sub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textCycleCountHeaderId = findViewById(R.id.textCycleCountHeaderId);
        this.textCycleCountHeaderName = findViewById(R.id.textCycleCountHeaderName);
        this.textCreationDate = findViewById(R.id.textCreationDate);
        this.recyclerViewCiclicoSub = findViewById(R.id.recyclerViewCiclicoSub);

        //Set controls
        this.inventarioCiclicoId = this.getIntent().getLongExtra("ciclicosId",0);
        MtlCycleCountHeaders mtlCycleCountHeaders = this.mPresenter.getMtlCycleCountHeaders(this.inventarioCiclicoId);
        if (mtlCycleCountHeaders != null) {

            this.textCycleCountHeaderId.setText(mtlCycleCountHeaders.getCycleCountHeaderId().toString());
            this.textCycleCountHeaderName.setText(mtlCycleCountHeaders.getCycleCountHeaderName());
            this.textCreationDate.setText(mtlCycleCountHeaders.getCreationDate());

            this.recyclerViewCiclicoSub.setHasFixedSize(true);
            this.recyclerViewCiclicoSub.setLayoutManager(new LinearLayoutManager(this));
            this.mPresenter.loadSubinventories(mtlCycleCountHeaders.getCycleCountHeaderId());
        }

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewCiclicoSub.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityCiclicoDetalle.class);
                        i.putExtra("ciclicosId", inventarioCiclicoId);
                        i.putExtra("subinventarioId", subinventories.get(position).getCodSubinventario());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ciclico_subinventario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent o = new Intent(this, ActivityCiclicos.class);
                startActivity(o);
                this.finish();
                return true;
            case R.id.cerrar:
                Log.d(TAG, "Cerrar ciclico");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar el conteo?", "Confirmación", "close");
                dialogClose.show(getSupportFragmentManager(), "closeConteoConfirm");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("close")) {
            mPresenter.closeConteoCiclico(this.inventarioCiclicoId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void fillSubinventarios(List<Subinventario> subinventories) {
        if (subinventories != null) {
            this.subinventories = subinventories;
            this.adapter = new AdapterConteoCiclicoSub(this.subinventories);
            this.recyclerViewCiclicoSub.setAdapter(this.adapter);
        }
    }

    public void mensajeOkCloseInventory() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText("Conteo Ciclico cerrado con éxito")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i = new Intent(getApplicationContext(), ActivityCiclicos.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

    public void mensajeErrorCloseInventory() {
        this.showError("No se pudo cerrar el Conteo Ciclico");
    }

}