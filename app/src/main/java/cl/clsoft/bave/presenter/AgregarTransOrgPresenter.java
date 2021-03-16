package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.ITransOrgService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityAgregarTransOrg;

public class AgregarTransOrgPresenter extends BasePresenter {

    private static final String TAG = "AgregarTransOrgPres";
    private ActivityAgregarTransOrg mView;
    private ITransOrgService mService;
    private TaskExecutor mTaskExecutor;

    public AgregarTransOrgPresenter(ActivityAgregarTransOrg mView, ITransOrgService mService, TaskExecutor mTaskExecutor) {
        this.mView = mView;
        this.mService = mService;
        this.mTaskExecutor = mTaskExecutor;
    }

    public void getLocalizadoresBySubinventario(String subinventarioCodigo) {
        mView.showProgres("Cargando localizadores");
        mTaskExecutor.async(new AgregarTransOrgPresenter.LocalizadoresBySubinventario(subinventarioCodigo));
    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void getSubinventarios(){
        List<Subinventario> subinventarios;
        try{
            subinventarios = this.mService.getSubinventarios();
            mView.fillSubinventario(subinventarios);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public boolean validaTransferencia(String articulo, String lote, String subinventario, String localizador, Double cantidad, String orgDestino, List<String> series){

        boolean ok = true;

        try {

            if (articulo.equals("")){
                mView.showError("Debe ingresar codigo Sigle");
                ok = false;
            }
            else if (cantidad == 0){
                mView.showError("Debe Ingresar una cantidad mayor a 0");
                ok = false;
            }
            else if (subinventario.equals("")){
                mView.showError("Debe ingresar un subinventario");
                ok = false;
            }
            else {
                this.mService.validaTransferencia(articulo, lote, subinventario, localizador, cantidad, orgDestino, series);
                ok = true;
            }
        }catch (ServiceException e){
            e.printStackTrace();
            if(e.getCodigo() == 1){
                mView.showWarning(e.getDescripcion());
            }else if (e.getCodigo() == 2){
                mView.showError(e.getDescripcion());
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
                localizadores = mService.getLocalizadoresBySubinventario(subinventarioCodigo);
            } catch (ServiceException e) {
                Log.d(TAG, "LocalizadoresBySubinventario::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }

            return localizadores;
        }

        @Override
        public void onPostExecute(@Nullable List<Localizador> result) {
            Log.d(TAG, "LocalizadoresBySubinventario::onPostExecute");
            mView.hideProgres();
            mView.fillLocator(result);
        }

    }
}
