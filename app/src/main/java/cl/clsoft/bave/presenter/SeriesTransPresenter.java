package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlOnhandQuantities;
import cl.clsoft.bave.model.MtlSerialNumbersInterface;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivitySeriesTrans;

public class SeriesTransPresenter extends BasePresenter {

    private static final String TAG = "SeriesTransPresenter";
    private ActivitySeriesTrans mview;
    private ITransSubinvService mService;

    public SeriesTransPresenter(ActivitySeriesTrans mview, ITransSubinvService mService) {
        this.mview = mview;
        this.mService = mService;
    }

    public List<MtlSerialNumbersInterface> getAllSeries(Long transactionInterfaceId){
        try {
            return this.mService.getAllSeries(transactionInterfaceId);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getSeries(String sigle, String lote, String subinventario, String localizador){
        List<MtlOnhandQuantities> mtlOnhandQuantities;
        try{
             mtlOnhandQuantities = this.mService.getSeries(sigle, lote,subinventario, localizador);
             mview.fillSerie(mtlOnhandQuantities);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void cargaSerie(String articulo, String lote, String subinventario, String localizador, String serie, Long cantidad, Long id) {

        try {
            if (serie.equals("")) {
                mview.showError("Debe ingresar numero de serie");
            }
            else {
                mService.cargaSerie(articulo,lote,subinventario,localizador, serie,cantidad, id);
                mview.showSuccess("Serie ingresada correctamente");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            if (e.getCodigo() == 1) {
                mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mview.showError(e.getDescripcion());
            }

        }

    }
}
