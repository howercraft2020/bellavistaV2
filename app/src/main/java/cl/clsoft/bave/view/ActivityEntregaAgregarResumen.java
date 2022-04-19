package cl.clsoft.bave.view;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.presenter.EntregaAgregarResumenPresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEntregaAgregarResumen extends BaseActivity<EntregaAgregarResumenPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "EntregaAgregar";
    private Long shipmentHeaderId;
    private Long transactionId;
    private Double cantidad;
    private String subinventoryCode;
    private String locatorCode;
    private boolean isLote = false;
    private boolean isSerie = false;
    private String lote;
    private String loteProveedor;
    private String vencimiento;
    private String categoria;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private List<String> series = new ArrayList<>();



    private IRestMtlSystemItems iRestMtlSystemItems;
    private IRestRcvTransactions iRestRcvTransactions;

    // Controls
    private TextView textProductoDescription;
    private TextView textProductoSigle;
    private TextView textProductoCantidad;
    private TextView textProductoLinea;
    private TextView textProductoLote;
    private TextView textProductoSerie;
    private TextView textSubinventoryCode;
    private TextView textLocatorCode;
    private TextView textLote;
    private TextView textLoteProveedor;
    private TextView textVencimiento;
    private TextView textCategoria;
    private TextView textAtributo1;
    private TextView textAtributo2;
    private TextView textAtributo3;
    private TextView textSeries;
    private RelativeLayout rlayoutLote;
    private RelativeLayout rlayoutSeries;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected EntregaAgregarResumenPresenter createPresenter(@NonNull Context context) {
        return new EntregaAgregarResumenPresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_agregar_resumen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textProductoDescription = findViewById(R.id.textProductoDescription);
        this.textProductoSigle = findViewById(R.id.textProductoSigle);
        this.textProductoCantidad = findViewById(R.id.textProductoCantidad);
        this.textProductoLinea = findViewById(R.id.textProductoLinea);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);
        this.textSubinventoryCode = findViewById(R.id.textSubinventoryCode);
        this.textLocatorCode = findViewById(R.id.textLocatorCode);
        this.textLote = findViewById(R.id.textLote);
        this.textLoteProveedor = findViewById(R.id.textLoteProveedor);
        this.textVencimiento = findViewById(R.id.textVencimiento);
        this.textCategoria = findViewById(R.id.textCategoria);
        this.textAtributo1 = findViewById(R.id.textAtributo1);
        this.textAtributo2 = findViewById(R.id.textAtributo2);
        this.textAtributo3 = findViewById(R.id.textAtributo3);
        this.textSeries = findViewById(R.id.textSeries);
        this.rlayoutLote = findViewById(R.id.rlayoutLote);
        this.rlayoutSeries = findViewById(R.id.rlayoutSeries);

        // Set Controls
        this.rlayoutLote.setVisibility(View.GONE);
        this.rlayoutSeries.setVisibility(View.GONE);
        this.shipmentHeaderId = this.getIntent().getLongExtra("ShipmentHeaderId", 0);
        this.transactionId = this.getIntent().getLongExtra("TransactionId", 0);
        this.cantidad = this.getIntent().getDoubleExtra("Cantidad", 0);
        this.subinventoryCode = this.getIntent().getStringExtra("SubinventoryCode");
        this.locatorCode = this.getIntent().getStringExtra("LocatorCode");
        this.lote = this.getIntent().getStringExtra("Lote");
        this.loteProveedor = this.getIntent().getStringExtra("LoteProveedor");
        this.vencimiento = this.getIntent().getStringExtra("Vencimiento");
        this.categoria = this.getIntent().getStringExtra("Categoria");
        this.atributo1 = this.getIntent().getStringExtra("Atributo1");
        this.atributo2 = this.getIntent().getStringExtra("Atributo2");
        this.atributo3 = this.getIntent().getStringExtra("Atributo3");
        this.series = this.getIntent().getStringArrayListExtra("series");
        if (this.series == null)
            this.series = new ArrayList<>();

        iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();


        iRestRcvTransactions.get(this.transactionId).enqueue(new Callback<RcvTransactions>() {
            @Override
            public void onResponse(Call<RcvTransactions> call, Response<RcvTransactions> response) {
                if(response.isSuccessful()==true && response.code()==200){
                    RcvTransactions transaction = response.body();

                    iRestMtlSystemItems.get(transaction.getItemId()).enqueue(new Callback<MtlSystemItems>() {
                        @Override
                        public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                            if(response.isSuccessful()==true && response.code()==200){

                                MtlSystemItems item = response.body();

                                if (item.getLotControlCode().equalsIgnoreCase("2")) {
                                    isLote = true;
                                } else {
                                    isLote = false;
                                }
                                if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                                    isSerie = true;
                                } else {
                                    isSerie = false;
                                }
                                Gson gson = new Gson();

                                Log.d(TAG,"PRODUCTO"+ gson.toJson(item));

                                fillProducto(item.getDescription(), item.getSegment1(), cantidad,
                                        transaction.getLineNum(), isLote, isSerie, subinventoryCode, locatorCode);

                                if (isLote) {
                                    fillLote(lote, loteProveedor,vencimiento,categoria,atributo1,atributo2,atributo3);
                                }
                                if (isSerie) {
                                    fillSeries(series);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                            showError("MtlSystemItems nula "+t.getMessage());
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<RcvTransactions> call, Throwable t) {
                showError("RcvTransactions nula "+t.getMessage());
            }
        });
        /*
        RcvTransactions transaction = mPresenter.getTransactionById(this.transactionId);
      //  if (transaction != null) {
            MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getItemId());
          //  if (item != null) {
                if (item.getLotControlCode().equalsIgnoreCase("2")) {
                    this.isLote = true;
                } else {
                    this.isLote = false;
                }
                if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                    this.isSerie = true;
                } else {
                    this.isSerie = false;
                }
                Gson gson = new Gson();

                Log.d(TAG,"PRODUCTO"+ gson.toJson(item));

                this.fillProducto(item.getDescription(), item.getSegment1(), this.cantidad,
                        transaction.getLineNum(), this.isLote, this.isSerie, this.subinventoryCode, this.locatorCode);

                if (this.isLote) {
                    this.fillLote(this.lote, this.loteProveedor, this.vencimiento, this.categoria, this.atributo1, this.atributo2, this.atributo3);
                }
                if (this.isSerie) {
                    this.fillSeries(this.series);
                }
       //     }
       // }

         */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        this.confirmacionSalir();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_entrega_agregar_resumen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                this.confirmacionSalir();
                return true;
            case R.id.grabar:
                Log.d(TAG, "grabar");
                ConfirmationDialog dialogSave = ConfirmationDialog.newInstance("Desea ingresar este producto a la entrega?", "Confirmación", "save");
                dialogSave.show(getSupportFragmentManager(), "saveAgregarConfirm");
                return true;
            case R.id.back:
                Log.d(TAG, "back");
                if (this.isSerie) {
                    Intent iSerie = new Intent(this, ActivityEntregaAgregarSerie.class);
                    iSerie.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iSerie.putExtra("TransactionId", this.transactionId);
                    iSerie.putExtra("Cantidad", this.cantidad);
                    iSerie.putExtra("SubinventoryCode", this.subinventoryCode);
                    iSerie.putExtra("LocatorCode", this.locatorCode);
                    iSerie.putExtra("Lote", this.lote);
                    iSerie.putExtra("LoteProveedor", this.loteProveedor);
                    iSerie.putExtra("Vencimiento", this.vencimiento);
                    iSerie.putExtra("Categoria", this.categoria);
                    iSerie.putExtra("Atributo1", this.atributo1);
                    iSerie.putExtra("Atributo2", this.atributo2);
                    iSerie.putExtra("Atributo3", this.atributo3);
                    iSerie.putStringArrayListExtra("series", (ArrayList<String>) series);
                    startActivity(iSerie);
                    this.finish();
                } else if (this.isLote) {
                    Intent iLote = new Intent(this, ActivityEntregaAgregarLote.class);
                    iLote.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iLote.putExtra("TransactionId", this.transactionId);
                    iLote.putExtra("Cantidad", this.cantidad);
                    iLote.putExtra("SubinventoryCode", this.subinventoryCode);
                    iLote.putExtra("LocatorCode", this.locatorCode);
                    iLote.putExtra("Lote", this.lote);
                    iLote.putExtra("LoteProveedor", this.loteProveedor);
                    iLote.putExtra("Vencimiento", this.vencimiento);
                    iLote.putExtra("Categoria", this.categoria);
                    iLote.putExtra("Atributo1", this.atributo1);
                    iLote.putExtra("Atributo2", this.atributo2);
                    iLote.putExtra("Atributo3", this.atributo3);
                    iLote.putStringArrayListExtra("series", (ArrayList<String>) series);
                    startActivity(iLote);
                    this.finish();
                } else {
                    Intent iAgregar = new Intent(this, ActivityEntregaAgregar.class);
                    iAgregar.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                    iAgregar.putExtra("TransactionId", this.transactionId);
                    iAgregar.putExtra("Cantidad", this.cantidad);
                    iAgregar.putExtra("SubinventoryCode", this.subinventoryCode);
                    iAgregar.putExtra("LocatorCode", this.locatorCode);
                    iAgregar.putExtra("Lote", this.lote);
                    iAgregar.putExtra("LoteProveedor", this.loteProveedor);
                    iAgregar.putExtra("Vencimiento", this.vencimiento);
                    iAgregar.putExtra("Categoria", this.categoria);
                    iAgregar.putExtra("Atributo1", this.atributo1);
                    iAgregar.putExtra("Atributo2", this.atributo2);
                    iAgregar.putExtra("Atributo3", this.atributo3);
                    iAgregar.putStringArrayListExtra("series", (ArrayList<String>) series);
                    startActivity(iAgregar);
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillProducto(String descripcion, String segment, Double cantidad, Long linea,
                              boolean isLote, boolean isSerie, String subinventoryCode, String locatorCode) {
        this.textProductoDescription.setText(descripcion);
        this.textProductoSigle.setText(segment);
        this.textProductoCantidad.setText(cantidad.toString());
        this.textProductoLinea.setText(linea.toString());
        if (isLote)
            this.textProductoLote.setText("SI");
        else
            this.textProductoLote.setText("NO");
        if (isSerie)
            this.textProductoSerie.setText("SI");
        else
            this.textProductoSerie.setText("NO");
        this.textSubinventoryCode.setText(subinventoryCode);
        this.textLocatorCode.setText(locatorCode);
    }

    private void fillLote(String lote, String loteProveedor, String vencimiento, String categoria, String atributo1, String atributo2, String atributo3) {
        this.textLote.setText(lote);
        this.textLoteProveedor.setText(loteProveedor);
        this.textVencimiento.setText(vencimiento);
        this.textCategoria.setText(categoria);
        this.textAtributo1.setText(atributo1);
        this.textAtributo2.setText(atributo2);
        this.textAtributo3.setText(atributo3);
        this.rlayoutLote.setVisibility(View.VISIBLE);
    }

    private void fillSeries(List<String> series) {
        String strSeries = "";

        if (series != null) {
            for (String serie : series) {
                strSeries = strSeries + serie + ", ";
            }
        }
        this.textSeries.setText(strSeries);
        this.rlayoutSeries.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("exit")) {
            Intent i = new Intent(this, ActivityEntregaDetalle.class);
            i.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
            startActivity(i);
            this.finish();
        } else if (tipo.equalsIgnoreCase("save")) {
            this.mPresenter.addTransacctionInterface(this.shipmentHeaderId, this.transactionId, this.subinventoryCode,
                    this.locatorCode, this.lote, this.loteProveedor, this.vencimiento, this.categoria, this.atributo1, this.atributo2, this.atributo3, this.series, this.cantidad);
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    public void resultadoOkAddTransaction() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
            .setContentText("Creación exitosa")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    Log.d("CMFA", "CLICK");
                    Intent i = new Intent(getApplicationContext(), ActivityEntregaDetalle.class);
                    i.putExtra("ShipmentHeaderId", shipmentHeaderId);
                    startActivity(i);
                    finish();
                }
            })
            .show();
    }
}
