package cl.clsoft.bave.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestConsulta;
import cl.clsoft.bave.apis.IRestHomologacion;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.presenter.ConsultaItemPresenter;
import cl.clsoft.bave.service.impl.ConsultaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityConsultaItem extends BaseActivity<ConsultaItemPresenter> {

    // Variables
    private String TAG = "ConsultaItem";
    private int LAUNCH_SEARCHSINGLE_ACTIVITY = 2;
    private String currentSegment = "";
    private String segment;
    private Long inventoryItemId;

    // Controls
    private TextInputLayout layoutSigle;
    private TextInputEditText textSigle;
    private ImageView iconSearch;
    private RecyclerView recyclerViewItems;
    private TextInputLayout layoutCodigoBarrasItem;
    private EditText textCodigobarrasItem;

    //API
    IRestConsulta iRestConsulta;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestHomologacion iRestHomologacion;

    @NonNull
    @Override
    protected ConsultaItemPresenter createPresenter(@NonNull Context context) {
        return new ConsultaItemPresenter(this, new AppTaskExecutor(this), new ConsultaServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.layoutSigle = findViewById(R.id.layoutSerie);
        this.textSigle = findViewById(R.id.textSigle);
        this.iconSearch = findViewById(R.id.iconSearch);
        this.recyclerViewItems = findViewById(R.id.recyclerViewItems);
        this.layoutCodigoBarrasItem = findViewById(R.id.layoutCodigoBarrasItem);
        this.textCodigobarrasItem = findViewById(R.id.textCodigobarrasItem);

        // API
        this.iRestConsulta= ApiUtils.getIRestConsulta();
        this.iRestMtlSystemItems  = ApiUtils.getIRestMtlSystemItems();
        this.iRestHomologacion = ApiUtils.getIRestHomologacion();

        // Set Controls


        this.textCodigobarrasItem.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String Homologacion = textCodigobarrasItem.getText().toString();
                iRestHomologacion.getInventoryItemId(Homologacion).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful() == true){
                            fillStock(Long.valueOf(response.body()));
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        showError("Error de Conexion"+t.getMessage());
                    }
                });
                return false;
            }
        });




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
                        if (!segment.equalsIgnoreCase(currentSegment)) {
                            currentSegment = segment;
                            MtlSystemItems item = mPresenter.getMtlSystemItemsBySegment(segment);
                            if (item != null) {
                                inventoryItemId = item.getInventoryItemId();
                                fillStock(inventoryItemId);
                            } else {
                                textSigle.setText("");
                                currentSegment = "";
                            }
                        }
                    }
                    action = true;
                }
                return action;
            }
        });

        this.iconSearch.setOnClickListener(v -> {
            Intent i = new Intent(this, ActivitySigleSearch.class);
            startActivityForResult(i, LAUNCH_SEARCHSINGLE_ACTIVITY);
        });
        this.recyclerViewItems.setHasFixedSize(true);
        this.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "home");
                this.finish();
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
                this.fillStock(inventoryItemId);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

   /* private void fillStock(Long inventoryItemId) {
        List<ConsultaItem> items = mPresenter.getAllByItem(inventoryItemId);
        AdapterItemConsultaSigle adapterItemConsultaSigle = new AdapterItemConsultaSigle(items);
        this.recyclerViewItems.setAdapter(adapterItemConsultaSigle);
        if (items.size() == 0) {
            showWarning("No se encontró Stock para " + segment);
        }
        this.textSigle.setText("");
    }*/


    private void fillStock(Long inventoryItemId) {
        System.out.println("FillStock: "+inventoryItemId);
        iRestMtlSystemItems.getBySegment(String.valueOf(inventoryItemId)).enqueue(new Callback<MtlSystemItems>() {
            @Override
            public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                if(response.isSuccessful() == true)
                {
                    System.out.println("FillStock : "+response.body().getInventoryItemId());
                    //IRest
                    iRestConsulta.getAllByItem(response.body().getInventoryItemId()).enqueue(new Callback<List<cl.clsoft.bave.model.ConsultaItem>>() {
                        @Override
                        public void onResponse(Call<List<cl.clsoft.bave.model.ConsultaItem>> call, Response<List<cl.clsoft.bave.model.ConsultaItem>> response)
                        {
                            List<ConsultaItem> items = response.body();
                            AdapterItemConsultaSigle adapterItemConsultaSigle = new AdapterItemConsultaSigle(items);
                            recyclerViewItems.setAdapter(adapterItemConsultaSigle);
                            if (items.size() == 0) {
                                showWarning("No se encontró Stock para " + segment);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<cl.clsoft.bave.model.ConsultaItem>> call, Throwable t) {
                        }
                    });
                }
                textCodigobarrasItem.setText("");
            }

            @Override
            public void onFailure(Call<MtlSystemItems> call, Throwable t) {

            }
        });
        //AdapterItemConsultaSigle adapterItemConsultaSigle = new AdapterItemConsultaSigle(items);
        //this.recyclerViewItems.setAdapter(adapterItemConsultaSigle);
        this.textSigle.setText("");
    }

}
