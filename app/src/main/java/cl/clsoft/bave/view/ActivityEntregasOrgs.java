package cl.clsoft.bave.view;

import android.content.Context;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BaseActivity;
import cl.clsoft.bave.presenter.EntregasOrgsPresenter;
import cl.clsoft.bave.service.impl.EntregaServiceImpl;
import cl.clsoft.bave.task.AppTaskExecutor;

public class ActivityEntregasOrgs extends BaseActivity<EntregasOrgsPresenter> {

    @NonNull
    @Override
    protected EntregasOrgsPresenter createPresenter(@NonNull Context context) {
        return new EntregasOrgsPresenter(this, new AppTaskExecutor(this), new EntregaServiceImpl());
    }

}
