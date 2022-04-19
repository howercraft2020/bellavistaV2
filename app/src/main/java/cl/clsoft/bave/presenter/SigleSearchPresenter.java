package cl.clsoft.bave.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.dao.rowmapper.service.IInventarioFisicoService;
import cl.clsoft.bave.task.AppTask;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivitySigleSearch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigleSearchPresenter extends BasePresenter {

    private static final String TAG = "SigleSearch";
    private ActivitySigleSearch mView;
    private IInventarioFisicoService mService;
    private final TaskExecutor mTaskExecutor;

    public SigleSearchPresenter(@NonNull final ActivitySigleSearch view, @NonNull final TaskExecutor taskExecutor, @NonNull final IInventarioFisicoService service){
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public void getItems(String pattern) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new SigleSearchPresenter.SearchSigle(pattern));
    }

    public void getItemsEntrega(String pattern, Long shipmentHeaderId) {
        mView.showProgres("Cargando productos...");
        //mTaskExecutor.async(new SigleSearchPresenter.SearchSigleEntrega(pattern, shipmentHeaderId));
        new SigleSearchPresenter.SearchSigleEntrega(pattern, shipmentHeaderId);
    }

    public void getItemsRecepcion(String pattern, Long poHeaderId) {
        mView.showProgres("Cargando productos...");
        //mTaskExecutor.async(new SigleSearchPresenter.SearchSigleRecepcion(pattern, poHeaderId));

        new SigleSearchPresenter.SearchSigleRecepcion(pattern, poHeaderId);

    }

    public void getItemsTransSubinv(String pattern, String subinventario, String locatorCodigo) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new SigleSearchPresenter.SearchSigleTransSubinv(pattern, subinventario, locatorCodigo));
    }

    public void getItemsTransOrg(String pattern, String subinventario, String locatorCodigo) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new SigleSearchPresenter.SearchSigleTransOrg(pattern, subinventario, locatorCodigo));
    }

    public void getItemsEntregaOrganizacion(String pattern, Long shipmentHeaderId) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new SigleSearchPresenter.SearchSigleEntregaOrganizaciones(pattern, shipmentHeaderId));
    }

    public void getItemsCiclico(String pattern, Long countHeaderId, Long locatorId) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new SigleSearchPresenter.SearchSigleCiclico(pattern, countHeaderId, locatorId));
    }

    public void getItemsFisico(String pattern, Long inventoryId, Long locatorId) {
        mView.showProgres("Cargando productos...");
        mTaskExecutor.async(new SigleSearchPresenter.SearchSigleFisico(pattern, inventoryId, locatorId));
    }

    private class SearchSigle implements AppTask<List<MtlSystemItems>> {

        private String pattern;

        public SearchSigle(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsByDescription(this.pattern);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);

        }
    }

    private class SearchSigleTransSubinv implements AppTask<List<MtlSystemItems>> {

        private String pattern;
        private String subinventario;
        private String locatorCodigo;

        public SearchSigleTransSubinv(String pattern, String subinventario, String locatorCodigo) {
            this.pattern = pattern;
            this.subinventario = subinventario;
            this.locatorCodigo = locatorCodigo;
        }

        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsTransSubinbByDescription(this.pattern,this.subinventario, this.locatorCodigo);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);
        }
    }

    private class SearchSigleTransOrg implements AppTask<List<MtlSystemItems>> {

        private String pattern;
        private String subinventario;
        private String locatorCodigo;

        public SearchSigleTransOrg(String pattern, String subinventario, String locatorCodigo) {
            this.pattern = pattern;
            this.subinventario = subinventario;
            this.locatorCodigo = locatorCodigo;
        }

        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsTransOrgByDescription(this.pattern,this.subinventario, this.locatorCodigo);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);
        }
    }

    private class SearchSigleRecepcion  {

        private String pattern;
        private Long poHeaderId;

        private IRestMtlSystemItems iRestMtlSystemItems;

        public SearchSigleRecepcion(String pattern, Long poHeaderId) {
            this.pattern = pattern;
            this.poHeaderId = poHeaderId;
            this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();


            iRestMtlSystemItems.getAllByDescriptionPoHeaderId(pattern,poHeaderId).enqueue(new Callback<List<MtlSystemItems>>() {
                @Override
                public void onResponse(Call<List<MtlSystemItems>> call, Response<List<MtlSystemItems>> response) {
                    if(response.isSuccessful()==true && response.code()==200){
                    mView.hideProgres();
                    mView.fillItem(response.body());

                    }

                }

                @Override
                public void onFailure(Call<List<MtlSystemItems>> call, Throwable t) {
                    mView.showError(t.getMessage());
                }
            });


        }

        /*
        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsRecepcionByDescription(this.pattern, this.poHeaderId);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);
        }

         */



    }

    private class SearchSigleEntrega  {

        private String pattern;
        private Long shipmentHeaderId;
        private IRestMtlSystemItems iRestMtlSystemItems;

        public SearchSigleEntrega(String pattern, Long shipmentHeaderId) {
            this.pattern = pattern;
            this.shipmentHeaderId = shipmentHeaderId;
            this.iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();


            iRestMtlSystemItems.getAllByDescriptionShipment(this.pattern,this.shipmentHeaderId).enqueue(new Callback<List<MtlSystemItems>>() {
                @Override
                public void onResponse(Call<List<MtlSystemItems>> call, Response<List<MtlSystemItems>> response) {
                    if(response.isSuccessful()==true && response.code()==200){


                        mView.hideProgres();
                        mView.fillItem(response.body());


                    }
                }

                @Override
                public void onFailure(Call<List<MtlSystemItems>> call, Throwable t) {
                    mView.showError(t.getMessage());
                }
            });





        }





        /*
        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsEntregaByDescription(this.pattern, this.shipmentHeaderId);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);

        }

         */
    }

    private class SearchSigleEntregaOrganizaciones implements AppTask<List<MtlSystemItems>> {

        private String pattern;
        private Long shipmentHeaderId;

        public SearchSigleEntregaOrganizaciones(String pattern, Long shipmentHeaderId) {
            this.pattern = pattern;
            this.shipmentHeaderId = shipmentHeaderId;
        }

        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsEntregaOrganizacionesByDescription(this.pattern, this.shipmentHeaderId);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);

        }
    }

    private class SearchSigleCiclico implements AppTask<List<MtlSystemItems>> {

        private String pattern;
        private Long countHeaderId;
        private Long locatorId;

        public SearchSigleCiclico(String pattern, Long countHeaderId, Long locatorId) {
            this.pattern = pattern;
            this.countHeaderId = countHeaderId;
            this.locatorId = locatorId;
        }

        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsCiclicoByDescription(this.pattern, this.countHeaderId, this.locatorId);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);

        }
    }

    private class SearchSigleFisico implements AppTask<List<MtlSystemItems>> {

        private String pattern;
        private Long inventoryId;
        private Long locatorId;

        public SearchSigleFisico(String pattern, Long inventoryId, Long locatorId) {
            this.pattern = pattern;
            this.inventoryId = inventoryId;
            this.locatorId = locatorId;
        }

        @Override
        public List<MtlSystemItems> execute() {
            Log.d(TAG, "SearchSigle::execute");
            List<MtlSystemItems> salida = new ArrayList<>();
            try {
                salida = mService.getItemsFisicoByDescription(this.pattern, this.inventoryId, this.locatorId);
            } catch (ServiceException e) {
                Log.d(TAG, "SearchSigle::execute::ServiceException");
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
        public void onPostExecute(@Nullable List<MtlSystemItems> result) {
            mView.hideProgres();
            mView.fillItem(result);

        }
    }
}
