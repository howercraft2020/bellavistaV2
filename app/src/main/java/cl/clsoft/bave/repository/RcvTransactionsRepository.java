package cl.clsoft.bave.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestRcvTransactions;
import cl.clsoft.bave.model.RcvTransactions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RcvTransactionsRepository {

    private IRestRcvTransactions iRestRcvTransactions;
    private MutableLiveData<List<RcvTransactions>> listMutableLiveData;
    private MutableLiveData<RcvTransactions> mutableLiveData;


    public RcvTransactionsRepository(){

        iRestRcvTransactions = ApiUtils.getIRestRcvTransactions();
        listMutableLiveData = new MutableLiveData<>();
        mutableLiveData = new MutableLiveData<>();

    }




}
