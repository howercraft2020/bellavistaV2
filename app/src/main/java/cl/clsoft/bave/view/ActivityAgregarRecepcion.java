package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestDatosRecepcion;
import cl.clsoft.bave.apis.IRestHomologacion;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestOrganizacionPrincipal;
import cl.clsoft.bave.apis.IRestPoLinesAll;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.apis.RetrofitClient;
import cl.clsoft.bave.apis.RetrofitClientRx;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosCabeceraRecepcion;
import cl.clsoft.bave.model.DatosRecepcion;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.AgregarRecepcionPresenter;
import cl.clsoft.bave.repository.RcvHeaderInterfaceRepository;
import cl.clsoft.bave.service.impl.RecepcionOcService;
import cl.clsoft.bave.viewmodel.MtlSystemItemsViewModel;
import cl.clsoft.bave.viewmodel.PoLinesViewModel;
import cl.clsoft.bave.viewmodel.RcvTransactionsInterfaceViewModel;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAgregarRecepcion extends BaseActivity<AgregarRecepcionPresenter> implements ConfirmationDialog.ConfirmationDialogListener, DialogSeleccionLinea.DialogSeleccionLineaListener{

    //Variables
     String codigoSigle;
    private String udm;
     Double cantidad;
    String numeroOc;
    String poHeaderId;
    Long numeroRecep;
    String comentario;
     String segment;
     Long inventoryItemId;
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;
    CharSequence[] items;



    //API REST VARIABLE

    MtlSystemItemsViewModel mtlSystemItemsViewModel;
    PoLinesViewModel poLinesViewModel;
    private IRestHomologacion iRestHomologacion;

    IRestDatosRecepcion iRestDatosRecepcion;
    IRestPoLinesAll iRestPoLinesAll;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestRcvHeadersInterface iRestRcvHeadersInterface;
    IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    IRestRcvStatus iRestRcvStatus;
    IRestOrganizacionPrincipal iRestOrganizacionPrincipal;


    //Controls
    private TextInputLayout layoutCantidad;
    private TextInputEditText textCantidad;
    private TextInputLayout layoutSigle;
     AutoCompleteTextView textSigle;
    private TextInputLayout layoutUdm;
    private AutoCompleteTextView textUdm;
    private TextInputLayout layoutNumerolinea;
     AutoCompleteTextView textNumeroLinea;
    private ImageView iconSearch;
    Long polineid,itemid;
    private TextInputLayout layoutCodigoBarrasItem;
    private EditText textCodigobarrasRecepOc;
    private  TextInputLayout layoutDescripcion;
    private TextInputLayout textDescripcion;


    @NonNull
    @Override
    protected AgregarRecepcionPresenter createPresenter(@NonNull Context context) {
        return new AgregarRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_agregar_recepcion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_highlight_off_white_36dp);

        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSigle = findViewById(R.id.layoutSigle);
        this.textSigle = findViewById(R.id.textSigle);
        this.layoutCantidad = findViewById(R.id.layoutCantidad);
        this.textCantidad = findViewById(R.id.textCantidad);
        this.layoutUdm = findViewById(R.id.layoutUdm);
        this.textUdm = findViewById(R.id.textUdm);
        this.layoutNumerolinea = findViewById(R.id.layoutNumerolinea);
        this.textNumeroLinea = findViewById(R.id.textNumeroLinea);
        this.iconSearch = findViewById(R.id.iconSearch);
        this.layoutCodigoBarrasItem = findViewById(R.id.layoutCodigoBarrasRecepOc);
        this.textCodigobarrasRecepOc = findViewById(R.id.textCodigobarrasRecepOc);
        this.layoutDescripcion = findViewById(R.id.layoutDescripcion);




        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        poHeaderId = getIntent().getStringExtra("poHeaderId");
        comentario = getIntent().getStringExtra("comentario");


        iRestDatosRecepcion  = ApiUtils.getIRestDatosRecepcion();
        iRestPoLinesAll = ApiUtils.getIRestPoLinesAllRx();
        iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItemsRx();
        iRestRcvStatus = ApiUtils.getIRestRcvStatus();
        iRestHomologacion = ApiUtils.getIRestHomologacion();

        iRestOrganizacionPrincipal = ApiUtils.getIRestOrganizacionPrincipalRx();
        iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterface();
        iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();

        mtlSystemItemsViewModel = new ViewModelProvider(this).get(MtlSystemItemsViewModel.class);

        mtlSystemItemsViewModel.init();


        poLinesViewModel = new ViewModelProvider(this).get(PoLinesViewModel.class);





        this.textSigle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                segment = parent.getAdapter().getItem(position).toString();




                mtlSystemItemsViewModel.MtlgetBySegment(segment);

                mtlSystemItemsViewModel.getBySegment().observe(ActivityAgregarRecepcion.this, new Observer<MtlSystemItems>() {
                    @Override
                    public void onChanged(MtlSystemItems mtlSystemItems) {

                        if(mtlSystemItems!=null){
                            Log.d("Articulos Cant:", "Leyendo MtlgetBySegment");
                            layoutCantidad.setHintEnabled(true);
                            textCantidad.setEnabled(true);

                            layoutCantidad.setHint("Cantidad (" + mtlSystemItems.getPrimaryUomCode() + ")");
                            textUdm.setText(mtlSystemItems.getPrimaryUomCode());
                            inventoryItemId = mtlSystemItems.getInventoryItemId();

                            //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                            validaLinea();
                        }
                        else {
                           // showWarning("Item " + segment + " no se ha encontrado en la maestra.");
                        }

                    }
                });



            }
        });


        this.textSigle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + textView.getText());
                Log.d(TAG, "actionId: " + actionId);
                boolean action = false;
                action = true;

                if(actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                    if(textView.getText() != null && !textView.getText().toString().isEmpty())
                    {

                        segment = textView.getText().toString();

                        mtlSystemItemsViewModel.MtlgetBySegment(segment);

                        mtlSystemItemsViewModel.getBySegment().observe(ActivityAgregarRecepcion.this, new Observer<MtlSystemItems>() {
                            @Override
                            public void onChanged(MtlSystemItems mtlSystemItems) {

                                if(mtlSystemItems!=null){
                                    layoutCantidad.setHintEnabled(true);
                                    textCantidad.setEnabled(true);
                                    layoutCantidad.setHint("Cantidad (" + mtlSystemItems.getPrimaryUomCode() + ")");
                                    textUdm.setText(mtlSystemItems.getPrimaryUomCode());
                                    inventoryItemId = mtlSystemItems.getInventoryItemId();


                                    //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                                    validaLinea();

                                } else {
                                    showWarning("Item " + segment + " no se ha encontrado en la maestra.");
                                }

                            }
                        });


                    }
                    action = true;
                }
                return action;
            }
        });



        this.textCodigobarrasRecepOc.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String Codigo =  textCodigobarrasRecepOc.getText().toString();
                iRestHomologacion.getInventoryItemId(Codigo).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG,"response body Codigo :" );
                        textSigle.setText(response.body());
                        Boolean action = false;
                        if(textSigle.getText() != null && !textSigle.getText().toString().isEmpty())
                        {
                            segment = textSigle.getText().toString();
                            mtlSystemItemsViewModel.MtlgetBySegment(segment);
                            mtlSystemItemsViewModel.getBySegment().observe(ActivityAgregarRecepcion.this, new Observer<MtlSystemItems>() {
                                @Override
                                public void onChanged(MtlSystemItems mtlSystemItems) {
                                    if(mtlSystemItems!=null){

                                        layoutCantidad.setHintEnabled(true);
                                        textCantidad.setEnabled(true);
                                        layoutCantidad.setHint("Cantidad (" + mtlSystemItems.getPrimaryUomCode() + ")");
                                        textUdm.setText(mtlSystemItems.getPrimaryUomCode());
                                        inventoryItemId = mtlSystemItems.getInventoryItemId();

                                        //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                                          validaLinea();
                                    }else {
                                        // showWarning("Item " + segment + " no se ha encontrado en la maestra.");
                                    }
                                }
                            });
                        }
                        action = true;
                        textCodigobarrasRecepOc.setText("");
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG,"Error de Conexion :"+t);}
                });

                return false;
            }
        });






        this.iconSearch.setOnClickListener(v -> {
            Intent iSearch = new Intent(this, ActivitySigleSearch.class);
            iSearch.putExtra("Tipo", "R");
            iSearch.putExtra("PoHeaderId", this.poHeaderId);
            startActivityForResult(iSearch, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });

       this.mPresenter.getArticulosByOcReceipt(Long.valueOf(poHeaderId),numeroRecep);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LAUNCH_SEARCHSINGLE_ACTIVITY){
            if(resultCode == Activity.RESULT_OK) {
                segment = data.getStringExtra("Segment1");
                this.textSigle.setText(segment);
                Log.d(TAG, "sigle: " + segment);

                mtlSystemItemsViewModel.MtlgetBySegment(segment);

                mtlSystemItemsViewModel.getBySegment().observe(ActivityAgregarRecepcion.this, new Observer<MtlSystemItems>() {

                    @Override
                    public void onChanged(MtlSystemItems mtlSystemItems) {

                        if(mtlSystemItems!=null){
                            layoutCantidad.setHintEnabled(true);
                            textCantidad.setEnabled(true);
                            layoutCantidad.setHint("Cantidad (" + mtlSystemItems.getPrimaryUomCode() + ")");
                            textUdm.setText(mtlSystemItems.getPrimaryUomCode());
                            inventoryItemId = mtlSystemItems.getInventoryItemId();


                            //mPresenter.getLines(item.getInventoryItemId(),Long.parseLong(poHeaderId));
                            validaLinea();

                        }else {
                            showWarning("Item " + segment + " no encontrado en tabla maestra");
                        }

                    }
                });


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                this.textSigle.setText("");
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String codigoSigle = "";
        Double cantidad = 0.0;
        Long poLineNum = 0L;
        String udm;


        if(this.textSigle.getText().toString().trim().length() != 0) {
            codigoSigle = this.textSigle.getText().toString();
        }

        if(this.textCantidad.getText().toString().trim().length() != 0) {
            cantidad = Double.parseDouble(this.textCantidad.getText().toString());

        }

        if(this.textUdm.getText().toString().trim().length() != 0) {
            udm = this.textUdm.getText().toString();
        }

        if(this.textNumeroLinea.getText().toString().trim().length() != 0) {
            poLineNum = Long.parseLong(this.textNumeroLinea.getText().toString());

        }

        switch (item.getItemId()){
            case R.id.clean:
                this.cleanScreen();
                return true;
            case R.id.action_save:

                Log.d(TAG, "Número de Línea es: " + poLineNum);
                Log.d(TAG, "ItemId  es: " + itemid);


                if(codigoSigle.equals("")) {
                    showError("Debe Ingresar un Codigo Sigle");
                }
                else if(cantidad == 0){
                    showError("Debe Ingresar una cantidad mayor que 0");
                }
                else {


                    final ProgressDialog progressDoalog;
                    progressDoalog = new ProgressDialog(ActivityAgregarRecepcion.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setMessage("Recepcionando Artículo....");
                    progressDoalog.setTitle("Recepciones");
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    // show it
                    progressDoalog.show();


                    RcvHeadersInterface headersInterface = new RcvHeadersInterface();


                    Observable<DatosCabeceraRecepcion> datosCabeceraRecepcionObservable = iRestDatosRecepcion.get(Long.parseLong(poHeaderId), numeroRecep);
                    Observable<DatosRecepcion> datosRecepcionObservable = iRestDatosRecepcion.getDatosRecepcion(codigoSigle, numeroOc, numeroRecep, poLineNum);
                    Observable<PoLinesAll> poLinesAllObservable = iRestPoLinesAll.getLineaRx(itemid, Long.parseLong(poHeaderId), poLineNum);
                    Observable<MtlSystemItems> mtlSystemItemsObservable = iRestMtlSystemItems.getBySegmentRx(codigoSigle);
                    Observable<OrganizacionPrincipal> organizacionPrincipalObservable = iRestOrganizacionPrincipal.getAllRx();

                    Observable<List<Object>> result = Observable.zip(datosCabeceraRecepcionObservable,
                                                                     datosRecepcionObservable,
                                                                     mtlSystemItemsObservable,
                                                                     poLinesAllObservable,
                                                                     organizacionPrincipalObservable,
                                                                     new Function5<DatosCabeceraRecepcion, DatosRecepcion, MtlSystemItems, PoLinesAll,OrganizacionPrincipal, List<Object>>() {
                        @NonNull
                        @Override
                        public List<Object> apply(@NonNull DatosCabeceraRecepcion datosCabeceraRecepcion,
                                                  @NonNull DatosRecepcion datosRecepcion,
                                                  @NonNull MtlSystemItems mtlSystemItems,
                                                  @NonNull PoLinesAll poLinesAll,
                                                  @NonNull OrganizacionPrincipal organizacionPrincipal) throws Exception {
                            List<Object> r = new ArrayList<>();

                            r.add(datosCabeceraRecepcion);
                            r.add(datosRecepcion);
                            r.add(mtlSystemItems);
                            r.add(poLinesAll);
                            r.add(organizacionPrincipal);


                            return r;


                        }
                    });

                    Double finalCantidad = cantidad;
                    result.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<List<Object>>() {


                        Long inventoryItemId;
                        Long poLineId;
                        String udm;


                        Double quantity = 0.0;
                        Double quantityReceived = 0.0;
                        Double quantityCancelled = 0.0;
                        Double qtyRcvTolerance = 0.0;
                        Double qtyPending = 0.0;
                        Long headerInterfaceId = 0L;
                        Long groupId = 0L;
                        Long interfaceTransactionId = 0L;

                        String fecha = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


                        DatosCabeceraRecepcion datosCabeceraRecepcion;
                        DatosRecepcion datosRecepcion;
                        PoLinesAll linea;
                        MtlSystemItems sigle;
                        OrganizacionPrincipal org;

                        List<Object> r = new ArrayList<>();

                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull List<Object> objects) {

                            r = objects;
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                            showError(e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                            Log.d("JAVARX", "Tamaño:" + r.size());

                            datosCabeceraRecepcion = DatosCabeceraRecepcion.class.cast(r.get(0));
                            datosRecepcion = DatosRecepcion.class.cast(r.get(1));
                            sigle = MtlSystemItems.class.cast(r.get(2));
                            linea = PoLinesAll.class.cast(r.get(3));
                            org = OrganizacionPrincipal.class.cast(r.get(4));
                            Gson gson = new Gson();


                            Log.d("TRACK", "Tamaño:" + gson.toJson(datosCabeceraRecepcion));
                            //Cabecera
                            headerInterfaceId = Long.parseLong(poHeaderId + numeroRecep.toString());
                            groupId = Long.parseLong(numeroRecep.toString() + poHeaderId);

                            RcvHeadersInterface rcvHeadersInterface = new RcvHeadersInterface();
                            RcvTransactionsInterface rcvTransactionsInterface = new RcvTransactionsInterface();

                            if (datosRecepcion == null) {

                                showError("No existe información para articulo :");

                            }
                            if (sigle.getSegment1() == null) {
                                showError("No se encuentra información para el articulo : ");

                            }
                            if (sigle != null) {

                                inventoryItemId = sigle.getInventoryItemId();
                                udm = sigle.getPrimaryUomCode();

                            }
                            if (linea == null) {
                                showError("Linea ingresada para articulo" + " no es valida.");

                            }

                            interfaceTransactionId = datosRecepcion.getPoLineId();

                            rcvHeadersInterface = new RcvHeadersInterface();
                            rcvHeadersInterface.setHeaderInterfaceId(headerInterfaceId);
                            rcvHeadersInterface.setReciptSourceCode("VENDOR");
                            rcvHeadersInterface.setTransactionType("NEW");
                            rcvHeadersInterface.setAutoTransactCode("");
                            rcvHeadersInterface.setLastUpdateDate(fecha);
                            rcvHeadersInterface.setLastUpdateBy(datosCabeceraRecepcion.getUserId());
                            rcvHeadersInterface.setCreatedBy(datosCabeceraRecepcion.getUserId());
                            rcvHeadersInterface.setReciptNum(datosCabeceraRecepcion.getReceiptNum());
                            rcvHeadersInterface.setVendorId(datosCabeceraRecepcion.getVendorId());
                            rcvHeadersInterface.setVendorSiteCode(datosCabeceraRecepcion.getVendorSiteCode());
                            rcvHeadersInterface.setVendorSiteId(datosCabeceraRecepcion.getVendorSiteId());
                            rcvHeadersInterface.setShipToOrganizationCode(org.getCode());
                            rcvHeadersInterface.setShipToOrganizationCode("H04");
                            rcvHeadersInterface.setLocationId(356L);
                            rcvHeadersInterface.setReceiverId(datosCabeceraRecepcion.getUserId());
                            rcvHeadersInterface.setCurrencyCode(datosCabeceraRecepcion.getCurrencyCode());
                            rcvHeadersInterface.setConversionRateType(datosCabeceraRecepcion.getRateType());
                            rcvHeadersInterface.setConversionRate(datosCabeceraRecepcion.getRate());
                            rcvHeadersInterface.setConversionRateDate(datosCabeceraRecepcion.getRateDate());
                            rcvHeadersInterface.setPaymentTermsId(datosCabeceraRecepcion.getTermsId());

                            rcvHeadersInterface.setTransactionDate(fecha);

                            rcvHeadersInterface.setComments("");
                            rcvHeadersInterface.setOrgId(datosCabeceraRecepcion.getOrgId());
                            rcvHeadersInterface.setProcessingStatusCode("PENDING");
                            rcvHeadersInterface.setValidationFlag("Y");
                            rcvHeadersInterface.setGroupId(groupId);
                            Log.d("HEADER", "INSERTANDO HEADER");

                            iRestRcvHeadersInterface.insert(rcvHeadersInterface).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful() == true) {
                                        Log.d("JAVARX", "RcvHeadersInterface Insert/Update con éxito");




                                    }


                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });


                            //Valida Cantidad

                            quantity = datosRecepcion.getQuantity();
                            quantityReceived = datosRecepcion.getQuantityReceived();
                            quantityCancelled = datosRecepcion.getQuantityCancelled();
                            qtyRcvTolerance = datosRecepcion.getQtyRcvTolerance();
                            qtyPending = (quantity - quantityReceived - quantityCancelled) * (1 + qtyRcvTolerance / 100);


                            Log.d("VAL_CANT", "Mostrando Cantidad OC: "+datosRecepcion.getQuantity());
                            Log.d("VAL_CANT", "Mostrando Cantidad a Ingresar: "+finalCantidad);
                            Log.d("VAL_CANT", "Mostrando Cantidad a PENDIENTE: "+qtyPending);

                            if(finalCantidad > qtyPending){

                                showError("Cantidad no puede ser mayor a : "+qtyPending);
                                progressDoalog.dismiss();
                            }

                            if (finalCantidad <= qtyPending) {

                                rcvTransactionsInterface = new RcvTransactionsInterface();
                                rcvTransactionsInterface.setInterfaceTransactionId(interfaceTransactionId);
                                rcvTransactionsInterface.setLastUpdatedDate(fecha);
                                rcvTransactionsInterface.setLastUpdatedBy(datosRecepcion.getUserId());
                                rcvTransactionsInterface.setCreationDate(fecha);
                                rcvTransactionsInterface.setCreatedBy(datosRecepcion.getUserId());
                                rcvTransactionsInterface.setTransactionType("RECEIVE");
                                rcvTransactionsInterface.setTransactionDate(fecha);
                                rcvTransactionsInterface.setQuantity(finalCantidad);
                                rcvTransactionsInterface.setUnitOfMeasure(datosRecepcion.getUnitMeasLookupCode());
                                rcvTransactionsInterface.setItemId(datosRecepcion.getItemId());
                                rcvTransactionsInterface.setItemId(itemid);
                                rcvTransactionsInterface.setItemDescription(datosRecepcion.getItemDescription());
                                rcvTransactionsInterface.setUomCode(datosRecepcion.getPrimaryUomCode());
                                rcvTransactionsInterface.setShipToLocationId(356L);
                                rcvTransactionsInterface.setPrimaryQuantity(finalCantidad);
                                rcvTransactionsInterface.setReceiptSourceCode("VENDOR");
                                rcvTransactionsInterface.setVendorId(datosRecepcion.getVendorId());
                                rcvTransactionsInterface.setVendorSiteId(datosRecepcion.getVendorSiteId());
                                rcvTransactionsInterface.setToOrganizationId(org.getIdOrganizacion());
                                rcvTransactionsInterface.setPoHeaderId(datosRecepcion.getPoHeaderId());
                                rcvTransactionsInterface.setPoLineId(datosRecepcion.getPoLineId());
                                rcvTransactionsInterface.setPoLineId(poLineId);
                                rcvTransactionsInterface.setPoLineLocation(datosRecepcion.getLineLocationId());
                                rcvTransactionsInterface.setPoUnitPrice(datosRecepcion.getUnitPrice());
                                rcvTransactionsInterface.setCurrencyCode(datosRecepcion.getCurrencyCode());
                                rcvTransactionsInterface.setSourceDocumentCode("PO");
                                rcvTransactionsInterface.setCurrencyConversionType(datosRecepcion.getRateType());
                                rcvTransactionsInterface.setCurrencyConversionRate(datosRecepcion.getRate());
                                rcvTransactionsInterface.setCurrencyConversionDate(datosRecepcion.getRateDate());
                                rcvTransactionsInterface.setPoDistributionId(datosRecepcion.getPoDistributionId());
                                rcvTransactionsInterface.setDestinationTypeCode("RECEIVING");
                                rcvTransactionsInterface.setLocationId(356L);
                                rcvTransactionsInterface.setDeliverToLocationId(356L);
                                rcvTransactionsInterface.setInspectionStatusCode("NOT INSPECTED");
                                rcvTransactionsInterface.setHeaderInterfaceId(headerInterfaceId);
                                rcvTransactionsInterface.setVendorSiteCode(datosRecepcion.getVendorSiteCode());
                                rcvTransactionsInterface.setProcessingStatusCode("PENDING");
                                rcvTransactionsInterface.setProcessingModeCode("BATCH");
                                rcvTransactionsInterface.setComments("");
                                rcvTransactionsInterface.setUseMtlLot(0L);
                                rcvTransactionsInterface.setUseMtlSerial(0L);
                                rcvTransactionsInterface.setTransactionStatusCode("PENDING");
                                rcvTransactionsInterface.setValidationFlag("Y");
                                rcvTransactionsInterface.setOrgId(datosRecepcion.getOrgId());
                                rcvTransactionsInterface.setGroupId(groupId);
                                rcvTransactionsInterface.setAutoTransactCode("RECEIVE");
                                rcvTransactionsInterface.setSegment1(sigle.getSegment1());
                                //ircvTransactionsInterfaceDao.insert(rcvTransactionsInterface);
                                Log.d("TRX", "INSERTANDO TRANSACTIONS");


                                iRestRcvTransactionsInterface.insert(rcvTransactionsInterface).enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Log.d("JAVARX", "GUardado en TRX Interface");

                                        iRestRcvStatus.actualiza_estado(2, Long.parseLong(poHeaderId), numeroRecep).enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                if (response.isSuccessful() == true) {
                                                    Log.d("JAVARX", "RcvStatus Estado = 2,en proceso  con éxito");
                                                    progressDoalog.dismiss();
                                                    resultadoOkAddTransaction();

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Log.d("JAVARX", "Error:" + t.getMessage());
                                    }
                                });
                            }
                         //FIN ON COMPLETE
                        }



                    });

                }

                //mPresenter.cargaRecepcion(codigoSigle,Long.parseLong(poHeaderId),numeroOc,numeroRecep, cantidad, poLineNum,polineid,itemid);

                return true;
            case android.R.id.home:
                Log.d(TAG, "home");
                this.confirmacionSalir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private List<Object> opera(DatosCabeceraRecepcion datosCabeceraRecepcion,DatosRecepcion datosRecepcion,PoLinesAll poLinesAll,MtlSystemItems mtlSystemItems){

        List<Object> l = new ArrayList<Object>();

        l.add(datosCabeceraRecepcion);
        l.add(datosRecepcion);

        return l;
    }


    private void handleResults(Object o) {

        if(o.getClass() == DatosRecepcion.class){
            DatosRecepcion d = DatosRecepcion.class.cast(o);

            Log.d("DRECEP", "PO_HEADER_ID:" +d.getPoHeaderId());

        }

        if(o.getClass() == DatosCabeceraRecepcion.class){

            DatosCabeceraRecepcion c = DatosCabeceraRecepcion.class.cast(o);

            Log.d("DCABE", "USER_ID:" +c.getUserId());
        }

        else {

            Log.d("NO_TIPO", "SIN TIPO" +o.toString());
        }

    }

    public void handleError(Throwable t){

        Log.d("ERROR", "MERGE_ERRROR:" +t.getMessage());
    }

    public void fillSigle(List<MtlSystemItems> articulos) {
        if (articulos != null) {
            if (articulos.size() > 0){
                String[] articulosArray = new String[articulos.size()];
                for (int i = 0; i <articulos.size();i++){
                    articulosArray[i] = articulos.get(i).getSegment1();
                }
                ArrayAdapter<String> adapterArticulos = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, articulosArray);


                this.textSigle.setAdapter(adapterArticulos);
                adapterArticulos.notifyDataSetChanged();
            }
        }
    }

    public void fillLines(List<PoLinesAll> lineas) {
        if (lineas != null) {
            if (lineas.size() > 0){
                String[] lineasArray = new String[lineas.size()];
                for (int i = 0; i <lineas.size();i++){
                    lineasArray[i] = lineas.get(i).getLineNum();
                }
                ArrayAdapter<String> adapterArticulos = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, lineasArray);
                this.textNumeroLinea.setAdapter(adapterArticulos);
            }
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        Intent i = new Intent(this, ActivityArticulosRecepcion.class);
        i.putExtra("numeroOc", numeroOc);
        i.putExtra("NumeroRecep", numeroRecep);
        i.putExtra("poHeaderId", poHeaderId);
        i.putExtra("comentario", comentario);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    private void confirmacionSalir() {
        ConfirmationDialog dialogExit = ConfirmationDialog.newInstance("Perdera los datos ingresados. Quiere salir?", "Confirmación", "exit");
        dialogExit.show(getSupportFragmentManager(), "exitAgregarConfirm");
    }

    public void resultadoOkAddTransaction() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText("Creación exitosa")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Log.d("CMFA", "CLICK");
                        Intent i = new Intent(getApplicationContext(), ActivityArticulosRecepcion.class);
                        i.putExtra("numeroOc", numeroOc);
                        i.putExtra("NumeroRecep", numeroRecep);
                        i.putExtra("poHeaderId", poHeaderId);
                        i.putExtra("comentario", comentario);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

    private void validaLinea() {

        Log.d("LINENUM", "LLAMANDO VALIDALINEA");

        if (this.inventoryItemId != null) {

            poLinesViewModel.init();

            poLinesViewModel.PoLinesgetLines(inventoryItemId,Long.parseLong(poHeaderId));

            poLinesViewModel.getLines().observe(this, new Observer<List<PoLinesAll>>() {
                @Override
                public void onChanged(List<PoLinesAll> poLinesAlls) {

                    Log.d("LINENUM", "CANT LINEAS"+poLinesAlls.size());
                    if (poLinesAlls.size() == 0) {
                        textSigle.setText("");
                        showWarning("Sigle " + segment + " no encontrado en OC");
                        inventoryItemId = null;
                    } else if (poLinesAlls.size() > 1) {
                        items = new CharSequence[poLinesAlls.size()];
                        int index = 0;
                        for (PoLinesAll line : poLinesAlls) {
                            Log.d(TAG, "items add " + line.getLineNum().toString());
                            items[index] = line.getLineNum().toString();
                            itemid = line.getItemId();
                            index++;
                        }
                        DialogSeleccionLinea dialogLinea =new DialogSeleccionLinea();
                        Bundle args = new Bundle();
                        args.putCharSequenceArray("items", items);

                        dialogLinea.setArguments(args);
                        dialogLinea.show(getSupportFragmentManager(), "DialogLinea");
                    } else {

                        Log.d("LINENUM", "ASIGANDO NUMERO DE LINEA ");
                        textNumeroLinea.setText(poLinesAlls.get(0).getLineNum());
                        polineid = poLinesAlls.get(0).getPoLineId();
                        itemid = poLinesAlls.get(0).getItemId();
                        //INGRESANDO CANTIDAD
                        textCantidad.setText(poLinesAlls.get(0).getQuantity().toString());
                        Log.d("LINENUM", "PO_LINE_ID:"+polineid);

                        Log.d("LINENUM", "NUMERO ES"+poLinesAlls.get(0).getLineNum());
                    }
                }
            });

        }
    }

    @Override
    public void onDialogLineaPositiveClick(DialogFragment dialog) {
        Log.d(TAG, "salida " + ((DialogSeleccionLinea) dialog).selectedItem);
        String linea = ((DialogSeleccionLinea) dialog).selectedItem;
        this.textNumeroLinea.setText(linea);
    }

    public void cleanScreen() {
        this.textSigle.setText("");
        this.textNumeroLinea.setText("");
        this.textCantidad.setText("");
        this.textUdm.setText("");
        this.textSigle.requestFocus();
    }


}