package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlMaterialTransactions;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.dao.rowmapper.service.IEntregaOrgsService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaOrgsAgregarResumen;

public class EntregaOrgsAgregarResumenPresenter extends BasePresenter {

    private static final String TAG = "PRESENTER";
    private ActivityEntregaOrgsAgregarResumen mView;
    private IEntregaOrgsService mService;
    private TaskExecutor mTaskExecutor;

    public EntregaOrgsAgregarResumenPresenter (@NonNull final ActivityEntregaOrgsAgregarResumen view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaOrgsService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
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
    public void addTransacctionInterface(Long shipmentHeaderId, Long transactionId, String subinventoryCode,
                                         String locatorCode, String lote, String categoria, String atributo1,
                                         String atributo2, String atributo3, List<String> series, Double cantidad) {
        try {
            this.mService.addTransacctionInterface(shipmentHeaderId, transactionId, subinventoryCode, locatorCode,
                    lote, categoria, atributo1, atributo2, atributo3, series, cantidad);
            mView.resultadoOkAddTransaction();
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
