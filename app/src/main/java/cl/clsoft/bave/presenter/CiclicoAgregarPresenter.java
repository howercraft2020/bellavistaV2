package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IConteoCiclicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityCiclicoAgregar;
import cl.clsoft.bave.view.ActivityCiclicoDetalle;

public class CiclicoAgregarPresenter extends BasePresenter {

    private static final String TAG = "CiclicoAgregar";
    private ActivityCiclicoAgregar mView;
    private IConteoCiclicoService mService;
    private TaskExecutor mTaskExecutor;

    public CiclicoAgregarPresenter(final ActivityCiclicoAgregar view, @NonNull final TaskExecutor taskExecutor, final IConteoCiclicoService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public void getLocalizadoresBySubinventario(String subinventarioCodigo, Long countHeaderId) {
        mView.showProgres("Cargando localizadores...");
        mTaskExecutor.async(new CiclicoAgregarPresenter.LocalizadoresBySubinventario(subinventarioCodigo, countHeaderId));
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

    public Localizador getLocalizadorbyCodigo(String codigo) {
        try {
            return this.mService.getLocalizadorByCodigo(codigo);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getSegmentosByCountHeaderIdSubinventoryLocatorId(Long countHeaderId, String subinventory, Long locatorId) {
        try {
            return this.mService.getSegmentsByCountHeaderIdSubinventoryLocator(countHeaderId, subinventory, locatorId);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getLotes(Long cycleCountHeaderId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getLotesByCountHeaderIdSubinventoryLocatorIdSegment(cycleCountHeaderId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getSeries(Long inventarioCiclicoId, String subinventarioId, Long locatorId, String segment) {
        try {
            return this.mService.getSerialByCountHeaderIdSubinventoryLocatorIdSegment(inventarioCiclicoId, subinventarioId, locatorId, segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void grabarInventario(Long inventarioCiclicoId, String subinventarioId, Long locatorId, String segment, String serie, String lote, Double cantidad) {
        try {
            this.mService.grabarInventario(inventarioCiclicoId, subinventarioId, locatorId, segment, serie, lote, cantidad);
            mView.cleanScreen();
            this.mView.showSuccess("Inventario ingresado.");
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
    }

    private class LocalizadoresBySubinventario implements AppTask<List<Localizador>> {

        private String subinventarioCodigo;
        private Long countHeaderId;

        public LocalizadoresBySubinventario(String subinventarioCodigo, Long countHeaderId) {
            this.subinventarioCodigo = subinventarioCodigo;
            this.countHeaderId = countHeaderId;
        }

        @Override
        public List<Localizador> execute() {
            Log.d(TAG, "LocalizadoresBySubinventario::execute");
            List<Localizador> localizadores = new ArrayList<>();
            try {
                localizadores = mService.getLocalizadoresBySubinventarioCountheaderId(this.subinventarioCodigo, this.countHeaderId);
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
