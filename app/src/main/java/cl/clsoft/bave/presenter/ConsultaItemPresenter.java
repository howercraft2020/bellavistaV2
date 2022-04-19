package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.dao.rowmapper.service.IConsultaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityConsultaItem;

public class ConsultaItemPresenter extends BasePresenter {

    private static final String TAG = "ConsultaItemPresenter";
    private ActivityConsultaItem mView;
    private IConsultaService mService;
    private TaskExecutor mTaskExecutor;

    public ConsultaItemPresenter(final ActivityConsultaItem view, @NonNull final TaskExecutor taskExecutor, final IConsultaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public List<ConsultaItem> getAllByItem(Long inventoryItemId) {
        try {
            return this.mService.getAllByItem(inventoryItemId);
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

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }
}
