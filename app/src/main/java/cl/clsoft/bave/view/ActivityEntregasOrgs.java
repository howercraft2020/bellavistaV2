package cl.clsoft.bave.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.presenter.EntregasOrgsPresenter;
import cl.clsoft.bave.service.impl.EntregaOrgsServiceImpl;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregasOrgs extends BaseActivity<EntregasOrgsPresenter> {

    // Variables
    private String TAG = "ActivityEntregasOrgs";
    private List<EntregaOrgsHeader> headers;

    // Controls
    private RecyclerView recyclerViewEntregas;
    private AdapterItemEntregaOrgs adapter;

    @NonNull
    @Override
    protected EntregasOrgsPresenter createPresenter(@NonNull Context context) {
        return new EntregasOrgsPresenter(this, new AppTaskExecutor(this), new EntregaOrgsServiceImpl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas_orgs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewEntregas = findViewById(R.id.recyclerViewEntregas);

        //Set Controls
        this.recyclerViewEntregas.setHasFixedSize(true);
        this.recyclerViewEntregas.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mPresenter.getEntregas();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillEntregas(List<EntregaOrgsHeader> headers) {
        if (headers != null) {
            this.headers = headers;
            this.adapter = new AdapterItemEntregaOrgs(headers);
            this.recyclerViewEntregas.setAdapter(this.adapter);
        }
    }

}
