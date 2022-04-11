package cl.clsoft.bave.apis;

import cl.clsoft.bave.model.PoDistributionsAll;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestPoDistributionsAll {


    @POST("PoDistributionsAll/insert")
    Call<Void> insert(@Body PoDistributionsAll poDistributionsAll);

    @POST("PoDistributionsAll/delete/{poHeaderId}")
    Call<Void> delete(@Path("poHeaderId") Long poHeaderId);

    @GET("PoDistributionsAll/getById/{poDistributionId}")
    Call<PoDistributionsAll> getById(@Path("poDistributionId") Long poDistributionId);

    @POST("PoDistributionsAll/delete/{poHeaderId}")
    Observable<Void> deleteRx(@Path("poHeaderId") Long poHeaderId);

}