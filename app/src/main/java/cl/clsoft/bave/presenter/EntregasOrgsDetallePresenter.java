package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.TransactionsDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.service.IEntregaOrgsService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregasOrgs;
import cl.clsoft.bave.view.ActivityEntregasOrgsDetalle;

public class EntregasOrgsDetallePresenter extends BasePresenter {

    private static final String TAG = "PRESENTER";
    private ActivityEntregasOrgsDetalle mView;
    private IEntregaOrgsService mService;
    private TaskExecutor mTaskExecutor;

    public EntregasOrgsDetallePresenter (@NonNull final ActivityEntregasOrgsDetalle view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaOrgsService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public EntregaOrgsHeader getEntrega(Long shipmentHeaderId) {
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


    public List<TransactionsDto> getTransactions(Long shipmentHeaderId) {
        try {
            return this.mService.getTransactionsInterfaceByShipmentHeader(shipmentHeaderId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void closeEntrega(Long shipmentHeaderId){
    }

}
