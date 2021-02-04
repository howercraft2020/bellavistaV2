package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityFisicos;

public class FisicosPresenter extends BasePresenter {

    private static final String TAG = "FisicosPresenter";
    private ActivityFisicos mView;
    private IInventarioFisicoService mService;

    public FisicosPresenter(@NonNull final ActivityFisicos mView, @NonNull final  IInventarioFisicoService mService) {
        this.mView = mView;
        this.mService = mService;
    }
}
