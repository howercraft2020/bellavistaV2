package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.ConsultaResumenItem;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.dao.rowmapper.service.IConsultaService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityConsultaSubinventario;

public class ConsultaSubinventarioPresenter extends BasePresenter {

    private static final String TAG = "ConsultaItemPresenter";
    private ActivityConsultaSubinventario mView;
    private IConsultaService mService;
    private TaskExecutor mTaskExecutor;

    public ConsultaSubinventarioPresenter(final ActivityConsultaSubinventario view, @NonNull final TaskExecutor taskExecutor, final IConsultaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public List<Subinventario> getSubinventarios() {
        try {
            return mService.getSubinventarios();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public void getStock(String subinventoryCode) {
        mView.showProgres("Cargando stock...");
        mTaskExecutor.async(new ConsultaSubinventarioPresenter.GetStock(subinventoryCode));
    }

    private class GetStock implements AppTask<List<ConsultaResumenItem>> {

        private String subinventoryCode;

        public GetStock(String subinventoryCode) {
            this.subinventoryCode = subinventoryCode;
        }

        @Override
        public List<ConsultaResumenItem> execute() {
            Log.d(TAG, "GetStock::execute");
            List<ConsultaResumenItem> stock = new ArrayList<>();
            try {
                stock = mService.getAllResumenBySubinventory(this.subinventoryCode);
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
        public void onPostExecute(@Nullable List<ConsultaResumenItem> result) {
            Log.d(TAG, "GetStock::onPostExecute");
            mView.hideProgres();
            mView.fillStock(result);
        }
    }

}
