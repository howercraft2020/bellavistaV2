package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.XXPDA_MTL_TRANS_LOTS_IFACE;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRestXXPDA_MTL_TRANS_LOTS_IFACE {


    @POST("XXPDA_MTL_TRANS_LOTS_IFACE/insert")
    Call<Void> insert(@Body XXPDA_MTL_TRANS_LOTS_IFACE xxpda_mtl_trans_lots_iface);


    @POST("XXPDA_MTL_TRANS_LOTS_IFACE/insert")
    Observable<Void> insertRx(@Body XXPDA_MTL_TRANS_LOTS_IFACE xxpda_mtl_trans_lots_iface);


}
