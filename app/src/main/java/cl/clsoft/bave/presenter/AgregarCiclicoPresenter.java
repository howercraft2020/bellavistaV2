package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dao.rowmapper.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityAgregarCiclico;

public class AgregarCiclicoPresenter extends BasePresenter {
    private static final String TAG = "AgregarRecepcionPresenter";
    private ActivityAgregarCiclico mview;
    private IRecepcionOcService mService;

    public AgregarCiclicoPresenter(ActivityAgregarCiclico mview, IRecepcionOcService mService) {
        this.mview = mview;
        this.mService = mService;
    }

}
