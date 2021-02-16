package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.service.IConteoCiclicoService;
import cl.clsoft.bave.view.ActivityCiclicoDetalle;
import cl.clsoft.bave.view.ActivityCiclicos;

public class CiclicoDetallePresenter extends BasePresenter {

    private static final String TAG = "CiclicoDetalle";
    private ActivityCiclicoDetalle mView;
    private IConteoCiclicoService mService;


    public CiclicoDetallePresenter(final ActivityCiclicoDetalle mView, final IConteoCiclicoService mService) {
        this.mView = mView;
        this.mService = mService;
    }

    public MtlCycleCountHeaders getDPreViousDetalleCiclicos(Long ciclicoInventarioId){
        try{
            return this.mService.getConteoCiclico(ciclicoInventarioId);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<MtlCycleCountEntries> getSigleInformation(Long idSigleDetalle){
        try{
            return this.mService.getAllSigleInformation(idSigleDetalle);
        } catch(ServiceException e){
            e.printStackTrace();
        }
        return null;
    }

}
