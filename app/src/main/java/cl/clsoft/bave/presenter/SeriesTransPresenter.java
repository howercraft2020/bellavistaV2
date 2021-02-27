package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivitySeriesTrans;

public class SeriesTransPresenter extends BasePresenter {

    private static final String TAG = "SeriesTransPresenter";
    private ActivitySeriesTrans mview;
    private ITransSubinvService mService;

    public SeriesTransPresenter(ActivitySeriesTrans mview, ITransSubinvService mService) {
        this.mview = mview;
        this.mService = mService;
    }
}
