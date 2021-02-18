package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityAgregarRecepcion;

public class AgregarRecepcionPresenter extends BasePresenter {

    private static final String TAG = "AgregarRecepcionPresenter";
    private ActivityAgregarRecepcion mview;
    private IRecepcionOcService mService;

    public AgregarRecepcionPresenter(ActivityAgregarRecepcion mview, IRecepcionOcService mService) {
        this.mview = mview;
        this.mService = mService;
    }

    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Long cantidad) {

        try{

            if(segment1.equals("")) {
                mview.showError("Debe Ingresar un Codigo Sigle");
            }
            else if(cantidad == 0){
                mview.showError("Debe Ingresar una cantidad mayor que 0");
            }
            else {
                this.mService.cargaRecepcion(segment1, poHeaderId, oc, receiptNum, cantidad);
                mview.showSuccess("Registro ingresado correctamente");
            }
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
