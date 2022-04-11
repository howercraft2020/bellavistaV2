package cl.clsoft.bave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.repository.MtlSystemItemsRepository;

public class MtlSystemItemsViewModel extends AndroidViewModel {

    private MtlSystemItemsRepository mtlSystemItemsRepository;
    private LiveData<MtlSystemItems> LiveDataItem;
    private LiveData<List<MtlSystemItems>> LiveDataItems;

    public MtlSystemItemsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){

        mtlSystemItemsRepository = new MtlSystemItemsRepository();
        LiveDataItem = mtlSystemItemsRepository.getBySegment();
        LiveDataItems = mtlSystemItemsRepository.getAllByOcReceipt();
    }

    public void MtlgetBySegment(String segment){

        mtlSystemItemsRepository.MtlgetBySegment(segment);
    }

    public LiveData<MtlSystemItems> getBySegment(){

        return LiveDataItem;
    }

    public void MtlgetAllByOcReceipt(Long po_header_id,Long ReceiptNum){

        mtlSystemItemsRepository.MtlgetAllByOcReceipt(po_header_id,ReceiptNum);

    }
    public LiveData<List<MtlSystemItems>> getAllByOcReceipt(){

        return LiveDataItems;

    }


}
