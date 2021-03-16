package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.service.ITransOrgService;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivitySeriesTransOrg;

public class SeriesTransOrgPresenter extends BasePresenter {

    private static final String TAG = "SeriesTransOrgPresenter";
    private ActivitySeriesTransOrg mView;
    private ITransOrgService mService;

    public SeriesTransOrgPresenter(ActivitySeriesTransOrg mView, ITransOrgService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public void getSeries(String sigle, String lote, String subinventario, String localizador){
        List<MtlOnhandQuantities> mtlOnhandQuantities;
        try{
            mtlOnhandQuantities = this.mService.getSeries(sigle, lote,subinventario, localizador);
            //mView.fillSerie(mtlOnhandQuantities);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public MtlOnhandQuantities getSerieIngresada(String sigle, String lote, String subinventario, String localizador, String serie){

        try{
            return this.mService.getSerieIngresada(sigle,lote,subinventario, localizador,serie);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
