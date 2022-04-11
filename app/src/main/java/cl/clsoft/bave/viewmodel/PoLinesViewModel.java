package cl.clsoft.bave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.repository.PoLineRepository;

public class PoLinesViewModel  extends AndroidViewModel {

    private PoLineRepository poLineRepository;
    private LiveData<List<PoLinesAll>> PoLinesLiveData;


    public PoLinesViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        poLineRepository = new PoLineRepository();
        PoLinesLiveData = poLineRepository.getLines();

    }

    public void PoLinesgetLines(Long inventoryItemId,Long poHeaderId){
        poLineRepository.PoLinesgetLines(inventoryItemId,poHeaderId);

    }
    public LiveData<List<PoLinesAll>> getLines(){
        return PoLinesLiveData;
    }

}
