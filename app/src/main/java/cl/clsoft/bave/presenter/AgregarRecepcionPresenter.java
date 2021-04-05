package cl.clsoft.bave.presenter;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.dao.IMtlSystemItemsDao;
import cl.clsoft.bave.dao.ISubinventarioDao;
import cl.clsoft.bave.dao.impl.MtlSystemItemsDaoImpl;
import cl.clsoft.bave.dao.impl.SubinventarioDaoImpl;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.PoLinesAll;
import cl.clsoft.bave.model.RcvTransactionsInterface;
import cl.clsoft.bave.model.Subinventario;
import cl.clsoft.bave.service.IRecepcionOcService;
import cl.clsoft.bave.view.ActivityAgregarRecepcion;

public class AgregarRecepcionPresenter extends BasePresenter {

    private static final String TAG = "AgregarRecepcionPresenter";
    private ActivityAgregarRecepcion mview;
    private IRecepcionOcService mService;

    public AgregarRecepcionPresenter(ActivityAgregarRecepcion mview, IRecepcionOcService mService) {
        this.mview = mview;
        this.mService = mService;
    }

    public void cargaRecepcion(String segment1, Long poHeaderId, String oc, Long receiptNum, Double cantidad, Long poLineNum) {

        try{

            if(segment1.equals("")) {
                mview.showError("Debe Ingresar un Codigo Sigle");
            }
            else if(cantidad == 0){
                mview.showError("Debe Ingresar una cantidad mayor que 0");
            }
            else {
                this.mService.cargaRecepcion(segment1, poHeaderId, oc, receiptNum, cantidad,poLineNum);
                mview.resultadoOkAddTransaction();
            }
        }catch (ServiceException e){
            e.printStackTrace();
            if(e.getCodigo() == 1){
                mview.showWarning(e.getDescripcion());
            }else if (e.getCodigo() == 2){
                mview.showError(e.getDescripcion());
            }
        }
    }

    public MtlSystemItems getMtlSystemItemsBySegment(String segment) {
        try {
            return this.mService.getMtlSystemItemsBySegment(segment);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        }
        return null;
    }

    public void getArticulosByOcReceipt(Long poHeaderId, Long receiptNum){
        List<MtlSystemItems> articulos;
        try{
            articulos  = this.mService.getArticulosByOcReceipt(poHeaderId,receiptNum);
            mview.fillSigle(articulos);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public List<PoLinesAll> getLines(Long inventoryItemId, Long poHeaderId){
        try{
            return this.mService.getLines(inventoryItemId,poHeaderId);
            //mview.fillLines(lineas);
        } catch(ServiceException e){
            if (e.getCodigo() == 1) {
                this.mview.showWarning(e.getDescripcion());
            } else if (e.getCodigo() == 2) {
                this.mview.showError(e.getDescripcion());
            }
        }
        return null;
    }


}
