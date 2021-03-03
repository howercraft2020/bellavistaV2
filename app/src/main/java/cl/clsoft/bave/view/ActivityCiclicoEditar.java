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
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.presenter.CiclicoEditarPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityCiclicoEditar extends BaseActivity<CiclicoEditarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private static final String TAG = "CiclicoEditar";
    private Long entryId;
    private Long inventarioCiclicoId;
    private String subinventarioId;
    private Double cantidad;

    // Controls
    private TextView textLocalizador;
    private TextView textNumeroParte;
    private TextView textCodigoSigle;
    private TextView textNumeroSerie;
    private TextView textNumeroLote;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;

    @NonNull
    @Override
    protected CiclicoEditarPresenter createPresenter(@NonNull Context context) {
        return new CiclicoEditarPresenter(this, new AppTaskExecutor(this), new ConteoCiclicoService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // setup layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_editar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textLocalizador = findViewById(R.id.textLocalizador);
        this.textNumeroParte = findViewById(R.id.textNumeroParte);
        this.textCodigoSigle = findViewById(R.id.textCodigoSigle);
        this.textNumeroSerie = findViewById(R.id.textNumeroSerie);
        this.textNumeroLote = findViewById(R.id.textNumeroLote);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);

        // set controls
        this.entryId = this.getIntent().getLongExtra("entryId",0);
        MtlCycleCountEntries mtlCycleCountEntries = this.mPresenter.getEntry(this.entryId);
        if (mtlCycleCountEntries != null) {
            this.inventarioCiclicoId = mtlCycleCountEntries.getCycleCountHeaderId();
            this.subinventarioId = mtlCycleCountEntries.getSubinventory();
            if (mtlCycleCountEntries.getLocatorId() != null) {
                this.textLocalizador.setText(mtlCycleCountEntries.getLocatorCode());
            } else {
                this.textLocalizador.setText("");
            }
            this.textNumeroParte.setText(mtlCycleCountEntries.getDescription());
            this.textCodigoSigle.setText(mtlCycleCountEntries.getSegment1());
            this.textNumeroSerie.setText(mtlCycleCountEntries.getSerialNumber());
            this.textNumeroLote.setText(mtlCycleCountEntries.getLotNumber());
            layoutCantidad.setHint("Cantidad (" + mtlCycleCountEntries.getPrimaryUomCode() + ")");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ciclico_editar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                ConfirmationDialog dialogDelete = ConfirmationDialog.newInstance("Esta seguro de eliminar el inventario?", "Confirmación", "delete");
                dialogDelete.show(getSupportFragmentManager(), "deleteInventarioConfirm");
                return true;
            case R.id.update:
                String strCantidad = this.textCantidad.getText().toString();
                if (strCantidad.isEmpty()) {
                    this.textCantidad.setError("Ingrese la cantidad");
                    return true;
                }
                cantidad = Double.valueOf(strCantidad);
                if (cantidad < 0) {
                    this.textCantidad.setError("Ingrese una cantidad válida");
                    return true;
                }

                ConfirmationDialog dialogUpdate = ConfirmationDialog.newInstance("Esta seguro de actualizar el inventario?", "Confirmación", "update");
                dialogUpdate.show(getSupportFragmentManager(), "updateInventarioConfirm");
                return true;
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), ActivityCiclicoDetalle.class);
                i.putExtra("ciclicosId", this.inventarioCiclicoId);
                i.putExtra("subinventarioId", this.subinventarioId);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("update")) {
            this.mPresenter.updateEntry(this.entryId, cantidad);
        } else if (tipo.equalsIgnoreCase("delete")) {
            this.mPresenter.deleteEntry(this.entryId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void mensajeOkDelete() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText("Eliminación exitosa")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Log.d("CMFA", "CLICK");
                        Intent i = new Intent(getApplicationContext(), ActivityCiclicoDetalle.class);
                        i.putExtra("ciclicosId", inventarioCiclicoId);
                        i.putExtra("subinventarioId", subinventarioId);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

}
