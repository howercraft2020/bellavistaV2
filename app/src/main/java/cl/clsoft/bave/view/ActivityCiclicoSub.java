package cl.clsoft.bave.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlCycleCountEntries;
import cl.clsoft.bave.apis.IRestMtlCycleCountHeaders;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.apis.IRestOrganizacionPrincipal;
import cl.clsoft.bave.apis.IRestSubinventario;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.presenter.CiclicoSubPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCiclicoSub extends BaseActivity<CiclicoSubPresenter> implements ConfirmationDialog.ConfirmationDialogListener {

    // Variables
    private String TAG = "ActivityAgregarFisicoInventario";
    private Long inventarioCiclicoId;
    private List<Subinventario> subinventories;

    // Controls
    private TextView textCycleCountHeaderId;
    private TextView textCycleCountHeaderName;
    private TextView textCreationDate;
    private RecyclerView recyclerViewCiclicoSub;
    private AdapterConteoCiclicoSub adapter;


    //REST API
    private IRestMtlCycleCountHeaders iRestMtlCycleCountHeaders;
    private IRestSubinventario iRestSubinventario;
    private IRestOrganizacionPrincipal iRestOrganizacionPrincipal;
    private IRestMtlCycleCountEntries iRestMtlCycleCountEntries;
    private IRestMtlSystemItems iRestMtlSystemItems;

    @NonNull
    @Override
    protected CiclicoSubPresenter createPresenter(@NonNull Context context) {
        return new CiclicoSubPresenter(this, new AppTaskExecutor(this), new ConteoCiclicoService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclico_sub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bind controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.textCycleCountHeaderId = findViewById(R.id.textCycleCountHeaderId);
        this.textCycleCountHeaderName = findViewById(R.id.textCycleCountHeaderName);
        this.textCreationDate = findViewById(R.id.textCreationDate);
        this.recyclerViewCiclicoSub = findViewById(R.id.recyclerViewCiclicoSub);

        //Set controls
        this.inventarioCiclicoId = this.getIntent().getLongExtra("ciclicosId",0);


        /*
        MtlCycleCountHeaders mtlCycleCountHeaders = this.mPresenter.getMtlCycleCountHeaders(this.inventarioCiclicoId);
        if (mtlCycleCountHeaders != null) {

            this.textCycleCountHeaderId.setText(mtlCycleCountHeaders.getCycleCountHeaderId().toString());
            this.textCycleCountHeaderName.setText(mtlCycleCountHeaders.getCycleCountHeaderName());
            this.textCreationDate.setText(mtlCycleCountHeaders.getCreationDate());

            this.recyclerViewCiclicoSub.setHasFixedSize(true);
            this.recyclerViewCiclicoSub.setLayoutManager(new LinearLayoutManager(this));
            this.mPresenter.loadSubinventories(mtlCycleCountHeaders.getCycleCountHeaderId());
        }
        */

        //
        iRestMtlCycleCountHeaders = ApiUtils.getIRestMtlCycleCountHeaders();
        iRestSubinventario = ApiUtils.getIRestSubinventario();
        iRestOrganizacionPrincipal = ApiUtils.getIRestOrganizacionPrincipal();
        iRestMtlCycleCountEntries = ApiUtils.getIRestMtlCycleCountEntries();
        iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();


        iRestMtlCycleCountHeaders.get(this.inventarioCiclicoId).enqueue(new Callback<MtlCycleCountHeaders>() {
            @Override
            public void onResponse(Call<MtlCycleCountHeaders> call, Response<MtlCycleCountHeaders> response) {

                if(response.isSuccessful()==true && response.code()==200 && response.body()!=null){

                    MtlCycleCountHeaders mtlCycleCountHeaders = response.body();

                    textCycleCountHeaderId.setText(mtlCycleCountHeaders.getCycleCountHeaderId().toString());
                    textCycleCountHeaderName.setText(mtlCycleCountHeaders.getCycleCountHeaderName());
                    textCreationDate.setText(mtlCycleCountHeaders.getCreationDate());

                    recyclerViewCiclicoSub.setHasFixedSize(true);
                    recyclerViewCiclicoSub.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    //mPresenter.loadSubinventories(mtlCycleCountHeaders.getCycleCountHeaderId());

                    iRestSubinventario.getAllByCiclico(mtlCycleCountHeaders.getCycleCountHeaderId()).enqueue(new Callback<List<Subinventario>>() {
                        @Override
                        public void onResponse(Call<List<Subinventario>> call, Response<List<Subinventario>> response) {

                            if(response.isSuccessful()==true && response.code()==200 && response.body().toString()!="[]"){

                                Log.d(TAG, "LocalizadoresBySubinventario::onPostExecute");
                                hideProgres();
                                fillSubinventarios(response.body());


                            }


                        }

                        @Override
                        public void onFailure(Call<List<Subinventario>> call, Throwable t) {

                        }
                    });



                }



            }

            @Override
            public void onFailure(Call<MtlCycleCountHeaders> call, Throwable t) {

            }
        });



        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.recyclerViewCiclicoSub.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = recyclerView.getChildAdapterPosition(child);
                        Intent i = new Intent(getApplicationContext(), ActivityCiclicoDetalle.class);
                        i.putExtra("ciclicosId", inventarioCiclicoId);
                        i.putExtra("subinventarioId", subinventories.get(position).getCodSubinventario());
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
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ciclico_subinventario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent o = new Intent(this, ActivityCiclicos.class);
                startActivity(o);
                this.finish();
                return true;
            case R.id.cerrar:
                Log.d(TAG, "Cerrar ciclico");
                ConfirmationDialog dialogClose = ConfirmationDialog.newInstance("Esta seguro de cerrar el conteo?", "Confirmación", "close");
                dialogClose.show(getSupportFragmentManager(), "closeConteoConfirm");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogAceptarClick(DialogFragment dialog) {
        String tipo = dialog.getArguments().getString("tipo");
        if (tipo.equalsIgnoreCase("close")) {
            //Cierre y cración de archivo conteo cíclico
            Log.d(TAG, "ConteoCiclicoService::closeConteoCiclico");
            Log.d(TAG, "ConteoCiclicoService::closeConteoCiclico::cycleCountHeaderId: " + this.inventarioCiclicoId);

            String salida = "";
            //mPresenter.closeConteoCiclico(this.inventarioCiclicoId);

            iRestOrganizacionPrincipal.getAll().enqueue(new Callback<OrganizacionPrincipal>() {
                @Override
                public void onResponse(Call<OrganizacionPrincipal> call, Response<OrganizacionPrincipal> response) {

                    if(response.isSuccessful()==true && response.code()==200 && response.body()!=null){

                        OrganizacionPrincipal organizacionPrincipal = response.body();


                        iRestMtlCycleCountHeaders.get(inventarioCiclicoId).enqueue(new Callback<MtlCycleCountHeaders>() {
                            @Override
                            public void onResponse(Call<MtlCycleCountHeaders> call, Response<MtlCycleCountHeaders> response) {


                                if(response.isSuccessful()==true && response.code()==200 && response.body()!=null){

                                    MtlCycleCountHeaders header = response.body();

                                    iRestMtlCycleCountEntries.getAllInventariadosByHeader(inventarioCiclicoId).enqueue(new Callback<List<MtlCycleCountEntries>>() {
                                        @Override
                                        public void onResponse(Call<List<MtlCycleCountEntries>> call, Response<List<MtlCycleCountEntries>> response) {



                                            if(response.isSuccessful()==true && response.code()==200 && response.body()!=null){

                                                List<MtlCycleCountEntries> entries = response.body();


                                                // Genera archivo Conteo
                                                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                                                String strLastUpdate = dateFormat.format(new Date());
                                                String nombreArchivo = "I_C_" + inventarioCiclicoId + ".txt";

                                                File tarjetaSD = Environment.getExternalStorageDirectory();
                                                File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
                                                File archivo = new File(Dir, nombreArchivo);
                                                try {
                                                    FileWriter writer = new FileWriter(archivo);




                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                for (MtlCycleCountEntries entry : entries) {





                                                }


                                            }


                                            if(response.isSuccessful()==true && response.code()==200 && response.body()==null){

                                                showError( "Conteo Ciclico " + inventarioCiclicoId + ": no se encontraron entries inventariados.");

                                            }




                                        }

                                        @Override
                                        public void onFailure(Call<List<MtlCycleCountEntries>> call, Throwable t) {

                                        }
                                    });



                                }
                                if(response.isSuccessful()==true && response.code()==200 && response.body()==null){

                                    showError("Conteo Ciclico " + inventarioCiclicoId + " no existe en el sistema");

                                }


                            }

                            @Override
                            public void onFailure(Call<MtlCycleCountHeaders> call, Throwable t) {

                            }
                        });


                    }
                    if(response.isSuccessful()==true && response.code()==200 && response.body()==null){

                        showError("Organizacion Principal no existe");

                    }

                }

                @Override
                public void onFailure(Call<OrganizacionPrincipal> call, Throwable t) {

                }
            });






        }
    }

    @Override
    public void onDialogCancelarClick(DialogFragment dialog) {

    }

    public void fillSubinventarios(List<Subinventario> subinventories) {
        if (subinventories != null) {
            this.subinventories = subinventories;
            this.adapter = new AdapterConteoCiclicoSub(this.subinventories);
            this.recyclerViewCiclicoSub.setAdapter(this.adapter);
        }
    }

    public void mensajeOkCloseInventory() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText("Conteo Ciclico cerrado con éxito")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i = new Intent(getApplicationContext(), ActivityCiclicos.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

    public void mensajeErrorCloseInventory() {
        this.showError("No se pudo cerrar el Conteo Ciclico");
    }

}
