package cl.clsoft.bave.presenter;

import android.media.MediaScannerConnection;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransOrgDetalle;
import cl.clsoft.bave.dao.rowmapper.service.ITransOrgService;
import cl.clsoft.bave.view.ActivityTransOrgDetalle;

public class TransOrgDetallePresenter extends BasePresenter {

    private static final String TAG = "TransOrgDetallePresenter";
    private ActivityTransOrgDetalle mView;
    private ITransOrgService mService;

    public TransOrgDetallePresenter(ActivityTransOrgDetalle mView, ITransOrgService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public List<DatosTransOrgDetalle> getTransferencias (String numeroTraspaso){
        try{
            return mService.getTransferencias(numeroTraspaso);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void crearArchivo(String transactionReference){
        try{
            String archivo = this.mService.crearArchivo(transactionReference);
            MediaScannerConnection.scanFile(mView, new String[] {archivo}, null, null);
            mView.resultadoOkCerrarTransferencia();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
