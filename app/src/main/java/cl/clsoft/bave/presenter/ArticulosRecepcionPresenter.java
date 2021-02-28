package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.RcvTransactionsInterface;
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

    public List<RcvTransactionsInterface> getAllArticulos(Long headerInterfaceId){
        try{
            return this.mService.getAllArticulos(headerInterfaceId);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void crearArchivo(Long interfaceheaderId, String numeroOc, Long receiptNum) {
        try{
             this.mService.crearArchivo(interfaceheaderId,numeroOc,receiptNum);
             this.mview.showSuccess("Archivo creado correctamente");
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
