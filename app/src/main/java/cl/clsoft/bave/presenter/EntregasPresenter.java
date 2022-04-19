package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dao.rowmapper.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregas;
import cl.clsoft.bave.viewmodel.RcvShipmentHeadersViewModel;

public class EntregasPresenter extends BasePresenter {

    private static final String TAG = "EntregasPresenter";
    private ActivityEntregas mview;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;


    private RcvShipmentHeadersViewModel rcvShipmentHeadersViewModel;




    public EntregasPresenter(@NonNull final ActivityEntregas mview, @NonNull final IEntregaService mService){
        this.mview = mview;
        this.mService = mService;
    }



    /*
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
          //mView.fillEntregas(result);
        }
    }
*/
}
