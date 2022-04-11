package cl.clsoft.bave.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestRcvShipmentHeaders;
import cl.clsoft.bave.model.RcvShipmentHeaders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RcvShipmentHeadersRepository {


    private IRestRcvShipmentHeaders iRestRcvShipmentHeaders;
    private MutableLiveData<List<RcvShipmentHeaders>> listMutableLiveData;
    private MutableLiveData<RcvShipmentHeaders> mutableLiveData;


    public RcvShipmentHeadersRepository() {

        iRestRcvShipmentHeaders = ApiUtils.getIRestRcvShipmentHeaders();
        listMutableLiveData = new MutableLiveData<>();
        mutableLiveData = new MutableLiveData<>();
    }


    public void ShipmentgetAll(){

        iRestRcvShipmentHeaders.getAll().enqueue(new Callback<List<RcvShipmentHeaders>>() {
            @Override
            public void onResponse(Call<List<RcvShipmentHeaders>> call, Response<List<RcvShipmentHeaders>> response) {
                if(response.isSuccessful()==true && response.code() == 200){

                    listMutableLiveData.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<RcvShipmentHeaders>> call, Throwable t) {

            }
        });

    }

    public void Shipmentget(Long shipmentHeaderId){


        iRestRcvShipmentHeaders.get(shipmentHeaderId).enqueue(new Callback<RcvShipmentHeaders>() {
            @Override
            public void onResponse(Call<RcvShipmentHeaders> call, Response<RcvShipmentHeaders> response) {
                if(response.isSuccessful()==true && response.code() == 200){

                    mutableLiveData.postValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<RcvShipmentHeaders> call, Throwable t) {

            }
        });

    }

    public void insert(RcvShipmentHeaders rcvShipmentHeaders){


        iRestRcvShipmentHeaders.insert(rcvShipmentHeaders).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public LiveData<List<RcvShipmentHeaders>> getAll(){

        return listMutableLiveData;

    }

    public LiveData<RcvShipmentHeaders> get(){

        return mutableLiveData;

    }


}

