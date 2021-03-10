package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityTransSubinvResumen;

public class TransSubinvResumenPresenter extends BasePresenter {

    private static final String TAG = "TransSubinvResumenPresenter";
    private ActivityTransSubinvResumen mview;
    private ITransSubinvService mservice;

    public TransSubinvResumenPresenter(ActivityTransSubinvResumen mview, ITransSubinvService mservice) {
        this.mview = mview;
        this.mservice = mservice;
    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mservice.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Long cantidad,
                              String subinventarioHasta, String localizadorHasta, List<String> series) {

        try {
                mservice.insertarDatos(id,nroTraspaso, articulo,lote, subinventario, localizador,cantidad,subinventarioHasta,localizadorHasta, series);
                mview.resultadoOkAddTransaction();

        }catch (ServiceException e) {
            e.printStackTrace();
            if (e.getCodigo() == 1) {
                mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mview.showError(e.getDescripcion());
            }
        }

    }
}
