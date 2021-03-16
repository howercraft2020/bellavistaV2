package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.MtlTransactionDetalleDto;
import cl.clsoft.bave.dto.TransactionDetalleDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityEntregaTransactionDetalle;
import cl.clsoft.bave.view.ActivityTransSubinvTransactionDetalle;

public class TransSubinvTransactionDetallePresenter extends BasePresenter {

    private static final String TAG = "TransSubinvDetalle";
    private ActivityTransSubinvTransactionDetalle mview;
    private ITransSubinvService mService;

    public TransSubinvTransactionDetallePresenter(@NonNull final ActivityTransSubinvTransactionDetalle mview, @NonNull ITransSubinvService mService) {
        this.mview = mview;
        this.mService = mService;
    }

    public MtlTransactionDetalleDto getTransactionsInterfaceById(Long interfaceTransactionId) {
        try {
            return this.mService.getTransactionsInterfaceById(interfaceTransactionId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mview.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) {
        try {
            return this.mService.getMtlSystemItemsById(inventoryItemId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mview.showError(e.getMessage());
        }
        return null;
    }

    public void deleteTransactionsInterfaceById(Long transactionInterfaceId) {
        try {
            this.mService.deleteTransactionsInterfaceById(transactionInterfaceId);
            mview.resultadoOkDeleteTransaction();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mview.showError(e.getMessage());
        }
    }
}
