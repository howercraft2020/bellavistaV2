package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IConteoCiclicoService;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityCiclicoSub;
import cl.clsoft.bave.view.ActivityFisicoAgregar;

public class CiclicoSubPresenter extends BasePresenter {

    private static final String TAG = "ConteoCiclico";
    private ActivityCiclicoSub mView;
    private IConteoCiclicoService mService;
    private TaskExecutor mTaskExecutor;

    public CiclicoSubPresenter(@NonNull final ActivityCiclicoSub view, @NonNull final TaskExecutor taskExecutor, @NonNull final IConteoCiclicoService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

}
