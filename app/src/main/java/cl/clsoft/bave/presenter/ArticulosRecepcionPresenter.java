package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityArticulosRecepcion;

public class ArticulosRecepcionPresenter extends BasePresenter {

    private static final String TAG = "ArticulosRecepcionPresenter";
    private ActivityArticulosRecepcion mview;
    private IRecepcionOcService mService;

    public ArticulosRecepcionPresenter(ActivityArticulosRecepcion mview, IRecepcionOcService mService) {
        this.mview = mview;
        this.mService = mService;
    }
}
