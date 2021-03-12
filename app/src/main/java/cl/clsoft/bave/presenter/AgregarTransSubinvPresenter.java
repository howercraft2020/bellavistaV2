package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityAgregarTransSubinv;

public class AgregarTransSubinvPresenter extends BasePresenter {

    private static final String TAG = "AgregarTransSubiPrese";
    private ActivityAgregarTransSubinv mview;
    private ITransSubinvService mService;
    private TaskExecutor mTaskExecutor;

    public AgregarTransSubinvPresenter(ActivityAgregarTransSubinv mview, @NonNull final TaskExecutor taskExecutor, final ITransSubinvService mService) {
        this.mview = mview;
        this.mTaskExecutor = taskExecutor;
        this.mService = mService;
    }

    public void getLocalizadoresBySubinventario(String subinventarioCodigo) {
        mview.showProgres("Cargando localizadores");
        mTaskExecutor.async(new AgregarTransSubinvPresenter.LocalizadoresBySubinventario(subinventarioCodigo));
    }

    public boolean cargaTransferencia(String articulo, String lote, String subinventario, String localizador, Double cantidad){

        boolean ok = true;

            try {

                if (articulo.equals("")){
                    mview.showError("Debe ingresar codigo Sigle");
                    ok = false;
                }
                else if (cantidad == 0){
                    mview.showError("Debe Ingresar una cantidad mayor a 0");
                    ok = false;
                }
                else if (subinventario.equals("")){
                    mview.showError("Debe ingresar un subinventario");
                    ok = false;
                }
                else {
                    this.mService.cargaTransferencia(articulo, lote, subinventario, localizador, cantidad);
                    ok = true;
                }
            }catch (ServiceException e){
            e.printStackTrace();
            if(e.getCodigo() == 1){
                mview.showWarning(e.getDescripcion());
            }else if (e.getCodigo() == 2){
                mview.showError(e.getDescripcion());
            }
            ok = false;
        }
        return ok;
    }


    public List<MtlSerialNumbersInterface> getAllSeries(Long transactionInterfaceId){
        try {
            return mService.getAllSeries(transactionInterfaceId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getSubinventarios(){
        List<Subinventario> subinventarios;
        try{
            subinventarios = this.mService.getSubinventarios();
            mview.fillSubinventario(subinventarios);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        }
        return null;
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
