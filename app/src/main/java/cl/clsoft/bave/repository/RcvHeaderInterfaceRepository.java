package cl.clsoft.bave.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.model.RcvHeadersInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RcvHeaderInterfaceRepository {

    private IRestRcvHeadersInterface iRestRcvHeadersInterface;
    private MutableLiveData<List<RcvHeadersInterface>> RcvHeadersMutableLiveData;

    public RcvHeaderInterfaceRepository() {

        iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterface();
        RcvHeadersMutableLiveData = new MutableLiveData<>();

    }

    public void insert(RcvHeadersInterface rcvHeadersInterface){
        iRestRcvHeadersInterface.insert(rcvHeadersInterface).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("HEADER", "INSERTA STATUS CODE:"+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("HEADER", "INSERTA FALLO MENSAJE:"+t.getMessage());
            }
        });

    }

}
