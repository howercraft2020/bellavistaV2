package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlPhysicalSubinventories;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityFisicoSub;

public class FisicoSubPresenter extends BasePresenter {

    private static final String TAG = "FisicoDetallePresenter";
    private ActivityFisicoSub mView;
    private IInventarioFisicoService mService;
    private TaskExecutor mTaskExecutor;

    public FisicoSubPresenter(final ActivityFisicoSub mView, @NonNull final TaskExecutor taskExecutor, final IInventarioFisicoService mService) {
        this.mView = mView;
        this.mService = mService;
        this.mTaskExecutor = taskExecutor;
    }

    public void closeInventory(Long inventoryId) {
        mView.showProgres("Cerrando inventario...");
        mTaskExecutor.async(new FisicoSubPresenter.CloseInventory(inventoryId));
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

    private class CloseInventory implements AppTask<Long> {

        private Long inventoryId;

        public CloseInventory(Long inventoryId) {
            this.inventoryId = inventoryId;
        }

        @Override
        public Long execute() {
            Log.d(TAG, "LocalizadoresBySubinventario::execute");
            Long salida = 0L;
            try {
                salida = mService.closeInventory(this.inventoryId);
            } catch (ServiceException e) {
                Log.d(TAG, "CloseInventory::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return salida;
        }

        @Override
        public void onPostExecute(@Nullable Long result) {
            mView.hideProgres();
            if (result.longValue() == 0) {
                mView.mensajeOkCloseInventory();
            } else {
                mView.mensajeErrorCloseInventory();
            }
        }
    }
}
