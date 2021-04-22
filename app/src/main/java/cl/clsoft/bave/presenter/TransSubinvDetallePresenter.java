package cl.clsoft.bave.presenter;

import android.media.MediaScannerConnection;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinvDetalle;
import cl.clsoft.bave.model.MtlTransactionsInterface;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityTransSubinvDetalle;

public class TransSubinvDetallePresenter extends BasePresenter {

    private static final String TAG = "TransSubinvPresenter";
    private ActivityTransSubinvDetalle mview;
    private ITransSubinvService mservice;

    public TransSubinvDetallePresenter(ActivityTransSubinvDetalle mview, ITransSubinvService mservice) {
        this.mview = mview;
        this.mservice = mservice;
    }

    public List<DatosTransSubinvDetalle> getTransferencias (String numeroTraspaso){
        try{
            return mservice.getTransferencias(numeroTraspaso);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void crearArchivo(String transactionReference){
        try{
            String archivo = this.mservice.crearArchivo(transactionReference);
            MediaScannerConnection.scanFile(mview, new String[] {archivo}, null, null);
            mview.resultadoOkCerrarTransferencia();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
