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
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.presenter.CiclicosPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;

public class ActivityCiclicos extends BaseActivity<CiclicosPresenter> {

    // Variables
    private static final String TAG = "ActivityCiclicos";
    private List<MtlCycleCountHeaders> ciclicos;

    // Controls
    private RecyclerView recyclerViewCiclicos;
    private AdapterItemConteoCiclico adapter;

    @NonNull
    @Override
    protected CiclicosPresenter createPresenter(@NonNull Context context) {
        return new CiclicosPresenter(this, new ConteoCiclicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclicos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
        this.recyclerViewCiclicos = findViewById(R.id.recyclerViewCiclicos);

        // Set Controls
        this.recyclerViewCiclicos.setHasFixedSize(true);
        this.recyclerViewCiclicos.setLayoutManager(new LinearLayoutManager(this));

        this.adapter = new AdapterItemConteoCiclico(mPresenter.getConteosCiclicos());
        this.recyclerViewCiclicos.setAdapter(this.adapter);
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

}
