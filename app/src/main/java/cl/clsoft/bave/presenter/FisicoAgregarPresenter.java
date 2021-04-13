package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityFisicoAgregar;

public class FisicoAgregarPresenter extends BasePresenter {

    private static final String TAG = "AgregarFisicoInventario";
    private ActivityFisicoAgregar mView;
    private IInventarioFisicoService mService;
    private final TaskExecutor mTaskExecutor;

    public FisicoAgregarPresenter(@NonNull final ActivityFisicoAgregar mView, @NonNull final TaskExecutor taskExecutor, @NonNull final IInventarioFisicoService mService){
        this.mView = mView;
        this.mTaskExecutor = taskExecutor;
        this.mService = mService;
    }

    public List<MtlPhysicalInventoryTags> getTagsByInventorySubinventory(Long physicalInventoryId, String subinventory) {
        try {
            mView.showProgres();
            List<MtlPhysicalInventoryTags> tags = this.mService.getAllTagsByInventorySubinventory(physicalInventoryId, subinventory);
            mView.hideProgres();
            return tags;
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getLocator(Long physicalInventoryId, String subinventory) {
        try {
            List<String> locators = this.mService.getLocator(physicalInventoryId, subinventory);
            Log.d(TAG, "locators size: " + locators.size());
            return locators;
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getSeries(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getSeries(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getLotes(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getLotes(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public List<String> getVencimientos(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            return this.mService.getVencimientos(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
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

    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Double cantidad) {
        try {
            this.mService.grabarInventario(inventarioId, subinventarioId, locatorId, segment, serie, lote, vencimiento,cantidad);
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

    public void getSegment1(Long inventarioId, String subinventarioCodigo, String locatorCodigo) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new FisicoAgregarPresenter.SigleByLoocalizador(inventarioId, subinventarioCodigo, locatorCodigo));
    }

    public void getLocalizadoresBySubinventario(String subinventarioCodigo, long inventarioId) {
        mView.showProgres("Cargando localizadores...");
        mTaskExecutor.async(new FisicoAgregarPresenter.LocalizadoresBySubinventario(subinventarioCodigo, inventarioId));
    }

    private class LocalizadoresBySubinventario implements AppTask<List<Localizador>> {

        private String subinventarioCodigo;
        private Long inventarioId;

        public LocalizadoresBySubinventario(String subinventarioCodigo, Long inventarioId) {
            this.subinventarioCodigo = subinventarioCodigo;
            this.inventarioId = inventarioId;
        }

        @Override
        public List<Localizador> execute() {
            Log.d(TAG, "LocalizadoresBySubinventario::execute");
            List<Localizador> localizadores = new ArrayList<>();
            try {
                localizadores = mService.getLocalizadoresBySubinventarioInventario(this.subinventarioCodigo, this.inventarioId);
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

    private class SigleByLoocalizador implements AppTask<List<String>> {

        private Long inventarioId;
        private String subinventarioCodigo;
        private String locatorCodigo;

        public SigleByLoocalizador(Long inventarioId, String subinventarioCodigo, String locatorCodigo) {
            this.inventarioId = inventarioId;
            this.subinventarioCodigo = subinventarioCodigo;
            this.locatorCodigo = locatorCodigo;
        }

        @Override
        public List<String> execute() {
            Log.d(TAG, "SigleByLoocalizador::execute");
            List<String> salida = new ArrayList<>();
            try {
                salida = mService.getSegment1(this.inventarioId, this.subinventarioCodigo, this.locatorCodigo);
            } catch (ServiceException e) {
                Log.d(TAG, "LocalizadoresBySubinventario::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return salida;
        }

        @Override
        public void onPostExecute(@Nullable List<String> result) {
            mView.hideProgres();
            mView.fillSigle(result);
        }
    }
}
