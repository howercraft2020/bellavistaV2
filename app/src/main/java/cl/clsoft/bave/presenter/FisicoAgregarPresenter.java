package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestLocalizador;
import cl.clsoft.bave.apis.IRestMtlPhysicalInventoryTags;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.dao.rowmapper.service.IInventarioFisicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityFisicoAgregar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FisicoAgregarPresenter extends BasePresenter {

    private static final String TAG = "AgregarFisicoInventario";
    private ActivityFisicoAgregar mView;
    private IInventarioFisicoService mService;
    private final TaskExecutor mTaskExecutor;

    //API
    IRestMtlPhysicalInventoryTags iRestMtlPhysicalInventoryTags;
    IRestMtlSystemItems iRestMtlSystemItems;
    IRestLocalizador iRestLocalizador;

    // Return de Response.body()
    static  List<MtlPhysicalInventoryTags> ReturnTags;
    static  List<String> ReturnLocators;
    static List<String> ReturnGetString;
    static MtlSystemItems ReturnMtlSystemItems;
    static Localizador Returnlocalizador;

    public FisicoAgregarPresenter(@NonNull final ActivityFisicoAgregar mView, @NonNull final TaskExecutor taskExecutor, @NonNull final IInventarioFisicoService mService){
        this.mView = mView;
        this.mTaskExecutor = taskExecutor;
        this.mService = mService;
        this.iRestMtlPhysicalInventoryTags = ApiUtils.getIRestMtlPhysicalInventoryTagsResponse();
        this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        this.iRestLocalizador = ApiUtils.getIRestLocalizador();
    }

    public List<MtlPhysicalInventoryTags> getTagsByInventorySubinventory(Long physicalInventoryId, String subinventory) {
        try {
            mView.showProgres();
            List<MtlPhysicalInventoryTags> tags = this.mService.getAllTagsByInventorySubinventory(physicalInventoryId, subinventory);

            iRestMtlPhysicalInventoryTags.getAllByInventorySubinventory(physicalInventoryId,subinventory).enqueue(new Callback<List<MtlPhysicalInventoryTags>>() {
                @Override
                public void onResponse(Call<List<MtlPhysicalInventoryTags>> call, Response<List<MtlPhysicalInventoryTags>> response) {
                    List<MtlPhysicalInventoryTags> tags =  response.body();
                    ReturnTags = response.body();
                }

                @Override
                public void onFailure(Call<List<MtlPhysicalInventoryTags>> call, Throwable t) {

                }
            });
            mView.hideProgres();
            return ReturnTags;
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
        // List<String> locators = this.mService.getLocator(physicalInventoryId, subinventory);
        // Log.d(TAG, "locators size: " + locators.size());
        iRestMtlPhysicalInventoryTags.getLocatorByInventorySubinventory(physicalInventoryId,subinventory).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful() == true){
                    ReturnLocators = response.body();
                    Log.d(TAG, "locators size: " + ReturnLocators.size());

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        return ReturnLocators;
    }

    public List<String> getSeries(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        iRestMtlPhysicalInventoryTags.getSeriesByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()== true){
                    ReturnGetString = response.body();
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                mView.showWarning(t.getMessage());
                ReturnGetString = null;
            }
        });
        // SE ELIMINO CATCH
        // return this.mService.getSeries(physicalInventoryId, subinventory, locatorId, segment);
        return ReturnGetString;
    }

    public List<String> getLotes(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {

        iRestMtlPhysicalInventoryTags.getLotesByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ReturnGetString = response.body();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                mView.showWarning(t.getMessage());
                ReturnGetString = null;
            }
        });

        /* try {
            return this.mService.getLotes(physicalInventoryId, subinventory, locatorId, segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null; */
        return ReturnGetString;
    }

    public List<String> getVencimientos(Long physicalInventoryId, String subinventory, Long locatorId, String segment) {
        try {
            iRestMtlPhysicalInventoryTags.getVencimientosByInventorySubinventoryLocatorSegment(physicalInventoryId, subinventory, locatorId, segment).enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if (response.isSuccessful() == true) {
                        ReturnGetString = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    mView.showWarning(t.getMessage());
                    ReturnGetString = null;
                }
            });
            // Se elimina Catch
            //return this.mService.getVencimientos(physicalInventoryId, subinventory, locatorId, segment);
            return ReturnGetString;

        } /*catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }*/ finally {

        }
        }


    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {

        iRestMtlSystemItems.getBySegment(segment).enqueue(new Callback<MtlSystemItems>() {
            @Override
            public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                if(response.isSuccessful() == true){
                    ReturnMtlSystemItems = response.body();
                }
            }

            @Override
            public void onFailure(Call<MtlSystemItems> call, Throwable t) {
                mView.showWarning(t.getMessage());
                ReturnMtlSystemItems = null;
            }
        });
       /* try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;*/
        return  ReturnMtlSystemItems;
    }

    public void grabarInventario(Long inventarioId, String subinventarioId, Long locatorId, String segment, String serie, String lote, String vencimiento, Double cantidad) {
     // REVISAR
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
        iRestLocalizador.getByCodigo(codigo).enqueue(new Callback<Localizador>() {
            @Override
            public void onResponse(Call<Localizador> call, Response<Localizador> response) {
                if(response.isSuccessful()==true){
                    Returnlocalizador = response.body();
                }
            }

            @Override
            public void onFailure(Call<Localizador> call, Throwable t) {
                    mView.showWarning(t.getMessage());
                    Returnlocalizador = null;
            }
        });

        /*try {
            return this.mService.getLocalizadorByCodigo(codigo);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;*/
        return Returnlocalizador;
    }

    //REVISAR  --->
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
