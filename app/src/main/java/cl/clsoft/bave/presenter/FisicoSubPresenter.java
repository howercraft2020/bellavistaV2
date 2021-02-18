package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityFisicoSub;

public class FisicoSubPresenter extends BasePresenter {
    private static final String TAG = "FisicoDetallePresenter";
    private ActivityFisicoSub mView;
    private IInventarioFisicoService mService;

    public FisicoSubPresenter(final ActivityFisicoSub mView, final IInventarioFisicoService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public MtlPhysicalInventories getPreviousInventarioFisicos(Long physicalInventoryId){
        try {
            return this.mService.getInventarioFisico(physicalInventoryId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MtlSystemItems> getItems(){
        try {
            return this.mService.getAllItems();
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }


    public List<MtlPhysicalSubinventories> getSubinventories(Long id){
        try {
            return this.mService.getSubinventories(id);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }
}
