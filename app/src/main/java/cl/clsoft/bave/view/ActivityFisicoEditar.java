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
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.presenter.FisicoEditarPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityFisicoEditar extends BaseActivity<FisicoEditarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityFisicoEditar";
    private Long tagId;
    private Long inventarioId;
    private String subinventarioId;
    private Double cantidad;
    private MtlPhysicalInventoryTags tag;

    // Controls
    private TextView textInventarioId;
    private TextView textSubinventario;
    private TextView textLocalizador;
    private TextView textNumeroParte;
    private TextView textCodigoSigle;
    private TextView textNumeroSerie;
    private TextView textNumeroLote;
    private TextView textVencimiento;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;

    @NonNull
    @Override
    protected FisicoEditarPresenter createPresenter(@NonNull Context context) {
        return new FisicoEditarPresenter(this, new InventarioFisicoService());
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_editar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.tagId = this.getIntent().getLongExtra("tagId", 0);
        this.textInventarioId = findViewById(R.id.textInventarioId);
        this.textSubinventario = findViewById(R.id.textSubinventario);
        this.textLocalizador = findViewById(R.id.textLocalizador);
        this.textNumeroParte = findViewById(R.id.textNumeroParte);
        this.textCodigoSigle = findViewById(R.id.textCodigoSigle);
        this.textNumeroSerie = findViewById(R.id.textNumeroSerie);
        this.textNumeroLote = findViewById(R.id.textNumeroLote);
        this.textVencimiento = findViewById(R.id.textVencimiento);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);

    }

    @Override
    protected void onStart() {
        super.onStart();
        tag = mPresenter.getTag(tagId);
        if (tag != null) {
            if (tag.getPhysicalInventoryId() != null) {
                this.textInventarioId.setText(tag.getPhysicalInventoryId().toString());
            } else {
                this.textInventarioId.setText("");
            }
            this.textSubinventario.setText(tag.getSubinventory());
            if (tag.getLocatorId() != null) {
                this.textLocalizador.setText(tag.getLocatorId().toString());
            } else {
                this.textLocalizador.setText("");
            }
            this.textNumeroParte.setText(tag.getDescription());
            this.textCodigoSigle.setText(tag.getSegment1());
            this.textNumeroSerie.setText(tag.getSerialNum());
            this.textNumeroLote.setText(tag.getLocatorCode());
            this.textVencimiento.setText(tag.getLotExpirationDate());
            if (tag.getCount() != null)
                this.textCantidad.setText(tag.getCount().toString());
            this.inventarioId = tag.getPhysicalInventoryId();
            this.subinventarioId = tag.getSubinventory();
            layoutCantidad.setHint("Cantidad (" + tag.getPrimaryUomCode() + ")");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inventario_editar, menu);
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
                Intent i = new Intent(getApplicationContext(), ActivityFisicoDetalle.class);
                i.putExtra("InventarioId", this.inventarioId);
                i.putExtra("SubinventarioId", this.subinventarioId);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void mensajeOkDelete() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText("Éxito")
        .setContentText("Eliminación exitosa")
        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                Log.d("CMFA", "CLICK");
                Intent i = new Intent(getApplicationContext(), ActivityFisicoDetalle.class);
                i.putExtra("InventarioId", inventarioId);
                i.putExtra("SubinventarioId", subinventarioId);
                startActivity(i);
                finish();
            }
        })
        .show();
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("update")) {
            this.mPresenter.updateTag(tagId, cantidad);
        } else if (tipo.equalsIgnoreCase("delete")) {
            this.mPresenter.deleteTag(tagId);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }
}
