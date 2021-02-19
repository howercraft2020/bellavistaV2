package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityTransSubinv;

public class TransSubinvPresenter extends BasePresenter {

    private static final String TAG = "TransSubinvPresenter";
    private ActivityTransSubinv mview;
    private ITransSubinvService mService;

    public TransSubinvPresenter(ActivityTransSubinv mview, ITransSubinvService mService) {
        this.mview = mview;
        this.mService = mService;
    }

    public List<MtlTransactionsInterface> getTransSubinv(){
        try{
            return this.mService.getTransSubinv();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

}
