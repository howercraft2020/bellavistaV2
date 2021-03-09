package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaDetalle;
import cl.clsoft.bave.view.ActivityEntregaTransactionDetalle;

public class EntregaTransactionDetallePresenter extends BasePresenter {

    private static final String TAG = "EntregaDetalle";
    private ActivityEntregaTransactionDetalle mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;

    public EntregaTransactionDetallePresenter(@NonNull final ActivityEntregaTransactionDetalle view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

}
