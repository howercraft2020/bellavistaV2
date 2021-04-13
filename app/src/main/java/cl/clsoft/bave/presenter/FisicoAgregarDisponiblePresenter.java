package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityFisicoAgregar;
import cl.clsoft.bave.view.ActivityFisicoAgregarDisponible;

public class FisicoAgregarDisponiblePresenter extends BasePresenter {

    private static final String TAG = "FISICO";
    private ActivityFisicoAgregarDisponible mView;
    private IInventarioFisicoService mService;
    private final TaskExecutor mTaskExecutor;

    public FisicoAgregarDisponiblePresenter(@NonNull final ActivityFisicoAgregarDisponible mView, @NonNull final TaskExecutor taskExecutor, @NonNull final IInventarioFisicoService mService){
        this.mView = mView;
        this.mTaskExecutor = taskExecutor;
        this.mService = mService;
    }

    public MtlPhysicalInventoryTags getTag(Long tagId) {
        try {
            return this.mService.getTag(tagId);
        } catch(ServiceException e) {

        }
        return null;
    }

    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Double cantidad) {
        try {
            this.mService.grabarInventario(inventarioId, subinventarioId, locatorId, segment, serie, lote, vencimiento,cantidad);
            this.mView.mensajeOkGrabar();
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
    }

}
