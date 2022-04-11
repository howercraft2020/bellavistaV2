package cl.clsoft.bave.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RcvTransactionsInterfaceRepository {

    private IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    private MutableLiveData<List<RcvTransactionsInterface>> rcvHeaderInterfaceAllMutableLiveData;

    private MutableLiveData<List<RcvTransactionsInterface>> rcvgetAllMutableLiveData;

    public RcvTransactionsInterfaceRepository() {
        iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterface();

        rcvHeaderInterfaceAllMutableLiveData = new MutableLiveData<>();
    }
    public void RcvgetAllByHeader(Long headerInterfaceId){

        iRestRcvTransactionsInterface.getAllByHeaderV2(headerInterfaceId).enqueue(new Callback<List<RcvTransactionsInterface>>() {
            @Override
            public void onResponse(Call<List<RcvTransactionsInterface>> call, Response<List<RcvTransactionsInterface>> response) {

            if(response.isSuccessful() == true && response.code() == 200){

              rcvHeaderInterfaceAllMutableLiveData.postValue(response.body());
            }


            }

            @Override
            public void onFailure(Call<List<RcvTransactionsInterface>> call, Throwable t) {
                rcvHeaderInterfaceAllMutableLiveData.postValue(null);
            }
        });

    }
    public void RcvgetArticulos(Long id){

        iRestRcvTransactionsInterface.getArticulos(id).enqueue(new Callback<List<RcvTransactionsInterface>>() {
            @Override
            public void onResponse(Call<List<RcvTransactionsInterface>> call, Response<List<RcvTransactionsInterface>> response) {


            if(response.isSuccessful() == true && response.code() == 200){

                rcvgetAllMutableLiveData.postValue(response.body());
            }


            }

            @Override
            public void onFailure(Call<List<RcvTransactionsInterface>> call, Throwable t) {

            }
        });


    }


    public void RcvInsert(RcvTransactionsInterface rcvTransactionsInterface){

        iRestRcvTransactionsInterface.insert(rcvTransactionsInterface).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("TRX", "INSERTA STATUS CODE:"+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TRX", "INSERTA FALLO MENSAJE:"+t.getMessage());
            }
        });

    }

    public void RcvDelete(Long interfaceTransactionId ){

        iRestRcvTransactionsInterface.delete(interfaceTransactionId);
    }

    public LiveData<List<RcvTransactionsInterface>> getAllByHeader(){

        return rcvHeaderInterfaceAllMutableLiveData;
    }

    public LiveData<List<RcvTransactionsInterface>> getAllArticulos(){

        return rcvgetAllMutableLiveData;
    }


}
