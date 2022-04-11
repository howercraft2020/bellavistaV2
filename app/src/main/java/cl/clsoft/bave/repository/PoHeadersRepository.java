package cl.clsoft.bave.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoheadersAll;
import cl.clsoft.bave.model.PoHeadersAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoHeadersRepository {

    private IRestPoheadersAll iRestPoheadersAll;
    private MutableLiveData<List<PoHeadersAll>> poHeadersAllMutableLiveData;
    private MutableLiveData<PoHeadersAll> poHeadersAllsingle;

    public PoHeadersRepository() {

        poHeadersAllMutableLiveData = new MutableLiveData<>();
        poHeadersAllsingle = new MutableLiveData<>();
        iRestPoheadersAll = ApiUtils.getIRestPoheadersAll();

    }

    public void PoHeadersgetAll(){
        iRestPoheadersAll.getAll().enqueue(new Callback<List<PoHeadersAll>>() {
            @Override
            public void onResponse(Call<List<PoHeadersAll>> call, Response<List<PoHeadersAll>> response) {
                Log.d("PO", "NO FALLO"+response.message());
            if(response.isSuccessful()){
                poHeadersAllMutableLiveData.postValue(response.body());

            }

            }

            @Override
            public void onFailure(Call<List<PoHeadersAll>> call, Throwable t) {
                Log.d("PO", "FALLO"+t.getMessage());
                poHeadersAllMutableLiveData.postValue(null);
            }
        });

    }
    public void PoHeadergetById(Long po_header_id){
        iRestPoheadersAll.getbyId(po_header_id).enqueue(new Callback<PoHeadersAll>() {
            @Override
            public void onResponse(Call<PoHeadersAll> call, Response<PoHeadersAll> response) {
              if(response.isSuccessful() == true && response.code()==200){

                  poHeadersAllsingle.postValue(response.body());

              }

            }

            @Override
            public void onFailure(Call<PoHeadersAll> call, Throwable t) {
                poHeadersAllsingle.postValue(null);
            }
        });



    }

    public LiveData<List<PoHeadersAll>> getAllLiveData(){
        if(poHeadersAllMutableLiveData == null){

            poHeadersAllMutableLiveData = new MutableLiveData<>();
        }

        return poHeadersAllMutableLiveData;
    }

    public LiveData<PoHeadersAll> getByIdLiveData(){

        if(poHeadersAllsingle == null){

            poHeadersAllsingle = new MutableLiveData<>();
        }
        return poHeadersAllsingle;

    }

}
