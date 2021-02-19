package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.service.IConteoCiclicoService;
import cl.clsoft.bave.view.ActivityCiclicoDetalle;
import cl.clsoft.bave.view.ActivityCiclicoSigleDetalle;

public class CiclicoSigleDetallePresenter extends BasePresenter {
    private static final String TAG = "CiclicoSigleDetalle";
    private ActivityCiclicoSigleDetalle mView;
    private IConteoCiclicoService mService;


    public CiclicoSigleDetallePresenter(final ActivityCiclicoSigleDetalle mView, final IConteoCiclicoService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public MtlCycleCountEntries getPreViousDetalleCiclicoSigle(Long idSigleDetalle){
        try{
            return this.mService.getCiclicoSigleDetalle(idSigleDetalle);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<MtlCycleCountEntries> getSigleInformation(Long idSigle){
        try{
            return this.mService.getAllSigleInformation(idSigle);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }


}
