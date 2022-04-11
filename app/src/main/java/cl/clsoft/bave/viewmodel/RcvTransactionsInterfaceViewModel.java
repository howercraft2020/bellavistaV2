package cl.clsoft.bave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.repository.RcvTransactionsInterfaceRepository;

public class RcvTransactionsInterfaceViewModel extends AndroidViewModel {


    private RcvTransactionsInterfaceRepository rcvTransactionsInterfaceRepository;
    private LiveData<List<RcvTransactionsInterface>> rcvHeaderInterfaceAllLiveData;

    private LiveData<List<RcvTransactionsInterface>> rcvgetAllLiveData;


    public RcvTransactionsInterfaceViewModel(@NonNull Application application) {
        super(application);
    }


    public void init(){

        rcvTransactionsInterfaceRepository = new RcvTransactionsInterfaceRepository();
        rcvHeaderInterfaceAllLiveData = rcvTransactionsInterfaceRepository.getAllByHeader();
        rcvgetAllLiveData = rcvTransactionsInterfaceRepository.getAllArticulos();
    }

    public void RcvgetAllByHeader(Long headerInterfaceId){

        rcvTransactionsInterfaceRepository.RcvgetAllByHeader(headerInterfaceId);

    }

    public void RcvgetArticulos(Long id){

        rcvTransactionsInterfaceRepository.RcvgetArticulos(id);

    }

    public void RcvInsert(RcvTransactionsInterface rcvTransactionsInterface){

        rcvTransactionsInterfaceRepository.RcvInsert(rcvTransactionsInterface);
    }
    public void RcvDelete(Long interfaceTransactionId ){

        rcvTransactionsInterfaceRepository.RcvDelete(interfaceTransactionId);

    }

    public LiveData<List<RcvTransactionsInterface>> getAllByHeader(){

        return rcvHeaderInterfaceAllLiveData;
    }

    public LiveData<List<RcvTransactionsInterface>> getAllArticulos(){

        return rcvgetAllLiveData;

    }


}
