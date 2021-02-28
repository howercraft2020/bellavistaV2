package cl.clsoft.bave.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import cl.clsoft.bave.R;
import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.CiclicoSubPresenter;
import cl.clsoft.bave.service.impl.ConteoCiclicoService;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityCiclicoSub extends BaseActivity<CiclicoSubPresenter> {

    // Variables
    private String TAG = "ActivityAgregarFisicoInventario";

    // Controls

    @NonNull
    @Override
    protected CiclicoSubPresenter createPresenter(@NonNull Context context) {
        return new CiclicoSubPresenter(this, new AppTaskExecutor(this), new ConteoCiclicoService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisico_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
