package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
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

    public TransactionDetalleDto getTransactionsInterfaceById(Long interfaceTransactionId) {
        try {
            return this.mService.getTransactionsInterfaceById(interfaceTransactionId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public RcvTransactions getTransactionById(Long transactionId) {
        try {
            return this.mService.getTransactionById(transactionId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) {
        try {
            return this.mService.getMtlSystemItemsById(inventoryItemId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) {
        try {
            this.mService.deleteTransactionsInterfaceById(interfaceTransactionId);
            mView.resultadoOkDeleteTransaction();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
    }

}
