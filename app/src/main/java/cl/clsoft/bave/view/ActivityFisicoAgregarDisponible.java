package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import cl.clsoft.bave.presenter.FisicoAgregarDisponiblePresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityFisicoAgregarDisponible extends BaseActivity<FisicoAgregarDisponiblePresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "FISICO";
    private Long tagId;
    private Long inventarioId;
    private String subinventarioId;
    private Double cantidad;
    private MtlPhysicalInventoryTags tag;

    // Controls
    private TextView textLocalizador;
    private TextView textNumeroParte;
    private TextView textCodigoSigle;
    private TextView textNumeroSerie;
    private TextView textNumeroLote;
    private TextView textVencimiento;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected FisicoAgregarDisponiblePresenter createPresenter(@NonNull Context context) {
        return new FisicoAgregarDisponiblePresenter(this, new AppTaskExecutor(this), new InventarioFisicoService());
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_agregar_disponible);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        // bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.tagId = this.getIntent().getLongExtra("tagId", 0);
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
            if (tag.getLocatorId() != null) {
                String[] localizadorArr = tag.getLocatorCode().split("\\.");
                this.textLocalizador.setText(localizadorArr[0]);
            } else {
                this.textLocalizador.setText("");
            }
            this.textNumeroParte.setText(tag.getDescription());
            this.textCodigoSigle.setText(tag.getSegment1());
            this.textNumeroSerie.setText(tag.getSerialNum());
            if (tag.getSerialNum() != null && !tag.getSerialNum().isEmpty()) {
                this.textCantidad.setEnabled(false);
                this.textCantidad.setText("1.0");
            }
            this.textNumeroLote.setText(tag.getLotNumber());
            this.textVencimiento.setText(tag.getLotExpirationDate());
            this.inventarioId = tag.getPhysicalInventoryId();
            this.subinventarioId = tag.getSubinventory();
            layoutCantidad.setHint("Cantidad (" + tag.getPrimaryUomCode() + ")");
        }
    }

    @Override
    public void onBackPressed() {
        this.confirmacionSalir();
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
        inflater.inflate(R.menu.menu_agregar_tag_disponible, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.confirmacionSalir();
                return true;
            case R.id.guardarInventario:
                this.grabarInventario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("exit")) {
            Intent i = new Intent(this, ActivityFisicoTags.class);
            i.putExtra("InventarioId", inventarioId);
            i.putExtra("SubinventarioId", this.subinventarioId);
            startActivity(i);
            this.finish();
        } else         if (tipo.equalsIgnoreCase("grabar")) {
            mPresenter.grabarInventario(this.inventarioId, tag.getSubinventory(), tag.getLocatorId(), tag.getSegment1(), tag.getSerialNum(), tag.getLotNumber(), tag.getLotExpirationDate(), this.cantidad);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    public void grabarInventario() {
        // Validaciones

        String strCantidad = this.textCantidad.getText().toString();
        if (strCantidad.isEmpty()) {
            this.textCantidad.setError("Ingrese la cantidad");
            return;
        }
        Double cantidad = Double.valueOf(strCantidad);
        if (cantidad < 0) {
            this.textCantidad.setError("Ingrese una cantidad válida");
            return;
        }

        this.cantidad = cantidad;
        // Confirmacion
        ConfirmationDialog dialogGrabar = ConfirmationDialog.newInstance("Esta seguro de grabar el inventario?", "Confirmación", "grabar");
        dialogGrabar.show(getSupportFragmentManager(), "grabarinventarioConfirm");

    }

    public void mensajeOkGrabar() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
                .setContentText("Creación exitosa")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i = new Intent(getApplicationContext(), ActivityFisicoTags.class);
                        i.putExtra("InventarioId", inventarioId);
                        i.putExtra("SubinventarioId", subinventarioId);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

}
