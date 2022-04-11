package cl.clsoft.bave.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoLinesAll;
import cl.clsoft.bave.model.PoLinesAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoLineRepository {

    private IRestPoLinesAll iRestPoLinesAll;
    private MutableLiveData<List<PoLinesAll>> PolinesMutableLiveData;

    public PoLineRepository() {

      iRestPoLinesAll = ApiUtils.getIRestPoLinesAll();
      PolinesMutableLiveData = new MutableLiveData<>();


    }

    public void PoLinesgetLines(Long inventoryItemId,Long poHeaderId){

        iRestPoLinesAll.getLines(inventoryItemId,poHeaderId).enqueue(new Callback<List<PoLinesAll>>() {
            @Override
            public void onResponse(Call<List<PoLinesAll>> call, Response<List<PoLinesAll>> response) {
                if(response.isSuccessful() == true && response.code() == 200){

                    PolinesMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PoLinesAll>> call, Throwable t) {
                PolinesMutableLiveData.postValue(null);
            }
        });


    }

    public LiveData<List<PoLinesAll>> getLines(){

        return PolinesMutableLiveData;

    }


}
