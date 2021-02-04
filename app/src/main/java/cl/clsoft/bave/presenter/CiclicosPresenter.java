package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IConteoCiclicoService;
import cl.clsoft.bave.view.ActivityCiclicos;

public class CiclicosPresenter extends BasePresenter {

    private static final String TAG = "CiclicosPresenter";
    private ActivityCiclicos mView;
    private IConteoCiclicoService mService;

    public CiclicosPresenter(@NonNull final ActivityCiclicos mView, @NonNull final IConteoCiclicoService mService) {
        this.mView = mView;
        this.mService = mService;
    }

}
