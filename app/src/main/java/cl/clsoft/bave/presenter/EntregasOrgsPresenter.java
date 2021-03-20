package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregas;
import cl.clsoft.bave.view.ActivityEntregasOrgs;

public class EntregasOrgsPresenter extends BasePresenter {

    private static final String TAG = "EntregasOrgsPresenter";
    private ActivityEntregasOrgs mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;

    public EntregasOrgsPresenter (@NonNull final ActivityEntregasOrgs view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

}
