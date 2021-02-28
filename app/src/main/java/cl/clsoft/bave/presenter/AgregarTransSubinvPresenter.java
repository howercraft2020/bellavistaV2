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

    public boolean cargaTransferencia(String articulo, String lote, String subinventario, String localizador, Long cantidad){

        boolean ok = true;

            try {

                if (articulo.equals("")){
                    mview.showError("Debe ingresar codigo Sigle");
                    ok = false;
                }
                else if (cantidad == 0){
                    mview.showError("Debe Ingresar una cantidad mayor a 0");
                    ok = false;
                }
                else if (subinventario.equals("")){
                    mview.showError("Debe ingresar un subinventario");
                    ok = false;
                }
                else {
                    this.mService.cargaTransferencia(articulo, lote, subinventario, localizador, cantidad);
                    ok = false;
                }
            }catch (ServiceException e){
            e.printStackTrace();
            if(e.getCodigo() == 1){
                mview.showWarning(e.getDescripcion());
            }else if (e.getCodigo() == 2){
                mview.showError(e.getDescripcion());
            }
            ok = false;
        }
        return ok;
    }
}
