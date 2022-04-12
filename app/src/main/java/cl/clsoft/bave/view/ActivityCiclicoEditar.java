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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlCycleCountEntries;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.presenter.CiclicoEditarPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCiclicoEditar extends BaseActivity<CiclicoEditarPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private static final String TAG = "CiclicoEditar";
    private Long entryId;
    private Long inventarioCiclicoId;
    private String subinventarioId;
    private Double cantidad;


    //REST API

    private IRestMtlCycleCountEntries iRestMtlCycleCountEntries;

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

        //API
        iRestMtlCycleCountEntries = ApiUtils.getIRestMtlCycleCountEntries();

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
            Log.d(TAG, "ConteoCiclicoService::updateEntry");
            Log.d(TAG, "ConteoCiclicoService::updateEntry::entryId: " + entryId);
            Log.d(TAG, "ConteoCiclicoService::updateEntry::cantidad: " + cantidad);

            iRestMtlCycleCountEntries.get(this.entryId).enqueue(new Callback<MtlCycleCountEntries>() {
                @Override
                public void onResponse(Call<MtlCycleCountEntries> call, Response<MtlCycleCountEntries> response) {

                    if(response.isSuccessful()==true && response.code()==200 && response.body().toString()!="{}"){

                        MtlCycleCountEntries entry = response.body();

                        entry.setCount(cantidad);
                        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                        String strLastUpdate = dateFormat.format(new Date());
                        entry.setLastUpdated(strLastUpdate.toUpperCase());

                        iRestMtlCycleCountEntries.insert(entry).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()==true && response.code()==200){

                                    showSuccess("Conteo editado Correctamente");
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });

                    }

                    if(response.isSuccessful()==true && response.code()==200 && response.body().toString()=="{}"){

                        showError("Entry " + entryId + " no existe.");

                    }

                }

                @Override
                public void onFailure(Call<MtlCycleCountEntries> call, Throwable t) {

                }
            });


        //this.mPresenter.updateEntry(this.entryId, cantidad);


        } else if (tipo.equalsIgnoreCase("delete")) {


            iRestMtlCycleCountEntries.get(this.entryId).enqueue(new Callback<MtlCycleCountEntries>() {
                @Override
                public void onResponse(Call<MtlCycleCountEntries> call, Response<MtlCycleCountEntries> response) {
                    if(response.isSuccessful()==true && response.code()==200 && response.body().toString()!="{}") {

                        MtlCycleCountEntries entry = response.body();


                        entry.setCount(null);
                        entry.setLastUpdated(null);

                        iRestMtlCycleCountEntries.insert(entry).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()==true && response.code()==200){

                                    showSuccess("Conteo eliminado Correctamente");
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });


                    }

                    if(response.isSuccessful()==true && response.code()==200 && response.body().toString()=="{}"){

                        showError("Entry " + entryId + " no existe.");

                    }


                }

                @Override
                public void onFailure(Call<MtlCycleCountEntries> call, Throwable t) {

                }
            });

            //this.mPresenter.deleteEntry(this.entryId);
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
