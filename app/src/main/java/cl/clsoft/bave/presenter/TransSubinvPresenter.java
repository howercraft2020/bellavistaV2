package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestDatosRecepcion;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.DatosTransSubinv;
import cl.clsoft.bave.dao.rowmapper.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityTransSubinv;


public class TransSubinvPresenter extends BasePresenter {

    private static final String TAG = "TransSubinvPresenter";
    private ActivityTransSubinv mview;
    private ITransSubinvService mService;

    //API
    IRestDatosRecepcion iRestDatosRecepcion;

    public TransSubinvPresenter(ActivityTransSubinv mview, ITransSubinvService mService) {
        this.mview = mview;
        this.mService = mService;
        this.iRestDatosRecepcion = ApiUtils.getIRestDatosRecepcion();
    }

    public List<DatosTransSubinv> getTransSubinv(){
        try{
            return this.mService.getTransSubinv();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

}
