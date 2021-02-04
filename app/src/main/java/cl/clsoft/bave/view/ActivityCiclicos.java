package cl.clsoft.bave.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.CiclicosPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;

public class ActivityCiclicos extends BaseActivity<CiclicosPresenter> {

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

    }

}
