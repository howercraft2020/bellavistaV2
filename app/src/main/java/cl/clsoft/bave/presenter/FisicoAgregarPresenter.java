package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityFisicoAgregar;

public class FisicoAgregarPresenter extends BasePresenter {

    private static final String TAG = "AgregarFisicoInventario";
    private ActivityFisicoAgregar mView;
    private IInventarioFisicoService mService;

    public FisicoAgregarPresenter(@NonNull final ActivityFisicoAgregar mView, @NonNull final IInventarioFisicoService mService){
        this.mView = mView;
        this.mService = mService;
    }

    public List<MtlPhysicalInventoryTags> getTagsByInventorySubinventory(Long physicalInventoryId, String subinventory) {
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

    public List<String> getLocator(Long physicalInventoryId, String subinventory) {
        try {
            List<String> locators = this.mService.getLocator(physicalInventoryId, subinventory);
            Log.d(TAG, "locators size: " + locators.size());
            return locators;
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getSegment1(Long physicalInventoryId, String subinventory, Long locatorId) {
        try {
            List<String> segments1 = this.mService.getSegment1(physicalInventoryId, subinventory, locatorId);
            Log.d(TAG, "segments1 size: " + segments1.size());
            return segments1;
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getSeries(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getSeries(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getLotes(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getLotes(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getVencimientos(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getVencimientos(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Long cantidad) {
        try {
            this.mService.grabarInventario(inventarioId, subinventarioId, locatorId, segment, serie, lote, vencimiento,cantidad);
            mView.cleanScreen();
            this.mView.showSuccess("Inventario ingresado.");
        } catch(ServiceException e){
            this.mView.showWarning(e.getDescripcion());
        }
    }
}
