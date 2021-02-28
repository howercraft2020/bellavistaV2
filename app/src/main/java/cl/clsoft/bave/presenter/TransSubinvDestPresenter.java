package cl.clsoft.bave.presenter;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.service.ITransSubinvService;
import cl.clsoft.bave.view.ActivityTransSubinvDest;

public class TransSubinvDestPresenter extends BasePresenter {

    private static final String TAG = "TransSubinvDestPresenter";
    private ActivityTransSubinvDest mview;
    private ITransSubinvService mservice;

    public TransSubinvDestPresenter(ActivityTransSubinvDest mview, ITransSubinvService mservice) {
        this.mview = mview;
        this.mservice = mservice;
    }

    public void insertarDatos(String articulo, String lote, String subinventario, String localizador, Long cantidad,
                              String subinventarioHasta, String localizadorHasta) {

        try {

            if(subinventarioHasta.equals("")){
                mview.showError("Debe ingresar Subinventario");
            }
            else {
                mservice.insertarDatos(articulo,lote, subinventario, localizador,cantidad,subinventarioHasta,localizadorHasta);
                mview.showSuccess("Datos cargados correctamente");
            }

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
