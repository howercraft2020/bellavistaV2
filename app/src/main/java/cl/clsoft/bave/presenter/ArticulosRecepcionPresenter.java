package cl.clsoft.bave.presenter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
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

    public void crearArchivo(Long interfaceheaderId, String numeroOc, Long receiptNum, Long poHeaderId, String comentario, Long groupId) {
        try{
             String archivo = this.mService.crearArchivo(interfaceheaderId,numeroOc,receiptNum, poHeaderId, comentario, groupId);
             MediaScannerConnection.scanFile(mview, new String[] {archivo}, null, null);
             mview.resultadoOkCerrarRecepcion();
        }catch (ServiceException e) {
            e.printStackTrace();
            if (e.getCodigo() == 1) {
                mview.showError(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mview.showError(e.getDescripcion());
            }
        }
    }

    public static void scanMedia(Context context, String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Intent scanFileIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scanFileIntent);
    }


}

