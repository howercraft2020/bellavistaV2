package cl.clsoft.bave.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestPoDistributionsAll;
import cl.clsoft.bave.apis.IRestPoLineLocationsAll;
import cl.clsoft.bave.apis.IRestPoLinesAll;
import cl.clsoft.bave.apis.IRestPoheadersAll;
import cl.clsoft.bave.apis.IRestRcvHeadersInterface;
import cl.clsoft.bave.apis.IRestRcvStatus;
import cl.clsoft.bave.apis.IRestRcvTransactionsInterface;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.dao.rowmapper.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityArticulosRecepcion;
import io.reactivex.Observable;
import io.reactivex.functions.Function4;

public class ArticulosRecepcionPresenter extends BasePresenter {

    private static final String TAG = "ArticulosRecepcionPresenter";
    private ActivityArticulosRecepcion mview;
    private IRecepcionOcService mService;

    //REST API VARIABLES
    private IRestRcvTransactionsInterface iRestRcvTransactionsInterface;
    private IRestRcvHeadersInterface iRestRcvHeadersInterface;
    private IRestPoLinesAll iRestPoLinesAll;
    private IRestPoheadersAll iRestPoheadersAll;
    private IRestPoDistributionsAll iRestPoDistributionsAll;
    private IRestPoLineLocationsAll iRestPoLineLocationsAll;
    private IRestRcvStatus iRestRcvStatus;

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


        //
        iRestRcvHeadersInterface = ApiUtils.getIRestRcvHeadersInterfaceRx();
        iRestRcvTransactionsInterface = ApiUtils.getIRestTransactionInterfaceRx();
        iRestPoLinesAll = ApiUtils.getIRestPoLinesAllRx();
        iRestPoheadersAll = ApiUtils.getIRestPoheadersAllRx();
        iRestPoDistributionsAll = ApiUtils.getIRestPoDistributionsAllRx();
        iRestPoLineLocationsAll = ApiUtils.getIRestPoLineLocationsAllRx();

        Observable<Void> rcvHeadersInterfaceObservable = iRestRcvHeadersInterface.deleteByHeaderInterfaceIdRx(interfaceheaderId);
        Observable<Void> rcvTransactionsInterfaceObservable = iRestRcvTransactionsInterface.deleteByHeaderInterfaceIdRx(interfaceheaderId);
        Observable<Void> poLinesAllObservable = iRestPoLinesAll.deleteByPoHeaderIdRx(poHeaderId);
        Observable<Void> poHeadersAllObservable = iRestPoheadersAll.deleteRx(poHeaderId);
        Observable<Void> poDistributionsAllObservable = iRestPoDistributionsAll.deleteRx(poHeaderId);
        Observable<Void> poLineLocationsAllObservable = iRestPoLineLocationsAll.deleteRx(poHeaderId);


        Observable<Void> result = Observable.zip(
                poLinesAllObservable,
                poHeadersAllObservable,
                poDistributionsAllObservable,
                poLineLocationsAllObservable,
                new Function4< Void, Void, Void, Void, Void>() {
                    @NonNull
                    @Override
                    public Void apply( @NonNull Void unused3, @NonNull Void unused4, @NonNull Void unused5, @NonNull Void unused6) throws Exception {

                    List<Object> a = new ArrayList<>();

                        a.add(unused3);
                        a.add(unused4);
                        a.add(unused5);
                        a.add(unused6);


                        return null;
                    }
                });






       /*
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

        */
    }

    public static void scanMedia(Context context, String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Intent scanFileIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scanFileIntent);
    }


}

