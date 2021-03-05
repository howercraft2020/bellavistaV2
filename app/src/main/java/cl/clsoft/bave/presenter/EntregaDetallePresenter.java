package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaDetalle;

public class EntregaDetallePresenter extends BasePresenter {

    private static final String TAG = "EntregaDetalle";
    private ActivityEntregaDetalle mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;

    public EntregaDetallePresenter(@NonNull final ActivityEntregaDetalle view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public RcvShipmentHeaders getEntrega(Long shipmentHeaderId) {
        try {
            return this.mService.getEntrega(shipmentHeaderId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

}
