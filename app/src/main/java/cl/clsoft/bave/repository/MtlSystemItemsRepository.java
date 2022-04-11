package cl.clsoft.bave.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlSystemItems;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.PoHeadersAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MtlSystemItemsRepository {

    private IRestMtlSystemItems iRestMtlSystemItems;
    private MutableLiveData<List<MtlSystemItems>> mtlSystemItemsMutableLiveData;
    private MutableLiveData<MtlSystemItems> mtlSystemItemMutableLiveData;

    public MtlSystemItemsRepository() {
        iRestMtlSystemItems = ApiUtils.getIRestMtlSystemItems();
        mtlSystemItemsMutableLiveData = new MutableLiveData<>();
        mtlSystemItemMutableLiveData = new MutableLiveData<>();
    }


    public void MtlgetBySegment(String segment){
        iRestMtlSystemItems.getBySegment(segment).enqueue(new Callback<MtlSystemItems>() {
            @Override
            public void onResponse(Call<MtlSystemItems> call, Response<MtlSystemItems> response) {
                if(response.isSuccessful() == true && response.code() == 200){

                    mtlSystemItemMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MtlSystemItems> call, Throwable t) {


                mtlSystemItemMutableLiveData.postValue(null);
            }
        });

    }


    public void MtlgetAllByOcReceipt(Long po_header_id,Long ReceiptNum){


        iRestMtlSystemItems.getAllByOcReceipt(po_header_id,ReceiptNum).enqueue(new Callback<List<MtlSystemItems>>() {
            @Override
            public void onResponse(Call<List<MtlSystemItems>> call, Response<List<MtlSystemItems>> response) {

                if(response.isSuccessful() == true && response.code() == 200){


                    mtlSystemItemsMutableLiveData.postValue(response.body());

                }


            }

            @Override
            public void onFailure(Call<List<MtlSystemItems>> call, Throwable t) {
                mtlSystemItemsMutableLiveData.postValue(null);
            }
        });

    }

    public LiveData<MtlSystemItems> getBySegment(){

        return  mtlSystemItemMutableLiveData;

    }

    public LiveData<List<MtlSystemItems>> getAllByOcReceipt(){


        return mtlSystemItemsMutableLiveData;

    }



}
