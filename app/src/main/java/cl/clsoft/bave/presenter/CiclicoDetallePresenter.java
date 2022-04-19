package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.dao.rowmapper.service.IConteoCiclicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityCiclicoDetalle;

public class CiclicoDetallePresenter extends BasePresenter {

    private static final String TAG = "CiclicoDetalle";
    private ActivityCiclicoDetalle mView;
    private IConteoCiclicoService mService;
    private TaskExecutor mTaskExecutor;

    public CiclicoDetallePresenter(final ActivityCiclicoDetalle mView, @NonNull final TaskExecutor taskExecutor, final IConteoCiclicoService mService) {
        this.mView = mView;
        this.mService = mService;
        this.mTaskExecutor = taskExecutor;
    }

    public void loadEntries(Long countHeaderId, String subinventory) {
        mView.showProgres("Cargando...");
        mTaskExecutor.async(new CiclicoDetallePresenter.GetEntriesInventariadas(countHeaderId, subinventory));
    }

    public MtlCycleCountHeaders getMtlCycleCountHeaders(Long id) {
        try {
            return mService.getConteoCiclico(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MtlCycleCountEntries> getSigleInformation(Long idSigleDetalle){
        try{
            return this.mService.getAllSigleInformation(idSigleDetalle);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    private class GetEntriesInventariadas implements AppTask<List<MtlCycleCountEntries>> {
        private Long countHeaderId;
        private String subinventory;

        public GetEntriesInventariadas(Long countHeaderId, String subinventory) {
            this.countHeaderId = countHeaderId;
            this.subinventory = subinventory;
        }

        @Override
        public List<MtlCycleCountEntries> execute() {
            Log.d(TAG, "GetEntriesInventariadas::execute");
            List<MtlCycleCountEntries> entries = new ArrayList<>();
            try {
                entries = mService.getAllEntriesInventariadas(this.countHeaderId, this.subinventory);
            } catch (ServiceException e) {
                Log.d(TAG, "GetEntriesInventariadas::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return entries;
        }

        @Override
        public void onPostExecute(@Nullable List<MtlCycleCountEntries> result) {
            Log.d(TAG, "GetEntriesInventariadas::onPostExecute");
            mView.hideProgres();
            mView.fillEntries(result);
        }
    }
}
