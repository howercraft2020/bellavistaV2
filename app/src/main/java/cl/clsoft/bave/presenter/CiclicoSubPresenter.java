package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlCycleCountEntries;
import cl.clsoft.bave.model.MtlCycleCountHeaders;
import cl.clsoft.bave.model.MtlSystemItems;
import cl.clsoft.bave.model.OrganizacionPrincipal;
import cl.clsoft.bave.dao.rowmapper.service.IConteoCiclicoService;
import cl.clsoft.bave.task.TaskExecutor;
import cl.clsoft.bave.view.ActivityCiclicoSub;

public class CiclicoSubPresenter extends BasePresenter {

    private static final String TAG = "ConteoCiclico";
    private ActivityCiclicoSub mView;
    private IConteoCiclicoService mService;
    private TaskExecutor mTaskExecutor;






    public CiclicoSubPresenter(@NonNull final ActivityCiclicoSub view, @NonNull final TaskExecutor taskExecutor, @NonNull final IConteoCiclicoService service) {
        this.mView = view;
        this.mTaskExecutor = taskExecutor;
        this.mService = service;

    }

    public MtlCycleCountHeaders getMtlCycleCountHeaders(Long id) {
        try {
            return mService.getConteoCiclico(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadSubinventories(Long id) {
        mView.showProgres("Cargando subinventarios...");
        //mTaskExecutor.async(new CiclicoSubPresenter.GetSubinventories(id));

    }

    public void closeConteoCiclico(Long id, MtlCycleCountHeaders header, List<MtlCycleCountEntries> entries, OrganizacionPrincipal organizacionPrincipal, MtlSystemItems mtlSystemItems) throws ServiceException {
        mView.showProgres("Cerrando conteo ciclico...");
        //mTaskExecutor.async(new CiclicoSubPresenter.CloseConteoCiclico(id));
        new CiclicoSubPresenter.CloseConteoCiclico(id,header,entries,organizacionPrincipal,mtlSystemItems).cerrar_conteo();

    }

    private class GetSubinventories  {

        private Long id;

        //REST API


        public GetSubinventories(Long id) {
            this.id = id;

        }




        /*
        @Override
        public List<Subinventario> execute() {




            Log.d(TAG, "GetSubinventories::execute");
            List<Subinventario> subinventarios = new ArrayList<>();
            try {
                subinventarios = mService.getAllSubinventariosByConteoCiclico(this.id);
            } catch (ServiceException e) {
                Log.d(TAG, "GetSubinventories::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return subinventarios;
        }

        @Override
        public void onPostExecute(@Nullable List<Subinventario> result) {
            Log.d(TAG, "LocalizadoresBySubinventario::onPostExecute");
            mView.hideProgres();
            mView.fillSubinventarios(result);
        }

         */
    }

    //private class CloseConteoCiclico implements AppTask<Long> {
    private class CloseConteoCiclico  {
        private Long id;
        private MtlCycleCountHeaders header;
        private List<MtlCycleCountEntries> entries;
        private OrganizacionPrincipal organizacionPrincipal;
        private MtlSystemItems mtlSystemItems;

        public CloseConteoCiclico(Long id, MtlCycleCountHeaders header, List<MtlCycleCountEntries> entries, OrganizacionPrincipal organizacionPrincipal, MtlSystemItems mtlSystemItems) {
            this.id = id;
            this.header = header;
            this.entries = entries;
            this.organizacionPrincipal = organizacionPrincipal;
            this.mtlSystemItems = mtlSystemItems;

        }


        public void cerrar_conteo() throws ServiceException {

            String archivo = mService.closeConteoCiclicov2(this.id,this.header,this.entries,this.organizacionPrincipal,this.mtlSystemItems);

        }


        /*
        @Override
        public Long execute() {
            Log.d(TAG, "CloseConteoCiclico::execute");
            Long salida = 0L;
            try {
                String archivo = mService.closeConteoCiclico(this.id);
                MediaScannerConnection.scanFile(mView, new String[] {archivo}, null, null);
            } catch (ServiceException e) {
                Log.d(TAG, "CloseConteoCiclico::execute::ServiceException");
                e.printStackTrace();
                mView.runOnUiThread(new Runnable() {
                    public void run() {
                        mView.showError(e.getDescripcion());
                    }
                });
            }
            return salida;
        }

        @Override
        public void onPostExecute(@Nullable Long result) {
            mView.hideProgres();
            if (result.longValue() == 0) {
                mView.mensajeOkCloseInventory();
            } else {
                mView.mensajeErrorCloseInventory();
            }
        }
        */


    }

}
