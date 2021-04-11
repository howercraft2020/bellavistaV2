package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityFisicoTags;

public class FisicoTagsPresenter extends BasePresenter {

    private static final String TAG = "FISICO_TAGS";
    private ActivityFisicoTags mView;
    private IInventarioFisicoService mService;
    private TaskExecutor mTaskExecutor;

    public FisicoTagsPresenter(@NonNull final ActivityFisicoTags mView, @NonNull final TaskExecutor taskExecutor, @NonNull IInventarioFisicoService mService){
        this.mView = mView;
        this.mService = mService;
        this.mTaskExecutor = taskExecutor;
    }

    public void getTagsInventariados(Long physicalInventoryId, String subinventory) {
        this.mView.showProgres("Cargando Tags...");
        this.mView.setCargandoTagsInventariado(true);
        this.mTaskExecutor.async(new FisicoTagsPresenter.TagsInventariados(physicalInventoryId, subinventory));
    }

    public void getTagsNoInventariados(Long physicalInventoryId, String subinventory) {
        this.mView.showProgres("Cargando Tags...");
        this.mView.setCargandoTagsNoInventariado(true);
        this.mTaskExecutor.async(new FisicoTagsPresenter.TagsNoInventariados(physicalInventoryId, subinventory));
    }

    /*
    public List<MtlPhysicalInventoryTags> getTagsInventariados(Long physicalInventoryId, String subinventory){
        try {
            mView.showProgres();
            List<MtlPhysicalInventoryTags> tags = this.mService.getAllTagsInventariadosByInventorySubinventory(physicalInventoryId, subinventory);
            mView.hideProgres();
            return tags;
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<MtlPhysicalInventoryTags> getTagsNoInventariados(Long physicalInventoryId, String subinventory){
        try {
            mView.showProgres();
            List<MtlPhysicalInventoryTags> tags = this.mService.getAllTagsNoInventariadosByInventorySubinventory(physicalInventoryId, subinventory);
            mView.hideProgres();
            return tags;
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }
     */

    public MtlPhysicalInventories getPreviousInventarioFisicos(Long physicalInventoryId){
        try {
            return this.mService.getInventarioFisico(physicalInventoryId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class TagsInventariados implements AppTask<List<MtlPhysicalInventoryTags>> {

        private Long physicalInventoryId;
        private String subinventory;

        public TagsInventariados(Long physicalInventoryId, String subinventory) {
            this.physicalInventoryId = physicalInventoryId;
            this.subinventory = subinventory;
        }

        @Override
        public List<MtlPhysicalInventoryTags> execute() {
            Log.d(TAG, "Entregas::execute");
            List<MtlPhysicalInventoryTags> tags = new ArrayList<>();
            try {
                tags = mService.getAllTagsInventariadosByInventorySubinventory(physicalInventoryId, subinventory);
            } catch (ServiceException e) {
                Log.d(TAG, "Entregas::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return tags;
        }

        @Override
        public void onPostExecute(@Nullable List<MtlPhysicalInventoryTags> result) {
            //mView.hideProgres();
            mView.setCargandoTagsInventariado(false);
            if (!mView.getCargandoTagsInventariado() && !mView.getCargandoTagsNoInventariado())
                mView.hideProgres();
            mView.fillTags(result);

        }
    }

    private class TagsNoInventariados implements AppTask<List<MtlPhysicalInventoryTags>> {

        private Long physicalInventoryId;
        private String subinventory;

        public TagsNoInventariados(Long physicalInventoryId, String subinventory) {
            this.physicalInventoryId = physicalInventoryId;
            this.subinventory = subinventory;
        }

        @Override
        public List<MtlPhysicalInventoryTags> execute() {
            Log.d(TAG, "Entregas::execute");
            List<MtlPhysicalInventoryTags> tags = new ArrayList<>();
            try {
                tags = mService.getAllTagsNoInventariadosByInventorySubinventory(physicalInventoryId, subinventory);
            } catch (ServiceException e) {
                Log.d(TAG, "Entregas::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return tags;
        }

        @Override
        public void onPostExecute(@Nullable List<MtlPhysicalInventoryTags> result) {
            //mView.hideProgres();
            mView.setCargandoTagsNoInventariado(false);
            if (!mView.getCargandoTagsInventariado() && !mView.getCargandoTagsNoInventariado())
                mView.hideProgres();
            mView.fillTagsNoInventariados(result);

        }
    }
}
