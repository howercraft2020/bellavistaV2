package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.DatosTransOrgDetalle;
import cl.clsoft.bave.presenter.TransOrgDetallePresenter;
import cl.clsoft.bave.service.impl.TransOrgService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityTransOrgDetalle extends BaseActivity<TransOrgDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    //Variables
    private static final String TAG = "ActivityTransOrgDet";
    private List<DatosTransOrgDetalle> transferencias;
    private String numeroTraspaso, glosa;
    private TextView numeroTraspasoEt, glosaEt;

    //Controls
    private RecyclerView recyclerViewTransOrgDetalle;
    private AdapterTransOrgDetalle adapter;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected TransOrgDetallePresenter createPresenter(@NonNull Context context) {
        return new TransOrgDetallePresenter(this, new TransOrgService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_trans_org_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_org_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numeroTraspasoEt = (TextView) findViewById(R.id.numeroTraspaso);
        glosaEt = (TextView) findViewById(R.id.glosa);

        numeroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        glosa = getIntent().getStringExtra("glosa");

        numeroTraspasoEt.setText(numeroTraspaso);
        glosaEt.setText(glosa);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewTransOrgDetalle = findViewById(R.id.recyclerViewTransOrgDetalle);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewTransOrgDetalle.setHasFixedSize(true);
        this.recyclerViewTransOrgDetalle.setLayoutManager(new LinearLayoutManager(this));

        transferencias = mPresenter.getTransferencias(numeroTraspaso);

        this.adapter = new AdapterTransOrgDetalle(transferencias);
        this.recyclerViewTransOrgDetalle.setAdapter(this.adapter);

        this.recyclerViewTransOrgDetalle.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                try{
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityTransOrgTransactionDetalle.class);
                        i.putExtra("transactionInterfaceId", transferencias.get(position).getTransactionInterfaceId());
                        i.putExtra("numeroTraspaso", numeroTraspaso);
                        i.putExtra("glosa", glosa);
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
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_create_file:
                Log.d(TAG, "cerrar");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar la transferencia?", "Confirmación", "close");
                dialogClose.show(getSupportFragmentManager(), "closeEntregaConfirm");
                return true;
            case R.id.action_more:
                Intent i = new Intent(this, ActivityAgregarTransOrgDestino.class);
                i.putExtra("numeroTraspaso", numeroTraspaso);
                i.putExtra("glosa", glosa);
                startActivity(i);
                this.finish();
                return true;
            case android.R.id.home:
                Intent ihome = new Intent(this, ActivityTransSubinv.class);
                startActivity(ihome);
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
            mPresenter.crearArchivo(this.numeroTraspaso);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void resultadoOkCerrarTransferencia() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
                .setContentText("Cierre exitoso")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i = new Intent(getApplicationContext(), ActivityTransSubinv.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }
}