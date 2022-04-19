package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.dto.MtlTransactionOrgDto;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.TransOrgTransactionDetallePresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.TransOrgService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityTransOrgTransactionDetalle extends BaseActivity<TransOrgTransactionDetallePresenter> implements ConfirmationDialog.ConfirmationDialogListener{

    // Variables
    private String TAG = "TransOrgAgregar";
    private Long transactionInterfaceId;
    private String numeroTraspaso;
    private String glosa;

    // Controls
    private TextView textProductoDescription;
    private TextView textProductoSigle;
    private TextView textProductoCantidad;
    private TextView textProductoLote;
    private TextView textProductoSerie;
    private TextView textSubinventoryCode;
    private TextView textOrgDestino;
    private TextView textLocatorCode;
    private TextView textLote;
    private TextView textGlosa;
    private TextView textSeries;
    private RelativeLayout rlayoutLote;
    private RelativeLayout rlayoutSeries;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected TransOrgTransactionDetallePresenter createPresenter(@NonNull Context context) {
        return new TransOrgTransactionDetallePresenter(this, new TransOrgService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_org_transaction_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textProductoDescription = findViewById(R.id.textProductoDescription);
        this.textProductoSigle = findViewById(R.id.textProductoSigle);
        this.textProductoCantidad = findViewById(R.id.textProductoCantidad);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);
        this.textSubinventoryCode = findViewById(R.id.textSubinventoryCode);
        this.textLocatorCode = findViewById(R.id.textLocatorCode);
        this.textOrgDestino = findViewById(R.id.textOrgDestino);
        this.textLote = findViewById(R.id.textLote);
        this.textGlosa = findViewById(R.id.textGlosa);
        this.textSeries = findViewById(R.id.textSeries);
        this.rlayoutLote = findViewById(R.id.rlayoutLote);
        this.rlayoutSeries = findViewById(R.id.rlayoutSeries);

        //Set Controls
        this.transactionInterfaceId = this.getIntent().getLongExtra("transactionInterfaceId",0);
        this.numeroTraspaso = getIntent().getStringExtra("numeroTraspaso");
        this.glosa = getIntent().getStringExtra("glosa");

        MtlTransactionOrgDto dto = mPresenter.getTransactionsInterfaceById(this.transactionInterfaceId);
        if (dto != null) {
            MtlSystemItems item = mPresenter.getMtlSystemItemsById(dto.getInventoryItemId());
            if (item != null){
                this.textProductoDescription.setText(item.getDescription());
                this.textProductoSigle.setText(item.getSegment1());
                this.textProductoCantidad.setText(dto.getCantidad().toString());
                this.textProductoLote.setText(dto.isLote() ? "SI" : "NO");
                this.textProductoSerie.setText(dto.isSerie() ? "SI" : "NO");
                this.textSubinventoryCode.setText(dto.getSubinvDesde());
                this.textOrgDestino.setText(dto.getOrgDestino());
                this.textLocatorCode.setText(dto.getLocalizador());
                this.textLote.setText(dto.getLote());
                this.textGlosa.setText(dto.getGlosa());

                if (dto.getSeries() != null) {
                    String strSeries = "";
                    for (String serie : dto.getSeries()) {
                        strSeries = strSeries + serie + ", ";
                    }
                    this.textSeries.setText(strSeries);
                }

                if (dto.isLote()) {
                    this.rlayoutLote.setVisibility(View.VISIBLE);
                } else {
                    this.rlayoutLote.setVisibility(View.GONE);
                }

                if (dto.isSerie()) {
                    this.rlayoutSeries.setVisibility(View.VISIBLE);
                } else {
                    this.rlayoutSeries.setVisibility(View.GONE);
                }

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                Intent i = new Intent(this, ActivityTransOrgDetalle.class);
                i.putExtra("transactionInterfaceId", this.transactionInterfaceId);
                i.putExtra("numeroTraspaso", numeroTraspaso);
                i.putExtra("glosa", glosa);
                startActivity(i);
                this.finish();
                return true;
            case R.id.delete:
                Log.d(TAG, "delete");
                ConfirmationDialog dialogDelete = ConfirmationDialog.newInstance("Esta seguro de eliminar la transacción?", "Confirmación", "delete");
                dialogDelete.show(getSupportFragmentManager(), "deleteTransactionConfirm");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("delete")) {
            mPresenter.deleteTransactionsInterfaceById(this.transactionInterfaceId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trans_org_transaction_detalle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void resultadoOkDeleteTransaction() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
                .setContentText("Eliminación exitosa")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Log.d("CMFA", "CLICK");
                        Intent i = new Intent(getApplicationContext(), ActivityTransOrg.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }
}