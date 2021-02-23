package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityAgregarTransSubinv;

public class AgregarTransSubinvPresenter extends BasePresenter {

    private static final String TAG = "AgregarTransSubinvPresenter";
    private ActivityAgregarTransSubinv mview;
    private ITransSubinvService mService;

    public AgregarTransSubinvPresenter(ActivityAgregarTransSubinv mview, ITransSubinvService mService) {
        this.mview = mview;
        this.mService = mService;
    }

    public void cargaTransferencia(String articulo, String lote, String subinventario){
            try {
                this.mService.cargaTransferencia(articulo,lote, subinventario);
            }catch (ServiceException e){
            e.printStackTrace();
            if(e.getCodigo() == 1){
                mview.showWarning(e.getDescripcion());
            }else if (e.getCodigo() == 2){
                mview.showError(e.getDescripcion());
            }
        }
    }
}
