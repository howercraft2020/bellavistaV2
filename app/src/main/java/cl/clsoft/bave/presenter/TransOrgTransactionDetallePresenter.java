package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dto.MtlTransactionOrgDto;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.dao.rowmapper.service.ITransOrgService;
import cl.clsoft.bave.view.ActivityTransOrgTransactionDetalle;

public class TransOrgTransactionDetallePresenter extends BasePresenter {

    private static final String TAG = "TransOrgDetalle";
    private ActivityTransOrgTransactionDetalle mView;
    private ITransOrgService mService;

    public TransOrgTransactionDetallePresenter(ActivityTransOrgTransactionDetalle mView, ITransOrgService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public MtlTransactionOrgDto getTransactionsInterfaceById(Long interfaceTransactionId) {
        try{
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

    public void deleteTransactionsInterfaceById(Long transactionInterfaceId) {
        try {
            this.mService.deleteTransactionsInterfaceById(transactionInterfaceId);
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
