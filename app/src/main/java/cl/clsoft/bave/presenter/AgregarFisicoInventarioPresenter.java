package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityAgregarFisicoInventario;

public class AgregarFisicoInventarioPresenter extends BasePresenter {
    private static final String TAG = "AgregarFisicoInventarioPresenter";
    private ActivityAgregarFisicoInventario mView;
    private IInventarioFisicoService mService;

    public AgregarFisicoInventarioPresenter(@NonNull final ActivityAgregarFisicoInventario mView, @NonNull final IInventarioFisicoService mService){
        this.mView = mView;
        this.mService = mService;
    }

}
