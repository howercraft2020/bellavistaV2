package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IEntregaOrgsService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaOrgsAgregarSerie;
import cl.clsoft.bave.view.ActivityEntregaOrgsTransactionDetalle;

public class EntregaOrgsTransactionDetallePresenter extends BasePresenter {

    private static final String TAG = "PRESENTER";
    private ActivityEntregaOrgsTransactionDetalle mView;
    private IEntregaOrgsService mService;
    private TaskExecutor mTaskExecutor;

    public EntregaOrgsTransactionDetallePresenter(@NonNull final ActivityEntregaOrgsTransactionDetalle view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaOrgsService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public TransactionDetalleDto getTransactionsInterfaceById(Long transactionId) {
        try {
            return this.mService.getTransactionDetalleById(transactionId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public MtlMaterialTransactions getTransactionById(Long transactionId) {
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

    public void deleteTransactionsById(Long transactionId) {
        try {
            this.mService.deleteTransactionsById(transactionId);
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
