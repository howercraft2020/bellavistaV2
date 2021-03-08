package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityTransSubinvDest;

public class TransSubinvDestPresenter extends BasePresenter {

    private static final String TAG = "TransSubinvDestPre";
    private ActivityTransSubinvDest mview;
    private ITransSubinvService mservice;
    private TaskExecutor mTaskExecutor;

    public TransSubinvDestPresenter(ActivityTransSubinvDest mview, @NonNull final TaskExecutor taskExecutor, final ITransSubinvService mservice) {
        this.mview = mview;
        this.mTaskExecutor = taskExecutor;
        this.mservice = mservice;
    }

    public void getLocalizadoresBySubinventario(String subinventarioCodigo) {
        mview.showProgres("Cargando localizadores");
        mTaskExecutor.async(new TransSubinvDestPresenter.LocalizadoresBySubinventario(subinventarioCodigo));
    }

    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Long cantidad,
                              String subinventarioHasta, String localizadorHasta, List<String> series) {

        try {

            if(subinventarioHasta.equals("")){
                mview.showError("Debe ingresar Subinventario");
            }
            else {
                mservice.insertarDatos(id,nroTraspaso, articulo,lote, subinventario, localizador,cantidad,subinventarioHasta,localizadorHasta, series);
                mview.showSuccess("Datos cargados correctamente");
            }

        }catch (ServiceException e) {
            e.printStackTrace();
            if (e.getCodigo() == 1) {
                mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mview.showError(e.getDescripcion());
            }
        }

    }

    public void getSubinventarios(){
        List<Subinventario> subinventarios;
        try{
            subinventarios = this.mservice.getSubinventarios();
            mview.fillSubinventario(subinventarios);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public boolean controlSerie (String articulo) {

        boolean ok = true;

        try{
            mservice.controlSerie(articulo);
            ok = true;
        }catch (ServiceException e) {
            e.printStackTrace();
            if (e.getCodigo() == 1) {
                mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mview.showError(e.getDescripcion());
            }
           ok = false;
        }

        return ok;
    }

    private class LocalizadoresBySubinventario implements AppTask<List<Localizador>> {

        private String subinventarioCodigo;

        public LocalizadoresBySubinventario(String subinventarioCodigo) {
            this.subinventarioCodigo = subinventarioCodigo;
        }

        @Override
        public List<Localizador> execute() {
            Log.d(TAG, "LocalizadoresBySubinventario::execute");
            List<Localizador> localizadores = new ArrayList<>();
            try {
                localizadores = mservice.getLocalizadoresBySubinventario(subinventarioCodigo);
            } catch (ServiceException e) {
                Log.d(TAG, "LocalizadoresBySubinventario::execute::ServiceException");
                e.printStackTrace();
                mview.runOnUiThread(new Runnable() {
                    public void run() {
                        mview.showError(e.getDescripcion());
                    }
                });
            }

            return localizadores;
        }

        @Override
        public void onPostExecute(@Nullable List<Localizador> result) {
            Log.d(TAG, "LocalizadoresBySubinventario::onPostExecute");
            mview.hideProgres();
            mview.fillLocator(result);
        }
    }
}
