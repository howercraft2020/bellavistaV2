package cl.clsoft.bave.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.FisicosPresenter;
import cl.clsoft.bave.service.impl.InventarioFisicoService;

public class ActivityFisicos extends BaseActivity<FisicosPresenter> {

    @NonNull
    @Override
    protected FisicosPresenter createPresenter(@NonNull Context context) {
        return new FisicosPresenter(this, new InventarioFisicoService());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Instance Layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisicos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind Controls
        this.llProgressBar = findViewById(R.id.llProgressBar);
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
