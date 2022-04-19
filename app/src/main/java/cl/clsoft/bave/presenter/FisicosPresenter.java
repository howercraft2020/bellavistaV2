package cl.clsoft.bave.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.clsoft.bave.apis.ApiUtils;
import cl.clsoft.bave.apis.IRestMtlPhysicalInventories;
import cl.clsoft.bave.base.BasePresenter;
import cl.clsoft.bave.exception.ServiceException;
import cl.clsoft.bave.model.MtlPhysicalInventories;
import cl.clsoft.bave.service.IInventarioFisicoService;
import cl.clsoft.bave.view.ActivityFisicos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FisicosPresenter extends BasePresenter {

    private static final String TAG = "FisicosPresenter";
    private ActivityFisicos mView;
    private IInventarioFisicoService mService;
    private IRestMtlPhysicalInventories iRestMtlPhysicalInventories;
    static List<MtlPhysicalInventories> ListMtlPhysical;

    public FisicosPresenter(@NonNull final ActivityFisicos mView, @NonNull final  IInventarioFisicoService mService) {
        this.mView = mView;
        this.mService = mService;
        this.iRestMtlPhysicalInventories = ApiUtils.getIRestMtlPhysicalInventories();
    }

    public List<MtlPhysicalInventories> getInventariosFisicos(){
        iRestMtlPhysicalInventories.getAll().enqueue(new Callback<List<MtlPhysicalInventories>>() {
            @Override
            public void onResponse(Call<List<MtlPhysicalInventories>> call, Response<List<MtlPhysicalInventories>> response) {
                if(response.isSuccessful()== true ){
                    ListMtlPhysical = response.body();
                }

            }

            @Override
            public void onFailure(Call<List<MtlPhysicalInventories>> call, Throwable t) {
                    ListMtlPhysical = null;
            }
        });

        /*try {
            return this.mService.getAllInventariosFisicos();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;*/
        return ListMtlPhysical;
}
}
