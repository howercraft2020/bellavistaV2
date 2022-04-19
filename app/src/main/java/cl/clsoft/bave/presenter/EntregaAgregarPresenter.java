package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestLocalizador;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.Localizador;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.RcvTransactions;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.dao.rowmapper.service.IEntregaService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityEntregaAgregar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntregaAgregarPresenter extends BasePresenter {

    private static final String TAG = "EntregaAgregar";
    private ActivityEntregaAgregar mView;
    private IEntregaService mService;
    private TaskExecutor mTaskExecutor;

    public EntregaAgregarPresenter(final ActivityEntregaAgregar view, @NonNull final TaskExecutor taskExecutor, final IEntregaService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;
    }

    public RcvTransactions getTransactionById(Long transactionId) {
        try {
            return this.mService.getTransactionById(transactionId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public MtlSystemItems getMtlSystemItemsById(Long inventoryItemId) {
        try {
            return this.mService.getMtlSystemItemsById(inventoryItemId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
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

    public List<RcvTransactions> getTransaccionsByShipmentInventory(Long shipmentHeaderId, Long inventoryItemId) {
        try {
            return this.mService.getTransaccionsByShipmentInventory(shipmentHeaderId, inventoryItemId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public List<Subinventario> getSubinventarios() {
        try {
            return mService.getSubinventarios();
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public List<String> getSegmentosByShipment(Long shipmentHeaderId) {
        try {
            return mService.getSegmentsByShipment(shipmentHeaderId);
        } catch(ServiceException e) {
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        } catch(Exception e){
            this.mView.showError(e.getMessage());
        }
        return null;
    }

    public void getLocalizadoresBySubinventario(String subinventarioCodigo) {
        mView.showProgres("Cargando localizadores...");
        //mTaskExecutor.async(new EntregaAgregarPresenter.LocalizadoresBySubinventario(subinventarioCodigo));
        new EntregaAgregarPresenter.LocalizadoresBySubinventario(subinventarioCodigo);


    }

    private class LocalizadoresBySubinventario  {



        private IRestLocalizador iRestLocalizador;
        private String subinventarioCodigo;

        public LocalizadoresBySubinventario(String subinventarioCodigo) {
            this.subinventarioCodigo = subinventarioCodigo;
            this.iRestLocalizador = ApiUtils.getIRestLocalizador();



            iRestLocalizador.getLocalizadoresBySubinventario(subinventarioCodigo).enqueue(new Callback<List<Localizador>>() {
                @Override
                public void onResponse(Call<List<Localizador>> call, Response<List<Localizador>> response) {
                    if(response.isSuccessful() == true && response.code()==200){

                        mView.hideProgres();
                        mView.fillLocator(response.body());


                    }
                }

                @Override
                public void onFailure(Call<List<Localizador>> call, Throwable t) {
                    mView.showError(t.getMessage());
                }
            });


        }
        /*
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

         */
    }

}
