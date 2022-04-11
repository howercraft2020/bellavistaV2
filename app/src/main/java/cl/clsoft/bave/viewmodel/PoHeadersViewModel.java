package cl.clsoft.bave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.repository.PoHeadersRepository;

public class PoHeadersViewModel extends AndroidViewModel {


    private PoHeadersRepository poHeadersRepository;
    private LiveData<List<PoHeadersAll>> poHeadersLiveData;

    public PoHeadersViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){

        poHeadersRepository = new PoHeadersRepository();
        poHeadersLiveData = poHeadersRepository.getAllLiveData();
    }

    public void PoHeadersgetAll(){
        poHeadersRepository.PoHeadersgetAll();
    }


    public LiveData<List<PoHeadersAll>> getAllLiveData(){
        if(poHeadersLiveData == null){

        this.PoHeadersgetAll();
        }
        return poHeadersLiveData;
    }




}
