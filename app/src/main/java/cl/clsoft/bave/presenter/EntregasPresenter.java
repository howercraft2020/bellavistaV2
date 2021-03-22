package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.service.IEntregaService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregas;

public class EntregasPresenter extends BasePresenter {

    private static final String TAG = "EntregasPresenter";
    private ActivityEntregas mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;

    public EntregasPresenter (@NonNull final ActivityEntregas view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public void getEntregas() {
        this.mView.showProgres("Cargando Entregas...");
        this.mTaskExecutor.async(new EntregasPresenter.Entregas());
    }

    private class Entregas implements AppTask<List<RcvShipmentHeaders>> {

        public Entregas() {}

        @Override
        public List<RcvShipmentHeaders> execute() {
            Log.d(TAG, "Entregas::execute");
            List<RcvShipmentHeaders> headers = new ArrayList<>();
            try {
                headers = mService.getEntregas();
            } catch (ServiceException e) {
                Log.d(TAG, "Entregas::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return headers;
        }

        @Override
        public void onPostExecute(@Nullable List<RcvShipmentHeaders> result) {
            mView.hideProgres();
            mView.fillEntregas(result);
        }
    }

}
