package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Organizacion;
import cl.clsoft.bave.dao.rowmapper.service.ITransOrgService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityAgregarTransOrgDestino;

public class AgregarTransOrgDestinoPresenter extends BasePresenter {

    private static final String TAG = "AgregarTransOrgDestin";
    private ActivityAgregarTransOrgDestino mView;
    private ITransOrgService mService;
    private TaskExecutor mTaskExecutor;

    public AgregarTransOrgDestinoPresenter(ActivityAgregarTransOrgDestino mView, ITransOrgService mService, TaskExecutor mTaskExecutor) {
        this.mView = mView;
        this.mService = mService;
        this.mTaskExecutor = mTaskExecutor;
    }

    public void getOrganizacionesDestino() {
        mView.showProgres("Cargando Organizaciones");
        mTaskExecutor.async(new AgregarTransOrgDestinoPresenter.OrganizacionDestino());
    }

    private class OrganizacionDestino implements AppTask<List<Organizacion>> {

        public OrganizacionDestino() {
        }


        @Override
        public List<Organizacion> execute() {
            Log.d(TAG, "OrganizacionDestino::execute");
            List<Organizacion> organizaciones = new ArrayList<>();
            try {
                organizaciones = mService.getOrganizacionesHacia();
            } catch (ServiceException e) {
                Log.d(TAG, "OrganizacionDestino::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }

            return organizaciones;
        }

        @Override
        public void onPostExecute(@Nullable List<Organizacion> result) {
            Log.d(TAG, "OrganizacionDestino::onPostExecute");
            mView.hideProgres();
            mView.fillOrganizaciones(result);
        }
    }
}
