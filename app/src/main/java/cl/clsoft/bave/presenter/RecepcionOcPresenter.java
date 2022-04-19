package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.PoHeadersAll;
import cl.clsoft.bave.dao.rowmapper.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityRecepcionOc;

public class RecepcionOcPresenter extends BasePresenter {

    private static final String TAG = "RecepcionOcPresenter";
    private ActivityRecepcionOc mview;
    private IRecepcionOcService mService;

    public RecepcionOcPresenter(@NonNull final ActivityRecepcionOc mview, @NonNull final IRecepcionOcService mService){
        this.mview = mview;
        this.mService = mService;
    }

    public List<PoHeadersAll> getRecepcionesOc(){
        try{
            return this.mService.getAllRecepcionesOc();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

}
