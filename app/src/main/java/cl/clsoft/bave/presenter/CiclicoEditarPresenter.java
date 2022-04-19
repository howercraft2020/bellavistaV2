package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.dao.rowmapper.service.IConteoCiclicoService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityCiclicoEditar;

public class CiclicoEditarPresenter extends BasePresenter {

    private static final String TAG = "CiclicoEditar";
    private ActivityCiclicoEditar mView;
    private IConteoCiclicoService mService;
    private TaskExecutor mTaskExecutor;

    public CiclicoEditarPresenter(final ActivityCiclicoEditar view, @NonNull final TaskExecutor taskExecutor, final IConteoCiclicoService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public MtlCycleCountEntries getEntry(Long entryId) {
        try {
            return this.mService.getEntry(entryId);
        } catch (ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void updateEntry(Long entryId, Double cantidad) {
        try {
            this.mService.updateEntry(entryId, cantidad);
            mView.showSuccess("Actualización exitosa");
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
    }

    public void deleteEntry(Long entryId) {
        try {
            this.mService.deleteEntry(entryId);
            mView.mensajeOkDelete();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
    }
}
