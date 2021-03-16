package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.service.ITransOrgService;
import cl.clsoft.bave.view.ActivityTransOrgResumen;

public class TransOrgResumenPresenter extends BasePresenter {

    private static final String TAG = "TransOrgResumenPresenter";
    private ActivityTransOrgResumen mView;
    private ITransOrgService mService;

    public TransOrgResumenPresenter(ActivityTransOrgResumen mView, ITransOrgService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mView.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void insertarDatos(String id, String nroTraspaso, String articulo, String lote, String subinventario, String localizador, Double cantidad, String glosa, List<String> series, String orgDestino) {
        try{
            mService.insertarDatos(id,nroTraspaso,articulo,lote,subinventario,localizador,cantidad,glosa,series,orgDestino);
            mView.resultadoOkAddTransaction();
            }catch (ServiceException e) {
            e.printStackTrace();
            if (e.getCodigo() == 1) {
                mView.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                mView.showError(e.getDescripcion());
            }
        }
    }
}
