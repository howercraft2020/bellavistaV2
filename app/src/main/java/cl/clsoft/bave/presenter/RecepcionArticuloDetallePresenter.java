package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityRecepcionArticuloDetalle;

public class RecepcionArticuloDetallePresenter extends BasePresenter {

    private static final String TAG = "RecepcionArticuloDetallePresenter";
    private ActivityRecepcionArticuloDetalle mView;
    private IRecepcionOcService mService;

    public RecepcionArticuloDetallePresenter(ActivityRecepcionArticuloDetalle mView, IRecepcionOcService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public void updateEntry(Long entryId, Double cantidad, Long lineLocationId) {
        try {
            this.mService.updateEntry(entryId, cantidad, lineLocationId);
            mView.showSuccess("Actualización exitosa");
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showError(e.getDescripcion());
            }
        }
    }
}
