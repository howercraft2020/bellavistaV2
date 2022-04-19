package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlPhysicalInventoryTags;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.dao.rowmapper.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityFisicoEditar;

public class FisicoEditarPresenter extends BasePresenter {

    private static final String TAG = "FisicoEditar";
    private ActivityFisicoEditar mView;
    private IInventarioFisicoService mService;
    IRestMtlPhysicalInventoryTags iRestMtlPhysicalInventoryTags;

    public FisicoEditarPresenter(@NonNull final ActivityFisicoEditar mView, @NonNull final IInventarioFisicoService mService){
        this.mView = mView;
        this.mService = mService;
        this.iRestMtlPhysicalInventoryTags = ApiUtils.getIRestMtlPhysicalInventoryTagsResponse();
    }

    public MtlPhysicalInventoryTags getTag(Long tagId) {
        try {
            return this.mService.getTag(tagId);
        } catch(ServiceException e) {

        }
        return null;
    }

    public void updateTag(Long tagId, Double cantidad) {
        try {

            this.mService.updateTag(tagId, cantidad);
            mView.showSuccess("Actualización exitosa");
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
    }

    public void deleteTag(Long tagId) {
        try {
            this.mService.deleteTag(tagId);
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
