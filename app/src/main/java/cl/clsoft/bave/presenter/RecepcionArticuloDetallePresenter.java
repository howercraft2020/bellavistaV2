package cl.clsoft.bave.presenter;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoLineLocationsAll;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.model.PoLineLocationsAll;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.dao.rowmapper.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityRecepcionArticuloDetalle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecepcionArticuloDetallePresenter extends BasePresenter {


    //VARIABLES REST API
    private IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    private IRestPoLineLocationsAll iRestPoLineLocationsAll;

    private static final String TAG = "RecepcionArticuloDetallePresenter";
    private ActivityRecepcionArticuloDetalle mView;
    private IRecepcionOcService mService;

    public RecepcionArticuloDetallePresenter(ActivityRecepcionArticuloDetalle mView, IRecepcionOcService mService) {
        this.mView = mView;
        this.mService = mService;

        this.iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();
        this.iRestPoLineLocationsAll = ApiUtils.getIRestPoLineLocationsAll();


    }

    public void updateEntry(Long entryId, Double cantidad, Long lineLocationId) {


        Log.d("RecepcionOcService", "RecepcionOcService::updateEntry");
        Log.d("RecepcionOcService", "RecepcionOcService::updateEntry::entryId: " + entryId);
        Log.d("RecepcionOcService", "RecepcionOcService::updateEntry::cantidad: " + cantidad);
        Log.d("RecepcionOcService", "RecepcionOcService::updateEntry::lineLocationId: " + lineLocationId);



        iRestRcvTransactionsInterface.getByInterfaceTransactionId(entryId).enqueue(new Callback<RcvTransactionsInterface>() {
            @Override
            public void onResponse(Call<RcvTransactionsInterface> call, Response<RcvTransactionsInterface> response) {



                if(response.isSuccessful()==true && response.body()!=null && response.code()==200){

                    RcvTransactionsInterface entry = response.body();
                    Log.d("RecepcionOcService", "CONSULTANDO::lineLocationId: " + lineLocationId);
                    iRestPoLineLocationsAll.getById(lineLocationId).enqueue(new Callback<PoLineLocationsAll>() {
                        @Override
                        public void onResponse(Call<PoLineLocationsAll> call, Response<PoLineLocationsAll> response) {
                            Double quantity = 0.0;
                            Double quantityReceived = 0.0;
                            Double quantityCancelled = 0.0;
                            Double qtyRcvTolerance = 0.0;
                            Double qtyPending = 0.0;

                            if(response.isSuccessful() == true && response.body()!=null && response.code()==200){

                                PoLineLocationsAll poLineLocationsAll = response.body();


                                quantity = poLineLocationsAll.getQuantity().doubleValue();
                                quantityReceived = poLineLocationsAll.getQuantityRecived().doubleValue();
                                quantityCancelled = poLineLocationsAll.getQuantityCancelled().doubleValue();
                                qtyRcvTolerance = poLineLocationsAll.getQtyRcvTolerance().doubleValue();
                                qtyPending = (quantity-quantityReceived-quantityCancelled) * (1 + qtyRcvTolerance/100);

                                if (cantidad > qtyPending){
                                    mView.showError("Cantidad no puede ser mayor a : "+qtyPending);
                                }



                                if(cantidad <= qtyPending) {
                                    entry.setQuantity(cantidad);
                                    entry.setPrimaryQuantity(cantidad);
                                    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                                    String strLastUpdate = dateFormat.format(new Date());
                                    entry.setLastUpdatedDate(strLastUpdate.toUpperCase());


                                    iRestRcvTransactionsInterface.insert(entry).enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            mView.showSuccess("Actualización Corecta");
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {

                                        }
                                    });

                                }
                            }

                            if(response.body()==null){

                                String error ="No se encuentra información para esta linea: "+lineLocationId;
                                Log.d("Error", error);
                                mView.showError(error);

                            }

                        }

                        @Override
                        public void onFailure(Call<PoLineLocationsAll> call, Throwable t) {
                            mView.showError(t.getMessage());
                        }
                    });


                }

                if(response.body()==null){

                    String error ="Entry " + entryId + " no existe.";
                    Log.d("Error", error);
                    mView.showError(error);

                }

            }

            @Override
            public void onFailure(Call<RcvTransactionsInterface> call, Throwable t) {

                mView.showError(t.getMessage());

            }
        });


    }

    public void deleteTransactionsInterfaceById(Long interfaceTransactionId) {
        Log.d("RecepcionOcService", "RecepcionOcServiceImpl::deleteTransactionsInterfaceById");
        Log.d("RecepcionOcService", "RecepcionOcServiceImpl::deleteTransactionsInterfaceById::interfaceTransactionId: " + interfaceTransactionId);

        iRestRcvTransactionsInterface.delete(interfaceTransactionId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful() == true && response.code()==200){

                    mView.showSuccess("Línea eliminada correctamente");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                mView.showError(t.getMessage());

            }
        });


    }
}
