package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.ConsultaItem;
import cl.clsoft.bave.model.ConsultaResumenItem;
import cl.clsoft.bave.service.IConsultaService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityConsultaSubinventario;
import cl.clsoft.bave.view.ActivityConsultaSubinventarioDetalle;

public class ConsultaSubinventarioDetallePresenter extends BasePresenter {

    private static final String TAG = "ConsultaSubinventario";
    private ActivityConsultaSubinventarioDetalle mView;
    private IConsultaService mService;
    private TaskExecutor mTaskExecutor;

    public ConsultaSubinventarioDetallePresenter(final ActivityConsultaSubinventarioDetalle view, @NonNull final TaskExecutor taskExecutor, final IConsultaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public void getStock(String subinventoryCode, Long inventoryItemId) {
        mView.showProgres("Cargando stock...");
        mTaskExecutor.async(new ConsultaSubinventarioDetallePresenter.GetStock(subinventoryCode, inventoryItemId));
    }

    private class GetStock implements AppTask<List<ConsultaItem>> {

        private String subinventoryCode;
        private Long inventoryItemId;

        public GetStock(String subinventoryCode, Long inventoryItemId) {
            this.subinventoryCode = subinventoryCode;
            this.inventoryItemId = inventoryItemId;
        }

        @Override
        public List<ConsultaItem> execute() {
            Log.d(TAG, "GetStock::execute");
            List<ConsultaItem> stock = new ArrayList<>();
            try {
                stock = mService.getAllBySubinventoryInventoryItem(this.subinventoryCode, this.inventoryItemId);
            } catch (ServiceException e) {
                Log.d(TAG, "GetStock::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return stock;
        }

        @Override
        public void onPostExecute(@Nullable List<ConsultaItem> result) {
            Log.d(TAG, "GetStock::onPostExecute");
            mView.hideProgres();
            mView.fillStock(result);
        }
    }

}
