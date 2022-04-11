package cl.clsoft.bave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cl.clsoft.bave.model.RcvShipmentHeaders;
import cl.clsoft.bave.repository.RcvShipmentHeadersRepository;

public class RcvShipmentHeadersViewModel extends AndroidViewModel {

    private RcvShipmentHeadersRepository rcvShipmentHeadersRepository;
    private LiveData<List<RcvShipmentHeaders>> listLiveData;
    private LiveData<RcvShipmentHeaders> liveData;

    public RcvShipmentHeadersViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        rcvShipmentHeadersRepository = new RcvShipmentHeadersRepository();
        listLiveData = rcvShipmentHeadersRepository.getAll();
        liveData = rcvShipmentHeadersRepository.get();

    }


    public void Shipmentget(Long shipmentHeaderId){
        rcvShipmentHeadersRepository.Shipmentget(shipmentHeaderId);

    }

    public void ShipmentgetAll(){

        rcvShipmentHeadersRepository.ShipmentgetAll();
    }


    public LiveData<List<RcvShipmentHeaders>> getAll(){

      return   rcvShipmentHeadersRepository.getAll();
    }

    public LiveData<RcvShipmentHeaders> get(){

        return rcvShipmentHeadersRepository.get();
    }

    public void insert(RcvShipmentHeaders rcvShipmentHeaders){

        rcvShipmentHeadersRepository.insert(rcvShipmentHeaders);
    }

}
