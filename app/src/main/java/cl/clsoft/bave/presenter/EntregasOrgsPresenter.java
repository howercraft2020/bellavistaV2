package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.EntregaOrgsHeader;
import cl.clsoft.bave.dao.rowmapper.service.IEntregaOrgsService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregasOrgs;

public class EntregasOrgsPresenter extends BasePresenter {

    private static final String TAG = "EntregasOrgsPresenter";
    private ActivityEntregasOrgs mView;
    private IEntregaOrgsService mService;
    private TaskExecutor mTaskExecutor;

    public EntregasOrgsPresenter (@NonNull final ActivityEntregasOrgs view, @NonNull final TaskExecutor taskExecutor, @NonNull final IEntregaOrgsService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public void getEntregas() {
        this.mView.showProgres("Cargando Entregas...");
        this.mTaskExecutor.async(new EntregasOrgsPresenter.Entregas());
    }

    private class Entregas implements AppTask<List<EntregaOrgsHeader>> {

        public Entregas() {}

        @Override
        public List<EntregaOrgsHeader> execute() {
            Log.d(TAG, "Entregas::execute");
            List<EntregaOrgsHeader> headers = new ArrayList<>();
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
        public void onPostExecute(@Nullable List<EntregaOrgsHeader> result) {
            mView.hideProgres();
            mView.fillEntregas(result);
        }
    }

}
