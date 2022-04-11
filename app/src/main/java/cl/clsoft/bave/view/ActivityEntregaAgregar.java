package cl.clsoft.bave.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.apis.IRestSubinventario;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.control.InstantAutoCompleteTextView;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.EntregaAgregarPresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEntregaAgregar extends BaseActivity<EntregaAgregarPresenter> implements ConfirmationDialog.ConfirmationDialogListener, DialogSeleccionLinea.DialogSeleccionLineaListener {

    // Variables
    private String TAG = "EntregaAgregar";
    private Long shipmentHeaderId;
    private Long transactionId;
    private String subinventoryCode;
    private String locatorCode;
    private Double cantidad;
    private Double cantidadTransaccion;
    private String lote;
    private String loteProveedor;
    private String vencimiento;
    private String categoria;
    private String atributo1;
    private String atributo2;
    private String atributo3;
    private List<String> series;
    private boolean isLote = false;
    private boolean isSerie = false;
    private String segment;
    private Long inventoryItemId;
    private String currentSigle = "";
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;
    CharSequence[] items;

    // Controls
    private TextInputLayout layoutSigle;
    private InstantAutoCompleteTextView textSigle;
    private ImageView iconSearch;
    private RelativeLayout rlayoutSigle;
    private RelativeLayout rlayoutItem;
    private TextView textProductoSigle;
    private TextView textProductoDescription;
    private TextView textProductoCantidad;
    private TextView textProductoLinea;
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private TextInputLayout layoutSubinventory;
    private InstantAutoCompleteTextView textSubinventory;
    private TextInputLayout layoutLocator;
    private InstantAutoCompleteTextView textLocator;
    private TextView textProductoLote;
    private TextView textProductoSerie;

    //VALRIABLES REST API
    private IRestRcvTransactions iRestRcvTransactions;
    private IRestMtlSystemItems iRestMtlSystemItems;
    private IRestSubinventario iRestSubinventario;

    @NonNull
    @Override
    protected EntregaAgregarPresenter createPresenter(@NonNull Context context) {
        return new EntregaAgregarPresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSigle = findViewById(R.id.layoutSerie);
        this.textSigle = findViewById(R.id.textSigle);
        this.iconSearch = findViewById(R.id.iconSearch);
        this.rlayoutSigle = findViewById(R.id.rlayoutSigle);
        this.rlayoutItem = findViewById(R.id.rlayoutItem);
        this.textProductoSigle = findViewById(R.id.textProductoSigle);
        this.textProductoDescription = findViewById(R.id.textProductoDescription);
        this.textProductoCantidad = findViewById(R.id.textProductoCantidad);
        this.textProductoLinea = findViewById(R.id.textProductoLinea);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);
        this.layoutSubinventory = findViewById(R.id.layoutSubinventory);
        this.textSubinventory = findViewById(R.id.textSubinventory);
        this.layoutLocator = findViewById(R.id.layoutLocator);
        this.textLocator = findViewById(R.id.textLocator);
        this.textProductoLote = findViewById(R.id.textProductoLote);
        this.textProductoSerie = findViewById(R.id.textProductoSerie);

        // Set Controls
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
        Log.d(TAG, "Lote: " + lote);
        Log.d(TAG, "Cantidad: " + cantidad);
        Log.d(TAG, "transactionId: " + transactionId);
        Log.d(TAG, "shipmentHeaderId: " + shipmentHeaderId);
        //Inicialización variables de API REST

        //transactionId=Long.parseLong("1907004");
        //cantidad = Double.parseDouble("20");
        iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        iRestSubinventario = ApiUtils.getIRestSubinventario();
        // Set up progress before call


        /*
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityEntregaAgregar.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando Códigos Sigle...");
        progressDoalog.setTitle("Entregas");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        */

        if (transactionId > 0) {


            iRestRcvTransactions.get(this.transactionId).enqueue(new Callback<RcvTransactions>() {
                @Override
                public void onResponse(Call<RcvTransactions> call, Response<RcvTransactions> response) {

                    if(response.isSuccessful() == true && response.code()==200){
                        RcvTransactions transaction = response.body();
                        if (transaction != null) {

                            iRestMtlSystemItems.get(transaction.getItemId()).enqueue(new Callback<MtlSystemItems>() {
                                @Override
                                public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                                    if(response.isSuccessful() == true && response.code()==200){

                                        MtlSystemItems item = response.body();

                                        if (item != null) {
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
                                            fillProducto(item.getDescription(), item.getSegment1(), transaction.getQuantity(),transaction.getLineNum(), isLote, isSerie);
                                            layoutCantidad.setVisibility(View.VISIBLE);
                                            layoutSubinventory.setVisibility(View.VISIBLE);
                                            layoutLocator.setVisibility(View.VISIBLE);
                                           // progressDoalog.dismiss();
                                        }


                                    }
                                    else {
                                        //progressDoalog.dismiss();
                                        Log.d("NULO", "VIENE NULO");
                                    }
                                }

                                @Override
                                public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                                    Log.d("CALLMTL1", "Error: " + t.getMessage());
                                }
                            });
                        }

                    }

                }

                @Override
                public void onFailure(Call<RcvTransactions> call, Throwable t) {
                    Log.d("CALLRCV", "Error: " + t.getMessage());
                }
            });


        }else {


            this.rlayoutSigle.setVisibility(View.VISIBLE);
            this.rlayoutItem.setVisibility(View.GONE);
            this.layoutCantidad.setVisibility(View.GONE);
            this.layoutSubinventory.setVisibility(View.GONE);
            this.layoutLocator.setVisibility(View.GONE);

           // progressDoalog.dismiss();
        }

        /*
        if (transactionId > 0) {
            RcvTransactions transaction = mPresenter.getTransactionById(this.transactionId);
            if (transaction != null) {
                MtlSystemItems item = mPresenter.getMtlSystemItemsById(transaction.getItemId());
                if (item != null) {
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
                    this.fillProducto(item.getDescription(), item.getSegment1(), transaction.getQuantity(),transaction.getLineNum(), this.isLote, this.isSerie);
                    this.layoutCantidad.setVisibility(View.VISIBLE);
                    this.layoutSubinventory.setVisibility(View.VISIBLE);
                    this.layoutLocator.setVisibility(View.VISIBLE);

                }
            }
        } else {
            this.rlayoutSigle.setVisibility(View.VISIBLE);
            this.rlayoutItem.setVisibility(View.GONE);
            this.layoutCantidad.setVisibility(View.GONE);
            this.layoutSubinventory.setVisibility(View.GONE);
            this.layoutLocator.setVisibility(View.GONE);
        }

         */

        this.textSigle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        segment = textView.getText().toString();
                        Log.d(TAG, "sigle: " + segment);
                        Log.d(TAG, "currentSigle: " + currentSigle);

                        if (!segment.equalsIgnoreCase(currentSigle)) {
                            currentSigle = segment;


                            iRestMtlSystemItems.getBySegment(segment).enqueue(new Callback<MtlSystemItems>() {
                                @Override
                                public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {

                                    if(response.isSuccessful() == true && response.code()==200){

                                        MtlSystemItems item = response.body();

                                        if (item != null) {
                                            inventoryItemId = item.getInventoryItemId();
                                        } else {
                                            textSigle.setText("");
                                            currentSigle = "";
                                        }
                                        validaSigle();

                                    }


                                }

                                @Override
                                public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                                    Log.d("CALLMTL2", "Error: " + t.getMessage());
                                }
                            });
                            /*
                            currentSigle = segment;

                            MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                            if (item != null) {
                                inventoryItemId = item.getInventoryItemId();
                            } else {
                                textSigle.setText("");
                                currentSigle = "";
                            }
                            validaSigle();
                            */

                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        this.textSigle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                segment = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "sigle: " + segment);
                Log.d(TAG, "currentSigle: " + currentSigle);

                if (!segment.equalsIgnoreCase(currentSigle)) {
                    currentSigle = segment;

                    iRestMtlSystemItems.getBySegment(segment).enqueue(new Callback<MtlSystemItems>() {
                        @Override
                        public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                            if(response.isSuccessful() == true && response.code()==200) {
                                MtlSystemItems item = response.body();

                                if (item != null) {
                                    inventoryItemId = item.getInventoryItemId();
                                } else {
                                    textSigle.setText("");
                                    currentSigle = "";
                                }
                                validaSigle();


                            }
                        }

                        @Override
                        public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                            Log.d("CALLMTL3", "Error: " + t.getMessage());

                        }
                    });



                    /*
                    currentSigle = segment;
                    MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                    if (item != null) {
                        inventoryItemId = item.getInventoryItemId();
                    } else {
                        textSigle.setText("");
                        currentSigle = "";
                    }
                    validaSigle();
                    */
                }
            }
        });


        this.iconSearch.setOnClickListener(v -> {
            Intent iSearch = new Intent(this, ActivitySigleSearch.class);
            iSearch.putExtra("Tipo", "E");
            iSearch.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
            startActivityForResult(iSearch, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });

        this.textSubinventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                subinventoryCode = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "subinventoryCode: " + subinventoryCode);
                if (subinventoryCode != null) {
                    mPresenter.getLocalizadoresBySubinventario(subinventoryCode);
                    textLocator.requestFocus();
                }
            }
        });

        this.textLocator.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                locatorCode = parent.getAdapter().getItem(pos).toString();
                Log.d(TAG, "locatorCode: " + locatorCode);
            }
        });


        this.textCantidad.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                //Log.d(TAG, "Key Code: " + event.getKeyCode());
                boolean action = false;
                action = true;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                        Double nuevaCantidad = Double.valueOf(textView.getText().toString());
                        if (nuevaCantidad > cantidadTransaccion) {
                            showWarning("Cantidad no puede ser superior a " + cantidadTransaccion);
                            textCantidad.setText(cantidadTransaccion.toString());
                        } else if (nuevaCantidad <= 0D) {
                            showWarning("Cantidad no puede ser 0 o menor");
                            textCantidad.setText(cantidadTransaccion.toString());
                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        iRestSubinventario.getAll().enqueue(new Callback<List<Subinventario>>() {
            @Override
            public void onResponse(Call<List<Subinventario>> call, Response<List<Subinventario>> response) {

                if(response.isSuccessful() == true && response.code()==200){

                    List<Subinventario> subinventarios = response.body();

                    if (subinventarios != null) {
                        if (subinventarios.size() > 0) {
                            String[] arrSubinventarios = new String[subinventarios.size()];
                            for (int i = 0; i < subinventarios.size(); i++) {
                                arrSubinventarios[i] = subinventarios.get(i).getCodSubinventario();
                            }
                            ArrayAdapter<String> adapterSubinventario = new ArrayAdapter<String>(ActivityEntregaAgregar.this, android.R.layout.select_dialog_item, arrSubinventarios);
                            textSubinventory.setAdapter(adapterSubinventario);
                            textSubinventory.setText(subinventoryCode);
                            if (subinventoryCode != null && !subinventoryCode.isEmpty()) {
                                mPresenter.getLocalizadoresBySubinventario(subinventoryCode);
                            }
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Subinventario>> call, Throwable t) {
                Log.d("CALLSUB1", "Error: " + t.getMessage());
            }
        });



        /*
        List<Subinventario> subinventarios = this.mPresenter.getSubinventarios();
        if (subinventarios != null) {
            if (subinventarios.size() > 0) {
                String[] arrSubinventarios = new String[subinventarios.size()];
                for (int i = 0; i < subinventarios.size(); i++) {
                    arrSubinventarios[i] = subinventarios.get(i).getCodSubinventario();
                }
                ArrayAdapter<String> adapterSubinventario = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrSubinventarios);
                this.textSubinventory.setAdapter(adapterSubinventario);
                this.textSubinventory.setText(this.subinventoryCode);
                if (this.subinventoryCode != null && !this.subinventoryCode.isEmpty()) {
                    mPresenter.getLocalizadoresBySubinventario(subinventoryCode);
                }
            }
        }
        */
        this.textCantidad.setText(this.cantidad.toString());
    }

    @Override
    protected void onStart() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityEntregaAgregar.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando Códigos Sigle...");
        progressDoalog.setTitle("Entregas");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        super.onStart();


        iRestRcvTransactions.getSegmentsByShipment(this.shipmentHeaderId).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()==true && response.code()==200){

                    List<String> segmentosShipment = response.body();

                    if (segmentosShipment.size() > 0) {
                        String[] segmentos = new String[segmentosShipment.size()];
                        for (int i = 0; i < segmentosShipment.size(); i++) {
                            segmentos[i] = segmentosShipment.get(i);
                        }
                        ArrayAdapter<String> adapterSegmentos = new ArrayAdapter<String>(ActivityEntregaAgregar.this, android.R.layout.select_dialog_item, segmentos);
                        textSigle.setAdapter(adapterSegmentos);
                        progressDoalog.dismiss();


                    }



                }



            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG, "Error llamando iRestRcvTransactions.getSegmentsByShipment : " + t.getMessage());
            }
        });

        /*
        List<String> segmentosShipment = mPresenter.getSegmentosByShipment(this.shipmentHeaderId);
        if (segmentosShipment.size() > 0) {
            String[] segmentos = new String[segmentosShipment.size()];
            for (int i = 0; i < segmentosShipment.size(); i++) {
                segmentos[i] = segmentosShipment.get(i);
            }
            ArrayAdapter<String> adapterSegmentos = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, segmentos);
            this.textSigle.setAdapter(adapterSegmentos);
        }
        */

    }

    @Override
    public void onBackPressed() {
        this.confirmacionSalir();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_entrega_agregar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                this.confirmacionSalir();
                return true;
            case R.id.limpiar:
                Log.d(TAG, "limpiar");
                this.cleanScreen();
                return true;
            case R.id.next:
                Log.d(TAG, "next");

                Log.d("CALLRCV2", "this.validaDatos(): " + this.validaDatos());
                Log.d("CALLRCV2", "isLote: " + isLote);
                Log.d("CALLRCV2", "isSerie: " + isSerie);
                Log.d("CALLRCV2", "ActivityEntregaAgregar " + this.transactionId);
                isLote = true;

                if (this.validaDatos()) {
                    if (this.isLote) {



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
                        iLote.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                        startActivity(iLote);
                        this.finish();
                    } else if (isSerie) {
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
                        iSerie.putStringArrayListExtra("series", (ArrayList<String>) this.series);
                        startActivity(iSerie);
                        this.finish();
                    } else {
                        Intent iResumen = new Intent(this, ActivityEntregaAgregarResumen.class);
                        iResumen.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
                        iResumen.putExtra("TransactionId", this.transactionId);
                        iResumen.putExtra("Cantidad", this.cantidad);
                        iResumen.putExtra("SubinventoryCode", this.subinventoryCode);
                        iResumen.putExtra("LocatorCode", this.locatorCode);
                        iResumen.putExtra("Lote", this.lote);
                        iResumen.putExtra("LoteProveedor", this.loteProveedor);
                        iResumen.putExtra("Vencimiento", this.vencimiento);
                        iResumen.putExtra("Categoria", this.categoria);
                        iResumen.putExtra("Atributo1", this.atributo1);
                        iResumen.putExtra("Atributo2", this.atributo2);
                        iResumen.putExtra("Atributo3", this.atributo3);
                        startActivity(iResumen);
                        this.finish();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SEARCHSINGLE_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                segment = data.getStringExtra("Segment1");
                inventoryItemId = data.getLongExtra("InventoryItemId", 0);
                this.textSigle.setText(segment);
                validaSigle();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    private void validaSigle() {




            iRestRcvTransactions.getAllByShipmentInventory(shipmentHeaderId,inventoryItemId).enqueue(new Callback<List<RcvTransactions>>() {
                @Override
                public void onResponse(Call<List<RcvTransactions>> call, Response<List<RcvTransactions>> response) {


                    if (response.isSuccessful() == true && response.code() == 200) {
                    List<RcvTransactions> transactions = response.body();
                    if (inventoryItemId != null) {


                      //  showSuccess("transactions.size()" + transactions.size());

                            if (transactions.size() == 0) {
                                textSigle.setText("");
                                showWarning("Sigle " + segment + " no encontrado en OC");
                                segment = null;
                                inventoryItemId = null;
                            } else if (transactions.size() > 1) {
                                items = new CharSequence[transactions.size()];
                                int index = 0;
                                for (RcvTransactions transaction : transactions) {
                                    Log.d(TAG, "items add " + transaction.getLineNum().toString());
                                    items[index] = transaction.getLineNum().toString();
                                    index++;
                                }
                                DialogSeleccionLinea dialogLinea = new DialogSeleccionLinea();
                                Bundle args = new Bundle();
                                args.putCharSequenceArray("items", items);
                                dialogLinea.setArguments(args);
                                dialogLinea.show(getSupportFragmentManager(), "DialogLinea");
                            }


                            else {
                                setSigle(transactions.get(0));
                            }


                    }


                    }

                }

                @Override
                public void onFailure(Call<List<RcvTransactions>> call, Throwable t) {
                    Log.d("CALLRCV3", "Error validaSigle: " + t.getMessage());

                }
            });


            /*
            List<RcvTransactions> transactions = mPresenter.getTransaccionsByShipmentInventory(shipmentHeaderId, inventoryItemId);
            if (transactions.size() == 0) {
                this.textSigle.setText("");
                showWarning("Sigle " + segment + " no encontrado en OC");
                this.segment = null;
                this.inventoryItemId = null;
            } else if (transactions.size() > 1) {
                items = new CharSequence[transactions.size()];
                int index = 0;
                for (RcvTransactions transaction : transactions) {
                    Log.d(TAG, "items add " + transaction.getLineNum().toString());
                    items[index] = transaction.getLineNum().toString();
                    index++;
                }
                DialogSeleccionLinea dialogLinea =new DialogSeleccionLinea();
                Bundle args = new Bundle();
                args.putCharSequenceArray("items", items);
                dialogLinea.setArguments(args);
                dialogLinea.show(getSupportFragmentManager(), "DialogLinea");
            }

            */






    }

    private void setSigle(RcvTransactions transaction) {
        this.transactionId = transaction.getTransactionId();

        iRestMtlSystemItems.getBySegment(segment).enqueue(new Callback<MtlSystemItems>() {
            @Override
            public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                if (response.isSuccessful() == true && response.code() == 200) {
                    MtlSystemItems item = response.body();

                    if (item != null) {
                        textProductoSigle.setText(segment);
                        textProductoDescription.setText(item.getDescription());
                        textProductoCantidad.setText(transaction.getQuantity().toString());
                        textProductoLinea.setText(transaction.getLineNum().toString());
                        if (item.getLotControlCode().equalsIgnoreCase("2")) {
                            isLote = true;
                            textProductoLote.setText("SI");
                        } else {
                            isLote = false;
                            textProductoLote.setText("NO");
                        }
                        if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                            isSerie = true;
                            textProductoSerie.setText("SI");
                        } else {
                            isSerie = false;
                            textProductoSerie.setText("NO");
                        }

                        rlayoutItem.setVisibility(View.VISIBLE);
                        rlayoutSigle.setVisibility(View.GONE);
                        layoutCantidad.setVisibility(View.VISIBLE);
                        layoutSubinventory.setVisibility(View.VISIBLE);
                        layoutLocator.setVisibility(View.VISIBLE);
                        textCantidad.setText(transaction.getQuantity().toString());
                        textSubinventory.requestFocus();
                        cantidadTransaccion = transaction.getQuantity();
                    }



                }
            }

            @Override
            public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                Log.d("CALLMTL4", "Error setSigle: " + t.getMessage());
            }
        });

        /*
        MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
        if (item != null) {
            this.textProductoSigle.setText(segment);
            this.textProductoDescription.setText(item.getDescription());
            this.textProductoCantidad.setText(transaction.getQuantity().toString());
            this.textProductoLinea.setText(transaction.getLineNum().toString());
            if (item.getLotControlCode().equalsIgnoreCase("2")) {
                this.isLote = true;
                this.textProductoLote.setText("SI");
            } else {
                this.isLote = false;
                this.textProductoLote.setText("NO");
            }
            if (item.getSerialNumberControlCode().equalsIgnoreCase("2") || item.getSerialNumberControlCode().equalsIgnoreCase("5")) {
                this.isSerie = true;
                this.textProductoSerie.setText("SI");
            } else {
                this.isSerie = false;
                this.textProductoSerie.setText("NO");
            }

            this.rlayoutItem.setVisibility(View.VISIBLE);
            this.rlayoutSigle.setVisibility(View.GONE);
            this.layoutCantidad.setVisibility(View.VISIBLE);
            this.layoutSubinventory.setVisibility(View.VISIBLE);
            this.layoutLocator.setVisibility(View.VISIBLE);
            this.textCantidad.setText(transaction.getQuantity().toString());
            this.textSubinventory.requestFocus();
            this.cantidadTransaccion = transaction.getQuantity();
        }
        */


    }

    public void fillLocator(List<Localizador> localizadores) {
        if (localizadores != null) {
            if (localizadores.size() > 0) {
                String[] locators = new String[localizadores.size()];
                for (int i = 0; i < localizadores.size(); i++) {
                    locators[i] = localizadores.get(i).getCodLocalizador();
                }
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, locators);
                this.textLocator.setAdapter(adapterLocator);
                this.textLocator.setText(this.locatorCode);
                this.textLocator.setEnabled(true);
                //this.hayLocalizador = true;
            } else {
                ArrayAdapter<String> adapterLocator = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, new String[0]);
                this.textLocator.setAdapter(adapterLocator);
                this.textLocator.setEnabled(false);
                this.locatorCode = "";
                this.textLocator.setText(this.locatorCode);
                //this.textLocator.setVisibility(View.GONE);
                //this.layoutLocator.setVisibility(View.GONE);
                //this.textSigle.setEnabled(false);
                //this.hayLocalizador = false;
                //this.loadSigle();
            }
        }
    }

    private void fillProducto(String descripcion, String segment, Double cantidad, Long linea, boolean isLote, boolean isSerie) {
        this.rlayoutSigle.setVisibility(View.GONE);
        this.rlayoutItem.setVisibility(View.VISIBLE);
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
        this.cantidadTransaccion = cantidad;
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("exit")) {
            Intent i = new Intent(this, ActivityEntregaDetalle.class);
            i.putExtra("ShipmentHeaderId", this.shipmentHeaderId);
            startActivity(i);
            this.finish();
        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    private boolean validaDatos() {

        // Valida Sigle
        if (this.transactionId == null) {
            return false;
        }

        // Valida Cantidad.
        if (this.textCantidad.getText() == null) {
            this.textCantidad.setError("ingrese la cantidad");
            return false;
        }
        if (this.textCantidad.getText().toString().isEmpty()) {
            this.textCantidad.setError("ingrese la cantidad");
            return false;
        }
        this.cantidad = Double.valueOf(this.textCantidad.getText().toString());

        // Valida Subinventario
        if (this.textSubinventory.getText() == null) {
            this.textSubinventory.setError("ingrese el subinventario");
            return false;
        }
        if (this.textSubinventory.getText().toString().isEmpty()) {
            this.textSubinventory.setError("ingrese el subinventario");
            return false;
        }
        this.subinventoryCode = this.textSubinventory.getText().toString();

        // Valida Localizador
        if (this.textLocator.getAdapter().getCount() > 0) {
            if (this.textLocator.getText() == null) {
                this.textLocator.setError("ingrese el localizador");
                return false;
            }
            if (this.textLocator.getText().toString().isEmpty()) {
                this.textLocator.setError("ingrese el localizador");
                return false;
            }
            this.locatorCode = this.textLocator.getText().toString();
        } else {
            this.locatorCode = "";
        }

        return true;
    }

    private void cleanScreen() {
        this.rlayoutSigle.setVisibility(View.VISIBLE);
        this.rlayoutItem.setVisibility(View.GONE);
        this.layoutCantidad.setVisibility(View.GONE);
        this.layoutSubinventory.setVisibility(View.GONE);
        this.layoutLocator.setVisibility(View.GONE);
        this.textSigle.setText("");

        // Reinicia Variables
        this.transactionId = null;
        this.segment = null;
        this.inventoryItemId = null;
        this.subinventoryCode = null;
        this.textSubinventory.setText("");
        this.locatorCode = null;
        this.textLocator.setText("");
        this.cantidad = null;
        this.textCantidad.setText("");

        this.lote = null;
        this.loteProveedor = null;
        this.vencimiento = null;
        this.categoria = null;
        this.atributo1 = null;
        this.atributo2 = null;
        this.atributo3 = null;

        this.series = new ArrayList<>();

    }

    @Override
    public void onDialogLineaPositiveClick(DialogFragment dialog) {
        Log.d(TAG, "salida " + ((DialogSeleccionLinea) dialog).selectedItem);
        String linea = ((DialogSeleccionLinea) dialog).selectedItem;


        iRestRcvTransactions.getAllByShipmentInventory(shipmentHeaderId,inventoryItemId).enqueue(new Callback<List<RcvTransactions>>() {
            @Override
            public void onResponse(Call<List<RcvTransactions>> call, Response<List<RcvTransactions>> response) {
                if (response.isSuccessful() == true && response.code() == 200) {

                    List<RcvTransactions> transactions = response.body();

                    for (RcvTransactions transaction : transactions) {
                        if (transaction.getLineNum().toString().equalsIgnoreCase(linea)) {
                            setSigle(transaction);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<RcvTransactions>> call, Throwable t) {

            }
        });

        /*
        List<RcvTransactions> transactions = mPresenter.getTransaccionsByShipmentInventory(shipmentHeaderId, inventoryItemId);


        for (RcvTransactions transaction : transactions) {
            if (transaction.getLineNum().toString().equalsIgnoreCase(linea)) {
                this.setSigle(transaction);
            }
        }
        */


    }
}
