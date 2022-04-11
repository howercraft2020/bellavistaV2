package cl.clsoft.bave.apis;
import cl.clsoft.bave.model.PoLineLocationsAll;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface IRestPoLineLocationsAll {

    @POST("PoLineLocationsAll/insert")
    Call<Void> insert(@Body PoLineLocationsAll poLineLocationsAll);

    @POST("PoLineLocationsAll/delete/{poHeaderId}")
    Call<Void> delete(@Path("poHeaderId") Long poHeaderId);

    @POST("PoLineLocationsAll/delete/{poHeaderId}")
    Observable<Void> deleteRx(@Path("poHeaderId") Long poHeaderId);

    @GET("PoLineLocationsAll/getById/{lineLocationId}")
    Call<PoLineLocationsAll> getById(@Path("lineLocationId") Long lineLocationId);


}
