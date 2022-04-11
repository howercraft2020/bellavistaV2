package cl.clsoft.bave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cl.clsoft.bave.model.RcvHeadersInterface;
import cl.clsoft.bave.repository.RcvHeaderInterfaceRepository;

public class RcvHeaderInterfaceViewModel extends AndroidViewModel {


    private RcvHeaderInterfaceRepository rcvHeaderInterfaceRepository;
    private LiveData<List<RcvHeadersInterface>> rcvHeadersLiveData;

    public RcvHeaderInterfaceViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        rcvHeaderInterfaceRepository = new RcvHeaderInterfaceRepository();

    }
    public void insert(RcvHeadersInterface rcvHeadersInterface){

        rcvHeaderInterfaceRepository.insert(rcvHeadersInterface);
    }


}
