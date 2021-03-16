package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransOrg;
import cl.clsoft.bave.service.ITransOrgService;
import cl.clsoft.bave.view.ActivityTransOrg;

public class TransOrgPresenter extends BasePresenter {

    private static final String TAG = "TransOrgPresenter";
    private ActivityTransOrg mView;
    private ITransOrgService mService;

    public TransOrgPresenter(ActivityTransOrg mView, ITransOrgService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public List<DatosTransOrg> getTransSubinv(){
        try{
            return this.mService.getTransSubinv();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
