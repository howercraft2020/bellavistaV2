package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityFisicoDetalle;
import cl.clsoft.bave.view.ActivityFisicoSub;

public class DetalleFisicoPresenter extends BasePresenter {
    private static final String TAG = "DetalleFisicoPresenter";
    private ActivityFisicoDetalle mView;
    private IInventarioFisicoService mService;

    public DetalleFisicoPresenter(@NonNull final ActivityFisicoDetalle mView, @NonNull IInventarioFisicoService mService){
        this.mView = mView;
        this.mService = mService;
    }

    public List<MtlPhysicalInventoryTags> getTagsByInventorySubinventory(Long physicalInventoryId, String subinventory){
        try {
            mView.showProgres();
            List<MtlPhysicalInventoryTags> tags = this.mService.getAllTagsByInventorySubinventory(physicalInventoryId, subinventory);
            mView.hideProgres();
            return tags;
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public MtlPhysicalInventories getPreviousInventarioFisicos(Long physicalInventoryId){
        try {
            return this.mService.getInventarioFisico(physicalInventoryId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

}
