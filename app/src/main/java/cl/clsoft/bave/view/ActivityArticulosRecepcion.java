package cl.clsoft.bave.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoDistributionsAll;
import cl.clsoft.bave.apis.IRestPoLineLocationsAll;
import cl.clsoft.bave.apis.IRestPoLinesAll;
import cl.clsoft.bave.apis.IRestPoheadersAll;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.presenter.ArticulosRecepcionPresenter;
import cl.clsoft.bave.dao.rowmapper.service.impl.RecepcionOcService;
import cl.clsoft.bave.viewmodel.RcvTransactionsInterfaceViewModel;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityArticulosRecepcion extends BaseActivity<ArticulosRecepcionPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private static final String TAG = "ActivityArticulosRecep";
    private List<RcvTransactionsInterface> articulos;
    private TextView segment1;
    private TextView receiptNum;
    private TextView poHeaderId;
    private TextInputLayout layoutComentario;
    private AutoCompleteTextView textComentario;
    private Long interfaceHeaderId;
    private Long groupId;
    String numeroOc;
    Long numeroRecep;
    String id;
    String comentario;

    //REST API Variable
    private RcvTransactionsInterfaceViewModel rcvTransactionsInterfaceViewModel;


    private IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    private IRestRcvHeadersInterface iRestRcvHeadersInterface;
    private IRestPoLinesAll iRestPoLinesAll;
    private IRestPoheadersAll iRestPoheadersAll;
    private IRestPoDistributionsAll iRestPoDistributionsAll;
    private IRestPoLineLocationsAll iRestPoLineLocationsAll;
    private IRestRcvStatus iRestRcvStatus;

    //Controls
    RecyclerView recyclerViewArticulosRecepcion;
    AdapterItemArticulosRecepcion adapter;
    private SweetAlertDialog dialog;

    @NonNull
    @Override
    protected ArticulosRecepcionPresenter createPresenter(@NonNull Context context) {
        return new ArticulosRecepcionPresenter(this, new RecepcionOcService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_articulos_recepcion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String comentarioCap = textComentario.getText().toString();

        switch (item.getItemId()){
            case R.id.action_more:
                //Log.d(TAG, "Agregar Recepcion");
                Intent i = new Intent(this, ActivityAgregarRecepcion.class);
                i.putExtra("numeroOc", numeroOc);
                i.putExtra("NumeroRecep", numeroRecep);
                i.putExtra("poHeaderId", id);
                i.putExtra("comentario", comentarioCap);

                startActivity(i);
                this.finish();
                return true;
            case R.id.action_create_file:
                Log.d(TAG, "cerrar");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar la recepción?", "Confirmación", "close");
                dialogClose.show(getSupportFragmentManager(), "closeEntregaConfirm");
                return true;
            case android.R.id.home:
                Intent imain = new Intent(this, ActivityRecepcionOc.class);
                startActivity(imain);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_recepcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.segment1 = (TextView) findViewById(R.id.segment1);
        this.receiptNum = (TextView) findViewById(R.id.receiptNum);
        this.poHeaderId = (TextView) findViewById(R.id.poHeaderId);
        this.layoutComentario = findViewById(R.id.layoutComentario);
        this.textComentario = findViewById(R.id.textComentario);

        numeroOc = getIntent().getStringExtra("numeroOc");
        numeroRecep = getIntent().getLongExtra("NumeroRecep",0);
        id = getIntent().getStringExtra("poHeaderId");
        comentario = getIntent().getStringExtra("comentario");

        interfaceHeaderId = Long.parseLong(id+numeroRecep);

        segment1.setText(numeroOc);
        receiptNum.setText(numeroRecep.toString());
        poHeaderId.setText(id);
        textComentario.setText(comentario);

        //Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewArticulosRecepcion = findViewById(R.id.recyclerViewArticulosRecepcion);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        // Set Controls
        this.recyclerViewArticulosRecepcion.setHasFixedSize(true);
        this.recyclerViewArticulosRecepcion.setLayoutManager(new LinearLayoutManager(this));


        //Llamada SQLITE
        /*
        this.articulos = mPresenter.getAllArticulos(interfaceHeaderId);
        this.adapter = new AdapterItemArticulosRecepcion(getApplicationContext(),articulos);
        this.recyclerViewArticulosRecepcion.setAdapter(this.adapter);
        */

        //Inicialización de REST API
        iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();
        iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterface();
        iRestPoLinesAll = ApiUtils.getIRestPoLinesAll();
        iRestPoheadersAll = ApiUtils.getIRestPoheadersAll();
        iRestPoDistributionsAll = ApiUtils.getIRestPoDistributionsAlll();
        iRestPoLineLocationsAll = ApiUtils.getIRestPoLineLocationsAll();
        iRestRcvStatus = ApiUtils.getIRestRcvStatus();

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityArticulosRecepcion.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando artículos....");
        progressDoalog.setTitle("Recepciones");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();

        //Llamada API REST VIEW MODEL

        articulos = new ArrayList<RcvTransactionsInterface>();



        rcvTransactionsInterfaceViewModel = new ViewModelProvider(this).get(RcvTransactionsInterfaceViewModel.class);
        rcvTransactionsInterfaceViewModel.init();
        rcvTransactionsInterfaceViewModel.RcvgetAllByHeader(interfaceHeaderId);


        rcvTransactionsInterfaceViewModel.getAllByHeader().observe(this, new Observer<List<RcvTransactionsInterface>>() {
            @Override
            public void onChanged(List<RcvTransactionsInterface> rcvTransactionsInterfaces) {


                progressDoalog.dismiss();
                adapter = new AdapterItemArticulosRecepcion(getApplicationContext(),rcvTransactionsInterfaces);
                recyclerViewArticulosRecepcion.setAdapter(adapter);
                articulos = rcvTransactionsInterfaces;

            }
        });


        //Log.d("Articulos Cant:", ""+adapter.getItemCount());

        this.recyclerViewArticulosRecepcion.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

                String comentarioCap = textComentario.getText().toString();

                try{
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityRecepcionArticuloDetalle.class);
                        i.putExtra("interfaceTransactionId", articulos.get(position).getInterfaceTransactionId());
                        i.putExtra("cantidad", articulos.get(position).getQuantity());
                        i.putExtra("lineLocationId", articulos.get(position).getPoLineLocation());
                        i.putExtra("numeroOc", numeroOc);
                        i.putExtra("NumeroRecep", numeroRecep);
                        i.putExtra("id", id);
                        i.putExtra("comentario", comentarioCap);
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
    public void onDialogAceptarClick(DialogFragment dialog) {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ActivityArticulosRecepcion.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cerrando Recepción....");
        progressDoalog.setTitle("Recepciones");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();


        String tipo = dialog.getArguments().getString("tipo");
        String comentario = textComentario.getText().toString();
        String groupId = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());

        if (tipo.equalsIgnoreCase("close")) {



            iRestPoheadersAll.delete(Long.parseLong(id)).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){

                        Log.d("JAVARX", "Borrando PoheadersAll PoHeaderID: "+id);



                        iRestPoLinesAll.deleteByPoHeaderId(Long.parseLong(id)).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                                if(response.isSuccessful()){

                                    Log.d("JAVARX", "Borrando PoLinesAll PoHeaderID: "+id);



                                    iRestPoLineLocationsAll.delete(Long.parseLong(id)).enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if(response.isSuccessful()){

                                                Log.d("JAVARX", "Borrando PoLineLocationsAll PoHeaderID: "+id);


                                                iRestPoDistributionsAll.delete(Long.parseLong(id)).enqueue(new Callback<Void>() {
                                                    @Override
                                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                                        if(response.isSuccessful()){

                                                            Log.d("JAVARX", "Borrando PoDistributionsAll PoHeaderID: "+id);


                                                            iRestRcvStatus.actualiza_estado(3,Long.parseLong(id),numeroRecep).enqueue(new Callback<Void>() {
                                                                @Override
                                                                public void onResponse(Call<Void> call, Response<Void> response) {

                                                                    if(response.isSuccessful()){

                                                                        progressDoalog.dismiss();
                                                                        Log.d("JAVARX", "RcvStatus Estado = 3, cerrado  con éxito");

                                                                        resultadoOkCerrarRecepcion();
                                                                    }


                                                                }

                                                                @Override
                                                                public void onFailure(Call<Void> call, Throwable t) {
                                                                    showError("Error Actualizando RCV_STATUS 3"+t.getMessage());
                                                                }
                                                            });


                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<Void> call, Throwable t) {
                                                        showError("Error Borrando PO_DISTRIBUTIONS_ALL"+t.getMessage());
                                                    }
                                                });




                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                            showError("Error Borrando PO_LINE_LOCATIONS"+t.getMessage());
                                        }
                                    });


                                }

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                showError("Error Borrando PO_LINES"+t.getMessage());
                            }
                        });




                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    showError("Error Borrando PO_HEADERS_ALL"+t.getMessage());
                }
            });

           // mPresenter.crearArchivo(interfaceHeaderId,numeroOc,numeroRecep,Long.parseLong(id), comentario, Long.parseLong(groupId));


        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void resultadoOkCerrarRecepcion() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Éxito")
                .setContentText("Cierre exitoso")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i = new Intent(getApplicationContext(), ActivityRecepcionOc.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

}